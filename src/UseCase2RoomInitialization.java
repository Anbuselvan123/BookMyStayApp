/**
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Demonstrates abstraction, inheritance, and polymorphism
 * for modeling room types in a hotel booking system.
 *
 * @author Anbuselvan
 * @version 2.1
 */

// Abstract Room class
abstract class Room {

    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

// Main class (must match file name)
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App (v2.1) =====");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("\n--- Single Room ---");
        single.displayDetails();
        System.out.println("Available Rooms: " + singleAvailable);

        System.out.println("\n--- Double Room ---");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + doubleAvailable);

        System.out.println("\n--- Suite Room ---");
        suite.displayDetails();
        System.out.println("Available Rooms: " + suiteAvailable);

        System.out.println("\nApplication Terminated.");
    }
}