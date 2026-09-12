//tree map
//keys are sorted
//put(insert), get(remove) , remove, are O(logn)
//treemap < key (K) , value (V) > hm= new TreeMap<> ();
//BST (binary search tree , self balancing trree)
//red black trees
package day3;
import java.util.*;
public class tree {
    public static void main(String args){
        TreeMap<String, Integer > tm = new TreeMap<>();
        tm.put("India", 1670);
        tm.put("vff", 544);
        tm.put("gbcg", 6567);
        tm.put("bgnh", 65);
        tm.put("bdrg", 1);
        System.out.println(tm);
    }
}
