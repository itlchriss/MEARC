import java.util.Arrays;
import java.util.List;

public class AsList {
    /*
         Original: 
         returns: 
         a list view of the specified array
         description:
         Returns a fixed-size list backed by the specified array. 
         (Changes to the returned list "write through" to the array.) 
         This method acts as bridge between array-based and collection-based APIs, 
         in combination with Collection.toArray(). 
         The returned list is serializable and implements RandomAccess. 
    */

    // ensures (*a list view of the specified array.*);
    // However, this is not a good specification. It mentioned the type, but nothing else.
    // ensures (*Returns a fixed-size list backed by the specified array.*);
    // This one is much better, at least it mentions the size.
    // ensures (*The result is a fixed-size list backed by the specified array.*);
    // Nevertheless, this specification is still too bad.
    // Rewriting it as:
    //+ semantics "length of parameter original", nn, 1, (*):original.length
    //+ semantics "size of result", nn, 1, (*):\result.size()
    //+ semantics "parameter original", nn, 1, (*):original.length
    //@ requires (*The parameter original should not be null.*);
    //@ ensures (*The size of result must be equal to the length of parameter original.*);
    public List<Integer> asList(Integer[] original) {
        return Arrays.asList(original);
    }
}