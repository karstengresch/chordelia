package org.gresch.quintett.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.persistence.KombinationsberechnungDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public void kombinationenBerechnen() throws Exception {
    // wohl:
    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungDao.getKombinationsberechnung();
    if (null == kombinationsberechnung) {
      throw new RuntimeException("Kann ohne Informationen zur Kombinationsberechnung nichts berechnen. Programm beendet sich.");
    }
    akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    //    throw new RuntimeException("'kombinationenBerechnen()' Noch nicht implementiert");
    // TODO Ganz hässlich
    // kombinationsberechnung.akkordKombinationen = new Akkordkombinationen(kombinationsberechnung.maxAnzahlToene,
    // kombinationsberechnung.aesthetischeGewichtung,
    // kombinationsberechnung.basisTon);
    // kombinationsberechnung.akkordKombinationen.berechneKombinationen();
  }

  @Transactional
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
  @Transactional(readOnly = true)
  public Kombinationsberechnung getKombinationsBerechnung() { // TODO ans DAO???
    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungDao.getKombinationsberechnung();

    if (null == kombinationsberechnung) {
      // kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(null);
      log.error("Konnte keine Kombinationsberechnung zurueckgeben!!!");
    }

    return kombinationsberechnung;
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void saveKombinationsBerechnung(Kombinationsberechnung kombinationsberechnung) {
    Kombinationsberechnung kombinationsberechungOld = kombinationsberechnungDao.getKombinationsberechnung();
    // Vielleicht in Erstinitialisierungsmethode?
    if (null == kombinationsberechungOld) {
      kombinationsberechnungDao.save(kombinationsberechnung);
      log.info("Speichere Kombinationsberechnung, weil noch keine ermittelt wurde.");
    } else {
      if (kombinationsberechungOld.getArgumentsString().equalsIgnoreCase(kombinationsberechnung.getArgumentsString())) {
        kombinationsberechnungDao.saveOrUpdate(kombinationsberechnung);
      } else {
        kombinationsberechnungDao.merge(kombinationsberechnung);
      }
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

  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void updateKombinationsberechnung(Kombinationsberechnung kombinationsberechnung) {
    kombinationsberechnungDao.refresh(kombinationsberechnung);
  }

}
