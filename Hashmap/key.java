package Hashmap;

import java.util.*;

public class key {
public static void main(String[] args) {
    HashMap<String, Integer > hm = new HashMap <> ();
    hm.put("india", 543);
    hm.put("USA", 54);
    hm.put("vsff",343);
    hm.put("bgfc", 53);
    hm.put("fgbs",655);
    hm.put("vfsvs", 679);

    Set<String > keys = hm.keySet();
    System.out.println(keys);
    for(String k : keys){
        System.out.println("key" + k + ", value"+hm.get(k));
        
    }
    }


}