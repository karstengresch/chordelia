package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository("tonDao")
public class TonDaoHibernateImpl {
  @PersistenceContext
  EntityManager entityManager;

  public TonDaoHibernateImpl() {
    // Beany baby
  }

  @Transactional
  public void makePersistentReadOnly(Ton ton) {
    SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(ton);
    session.setReadOnly(ton, true);
    transaction.commit();
  }
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass
  // spezielle Nicht-CRUD-Methoden nötig werden.

  public Ton findByExample(Ton xTon) {
    return (Ton) entityManager.unwrap(SessionFactory.class).getCurrentSession().get(Ton.class, xTon.getAbstandZumEingestrichenenC());
  }
}
