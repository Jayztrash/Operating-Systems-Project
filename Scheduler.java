import java.util.*;

public class Scheduler {
    private static List<Process> processList = new ArrayList<>();

    public static void readProcessData(String fileName) {
        processList = ProcessReader.readProcesses(fileName);
    }

     // FCFS Scheduling
    public static void fcfsScheduling() {
        processList.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int time = 0;
        System.out.println("\nFCFS Scheduling Completed.");
        System.out.println("Gantt Chart:");
        System.out.print("|");

        for (Process p : processList) {
            p.waitingTime = time - p.arrivalTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;

            // prints gantt chart
            System.out.print(" P" + p.pid + " |");
            time += p.burstTime;
        }
        System.out.println();

        // prints time
        time = 0;
        System.out.print("0");
        for (Process p : processList) {
            time += p.burstTime;
            System.out.printf("   %d", time);
        }
        System.out.println("\n");

        // prints time details
        System.out.println("Process\tWT\tTAT");
        int totalWT = 0, totalTAT = 0;
        for (Process p : processList) {
            System.out.println("P" + p.pid + "\t" + p.waitingTime + "\t" + p.turnaroundTime);
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // prints the 2 averages we need
        System.out.printf("\nAverage WT: %.2f\n", (double) totalWT / processList.size());
        System.out.printf("Average TAT: %.2f\n", (double) totalTAT / processList.size());
    }

    // rr scheduling
    public static void roundRobinScheduling(int timeQuantum) {
        Queue<Process> queue = new LinkedList<>(processList);
        int time = 0;
        List<String> ganttChart = new ArrayList<>();
        Map<Integer, Integer> completionTime = new HashMap<>();

        System.out.println("\nRound Robin Scheduling (Time Quantum = " + timeQuantum + ")");

        while (!queue.isEmpty()) {
            Process p = queue.poll();
            
            if (p.remainingTime > timeQuantum) {
                time += timeQuantum;
                p.remainingTime -= timeQuantum;
                queue.add(p); 
                ganttChart.add("P" + p.pid);
            } else {
                time += p.remainingTime;
                p.remainingTime = 0;
                p.turnaroundTime = time - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                completionTime.put(p.pid, time);
                ganttChart.add("P" + p.pid);
            }
        }

        // prints chart
        System.out.println("Gantt Chart:");
        System.out.print("|");
        for (String p : ganttChart) {
            System.out.print(" " + p + " |");
        }
        System.out.println();

        
        int t = 0;
        System.out.print("0");
        for (String p : ganttChart) {
            t += timeQuantum; 
            System.out.printf("   %d", t);
        }
        System.out.println("\n");

        // prints details
        System.out.println("Process\tWT\tTAT");
        int totalWT = 0, totalTAT = 0;
        for (Process p : processList) {
            System.out.println("P" + p.pid + "\t" + p.waitingTime + "\t" + p.turnaroundTime);
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // prints averages
        System.out.printf("\nAverage WT: %.2f\n", (double) totalWT / processList.size());
        System.out.printf("Average TAT: %.2f\n", (double) totalTAT / processList.size());
    }
}
