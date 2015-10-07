package pl.com.szust.robert.rpn;

public interface Convertable<T extends Enum> {
  T fromString(String string);
}
