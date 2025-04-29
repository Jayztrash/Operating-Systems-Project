public class ProcessThread extends Thread {
    int pid, burstTime;

    // initialize process info
    public ProcessThread(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }

    @Override
    public void run() {
        System.out.println("Process " + pid + " started. Burst Time: " + burstTime + "s");
        
        // simulate cpu burst with sleep
        try {
            Thread.sleep(burstTime * 1000); 
        } catch (InterruptedException e) {
            System.out.println("Process " + pid + " was interrupted.");
        }

        System.out.println("Process " + pid + " finished.");
    }
}
