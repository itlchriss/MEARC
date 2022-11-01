import java.util.Collection;
import java.util.List;

public class IsEmpty {
    //@ ensures(*if this collection is empty, the result is true.*);
    //@ ensures(collection.size() == 0 ==> \result == true);
    public boolean CollectionIsEmpty(Collection<Integer> collection) {
	    return collection.isEmpty();
    }
}
