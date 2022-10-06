import java.util.List;
import java.util.Objects;

public class Set {
    // ensures (*Replaces the element at the specified position index in this list x with the specified element y.*);
    //@ ensures (*the element at the specified position index in this list x is replaced by the specified element y.*);
    public void listSet(List<Integer> x, int index, Integer y) {
            x.set(index, y);
    }
}