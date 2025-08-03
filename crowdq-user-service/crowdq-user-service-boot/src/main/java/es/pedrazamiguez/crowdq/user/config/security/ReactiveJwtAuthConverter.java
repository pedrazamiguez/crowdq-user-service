package es.pedrazamiguez.crowdq.user.config.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveJwtAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

  private final ReactiveJwtAuthenticationConverterAdapter delegate;

  public ReactiveJwtAuthConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
    this.delegate = new ReactiveJwtAuthenticationConverterAdapter(converter);
  }

  @Override
  public Mono<AbstractAuthenticationToken> convert(final Jwt source) {
    return delegate.convert(source);
  }

  private Collection<GrantedAuthority> extractAuthorities(final Jwt jwt) {
    Map<String, Object> realmAccess = jwt.getClaim("realm_access");
    if (realmAccess == null || !(realmAccess.get("roles") instanceof Collection<?> roles)) {
      return Collections.emptyList();
    }

    return roles.stream()
        .filter(Objects::nonNull)
        .map(Object::toString)
        .map(role -> "ROLE_" + role.toUpperCase())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}
