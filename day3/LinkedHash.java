//keys are insertion ordered
//linkedHashMap < >hm = new LinkedHashMap <> ();

package day3;
import java.util.*;
public class LinkedHash {
    public static void main(String args[]){
        LinkedHashMap<String, Integer > map = new LinkedHashMap<>();
        map.put("india", 160);
        map.put("indo", 60);
        map.put("nepal", 5);
        System.out.println(map);

        //hash map
        HashMap<String, Integer > mapp = new HashMap<>();
        mapp.put("india", 160);
        mapp.put("indo", 60);
        mapp.put("nepal", 5);
        System.out.println(map);
    }
    
}
