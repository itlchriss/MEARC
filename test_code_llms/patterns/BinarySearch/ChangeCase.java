public class ChangeCase {

  //@ requires(*The character parameter c is less than or equal to 'Z' and is greater than or equal to 'A'.*);
  //@ ensures(*The result is less than or equal to 'z' and is greater than or equal to 'a'.*);
  //@ also
  //@ requires(*The character parameter c is less than or equal to 'z' and is greater than or equal to 'a'.*);
  //@ ensures(*The result is less than or equal to 'Z' and is greater than or equal to 'A'.*);
  //@ also
  //@ requires(*The character parameter c is not less than or equal to 'Z' and is not greater than or equal to 'A' and is not less than or equal to 'z' and is not greater than or equal to 'a'.*);
  //@ ensures(*The character result is equal to the character parameter c.*);
  public char changeCase(char c) {
    char result = ' ';    
    if (c > 'z') {
      result = c;
    } else if (c >= 'a') {
      result =  (char)(c - 'a' + 'A');
    } else if (c > 'Z') {
      result =  c;
    } else if (c >= 'A') {
      result =  (char)(c - 'A' + 'a');
    } else {
      result = c;
    }
    return result;
  }

}


