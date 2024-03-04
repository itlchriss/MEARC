package g1301_1400.s1319_number_of_operations_to_make_network_connected;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #Graph_Theory_I_Day_8_Standard_Traversal #2022_03_19_Time_9_ms_(67.64%)_Space_64.6_MB_(49.60%)

import java.util.Arrays;

public class Solution {

    private static final int IMPOSSIBLE_TO_CONNECT = -1;
    private int disconnectedComputers;
    private int[] parent;
    private int[] rank;
//@ ensures(*Preconditions:*);
//@ ensures(*The total number of computers, `totalNumberOfComputers`, is a positive integer.*);
//@ ensures(*The `connections` array is not null.*);
//@ ensures(*The `connections` array has a length greater than or equal to 1.*);
//@ ensures(*Each element in the `connections` array is an array of length 2.*);
//@ ensures(*The first element of each connection, `connections[i][0]`, is a valid computer number (between 0 and `totalNumberOfComputers - 1`).*);
//@ ensures(*The second element of each connection, `connections[i][1]`, is a valid computer number (between 0 and `totalNumberOfComputers - 1`).*);
//@ ensures(*The first and second elements of each connection, `connections[i][0]` and `connections[i][1]`, are not equal.*);
//@ ensures(*There are no repeated connections.*);
//@ ensures(*No two computers are connected by more than one cable.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the minimum number of times cables need to be extracted and placed in order to make all computers connected.*);
//@ ensures(*If it is not possible to make all computers connected, the return value is -1.*);

    public int makeConnected(int totalNumberOfComputers, int[][] connections) {
        if (connections.length < totalNumberOfComputers - 1) {
            return IMPOSSIBLE_TO_CONNECT;
        }

        disconnectedComputers = totalNumberOfComputers;
        rank = new int[totalNumberOfComputers];
        parent = new int[totalNumberOfComputers];
        Arrays.setAll(parent, intFromZero -> intFromZero++);

        for (final int[] connection : connections) {
            unionFind(connection[0], connection[1]);
        }

        return disconnectedComputers - 1;
    }

    private void unionFind(int first, int second) {
        int parentFirst = findParent(first);
        int parentSecond = findParent(second);

        if (parentFirst != parentSecond) {
            joinByRank(parentFirst, parentSecond);
            disconnectedComputers--;
        }
    }

    private int findParent(int index) {
        if (parent[index] != index) {
            parent[index] = findParent(parent[index]);
        }
        return parent[index];
    }

    private void joinByRank(int first, int second) {
        if (rank[first] < rank[second]) {
            parent[first] = second;
        } else if (rank[second] < rank[first]) {
            parent[second] = first;
        } else {
            parent[first] = second;
            rank[second]++;
        }
    }
}