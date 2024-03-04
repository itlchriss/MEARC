package g1401_1500.s1418_display_table_of_food_orders_in_a_restaurant;

// #Medium #Array #String #Hash_Table #Sorting #Ordered_Set
// #2022_03_26_Time_42_ms_(77.01%)_Space_126.7_MB_(59.77%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input list "orders" is not null.*);
//@ ensures(*2. The length of "orders" is greater than or equal to 1.*);
//@ ensures(*3. Each element in "orders" is a list of length 3.*);
//@ ensures(*4. The length of the customer name and food item in each order is between 1 and 20.*);
//@ ensures(*5. The table number in each order is a valid integer between 1 and 500.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The output list "displayTable" is not null.*);
//@ ensures(*2. The length of "displayTable" is equal to the number of unique table numbers in "orders" plus 1 (for the header row).*);
//@ ensures(*3. The length of each sublist in "displayTable" is equal to the number of unique food items in "orders" plus 1 (for the table number column).*);
//@ ensures(*4. The first element of the first sublist in "displayTable" is the string "Table".*);
//@ ensures(*5. The remaining elements of the first sublist in "displayTable" are the unique food items in alphabetical order.*);
//@ ensures(*6. The first element of each sublist in "displayTable" (excluding the first sublist) is a unique table number in numerical order.*);
//@ ensures(*7. The remaining elements of each sublist in "displayTable" (excluding the first sublist) represent the count of each food item ordered by the corresponding table.*);
    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeMap<Integer, Map<String, Integer>> map = new TreeMap<>();
        Set<String> dishSet = new HashSet<>();
        for (List<String> order : orders) {
            int tableNumber = Integer.parseInt(order.get(1));
            String dishName = order.get(2);
            dishSet.add(dishName);
            map.putIfAbsent(tableNumber, new HashMap<>());
            Map<String, Integer> dishCountMap = map.get(tableNumber);
            if (!dishCountMap.containsKey(dishName)) {
                dishCountMap.put(dishName, 1);
            } else {
                dishCountMap.put(dishName, dishCountMap.get(dishName) + 1);
            }
        }
        List<String> dishes = new ArrayList<>(dishSet);
        Collections.sort(dishes);
        dishes.add(0, "Table");
        List<List<String>> result = new ArrayList<>();
        result.add(dishes);
        for (Map.Entry<Integer, Map<String, Integer>> entry : map.entrySet()) {
            List<String> row = new ArrayList<>();
            row.add("" + entry.getKey());
            for (int i = 1; i < dishes.size(); i++) {
                if (map.get(entry.getKey()).containsKey(dishes.get(i))) {
                    row.add(Integer.toString(map.get(entry.getKey()).get(dishes.get(i))));
                } else {
                    row.add("0");
                }
            }
            result.add(row);
        }
        return result;
    }
}