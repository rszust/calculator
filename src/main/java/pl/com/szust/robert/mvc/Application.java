package pl.com.szust.robert.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author rszus_000
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
  /**
   * Spring boot application entry point.
   * @param args
   */
  public static void main(String...args) {
    SpringApplication.run(new Object[]{ Application.class }, args);
  }
  
  @Override
  protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }
}
