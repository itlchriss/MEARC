import java.util.Collection;
import java.util.List;

public class Contains {
    //@ ensures(*If this collection contains the specified element o, the result is true.*);
    //@ ensures((\exists int i; 0 <= i < collection.length; collection[i] == o) ==> (\result==true));
    public Boolean CollectionContains(Collection<Integer> collection, Integer o) {
        return collection.contains(o);
    }
}
