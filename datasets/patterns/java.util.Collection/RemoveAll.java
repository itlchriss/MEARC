import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RemoveAll {
    //@ ensures(*This collection contains no elements that are common with the specified collection.*);
    //@ ensures(*The result is true if this collection changed.*);
    public Boolean CollectionRemoveAll(List<Integer> collection, List<Integer> c) {
        return collection.removeAll(c);
    }
}
