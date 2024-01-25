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
    //@ ensures(*Returns the length of the sequence of characters represented by this object.*);
    public int length(String s) {
      return s.length();
    }
    
    //@ ensures(*Returns true if length() is 0, otherwise false.*);
    public boolean isEmpty(String s) {
      return s.isEmpty();
    }

    //@ requires(*index - the index of the char value.*);
    //@ ensures(*Returns the char value at the specified index of this string. The first char value is at index 0.*);
    //@ signals(StringIndexOutOfBoundsException e)(*if the index argument is negative or not less than the length of this string.*);
    public char charAt(String s, int index)
        throws StringIndexOutOfBoundsException {
          return s.charAt(index);
        }

    // Note: this method cannot be verified initially
    //@ requires(*srcBegin - index of the first character in the string to copy.*);
    //@ requires(*srcEnd - index after the last character in the string to copy.*);
    //@ requires(*dst - the destination array.*);
    //@ requires(*dstBegin - the start offset in the destination array.*);
    //@ ensures(*Copies characters from this string into the destination character array.*);
    public void getChars(String s, int srcBegin, int srcEnd,
                                        char[] dst, int dstBegin)
      throws IndexOutOfBoundsException {
        s.getChars(srcBegin, srcEnd, dst, dstBegin);
      }

    //@ requires(*charsetName - The name of a supported charset*);
    //@ ensures(*Returns the resultant byte array.*);
    //@ signals(UnsupportedEncodingException e)(*If the named charset is not supported.*);
    public byte[] getBytes(String s, String charsetName)
      throws UnsupportedEncodingException {
        return s.getBytes(charsetName);
      }

    //@ requires(*charset - The Charset to be used to encode the String*);
    //@ ensures(*Returns the resultant byte array.*);
    public byte[] getBytes(String s, java.nio.charset.Charset charset)
      throws UnsupportedEncodingException {
        return s.getBytes(charset);
      }


    //@ ensures(*Returns the resultant byte array.*);
    //@ ensures(*Encodes this String into a sequence of bytes using the platform's default charset, storing the result into a new byte array.*);
    public byte[] getBytes(String s) {
        return s.getBytes();
    }

    // Note: this method cannot be verified initially    
    //@ ensures(*Compares this string to the specified object.*);
    public boolean equals(String s, Object anObject) {
      return s.equals(anObject);
    }

    //Note: this method cannot be verified initially
    public boolean contentEquals(String s, StringBuffer sb) {
      return s.contentEquals(sb);
    }

    //@ requires(*anotherString - The String to compare this String against.*);
    //@ ensures(*Returns true if the argument is not null and it represents an equivalent String ignoring case; false otherwise.*);
    public boolean equalsIgnoreCase(String s, String anotherString) {
      return s.equalsIgnoreCase(anotherString);
    }

    //@ requires(*anotherString - the String to be compared.*);
    //@ ensures(*Returns the value 0 if the argument string is equal to this string; a value less than 0 if this string is lexicographically less than the string argument; and a value greater than 0 if this string is lexicographically greater than the string argument.*);
    public int compareTo(String s, String anotherString) {
      return s.compareTo(anotherString);
    }

    // Note: this method cannot be verified initially
    public int compareToIgnoreCase(String s, String anotherstr) {
      return s.compareToIgnoreCase(anotherstr);
    }

    // Note: this method cannot be verified initially
    public boolean regionMatches(String s, int toffset, String other, int ooffset, int len) {
      return s.regionMatches(toffset, other, ooffset, len);
    }

    // Note: this method cannot be verified initially
    public boolean Matches(String s, boolean ignoreCase, int toffset, String other, int ooffset, int len) {
      return s.regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    // Note: this method cannot be verified initially
    public boolean startsWith(String s, String prefix, int toffset) {
      return s.startsWith(prefix, toffset);
    }

    //@ requires(*prefix - the prefix.*);
    //@ ensures(*Returns true if the character sequence represented by the argument is a prefix of the character sequence represented by this string; false otherwise. Note also that true will be returned if the argument is an empty string or is equal to this String object as determined by the equals(Object) method.*);
    public boolean startsWith(String s, String prefix) {
      return s.startsWith(prefix);
    }

    //@ requires(*suffix - the suffix.*);
    //@ ensures(*true if the character sequence represented by the argument is a suffix of the character sequence represented by this object; false otherwise. Note that the result will be true if the argument is the empty string or is equal to this String object as determined by the equals(Object) method.*);
    public boolean endsWith(String s, String suffix) {
      return s.endsWith(suffix);
    }

    // specification is inherited, this method does have side effects!
    //@ ensures(*Returns a hash code value for this object.*);
    public int hashCode(String s) {
      return s.hashCode();
    }

    //@ requires(*ch - a character (Unicode code point).*);
    //@ ensures(*Returns the index of the first occurrence of the character in the character sequence represented by this object, or -1 if the character does not occur.*);
    public int indexOf(String s, int ch) {
      return s.indexOf(ch);
    }

    // Note: this method cannot be verified initially
    public int indexOf(String s, int ch, int fromIndex) {
      return s.indexOf(ch, fromIndex);
    }

    // Note: this method cannot be verified initially
    public int lastIndexOf(String s, int ch) {
      return s.lastIndexOf(ch);
    }

    //@ requires(*ch - a character (Unicode code point).*);
    //@ requires(*fromIndex - the index to start the search from. There is no restriction on the value of fromIndex. If it is greater than or equal to the length of this string, it has the same effect as if it were equal to one less than the length of this string: this entire string may be searched. If it is negative, it has the same effect as if it were -1: -1 is returned.*);
    //@ ensures(*Returns the index of the last occurrence of the character in the character sequence represented by this object that is less than or equal to fromIndex, or -1 if the character does not occur before that point.*);
    public int lastIndexOf(String s, int ch, int fromIndex) {
      return s.lastIndexOf(ch, fromIndex);
    }

    // Note: this method cannot be verified initially
    public int indexOf(String s, String str) {
      return s.indexOf(str);
    }

    // Note: this method cannot be verified initially
    public int indexOf(String s, String str, int fromIndex) {
      return s.indexOf(str, fromIndex);
    }
      
    // Note: this method cannot be verified initially
    public int lastIndexOf(String s, String str) {
      return s.lastIndexOf(str);
    }

    // Note: this method cannot be verified initially
    public int lastIndexOf(String s, String str, int fromIndex) {
      return s.lastIndexOf(str, fromIndex);
    }


    //@ requires(*beginIndex - the beginning index, inclusive.*);
    //@ ensures(*Returns the specified substring.*);
    //@ signals(StringIndexOutOfBoundsException e)(*if beginIndex is negative or larger than the length of this String object.*);
    public String substring(String s, int beginIndex)
      throws StringIndexOutOfBoundsException {
      return s.substring(beginIndex);
    }

    //@ requires(*beginIndex - the beginning index, inclusive.*);
    //@ requires(*endIndex - the ending index, exclusive.*);
    //@ ensures(*Returns the specified substring.*);
    //@ signals(StringIndexOutOfBoundsException e)(*if the beginIndex is negative, or endIndex is larger than the length of this String object, or beginIndex is larger than endIndex.*);
    public String substring(String s, int beginIndex,int endIndex)
      throws StringIndexOutOfBoundsException {
      return s.substring(beginIndex, endIndex);
    }

    //@ requires(*beginIndex - the beginning index, inclusive.*);
    //@ requires(*endIndex - the ending index, exclusive.*);
    //@ ensures(*Returns the specified subsequence.*);
    //@ signals(IndexOutOfBoundsException e)(*if beginIndex or endIndex is negative, if endIndex is greater than length(), or if beginIndex is greater than endIndex.*);
    public CharSequence subSequence(String s, int beginIndex, int endIndex) {
      return s.subSequence(beginIndex, endIndex);
    }

    //@ requires(*str - the String that is concatenated to the end of this String.*);
    //@ ensures(*Returns a string that represents the concatenation of this object's characters followed by the string argument's characters.*);
    public String concat(String s, String str) {
      return s.concat(str);
    }

    //@ requires(*oldChar - the old character.*);
    //@ requires(*newChar - the new character.*);
    //@ ensures(*Returns a string derived from this string by replacing every occurrence of oldChar with newChar.*);
    public String replace(String s, char oldChar, char newChar) {
      return s.replace(oldChar, newChar);
    }

    //@ requires(*target - The sequence of char values to be replaced*);
    //@ requires(*replacement - The replacement sequence of char values*);
    //@ ensures(*Returns The resulting string*);
    public String replace(String s, CharSequence target, CharSequence replacement) {
      return s.replace(oldString, newString);
    }

    //@ requires(*regex - the regular expression to which this string is to be matched*);
    //@ ensures(*Returns true if, and only if, this string matches the given regular expression.*);
    //@ signals(PatternSyntaxException e)(*if the regular expression's syntax is invalid*);
    public boolean matches(String s, String regex) {
      return s.matches(regex);
    }

    //@ requires(*regex - the regular expression to which this string is to be matched*);
    //@ requires(*replacement - the string to be substituted for the first match*);
    //@ ensures(*Returns The resulting string*);
    //@ signals(PatternSyntaxException e)(*if the regular expression's syntax is invalid*);
    public String replaceFirst(String s, String regex, String replacement) {
      return s.replaceFirst(regex, replacement);
    }

    //@ requires(*regex - the regular expression to which this string is to be matched*);
    //@ requires(*replacement - the string to be substituted for the first match*);
    //@ ensures(*Returns The resulting string*);
    //@ signals(PatternSyntaxException e)(*if the regular expression's syntax is invalid*);
    public String replaceAll(String s, String regex, String replacement) {
      return s.replaceAll(regex, replacement);
    }

    //@ requires(*regex - the delimiting regular expression*);
    //@ requires(*limit - the result threshold, as described above*);
    //@ ensures(*Returns the array of strings computed by splitting this string around matches of the given regular expression*);
    //@ signals(PatternSyntaxException e)(*if the regular expression's syntax is invalid*);
    public String[] split(String s, String regex, int limit) {
      return s.split(regex, limit);
    }

    //@ requires(*regex - the delimiting regular expression*);
    //@ ensures(*Returns the array of strings computed by splitting this string around matches of the given regular expression*);
    //@ signals(PatternSyntaxException e)(*if the regular expression's syntax is invalid*);
    public String[] split(String s, String regex) {
      return s.split(regex);
    }

    //@ requires(*locale - use the case transformation rules for this locale*);
    //@ ensures(*Returns the String, converted to lowercase.*);
    public String toLowerCase(String s, Locale locale) {
      return s.toLowerCase(locale);
    }

    //@ ensures(*Returns the String, converted to lowercase.*);
    public String toLowerCase(String s) {
      return s.toLowerCase();
    }

    //@ requires(*locale - use the case transformation rules for this locale*);
    //@ ensures(*Returns the String, converted to uppercase.*);
    public String toUpperCase(String s, Locale locale) {
      return s.toUpperCase(locale);
    }

    //@ ensures(*Returns the String, converted to uppercase.*);
    public String toUpperCase(String s) {
      return s.toUpperCase();
    }

    // Note: this method cannot be verified initially
    //@ ensures(*Returns A string whose value is this string, with any leading and trailing white space removed, or this string if it has no leading or trailing white space.*);
    public String trim(String s) {
      return s.trim();
    }

    //@ ensures(*Returns the string itself.*);
    public String toString(String s) {
      return s.toString();
    }
    
    //@ ensures(*Returns a newly allocated character array whose length is the length of this string and whose contents are initialized to contain the character sequence represented by this string. *);
    public char[] toCharArray(String s) {
      return s.toCharArray();
    }

    // Note: this method cannot be verified initially
    //@ requires(*obj - an Object.*);
    //@ ensures(*Returns if the argument is null, then a string equal to "null"; otherwise, the value of obj.toString() is returned.*);
    public static String valueOf(String s, Object obj) {
      return s.valueOf(obj);
    }

    // Note: this method cannot be verified initially
    //@ requires(*data - the character array.*);
    //@ ensures(*Returns a String that contains the characters of the character array.*);
    public static String valueOf(String s, char[] data) {
      return s.valueOf(data);
    }

    // Note: this method cannot be verified initially
    //@ requires(*data - the character array.*);
    //@ requires(*offset - initial offset of the subarray.*);
    //@ requires(*count - length of the subarray.*);
    //@ ensures(*Returns a String that contains the characters of the specified subarray of the character array.*);
    //@ signals(IndexOutOfBoundsException e)(*if offset is negative, or count is negative, or offset+count is larger than data.length.*);
    public static String valueOf(String s, char[] data, int offset, int count) {
      return s.valueOf(data, offset, count);
    }

    // Note: this method cannot be verified initially
    //@ requires(*data - the character array.*);
    //@ requires(*offset - initial offset of the subarray.*);
    //@ requires(*count - length of the subarray.*);
    //@ ensures(*Returns a String that contains the characters of the specified subarray of the character array.*);
    //@ signals(IndexOutOfBoundsException e)(*if offset is negative, or count is negative, or offset+count is larger than data.length.*);
    public static String copyValueOf(String s, char[] data, int offset, int count) {
      return s.copyValueOf(data, offset, count);
    }

    //@ requires(*data - the character array.*);
    //@ ensures(*Returns a String that contains the characters of the specified subarray of the character array.*);
    public static String copyValueOf(String s, char[] data) {
      return s.copyValueOf(data);
    }
        

    //@ requires(*c - a char.*);
    //@ ensures(*Returns a string of length 1 containing as its single character the argument c.*);
    public static  String valueOf(String s, char c) {
      return s.valueOf(c);
    }

    //Note: native method cannot have a body. this is point that we need to explain in the experiment setup
    //@ ensures(*Returns a string that has the same contents as this string, but is guaranteed to be from a pool of unique strings.*);
    public String intern(String s) {
      return s.intern();
    }

    //@ requires(*format - A format string*);
    //@ requires(*args - Arguments referenced by the format specifiers in the format string. If there are more arguments than format specifiers, the extra arguments are ignored. The number of arguments is variable and may be zero. The maximum number of arguments is limited by the maximum dimension of a Java array as defined by The Java™ Virtual Machine Specification. The behaviour on a null argument depends on the conversion.*);
    //@ ensures(*Returns A formatted string*);
    //@ signals(IllegalFormatException e)(*If a format string contains an illegal syntax, a format specifier that is incompatible with the given arguments, insufficient arguments given the format string, or other illegal conditions. For specification of all possible formatting errors, see the Details section of the formatter class specification.*);
    public static String format(String s, String format, Object ... args) {
      return s.format(format, args);
    }

}
