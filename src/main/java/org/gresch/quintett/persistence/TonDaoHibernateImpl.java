package org.gresch.quintett.persistence;

import javax.annotation.Resource;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("tonDao")
public class TonDaoHibernateImpl extends com.trg.dao.dao.standard.GenericDAOImpl<Ton, Integer> implements TonDao
{
  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  public TonDaoHibernateImpl()
  {
    // Beany baby
  }

  @Override
  public void makePersistentReadOnly(Ton ton)
  {
    // FIXME
    sessionFactory.getCurrentSession().saveOrUpdate(ton);
    sessionFactory.getCurrentSession().setReadOnly(ton, true);
  }
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass
  // spezielle Nicht-CRUD-Methoden nötig werden.

  @Override
  public Ton findByExample(Ton xTon)
  {
    return (Ton) sessionFactory.getCurrentSession().get(Ton.class, xTon.getAbstandZumEingestrichenenC());
  }
}
