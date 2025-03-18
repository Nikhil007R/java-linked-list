class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    public Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head;

    // Insert at the beginning
    public void insertAtBeginning(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Insert at the end
    public void insertAtEnd(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    // Insert at a specific position
    public void insertAtPosition(int rollNo, String name, int age, char grade, int position) {
        if (position <= 0) {
            insertAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNo, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds!");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    // Delete a student by roll number
    public void deleteByRollNumber(int rollNo) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }
        Student temp = head, prev = null;
        while (temp != null && temp.rollNo != rollNo) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Student not found!");
            return;
        }
        prev.next = temp.next;
    }

    // Search for a student by roll number
    public void searchByRollNumber(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found!");
    }

    // Display all students
    public void displayStudents() {
        if (head == null) {
            System.out.println("No students in the list!");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    // Update a student's grade by roll number
    public void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found!");
    }

    public static void main(String[] args) {
        StudentList list = new StudentList();
        list.insertAtEnd(1, "Aman", 20, 'A');
        list.insertAtEnd(2, "Naman", 19, 'B');
        list.insertAtBeginning(3, "Piyush", 21, 'C');
        list.insertAtPosition(4, "Sanya", 20, 'B', 2);

        System.out.println("All Student Records:");
        list.displayStudents();

        System.out.println("\nSearching for Roll No 2:");
        list.searchByRollNumber(2);

        System.out.println("\nUpdating Grade for Roll No 1:");
        list.updateGrade(1, 'A');
        list.displayStudents();

        System.out.println("\nDeleting Roll No 103:");
        list.deleteByRollNumber(103);
        list.displayStudents();
    }
}
