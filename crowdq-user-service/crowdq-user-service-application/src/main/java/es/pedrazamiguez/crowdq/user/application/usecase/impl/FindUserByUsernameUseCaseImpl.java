package es.pedrazamiguez.crowdq.user.application.usecase.impl;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import es.pedrazamiguez.crowdq.user.domain.repository.UserRepository;
import es.pedrazamiguez.crowdq.user.domain.usecase.FindUserByUsernameUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindUserByUsernameUseCaseImpl implements FindUserByUsernameUseCase {

  private final UserRepository userRepository;

  @Override
  public Mono<User> execute(String username) {
    log.info("Finding user by username: {}", username);
    return userRepository.findByUsername(username)
        .doOnNext(user -> log.info("User found: {}", user))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("User not found for username: {}", username);
          return Mono.empty();
        }))
        .doOnError(error -> log.error("Error finding user by username: {}", username, error));
  }
}
