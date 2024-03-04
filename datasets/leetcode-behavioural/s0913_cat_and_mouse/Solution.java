package g0901_1000.s0913_cat_and_mouse;

// #Hard #Dynamic_Programming #Math #Graph #Memoization #Topological_Sort #Game_Theory
// #2022_03_29_Time_16_ms_(97.20%)_Space_47_MB_(78.40%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static final int DRAW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = 2;
    private static final int MOUSE = 0;
    private static final int CAT = 1;
//@ ensures(*Preconditions:*);
//@ ensures(*The input graph must be a valid undirected graph.*);
//@ ensures(*The graph must have at least 3 nodes.*);
//@ ensures(*Each node in the graph must be represented by a unique integer.*);
//@ ensures(*The mouse starts at node 1 and the cat starts at node 2.*);
//@ ensures(*There is a hole at node 0.*);
//@ ensures(*The mouse and the cat can always move.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the outcome of the game:*);
//@ ensures(*  - 1 if the mouse wins the game.*);
//@ ensures(*  - 2 if the cat wins the game.*);
//@ ensures(*  - 0 if the game is a draw.*);
//@ ensures(*The outcome of the game is determined based on the optimal moves made by both players.*);
//@ ensures(*If the cat occupies the same node as the mouse, the cat wins.*);
//@ ensures(*If the mouse reaches the hole, the mouse wins.*);
//@ ensures(*If a position is repeated (i.e., the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.*);

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] states = new int[n][n][2];
        int[][][] degree = new int[n][n][2];
        for (int m = 0; m < n; ++m) {
            for (int c = 0; c < n; ++c) {
                degree[m][c][MOUSE] = graph[m].length;
                degree[m][c][CAT] = graph[c].length;
                for (int node : graph[c]) {
                    if (node == 0) {
                        --degree[m][c][CAT];
                        break;
                    }
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i < n; ++i) {
            states[0][i][MOUSE] = MOUSE_WIN;
            states[0][i][CAT] = MOUSE_WIN;
            states[i][i][MOUSE] = CAT_WIN;
            states[i][i][CAT] = CAT_WIN;
            q.offer(new int[] {0, i, MOUSE, MOUSE_WIN});
            q.offer(new int[] {i, i, MOUSE, CAT_WIN});
            q.offer(new int[] {0, i, CAT, MOUSE_WIN});
            q.offer(new int[] {i, i, CAT, CAT_WIN});
        }
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int mouse = state[0];
            int cat = state[1];
            int turn = state[2];
            int result = state[3];
            if (mouse == 1 && cat == 2 && turn == MOUSE) {
                return result;
            }
            int prevTurn = 1 - turn;
            for (int prev : graph[prevTurn == MOUSE ? mouse : cat]) {
                int prevMouse = prevTurn == MOUSE ? prev : mouse;
                int prevCat = prevTurn == CAT ? prev : cat;
                if (prevCat != 0
                        && states[prevMouse][prevCat][prevTurn] == DRAW
                        && (prevTurn == MOUSE && result == MOUSE_WIN
                                || prevTurn == CAT && result == CAT_WIN
                                || --degree[prevMouse][prevCat][prevTurn] == 0)) {
                    states[prevMouse][prevCat][prevTurn] = result;
                    q.offer(new int[] {prevMouse, prevCat, prevTurn, result});
                }
            }
        }
        return DRAW;
    }
}