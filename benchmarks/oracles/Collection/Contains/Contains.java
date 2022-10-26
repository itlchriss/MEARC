import java.util.Collection;
import java.util.Collections;
import java.util.List;

// public class Contains {
//     //@ ensures(*If this collection contains the specified element o, the result is true.*);
//     //@ ensures((\exists int i; 0 <= i < collection.size(); ((List<Integer>)collection).get(i) == o) ==> (\result==true));
//     public Boolean CollectionContains(Collection<Integer> collection, Integer o) {
//         return collection.contains(o);
//     }
// }
public class Contains {
    //@ ensures(*If this collection contains the specified element o, the result is true.*);
    /*
        ensures(
            ((o == null && (\exists int i; 0 <= i < collection.size(); collection.get(i) == null)) ||
            (
                \exists int i; 0 <= i < collection.size(); collection.get(i) == o
            )) ==> (\result==true)
        );
    @*/
    //@ ensures (((o == null && (\exists int i; 0 <= i < collection.size(); collection.get(i) == null)) || (\exists int i; 0 <= i < collection.size();collection.get(i) == o)) ==> (\result==true));
    public Boolean CollectionContains(List<Integer> collection, Integer o) {
        return collection.contains(o);
    }
}