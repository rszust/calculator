package pl.com.szust.robert.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.com.szust.robert.mvc.model.CalculateRequest;
import pl.com.szust.robert.mvc.model.CalculateResponse;
import pl.com.szust.robert.rpn.InfixExpressionParser;
import pl.com.szust.robert.rpn.RpnCalculator;
import pl.com.szust.robert.rpn.exceptions.RpnInvalidExpressionException;
import pl.com.szust.robert.rpn.exceptions.UnsupportedExpressionElementException;

@Controller
public class CalculatorRestController {
  @ResponseBody
  @RequestMapping(value = "/calculator", method = RequestMethod.POST)
  public CalculateResponse calculate(@RequestBody CalculateRequest request) throws UnsupportedExpressionElementException, RpnInvalidExpressionException {
    InfixExpressionParser parser = new InfixExpressionParser();
    String rpnExpression = parser.rpn(request.getExpression());
    RpnCalculator calculator = new RpnCalculator(rpnExpression);
    CalculateResponse response = new CalculateResponse();
    response.setResponse(calculator.calculate());
    response.setExpression(request.getExpression());
    response.setPrecision(request.getPrecision());
    response.setTimestamp(DateTime.now().toString());
    return response;
  }
  
  @RequestMapping(value = "/calculator", method = RequestMethod.GET)
  public String displayForm(HttpServletRequest request) {
    return "index";
  }
}
