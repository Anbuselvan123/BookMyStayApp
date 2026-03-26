import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates use of Queue to handle booking requests
 * in FIFO order without modifying inventory.
 *
 * @author Anbuselvan
 * @version 5.0
 */

// Reservation class
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// Booking Request Queue
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // Display all requests (without removing)
    public void displayQueue() {
        System.out.println("\n===== Booking Request Queue =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class (must match file name)
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App (v5.0) =====");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Anbu", "Single Room");
        Reservation r2 = new Reservation("Kumar", "Double Room");
        Reservation r3 = new Reservation("Priya", "Suite Room");

        // Add requests (FIFO order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("\nAll requests are stored in FIFO order.");
        System.out.println("No room allocation is done in this stage.");
    }
}