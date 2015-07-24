package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface TonDao<T, ID extends Serializable> extends CrudRepository<Ton, Integer> {
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass spezielle Nicht-CRUD-Methoden nötig werden.
  default void makePersistentReadOnly(Ton ton, EntityManager entityManager) {
    SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(ton);
    session.setReadOnly(ton, true);
    transaction.commit();
  }

  Ton findByExample(Ton ton);

  // T findOne(ID id);
}
