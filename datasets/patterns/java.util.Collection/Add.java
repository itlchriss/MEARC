import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Add {
    //@ ensures(*If the collection contains the specified element e, the result is true.*);
    //@ ensures(*If the collection changed, the result is true.*);
    //@ ensures (((e == null && (\exists int i; 0 <= i < collection.size(); collection.get(i) == null)) || (\exists int i; 0 <= i < collection.size();collection.get(i) == e)) ==> (\result==true));
    //@ ensures (((collection.size() != \old(collection).size()) || (\exists int i; 0 <= i < collection.size(); collection.get(i) != \old(collection).get(i))) ==> (\result==true));
    public Boolean CollectionAdd(List<Integer> collection, Integer e) {
        return collection.add(e);
    }
}
