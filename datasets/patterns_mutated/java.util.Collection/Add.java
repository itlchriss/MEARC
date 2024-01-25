import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Add {
    //@ ensures(*If the collection contains the specified element e, the result is true.*);
    //@ ensures(*If the collection changed, the result is true.*);
    public Boolean CollectionAdd(List<Integer> collection, Integer e) {
        return collection.add(e);
    }
}
