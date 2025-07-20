package es.pedrazamiguez.crowdq.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

  private final ReactiveJwtAuthConverter reactiveJwtAuthConverter;

  public SecurityConfiguration(final ReactiveJwtAuthConverter reactiveJwtAuthConverter) {
    this.reactiveJwtAuthConverter = reactiveJwtAuthConverter;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(
            exchanges ->
                exchanges.pathMatchers("/actuator/**").permitAll().anyExchange().authenticated())
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(this.reactiveJwtAuthConverter)))
        .build();
  }
}
