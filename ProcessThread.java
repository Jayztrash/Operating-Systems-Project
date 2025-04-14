public class ProcessThread extends Thread {
    private int pid;
    private int burstTime;

    public ProcessThread(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }

    @Override
    public void run() {
        System.out.println("Process " + pid + " started. Burst Time: " + burstTime + "s");
        try {
            // cpu burst similation
            Thread.sleep(burstTime * 1000); 
        } catch (InterruptedException e) {
            System.out.println("Process " + pid + " was interrupted.");
        }
        System.out.println("Process " + pid + " finished.");
    }
}
