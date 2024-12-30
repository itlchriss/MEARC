package g0401_0500.s0476_number_complement;

// #Easy #Bit_Manipulation #2022_07_20_Time_0_ms_(100.00%)_Space_40.7_MB_(65.79%)

public class Solution {
//@ requires(*The integer parameter `num` is less than 2^31 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1047. Remove All Adjacent Duplicates In String II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `S` of lowercase letters and an integer `K`.*);
//@ requires(**);
//@ requires(*We want to remove some characters from `S` such that the number of different characters is exactly `K`.*);
//@ requires(**);
//@ requires(*If it is not possible to remove `K` characters, then return an empty string `" "`.*);
//@ requires(**);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** S =  "deeedbbcccbdaa ", K = 3*);
//@ requires(***Output:**  "aa"*);
//@ requires(***Explanation:** *);
//@ requires(*First we remove "eee" and "bbb", so the string becomes "dcccbdaa" with 3 different characters.*);
//@ requires(*Then we remove "ccc", so the string becomes "dbdaa" with 3 different characters.*);
//@ requires(*Finally, we remove "ddd", so the final string is "aa" with 2 different characters.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** S =  "abcd ", K = 2*);
//@ requires(***Output:**  "abcd "*);
//@ requires(***Explanation:** *);
//@ requires(*We cannot remove any characters since the string does not have any duplicated character, so the answer is "abcd ".*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** S =  "aaaaaabbbccc ", K = 1*);
//@ requires(***Output:**  "cc "*);
//@ requires(***Explanation:** *);
//@ requires(*First we remove 5 'a', so the string becomes "aaabbbccc" with 3 different characters.*);
//@ requires(*Then we remove 2 'b', so the string becomes "aaacccc" with 3 different characters.*);
//@ requires(*Finally, we remove 3 'c', so the final string is "ccc" with 1 different character.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= S.length <= 10^5`*);
//@ requires(**   `S` consists of lowercase English letters.*);
//@ requires(**   `0 <= K <= S.length`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve this problem in `O(N)` and `O(1)` space complexity?*);
//@ ensures(*The integer result is less than 2^31 and is greater than or equal to 0.*);
//@ ensures(*If the integer parameter `num` is equal to 5, the integer result is equal to 2.*);
//@ ensures(*If the integer parameter `num` is equal to 1, the integer result is equal to 0.*);
//@ ensures(*Return the resulting string after removing `K` characters. You may return any valid answer, and the answer is guaranteed to be unique under the given constraint.*);
    public int findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}