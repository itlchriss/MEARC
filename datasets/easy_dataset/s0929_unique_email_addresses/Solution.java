package g0901_1000.s0929_unique_email_addresses;

// #Easy #Array #String #Hash_Table #2022_03_29_Time_10_ms_(92.59%)_Space_45.3_MB_(79.48%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `emails` is not null.*);
//@ ensures(*The length of the input array `emails` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `emails` is not null.*);
//@ ensures(*Each element in the input array `emails` is a valid email address.*);
//@ ensures(*Each element in the input array `emails` contains exactly one '@' character.*);
//@ ensures(*Each element in the input array `emails` has a non-empty local name.*);
//@ ensures(*Each element in the input array `emails` does not start with a '+' character.*);
//@ ensures(*Each element in the input array `emails` has a domain name that ends with the ".com" suffix.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the number of different addresses that actually receive mails.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the length of the input array `emails`.*);
//@ ensures(*The output is the number of unique email addresses after applying the rules for adding periods and ignoring characters after the first '+' sign.*);
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String s : emails) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < s.length()) {
                char c = s.charAt(i);
                if (c == '+' || c == '@') {
                    sb.append('@');
                    i = s.indexOf("@") + 1;
                    sb.append(s.substring(i));
                    break;
                } else if (c != '.') {
                    sb.append(c);
                }
                i++;
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}