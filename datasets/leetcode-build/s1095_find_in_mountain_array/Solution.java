package g1001_1100.s1095_find_in_mountain_array;

// #Hard #Array #Binary_Search #Interactive #2022_02_22_Time_0_ms_(100.00%)_Space_42.1_MB_(37.47%)

/*
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *
	//@ requires(*The length of the mountain array must be at least 3.*);
	//@ requires(*The target value must be between 0 and 10^9 (inclusive).*);
	//@ requires(*The values in the mountain array must be between 0 and 10^9 (inclusive).*);
	//@ ensures(*If the minimum index such that `mountainArr.get(index) == target` exists, return that index.*);
	//@ ensures(*If the minimum index does not exist, return -1.*);     public int get(int index) {}
 *     public int length() {}
 * }
 */
public class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peakIndex = findPeak(mountainArr);
        if (target == mountainArr.get(peakIndex)) {
            return peakIndex;
        }
        int leftResult = findInPeakLeft(target, peakIndex, mountainArr);
        if (leftResult != -1) {
            return leftResult;
        }
        return findInPeakRight(target, peakIndex, mountainArr);
    }

    private int findPeak(MountainArray mountainArray) {
        int len = mountainArray.length();
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArray.get(mid) < mountainArray.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int findInPeakLeft(int target, int peakIndex, MountainArray mountainArray) {
        int leftIndex = 0;
        int rightIndex = peakIndex - 1;
        while (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (target > mountainArray.get(midIndex)) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex;
            }
        }
        return target == mountainArray.get(leftIndex) ? leftIndex : -1;
    }

    private int findInPeakRight(int target, int peakIndex, MountainArray mountainArray) {
        int leftIndex = peakIndex + 1;
        int rightIndex = mountainArray.length() - 1;
        while (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (target < mountainArray.get(midIndex)) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex;
            }
        }
        return target == mountainArray.get(leftIndex) ? leftIndex : -1;
    }
}