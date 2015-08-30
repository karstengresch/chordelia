package org.gresch.quintett.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.persistence.KombinationsberechnungDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;

// TODO Logger
@Service("kombinationsberechnungService")
public class KombinationsberechnungServiceImpl implements KombinationsberechnungService {

  @PersistenceContext
  EntityManager entityManager;
  private Log log = LogFactory.getLog(KombinationsberechnungServiceImpl.class);
  @Resource(name = "kombinationsberechnungDao")
  private KombinationsberechnungDao kombinationsberechnungDao;
  @Resource(name = "akkordKombinationenService")
  private AkkordKombinationenService akkordKombinationenService;
  //@Resource(name = "sessionFactory")
  // private SessionFactory sessionFactory;

  //  @Autowired
  //  public KombinationsberechnungServiceImpl(KombinationsberechnungDao kombinationsberechnungDao)
  //  {
  //    this.kombinationsberechnungDao = kombinationsberechnungDao;
  //    this.kombinationsberechnung = kombinationsberechnungDao.getKombinationsberechnung();
  //    // Beany baby
  //  }

  public KombinationsberechnungServiceImpl() {

  }

  /*
     * (non-Javadoc)
     *
     * @see org.gresch.quintett.service.KombinationsberechnungService#kombinationenBerechnen()
     */
  @org.springframework.transaction.annotation.Transactional
    (
      propagation = Propagation.REQUIRED,
      readOnly = false,
      noRollbackFor = Throwable.class
    )
  public void kombinationenBerechnen() throws Exception {
    // wohl:
    Kombinationsberechnung kombinationsberechnung = (Kombinationsberechnung) kombinationsberechnungDao.findOne(1);
    if (null == kombinationsberechnung) {
      throw new RuntimeException("Kann ohne Informationen zur Kombinationsberechnung nichts berechnen. Programm beendet sich.");
    }
    akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
  }

  @org.springframework.transaction.annotation.Transactional
    (
      propagation = Propagation.REQUIRES_NEW,
      readOnly = false,
      noRollbackFor = Throwable.class
    )
  public void kombinationenAusgeben() {
    throw new RuntimeException("'kombinationenAusgeben()' Noch nicht implementiert");
    // FIXME Services verwenden!
    // TODO Ausgabeformat auswerten
    // Profiling
    //    if (kombinationsberechnung.renderer.equals(QuintettRenderer.KONSOLEN_RENDERER))
    //    {
    //      log.info("Kombinationsberechnung.kombinationenAusgeben(): Starte...");
    //      // XXX Renderer Akkordkombinationen.loggeKombinationen();
    //    }
    //    else if (kombinationsberechnung.renderer.equals(QuintettRenderer.LILYPOND_RENDERER))
    //    {
    //      new QuintettRendererLilyPondImpl().rendereKombinationen(kombinationsberechnung.akkordkombinationen);
    //    }
    //    else if (kombinationsberechnung.renderer.equals(QuintettRenderer.MIDI_RENDERER))
    //    {
    //      new QuintettRendererMidiImpl().rendereKombinationen(kombinationsberechnung.akkordkombinationen);
    //    }
    //    else if (kombinationsberechnung.renderer.equals(QuintettRenderer.LILYPOND_AND_MIDI_RENDERER))
    //    {
    //      new QuintettRendererLilypondAndMidiImpl().rendereKombinationen(kombinationsberechnung.akkordkombinationen);
    //    }

  }

  public void verzeichnisseVorbereiten() {
    try {
      FileUtils.forceMkdir(new File(Kombinationsberechnung.TEMPORAERES_VERZEICHNIS_PFAD));
    } catch (IOException e) {
      log.error("Kombinationsberechnung.verzeichnisseVorbereiten(): Konnte trotz Commons Verzeichnis nicht anlegen!\n", e);
    }
  }

  @Override
  //  @Transactional(propagation=Propagation.REQUIRES_NEW)
  @org.springframework.transaction.annotation.Transactional
    (
      propagation = Propagation.REQUIRED,
      readOnly = true,
      noRollbackFor = Throwable.class
    )
  public Kombinationsberechnung getKombinationsBerechnung() { // TODO ans DAO???
    Kombinationsberechnung kombinationsberechnung = (Kombinationsberechnung) kombinationsberechnungDao.findOne(1);

    if (null == kombinationsberechnung) {
      // kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(null);
      log.error("Konnte keine Kombinationsberechnung zurueckgeben!!!");
    }

    return kombinationsberechnung;
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(isolation = Isolation.SERIALIZABLE)
  public void saveKombinationsBerechnung(Kombinationsberechnung kombinationsberechnung) {
    Kombinationsberechnung kombinationsberechungOld = null;

    Integer berechnungsId = kombinationsberechnungDao.getBerechnungsId();

    if(null != berechnungsId)
    {
      try {
        kombinationsberechungOld = (Kombinationsberechnung) kombinationsberechnungDao.findOne(berechnungsId);
        // Vielleicht in Erstinitialisierungsmethode?

      } catch (Exception e) {
        log.info("Did not find Combination Calculation, trying inserting...");
      }
    }
    else {

          kombinationsberechnungDao.save(kombinationsberechnung);

          log.info("Speichere Kombinationsberechnung, weil noch keine ermittelt wurde.");
        // log.error("Could neither find or update Combination Calculation from DB: " + e.getLocalizedMessage());

    }

    //    FlushMode flushModeOld = sessionFactory.getCurrentSession().getFlushMode();
    //    sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
    //    sessionFactory.getCurrentSession().flush();
    ////    sessionFactory.getCurrentSession().getTransaction().commit();
    ////    sessionFactory.getCurrentSession().beginTransaction();
    //    sessionFactory.getCurrentSession().setFlushMode(flushModeOld);

    // TODO In Generic-DAO-Methode (?)

  }

  @Override
  public Long getAnzahlBerechneterAkkorde() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getLetzteAkkordId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getLetzteBasisAkkordId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getLetzteBasisAkkordKlangschaerfe() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getMaxAkkordIdZuBasisAkkordId(Integer letzteBasisAkkordIdInteger) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getMaxAnzahlAkkordToeneAusAkkorden() {
    // TODO Auto-generated method stub
    return null;
  }


  @org.springframework.transaction.annotation.Transactional(isolation = Isolation.REPEATABLE_READ)
  public void updateKombinationsberechnung(EntityManager entityManager, Kombinationsberechnung kombinationsberechnung) {
    try {
      kombinationsberechnungDao.saveOrUpdate(entityManager, kombinationsberechnung);
    } catch (Exception e) {
      log.error("Could npt update Combinatoric Calculation", e);
    }
  }

}
