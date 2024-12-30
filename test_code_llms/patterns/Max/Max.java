public class Max {


  //@ requires(*The length of the integer array parameter is greater than 0.*);
  //@ ensures(*All values in the integer array parameter a are less than or equal to the integer result.*);
  int m(int[] a) {
    int max = a[0];

    //@ loop_invariant 0 <= i <= a.length;
    for (int i=0; i<a.length; i++) {
      if (max < a[i]) max = a[i];
    }
    return max;
  }

}

