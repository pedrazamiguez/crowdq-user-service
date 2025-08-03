package es.pedrazamiguez.crowdq.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class CrowdQUserServiceApplication {

  private CrowdQUserServiceApplication() {
    // Prevent instantiation
  }

  public static void main(final String[] args) {
    SpringApplication.run(CrowdQUserServiceApplication.class, args);
  }
}
