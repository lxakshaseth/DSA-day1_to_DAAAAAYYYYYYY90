import java.util.*;
public class hashing{
    public static void main(String args[]){
        //create
        HashMap < String , Integer > map = new HashMap<>();
        //insert
        map.put("india", 100);
        map.put("bhutan", 10);
        map.put("usa", 105);
        map.put("nepal", 14);
        map.put("russia", 199);
        map.put("pak", 104);
        map.put("america", 80);

        System.out.println(map);

        // get -O(1)
        int population = map.get("india");
        System.out.println("population of india : " +population );

        // int ppopulation = map.get("indonesia");
        // System.out.println("population of indonesia : " +ppopulation );


        //containsKey - O(1)
        System.out.println(map.containsKey("india"));
        System.out.println(map.containsKey("indonesia"));

        //remove - O(1)
        System.out.println(map.remove("pak"));
        System.out.println(map);

        //size

        System.out.println(map.size());
    }
}