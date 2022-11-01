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

    //@ requires (*The parameter original should not be null.*);
    //@ ensures (*The size of result equals to the length of parameter original.*);
    public List<Integer> asList(Integer[] original) {
        return Arrays.asList(original);
    }
}
