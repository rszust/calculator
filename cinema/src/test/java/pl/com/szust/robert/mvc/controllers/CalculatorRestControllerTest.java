package pl.com.szust.robert.mvc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestContextManager;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.com.szust.robert.mvc.Application;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class CalculatorRestControllerTest {
  @Parameters
  public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {     
               { "3 + 4", 7 }, {"3 - 4",-1}, {"-4 + -5",-9},
               { "3 * 5", 15}, {"3 * -5",-15}, {"-3 * -5", 15},
               { "4 / 3", 1.33333}, {"4 / 4", 1}, {"-4 / 4", -1},
               {"sqr ( 2 )", 4}, { "sqr ( 4 * 1 )", 16}, { "sqr ( 4 + 1 )", 25},
               { " sqrt ( 4 )", 2} ,{"sqrt ( -3 * -3 )", 3}, {"sqrt ( sqr ( 3 ) )",3}
         });
  }
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final String SERVICE_URL = "http://localhost:8888/calculator";
  private RestTemplate restTemplate = new TestRestTemplate();
  private String infixExpression;
  private double expected;
  private TestContextManager testContextManager;

  @Before
  public void setUpContext() throws Exception {
    this.testContextManager = new TestContextManager(getClass());
    this.testContextManager.prepareTestInstance(this);
  }

  public CalculatorRestControllerTest(String infixExpression, double expected) {
    super();
    this.infixExpression = infixExpression;
    this.expected = expected;
  }
  
  @Test
  public void test() throws Exception {
    //GIVEN
    Map<String, Object> requestBody = new HashMap<String, Object>();
    requestBody.put("expression", infixExpression);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
    //WHEN
    Map<String, Object> calculateResponse = restTemplate.postForObject(SERVICE_URL, httpEntity, Map.class, Collections.EMPTY_MAP);
    //THEN
    assertNotNull(calculateResponse);
    assertEquals(expected, (Double) calculateResponse.get("response"), 0.00001 );
  }
  
}
