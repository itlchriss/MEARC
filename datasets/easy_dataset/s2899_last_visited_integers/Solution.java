package g2801_2900.s2899_last_visited_integers;

// #Easy #Array #String #Simulation #2023_12_20_Time_2_ms_(96.41%)_Space_44.5_MB_(5.24%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `words` is not null.*);
//@ ensures(*The input list `words` contains at least one element.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list is not null.*);
//@ ensures(*The length of the returned list is equal to the number of occurrences of the string "prev" in the input list `words`.*);
//@ ensures(*The elements in the returned list are integers.*);
//@ ensures(*The last visited integer for each occurrence of the string "prev" is correctly calculated according to the given rules.*);
//@ ensures(*If the number of consecutive "prev" strings is greater than the total visited integers, the last visited integer is -1.*);
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<String> prevEle = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < words.size(); i++) {
            if (!words.get(i).equals("prev")) {
                count = 0;
                prevEle.add(words.get(i));
                continue;
            }
            if (count >= prevEle.size()) {
                res.add(-1);
            } else {
                res.add(Integer.parseInt(prevEle.get(prevEle.size() - count - 1)));
            }
            count++;
        }
        return res;
    }
}