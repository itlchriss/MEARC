package g0701_0800.s0721_accounts_merge;

// #Medium #Array #String #Depth_First_Search #Breadth_First_Search #Union_Find
// #2022_03_24_Time_71_ms_(31.21%)_Space_68.3_MB_(18.21%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `accounts` is not null.*);
//@ ensures(*Each element `accounts[i]` in the `accounts` list is not null.*);
//@ ensures(*The first element `accounts[i][0]` in each `accounts[i]` list is a non-empty string representing a name.*);
//@ ensures(*The rest of the elements in each `accounts[i]` list are non-empty strings representing emails.*);
//@ ensures(*The length of the `accounts` list is between 1 and 1000.*);
//@ ensures(*The length of each `accounts[i]` list is between 2 and 10.*);
//@ ensures(*The length of each email `accounts[i][j]` (for j > 0) is between 1 and 30.*);
//@ ensures(*The characters in `accounts[i][0]` are all English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list is not null.*);
//@ ensures(*The returned list contains the merged accounts in the specified format.*);
//@ ensures(*The first element of each account in the returned list is the name.*);
//@ ensures(*The rest of the elements in each account in the returned list are emails in sorted order.*);
//@ ensures(*The accounts in the returned list can be in any order.*);
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack<>();
                stack.push(email);
                List<String> component = new ArrayList<>();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei : graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}