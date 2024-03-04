package g2201_2300.s2288_apply_discount_to_prices;

// #Medium #String #2022_06_17_Time_82_ms_(97.98%)_Space_68.3_MB_(85.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input sentence is a non-empty string.*);
//@ ensures(*The discount is a non-negative integer between 0 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output sentence is a string representing the modified sentence.*);
//@ ensures(*All words representing prices in the output sentence have been updated with the discount applied.*);
//@ ensures(*All updated prices in the output sentence have exactly two decimal places.*);
//@ ensures(*The output sentence has the same words and order as the input sentence, except for the updated prices.*);
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(applyDiscount(word, discount));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String applyDiscount(String s, int discount) {
        if (s.charAt(0) == '$' && s.length() > 1) {
            long price = 0;
            for (int i = 1; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    // Error case. We could also use Long.parseLong() here.
                    return s;
                }
                price *= 10;
                price += (s.charAt(i) - '0') * (100 - discount);
            }
            String stringPrice = String.valueOf(price);
            if (price < 10) {
                return "$0.0" + stringPrice;
            }
            if (price < 100) {
                return "$0." + stringPrice;
            }
            return "$"
                    + stringPrice.substring(0, stringPrice.length() - 2)
                    + "."
                    + stringPrice.substring(stringPrice.length() - 2);
        }
        return s;
    }
}