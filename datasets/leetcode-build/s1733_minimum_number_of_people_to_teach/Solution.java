package g1701_1800.s1733_minimum_number_of_people_to_teach;

// #Medium #Array #Greedy #2022_04_28_Time_11_ms_(98.57%)_Space_56.3_MB_(95.71%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The input integer `n` represents the number of languages, and it should be between 2 and 500 (inclusive).*);
	//@ requires(*The array `languages` represents the languages known by each user, and its length `m` should be between 1 and 500 (inclusive).*);
	//@ requires(*Each element `languages[i]` in the `languages` array represents the set of languages known by the `i-th` user, and its length should be between 1 and `n` (inclusive).*);
	//@ requires(*Each language `languages[i][j]` in the `languages` array should be a number between 1 and `n` (inclusive).*);
	//@ requires(*The array `friendships` represents the friendships between users, and its length should be between 1 and 500 (inclusive).*);
	//@ requires(*Each element `friendships[i]` in the `friendships` array represents a friendship between the users `u_i` and `v_i`, where `1 <= u_i < v_i <= languages.length`.*);
	//@ requires(*All tuples `(u_i, v_i)` in the `friendships` array are unique.*);
	//@ requires(*The `languages` array contains only unique values.*);
	//@ ensures(*The method should return an integer representing the minimum number of users that need to be taught a language in order for all friends to be able to communicate with each other.*);
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] speak = new boolean[m + 1][n + 1];
        boolean[][] teach = new boolean[m + 1][n + 1];
        for (int user = 0; user < m; user++) {
            int[] userLanguages = languages[user];
            for (int userLanguage : userLanguages) {
                speak[user + 1][userLanguage] = true;
            }
        }
        List<int[]> listToTeach = new ArrayList<>();
        for (int[] friend : friendships) {
            int userA = friend[0];
            int userB = friend[1];
            boolean hasCommonLanguage = false;
            for (int language = 1; language <= n; language++) {
                if (speak[userA][language] && speak[userB][language]) {
                    hasCommonLanguage = true;
                    break;
                }
            }
            if (!hasCommonLanguage) {
                for (int language = 1; language <= n; language++) {
                    if (!speak[userA][language]) {
                        teach[userA][language] = true;
                    }
                    if (!speak[userB][language]) {
                        teach[userB][language] = true;
                    }
                }
                listToTeach.add(friend);
            }
        }
        int minLanguage = Integer.MAX_VALUE;
        int languageToTeach = 0;
        for (int language = 1; language <= n; language++) {
            int count = 0;
            for (int user = 1; user <= m; user++) {
                if (teach[user][language]) {
                    count++;
                }
            }
            if (count < minLanguage) {
                minLanguage = count;
                languageToTeach = language;
            }
        }
        Set<Integer> setToTeach = new HashSet<>();
        for (int[] friend : listToTeach) {
            int userA = friend[0];
            int userB = friend[1];
            if (!speak[userA][languageToTeach]) {
                setToTeach.add(userA);
            }
            if (!speak[userB][languageToTeach]) {
                setToTeach.add(userB);
            }
        }
        return setToTeach.size();
    }
}