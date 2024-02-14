package g2401_2500.s2491_divide_players_into_teams_of_equal_skill;

// #Medium #Array #Hash_Table #Sorting #Two_Pointers
// #2023_01_27_Time_21_ms_(70.31%)_Space_73.6_MB_(27.92%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `skill` is not null.*);
	//@ requires(*The length of the input array `skill` is even.*);
	//@ requires(*The length of the input array `skill` is greater than or equal to 2.*);
	//@ requires(*Each element in the input array `skill` is a positive integer.*);
	//@ requires(*Each element in the input array `skill` is less than or equal to 1000.*);
	//@ ensures(*The output is a long integer.*);
	//@ ensures(*The output is the sum of the chemistry of all the teams.*);
	//@ ensures(*If there is no way to divide the players into teams such that the total skill of each team is equal, the output is -1.*);
    public long dividePlayers(int[] skill) {
        int i = 0;
        int j = skill.length - 1;
        Arrays.sort(skill);
        int sum = skill[i] + skill[j];
        long p = 0;
        while (i < j) {
            if (skill[i] + skill[j] != sum) {
                return -1;
            }
            p += ((long) skill[i] * skill[j]);
            i++;
            j--;
        }
        return p;
    }
}