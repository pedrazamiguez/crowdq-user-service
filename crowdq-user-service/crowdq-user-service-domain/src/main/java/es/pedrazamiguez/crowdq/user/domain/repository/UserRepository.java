package es.pedrazamiguez.crowdq.user.domain.repository;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

  Mono<User> findByUsername(String username);
}
