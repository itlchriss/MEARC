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
    //@ requires array != null;
    //@ requires (*The parameter array must be sorted in ascending order.*);
    //@ requires (\forall int i; 0 < i < array.length; array[i-1] <= array[i]); 
    //@ ensures (*Returns the parameter array's index of the parameter key if the parameter array contains the parameter key.*);
    // ===> preprocessed to *The result is the array's index of the search key if the array contains the key*
    //@ ensures ((\exists int i; 0 <= i < array.length; array[i] == key) ==> \result == \old(Arrays.asList(array).indexOf(key)));
    //@ ensures (*The return value will be >= 0 if and only if the parameter array contains the parameter key.*);
    // ===> modified to *The return_value is greater than or equal to 0 if and only if the array contains the key.*
    // ===> preprocessed to *The return_value is greater_than_or_equal_to 0 _if_ the array contains the key.*
    //@ ensures (\result >= 0 <==> (\exists int i; 0 <= i < array.length; array[i] == key));
    //@ ensures (*If the parameter array does not contain the parameter key, the (-result-1) is the parameter key's insertion point in the parameter array.*);
    /* ensures (
            !(\exists int i; 0 <= i < array.length; array[i] == key) ==>
            ((
                (\forall int j; 0 <= j < array.length; array[j] < key) ==> \result == (-1-array.length)
            ) || 
            (
                (\forall int j; 0 <= j < (-1-\result); array[j] < key) && (\forall int j; (-1-\result) <= j < array.length; key < array[j])
            ))
        );
    */
    /*@ ensures (
            !(\exists int i; 0 <= i < array.length; array[i] == key) ==>
            (\forall int j; 0 <= j < (-1-\result); array[j] < key) && (\forall int j; (-1-\result) <= j < array.length; key < array[j])
        );
    @*/
    public int binarySearch(int[] array, int key) {
        return Arrays.binarySearch(array, key);
    }
}

/*
exists x.(
    _result(x) & 
    exists z3.(
        exists z1.(
            _array(z1) & 
            Rel(z3,z1)
        ) & 
        _index(z3) & 
        exists z2.(
            _search(z2) & 
            _key(z2) & 
            _of(z3,z2)
        ) & (x = z3)))


z3 === _index ---> \old(Arrays.asList((x)).indexOf((y)))
z1 === _array
z3 === _key 
Rel(z3, z1) === Rel(_index, _array) === \old(Arrays.asList(array).indexOf((y)))
----Note: Rel(x, y) x must be in type expression
_of(z3, z2) === \sub(z2)2(z3) === \old(Arrays.asList(array).indexOf(key))
----Note: add an SI for _of, where _of(x, y) is \sub(y)2(x) restricting (x) is an expression


x === \result
(x = z3) === \result == \old(Arrays.asList(array)).indexOf(key)
*/