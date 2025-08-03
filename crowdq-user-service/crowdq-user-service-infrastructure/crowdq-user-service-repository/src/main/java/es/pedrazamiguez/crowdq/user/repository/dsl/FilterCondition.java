package es.pedrazamiguez.crowdq.user.repository.dsl;

public record FilterCondition(
    String field,
    es.pedrazamiguez.crowdq.user.repository.dsl.FilterCondition.Operator operator,
    String value) {
  public enum Operator {
    EQUAL,
    NOT_EQUAL,
    GREATER_THAN,
    LESS_THAN,
    GREATER_OR_EQUAL,
    LESS_OR_EQUAL,
    LIKE
  }
}
