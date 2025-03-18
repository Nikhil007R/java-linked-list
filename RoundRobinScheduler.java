class Process {
    int processId;
    int burstTime;
    int priority;
    int remainingTime;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private static final int TIME_QUANTUM = 4; // Fixed time quantum

    // Add a new process at the end (Circular Linked List)
    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            tail.next = head; // Circular linking
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head; // Circular linking
        }
    }

    // Remove a process by Process ID
    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes to remove!");
            return;
        }

        Process current = head, prev = null;

        do {
            if (current.processId == processId) {
                if (current == head && current == tail) { // Only one process in the list
                    head = null;
                    tail = null;
                } else if (current == head) { // Removing head
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) { // Removing tail
                    prev.next = head;
                    tail = prev;
                } else { // Removing in-between node
                    prev.next = current.next;
                }
                System.out.println("Process " + processId + " has completed execution and is removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Process ID " + processId + " not found!");
    }

    // Simulate Round Robin Scheduling
    public void executeRoundRobin() {
        if (head == null) {
            System.out.println("No processes to schedule!");
            return;
        }

        int totalTime = 0, totalWaitingTime = 0, totalTurnaroundTime = 0;
        int processCount = countProcesses();

        Process current = head;

        System.out.println("\nStarting Round Robin Scheduling (Time Quantum: " + TIME_QUANTUM + ")...");

        while (head != null) {
            Process temp = head;
            do {
                if (temp.remainingTime > 0) {
                    int executionTime = Math.min(temp.remainingTime, TIME_QUANTUM);
                    totalTime += executionTime;
                    temp.remainingTime -= executionTime;

                    System.out.println("Process " + temp.processId + " executed for " + executionTime + " units. Remaining: " + temp.remainingTime);

                    if (temp.remainingTime == 0) {
                        int turnaroundTime = totalTime;
                        int waitingTime = turnaroundTime - temp.burstTime;
                        totalTurnaroundTime += turnaroundTime;
                        totalWaitingTime += waitingTime;

                        System.out.println("Process " + temp.processId + " completed. Turnaround Time: " + turnaroundTime + ", Waiting Time: " + waitingTime);

                        removeProcess(temp.processId);
                    }
                }
                temp = temp.next;
            } while (temp != head && head != null);
        }

        // Display Average Waiting Time and Turnaround Time
        double avgWaitingTime = (double) totalWaitingTime / processCount;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processCount;

        System.out.println("\nRound Robin Scheduling Completed!");
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    // Count total processes
    public int countProcesses() {
        if (head == null) return 0;

        int count = 0;
        Process temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    // Display all processes in circular list
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process temp = head;
        System.out.println("\nProcesses in Queue:");
        do {
            System.out.println("Process ID: " + temp.processId + " | Burst Time: " + temp.burstTime + " | Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        // Adding processes
        scheduler.addProcess(1, 10, 3);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 2);
        scheduler.addProcess(4, 12, 4);

        // Display processes
        scheduler.displayProcesses();

        // Execute Round Robin Scheduling
        scheduler.executeRoundRobin();
    }
}
