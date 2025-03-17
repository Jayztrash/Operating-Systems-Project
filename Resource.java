import java.util.*

public enum ResourceState {
    FREE,
    ALLOCATED
}


class Resource {
    ResourceState state;
    LinkedList<Process> waitingList;

    Resource(ResourceState state, LinkedList<Process> waitingList) {
        this.state = FREE;
        this.waitingList = waitingList;
    }
}