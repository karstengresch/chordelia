package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface TonDao<T, ID extends Serializable> extends CrudRepository<Ton, Integer> {
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass spezielle Nicht-CRUD-Methoden nötig werden.
  @Transactional
  default void makePersistentReadOnly(Ton ton, Session session) {
    // SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    // Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(ton);
    session.setReadOnly(ton, true);
    transaction.commit();
  }

  @Query(value = "select t from Ton t where t = :ton")
  Ton findByExample(@Param(value = "ton") Ton ton);

  // T findOne(ID id);
}
