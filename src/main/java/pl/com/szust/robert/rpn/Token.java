package pl.com.szust.robert.rpn;

public enum Token {
  WHITESPACE(" ");
  private String s;
  
  private Token(String s) {
    this.s = s;
  }
  
  @Override
  public String toString() {
    return s;
  }
}
