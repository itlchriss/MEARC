package g2901_3000.s2960_count_tested_devices_after_test_operations;

// #Easy #Array #Simulation #2024_01_15_Time_0_ms_(100.00%)_Space_43.4_MB_(43.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `batteryPercentages` is not null.*);
//@ ensures(*The length of the input array `batteryPercentages` is greater than or equal to 1.*);
//@ ensures(*The length of the input array `batteryPercentages` is less than or equal to 100.*);
//@ ensures(*Each element in the input array `batteryPercentages` is an integer.*);
//@ ensures(*Each element in the input array `batteryPercentages` is greater than or equal to 0.*);
//@ ensures(*Each element in the input array `batteryPercentages` is less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to the length of the input array `batteryPercentages`.*);
//@ ensures(*The input array `batteryPercentages` is not modified if all devices have a battery percentage of 0.*);
//@ ensures(*The input array `batteryPercentages` is modified if at least one device has a battery percentage greater than 0.*);
    public int countTestedDevices(int[] batteryPercentages) {
        int count = 0;
        int diff = 0;
        for (int n : batteryPercentages) {
            if (n - diff > 0) {
                count++;
                diff++;
            }
        }
        return count;
    }
}