import java.util.Arrays;

public class BinarySearchInt2 {   
    //@ requires (*The parameter array must not be null.*);
    //@ requires (*The parameter array must be sorted in ascending order.*);
    //@ ensures (*Returns the parameter array's index of the parameter key if the parameter array contains the parameter key.*);
    //@ ensures (*Returns the index of the search key in the array if the array contains the key.*);
    // ===> preprocessed to *The result is the array's index of the search key if the array contains the key*
    //@ ensures (*The return value will be >= 0 if and only if the parameter array contains the parameter key.*);
    // ===> modified to *The return_value is greater than or equal to 0 if and only if the array contains the key.*
    // ===> preprocessed to *The return_value is greater_than_or_equal_to 0 _if_ the array contains the key.*
    //@ ensures (*If the parameter array does not contain the parameter key, the (-result-1) is the parameter key's insertion point in the parameter array.*);
    public int binarySearch(int[] array, int key) {
        return Arrays.binarySearch(array, key);
    }
}