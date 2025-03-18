class TextState {
    String content;
    TextState next;
    TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextState head;  // First state
    private TextState current;  // Current state
    private static final int MAX_HISTORY = 10;  // Max states
    private int historyCount = 0;  // Current count of stored states

    public TextEditor() {
        head = null;
        current = null;
    }

    // Add a new text state (like typing a new sentence)
    public void addTextState(String newText) {
        TextState newState = new TextState(newText);

        if (head == null) {
            head = newState;
            current = newState;
        } else {
            // If redo steps exist, clear them before adding a new state
            if (current.next != null) {
                current.next = null;
            }
            newState.prev = current;
            current.next = newState;
            current = newState;
        }

        // Maintain history limit
        historyCount++;
        if (historyCount > MAX_HISTORY) {
            head = head.next;  // Remove the oldest state
            head.prev = null;
            historyCount--;
        }
    }

    // Undo: Move to the previous state
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("No more undo steps available!");
        }
    }

    // Redo: Move to the next state
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("No more redo steps available!");
        }
    }

    // Display the current text state
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("No text available.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        // Simulating user input
        editor.addTextState("Hello");
        editor.addTextState("Hello, World!");
        editor.addTextState("Hello, World! This is a text editor.");
        editor.addTextState("Hello, World! This is a simple text editor.");

        editor.displayCurrentState(); // Latest text

        // Undo operations
        editor.undo();
        editor.undo();
        editor.displayCurrentState();

        // Redo operations
        editor.redo();
        editor.displayCurrentState();

        // Adding more states (pushing old history out)
        for (int i = 1; i <= 10; i++) {
            editor.addTextState("Version " + i);
        }
        editor.displayCurrentState(); // Latest version

        // Trying to undo beyond history
        for (int i = 0; i < 12; i++) {
            editor.undo();
        }
    }
}
