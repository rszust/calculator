package pl.com.szust.robert.mvc.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement
public class CalculateResponse {
  private String expression;
  private Double response;
  private String precision;
  private String timestamp;

  public Double getResponse() {
    return response;
  }
  
  public void setResponse(Double response) {
    this.response = response;
  }
  
  public String getPrecision() {
    return precision;
  }
  
  public void setPrecision(String precision) {
    this.precision = precision;
  }
  
  public String getExpression() {
    return expression;
  }
  
  public void setExpression(String expression) {
    this.expression = expression;
  }
  
  public String getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }  
  
}
