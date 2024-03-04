package g0601_0700.s0609_find_duplicate_file_in_system;

// #Medium #Array #String #Hash_Table #2022_03_21_Time_20_ms_(97.68%)_Space_51.3_MB_(87.10%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*For each group of duplicate files, the list of file paths must be in the format "directory_path/file_name.txt".*);
//@ ensures(*The list of groups of duplicate file paths must be returned in any order.*);
//@ ensures(*Each directory info string in the input list must follow the format "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)".*);
//@ ensures(*The output list must contain all the duplicate files in the file system in terms of their paths.*);
//@ ensures(*A group of duplicate files consists of at least two files that have the same content.*);
//@ ensures(*The file content must be considered for identifying duplicate files.*);
//@ ensures(*The constraints on the input paths must be adhered to.*);
//@ ensures(*The method must handle the given follow-up questions regarding searching files in a real file system and optimizing for large file content and limited file reading capabilities.*);
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