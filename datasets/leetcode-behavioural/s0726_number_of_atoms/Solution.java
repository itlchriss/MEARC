package g0701_0800.s0726_number_of_atoms;

// #Hard #String #Hash_Table #Sorting #Stack #2022_03_24_Time_4_ms_(87.22%)_Space_42.7_MB_(28.23%)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@SuppressWarnings({"java:S135", "java:S1149"})
public class Solution {
    private boolean isLower(char c) {
        return c >= 97 && c <= 122;
    }

    private boolean isDigit(char c) {
        return c >= 48 && c <= 57;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `formula` is not null.*);
//@ ensures(*The input string `formula` is a valid chemical formula.*);
//@ ensures(*The input string `formula` consists of English letters, digits, `'('`, and `')'`.*);
//@ ensures(*The length of the input string `formula` is between 1 and 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The output string represents the count of each atom in the chemical formula.*);
//@ ensures(*The output string is in the format of the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.*);
//@ ensures(*The output string contains only valid atomic elements.*);
//@ ensures(*The output string contains the count of each atomic element in the chemical formula.*);
//@ ensures(*The output string is generated in such a way that all the values in the output fit in a 32-bit integer.*);

    public String countOfAtoms(String formula) {
        int product = 1;
        Stack<Integer> mlrStack = new Stack<>();
        Map<String, Integer> count = new HashMap<>();
        int i = formula.length() - 1;
        while (i >= 0) {
            char c = formula.charAt(i);
            if (c == '(') {
                product /= mlrStack.pop();
                i--;
                continue;
            }
            int rank = 1;
            int mlr = 0;
            while (isDigit(c)) {
                mlr += rank * (c - 48);
                rank *= 10;
                c = formula.charAt(--i);
            }
            if (mlr == 0) {
                ++mlr;
            }
            mlrStack.push(mlr);
            product *= mlr;
            if (c == ')') {
                i--;
                continue;
            }
            StringBuilder atom = new StringBuilder();
            while (isLower(c)) {
                atom.insert(0, c);
                c = formula.charAt(--i);
            }
            atom.insert(0, c);
            String name = atom.toString();
            count.put(name, count.getOrDefault(name, 0) + product);
            product /= mlrStack.pop();
            i--;
        }
        List<String> atomList = new ArrayList<>(count.keySet());
        atomList.sort(Comparator.naturalOrder());
        StringBuilder res = new StringBuilder();
        for (String atom : atomList) {
            res.append(atom);
            if (count.get(atom) > 1) {
                res.append(count.get(atom));
            }
        }
        return res.toString();
    }
}