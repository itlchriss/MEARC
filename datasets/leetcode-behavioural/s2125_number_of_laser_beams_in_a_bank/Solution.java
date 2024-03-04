package g2101_2200.s2125_number_of_laser_beams_in_a_bank;

// #Medium #Array #String #Math #Matrix #2022_06_02_Time_19_ms_(76.00%)_Space_57.3_MB_(20.78%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `bank` is not null.*);
//@ ensures(*The length of `bank` is greater than 0.*);
//@ ensures(*The length of each string in `bank` is greater than 0.*);
//@ ensures(*The characters in each string of `bank` are either '0' or '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the total number of laser beams in the bank.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to the maximum possible number of laser beams in the bank.*);
//@ ensures(*The return value is equal to the number of pairs of security devices that meet the conditions for having a laser beam between them.*);
//@ ensures(*For each pair of security devices that have a laser beam between them, the two devices are located on two different rows.*);
//@ ensures(*For each pair of security devices that have a laser beam between them, there are no security devices in any row between the two rows of the devices.*);
//@ ensures(*For each pair of security devices that do not have a laser beam between them, either the two devices are located on the same row or there are security devices in at least one row between the two rows of the devices.*);
    public int numberOfBeams(String[] bank) {
        int beam = 0;
        int prev = 0;
        for (String s : bank) {
            int nos = 0;
            for (char j : s.toCharArray()) {
                if (j == '1') {
                    nos++;
                }
            }
            if (nos > 0) {
                if (prev == 0) {
                    prev = nos;
                } else {
                    beam += prev * nos;
                    prev = nos;
                }
            }
        }
        return beam;
    }
}