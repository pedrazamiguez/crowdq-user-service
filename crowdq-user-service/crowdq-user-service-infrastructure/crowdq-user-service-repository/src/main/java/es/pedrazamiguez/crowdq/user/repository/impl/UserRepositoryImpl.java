package es.pedrazamiguez.crowdq.user.repository.impl;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import es.pedrazamiguez.crowdq.user.domain.repository.UserRepository;
import es.pedrazamiguez.crowdq.user.repository.mapper.UserEntityMapper;
import es.pedrazamiguez.crowdq.user.repository.r2dbc.UserReactiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserReactiveRepository userReactiveRepository;

  private final UserEntityMapper userEntityMapper;

  @Override
  public Mono<User> findByUsername(String username) {
    log.info("Finding user by username: {}", username);
    return userReactiveRepository
        .findByUsername(username)
        .map(userEntityMapper::toDomain)
        .switchIfEmpty(
            Mono.fromRunnable(() -> log.warn("User not found for username: {}", username)))
        .doOnError(error -> log.error("Error finding user by username: {}", username, error));
  }
}
