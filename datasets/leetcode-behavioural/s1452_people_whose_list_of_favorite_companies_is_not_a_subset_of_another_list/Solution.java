package g1401_1500.s1452_people_whose_list_of_favorite_companies_is_not_a_subset_of_another_list;

// #Medium #Array #String #Hash_Table #2022_03_28_Time_117_ms_(78.71%)_Space_83.9_MB_(33.17%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("java:S1119")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `favoriteCompanies` is a non-empty array.*);
//@ ensures(*Each element `favoriteCompanies[i]` in the array is a non-empty list of favorite companies for the `ith` person.*);
//@ ensures(*All strings in `favoriteCompanies[i]` are distinct.*);
//@ ensures(*All lists of favorite companies are distinct, meaning that if we sort alphabetically each list, `favoriteCompanies[i]` is not equal to `favoriteCompanies[j]`.*);
//@ ensures(*All strings consist of lowercase English letters only.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a list of indices of people whose list of favorite companies is not a subset of any other list of favorite companies.*);
//@ ensures(*The indices are returned in increasing order.*);
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        List<Integer> res = new ArrayList<>();
        List<Set<String>> in = new ArrayList<>();
        for (List<String> list : favoriteCompanies) {
            in.add(new HashSet<>(list));
        }
        outer:
        for (int i = 0; i < n; i++) {
            for (int j : res) {
                if (isSubset(in.get(i), in.get(j))) {
                    continue outer;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (isSubset(in.get(i), in.get(j))) {
                    continue outer;
                }
            }
            res.add(i);
        }
        return res;
    }

    private boolean isSubset(Set<String> subset, Set<String> set) {
        if (subset.size() >= set.size()) {
            return false;
        }
        return set.containsAll(subset);
    }
}