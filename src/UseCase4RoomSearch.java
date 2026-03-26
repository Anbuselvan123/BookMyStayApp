package uc4;

import java.util.HashMap;

abstract class Room {
    protected String type;
    protected int beds;
    protected double price;

    public Room(String t, int b, double p) {
        type = t; beds = b; price = p;
    }

    public void display() {
        System.out.println(type + " | Beds: " + beds + " | ₹" + price);
    }

    public String getType() {
        return type;
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room",1,1500); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room",2,2500); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room",3,5000); }
}

class RoomInventory {
    private HashMap<String,Integer> map = new HashMap<>();

    public RoomInventory() {
        map.put("Single Room",5);
        map.put("Double Room",3);
        map.put("Suite Room",0);
    }

    public int getAvailability(String t){
        return map.getOrDefault(t,0);
    }
}

class SearchService {
    RoomInventory inv;

    public SearchService(RoomInventory i){
        inv = i;
    }

    public void search(Room[] rooms){
        for(Room r: rooms){
            int a = inv.getAvailability(r.getType());
            if(a > 0){
                r.display();
                System.out.println("Available: "+a);
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args){
        RoomInventory inv = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        new SearchService(inv).search(rooms);
    }
}