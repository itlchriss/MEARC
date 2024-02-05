package g0001_0100.s0018_4sum;

// #Medium #Array #Sorting #Two_Pointers #2023_08_09_Time_3_ms_(99.77%)_Space_43.9_MB_(82.30%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S135")
public class Solution {
    /*@
        requires (list != null);
        requires (\forall int i; 0 <= i < list.size(); list.get(i) != null);
        public model pure boolean _unique(List<List<Integer>> list) {
            java.util.Set<List<Integer>> s = new java.util.HashSet<List<Integer>>(list);
            return s.size() == list.size();
        }
        public model pure boolean _unique(Integer[] array) {
            java.util.Set<Integer> s = new java.util.HashSet<Integer>(Arrays.asList(array));
            return s.size() == array.length;
        }
        public model pure boolean _unique(Object[] array) {
            java.util.Set<Object> s = new java.util.HashSet<Object>(Arrays.asList(array));
            return s.size() == array.length;
        }
        public model pure boolean _check_list_size(List<Object> list, int x) { 
            return list != null && list.size() == x;
        }
        public model pure boolean _check_array_length(Object[] array, int x) { 
            return array.length == x;
        }
        public model pure boolean _at_least(int[] array, int x, String _compareType) {
            if (_compareType == "elements") {
                return array.length >= x;
            }
            return false;
        }
        public model pure boolean _at_most(Object[] array, int x, String _compareType) {
            if (_compareType == "elements") {
                return array.length <= x;
            }
            return false;
        }
 
            public model pure boolean _is_quadriplet(List<Object> list) {
                return _check_list_size(list, 4);
            }

    @*/
    // the second and third precondition needs to be inherited
    // the return type needs to be inherited
    /*@
        requires(3 <= input.size() <= 200);
        requires(\forall int i; 0 <= i < input.size(); -1000000000 <= input.get(i) <= 1000000000);
        public model pure long sum(List<Integer> input) {
            long r = 0;            
            //@ loop_invariant 0 <= i <= input.size();
            //@ decreasing input.size() - i;
            for (int i = 0; i < input.size(); ++i) { 
                r += input.get(i);                
            }
            return r;
        }
    @*/
    // the first precondition is wrong, the original one is (1 <= nums.length <= 200) which is too weak for the first loop
    //@ requires(3 <= nums.length <= 200);
    //@ requires(\forall int i; 0 <= i < nums.length; -1000000000 <= nums[i] <= 1000000000);
    //@ requires(-1000000000 <= target <= 1000000000);
    // these are the correct postconditions
    //@ ensures(\forall int i; 0 <= i < (\result).size(); \result.get(i) != null);
    //@ ensures(\forall int i; 0 <= i < (\result).size(); \result.get(i).size() == 4);
    /*@
        ensures(
            \forall int i; 0 <= i < \result.size(); (
                sum(\result.get(i)) == target
            )
        );
    @*/
    //@ requires(*The input array `nums` is not null.*);
    //@ requires(*The input array `nums` has at least 4 elements.*);
    //@ requires(*The target integer `target` is not null.*);
    //@ ensures(*The method returns a list of lists, where each inner list represents a unique quadruplet.*);
    //@ ensures(*Each quadruplet in the returned list contains four integers from the input array `nums`.*);
    //@ ensures(*The sum of each quadruplet in the returned list is equal to the target integer `target`.*);
    //@ ensures(*The order of the quadruplets in the returned list can be in any order.*);
    //@ ensures(*The returned list does not contain any duplicate quadruplets.*);
    //@ requires(_at_least(nums, 4, "elements"));
    //@ requires(nums != null);
    //@ ensures(_unique(\result));
    public List<List<Integer>> fourSum(/*@ non_null @*/int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        //@ loop_invariant n >= 3;
        //@ loop_invariant 0 <= i <= n - 3;
        //@ decreasing n - i;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }            
            //@ loop_invariant i + 1 <= j <= n - 2;
            //@ decreasing n - j;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[j] + nums[j + 1] + nums[j + 2] > target - nums[i]) {
                    break;
                }
                if ((long) nums[j] + nums[n - 2] + nums[n - 1] < target - nums[i]) {
                    continue;
                }
                int tempTarget = target - (nums[i] + nums[j]);
                int low = j + 1;
                int high = n - 1;
                //@ loop_invariant j + 1 <= low < nums.length;
                //@ decreasing nums.length - low;
                //@ loop_invariant 0 <= high <= n - 1;
                //@ decreasing high;
                while (low < high) {
                    int curSum = nums[low] + nums[high];
                    if (curSum == tempTarget) {
                        List<Integer> tempList = new ArrayList<>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[low]);
                        tempList.add(nums[high]);
                        result.add(tempList);
                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low - 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high + 1]) {
                            high--;
                        }
                    } else if (curSum < tempTarget) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }
}
