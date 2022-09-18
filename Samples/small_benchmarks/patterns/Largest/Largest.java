public class Largest {
    // /*@ ensures(
    //     (n1 >= n2) ==> \result == n1);
    // @*/
    // /*@ ensures(
    //     (n1 < n2) ==> \result == n2);
    // @*/
    //@ ensures \result >= n1 && \result >= n2;
    //@ ensures \result == n1 || \result == n2;
    public static int largest(int n1, int n2) {
        int max = n1;
        if (n1 <= n2)
            max = n2;
        return max;
    }
}