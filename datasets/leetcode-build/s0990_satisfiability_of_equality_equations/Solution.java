package g0901_1000.s0990_satisfiability_of_equality_equations;

// #Medium #Array #String #Graph #Union_Find #2022_03_31_Time_5_ms_(24.79%)_Space_43.5_MB_(18.67%)

import java.util.HashMap;

public class Solution {
    private int[] par;
	//@ requires(*The input array `equations` is not null.*);
	//@ requires(*The length of `equations` is between 1 and 500.*);
	//@ requires(*Each string in `equations` has a length of 4.*);
	//@ requires(*The first character of each string in `equations` is a lowercase letter.*);
	//@ requires(*The second character of each string in `equations` is either '=' or '!'.*);
	//@ requires(*The third character of each string in `equations` is '='.*);
	//@ requires(*The fourth character of each string in `equations` is a lowercase letter.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to assign integers to variable names to satisfy all the given equations.*);

    public boolean equationsPossible(String[] equations) {
        int counter = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (String str : equations) {
            char ch = str.charAt(0);
            if (!map.containsKey(ch)) {
                map.put(ch, counter);
                counter++;
            }
            ch = str.charAt(3);
            if (!map.containsKey(ch)) {
                map.put(ch, counter);
                counter++;
            }
        }
        par = new int[counter];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        for (String str : equations) {
            String oper = str.substring(1, 3);
            if (oper.equals("==")) {
                int px = find(map.get(str.charAt(0)));
                int py = find(map.get(str.charAt(3)));
                if (px != py) {
                    par[px] = py;
                }
            }
        }
        for (String str : equations) {
            String oper = str.substring(1, 3);
            if (oper.equals("!=")) {
                int px = find(map.get(str.charAt(0)));
                int py = find(map.get(str.charAt(3)));
                if (px == py) {
                    return false;
                }
            }
        }
        return true;
    }

    private int find(int x) {
        if (par[x] == x) {
            return x;
        }
        par[x] = find(par[x]);
        return par[x];
    }
}