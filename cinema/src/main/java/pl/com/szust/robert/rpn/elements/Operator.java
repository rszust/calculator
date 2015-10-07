package pl.com.szust.robert.rpn.elements;

import pl.com.szust.robert.rpn.Associativity;
import pl.com.szust.robert.rpn.Stackable;

public enum Operator implements Stackable {

  ADDITION(1, Associativity.LEFT, "+"),
  SUBSTRACTION(1, Associativity.LEFT, "-"), 
  DIVISION(2, Associativity.LEFT, "/"), 
  MULTIPLICATION(2, Associativity.LEFT, "*"),
  UNKNOWN(0, Associativity.NONE, "");
  
  private int precendence;
  private String operator;
  private Associativity associativity;

  private Operator(int precendence, Associativity associativy, String operator) {
    this.precendence = precendence;
    this.associativity = associativy;
    this.operator = operator;
  }

  @Override
  public int getPriority() {
    return precendence;
  }

  public Associativity getAssociativity() {
    return associativity;
  }
  
  @Override
  public String toString() {
    return this.operator;
  }

  public static Operator fromString(String c) {
    switch (c) {
      case "+":
        return ADDITION;
      case "-":
        return SUBSTRACTION;
      case "*":
        return MULTIPLICATION;
      case "/":
        return DIVISION;
    }
    return null;
  }
}
