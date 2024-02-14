package g0201_0300.s0241_different_ways_to_add_parentheses;

// #Medium #String #Dynamic_Programming #Math #Recursion #Memoization
// #2022_07_05_Time_3_ms_(70.81%)_Space_42.5_MB_(73.65%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input expression is a non-empty string.*);
	//@ requires(*The input expression consists of digits and the operators '+', '-', and '*'.*);
	//@ requires(*The length of the input expression is between 1 and 20.*);
	//@ requires(*All the integer values in the input expression are in the range [0, 99].*);
	//@ ensures(*The method returns a list of integers.*);
	//@ ensures(*The list contains all possible results from computing all the different possible ways to group numbers and operators in the input expression.*);
	//@ ensures(*The order of the results in the list can be any order.*);
    public List<Integer> diffWaysToCompute(String expression) {
        return diffWaysToCompute(expression, new HashMap<>());
    }

    private List<Integer> diffWaysToCompute(String expression, Map<String, List<Integer>> map) {
        if (map.containsKey(expression)) {
            return map.get(expression);
        }
        List<Integer> values = new ArrayList<>();
        if (!hasOperator(expression)) {
            // base case
            values.add(Integer.parseInt(expression));
        } else {
            // Recursive case. DFS
            for (int i = 0; i < expression.length(); i++) {
                char symbol = expression.charAt(i);

                if (!Character.isDigit(symbol)) {
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i), map);
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1), map);
                    for (Integer l : left) {
                        for (Integer r : right) {
                            switch (symbol) {
                                case '+':
                                    values.add(l + r);
                                    break;
                                case '-':
                                    values.add(l - r);
                                    break;
                                case '*':
                                    values.add(l * r);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        map.put(expression, values);
        return values;
    }

    private boolean hasOperator(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                case '-':
                case '*':
                    return true;
                default:
                    break;
            }
        }
        return false;
    }
}