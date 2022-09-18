import java.util.Arrays;

public class JavaArrayAPI {
    
    // //@requires a != null && b != null;
    // //@requires a.length == b.length;
    // //@ requires (\exists char[] x; x == a; \exists char[] y; y == null; x != y);
    // //@ requires (\exists char[] x; x == b; \exists char[] y; y == null; x != y);    
    // //@requires (\exists char[] k; k == a; \exists int x; x == k.length; \exists char[] m; m == b; \exists int y; y == m.length; x == y);
    // //@ requires (\exists int x; \exists char[] k; k == a; x == k.length; \exists char[] m; m == b; \exists int y; y == m.length; x == y);
    // //@ requires (\exists char[] x; x == a; \exists char[] z1; z1 != null && z1.length <= 10; x == z1);
    // //@ requires (\exists char[] x; x == b; \exists char[] z1; z1 != null && z1.length <= 10; x == z1);
    // /*@ 
    //    ensures (\exists boolean x1; x1 == \result; \exists boolean z1; z1 == true; z1 == x1)
    //    ==>
    //    (\forall int k; 0 <= k && k < a.length; a[k] == b[k]);
    // @*/
    // /*@
    //     ensures (\exists boolean x1; x1 == \result; \exists boolean z1; z1 == false; z1 == x1)
    //     ==>
    //     !(\forall int k; 0 <= k && k < a.length; a[k] == b[k]);
    //  @*/
    // public static boolean checkEquals(char[] a, char[] b) {
    //     return Arrays.equals(a, b);
    // }

    //@ requires (\exists int[] x; x == a; \exists int[] z; (\forall int k; 0 < k < z.length; z[k - 1] <= z[k]); z == a);
    //@ requires (\exists int[] x; x == a; \exists int[] z; z != null; z == a);
    /* ensures !((\exists int x; x == \result; \exists int z; z == 0; x >= z) 
        ==> (\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1));
        ensures (\exists int x; x == \result; \exists int z; z == 0; x >= z) 
        ==> (\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1);
    */
    /* ensures !((\result >= 0) 
        ==> (\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1));
                ensures (\exists int x1; x1 == \result; \exists int z2; z2 == 0; x1 >= z2)
        ==> (\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1);
    */
    /*@
        ensures (\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1)
        ==> (\exists int x1; x1 == \result; \exists int z2; z2 == 0; x1 >= z2);
        ensures !(\exists int x1; x1 == a[\result]; \exists int z1; z1 == key; x1 == z1)
        ==> !(\exists int x1; x1 == \result; \exists int z2; z2 == 0; x1 >= z2);
    @*/
    /*
        ensures (\result >= 0) ==> (a[\result] == key);
    */     
    // ensures (\result >= 0) <==> (0 <= \result && \result < a.length && a[\result] == key) && !((-a.length - 1) <= \result && (\forall int j; 0 <= j < (-1-\result); a[j] < key) && (\forall int j; (-1-\result) <= j < a.length; key < a[j]));
    // ensures (\result < 0) <==> !((0 <= \result && \result < a.length && a[\result] == key) && !((-a.length - 1) <= \result && (\forall int j; 0 <= j < (-1-\result); a[j] < key) && (\forall int j; (-1-\result) <= j < a.length; key < a[j])));
    public static int checkBinarySearch(int[] a, int key) {
        return Arrays.binarySearch(a, key);
    }

}
