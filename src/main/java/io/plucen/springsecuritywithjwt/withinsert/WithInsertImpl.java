package io.plucen.springsecuritywithjwt.withinsert;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

@RequiredArgsConstructor
public class WithInsertImpl<T> implements WithInsert<T> {

  private final JdbcAggregateTemplate jdbcAggregateTemplate;

  @Override
  public T insert(T entry) {
    return jdbcAggregateTemplate.insert(entry);
  }
}
