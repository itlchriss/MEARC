import java.util.Collection;
import java.util.List;

public class Add {
    //@ ensures(*The collection contains the specified element e.*);
    //@ ensures(*If the collection changed, the result is true.*);
    public Boolean CollectionAdd(Collection<Integer> collection, Integer e) {
        return collection.add(e);
    }
}
