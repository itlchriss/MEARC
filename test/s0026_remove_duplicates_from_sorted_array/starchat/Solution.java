package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 30000 and is greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100 and is greater than or equal to -100.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 275\. Find the Celebrity*);
//@ requires(*Medium*);
//@ requires(*Suppose you are at a party with `n` people labeled from `0` to `n - 1` and among them, there may exist one celebrity. The definition of a celebrity is that all the other `n - 1` people know the celebrity, but the celebrity does not know any of them.*);
//@ requires(**);
//@ requires(*Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).*);
//@ requires(**);
//@ requires(*You are given a helper function `bool knows(a, b)` which tells you whether A knows B. Implement a function `int findCelebrity(n)`. There will be exactly one celebrity if they exist, and you must return that person's label if they exist, or `-1` if there is no celebrity. Additionally, you need to implement a function `void verifyCelebrity(int celebrity)` that verifies whether the given person is a celebrity or not.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** graph = \[\[1,1,0\],\[1,1,0\],\[0,0,1\]\]*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** There are three persons labeled with 0, 1 and 2. graph\[i\]\[j\] = 1 means person i knows person j, otherwise graph\[i\]\[j\] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** graph = \[\[1,0,1\],\[1,1,0\],\[0,1,1\]\]*);
//@ requires(***Output:** -1*);
//@ requires(***Explanation:** There is no celebrity.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `n == graph.length`*);
//@ requires(**   `n == graph[i].length`*);
//@ requires(**   `2 <= n <= 100`*);
//@ requires(**   `graph[i][j]` is `0` or `1`.*);
//@ requires(**   `graph[i][i] == 1`*);
//@ requires(**);
//@ requires(***Follow up:** If the maximum number of calls to the API `knows` is `3 * n`, can you find a solution in less than `n` steps?*);
//@ ensures(*The integer result is less than or equal to the length of the integer array parameter `nums` and is greater than or equal to 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,2], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,0,1,1,1,2,2,3,3,4], the integer result is equal to 5.*);
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        if (n <= 1) {
            return n;
        }
        //@ maintaining 0 <= j <= nums.length;
        //@ maintaining 0 <= i < j;
        while (j <= n - 1) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }
}