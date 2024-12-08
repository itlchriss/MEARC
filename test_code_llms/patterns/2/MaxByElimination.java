public class MaxByElimination {

  //@ requires(*The integer array parameter a is not null and the length of the integer array parameter a is greater than 0.*);
  //@ ensures(*The integer result is less than the length of the integer array parameter a and is greater than or equal to 0.*);
  //@ ensures(*All values in the integer array parameter a are less than or equal to the value of the integer array parameter a at the index of the result.*);
  public static int max(int[] a) {
    int x = 0;
    int y = a.length-1;

    //@ loop_invariant 0 <= x <= y < a.length;
    // So far either a[y] is the largest or a[x] is the largest of everything beyond x and beyond y (not including a[x] and a[y])
    /*@ loop_invariant ((\forall int i; 0<=i && i<x; a[i] <= a[y]) && (\forall int i; y < i && i < a.length; a[i] <= a[y]))
	               ||  ((\forall int i; 0<=i && i<x; a[i] <= a[x]) && (\forall int i; y < i && i < a.length; a[i] <= a[x]));
     */	
    //@ decreases y-x;
    while (x != y) {
      if (a[x] <= a[y]) x++;
      else y--;
    }
    return x;
  }
}

