package org.gresch.quintett.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Nach "Java Persistence with Hibernate", Greenwich (Connecticut), 2007.
 * 
 * 
 * @author Karsten
 *
 * @param <T> The generic type.
 * @param <Id> The database instance identifier.
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
