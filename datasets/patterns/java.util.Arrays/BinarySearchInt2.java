import java.util.Arrays;

public class BinarySearchInt2 {   
    /*  public normal_behavior
         requires array != null &&
                  (\forall int i; 
                   0 < i < array.length;
                   array[i-1] <= array[i]); 
        old boolean hasKey = (\exists int i; 0 <= i < array.length; array[i] == key);
        {| 
           requires hasKey;
           ensures 0 <= \result && \result < array.length 
                 && array[\result] == key;
          also
           requires !hasKey;
           ensures \result < 0 && (-array.length-1) <= \result;
           ensures (\forall int j; 0 <= j < (-1-\result); array[j] < key);
           ensures (\forall int j; (-1-\result) <= j < array.length; key < array[j]);
      |}
      */ 
    //@ requires (*The parameter array must not be null.*);
    //@ requires (*The parameter array must be sorted in ascending order.*);
    //@ ensures (*Returns the parameter array's index of the parameter key if the parameter array contains the parameter key.*);
    //@ ensures (*The return value will be >= 0 if and only if the parameter array contains the parameter key.*);
    //@ ensures (*If the parameter array does not contain the parameter key, the (-result-1) is the parameter key's insertion point in the parameter array.*);
    public int binarySearch(int[] array, int key) {
        return Arrays.binarySearch(array, key);
    }
}

