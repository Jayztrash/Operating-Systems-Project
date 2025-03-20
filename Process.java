import java.util.*;
// represents a process in our system
public class Process {
    // process id
    int pid;
    // when the process arrives in the system
    int arrivalTime;
    // how long the process needs the CPU
    int burstTime;
    // priority of the process
    int priority;
    // for rr scheduling
    int remainingTime; 
    // time spent waiting after running
    int waitingTime;
    // total time from arrival to completion
    int turnaroundTime;
    // initializes a process object with its ID, arrival time, burst time, and priority
    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; 
    }
}

