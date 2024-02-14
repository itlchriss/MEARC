package g0601_0700.s0605_can_place_flowers;

// #Easy #Array #Greedy #Udemy_Arrays #2022_03_21_Time_1_ms_(96.77%)_Space_51.2_MB_(61.33%)

public class Solution {
	//@ requires(*The input array `flowerbed` is not null.*);
	//@ requires(*The length of `flowerbed` is greater than or equal to 1.*);
	//@ requires(*The elements of `flowerbed` are either 0 or 1.*);
	//@ requires(*There are no two adjacent flowers in `flowerbed`.*);
	//@ requires(*The value of `n` is non-negative.*);
	//@ ensures(*The method returns a boolean value indicating whether `n` new flowers can be planted in the `flowerbed` without violating the no-adjacent-flowers rule.*);
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && n > 0) {
                boolean left = i == 0 || flowerbed[i - 1] == 0;
                boolean right = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
                if (left && right) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
            if (n == 0) {
                break;
            }
        }
        return n == 0;
    }
}