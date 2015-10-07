package pl.com.szust.robert.rpn;

import java.util.Stack;

import org.apache.commons.lang3.math.NumberUtils;

import pl.com.szust.robert.rpn.elements.Bracket;
import pl.com.szust.robert.rpn.elements.Operator;
import pl.com.szust.robert.rpn.elements.UnaryFunction;
import pl.com.szust.robert.rpn.exceptions.UnsupportedInfixExpressionElementException;
 
public class InfixExpressionParser {

  private final Stack<Stackable> stack;
  private final StringBuilder output;
  
  public InfixExpressionParser() {
    stack  = new Stack<Stackable>();
    output = new StringBuilder();
  }
  
  public String rpn(String expression) throws UnsupportedInfixExpressionElementException {
    Operator op;
    Bracket br;
    UnaryFunction uf;
    for (String c : expression.trim().toLowerCase().split("\\s")) {
      if (NumberUtils.isNumber(c)) {
        appendToOutput(c);
      } else if ((op = Operator.fromString(c)) != null) {
        handleOperator(op);
      } else if ((br = Bracket.fromString(c)) != null) {
        handleBracket(br);
      } else if ( (uf = UnaryFunction.fromString(c)) != null ) {
        handleUnaryFunction(uf);
      } else {
        throw new UnsupportedInfixExpressionElementException(expression, c);
      }
    }
    handleRemaingElements();
    return output.toString();
  }

  private void handleRemaingElements() {
    while (!stack.isEmpty()) {
      appendStackToOutput();
    }
  }

  private void handleUnaryFunction(UnaryFunction uf) {
    if (uf != null) {
      stack.push(uf);
    }
  }

  private void handleOperator(Operator op) {
    if (stack.isEmpty() || isStackOperatorPriorityLower(op)) {
      stack.push(op);
    } else {
      while (!stack.isEmpty() && !isStackOperatorPriorityLower(op)) {
        appendStackToOutput();
      }
      stack.push(op);
    }
  }

  private void handleBracket(Bracket br) {
    switch (br) {
      case LEFT_CURLY:
      case LEFT_ROUND:
      case LEFT_SQUARE:
        handleLeftBracket(br);
        break;
      case RIGHT_CURLY:
        handleRightCurlyBracket(br);
        break;
      case RIGHT_ROUND:
        handleRightRoundBracket(br);
        break;
      case RIGHT_SQUARE:
        handleRightSquareBracket(br);
        break;
    }
  }

  private boolean isStackOperatorPriorityLower(Operator op) {
    return op.getPriority() > stack.peek().getPriority();
  }
  
  protected void handleRightRoundBracket(Bracket br) {
    handleRightBracket(br, Bracket.LEFT_ROUND, Bracket.RIGHT_ROUND);
  }
  
  protected void handleRightSquareBracket(Bracket br) {
    handleRightBracket(br, Bracket.LEFT_SQUARE, Bracket.RIGHT_SQUARE);
  }
  
  protected void handleRightCurlyBracket(Bracket br) {
    handleRightBracket(br, Bracket.LEFT_CURLY, Bracket.RIGHT_CURLY);
  }
  
  protected void handleLeftBracket(Bracket br) {
    stack.push(br);
  }

  private void handleRightBracket(Bracket br, Bracket left, Bracket right) {
    if (br == right) {
      while (!stack.isEmpty() && stack.peek() != left) {
        output.append(stack.pop());
        output.append(Token.WHITESPACE);
      }
      if (!stack.isEmpty() && stack.peek() == left) {
        stack.pop();
      }
    }
  }
  
  private void appendToOutput(String c) {
    output.append(c);
    output.append(Token.WHITESPACE);
  }
  
  private void appendStackToOutput() {
    output.append(stack.pop());
    output.append(Token.WHITESPACE);
  }

}
