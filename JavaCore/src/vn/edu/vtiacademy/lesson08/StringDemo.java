package vn.edu.vtiacademy.lesson08;

public class StringDemo {

  public static void main(String[] args) {
    String s = new String("hello"); //llo => indexOf = 2, lastIndexOf = 7
    System.out.println(s);
    String s1 = "java";
    System.out.println(s1);

    int lengthOfS1 = s1.length();
    System.out.println("Length of s1: " + lengthOfS1);
    System.out.println("Length of s1: " + calculateLengthOf(s1));

    String s2 = s.concat(s1);
    System.out.println("s concat s1 = " + s2);
    System.out.println("s concat s1 = " + merge(s, s1));
    System.out.println("s = " + s);

    System.out.println("Character at index 3 is '" + s.charAt(3) + "' of " + s);
    System.out.println("Character at index 3 is '" + getCharacterOfIndex(s, 3) + "' of " + s);

    System.out.println(s1 + ".equals(\"Java\") = " + s1.equals("Java"));
    System.out.println("isEqual(s1, \"Java\") = " + isEqual(s1, "Java"));
    System.out.println("isEqual(s, s1) = " + isEqual(s, s1));

    System.out.println("compareLength(s, s1) = " + compareLength(s, s1));

    System.out.println("compareLength(s, s1) = " + (s.length() - s1.length()));

    System.out.println("compareStrings(\"abc\", \"abc\") = " + compareStrings("abc", "abc"));
    System.out.println("compareStrings(\"abc\", \"def\") = " + compareStrings("abc", "def"));

    System.out.println("compareStringLengths(\"abc\", \"def\") = " + compareStringLengths("abc", "def"));
    System.out.println("compareStringLengths(\"abcd\", \"abc\") = " + compareStringLengths("abcd", "abc"));
  }

  public static int calculateLengthOf(String s) {
    return s.length();
  }

  public static int lengthOf(String s) {
    return s.length();
  }

  public static String merge(String s1, String s2) {
    return s1.concat(s2);
  }

  public static char getCharacterOfIndex(String s, int index) {
    return s.charAt(index);
  }

  public static boolean isEqual(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }

    for (int i = 0; i < s1.length(); i++) {
      char c1 = s1.charAt(i);
      char c2 = s2.charAt(i);

      if (c1 != c2) {
        return false;
      }
    }

    return true;
  }

  public static int compareLength(String s1, String s2) {
    return s1.compareTo(s2);
  }

  public static boolean compareStrings(String s1, String s2) {
    return s1.equals(s2);
  }

  public static int compareStringLengths(String s1, String s2) {
    return Integer.compare(s1.length(), s2.length());
  }

}