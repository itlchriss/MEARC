import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ContainsAll {
    //@ ensures(*if this collection coll contains all of the elements in the specified collection e, the result is true*);
    public Boolean ContainsAll(List<Integer> coll, List<Integer> e) {
        return collection.containsAll(e);
    }
}