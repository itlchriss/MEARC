package g0801_0900.s0835_image_overlap;

// #Medium #Array #Matrix #2022_03_24_Time_6_ms_(98.73%)_Space_43.4_MB_(60.76%)

public class Solution {
	//@ requires(*The input matrices `img1` and `img2` are square matrices of size `n x n`.*);
	//@ requires(*The values in the matrices `img1` and `img2` are either `0` or `1`.*);
	//@ ensures(*The method returns an integer representing the largest possible overlap between the two images.*);
	//@ ensures(*The overlap is calculated by counting the number of positions that have a `1` in both images.*);
	//@ ensures(*The translation of one image does not include any kind of rotation.*);
	//@ ensures(*Any `1` bits that are translated outside of the matrix borders are erased.*);
    public int largestOverlap(int[][] img1, int[][] img2) {
        int[] bits1 = bitwise(img1);
        int[] bits2 = bitwise(img2);
        int n = img1.length;
        int res = 0;
        for (int hori = -1 * n + 1; hori < n; hori++) {
            for (int veti = -1 * n + 1; veti < n; veti++) {
                int curOverLapping = 0;
                if (veti < 0) {
                    for (int i = -1 * veti; i < n; i++) {
                        if (hori < 0) {
                            curOverLapping +=
                                    Integer.bitCount(
                                            (bits1[i] << -1 * hori) & bits2[i - -1 * veti]);
                        } else {
                            curOverLapping +=
                                    Integer.bitCount((bits1[i] >> hori) & bits2[i - -1 * veti]);
                        }
                    }
                } else {
                    for (int i = 0; i < n - veti; i++) {
                        if (hori < 0) {
                            curOverLapping +=
                                    Integer.bitCount((bits1[i] << -1 * hori) & bits2[veti + i]);
                        } else {
                            curOverLapping +=
                                    Integer.bitCount((bits1[i] >> hori) & bits2[veti + i]);
                        }
                    }
                }
                res = Math.max(res, curOverLapping);
            }
        }
        return res;
    }

    private int[] bitwise(int[][] img) {
        int[] bits = new int[img.length];
        for (int i = 0; i < img.length; i++) {
            int cur = 0;
            for (int j = 0; j < img[0].length; j++) {
                cur = cur * 2 + img[i][j];
            }
            bits[i] = cur;
        }
        return bits;
    }
}