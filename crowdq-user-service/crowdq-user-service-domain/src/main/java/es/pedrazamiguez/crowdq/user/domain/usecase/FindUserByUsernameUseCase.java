package es.pedrazamiguez.crowdq.user.domain.usecase;

import es.pedrazamiguez.crowdq.user.domain.entity.User;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface FindUserByUsernameUseCase {

  Mono<User> execute(String username);
}
