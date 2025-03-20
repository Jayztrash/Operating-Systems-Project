public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <filename> <scheduling_algorithm>");
            return;
        }
        
        String filename = args[0];
        String algorithm = args[1];

        Scheduler.readProcessData(filename);

        switch (algorithm.toLowerCase()) {
            case "fcfs":
                Scheduler.fcfsScheduling();
                System.out.println("FCFS Scheduling completed.");
                break;
            case "rr":
                if (args.length < 3) {
                    System.out.println("Usage: java Main <filename> rr <time_quantum>");
                    return;
                }
                int timeQuantum = Integer.parseInt(args[2]);
                Scheduler.roundRobinScheduling(timeQuantum);
                System.out.println("Round Robin Scheduling completed.");
                break;
            default:
                System.out.println("Unknown scheduling algorithm: " + algorithm);
        }
    }
}
