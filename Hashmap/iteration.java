package Hashmap;
import java.util.*;
public class iteration {
    public static void main (String[] args){
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("india", 100);
        // hm.put("", 100);
        hm.put("china",150 );
        hm.put("us", 50);
        hm.put("indonesia", 6);
        hm.put("nepal", 6);

        //iterate
        Set<String> keys = hm.keySet();
        System.out.println(keys);

        //foreach
        for(String K :keys){
            System.out.println("key=" + K +",vallue="+hm.get(K));
        }



    }
    
}
