package pl.com.szust.robert.rpn.exceptions;

public class UnsupportedRpnExpressionElementExcepion extends
    UnsupportedExpressionElementException {

  private static final long serialVersionUID = 1796021903405674437L;

  public UnsupportedRpnExpressionElementExcepion(String expression,
      String element) {
    super(expression, element);
  }

}
