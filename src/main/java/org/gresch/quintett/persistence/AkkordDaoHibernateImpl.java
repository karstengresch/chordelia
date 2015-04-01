package org.gresch.quintett.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.tonmodell.Akkord;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.trg.dao.dao.standard.GenericDAOImpl;

@Repository("akkordDao")
public class AkkordDaoHibernateImpl extends GenericDAOImpl<Akkord, Integer> implements AkkordDao
{
  private Log log = LogFactory.getLog(AkkordDaoHibernateImpl.class);

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  public AkkordDaoHibernateImpl()
  {
    // Beany baby
  }

  public void makePersistentReadOnly(Akkord akkord)
  {
    // TODO Ggf. prüfen, ob FlushMode.MANUAL hier nicht besser wäre.

    try
    {
//      Session session = sessionFactory.openSession();
//      session.saveOrUpdate(akkord);
//      session.clear();
//      session.close();
//      log.info("Going to save no: " + akkord.getId());
       sessionFactory.getCurrentSession().saveOrUpdate(akkord);
    }
    catch (Exception e)
    {
      log.error("Konnte Akkord nicht persistieren. Fehler war: " + e.getLocalizedMessage());
    }
  }

  @Override
  public Set<Akkord> getAkkordkombinationenZuBasisAkkord(Akkord basisAkkord)
  {
    // TODO Null-Check etc.
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Akkord.class);
    criteria.createAlias("eq", "basisAkkordId");
    @SuppressWarnings("unchecked")
    List<Akkord> akkordList = criteria.list();
    Set<Akkord> akkordSet = Collections.synchronizedSortedSet(new TreeSet<Akkord>());
    for (Akkord akkord : akkordList)
    {
      akkordSet.add(akkord);
    }
    return akkordSet;
  }
  // XXX TODO Auf- oder absteigend : Klangschärfe und AkkordId wohl getrennt !?
  @Override
  public ScrollableResults getScrollableResultByBasisAkkordRange(Integer minBlockId, Integer maxBlockId, int fetchBlockGroesze,  boolean absteigend)
  {
    String steigendString = absteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString = "FROM Akkord akkord WHERE akkord.id between " + minBlockId + " and " + maxBlockId + " ORDER BY akkord.anzahlToene, akkord.klangschaerfe " + steigendString + ", akkord.id " + steigendString;
    Query akkordResultQuery = sessionFactory.getCurrentSession().createQuery(queryString);
    //     akkordResultQuery.setFetchSize(fetchBlockGroesze);
    akkordResultQuery.setReadOnly(true);
//   akkordResultQuery.setCacheMode(CacheMode.IGNORE);
    akkordResultQuery.setComment("Scrollable results by basis akkord range");
    ScrollableResults akkordCursor = akkordResultQuery.scroll();
    return akkordCursor;
  }


  @Override
  public List<Integer> getAkkordIdsByAnzahlToene(int anzahlToene, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend)
  {
    String klangschaerfeAbsteigendString = klangschaerfeAbsteigend ? "DESC" : "ASC";
    String akkordIdAbsteigendString = akkordIdAbsteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString = "SELECT akkord.id FROM Akkord akkord ORDER BY akkord.anzahlToene, akkord.klangschaerfe " + akkordIdAbsteigendString + ", akkord.id " + klangschaerfeAbsteigendString;
    Query akkordResultQuery = sessionFactory.getCurrentSession().createQuery(queryString);
    akkordResultQuery.setReadOnly(true);
    @SuppressWarnings("unchecked")
    List<Integer> resultList = akkordResultQuery.list(); 
    return resultList;
  }

  @Override
  public List<Integer> getAkkordIdsByRange(int minId, int maxId, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend)
  {
    String steigendString = klangschaerfeAbsteigend ? "DESC" : "ASC";
    String akkordIdAbsteigendString = akkordIdAbsteigend ? "DESC" : "ASC";
    String queryString = "";
    queryString = "SELECT akkord.id FROM Akkord akkord WHERE akkord.id between " + minId + " and " + maxId + " ORDER BY akkord.anzahlToene, akkord.klangschaerfe " + steigendString + ", akkord.id " + akkordIdAbsteigendString;
    Query akkordResultQuery = sessionFactory.getCurrentSession().createQuery(queryString);
    akkordResultQuery.setReadOnly(true);
    @SuppressWarnings("unchecked")
    List<Integer> resultList = akkordResultQuery.list(); 
    return resultList;
  }


  //  // Noch benötigt? => Nicht im  Interface!
  //  Map<Akkord, Integer> getAkkorde(Integer rangeBeginId, Integer rangeEndID)
  //  {
  //    Map<Akkord, Integer> akkorde = null;
  //
  //    return akkorde;
  //  }
  //
  //  // Noch nicht im Interface! void oder doch boolean?
  //  public void deleteAkkordeGreaterEqualBasisAkkordId(Integer xBasisAkkordId)
  //  {
  //
  //
  //  }

}
