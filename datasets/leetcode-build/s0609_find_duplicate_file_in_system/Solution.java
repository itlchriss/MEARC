package g0601_0700.s0609_find_duplicate_file_in_system;

// #Medium #Array #String #Hash_Table #2022_03_21_Time_20_ms_(97.68%)_Space_51.3_MB_(87.10%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input `paths` is not null.*);
	//@ requires(*The length of `paths` is greater than or equal to 1.*);
	//@ requires(*Each element in `paths` is not null.*);
	//@ requires(*Each element in `paths` is a valid directory info string.*);
	//@ requires(*Each file path in the directory info string is a valid file path.*);
	//@ requires(*Each file content in the directory info string is a valid file content.*);
	//@ ensures(*The output is not null.*);
	//@ ensures(*The output is a list of groups of duplicate file paths.*);
	//@ ensures(*Each group in the output contains all the file paths of the files that have the same content.*);
	//@ ensures(*Each file path in the output is a string in the format "directory_path/file_name.txt".*);
	//@ ensures(*The order of the groups in the output can be any order.*);
	//@ ensures(*The order of the file paths within each group in the output can be any order.*);
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] pathComponents = path.split(" ");
            String root = pathComponents[0];
            for (int i = 1; i < pathComponents.length; i++) {
                int startIndex = pathComponents[i].indexOf("(");
                int endIndex = pathComponents[i].lastIndexOf(")");
                String content = pathComponents[i].substring(startIndex, endIndex);

                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(root + "/" + pathComponents[i].substring(0, startIndex));
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                result.add(list);
            }
        }
        return result;
    }
}