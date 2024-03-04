package g0601_0700.s0657_robot_return_to_origin;

// #Easy #String #Simulation #2022_03_21_Time_3_ms_(99.87%)_Space_45.2_MB_(25.61%)

public class Solution {
//@ ensures(*If the string parameter `moves` contains an equal number of 'U' and 'D' moves, and an equal number of 'L' and 'R' moves, the boolean result is true.*);
//@ ensures(*If the string parameter `moves` does not result in the robot returning to the origin after completing all moves, the boolean result is false.*);
    public boolean judgeCircle(String moves) {
        int[] map = new int[26];
        for (char c : moves.toCharArray()) {
            map[c - 'A']++;
        }
        return map['U' - 'A'] == map['D' - 'A'] && map['L' - 'A'] == map['R' - 'A'];
    }
}