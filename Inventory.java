class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head;

    // Insert at the beginning
    public void insertAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Insert at the end
    public void insertAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Insert at a specific position
    public void insertAtPosition(String itemName, int itemId, int quantity, double price, int position) {
        if (position <= 0) {
            insertAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        Item temp = head;
        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds!");
            return;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Remove item by Item ID
    public void removeItem(int itemId) {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Item temp = head, prev = null;
        while (temp != null && temp.itemId != itemId) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Item not found!");
            return;
        }
        prev.next = temp.next;
    }

    // Update quantity by Item ID
    public void updateQuantity(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found!");
    }

    // Search item by Item ID
    public void searchByItemId(int itemId) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("Found: " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found!");
    }

    // Search item by Item Name
    public void searchByItemName(String itemName) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Found: " + temp.itemName + " (ID: " + temp.itemId + ") | Quantity: " + temp.quantity + " | Price: " + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No items found with name: " + itemName);
    }

    // Calculate total inventory value
    public void calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: ₹" + totalValue);
    }

    // Sort inventory by Item Name (Ascending)
    public void sortByItemName() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if (temp.itemName.compareToIgnoreCase(temp.next.itemName) > 0) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    // Sort inventory by Price (Ascending)
    public void sortByPrice() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if (temp.price > temp.next.price) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    // Swap function for sorting
    private void swap(Item a, Item b) {
        String tempName = a.itemName;
        int tempId = a.itemId;
        int tempQty = a.quantity;
        double tempPrice = a.price;

        a.itemName = b.itemName;
        a.itemId = b.itemId;
        a.quantity = b.quantity;
        a.price = b.price;

        b.itemName = tempName;
        b.itemId = tempId;
        b.quantity = tempQty;
        b.price = tempPrice;
    }

    // Display all items
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.itemId + " | Name: " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: ₹" + temp.price);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding items
        inventory.insertAtEnd("Laptop", 101, 10, 50000);
        inventory.insertAtEnd("Phone", 102, 20, 20000);
        inventory.insertAtBeginning("Tablet", 103, 15, 30000);
        inventory.insertAtPosition("Monitor", 104, 5, 15000, 2);

        // Display inventory
        System.out.println("\nAll Items:");
        inventory.displayInventory();

        // Searching
        System.out.println("\nSearching for Item ID 102:");
        inventory.searchByItemId(102);

        System.out.println("\nSearching for 'Laptop':");
        inventory.searchByItemName("Laptop");

        // Updating quantity
        System.out.println("\nUpdating quantity for Item ID 101 to 12:");
        inventory.updateQuantity(101, 12);
        inventory.displayInventory();

        // Calculating total value
        System.out.println("\nCalculating total inventory value:");
        inventory.calculateTotalValue();

        // Sorting
        System.out.println("\nSorting by Item Name:");
        inventory.sortByItemName();
        inventory.displayInventory();

        System.out.println("\nSorting by Price:");
        inventory.sortByPrice();
        inventory.displayInventory();
    }
}
