package g0801_0900.s0831_masking_personal_information;

// #Medium #String #2022_03_24_Time_1_ms_(84.30%)_Space_42.1_MB_(63.64%)

public class Solution {
	//@ requires(*The input string `s` is either a valid email address or a phone number.*);
	//@ requires(*If `s` is an email address, it consists of uppercase and lowercase English letters and exactly one '@' symbol and '.' symbol.*);
	//@ requires(*If `s` is a phone number, it consists of digits, spaces, and the symbols '(', ')', '-', and '+'.*);
	//@ requires(*The length of `s` is within the specified constraints (8 <= s.length <= 40 for email addresses, 10 <= s.length <= 20 for phone numbers).*);
	//@ ensures(*The returned string is the masked personal information.*);
	//@ ensures(*If `s` is an email address, the uppercase letters in the name and domain are converted to lowercase letters.*);
	//@ ensures(*If `s` is an email address, the middle letters of the name (excluding the first and last letters) are replaced by 5 asterisks.*);
	//@ ensures(*If `s` is a phone number, all separation characters are removed.*);
	//@ ensures(*If `s` is a phone number, the masked phone number has the specified format based on the number of digits in the country code.*);
	//@ ensures(*The last 4 digits of the local number are represented as 'XXXX' in the masked phone number.*);
    public String maskPII(String s) {
        StringBuilder masked = new StringBuilder();
        if (Character.isAlphabetic(s.charAt(0))) {
            int locationOfAtSymbol = s.indexOf("@") - 1;
            masked.append(s.charAt(0)).append("*****").append(s.substring(locationOfAtSymbol));
            return masked.toString().toLowerCase();
        } else {
            StringBuilder allDigits = new StringBuilder();
            int pointer = -1;
            while (++pointer < s.length()) {
                if (Character.isDigit(s.charAt(pointer))) {
                    allDigits.append(s.charAt(pointer));
                }
            }
            int numDigits = allDigits.length();
            if (numDigits == 11) {
                masked.append("+*-");
            } else if (numDigits == 12) {
                masked.append("+**-");
            } else if (numDigits == 13) {
                masked.append("+***-");
            }
            masked.append("***-***-").append(allDigits.substring(numDigits - 4));
            return masked.toString();
        }
    }
}