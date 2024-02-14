package g1701_1800.s1773_count_items_matching_a_rule;

// #Easy #Array #String #2022_05_02_Time_3_ms_(98.33%)_Space_46.9_MB_(92.77%)

import java.util.List;

public class Solution {
	//@ requires(*The `items` list is not null.*);
	//@ requires(*The `ruleKey` string is not null.*);
	//@ requires(*The `ruleValue` string is not null.*);
	//@ requires(*The `items` list contains sublists of length 3, where each sublist contains a type, color, and name.*);
	//@ requires(*The `ruleKey` string is equal to either "type", "color", or "name".*);
	//@ requires(*All strings in the `items` list consist only of lowercase letters.*);
	//@ ensures(*The method returns an integer representing the number of items that match the given rule.*);
	//@ ensures(*The returned integer is greater than or equal to 0.*);
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        int checkRuleNum = 0;
        if (ruleKey.equals("color")) {
            checkRuleNum = 1;
        } else if (ruleKey.equals("name")) {
            checkRuleNum = 2;
        }
        for (List<String> item : items) {
            if (item.get(checkRuleNum).equals(ruleValue)) {
                ans++;
            }
        }
        return ans;
    }
}