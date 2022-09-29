import java.util.Collection;
import java.util.List;

public class Clear {
    //@ ensures(*The collection will be empty.*);
    //@ ensures(collection.size() == 0);
    public void CollectionClear(Collection<Integer> collection) {
	    collection.clear();
    }
}
