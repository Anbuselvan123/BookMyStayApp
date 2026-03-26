import java.util.HashMap;

/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates use of HashMap for managing room availability
 * as a single source of truth.
 *
 * @author Anbuselvan
 * @version 3.1
 */

// Inventory class (centralized management)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found.");
        }
    }

    // Display all inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory =====");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }
}

// Main class (must match file name)
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App (v3.1) =====");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example: Update availability
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        // Example: Get availability
        System.out.println("\nAvailable Suite Rooms: " +
                inventory.getAvailability("Suite Room"));

        System.out.println("\nApplication Terminated.");
    }
}