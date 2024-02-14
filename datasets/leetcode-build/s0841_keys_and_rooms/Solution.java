package g0801_0900.s0841_keys_and_rooms;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Data_Structure_II_Day_19_Graph
// #Graph_Theory_I_Day_7_Standard_Traversal #2022_03_24_Time_3_ms_(51.54%)_Space_42.3_MB_(75.53%)

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	//@ requires(*1. The input `rooms` is not null.*);
	//@ requires(*2. The length of `rooms` is greater than or equal to 2.*);
	//@ requires(*3. The length of `rooms` is less than or equal to 1000.*);
	//@ requires(*4. The values of `rooms[i]` are unique.*);
	//@ requires(*5. The sum of the lengths of all `rooms[i]` is greater than or equal to 1.*);
	//@ requires(*6. The sum of the lengths of all `rooms[i]` is less than or equal to 3000.*);
	//@ requires(*7. The values of `rooms[i][j]` are integers.*);
	//@ requires(*8. The values of `rooms[i][j]` are greater than or equal to 0.*);
	//@ requires(*9. The values of `rooms[i][j]` are less than `n`, where `n` is the length of `rooms`.*);
	//@ ensures(*1. The method returns a boolean value indicating whether it is possible to visit all the rooms.*);
	//@ ensures(*2. If the method returns true, it means that it is possible to visit all the rooms.*);
	//@ ensures(*3. If the method returns false, it means that it is not possible to visit all the rooms.*);
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        TreeSet<Integer> treeSet = new TreeSet<>(rooms.get(0));
        while (!treeSet.isEmpty()) {
            Integer key = treeSet.pollFirst();
            if (!visited.add(key)) {
                continue;
            }
            if (visited.size() == rooms.size()) {
                return true;
            }
            treeSet.addAll(rooms.get(key));
        }
        return visited.size() == rooms.size();
    }
}