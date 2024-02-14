package g0901_1000.s0904_fruit_into_baskets;

// #Medium #Array #Hash_Table #Sliding_Window #2022_03_28_Time_6_ms_(95.60%)_Space_69.2_MB_(76.55%)

public class Solution {
	//@ requires(*The input array `fruits` is not null.*);
	//@ requires(*The length of the input array `fruits` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `fruits` are non-negative integers.*);
	//@ requires(*The elements in the input array `fruits` are less than the length of the input array `fruits`.*);
	//@ ensures(*The output is an integer representing the maximum number of fruits that can be picked.*);
	//@ ensures(*The output is greater than or equal to 0.*);
    public int totalFruit(int[] fruits) {
        int end = 1;
        int basket1 = fruits[0];
        int basket2 = -1;
        int secondFruitIndex = -1;
        int maxTotal = 1;
        int counter = 1;
        while (end < fruits.length) {
            if (fruits[end - 1] != fruits[end]) {
                if (basket2 == -1) {
                    basket2 = fruits[end];
                    secondFruitIndex = end;
                    counter++;
                } else if (fruits[end] == basket1) {
                    basket1 = basket2;
                    basket2 = fruits[end];
                    secondFruitIndex = end;
                    counter++;
                } else {
                    counter = end - secondFruitIndex + 1;
                    basket1 = basket2;
                    basket2 = fruits[end];
                    secondFruitIndex = end;
                }
            } else {
                counter++;
            }
            end++;
            maxTotal = Math.max(maxTotal, counter);
        }
        return maxTotal;
    }
}