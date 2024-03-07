package g1401_1500.s1415_the_k_th_lexicographical_string_of_all_happy_strings_of_length_n;

// #Medium #String #Backtracking #2022_03_27_Time_2_ms_(90.55%)_Space_42.5_MB_(65.75%)

public class Solution {
    private char[] arr = new char[] {'a', 'b', 'c'};
    private String res = "";
    private int k;

    private void get(StringBuilder str, int n, int index) {
        if (k < 1) {
            return;
        }
        if (str.length() == n) {
            if (k == 1) {
                res = str.toString();
            }
            k--;
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (i == index) {
                continue;
            }
            str.append(arr[i]);
            get(str, n, i);
            str.deleteCharAt(str.length() - 1);
        }
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The value of n must be between 1 and 10 (inclusive).*);
//@ ensures(*The value of k must be between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a string that is a happy string of length n.*);
//@ ensures(*The returned string is the kth string in the list of all happy strings of length n.*);
//@ ensures(*If there are less than k happy strings of length n, the method returns an empty string.*);

    public String getHappyString(int n, int k) {
        this.k = k;
        get(new StringBuilder(), n, -1);
        return res;
    }
}