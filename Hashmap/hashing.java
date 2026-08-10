package Hashmap;

import java.util.*;

public class hashing {
        public static void main(String args[]){
            //country(key ) , population(value)
            HashMap<String , Integer > map = new HashMap<>();


            //insertion
            map.put("India",120);
            map.put("SRi",90);
            map.put("nepal",20);
            map.put("usa",190);

            System.out.println(map);
            map.put("India",160);
            System.out.println(map);

            //search
            if(map.containsKey("India")){
                System.out.println("key is present in the map");

            } else {
                System.out.println("key is not found in map");
            }
            System.out.println(map.get("nepal")); //key exist
            System.out.println(map.get("indo")); // keys not exist

            int arr [] = {12, 15 , 16};
            for(int i = 0; i < 3 ; i++){
                System.out.println();
            }
            System.out.println();
            for(int val : arr){
                System.out.print(val + " ");
            }
            System.out.println();

        }
}
