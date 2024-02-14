package g1101_1200.s1125_smallest_sufficient_team;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Bitmask
// #2023_06_01_Time_3_ms_(98.50%)_Space_41.4_MB_(87.22%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private List<Integer> ans = new ArrayList<>();
	//@ requires(*The `skills` array is not null.*);
	//@ requires(*The `people` list is not null.*);
	//@ requires(*The `skills` array and `people` list are not empty.*);
	//@ requires(*The length of `req_skills` is greater than or equal to 1.*);
	//@ requires(*The length of each string in `req_skills` is greater than or equal to 1.*);
	//@ requires(*Each string in `req_skills` consists of lowercase English letters.*);
	//@ requires(*The length of `people` is greater than or equal to 1.*);
	//@ requires(*The length of each list in `people` is greater than or equal to 0.*);
	//@ requires(*The length of each string in each list in `people` is greater than or equal to 1.*);
	//@ requires(*Each string in each list in `people` consists of lowercase English letters.*);
	//@ requires(*Each skill in each list in `people` is a skill in `req_skills`.*);
	//@ ensures(*The returned array is not null.*);
	//@ ensures(*The returned array is not empty.*);
	//@ ensures(*The length of the returned array is less than or equal to the length of `people`.*);
	//@ ensures(*Each element in the returned array is a valid index of a person in the `people` list.*);
	//@ ensures(*For every required skill in `req_skills`, there is at least one person in the returned array who has that skill.*);

    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int n = skills.length;
        int m = people.size();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(skills[i], i);
        }
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            List<String> list = people.get(i);
            int val = 0;
            for (String skill : list) {
                val |= 1 << map.get(skill);
            }
            arr[i] = val;
        }
        boolean[] banned = new boolean[m];
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int val = arr[i] | arr[j];
                if (val == arr[i]) {
                    banned[j] = true;
                } else if (val == arr[j]) {
                    banned[i] = true;
                }
            }
        }
        helper(0, n, arr, new ArrayList<>(), banned);
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private void helper(int cur, int n, int[] arr, List<Integer> list, boolean[] banned) {
        if (cur == (1 << n) - 1) {
            if (ans.isEmpty() || ans.size() > list.size()) {
                ans = new ArrayList<>(list);
            }
            return;
        }
        if (!ans.isEmpty() && list.size() >= ans.size()) {
            return;
        }
        int zero = 0;
        while (((cur >> zero) & 1) == 1) {
            zero++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (banned[i]) {
                continue;
            }
            if (((arr[i] >> zero) & 1) == 1) {
                list.add(i);
                helper(cur | arr[i], n, arr, list, banned);
                list.remove(list.size() - 1);
            }
        }
    }
}