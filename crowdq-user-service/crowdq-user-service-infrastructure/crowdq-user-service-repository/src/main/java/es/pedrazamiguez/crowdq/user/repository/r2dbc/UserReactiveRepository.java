package es.pedrazamiguez.crowdq.user.repository.r2dbc;

import es.pedrazamiguez.crowdq.user.repository.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends ReactiveCrudRepository<UserEntity, UUID> {

  Mono<UserEntity> findByUsername(String username);
}
