package pl.com.szust.robert.rpn;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class InfixExpressionParserAndRpnCalculatorTest {
  
  @Parameters
  public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {     
               { "3 + 4", 7 }, {"3 - 4",-1}, {"-4 + -5",-9},
               { "3 * 5", 15}, {"3 * -5",-15}, {"-3 * -5", 15},
               { "4 / 3", 1.33333}, {"4 / 4", 1}, {"-4 / 4", -1},
               {"sqr ( 2 )", 4}, { "sqr ( 4 * 1 )", 16}, { "sqr ( 4 + 1 )", 25},
               { " sqrt ( 4 )", 2} ,{"sqrt ( -3 * -3 )", 3}, {"sqrt ( sqr ( 3 ) )",3},
               {"{ 3 + 4 } * [ 4 + 3 ] * { 6 + 3 }", 441},
               {" [ { 3 + 4 } * ( 4 + 3 ) ] * { 6 + 3 }", 441}
         });
  }
  
  private String infixExpression;
  private double expected;
  
  public InfixExpressionParserAndRpnCalculatorTest(String infixExpression, double expected) {
    super();
    this.infixExpression = infixExpression;
    this.expected = expected;
  }
  
  @Test
  public void test() throws Exception {
    //given
    //when
    double result = new RpnCalculator(new InfixExpressionParser().rpn(infixExpression)).calculate();
    //then
    assertEquals(expected, result, 0.0001);
  }
  
}
