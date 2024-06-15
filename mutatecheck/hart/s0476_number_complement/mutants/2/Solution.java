package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
// requires((num >= 1) && (num < 2147483648));
//@ ensures((num == 5) ==> (\result == 2));
//@ ensures((num == 1) ==> (\result == 0));
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) % 1);
    }
}
