class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next; // Pointer to next node

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;
    private int ticketCount = 0;

    // Add a new ticket reservation at the end
    public void bookTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        ticketCount++;

        if (head == null) {
            head = newTicket;
            tail = newTicket;
            tail.next = head; // Make it circular
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Maintain circular linkage
        }
        System.out.println("Ticket booked successfully: " + ticketID);
    }

    // Remove a ticket by Ticket ID
    public void cancelTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        Ticket prev = null;

        // If head itself is the ticket to be deleted
        if (head.ticketID == ticketID) {
            ticketCount--;
            if (head == tail) { // Only one node in the list
                head = null;
                tail = null;
            } else {
                tail.next = head.next;
                head = head.next;
            }
            System.out.println("Ticket " + ticketID + " cancelled.");
            return;
        }

        // Searching for the ticket
        do {
            prev = temp;
            temp = temp.next;

            if (temp.ticketID == ticketID) {
                ticketCount--;
                prev.next = temp.next;

                if (temp == tail) { // If tail is deleted, update tail
                    tail = prev;
                }
                System.out.println("Ticket " + ticketID + " cancelled.");
                return;
            }
        } while (temp != head);

        System.out.println("Ticket ID " + ticketID + " not found.");
    }

    // Display all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        System.out.println("Booked Tickets:");
        do {
            System.out.println("Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName +
                    ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber +
                    ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    // Search ticket by Customer Name or Movie Name
    public void searchTicket(String key) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(key) || temp.movieName.equalsIgnoreCase(key)) {
                System.out.println("Ticket Found - ID: " + temp.ticketID + ", Customer: " + temp.customerName +
                        ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber +
                        ", Booking Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No ticket found for: " + key);
        }
    }

    // Get total number of booked tickets
    public int totalTickets() {
        return ticketCount;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        // Booking tickets
        system.bookTicket(56, "Nikhil", "Saw", "A1", "10:30 AM");
        system.bookTicket(89, "Riya", "Interstellar", "B2", "1:00 PM");
        system.bookTicket(69, "Ishant", "Mission Impossible", "C3", "3:30 PM");

        // Display all tickets
        system.displayTickets();

        // Searching tickets
        system.searchTicket("Riya");
        system.searchTicket("Interstellar");

        // Cancel a ticket
        system.cancelTicket(69);
        system.cancelTicket(101);
        system.displayTickets();

        // Total tickets booked
        System.out.println("Total Booked Tickets: " + system.totalTickets());
    }
}
