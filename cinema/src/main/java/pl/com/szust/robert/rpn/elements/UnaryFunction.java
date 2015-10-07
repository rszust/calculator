package pl.com.szust.robert.rpn.elements;

import pl.com.szust.robert.rpn.Stackable;

public enum UnaryFunction implements Stackable {
  SQR, SQRT;

  @Override
  public int getPriority() {
    return 4;
  }
  
  public static UnaryFunction fromString(String s) {
    switch (s.toLowerCase()) {
      case "sqr":
        return SQR;
      case "sqrt":
        return SQRT;
      default:
        return null;
    }
  }
  
}
