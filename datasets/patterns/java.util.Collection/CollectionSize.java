import java.util.Collection;
import java.util.List;

public class CollectionSize {
    //@ ensures(*The result is the number of elements of this collection.*);
    public int CollectionSize(Collection<Integer> collection) {
        return collection.size();
    }
}
