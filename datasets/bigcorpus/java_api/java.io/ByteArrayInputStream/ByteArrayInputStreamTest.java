import java.io.*;

public class ByteArrayInputStreamTest extends ByteArrayInputStream {

    public ByteArrayInputStreamTest(byte[] bytes) {
        super(bytes);
    }

    public ByteArrayInputStreamTest(byte[] bytes, int off, int len) {
        super(bytes, off, len);
    }

    public synchronized int available() {
        return super.available();
    }

    // public synchronized int read() {
        // return super.read();
    // }

    public void close() throws IOException {
        super.close();
    }

    public synchronized void reset() {
        super.reset();
    }

    public boolean markSupported() {
        return super.markSupported();
    }

    public void mark(int limit) {
        super.mark(limit);
    }

    public synchronized long skip(long len) {
        return super.skip(len);
    }

    public synchronized int read(byte[] bytes, int off, int len) {
        return super.read(bytes, off, len);
    }
}