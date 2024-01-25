// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringGetBytesTest
{

    //@ requires(*charsetName - The name of a supported charset*);
    //@ ensures(*Returns the resultant byte array.*);
    //@ signals(UnsupportedEncodingException e)(*If the named charset is not supported.*);
    public byte[] getBytes(String s, String charsetName)
      throws UnsupportedEncodingException {
        return s.getBytes(charsetName);
      }
}