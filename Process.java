public enum PCBState {
    NEW,
    READY,
    RUNNING,
    BLOCKED,
    SUSPENDED,
    SUSPENDED_BLOCKED,
    TERMINATED
}

class Process {
    int pid;
    PCBState state;
    int arrivalTime;
    int burstTime;
    int priority;
    double waitingTime;
    double turnaroundTime;
    Set<Resource> resources = new HashSet<>();


    public Process(int pid, PCBState state, int arrivalTime, int burstTime, int priority, Set<Resources> resources) {
        this.pid = pid;
        this.state = state;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.resources = resources;
    }

    void activate() {
        state = PCBState.READY;
    }

    void suspend() {
        if (this.state == PCBState.READY || this.state == PCBState.RUNNING) {
            this.state = PCBState.SUSPENDED;
        } elif (this.state == PCBState.BLOCKED) {
            this.state = PCBState.SUSPENDED_BLOCKED;
        }
        //scheduler();
    }

    void reactivate() {
        if (this.state == PCBState.SUSPENDED) {
            this.state = PCBState.READY
        } elif (this.state == PCBState.SUSPENDED_BLOCKED) {
            this.state = PCBState.BLOCKED;
        }
    }

    void request(Resource r) {
        if (this.state == PCBState.READY || this.state == PCBState.RUNNING) {
            if (r.state = ResourceState.FREE) {
                r.state = ResourceState.ALLOCATED
            }
        }
    }


}
