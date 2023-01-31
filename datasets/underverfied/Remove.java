import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Remove {
    //@ ensures(*Returns true if the element o was removed as a result of this call*);
    /*@ ensures (
        \result == true ==> 
        !(collection.contains(o)) ||
        (
            collection.contains(o) && 
            (
                collection.size() == \old(collection.size()) - 1
            )
        )
    );
    @*/
    public Boolean CollectionRemove(List<Integer> collection, Integer o) {
        return collection.remove(o);
    }
}
