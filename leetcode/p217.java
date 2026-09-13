package leetcode;

import java.util.Arrays;

public class p217 {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        p217 obj = new p217();

        int[] nums = {1, 2, 3, 1};

        System.out.println(obj.containsDuplicate(nums));
    }
}