package Hashmap;
import java.util.*;

public class classroom {

    public static void main(String args[]) {

        HashMap<String, Integer> hm = new HashMap<>();

        // insert
        hm.put("india", 500);
        hm.put("usa", 7);
        hm.put("russia", 77);

        System.out.println(hm);

        int population = hm.get("india");
        System.out.println("population");
    }
} 