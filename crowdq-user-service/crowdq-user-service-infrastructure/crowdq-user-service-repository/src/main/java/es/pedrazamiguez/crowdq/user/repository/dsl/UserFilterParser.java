package es.pedrazamiguez.crowdq.user.repository.dsl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.ObjectUtils;

public final class UserFilterParser {

  public List<FilterCondition> parse(final String query) {
    if (ObjectUtils.isEmpty(query)) {
      return Collections.emptyList();
    }

    return Arrays.stream(query.split(","))
        .map(String::trim)
        .map(this::parseCondition)
        .filter(Objects::nonNull)
        .toList();
  }

  private FilterCondition parseCondition(final String token) {
    Pattern pattern =
        Pattern.compile("(?<field>\\w+)(?<op>=|:|!=|!:|>=|>:|<=|<:|>|<|~)(?<value>.+)");
    Matcher matcher = pattern.matcher(token);
    if (!matcher.matches()) {
      return null;
    }

    String field = matcher.group("field");
    String op = matcher.group("op");
    String value = matcher.group("value");

    FilterCondition.Operator operator =
        switch (op) {
          case "=", ":" -> FilterCondition.Operator.EQUAL;
          case "!=", "!:" -> FilterCondition.Operator.NOT_EQUAL;
          case ">=", ">:" -> FilterCondition.Operator.GREATER_OR_EQUAL;
          case "<=", "<:" -> FilterCondition.Operator.LESS_OR_EQUAL;
          case ">" -> FilterCondition.Operator.GREATER_THAN;
          case "<" -> FilterCondition.Operator.LESS_THAN;
          case "~" -> FilterCondition.Operator.LIKE;
          default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };

    return new FilterCondition(field, operator, value);
  }
}
