import java.util.*;

public class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int priority;
    int remainingTime; 
    int waitingTime;
    int turnaroundTime;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; 
    }
}

