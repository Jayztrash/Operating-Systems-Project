import java.util.*;
import java.util.concurrent.Semaphore;

public class ThreadSimulator {
    public static void main(String[] args) throws InterruptedException {
        final int bufferSize = 5;
        final int numProducers = 2;
        final int numConsumers = 2;
        final int itemsPerProducer = 10;

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(bufferSize);
        List<Thread> threads = new ArrayList<>();

        // start producers
        for (int i = 1; i <= numProducers; i++) {
            Thread producer = new Producer(buffer, itemsPerProducer, i);
            threads.add(producer);
            producer.start();
        }

        // start consumers
        for (int i = 1; i <= numConsumers; i++) {
            Thread consumer = new Consumer(buffer, itemsPerProducer, i);
            threads.add(consumer);
            consumer.start();
        }

        // wait for all to finish
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All processes completed.");
    }

    // buffer using semaphores and mutex
    static class BoundedBuffer<T> {
        private final T[] buffer;
        private int putPtr = 0, takePtr = 0, count = 0;
        private final Semaphore mutex = new Semaphore(1);
        private final Semaphore items;
        private final Semaphore spaces;

        @SuppressWarnings("unchecked")
        public BoundedBuffer(int capacity) {
            buffer = (T[]) new Object[capacity];
            items = new Semaphore(0);
            spaces = new Semaphore(capacity);
        }

        public void put(T item) throws InterruptedException {
            System.out.printf("[%s] Waiting for space...%n", Thread.currentThread().getName());
            spaces.acquire();
            System.out.printf("[%s] Acquired space.%n", Thread.currentThread().getName());

            mutex.acquire();
            System.out.printf("[%s] Acquired mutex to put.%n", Thread.currentThread().getName());

            buffer[putPtr] = item;
            putPtr = (putPtr + 1) % buffer.length;
            count++;
            System.out.printf("[%s] Put item %s. Buffer size: %d%n", Thread.currentThread().getName(), item, count);

            mutex.release();
            System.out.printf("[%s] Released mutex after put.%n", Thread.currentThread().getName());

            items.release();
        }

        public T take() throws InterruptedException {
            System.out.printf("[%s] Waiting for items...%n", Thread.currentThread().getName());
            items.acquire();
            System.out.printf("[%s] Acquired item permit.%n", Thread.currentThread().getName());

            mutex.acquire();
            System.out.printf("[%s] Acquired mutex to take.%n", Thread.currentThread().getName());

            T item = buffer[takePtr];
            takePtr = (takePtr + 1) % buffer.length;
            count--;
            System.out.printf("[%s] Took item %s. Buffer size: %d%n", Thread.currentThread().getName(), item, count);

            mutex.release();
            System.out.printf("[%s] Released mutex after take.%n", Thread.currentThread().getName());

            spaces.release();
            return item;
        }
    }

    static class Producer extends Thread {
        private final BoundedBuffer<Integer> buffer;
        private final int itemsToProduce;

        public Producer(BoundedBuffer<Integer> buffer, int itemsToProduce, int id) {
            super("Producer-" + id);
            this.buffer = buffer;
            this.itemsToProduce = itemsToProduce;
        }

        @Override
        public void run() {
            for (int i = 1; i <= itemsToProduce; i++) {
                try {
                    buffer.put(i);
                    Thread.sleep(500); // simulate work
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Interrupted.%n", getName());
                }
            }
            System.out.printf("[%s] Finished producing.%n", getName());
        }
    }

    static class Consumer extends Thread {
        private final BoundedBuffer<Integer> buffer;
        private final int itemsToConsume;

        public Consumer(BoundedBuffer<Integer> buffer, int itemsToConsume, int id) {
            super("Consumer-" + id);
            this.buffer = buffer;
            this.itemsToConsume = itemsToConsume;
        }

        @Override
        public void run() {
            for (int i = 1; i <= itemsToConsume; i++) {
                try {
                    buffer.take();
                    Thread.sleep(800); // simulate work
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Interrupted.%n", getName());
                }
            }
            System.out.printf("[%s] Finished consuming.%n", getName());
        }
    }
}
