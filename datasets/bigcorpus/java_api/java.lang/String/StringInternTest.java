// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringTest
{
    //Note: native method cannot have a body. this is point that we need to explain in the experiment setup
    //@ ensures(*Returns a string that has the same contents as this string, but is guaranteed to be from a pool of unique strings.*);
    public String intern(String s) {
      return s.intern();
    }


}
