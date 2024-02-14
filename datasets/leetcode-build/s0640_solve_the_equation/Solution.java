package g0601_0700.s0640_solve_the_equation;

// #Medium #String #Math #Simulation #2022_03_21_Time_5_ms_(69.29%)_Space_42.2_MB_(48.57%)

public class Solution {
	//@ requires(*The input equation must be a string.*);
	//@ requires(*The equation must contain only the characters '+', '-', 'x', and '='.*);
	//@ requires(*The equation must have exactly one '=' character.*);
	//@ requires(*The equation must consist of integers with an absolute value in the range [0, 100] without any leading zeros.*);
	//@ requires(*The variable 'x' must be present in the equation.*);
	//@ ensures(*If there is no solution for the equation, the method should return the string "No solution".*);
	//@ ensures(*If there are infinite solutions for the equation, the method should return the string "Infinite solutions".*);
	//@ ensures(*If there is exactly one solution for the equation, the method should return a string in the form "x=#value", where #value is the value of 'x' as an integer.*);
    public String solveEquation(String equation) {
        String[] eqs = equation.split("=");

        int[] arr1 = evaluate(eqs[0]);
        int[] arr2 = evaluate(eqs[1]);

        if (arr1[0] == arr2[0] && arr1[1] == arr2[1]) {
            return "Infinite solutions";
        } else if (arr1[0] == arr2[0]) {
            return "No solution";
        } else {
            return "x=" + (arr2[1] - arr1[1]) / (arr1[0] - arr2[0]);
        }
    }

    private int[] evaluate(String eq) {
        char[] arr = eq.toCharArray();
        boolean f = false;
        int a = 0;
        int b = 0;

        int i = 0;
        if (arr[0] == '-') {
            f = true;
            i++;
        }
        while (i < arr.length) {
            if (arr[i] == '-') {
                f = true;
                i++;
            } else if (arr[i] == '+') {
                i++;
            }
            StringBuilder sb = new StringBuilder();
            while (i < arr.length && Character.isDigit(arr[i])) {
                sb.append(arr[i]);
                i++;
            }
            String n = sb.toString();
            if (i < arr.length && arr[i] == 'x') {
                int number;
                if (n.equals("")) {
                    number = 1;
                } else {
                    number = Integer.parseInt(n);
                }
                if (f) {
                    number = -number;
                }
                a += number;
                i++;
            } else {
                int number = Integer.parseInt(n);
                if (f) {
                    number = -number;
                }
                b += number;
            }
            f = false;
        }
        int[] op = new int[2];
        op[0] = a;
        op[1] = b;
        return op;
    }
}