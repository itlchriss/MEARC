package g1701_1800.s1739_building_boxes;

// #Hard #Math #Greedy #Binary_Search #2022_04_29_Time_1_ms_(84.38%)_Space_39.2_MB_(84.38%)

public class Solution {
    static final double ONE_THIRD = 1.0d / 3.0d;
	//@ requires(*The input `n` is a positive integer.*);
	//@ ensures(*The output is an integer representing the minimum number of boxes touching the floor.*);
	//@ ensures(*The boxes are placed in the storeroom according to the given rules.*);
	//@ ensures(*Each box is a cube with a side length of 1 unit.*);
	//@ ensures(*The width, length, and height of the storeroom are all equal to `n` units.*);
	//@ ensures(*The boxes are placed anywhere on the floor of the storeroom.*);
	//@ ensures(*If box `x` is placed on top of box `y`, each side of the four vertical sides of box `y` must either be adjacent to another box or to a wall.*);

    public int minimumBoxes(int n) {
        int k = findLargestTetrahedralNotGreaterThan(n);
        int used = tetrahedral(k);
        int floor = triangular(k);
        int unused = (n - used);
        if (unused == 0) {
            return floor;
        }
        int r = findSmallestTriangularNotLessThan(unused);
        return (floor + r);
    }

    private int findLargestTetrahedralNotGreaterThan(int te) {
        int a = (int) Math.ceil(Math.pow(product(6, te), ONE_THIRD));
        while (tetrahedral(a) > te) {
            a--;
        }
        return a;
    }

    private int findSmallestTriangularNotLessThan(int t) {
        int a = -1 + (int) Math.floor(Math.sqrt(product(t, 2)));
        while (triangular(a) < t) {
            a++;
        }
        return a;
    }

    private int tetrahedral(int a) {
        return (int) ratio(product(a, a + 1, a + 2), 6);
    }

    private int triangular(int a) {
        return (int) ratio(product(a, a + 1), 2);
    }

    private long product(long... vals) {
        long product = 1L;
        for (long val : vals) {
            product *= val;
        }
        return product;
    }

    private long ratio(long a, long b) {
        return (a / b);
    }
}