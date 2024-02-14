package g1601_1700.s1629_slowest_key;

// #Easy #Array #String #2023_09_03_Time_1_ms_(94.07%)_Space_43.9_MB_(9.16%)

@SuppressWarnings("java:S3824")
public class Solution {
	//@ requires(*The length of `releaseTimes` and `keysPressed` arrays should be the same.*);
	//@ requires(*The length of `releaseTimes` and `keysPressed` arrays should be at least - The elements in `releaseTimes` array should be in ascending order.*);
	//@ requires(*The elements in `keysPressed` array should be lowercase English letters.*);
	//@ ensures(*The method should return a character representing the keypress that had the longest duration.*);
	//@ ensures(*If there are multiple keypresses with the longest duration, the method should return the lexicographically largest key.*);
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int maxIndex = 0;
        int maxValue = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            final int newVal = releaseTimes[i] - releaseTimes[i - 1];
            if (newVal > maxValue) {
                maxValue = newVal;
                maxIndex = i;
            } else if (newVal == maxValue && keysPressed.charAt(i) > keysPressed.charAt(maxIndex)) {
                maxIndex = i;
            }
        }
        return keysPressed.charAt(maxIndex);
    }
}