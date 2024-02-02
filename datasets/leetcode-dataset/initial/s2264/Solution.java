package g2201_2300.s2264_largest_3_same_digit_number_in_string;

// #Easy #String #2022_06_15_Time_3_ms_(74.57%)_Space_42.2_MB_(79.34%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*"777" is the largest, so we return "777".
Return _the **maximum good** integer as a **string** or an empty string_ `""` _if no such integer exists_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public String largestGoodInteger(String num) {
        String maxi = "000";
        int c = 0;
        for (int i = 0; i < num.length() - 2; i++) {
            String s = num.substring(i, i + 3);
            if (s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2)) {
                if (s.compareTo(maxi) >= 0) {
                    maxi = s;
                }
                ++c;
            }
        }
        if (c == 0) {
            return "";
        }
        return maxi;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
