package g1601_1700.s1694_reformat_phone_number;

// #Easy #String #2022_04_13_Time_1_ms_(99.67%)_Space_40.7_MB_(88.24%)

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `number` is a non-null string.*);
//@ ensures(*The length of `number` is between 2 and 100 (inclusive).*);
//@ ensures(*`number` consists of digits, spaces, and/or dashes.*);
//@ ensures(*There are at least two digits in `number`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is the reformatted phone number after removing all spaces and dashes.*);
//@ ensures(*The digits are grouped from left to right into blocks of length 3 until there are 4 or fewer digits.*);
//@ ensures(*If there are 2 digits remaining, they are grouped into a single block of length - If there are 3 digits remaining, they are grouped into a single block of length - If there are 4 digits remaining, they are grouped into two blocks of length 2 each.*);
//@ ensures(*The blocks are joined by dashes.*);
//@ ensures(*The reformatting process should never produce any blocks of length - The reformatting process should produce at most two blocks of length 2.*);
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        String cleaned = sb.toString();
        sb.setLength(0);
        int i = 0;
        while (i < cleaned.length()) {
            if (i + 4 == cleaned.length()) {
                sb.append(cleaned, i, i + 2);
                sb.append("-");
                sb.append(cleaned.substring(i + 2));
                break;
            } else if (i + 3 <= cleaned.length()) {
                sb.append(cleaned, i, i + 3);
                sb.append("-");
                i += 3;
            } else {
                sb.append(cleaned.substring(i));
                break;
            }
        }
        if (sb.charAt(sb.length() - 1) == '-') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}