public class Largest {
    // /*@ ensures(
    //     (x >= y) ==> \result == x);
    // @*/
    // /*@ ensures(
    //     (x < y) ==> \result == y);
    // @*/
    //@ ensures \result >= x && \result >= y;
    //@ ensures \result == x || \result == y;
    //@ ensures (*The result should be greater than or equal to the parameter x and the result should be greater than or equal to the parameter y.*);
    //@ ensures (*The result is equal to the parameter x or the result is equal to the parameter y.*);
    public static int largest(int x, int y) {
        int max = x;
        if (x <= y)
            max = y;
        return max;
    }
}
