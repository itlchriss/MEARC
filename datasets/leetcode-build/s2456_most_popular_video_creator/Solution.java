package g2401_2500.s2456_most_popular_video_creator;

// #Medium #Array #String #Hash_Table #Sorting #Heap_Priority_Queue
// #2022_12_16_Time_57_ms_(97.10%)_Space_85.2_MB_(99.42%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The lengths of the `creators`, `ids`, and `views` arrays are all equal.*);
	//@ requires(*The length of the arrays is at least 1 and at most 10^5.*);
	//@ requires(*The lengths of the `creators[i]` and `ids[i]` strings are at least 1 and at most 5.*);
	//@ requires(*The `creators[i]` and `ids[i]` strings consist only of lowercase English letters.*);
	//@ requires(*The `views[i]` values are non-negative integers.*);
	//@ ensures(*The method returns a 2D array of strings.*);
	//@ ensures(*Each element in the returned array is a 2-element array of strings.*);
	//@ ensures(*The returned array contains the creators with the highest popularity and the ids of their most viewed videos.*);
	//@ ensures(*If multiple creators have the highest popularity, all of them are included in the returned array.*);
	//@ ensures(*If multiple videos have the highest view count for a creator, the lexicographically smallest id is included in the returned array.*);
	//@ ensures(*The order of the elements in the returned array can be arbitrary.*);
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        HashMap<String, Long> totalViews = new HashMap<>();
        HashMap<String, Integer> maxView = new HashMap<>();
        long globalMaxView = 0;
        for (int i = 0; i < creators.length; i++) {
            long currentView = totalViews.getOrDefault(creators[i], 0L) + views[i];
            globalMaxView = Math.max(currentView, globalMaxView);
            totalViews.put(creators[i], currentView);
            int lastIndex = maxView.getOrDefault(creators[i], -1);
            if (!maxView.containsKey(creators[i])
                    || views[lastIndex] < views[i]
                    || (views[lastIndex] == views[i] && ids[lastIndex].compareTo(ids[i]) > 0)) {
                maxView.put(creators[i], i);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, Long> entry : totalViews.entrySet()) {
            if (entry.getValue() == globalMaxView) {
                res.add(Arrays.asList(entry.getKey(), ids[maxView.get(entry.getKey())]));
            }
        }
        return res;
    }
}