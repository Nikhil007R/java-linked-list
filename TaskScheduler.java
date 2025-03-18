class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task currentTask = null; // Pointer to track the current task in the circular list

    // Insert task at the beginning
    public void insertAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head; // Circular link
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head; // Maintain circular nature
        }
    }

    // Insert task at the end
    public void insertAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head; // Maintain circular link
        }
    }

    // Insert task at a specific position
    public void insertAtPosition(int taskId, String taskName, int priority, String dueDate, int position) {
        if (position <= 0) {
            insertAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task temp = head;
        for (int i = 1; temp.next != head && i < position; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) {
            tail = newTask;
            tail.next = head;
        }
    }

    // Remove task by Task ID
    public void removeTask(int taskId) {
        if (head == null) {
            System.out.println("No tasks to remove!");
            return;
        }
        Task temp = head, prev = null;
        do {
            if (temp.taskId == taskId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Task with ID " + taskId + " removed successfully.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Task not found!");
    }

    // View the current task and move to the next task
    public void viewAndMoveToNext() {
        if (head == null) {
            System.out.println("No tasks available!");
            return;
        }
        if (currentTask == null) {
            currentTask = head;
        }
        System.out.println("Current Task: " + currentTask.taskName + " (Priority: " + currentTask.priority + ")");
        currentTask = currentTask.next;
    }

    // Display all tasks
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks available!");
            return;
        }
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Search task by Priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available!");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Found: " + temp.taskName + " (Task ID: " + temp.taskId + ", Due: " + temp.dueDate + ")");
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks
        scheduler.insertAtEnd(101, "Complete Report", 1, "2025-03-20");
        scheduler.insertAtEnd(102, "Submit Assignment", 2, "2025-03-22");
        scheduler.insertAtBeginning(103, "Prepare Presentation", 1, "2025-03-18");
        scheduler.insertAtPosition(104, "Code Review", 3, "2025-03-25", 2);

        // Display all tasks
        System.out.println("\nAll Tasks:");
        scheduler.displayTasks();

        // View and move to next task
        System.out.println("\nViewing Tasks in Circular Order:");
        scheduler.viewAndMoveToNext();
        scheduler.viewAndMoveToNext();
        scheduler.viewAndMoveToNext();

        // Searching
        System.out.println("\nSearching for tasks with priority 1:");
        scheduler.searchByPriority(1);

        // Removing a task
        System.out.println("\nRemoving Task ID 103:");
        scheduler.removeTask(103);
        scheduler.displayTasks();
    }
}
