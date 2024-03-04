package g2301_2400.s2391_minimum_amount_of_time_to_collect_garbage;

// #Medium #Array #String #Prefix_Sum #2022_09_02_Time_7_ms_(98.86%)_Space_92_MB_(72.81%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `garbage` is not null.*);
//@ ensures(*The input array `travel` is not null.*);
//@ ensures(*The length of `garbage` is greater than or equal to 2.*);
//@ ensures(*The length of `garbage` is less than or equal to 10^5.*);
//@ ensures(*Each element in `garbage` is a string consisting of only the characters 'M', 'P', and 'G'.*);
//@ ensures(*The length of each element in `garbage` is greater than or equal to 1.*);
//@ ensures(*The length of each element in `garbage` is less than or equal to 10.*);
//@ ensures(*The length of `travel` is equal to the length of `garbage` minus 1.*);
//@ ensures(*Each element in `travel` is an integer greater than or equal to 1.*);
//@ ensures(*Each element in `travel` is an integer less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of minutes needed to pick up all the garbage.*);
    public int garbageCollection(String[] garbage, int[] travel) {
        int cTime = 0;
        for (String str : garbage) {
            cTime += str.length();
        }
        int n = travel.length;
        for (int i = 1; i < n; i++) {
            travel[i] += travel[i - 1];
        }
        int mT = getMostTra(garbage, 'M');
        int pT = getMostTra(garbage, 'P');
        int gT = getMostTra(garbage, 'G');
        int m = mT <= 0 ? 0 : travel[mT - 1];
        int p = pT <= 0 ? 0 : travel[pT - 1];
        int g = gT <= 0 ? 0 : travel[gT - 1];
        int tTime = m + p + g;
        return cTime + tTime;
    }

    private int getMostTra(String[] garbage, char c) {
        int n = garbage.length;
        for (int i = n - 1; i >= 0; i--) {
            if (garbage[i].indexOf(c) != -1) {
                return i;
            }
        }
        return -1;
    }
}