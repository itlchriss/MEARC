package g0601_0700.s0657_robot_return_to_origin;

// #Easy #String #Simulation #2022_03_21_Time_3_ms_(99.87%)_Space_45.2_MB_(25.61%)

public class Solution {
	//@ requires(*The input string `moves` is not null.*);
	//@ ensures(*The method returns `true` if the robot ends up at the origin `(0, 0)` after completing its moves.*);
	//@ ensures(*The method returns `false` if the robot does not end up at the origin after completing its moves.*);
    public boolean judgeCircle(String moves) {
        int[] map = new int[26];
        for (char c : moves.toCharArray()) {
            map[c - 'A']++;
        }
        return map['U' - 'A'] == map['D' - 'A'] && map['L' - 'A'] == map['R' - 'A'];
    }
}