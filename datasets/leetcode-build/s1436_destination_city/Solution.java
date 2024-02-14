package g1401_1500.s1436_destination_city;

// #Easy #String #Hash_Table #2022_06_21_Time_3_ms_(81.47%)_Space_43.7_MB_(53.89%)

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The input `paths` is not null.*);
	//@ requires(*The length of `paths` is greater than or equal to 1.*);
	//@ requires(*Each element in `paths` is a list of size 2.*);
	//@ requires(*The length of each city name in `paths` is between 1 and 10.*);
	//@ requires(*The city names in `paths` are not equal to each other.*);
	//@ ensures(*The output is a non-null string.*);
	//@ ensures(*The output is the destination city, which is the city without any path outgoing to another city.*);
	//@ ensures(*The output city is one of the cities in `paths`.*);
    public String destCity(List<List<String>> paths) {
        Set<String> set = new HashSet<>();
        for (List<String> strings : paths) {
            set.add(strings.get(0));
        }
        for (List<String> path : paths) {
            if (!set.contains(path.get(1))) {
                return path.get(1);
            }
        }
        return "";
    }
}