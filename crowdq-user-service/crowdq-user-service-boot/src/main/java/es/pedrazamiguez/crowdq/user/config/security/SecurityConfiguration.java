package es.pedrazamiguez.crowdq.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  private final ReactiveJwtAuthConverter reactiveJwtAuthConverter;

  public SecurityConfiguration(final ReactiveJwtAuthConverter reactiveJwtAuthConverter) {
    this.reactiveJwtAuthConverter = reactiveJwtAuthConverter;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
    return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(
            exchanges ->
                exchanges

                    // Actuator endpoints
                    .pathMatchers("/actuator/**")
                    .permitAll()

                    // Index page
                    .pathMatchers(HttpMethod.GET, "/")
                    .permitAll()

                    // Swagger UI and OpenAPI documentation
                    .pathMatchers(
                        "/v3/api-docs/**", "/swagger-ui/**", "/crowdq-user-service-api-spec.yml")
                    .permitAll()

                    // All other requests
                    .anyExchange()
                    .authenticated())
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(this.reactiveJwtAuthConverter)))
        .build();
  }
}
