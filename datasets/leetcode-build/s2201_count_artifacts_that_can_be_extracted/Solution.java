package g2201_2300.s2201_count_artifacts_that_can_be_extracted;

// #Medium #Array #Hash_Table #Simulation #2022_06_11_Time_7_ms_(82.97%)_Space_91.5_MB_(99.45%)

public class Solution {
	//@ requires(*The input grid size `n` is a positive integer.*);
	//@ requires(*The `artifacts` array is not null and contains valid artifact coordinates.*);
	//@ requires(*The `dig` array is not null and contains valid excavation coordinates.*);
	//@ requires(*The entries of `dig` are unique.*);
	//@ ensures(*The method returns an integer representing the number of artifacts that can be extracted.*);
	//@ ensures(*The artifacts are extracted by excavating cells in the grid.*);
	//@ ensures(*If a cell is excavated and it contains a part of an artifact, that part is uncovered.*);
	//@ ensures(*If all the parts of an artifact are uncovered, it can be extracted.*);
	//@ ensures(*The method does not modify the original grid or artifact coordinates.*);
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int[][] ar = new int[n][n];
        for (int[] ints : dig) {
            ar[ints[0]][ints[1]] = 1;
        }
        int ans = 0;
        for (int[] artifact : artifacts) {
            int x1 = artifact[0];
            int y1 = artifact[1];
            int x2 = artifact[2];
            int y2 = artifact[3];
            int flag = 0;
            int a = x1;
            int b = y1;
            while (a <= x2) {
                b = y1;
                while (b <= y2) {
                    if (ar[a][b] != 1) {
                        flag = 1;
                        break;
                    }
                    b++;
                }
                if (flag == 1) {
                    break;
                }
                a++;
            }
            if (a == x2 + 1 && b == y2 + 1) {
                ans++;
            }
        }
        return ans;
    }
}