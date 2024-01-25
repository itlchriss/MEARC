// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringGetCharsTest
{


    // Note: this method cannot be verified initially
    public void getChars(String s, int srcBegin, int srcEnd,
                                        char[] dst, int dstBegin)
      throws IndexOutOfBoundsException {
        s.getChars(srcBegin, srcEnd, dst, dstBegin);
      }



}
