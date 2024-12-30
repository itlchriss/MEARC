public class Sum {


  //@ requires(*The length of the integer array parameter is greater than 0.*);
  //@ ensures(*The integer result is equal to the sum of the values in the integer array parameter a.*);
  //@ requires(a.length > 0);
  //@ ensures(\result == \sum int i; 0 <= i < a.length; a[i]);
  int m(int[] a) {
    int sum = 0;

    //@ loop_invariant 0 <= i <= a.length;
    for (int i=0; i<a.length; i++) {
      //@ assume Integer.MIN_VALUE <= sum + a[i] <= Integer.MAX_VALUE; // Just assume we never overflow
      sum += a[i];
    }
    return sum;
  }

}

