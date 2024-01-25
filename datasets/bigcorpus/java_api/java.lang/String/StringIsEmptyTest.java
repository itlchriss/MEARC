// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringIsEmptyTest
{
    //@ ensures(*Returns true if length() is 0, otherwise false.*);
    public boolean isEmpty(String s) {
      return s.isEmpty();
    }

}