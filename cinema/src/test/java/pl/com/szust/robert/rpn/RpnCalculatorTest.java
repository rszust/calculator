package pl.com.szust.robert.rpn;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RpnCalculatorTest {

  @Parameters
  public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {     
               { "3 4 +", 7 }, {"3 4 -",-1}, {"-4 -5 +",-9},
               { "3 5 *", 15}, {"3 -5 *",-15}, {"-3 -5 *", 15},
               { "4 3 /", 1.33333}, {"4 4 /", 1}, {"-4 4 /", -1},
               {"2 sqr", 4}, { "4 sqr", 16}, { "3 1 + sqr", 16},
               { " 4 sqrt", 2} ,{"-3 -3 * sqrt", 3}
         });
  }
  
  private String rpnExpression;
  private double expected;
  
  public RpnCalculatorTest(String rpnExpression, double expected) {
    super();
    this.rpnExpression = rpnExpression;
    this.expected = expected;
  }
  
  @Test
  public void test() throws Exception {
    //given
    //when
    double result = new RpnCalculator(rpnExpression).calculate();
    //then
    assertEquals(expected, result, 0.0001);
  }
  
  
}
