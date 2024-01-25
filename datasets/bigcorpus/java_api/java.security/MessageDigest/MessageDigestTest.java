// package java.security;
import java.security.*;
import java.lang.String;

// public abstract class MessageDigest extends MessageDigestSpi {
public class MessageDigestTest extends MessageDigest {

  protected MessageDigestTest(java.lang.String s) {
    super(s);
  }

//   public static MessageDigestTest getInstance(java.lang.String s) throws NoSuchAlgorithmException {
    // return this.getInstance(s);
//   }

//   public static MessageDigestTest getInstance(java.lang.String s, java.lang.String ss) throws NoSuchAlgorithmException, NoSuchProviderException {
//     super(s, ss);
//   }

//   public static MessageDigestTest getInstance(java.lang.String s, Provider p) throws NoSuchAlgorithmException {
//     super(s, p);
//   }
  
//   public final Provider getProvider();
  
  public void update(byte b) {
    super.update(b);
  }

  public void update(byte[] buf, int offset, int length) {
    super.update(buf, offset, length);
  }
  
  public void update(byte[] bb) {
    super.update(bb);
  }
  
//   public final void update(java.nio.ByteBuffer bb) {
//     super.update(bb);
//   }
  
  public byte[] digest() {
    return super.digest();
  }

  public int digest(byte[] bb, int offset, int length) throws DigestException {
    return super.digest(bb, offset, length);
  }
  
  public byte[] digest(byte[] bb) {
    return super.digest(bb);
  }
  
  public java.lang.String toString() {
    return super.toString();
  }
  
  public static boolean isEqual(byte[] bb, byte[] bbb) {
    // return super.isEqual(bb, bbb);
    return MessageDigest.isEqual(bb, bbb);
  }

  public void reset() {
    super.reset();
  }
//   public final java.lang.String getAlgorithm();
  
//   public final int getDigestLength();
  public java.lang.Object clone() throws java.lang.CloneNotSupportedException {
    return super.clone();
  }
}