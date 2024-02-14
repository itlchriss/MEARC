package g0301_0400.s0332_reconstruct_itinerary;

// #Hard #Depth_First_Search #Graph #Eulerian_Circuit
// #2022_07_10_Time_4_ms_(100.00%)_Space_43_MB_(91.20%)

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input list `tickets` is not null.*);
	//@ requires(*The length of `tickets` is at least 1.*);
	//@ requires(*Each element in `tickets` is a list of length 2.*);
	//@ requires(*The length of the first element in `tickets` is 3.*);
	//@ requires(*The length of the second element in `tickets` is 3.*);
	//@ requires(*The departure airport in each ticket is represented by uppercase English letters.*);
	//@ requires(*The arrival airport in each ticket is represented by uppercase English letters.*);
	//@ requires(*The departure airport in the first ticket is "JFK".*);
	//@ ensures(*The output is a list of strings.*);
	//@ ensures(*The output list contains all the airports in the itinerary in order.*);
	//@ ensures(*The output list starts with "JFK".*);
	//@ ensures(*The output list is the smallest lexical order when read as a single string.*);
	//@ ensures(*Each ticket in `tickets` is used exactly once in the itinerary.*);
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> ans = new LinkedList<>();
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dest = ticket.get(1);
            PriorityQueue<String> pq = map.getOrDefault(src, new PriorityQueue<>());
            pq.add(dest);
            map.put(src, pq);
        }
        dfs(map, "JFK", ans);
        return ans;
    }

    private void dfs(Map<String, PriorityQueue<String>> map, String src, LinkedList<String> ans) {
        PriorityQueue<String> temp = map.get(src);
        while (temp != null && !temp.isEmpty()) {
            String nbr = temp.remove();
            dfs(map, nbr, ans);
        }
        ans.addFirst(src);
    }
}