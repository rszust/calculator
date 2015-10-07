package pl.com.szust.robert.mvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalculateRequest {
  private String expression;
  private int    threads;
  private String precision;
  
  public String getExpression() {
    return expression;
  }
  
  public void setExpression(String request) {
    this.expression = request;
  }
  
  public int getThreads() {
    return threads;
  }
  
  public void setThreads(int threads) {
    this.threads = threads;
  }
  
  public String getPrecision() {
    return precision;
  }
  
  public void setPrecision(String presision) {
    this.precision = presision;
  }
  
}
