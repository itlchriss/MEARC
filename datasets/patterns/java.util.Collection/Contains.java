import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Contains {
    //@ ensures(*If this collection contains the specified element o, the result is true.*);
    public Boolean CollectionContains(List<Integer> collection, Integer o) {
        return collection.contains(o);
    }
}