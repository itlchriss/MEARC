package g0801_0900.s0838_push_dominoes;

// #Medium #String #Dynamic_Programming #Two_Pointers
// #2022_03_24_Time_21_ms_(73.78%)_Space_54.4_MB_(50.10%)

public class Solution {
	//@ requires(*The input string `dominoes` is not null.*);
	//@ requires(*The length of the input string `dominoes` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `dominoes` are either 'L', 'R', or '.'.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The length of the output string is the same as the length of the input string.*);
	//@ ensures(*The characters in the output string are either 'L', 'R', or '.'.*);
	//@ ensures(*The output string represents the final state of the dominoes after they have been pushed.*);
    public String pushDominoes(String dominoes) {
        char[] ch = new char[dominoes.length() + 2];
        ch[0] = 'L';
        ch[ch.length - 1] = 'R';
        for (int k = 1; k < ch.length - 1; k++) {
            ch[k] = dominoes.charAt(k - 1);
        }
        int i = 0;
        int j = 1;
        while (j < ch.length) {
            while (ch[j] == '.') {
                j++;
            }
            if (ch[i] == 'L' && ch[j] == 'L') {
                while (i != j) {
                    ch[i] = 'L';
                    i++;
                }
                j++;
            } else if (ch[i] == 'R' && ch[j] == 'R') {
                while (i != j) {
                    ch[i] = 'R';
                    i++;
                }
                j++;
            } else if (ch[i] == 'L' && ch[j] == 'R') {
                i = j;
                j++;
            } else if (ch[i] == 'R' && ch[j] == 'L') {
                int rTemp = i + 1;
                int lTemp = j - 1;
                while (rTemp < lTemp) {
                    ch[rTemp] = 'R';
                    ch[lTemp] = 'L';
                    rTemp++;
                    lTemp--;
                }
                i = j;
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 1; k < ch.length - 1; k++) {
            sb.append(ch[k]);
        }
        return sb.toString();
    }
}