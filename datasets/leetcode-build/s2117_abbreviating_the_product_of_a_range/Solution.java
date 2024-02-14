package g2101_2200.s2117_abbreviating_the_product_of_a_range;

// #Hard #Math #2022_06_01_Time_70_ms_(89.47%)_Space_42.2_MB_(78.95%)

public class Solution {
	//@ requires(*The input integers `left` and `right` must be positive.*);
	//@ requires(*`left` must be less than or equal to `right`.*);
	//@ ensures(*The output string must represent the abbreviated product of all integers in the inclusive range `[left, right]`.*);
	//@ ensures(*The output string must be in the format `"<pre>...<suf>eC"`, where `<pre>` denotes the first 5 digits of the product, `<suf>` denotes the last 5 digits of the product after removing trailing zeros, and `C` denotes the count of trailing zeros.*);
	//@ ensures(*If there are no trailing zeros in the product, the output string must not contain the `eC` part.*);
	//@ ensures(*If the number of digits in the product is less than or equal to 10, the output string must not be further abbreviated.*);
	//@ ensures(*If the number of digits in the product is greater than 10, the output string must be abbreviated by only including the first 5 digits and the last 5 digits after removing trailing zeros.*);
    public String abbreviateProduct(int left, int right) {
        long threshold0 = 100_000_000_000_000L;
        long threshold1 = 10_000_000_000L;
        long threshold2 = 100_000;
        long curr = 1;
        int i;
        int zerosCount = 0;
        for (i = left; i <= right && curr < threshold0; i++) {
            curr *= i;
            while (curr % 10 == 0) {
                curr /= 10;
                zerosCount++;
            }
        }
        if (curr < threshold1) {
            return String.format("%de%d", curr, zerosCount);
        }

        long low = curr % threshold1;
        double high = curr;
        while (high > threshold1) {
            high /= 10;
        }

        for (; i <= right; i++) {
            low *= i;
            high *= i;
            while (low % 10 == 0) {
                low /= 10;
                zerosCount++;
            }
            if (low >= threshold1) {
                low %= threshold1;
            }
            while (high > threshold1) {
                high /= 10;
            }
        }

        while (high >= threshold2) {
            high /= 10;
        }
        low %= threshold2;
        return String.format("%d...%05de%d", (int) high, low, zerosCount);
    }
}