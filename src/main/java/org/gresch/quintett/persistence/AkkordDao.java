package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Akkord;
import org.hibernate.*;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface AkkordDao<T, ID extends Serializable> extends CrudRepository<Akkord, Integer> {

  default void makePersistentReadOnly(Akkord akkord, EntityManager entityManager) {
    SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(akkord);
    session.setReadOnly(akkord, true);
    transaction.commit();
  }


  default Set<Akkord> getAkkordkombinationenZuBasisAkkord(Akkord basisAkkord, EntityManager entityManager) {
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
  default ScrollableResults getScrollableResultByBasisAkkordRange(Integer minBlockId, Integer maxBlockId, int fetchBlockGroesze, boolean absteigend, EntityManager entityManager) {
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

  default List<Integer> getAkkordIdsByAnzahlToene(int anzahlToene, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend, EntityManager entityManager) {
    String klangschaerfeAbsteigendString = klangschaerfeAbsteigend ? "DESC" : "ASC";
    String akkordIdAbsteigendString = akkordIdAbsteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString = "SELECT akkord.id FROM Akkord akkord ORDER BY akkord.anzahlToene, akkord.klangschaerfe " + akkordIdAbsteigendString + ", akkord.id "
      + klangschaerfeAbsteigendString;
    Query akkordResultQuery = entityManager.unwrap(SessionFactory.class).getCurrentSession().createQuery(queryString);
    akkordResultQuery.setReadOnly(true);
    @SuppressWarnings("unchecked")
    List<Integer> resultList = akkordResultQuery.list();
    return resultList;
  }


  default List<Integer> getAkkordIdsByRange(int minId, int maxId, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend, EntityManager entityManager) {
    String steigendString = klangschaerfeAbsteigend ? "DESC" : "ASC";
    String akkordIdAbsteigendString = akkordIdAbsteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString =
      "SELECT akkord.id FROM Akkord akkord WHERE akkord.id between " + minId + " and " + maxId + " ORDER BY akkord.anzahlToene, akkord.klangschaerfe "
        + steigendString + ", akkord.id " + akkordIdAbsteigendString;
    Query akkordResultQuery = entityManager.unwrap(SessionFactory.class).getCurrentSession().createQuery(queryString);
    akkordResultQuery.setReadOnly(true);
    @SuppressWarnings("unchecked")
    List<Integer> resultList = akkordResultQuery.list();
    return resultList;
  }


}