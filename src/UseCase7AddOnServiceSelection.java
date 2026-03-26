import java.util.*;

// Add-On Service Class
class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    // Add services to a reservation
    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    // Get services for a reservation
    public List<AddOnService> getServices(String reservationId) {
        return reservationServices.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total additional cost
    public double calculateTotalCost(String reservationId) {
        double total = 0.0;
        for (AddOnService service : getServices(reservationId)) {
            total += service.cost;
        }
        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("\nAdd-On Services for Reservation " + reservationId + ":");
        for (AddOnService service : services) {
            System.out.println(" - " + service);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample reservation IDs (from Use Case 6)
        String res1 = "R1001";
        String res2 = "R1002";

        // Create Add-On Services
        AddOnService breakfast = new AddOnService("Breakfast", 250);
        AddOnService wifi = new AddOnService("WiFi", 100);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 500);
        AddOnService extraBed = new AddOnService("Extra Bed", 300);

        // Guest selects services
        manager.addService(res1, breakfast);
        manager.addService(res1, wifi);
        manager.addService(res1, airportPickup);

        manager.addService(res2, wifi);
        manager.addService(res2, extraBed);

        // Display results
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}