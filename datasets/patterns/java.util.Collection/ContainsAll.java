import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ContainsAll {
    // ensures(*Returns true if this collection contains all of the elements in the specified collection.*);
    //@ ensures(*if this collection coll contains all of the elements in the specified collection e, the result is true*);
    /*@ 
        ensures (
            e == null ||
            (
                e != null && collection != null && e.size() > 0 && 
                (   
                    (\forall int i; 0 <= i < e.size(); collection.contains(e)) &&
                    collection.size() == \old(collection).size() + e.size()
                )
            ) ==> (\result == true)
        );
    @*/
    public Boolean ContainsAll(List<Integer> coll, List<Integer> e) {
        return collection.containsAll(e);
    }
}