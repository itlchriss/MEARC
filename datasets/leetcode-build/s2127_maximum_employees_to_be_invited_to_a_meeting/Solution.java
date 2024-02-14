package g2101_2200.s2127_maximum_employees_to_be_invited_to_a_meeting;

// #Hard #Depth_First_Search #Graph #Topological_Sort
// #2022_06_03_Time_37_ms_(85.71%)_Space_94.2_MB_(70.83%)

import java.util.ArrayDeque;

public class Solution {
	//@ requires(*The input array `favorite` is not null.*);
	//@ requires(*The length of the input array `favorite` is greater than or equal to 2.*);
	//@ requires(*The length of the input array `favorite` is less than or equal to 10^5.*);
	//@ requires(*Each element in the input array `favorite` is an integer.*);
	//@ requires(*Each element in the input array `favorite` is between 0 and n-1, where n is the length of the input array.*);
	//@ requires(*Each element in the input array `favorite` is not equal to its corresponding index.*);
	//@ ensures(*The output is an integer representing the maximum number of employees that can be invited to the meeting.*);
    public int maximumInvitations(int[] fav) {
        int len = fav.length;
        int[] follow = new int[len];
        for (int k : fav) {
            follow[k]++;
        }
        int[] pathlen = new int[len];
        boolean[] visit = new boolean[len];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (follow[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            int j = fav[i];
            visit[i] = true;
            pathlen[j] = Math.max(pathlen[j], pathlen[i] + 1);
            if (--follow[j] == 0) {
                q.offer(j);
            }
        }
        int maxCycle = 0;
        int sumPath = 0;
        int i = 0;
        while (i < len) {
            if (visit[i]) {
                i++;
            }
            int clen = 0;
            while (!visit[i]) {
                clen++;
                visit[i] = true;
                i = fav[i];
            }
            if (clen == 2) {
                sumPath += pathlen[i] + pathlen[fav[i]] + 2;
            } else {
                maxCycle = Math.max(maxCycle, clen);
            }
            i++;
        }
        return Math.max(maxCycle, sumPath);
    }
}