package pl.com.szust.robert.rpn.exceptions;

public class UnsupportedExpressionElementException extends Exception {
  private static final long serialVersionUID = 4600262055520949512L;
  private final String element;
  private final String expression;
  
  public UnsupportedExpressionElementException(String expression, String element) {
    this.element = element;
    this.expression = expression;
  }
  
  public String getElement() {
    return element;
  }
  
  @Override
  public String getMessage() {
    return "The " + element + " is unsupported expression element occured in " + expression + " .";
  }
}
