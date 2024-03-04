package g1601_1700.s1620_coordinate_with_maximum_network_quality;

// #Medium #Array #Enumeration #2022_06_13_Time_58_ms_(68.75%)_Space_43_MB_(43.75%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `towers` is not null.*);
//@ ensures(*The length of `towers` is greater than or equal to 1 and less than or equal to 50.*);
//@ ensures(*Each element in `towers` is an array of length 3.*);
//@ ensures(*The first element of each tower array represents the x-coordinate and is an integer between 0 and 50.*);
//@ ensures(*The second element of each tower array represents the y-coordinate and is an integer between 0 and 50.*);
//@ ensures(*The third element of each tower array represents the quality factor and is an integer between 0 and 50.*);
//@ ensures(*The input integer `radius` is between 1 and 50.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `result` is not null.*);
//@ ensures(*The length of `result` is 2.*);
//@ ensures(*The first element of `result` represents the x-coordinate and is an integer between 0 and 50.*);
//@ ensures(*The second element of `result` represents the y-coordinate and is an integer between 0 and 50.*);
//@ ensures(*The network quality at the coordinate represented by `result` is maximum.*);
//@ ensures(*If there are multiple coordinates with the same network quality, the lexicographically minimum non-negative coordinate is returned.*);
    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] res = new int[2];
        double maxQuality = 0;
        double quality;
        int finalX = 0;
        int finalY = 0;
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                quality = 0;
                for (int[] tower : towers) {
                    int x = tower[0] - i;
                    int y = tower[1] - j;
                    double dist = Math.sqrt((double) x * x + y * y);
                    if (dist <= radius) {
                        quality += Math.floor(tower[2] / (1 + dist));
                    }
                }
                if (maxQuality < quality) {
                    maxQuality = quality;
                    finalX = i;
                    finalY = j;
                }
            }
        }
        res[0] = finalX;
        res[1] = finalY;
        return res;
    }
}