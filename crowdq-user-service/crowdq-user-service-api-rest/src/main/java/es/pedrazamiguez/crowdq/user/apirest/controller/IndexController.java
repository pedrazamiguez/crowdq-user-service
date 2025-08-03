package es.pedrazamiguez.crowdq.user.apirest.controller;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class IndexController {

  @GetMapping("/")
  public Mono<Void> index(final ServerWebExchange exchange) {
    log.info("Redirecting to Swagger UI");
    return Mono.fromRunnable(
            () -> {
              exchange.getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
              exchange.getResponse().getHeaders().setLocation(URI.create("/swagger-ui/index.html"));
            })
        .then();
  }
}
