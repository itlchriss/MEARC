import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Contains {
    //@ ensures(*Returns true if the colleaction contains the specified element o.*);
    public Boolean CollectionContains(List<Integer> collection, Integer o) {
        return collection.contains(o);
    }
}
