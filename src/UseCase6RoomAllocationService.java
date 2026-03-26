import java.util.*;

// Booking Request Class
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " -> " + inventory.get(type));
        }
    }
}

// Booking Service
public class UseCase6RoomAllocationService {

    // Queue for FIFO booking requests
    private static Queue<BookingRequest> requestQueue = new LinkedList<>();

    // Map: RoomType -> Set of allocated Room IDs
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to ensure uniqueness of room IDs
    private static Set<String> allRoomIds = new HashSet<>();

    private static InventoryService inventoryService = new InventoryService();

    public static void main(String[] args) {

        // Sample booking requests
        requestQueue.add(new BookingRequest("Alice", "Single"));
        requestQueue.add(new BookingRequest("Bob", "Double"));
        requestQueue.add(new BookingRequest("Charlie", "Single"));
        requestQueue.add(new BookingRequest("David", "Suite"));
        requestQueue.add(new BookingRequest("Eve", "Single")); // may fail

        processBookings();

        inventoryService.displayInventory();
    }

    // Process bookings in FIFO order
    public static void processBookings() {

        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll();

            System.out.println("\nProcessing booking for: " + request.customerName);

            // Check availability
            if (!inventoryService.isAvailable(request.roomType)) {
                System.out.println("❌ No rooms available for type: " + request.roomType);
                continue;
            }

            // Generate unique room ID
            String roomId = generateUniqueRoomId(request.roomType);

            // Allocate room (Atomic operation simulation)
            allocateRoom(request.roomType, roomId);

            // Update inventory immediately
            inventoryService.decrement(request.roomType);

            // Confirm booking
            System.out.println("✅ Booking Confirmed!");
            System.out.println("Customer: " + request.customerName);
            System.out.println("Room Type: " + request.roomType);
            System.out.println("Room ID: " + roomId);
        }
    }

    // Generate unique room ID
    private static String generateUniqueRoomId(String roomType) {
        String roomId;

        do {
            roomId = roomType.substring(0, 1).toUpperCase() + UUID.randomUUID().toString().substring(0, 4);
        } while (allRoomIds.contains(roomId)); // ensure uniqueness

        return roomId;
    }

    // Allocate room safely
    private static void allocateRoom(String roomType, String roomId) {

        // Add to global set
        allRoomIds.add(roomId);

        // Map room type to allocated IDs
        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        allocatedRooms.get(roomType).add(roomId);
    }
}
