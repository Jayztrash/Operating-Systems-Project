private static void roundRobinScheduling(List<Process> processes, int quantum) {
    Queue<Process> queue = new LinkedList<>(processes);
    Map<Integer, Integer> remainingBurst = new HashMap<>();
    int time = 0, totalWT = 0, totalTAT = 0;

    processes.forEach(p -> remainingBurst.put(p.pid, p.burstTime));

    System.out.print("Gantt Chart: ");
    
    while (!queue.isEmpty()) {
        Process p = queue.poll();
        int remainingTime = remainingBurst.get(p.pid);
        int executionTime = Math.min(remainingTime, quantum);

        System.out.print("| P" + p.pid + " ");
        time += executionTime;
        remainingBurst.put(p.pid, remainingTime - executionTime);

        if (remainingBurst.get(p.pid) > 0) queue.add(p);
        else {
            p.turnaroundTime = time - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }
    }
    System.out.println("|");
    
    printMetrics(processes, totalWT, totalTAT);
}

