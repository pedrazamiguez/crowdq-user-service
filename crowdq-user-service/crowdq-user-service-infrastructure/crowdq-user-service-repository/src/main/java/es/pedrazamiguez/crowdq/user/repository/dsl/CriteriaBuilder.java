package es.pedrazamiguez.crowdq.user.repository.dsl;

import java.util.List;
import org.springframework.data.relational.core.query.Criteria;

public final class CriteriaBuilder {
  public Criteria build(final List<FilterCondition> conditions) {
    Criteria criteria = Criteria.empty();

    for (FilterCondition cond : conditions) {
      Criteria c =
          switch (cond.operator()) {
            case EQUAL -> Criteria.where(cond.field()).is(cond.value());
            case NOT_EQUAL -> Criteria.where(cond.field()).not(cond.value());
            case GREATER_THAN -> Criteria.where(cond.field()).greaterThan(cond.value());
            case LESS_THAN -> Criteria.where(cond.field()).lessThan(cond.value());
            case GREATER_OR_EQUAL -> Criteria.where(cond.field()).greaterThanOrEquals(cond.value());
            case LESS_OR_EQUAL -> Criteria.where(cond.field()).lessThanOrEquals(cond.value());
            case LIKE -> Criteria.where(cond.field()).like(cond.value());
          };
      criteria = criteria.and(c);
    }

    return criteria;
  }
}
