package g1401_1500.s1405_longest_happy_string;

// #Medium #String #Greedy #Heap_Priority_Queue
// #2022_03_25_Time_0_ms_(100.00%)_Space_40.9_MB_(69.62%)

public class Solution {
	//@ requires(*The method takes three integers `a`, `b`, and `c` as input.*);
	//@ ensures(*The method returns a string that satisfies the following conditions:*);
	//@ ensures(*- The string only contains the letters `'a'`, `'b'`, and `'c'`.*);
	//@ ensures(*- The string does not contain any of `"aaa"`, `"bbb"`, or `"ccc"` as a substring.*);
	//@ ensures(*- The string contains at most `a` occurrences of the letter `'a'`.*);
	//@ ensures(*- The string contains at most `b` occurrences of the letter `'b'`.*);
	//@ ensures(*- The string contains at most `c` occurrences of the letter `'c'`.*);
	//@ ensures(*If there are multiple longest happy strings, the method can return any of them.*);
	//@ ensures(*If there is no such string, the method returns the empty string `""`.*);
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int[] remains = new int[] {a, b, c};
        char[] chars = new char[] {'a', 'b', 'c'};
        int preIndex = -1;
        do {
            int index;
            boolean largest;
            if (preIndex != -1
                    && remains[preIndex]
                            == Math.max(remains[0], Math.max(remains[1], remains[2]))) {
                if (preIndex == 0) {
                    index = remains[1] > remains[2] ? 1 : 2;
                } else if (preIndex == 1) {
                    index = remains[0] > remains[2] ? 0 : 2;
                } else {
                    index = remains[0] > remains[1] ? 0 : 1;
                }
                largest = false;
            } else {
                index = remains[0] > remains[1] ? 0 : 1;
                index = remains[index] > remains[2] ? index : 2;
                largest = true;
            }
            remains[index]--;
            sb.append(chars[index]);
            if (remains[index] > 0 && largest) {
                remains[index]--;
                sb.append(chars[index]);
            }
            preIndex = index;
        } while (remains[0] + remains[1] + remains[2] != remains[preIndex]);
        return sb.toString();
    }
}