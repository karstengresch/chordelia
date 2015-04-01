package org.gresch.quintett.persistence;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * TODO: Remove Singleton impl. when starting to use Spring.
 * TODO: Logging.
 *
 * @author Karsten
 *
 */
@Repository("kombinationsberechnungDao")
public class KombinationsberechnungDaoHibernateImpl extends com.trg.dao.dao.standard.GenericDAOImpl<Kombinationsberechnung, Integer> implements KombinationsberechnungDao
{
  private final Log log = LogFactory.getLog(KombinationsberechnungDaoHibernateImpl.class);
  // TODO remove singleton
  private final static KombinationsberechnungDaoHibernateImpl theInstance = new KombinationsberechnungDaoHibernateImpl();
  // TODO sessionFactory
  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  private KombinationsberechnungDaoHibernateImpl()
  {
    // Beany
  }

  @Override
  public Long getAnzahlBerechneterAkkorde()
  {
    Long anzahlBerechneterAkkorde;

    // anzahlBerechneterAkkorde = (Long) sessionFactory.getCurrentSession().createQuery("select count(distinct a) from Akkord a").uniqueResult();
    try
    {
      anzahlBerechneterAkkorde = Long.valueOf(((Integer) sessionFactory.getCurrentSession().createSQLQuery("select max(id) from Akkord").uniqueResult()).longValue());
    }
    catch (NullPointerException e)
    {
      anzahlBerechneterAkkorde = Long.valueOf(0);
    }

    return anzahlBerechneterAkkorde;
  }

  @Override
  public Long getAnzahlBerechneterAkkordeZuAnzahlAkkordToene(Integer xAkkordToene)
  {
    Long anzahlBerechneterToene;

    // anzahlBerechneterToene = (Long) sessionFactory.getCurrentSession().createQuery("select count(distinct a) from Akkord a where a.anzahlToene = " + xAkkordToene).uniqueResult();
    anzahlBerechneterToene = Long.valueOf((String) sessionFactory.getCurrentSession().createSQLQuery("select max(id) from Akkord a where a.anzahlToene = " + xAkkordToene)
                                                                 .uniqueResult());
    return anzahlBerechneterToene;
  }

  @Override
  public Integer getMaxAnzahlAkkordToeneAusBerechnungsInformation()
  {
    Integer maxAnzahlAkkordToeneAusBerechnungsInformation = 0;

    // alt:maxAnzahlAkkordToene = (Integer) sessionFactory.getCurrentSession().createQuery("select max(a.anzahlToene) from Akkord
    // a").uniqueResult();
    maxAnzahlAkkordToeneAusBerechnungsInformation = (Integer) sessionFactory.getCurrentSession()
                                                                            .createSQLQuery("select bereits_berechnete_toene from berechnungs_informationen").uniqueResult();
    if (null == maxAnzahlAkkordToeneAusBerechnungsInformation)
    {
      // Später: Loggen
      maxAnzahlAkkordToeneAusBerechnungsInformation = -1;
    }
    return Integer.valueOf(maxAnzahlAkkordToeneAusBerechnungsInformation.intValue());
  }

  @Override
  public Integer getMaxAnzahlAkkordToeneAusAkkorden()
  {
    // BigInteger maxAnzahlAkkordToeneAusAkkorden = BigInteger.valueOf(0);
    Integer maxAnzahlAkkordToeneAusAkkorden = 0;
    // alt:maxAnzahlAkkordToene = (Integer) sessionFactory.getCurrentSession().createQuery("select max(a.anzahlToene) from Akkord
    // a").uniqueResult();
    // String subselectQuery = "select max(c.county) from (select count(position) county from ton_akkord group by akkord_id) c";
    String subselectQuery = "select (max(position)+1) from TON_AKKORD where AKKORD_ID = ( select max(akkord_id) from ton_akkord)";
    maxAnzahlAkkordToeneAusAkkorden = (Integer) sessionFactory.getCurrentSession().createSQLQuery(subselectQuery).uniqueResult();

    if (null == maxAnzahlAkkordToeneAusAkkorden)
    {
      // Später: Loggen
      maxAnzahlAkkordToeneAusAkkorden = -1; // BigInteger.valueOf(-1);
    }

    return Integer.valueOf(maxAnzahlAkkordToeneAusAkkorden.intValue());
  }

  public static KombinationsberechnungDao getInstance()
  {
    return theInstance;
  }

  @Override
  public Integer getMaxIdZuAnzahlAkkordToene(Integer xAkkordToene)
  {
    Integer maxId;
    maxId = (Integer) sessionFactory.getCurrentSession().createQuery("select max(a.id) from Akkord a where a.anzahlToene = " + xAkkordToene).uniqueResult();
    return maxId;
  }

  @Override
  public Integer getMinIdZuAnzahlAkkordToene(Integer xAkkordToene)
  {
    Integer maxId;
    // TODO Projections
    maxId = (Integer) sessionFactory.getCurrentSession().createQuery("select min(a.id) from Akkord a where a.anzahlToene = " + xAkkordToene).uniqueResult();
    return maxId;
  }

  //  public Boolean ladenAusDatenbankNuetzlich()
  //  {
  //
  //    Integer maxAnzahlToene = -1;
  //    if ((getMaxAnzahlAkkordToeneAusAkkorden() - getMaxAnzahlAkkordToeneAusAkkorden()) != 0)
  //    {
  //      maxAnzahlToene = getMaxAnzahlAkkordToeneAusAkkorden();
  //    }
  //    else
  //    {
  //      maxAnzahlToene = getMaxAnzahlAkkordToeneAusAkkorden();
  //    }
  //
  //    return (getAnzahlBerechneterAkkorde() > 0 && maxAnzahlToene >= 3) ? true : false;
  //  }

  // ???
  @Override
  public boolean saveOrUpdate(Kombinationsberechnung kombinationsberechnung)
  {

    boolean successfullyUpdated = false;
    try
    {
      sessionFactory.getCurrentSession().saveOrUpdate(kombinationsberechnung);
      successfullyUpdated = true;
    }
    catch (Exception e)
    {
      log.error("Konnte Kombinationsberechnung nicht updaten: " + e.getLocalizedMessage());
    }

    return successfullyUpdated;

  }

  @Override
  public Integer getBerechnungsId()
  {
    Integer berechnungsId = 0;

    berechnungsId = (Integer) sessionFactory.getCurrentSession().createQuery("select max(b.id) from Kombinationsberechnung b").uniqueResult();

    if (null == berechnungsId)
    {
      // Später: Loggen
    }

    return berechnungsId;
  }

  @Override
  public Integer getLetzteBasisAkkordKlangschaerfe()
  {
    Integer letzteBasisAkkordKlangschaerfe = 0;
    try
    {
      letzteBasisAkkordKlangschaerfe = (Integer) sessionFactory.getCurrentSession().createSQLQuery("select letzte_basis_akkord_klangschaerfe from berechnungs_informationen")
                                                               .uniqueResult();
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }

    if (null == letzteBasisAkkordKlangschaerfe)
    {
      // Später: Loggen
    }
    return letzteBasisAkkordKlangschaerfe;
  }

  public Integer getLetzteAkkordId()
  {
    Integer letzteAkkordId = 0;
    try
    {
      letzteAkkordId = (Integer) sessionFactory.getCurrentSession().createSQLQuery("select " + "letzte_akkord_id from berechnungs_informationen").uniqueResult();
    }
    catch (Exception e)
    {
      // TODO: Kommt, wenn noch nicht vorhanden - dann gar nicht erst abfragen!
      System.out.println(e.toString());
    }

    if (null == letzteAkkordId)
    {
      // Später: Loggen
    }
    return letzteAkkordId;
  }

  @Override
  public Integer getLetzteBasisAkkordId()
  {
    {
      Integer letzteBasisAkkordId = 0;
      try
      {
        letzteBasisAkkordId = (Integer) sessionFactory.getCurrentSession().createSQLQuery("select letzte_basis_akkord_id from berechnungs_informationen").uniqueResult();
      }
      catch (Exception e)
      {
        System.out.println(e.toString());
      }

      if (null == letzteBasisAkkordId)
      {
        // Später: Loggen
      }
      return letzteBasisAkkordId;
    }
  }

  @Override
  public Integer getMaxAkkordIdZuBasisAkkordId(Integer xBasisAkkordId)
  {
    {
      Integer maxAkkordAkkordIdZuBasisAkkordId = 0;
      try
      {
        maxAkkordAkkordIdZuBasisAkkordId = (Integer) sessionFactory.getCurrentSession()
                                                                   .createSQLQuery(("select max(id) from akkord where basis_akkord_id = " + xBasisAkkordId)).uniqueResult();
      }
      catch (Exception e)
      {
        System.out.println(e.toString());
      }

      if (null == maxAkkordAkkordIdZuBasisAkkordId)
      {
        // Später: Loggen
      }
      return maxAkkordAkkordIdZuBasisAkkordId;
    }
  }

  @Override
  public Kombinationsberechnung getKombinationsberechnung()
  {
//    Kombinationsberechnung kombinationsberechnung = (Kombinationsberechnung) sessionFactory.getCurrentSession().get(Kombinationsberechnung.class, 1);
    Kombinationsberechnung kombinationsberechnung = find(1);
    if (null == kombinationsberechnung)
    {
      log.error("******* Konnte keine Kombinationsberechnung zurueckgeben!");
    }
    return kombinationsberechnung;
  }

  @Override
  public void merge(Kombinationsberechnung kombinationsberechnung)
  {
    try
    {
      sessionFactory.getCurrentSession().merge(kombinationsberechnung);
    }
    catch (Exception e)
    {
      log.error("Konnte Kombinationsberechnung nicht updaten: " + e.getLocalizedMessage());
    }

  }

}
