package g2201_2300.s2299_strong_password_checker_ii;

// #Easy #String #2022_06_15_Time_1_ms_(97.32%)_Space_42.3_MB_(38.52%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a string `password`, return `true` _if it is a **strong** password_. Otherwise, return `false`. **Explanation:** The password meets all the requirements. Therefore, we return true. **Explanation:** The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false. **Explanation:** The password does not meet the length requirement. Therefore, we return false.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean l = false;
        boolean u = false;
        boolean d = false;
        boolean s = false;
        String special = "!@#$%^&*()-+";
        char previous = '.';
        for (int i = 0; i != password.length(); i++) {
            char ch = password.charAt(i);
            if (ch == previous) {
                return false;
            }
            previous = ch;
            if (ch >= 'A' && ch <= 'Z') {
                u = true;
            } else if (ch >= 'a' && ch <= 'z') {
                l = true;
            } else if (ch >= '0' && ch <= '9') {
                d = true;
            } else if (special.indexOf(ch) != -1) {
                s = true;
            }
        }
        return l && u && d && s;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
