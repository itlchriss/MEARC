package g2901_3000.s2933_high_access_employees;

// #Medium #Array #String #Hash_Table #Sorting #2024_01_02_Time_9_ms_(87.94%)_Space_45.6_MB_(5.79%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private boolean isPossible(int a, int b) {
        int hb = b / 100;
        int ha = a / 100;
        int mind = b % 100;
        int mina = a % 100;
        if (hb == 23 && ha == 0) {
            return false;
        }
        if (hb - ha > 1) {
            return false;
        }
        if (hb - ha == 1) {
            mind += 60;
        }
        return mind - mina < 60;
    }

    private boolean isHighAccess(List<Integer> list) {
        if (list.size() < 3) {
            return false;
        }
        int i = 0;
        int j = 1;
        int k = 2;
        while (k < list.size()) {
            int a = list.get(i++);
            int b = list.get(j++);
            int c = list.get(k++);
            if (isPossible(a, c) && isPossible(b, c) && isPossible(a, b)) {
                return true;
            }
        }
        return false;
    }

    private int stringToInt(String str) {
        int i = 1000;
        int val = 0;
        for (char ch : str.toCharArray()) {
            int n = ch - '0';
            val += i * n;
            i = i / 10;
        }
        return val;
    }
	//@ requires(*1. The input `accessTimes` is a 2D array of strings.*);
	//@ requires(*2. The size of `accessTimes` is `n`.*);
	//@ requires(*3. Each element `accessTimes[i]` is an array of length 2.*);
	//@ requires(*4. `accessTimes[i][0]` represents the name of an employee.*);
	//@ requires(*5. `accessTimes[i][1]` represents the access time of that employee.*);
	//@ requires(*6. All entries in `accessTimes` are within the same day.*);
	//@ requires(*7. The access time is represented as four digits using a 24-hour time format.*);
	//@ requires(*8. The access times at the start and end of the day are not counted within the same one-hour period.*);
	//@ ensures(*1. The method returns a list of strings.*);
	//@ ensures(*2. The list contains the names of high-access employees.*);
	//@ ensures(*3. The order of the names in the list can be any order.*);
	//@ ensures(*4. An employee is considered high-access if they have accessed the system three or more times within a one-hour period.*);
	//@ ensures(*5. Times with exactly one hour of difference are not considered part of the same one-hour period.*);

    public List<String> findHighAccessEmployees(List<List<String>> accessTimes) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (List<String> list : accessTimes) {
            List<Integer> temp = map.getOrDefault(list.get(0), new ArrayList<>());
            int val = stringToInt(list.get(1));
            temp.add(val);
            map.put(list.get(0), temp);
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> temp = entry.getValue();
            Collections.sort(temp);
            if (isHighAccess(temp)) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}