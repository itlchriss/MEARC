import javax.crypto.*;

// public class MacTest extends Mac {
public class MacTest {

      //@ model public boolean isReset;

      // protected MacTest(MacSpi spi, java.security.Provider provider, java.lang.String algorithm) {
      //       super(spi, provider, algorithm);
      // }

      //Note: cannot access parent's private constructor
      // private Mac(java.security.Provider.Service service, java.util.Iterator<java.security.Provider.Service> iter, java.lang.String algorithm) {
            
      // }

      
      public final java.lang.String getAlgorithm(Mac mac) {
            return mac.getAlgorithm();
      }

      //Note: cannot verify runtime related method
      // public static final Mac getInstance(java.lang.String algorithm) throws java.security.NoSuchAlgorithmException 

      //Note: cannot verify runtime related method
      // public static final Mac getInstance(java.lang.String algorithm, java.lang.String provider) throws java.security.NoSuchAlgorithmException, java.security.NoSuchProviderException;

      //Note: cannot verify runtime related method
      // public static final Mac getInstance(java.lang.String algorithm, java.security.Provider provider) throws java.security.NoSuchAlgorithmException;

      //Note: cannot verify private method
      // void chooseFirstProvider(Mac mac) {
      //       mac.chooseFirstProvider();
      // }

      //Note: cannot verify private method
      // private void chooseProvider(java.security.Key key, java.security.spec.AlgorithmParameterSpec spec) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
            // super.chooseFirstProvider(key, spec);
      // }
      public final java.security.Provider getProvider(Mac mac) {
            return mac.getProvider();
      }
      

      public final int getMacLength(Mac mac) {
            return mac.getMacLength();
      }

      public final void init(Mac mac, java.security.Key key) throws java.security.InvalidKeyException {
            mac.init(key);
      }

      public final void init(Mac mac, java.security.Key key, java.security.spec.AlgorithmParameterSpec spec) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
            mac.init(key, spec);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final void update(Mac mac, byte b) throws java.lang.IllegalStateException {
            mac.update(b);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final void update(Mac mac, byte[] buf) throws java.lang.IllegalStateException {
            mac.update(buf);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final void update(Mac mac, byte[] buf, int offset, int len) throws java.lang.IllegalStateException {
            mac.update(buf, offset, len);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final void update(Mac mac, java.nio.ByteBuffer buffer) {
            mac.update(buffer);
      }
      
      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final byte[] doFinal(Mac mac) throws java.lang.IllegalStateException {
            return mac.doFinal();
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      //@ requires !isReset;
      public final void doFinal(Mac mac, byte[] out, int outOffset) throws ShortBufferException, java.lang.IllegalStateException {
            mac.doFinal(out, outOffset);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final byte[] doFinal(Mac mac, byte[] input) throws java.lang.IllegalStateException {
            return mac.doFinal(input);
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final void reset(Mac mac) {
            mac.reset();
      }

      //Note: this one is a precondition that must exist 
      //@ requires mac.initialized;
      public final java.lang.Object clone(Mac mac) throws java.lang.CloneNotSupportedException {
            return mac.clone();
      }

}

