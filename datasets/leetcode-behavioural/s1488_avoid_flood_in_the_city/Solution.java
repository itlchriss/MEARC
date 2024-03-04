package g1401_1500.s1488_avoid_flood_in_the_city;

// #Medium #Array #Hash_Table #Greedy #Binary_Search #Heap_Priority_Queue #Binary_Search_II_Day_18
// #2022_04_05_Time_82_ms_(75.08%)_Space_64.3_MB_(81.23%)

import java.util.HashMap;
import java.util.TreeSet;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `rains` is not null.*);
//@ ensures(*The length of the input array `rains` is greater than or equal to 1.*);
//@ ensures(*The elements of the input array `rains` are integers.*);
//@ ensures(*The elements of the input array `rains` are greater than or equal to 0.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of the output array `ans` is equal to the length of the input array `rains`.*);
//@ ensures(*The elements of the output array `ans` are integers.*);
//@ ensures(*The elements of the output array `ans` are either -1 or a valid lake number.*);
//@ ensures(*If `rains[i] > 0`, then `ans[i]` is equal to -1.*);
//@ ensures(*If `rains[i] == 0`, then `ans[i]` is a valid lake number that can be dried.*);
//@ ensures(*If it is impossible to avoid flood, the output array `ans` is empty.*);
    public int[] avoidFlood(int[] rains) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        TreeSet<Integer> tree = new TreeSet<>();
        int[] ans = new int[rains.length];
        for (int i = 0; i < rains.length; i = i + 1) {
            int val = rains[i];
            if (val != 0) {
                if (hm.containsKey(val)) {
                    int mapVal = hm.get(val);
                    if (tree.ceiling(mapVal) != null) {
                        ans[tree.ceiling(mapVal)] = val;
                        hm.put(val, i);
                        tree.remove(tree.ceiling(mapVal));
                    } else {
                        return new int[0];
                    }
                } else {
                    hm.put(val, i);
                }
                ans[i] = -1;
            } else {
                tree.add(i);
            }
        }
        for (int tr : tree) {
            ans[tr] = 1;
        }
        return ans;
    }
}