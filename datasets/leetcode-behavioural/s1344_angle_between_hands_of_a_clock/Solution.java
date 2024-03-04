package g1301_1400.s1344_angle_between_hands_of_a_clock;

// #Medium #Math #2022_03_19_Time_0_ms_(100.00%)_Space_39_MB_(81.98%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input values `hour` and `minutes` must be integers.*);
//@ ensures(*2. The value of `hour` must be between 1 and 12 (inclusive).*);
//@ ensures(*3. The value of `minutes` must be between 0 and 59 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return a double value representing the smaller angle (in degrees) formed between the hour and minute hand.*);
//@ ensures(*2. The returned angle should be within 10^-5 of the actual value.*);
//@ ensures(*3. The returned angle should be non-negative and less than or equal to 180 degrees.*);
    public double angleClock(int hour, int minutes) {
        double minAngle = minutes * 360.0 / 60;
        double hourAnglePart1 = hour != 12 ? (hour * 360.0) / 12 : 0;
        double hourAnglePart2 = (double) (30 * minutes) / (double) 60;
        double hourAngle = hourAnglePart1 + hourAnglePart2;
        double preResult = Math.abs(minAngle - (hourAngle));
        return preResult > 180 ? 360 - preResult : preResult;
    }
}