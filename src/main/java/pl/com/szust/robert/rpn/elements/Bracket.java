package pl.com.szust.robert.rpn.elements;

import pl.com.szust.robert.rpn.Stackable;

public enum Bracket implements Stackable {
  LEFT_ROUND("("),
  RIGHT_ROUND(")"),
  LEFT_SQUARE("["),
  RIGHT_SQUARE("]"),
  LEFT_CURLY("{"),
  RIGHT_CURLY("}");
  private String expression;
  
  private Bracket(String expression) {
    
  }
  
  @Override
  public int getPriority() {
    return 0;
  }
  
  public static Bracket fromString(String c) {
    switch (c) {
      case "(":
        return LEFT_ROUND;
      case ")":
        return RIGHT_ROUND;
      case "[":
        return LEFT_SQUARE;
      case "]":
        return RIGHT_SQUARE;
      case "{":
        return LEFT_CURLY;
      case "}":
        return RIGHT_CURLY;
    }
    return null;
  }
  
}