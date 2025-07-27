package es.pedrazamiguez.crowdq.user.controller;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import es.pedrazamiguez.crowdq.user.domain.usecase.FindUserByUsernameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

  private final FindUserByUsernameUseCase findUserByUsernameUseCase;

  public UserController(FindUserByUsernameUseCase findUserByUsernameUseCase) {
    this.findUserByUsernameUseCase = findUserByUsernameUseCase;
  }

  @GetMapping("/{username}")
  public Mono<ResponseEntity<User>> findByUsername(@PathVariable String username) {
    return findUserByUsernameUseCase
        .execute(username)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
}
