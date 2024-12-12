public class MaxByElimination {

    //@ requires a != null && a.length > 0;
    //@ ensures 0 <= \result < a.length;
    //@ ensures (\forall int i; 0 <= i < a.length; a[i] <= a[\result]);
    public static int findMaxByElimination(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be null and must have at least one element.");
        }

        int left = 0;
        int right = a.length - 1;

        //@ loop_invariant 0 <= left <= right < a.length;
        /*@ loop_invariant ((\forall int i; 0<=i && i<left; a[i] <= a[right]) && (\forall int i; right < i && i < a.length; a[i] <= a[right]))
                    ||  ((\forall int i; 0<=i && i<left; a[i] <= a[left]) && (\forall int i; right < i && i < a.length; a[i] <= a[left]));
        */	
        //@ decreases right-left;
        while (left < right) {
            if (a[left] > a[right]) {
                right--;
            } else {
                left++;
            }
        }

        return left;
    }
}