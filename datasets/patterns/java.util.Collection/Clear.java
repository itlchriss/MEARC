import java.util.Collection;
import java.util.List;

public class Clear {
    //@ ensures(*The collection will be empty.*);
    public void CollectionClear(Collection<Integer> collection) {
	    collection.clear();
    }
}
