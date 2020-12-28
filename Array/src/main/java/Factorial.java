import java.sql.SQLOutput;

public class Factorial {
  public static int facCalc(int input){
    if(input == 1){
      return 1;
    }
    return input*facCalc(input-1);
  }

  //Write a recursive function that takes in a number (x) and an
  // exponent (n) and returns the result of xn
  public static int calcPower(int input, int power){
    if(power ==0){
      return 1;//bug return 1
    }
    return input*calcPower(input,power-1);
  }


  //Write a recursive function isPalindrome accepts a string
  //and returns true if it reads the same forwards as backwards.
  public static boolean isPalindrom(String s){
    if(s.length()<2){
      return true;
    }
    else{
      if(s.charAt(s.length() - 1) != s.charAt(0))
        return false;
    }
    String middle = s.substring(1,s.length()-2);
    return isPalindrom(middle);
  }

  public static void main(String[] args) {
    System.out.println(facCalc(3));

    System.out.println(calcPower(2,3));

    System.out.println(isPalindrom("aoaoa"));


  }
}
