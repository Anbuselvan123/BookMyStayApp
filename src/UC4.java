import java.util.HashMap;

    /**
     * Use Case 4: Room Search & Availability Check
     *
     * Demonstrates read-only access to inventory and filtering
     * available rooms without modifying system state.
     *
     * @author Anbuselvan
     * @version 4.0
     */

// -------------------- ROOM DOMAIN --------------------
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

        public String getType() {
            return type;
        }
    }

    class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1500);
        }
    }

    class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2500);
        }
    }

    class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // -------------------- INVENTORY --------------------
    class RoomInventory {

        private HashMap<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 0); // Example: unavailable
        }

        // Read-only method
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
    }

    // -------------------- SEARCH SERVICE --------------------
    class RoomSearchService {

        private RoomInventory inventory;

        public RoomSearchService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        // Search available rooms (READ ONLY)
        public void searchAvailableRooms(Room[] rooms) {

            System.out.println("===== Available Rooms =====");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.getType());

                // Defensive check: show only available rooms
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                    System.out.println("-------------------------");
                }
            }
        }
    }

    // -------------------- MAIN CLASS --------------------
    public class UseCase4RoomSearch {

        public static void main(String[] args) {

            System.out.println("===== Book My Stay App (v4.0) =====");

            // Initialize inventory
            RoomInventory inventory = new RoomInventory();

            // Create room objects
            Room[] rooms = {
                    new SingleRoom(),
                    new DoubleRoom(),
                    new SuiteRoom()
            };

            // Search service (read-only)
            RoomSearchService searchService = new RoomSearchService(inventory);

            // Perform search
            searchService.searchAvailableRooms(rooms);

            System.out.println("\nSearch Completed. No changes made to inventory.");
        }
    }

