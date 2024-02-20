package g0401_0500.s0468_validate_ip_address;

// #Medium #String #2022_07_19_Time_2_ms_(73.87%)_Space_42.4_MB_(42.34%)

public class Solution {
    private static final String NEITHER = "Neither";
	//@ requires(*The input `ip` is a string representing an IP address.*);
	//@ ensures(*If `ip` is a valid IPv4 address, the method returns the string "IPv4".*);
	//@ ensures(*If `ip` is a valid IPv6 address, the method returns the string "IPv6".*);
	//@ ensures(*If `ip` is not a valid IPv4 or IPv6 address, the method returns the string "Neither".*);

    public String validIPAddress(String ip) {
        if (ip.length() == 0) {
            return NEITHER;
        }
        String[] arr = ip.split("\\.", -1);
        String[] arr1 = ip.split(":", -1);
        if (arr.length == 4) {
            for (String num : arr) {
                try {
                    if ((num.length() > 1 && num.startsWith("0")) || Integer.parseInt(num) > 255) {
                        return NEITHER;
                    }
                } catch (Exception e) {
                    return NEITHER;
                }
            }
            return "IPv4";
        } else if (arr1.length == 8) {
            for (String num : arr1) {
                if (num.length() < 1 || num.length() > 4) {
                    return NEITHER;
                }
                for (int j = 0; j < num.length(); j++) {
                    char ch = num.charAt(j);
                    if (ch > 9
                            && (Character.isLowerCase(ch) && ch > 'f'
                                    || Character.isUpperCase(ch) && ch > 'F')) {
                        return NEITHER;
                    }
                }
            }
            return "IPv6";
        }
        return NEITHER;
    }
}