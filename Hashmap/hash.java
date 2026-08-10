package Hashmap;

import java.util.HashMap;

public class hash {

    public static void main(String[] args) {

        HashMap<String, Integer> marks = new HashMap<>();

        marks.put("AKSHAT", 65);
        marks.put("ADITYA", 95);
        marks.put("ADARSH", 85);

        System.out.println(marks.get("ADITYA"));
    }
}