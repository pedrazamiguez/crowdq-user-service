package es.pedrazamiguez.crowdq.user.apirest.controller;

import es.pedrazamiguez.crowdq.user.apirest.mapper.UserRestMapper;
import es.pedrazamiguez.crowdq.user.domain.usecase.FindUserByUsernameUseCase;
import es.pedrazamiguez.crowdq.user.openapi.UserApi;
import es.pedrazamiguez.crowdq.user.openapi.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public final class UserController implements UserApi {

  private final FindUserByUsernameUseCase findUserByUsernameUseCase;

  private final UserRestMapper userRestMapper;

  @Override
  public Mono<ResponseEntity<UserDto>> getUserByUsername(
      final String username, final ServerWebExchange exchange) {

    return this.findUserByUsernameUseCase
        .execute(username)
        .map(this.userRestMapper::toDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
}
