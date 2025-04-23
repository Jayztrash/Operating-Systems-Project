import java.io.File;
import java.util.*;

// reads process data and simulates each process as a thread
public class ThreadSimulator {
    public static void main(String[] args) {
        List<ProcessThread> threads = new ArrayList<>();

        // reads from processes.txt file
        try (Scanner scanner = new Scanner(new File("processes.txt"))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip the header line

            while (scanner.hasNextLine()) {
                // splits each line into parts
                String[] parts = scanner.nextLine().trim().split("\\s+");
                if (parts.length >= 3) {
                    int pid = Integer.parseInt(parts[0]);
                    int arrivalTime = Integer.parseInt(parts[1]); // Not used in this simulation
                    int burstTime = Integer.parseInt(parts[2]);

                    // creates and starts a new thread for the process
                    ProcessThread thread = new ProcessThread(pid, burstTime);
                    threads.add(thread);
                    thread.start();
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // waits for all threads to finish
        for (ProcessThread t : threads) {
            try {
               
                // waits for a single thread to finish
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + t.getName());
            }
        }

        System.out.println("All processes completed.");
    }
}
