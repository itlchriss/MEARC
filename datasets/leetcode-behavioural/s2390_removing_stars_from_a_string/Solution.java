package g2301_2400.s2390_removing_stars_from_a_string;

// #Medium #String #Stack #Simulation #2022_09_02_Time_31_ms_(90.55%)_Space_62.6_MB_(76.40%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `s` is not empty.*);
//@ ensures(*The input string `s` contains at least one star character.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string does not contain any star characters.*);
//@ ensures(*The returned string is a unique string.*);
//@ ensures(*The length of the returned string is less than or equal to the length of the input string `s`.*);
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        int stars = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '*') {
                ++stars;
            } else if (stars > 0) {
                --stars;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.reverse().toString();
    }
}