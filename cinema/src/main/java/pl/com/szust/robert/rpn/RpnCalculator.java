package pl.com.szust.robert.rpn;

import java.util.Stack;
import java.util.logging.Logger;

import pl.com.szust.robert.rpn.exceptions.RpnInvalidExpressionException;
import pl.com.szust.robert.rpn.exceptions.UnsupportedRpnExpressionElementExcepion;

public class RpnCalculator {
  private static final Logger logger = Logger.getLogger(RpnCalculator.class.getName());
  private final Stack<Double> stack;
  private final String expression;
  
  public RpnCalculator(String expression) {
    stack = new Stack<>();
    this.expression = expression;
  }
  
  public double calculate () throws UnsupportedRpnExpressionElementExcepion, RpnInvalidExpressionException {
    logger.info("Calculating expression: " + expression);
    double result = 0;
    for (String token:expression.trim().toLowerCase().split(Token.WHITESPACE.toString())) {
      switch (token) {
        case "+":
          stack.push(stack.pop() + stack.pop());
          break;
        case "-":
          stack.push(-stack.pop() + stack.pop());
          break;
        case "*":
          stack.push(stack.pop() * stack.pop());
          break;
        case "/":
          stack.push( ( 1 / stack.pop()) * stack.pop()  );
          break;
        case "sqr":
          stack.push(Math.pow(stack.pop(), 2.0));
          break;
        case "sqrt":
          stack.push(Math.pow(stack.pop(), 0.5));
          break;
        default:
          if (token.matches("\\-?[0-9]*")) {
            //System.out.println(token);
            stack.add(Double.valueOf(token));
          } else {
            throw new UnsupportedRpnExpressionElementExcepion(expression, token);
          }
      }
    }
    if (stack.size() == 1) {
      result  = stack.pop();
    } else {
      throw new RpnInvalidExpressionException();
    }
    return result;
  }

}
