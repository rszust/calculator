package pl.com.szust.robert.rpn.exceptions;

public class UnsupportedInfixExpressionElementException extends UnsupportedExpressionElementException {
  private static final long serialVersionUID = -2803022443054142450L;

  public UnsupportedInfixExpressionElementException(String expression, String element) {
    super(expression, element);
  }

}
