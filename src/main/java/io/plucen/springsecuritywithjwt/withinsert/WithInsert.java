package io.plucen.springsecuritywithjwt.withinsert;

public interface WithInsert<T> {
  T insert(T entry);
}
