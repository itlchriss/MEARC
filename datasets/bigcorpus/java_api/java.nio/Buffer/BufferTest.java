import java.nio.*;

public class BufferTest {
    public Object array(Buffer buffer) {
        return buffer.array();
    }

    public int arrayOffset(Buffer buffer) {
        return buffer.arrayOffset();
    }

    public int capacity(Buffer buffer) {
        return buffer.capacity();
    }

    // public Buffer clear(Buffer buffer) {
        // return buffer.clear();
    // }

    // public Buffer fliptest(Buffer buffer) {
        // return buffer.flip();
    // }

    public Boolean hasArray(Buffer buffer) {
        return buffer.hasArray();
    }

    public Boolean hasRemaining(Buffer buffer) {
        return buffer.hasRemaining();
    }

    public Boolean isDirect(Buffer buffer) {
        return buffer.isDirect();
    }

    public Boolean isReadOnly(Buffer buffer) {
        return buffer.isReadOnly();
    }

    public int limit(Buffer buffer) {
        return buffer.limit();
    }

    // public Buffer limit(Buffer buffer, int newLimit) {
    //     return buffer.limit(newLimit);
    // }

    // public Buffer mark(Buffer buffer) {
    //     return buffer.mark();
    // }

    public int position(Buffer buffer) {
        return buffer.position();
    }

    // public Buffer position(Buffer buffer, int newPosition) {
    //     return buffer.position(newPosition);
    // }

    public int remaining(Buffer buffer) {
        return buffer.remaining();
    }

    // public Buffer reset(Buffer buffer) {
    //     return buffer.reset();
    // }

    // public Buffer rewind(Buffer buffer) {
        // return buffer.rewind();
    // }
}