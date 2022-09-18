import java.util.Arrays;

public class BinarySearch {
    //- eliminates "(as by the sort(int[]) method)"
    //- eliminates "prior to making this call"    
    //- semantics "sort",vbn,1,(x):\forall int k;0 <= k && k < x.length-1;x[k]<=x[k+1]
    //- semantics "input array", nn, 1, (*):array
    //- semantics "specified key", nn, 1, (*):key
    //- eliminates "defined as the point at which the key would be inserted into the array:"
    //- eliminates "Note that this guarantees that"
    //- eliminates "If the array is not sorted, the results are undefined."
    //- semantics "index of the search key", nn, 1, (*):Arrays.asList(array).indexOf(key)
    //- semantics "index of the first element greater than the key", nn, 1, (*):\exists int ip; (\forall int k; k < ip && array[ip] > key); ip
    //- semantics "all elements in the array", nn, 1, (*):\forall int i; 0 <= i < array.length; array[i]
    //- semantics "some elements in the array", nn, 1, (*):\exists int i; 0 <= i < array.length; array[i]
    //- semantics "insertion point", nn, 1, (*):-\result-1
    //- semantics "array.length", nn, 1, (*):array.length
    //- semantics "contain", vb, 2, (a, b):\exists int i; 0 <= i < a.length; a[i] == b
    //- semantics "less than", in, 2, (a, b):a < b
    //- semantics "greater than", vbg, 2, (a, b):a > b
    //- semantics "do", vbz, 1, (*):I
    //@ requires (*The array must be sorted (as by the sort(int[]) method) prior to making this call.*);
    //@ ensures (*The result is the index of the search key, if the array contains the key.*);
    //@ ensures (*If the array does not contain the key and all elements in the array are less than the key, the insertion point is equal to the array.length.*);
    //@ ensures (*If the array does not contain the key and some elements in the array are greater than the key, the insertion point is the index of the first element greater than the key.*);
    //@ requires !(array == null);
    //@ requires (\forall int k;0 < k < array.length;array[k -1]<=array[k]);
    // This is working.
    // ensures (\exists int i; 0 <= i < array.length; array[i] == key) ==> (array[\result] == key); 
    /* ensures (\exists int i; 0 <= i < array.length; array[i] == key) ==> 
        (\result==Arrays.asList(array).indexOf(key));
    */
    // Arrays.asList(array).indexOf(key) --> this is not correctly evaluated in z3/openjml
    // fix it by array[\result] == key
    /* ensures ( (\forall int i; 0 <= i < array.length; array[i] <key) ) 
        ==> ((\result*-1)-1==array.length);
    */
    /* ensures (\exists int i; 0 <= i < array.length; array[i] >key) && 
        !(\exists int i; 0 <= i < array.length; array[i] ==key) ==> 
        (\exists int ip; (\forall int k; k < ip; array[ip] > key && array[k] < key); ip==-\result-1);
    */
    public int binarySearch(int[] array, int key) {
        return Arrays.binarySearch(array, key);        
    }

    // public static void main(String args[]) {
    //     BinarySearch b = new BinarySearch();
    //     b.binarySearch(new int[]{1, 2, 3, 4, 5}, 5);
    // }
}