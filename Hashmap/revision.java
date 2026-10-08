package Hashmap;
import java.util.*;
public class revision {
    public static void main(String args[]){
        //create
        HashMap<String , Integer > hm = new HashMap<>();

        //insert
        hm.put("india", 100);
        hm.put("china",120);
        hm.put("us", 80);

        System.out.println(hm);

        //get - 0(1)
        int population = hm.get("india");
        System.out.println(population);

        System.out.println(hm.get("indonesia"));

        //contain key - 0(1)
        System.out.println(hm.containsKey("india")); //true
        System.out.println(hm.containsKey("indonesia"));  //false
    }

    
}
