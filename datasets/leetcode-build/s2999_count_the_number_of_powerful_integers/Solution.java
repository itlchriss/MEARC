package g2901_3000.s2999_count_the_number_of_powerful_integers;

// #Hard #String #Dynamic_Programming #Math #2024_01_17_Time_1_ms_(100.00%)_Space_42.6_MB_(83.99%)

public class Solution {
	//@ requires(*The input integers `start`, `finish`, and `limit` must be valid and within the specified constraints.*);
	//@ requires(*The input string `s` must be a valid positive integer and must not have leading zeros.*);
	//@ ensures(*The method should return the total number of powerful integers in the range `[start..finish]`.*);
	//@ ensures(*Other behavioral requirements:*);
	//@ ensures(*The method should consider an integer `x` as powerful if it ends with the string `s` and each digit in `x` is at most `limit`.*);
	//@ ensures(*The method should count the number of powerful integers in the range `[start..finish]`.*);
	//@ ensures(*The method should not count any integer that is smaller than `start` or larger than `finish`.*);
	//@ ensures(*The method should not count any integer that does not end with the string `s`.*);
	//@ ensures(*The method should not count any integer that has a digit greater than `limit`.*);
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long sn = Long.parseLong(s);
        if (finish < sn) {
            return 0;
        }
        start = Math.max(start, sn);
        long originalL = s.length();
        long factor = 1;
        for (long i = 1; i <= originalL; i++) {
            factor *= 10;
        }
        long sx = (start - sn) % factor == 0 ? (start - sn) / factor : (start - sn) / factor + 1;
        long lx = (finish - sn) / factor;

        return sx == 0
                ? indexOfLimitIntSmallerThanOrEqual(lx, limit) + 1
                : indexOfLimitIntSmallerThanOrEqual(lx, limit)
                        - indexOfLimitIntSmallerThanOrEqual(sx - 1, limit);
    }

    private long indexOfLimitIntSmallerThanOrEqual(long target, int limit) {
        String s = Long.toString(target);
        long index = 0;
        boolean limitViolated = false;
        for (int i = 0; i < s.length(); i++) {
            index *= limit + 1;
            if (!limitViolated) {
                if (s.charAt(i) - '0' > limit) {
                    limitViolated = true;
                    index += limit;
                } else {
                    index += (s.charAt(i) - '0');
                }
            } else {
                index += limit;
            }
        }
        return index;
    }
}