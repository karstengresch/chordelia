package org.gresch.quintett.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Nach "Java Persistence with Hibernate", Greenwich (Connecticut), 2007.
 *
 * @param <T>  The generic type.
 * @param <Id> The database instance identifier.
 * @author Karsten
 */
public interface GenerischesDao<T, Id extends Serializable> {
  T findById(Id xId, boolean xLock);

  List<T> findAll();

  List<T> findByExample(T xInstanceExample, String... xExcludeProperty);

  T makePersistentReadOnly(T xEntity);

  void makeTransient(T xEntity);

  void flush();

  void clear();
}
