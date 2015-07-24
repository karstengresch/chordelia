package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Akkord;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface AkkordDao<T, ID extends Serializable> extends CrudRepository<Akkord, Integer> {

  default void makePersistentReadOnly(Akkord akkord, EntityManager entityManager)  {
    SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(akkord);
    session.setReadOnly(akkord, true);
    transaction.commit();
  }


  default Set<Akkord> getAkkordkombinationenZuBasisAkkord(Akkord basisAkkord, EntityManager entityManager)    {
    Criteria criteria = entityManager.unwrap(SessionFactory.class).getCurrentSession().createCriteria(Akkord.class);

  criteria.createAlias("eq", "basisAkkordId");
  @SuppressWarnings("unchecked")
  List<Akkord> akkordList = criteria.list();
  Set<Akkord> akkordSet = Collections.synchronizedSortedSet(new TreeSet<Akkord>());
  for (Akkord akkord : akkordList) {
    akkordSet.add(akkord);
  }
  return akkordSet;
  }

  // Gibt die aktuellen (Basis-)akkorde zum Iterieren zurück.
  default ScrollableResults getScrollableResultByBasisAkkordRange(Integer minBlockId, Integer maxBlockId, int fetchBlockGroesze, boolean absteigend, EntityManager entityManager)
  {
    String steigendString = absteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString = "FROM Akkord akkord WHERE akkord.id between " + minBlockId + " and " + maxBlockId + " ORDER BY akkord.anzahlToene, akkord.klangschaerfe "
      + steigendString + ", akkord.id " + steigendString;
    Query akkordResultQuery = entityManager.unwrap(SessionFactory.class).getCurrentSession().createQuery(queryString);
    //     akkordResultQuery.setFetchSize(fetchBlockGroesze);
    akkordResultQuery.setReadOnly(true);
    //   akkordResultQuery.setCacheMode(CacheMode.IGNORE);
    akkordResultQuery.setComment("Scrollable results by basis akkord range");
    ScrollableResults akkordCursor = akkordResultQuery.scroll();
    return akkordCursor;
  }

  @org.springframework.data.jpa.repository.Query(value = "SELECT akkord.id FROM Akkord akkord ORDER BY akkord.anzahlToene, akkord.klangschaerfe, :anzahlToene, :klangschaerfeAbsteigend, :akkordIdAbsteigend")
  List<Integer> getAkkordIdsByAnzahlToene(@Param(value = "anzahlToene") int anzahlToene, @Param(value = "klangschaerfeAbsteigend") boolean klangschaerfeAbsteigend, @Param(value = "akkordIdAbsteigend") boolean akkordIdAbsteigend);

  List<Integer> getAkkordIdsByRange(int minId, int maxId, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend);

  //  T findOne(ID id);
}
