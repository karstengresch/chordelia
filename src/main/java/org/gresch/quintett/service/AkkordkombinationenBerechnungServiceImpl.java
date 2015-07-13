package org.gresch.quintett.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.AesthetischeGewichtung;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.domain.tonmodell.Akkord;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.persistence.AkkordDao;
import org.gresch.quintett.persistence.TonDao;
import org.gresch.quintett.service.util.AkkordkombinationenBerechnungServiceHelper;
import org.hibernate.FlushMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Service("akkordkombinationenBerechnungService")
public class AkkordkombinationenBerechnungServiceImpl implements AkkordkombinationenBerechnungService {

  public final static Log log = LogFactory.getLog(AkkordkombinationenBerechnungServiceImpl.class);
  // Spring-Beans
  @PersistenceContext
  EntityManager entityManager;

  //  @Resource(name = "kombinationsberechnungDao")
  //  private KombinationsberechnungDao kombinationsberechnungDao;
  //
  //  @Resource(name = "tonService")
  //  private TonService tonService;
  @Resource(name = "kombinationsberechnungService")
  private KombinationsberechnungService kombinationsberechnungService;
  @Resource(name = "tonDao")
  private TonDao tonDao;
  @Resource(name = "akkordDao")
  private AkkordDao akkordDao;
  @Resource(name = "akkordKombinationenService")
  private AkkordKombinationenService akkordKombinationenService;

  public AkkordkombinationenBerechnungServiceImpl() {
    // Beany
  }

  //  @Override
  //  @Transactional(propagation = Propagation.REQUIRES_NEW)
  //  public int berechneUndPersistiereKombinationen(int akkordIdStart, int akkordIdEnde, int maxAnzahlToene, AesthetischeGewichtung AesthetischeGewichtung) throws Exception
  //  {
  //    throw new RuntimeException("Noch nicht implementiert. [berechneUndPersistiereKombinationen(int akkordIdStart, int akkordIdEnde, int maxAnzahlToene, AesthetischeGewichtung AesthetischeGewichtung)]");
  //
  //  }

  /* ####### ALGORITHMUS BEGINNT HIER ####### */
  //(propagation = Propagation.REQUIRES_NEW)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public int berechneUndPersistiereNachBasisAkkordBlock(int minBlockId, int maxBlockId, int incrementorToene, int lastAkkordId) {

    Kombinationsberechnung kombinationsberechnung = null;
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    boolean hatAbsteigendeKlangschaerfe = kombinationsberechnung.getHatAbsteigendeKlangschaerfe();
    // TODO assert anstelle dessen.
    if (null == kombinationsberechnung) {
      throw new RuntimeException("AkkordkombinationenBerechnungService: Kombinationsberechnung darf nicht null sein!");
    }
    Session currentSession = entityManager.unwrap(SessionFactory.class).getCurrentSession();
    //    Session session = entityManager.unwrap(SessionFactory.class).openSession();
    //    Session internalSession = SessionFactoryUtils.getNewSession(sessionFactory);
    FlushMode flushModeOld = currentSession.getFlushMode();
    currentSession.setFlushMode(FlushMode.MANUAL);
    //    internalSession.setFlushMode(FlushMode.MANUAL);
    //    StatelessSession session = entityManager.unwrap(SessionFactory.class).openStatelessSession();
    //    org.hibernate.Transaction tx = internalSession.beginTransaction();
    // ggf. delete from akkord where basisakkordId >= akkordIdStart
    // TODO Fehlerbehandlung für den Fall, dass basisAkkordIdStart > als max(basisAkkordId) from akkord
    // TODO Routine für den Fall, dass basisAkkordIdStart und basisAkkordIdEnde nicht vorhanden sind. Ggf. einmal durchiterieren (teuer).
    // TODO Möglicherweise hier die Batch-Implementierung nach Bauer/King, also ohne @Transactional - 2009-03-24
    int incrementorAkkorde = 0; // Gibt die Zahl sämtlicher bearbeiteter Akkorde an, auch der ungültigen.
    int incrementorIntervalle = -1; // Für Anzahl der Intervalle => Wozu Töne und Intervalle?
    // TODO Für kleinere/größere Abstände als Halbtöne veränderbar machen.
    int _anzahlIntervalle = 11; // aesthetischeGewichtung.getSize();
    int anzahlAkkorde = lastAkkordId; // basisAkkordIdEnde; // die Anzahl der _hinzugefügten_ Akkorde, also der gültigen Akkorde. Zugleich Akkord-ID.
    int intervallEins = 0;
    int intervallZwei = 0;
    ScrollableResults akkordCursor = null;
    //    akkordCursor = null;
    //    akkordCursor = akkordKombinationenService.getScrollableResultByBasisAkkordRange(minBlockId, maxBlockId, 0, true);
    akkordCursor = akkordDao.getScrollableResultByBasisAkkordRange(minBlockId, maxBlockId, 0, hatAbsteigendeKlangschaerfe);
    //    String queryString = "";
    //    queryString = "FROM Akkord akkord WHERE akkord.id between " + minBlockId + " and " + maxBlockId + " ORDER BY akkord.id ASC";
    //    Query akkordResultQuery = session.createQuery(queryString);
    //    //     akkordResultQuery.setFetchSize(fetchBlockGroesze);
    //    akkordResultQuery.setReadOnly(true);
    //    akkordResultQuery.setCacheMode(CacheMode.IGNORE);
    //    //    akkordResultQuery.setComment("Karstens Abfragekommentar");
    //    akkordCursor = akkordResultQuery.scroll(ScrollMode.FORWARD_ONLY);
    // log.info("Cursor ist: " + akkordCursor.)
    int incrementorCursorzeilen = 0;

    while (akkordCursor.next()) {
      incrementorCursorzeilen++;
      Akkord akkordFromCursor = (Akkord) akkordCursor.get(0); // 0 für aktuellen Cursorzeiger
      // Permutationen nur für letzte Kombination berechnen
      if (null != akkordFromCursor && (akkordFromCursor.getAnzahlToene() == (incrementorToene - 1))) // da oberer Zähler 2-terminiert
      {
        // Einmal für erste Schleife - besser machen!
        if (log.isDebugEnabled()) {
          log.debug("A~K~.berechneKombinationen(): In Schleife zwei: Akkord-Nr.: " + String.valueOf(incrementorAkkorde));
        }
        // TODO Schleife für die Intervalle, die oben oder unten hinzuzufügen sind.
        // TODO iterieren über Akkorde aus vorhergehender Schleife / KG 2006-03-23

        for (incrementorIntervalle = 1; incrementorIntervalle < _anzahlIntervalle + 1; incrementorIntervalle++) {
          //
          Akkord bufferAkkord = null;
          bufferAkkord = null;
          bufferAkkord = akkordFromCursor;
          Ton _ton = null;
          int fetchIntervall = -1;
          // _ton = new Ton();
          if (log.isDebugEnabled()) {
            log.debug("A~K~.berechneKombinationen(): In Schleife drei: " + String.valueOf(incrementorIntervalle));
          }
          // TODO Sollte vorletzter sein ---> VON OBEN <---
          // Deshalb Umkehrung implementieren.
          if (kombinationsberechnung.getHatAbsteigendeKlangschaerfe()) {
            // ******* TODO Akkord nach oben verschieben wg. gleichbleibendem Basiston! *******
            if (log.isDebugEnabled()) {
              log.debug("Akkordkombinationen.berechneKombinationen(): _akkord.get(0).getAbstandZumEingestrichenenC(): "
                + bufferAkkord.getTonZuNummerNullbasiert(0).getAbstandZumEingestrichenenC());
              log.debug("Akkordkombinationen.berechneKombinationen(): aesthetischeGewichtung.getGewichtung().get()   : "
                + AesthetischeGewichtung.getGewichtungSortierung().get(Integer.valueOf(incrementorIntervalle)));
            }
            intervallEins = 0;
            intervallZwei = 0;
            intervallEins = bufferAkkord.getTonZuNummerNullbasiert(0).getAbstandZumEingestrichenenC();
            intervallZwei = AesthetischeGewichtung.getGewichtungSortierung().get(Integer.valueOf(incrementorIntervalle));
            // _ton.setAbstandZumEingestrichenenC(intervallEins - intervallZwei);
            // _ton = Tonumfang.getTon(intervallEins - intervallZwei);
            fetchIntervall = intervallEins - intervallZwei;
            _ton = tonDao.find((fetchIntervall));
          } else if (!kombinationsberechnung.getHatAbsteigendeKlangschaerfe()) {
            intervallEins = 0;
            intervallZwei = 0;
            intervallEins = bufferAkkord.getTonZuNummerNullbasiert(bufferAkkord.getTonList().size() - 1).getAbstandZumEingestrichenenC();
            intervallZwei = AesthetischeGewichtung.getGewichtungSortierung().get(Integer.valueOf(incrementorIntervalle));
            // -1 für Sortierung von unten/aufsteigend: Immer nur das letzte Intervall betrachtend.
            // _ton.setAbstandZumEingestrichenenC(intervallEins + intervallZwei);
            // _ton = Tonumfang.getTon(intervallEins + intervallZwei);
            _ton = tonDao.find((intervallEins + intervallZwei));
          }
          List<Ton> _tonList;
          _tonList = new LinkedList<Ton>();
          // Prüfen!
          if (kombinationsberechnung.getHatAbsteigendeKlangschaerfe()) {
            // ???
            _tonList.add(tonDao.findByExample((_ton)));
            //                  _tonList.add(tonDao.find(_ton.getId()));
            //                  _tonList.add(_ton);
            _tonList.addAll(bufferAkkord.getTonList());
            List<Ton> _tonListBuffer;
            _tonListBuffer = new LinkedList<Ton>();
            // XXX: FIXME Was ist, wenn der Abstand kleiner als der Basiston ist?
            _tonListBuffer.addAll(transponiere(_tonList, (intervallZwei * -1)));
            _tonList = null;
            _tonList = new LinkedList<Ton>();
            _tonList.addAll(_tonListBuffer);
            _tonListBuffer = null;
          } else if (!kombinationsberechnung.getHatAbsteigendeKlangschaerfe()) {
            _tonList.add(tonDao.findByExample((_ton)));
            //                  _tonList.add(_ton);
            //                  _tonList.add(tonDao.find(_ton.getId()));
          }
          // TODO Sollte das nicht nach der TonArray-Schleife folgen?
          Akkord finalAkkord = null;
          finalAkkord = new Akkord();
          finalAkkord.setTonList(_tonList);
          finalAkkord.setKlangschaerfe(
            (akkordFromCursor.getKlangschaerfe() + kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(intervallZwei)));
          finalAkkord.setAnzahlToene(_tonList.size());
          _tonList = null;

          incrementorAkkorde++;
          bufferAkkord = null;
          if (AkkordkombinationenBerechnungServiceHelper.istEinErlaubterAkkord(finalAkkord)) {
            // Mit Generate-ID wird offenbar kein Batch unterstützt
            finalAkkord.setId(anzahlAkkorde + 1);
            int temporaereBasisAkkordId = -1;
            temporaereBasisAkkordId = akkordFromCursor.getId();
            finalAkkord.setBasisAkkordId(temporaereBasisAkkordId);
            int temporaereAkkordId = finalAkkord.getId();

            if (akkordCursor.isLast()) {
              if (log.isDebugEnabled()) {
                log.debug("akkordCursor.isLast() at row number: " + akkordCursor.getRowNumber());
              }
            }

            if (log.isDebugEnabled()) {
              log.info("A~K.berechneKombinationen(): ID: " + finalAkkord.getId() + " - Anzahl Töne:" + finalAkkord.getAnzahlToene()
                + " - Klangschärfe: "
                + finalAkkord.getKlangschaerfe());
            }

            //                  saveAkkord(akkord2);
            akkordDao.makePersistentReadOnly(finalAkkord);
            //                  entityManager.unwrap(SessionFactory.class).getCurrentSession().evict(finalAkkord);
            anzahlAkkorde++;

            //            entityManager.unwrap(SessionFactory.class).getCurrentSession().evict(finalAkkord);

            //                  if((anzahlAkkorde % 1000 == 0) || (incrementorToene == 3 && anzahlAkkorde % 1000 == 0))
            //                  {
            //                    entityManager.unwrap(SessionFactory.class).evict(Akkord.class);
            //                    FlushMode flushModeOld = entityManager.unwrap(SessionFactory.class).getCurrentSession().getFlushMode();
            //                    entityManager.unwrap(SessionFactory.class).getCurrentSession().setFlushMode(FlushMode.MANUAL);
            //                    entityManager.unwrap(SessionFactory.class).getCurrentSession().flush();
            //                    log.info("flushNachBasiston (1)");
            //                    entityManager.unwrap(SessionFactory.class).getCurrentSession().setFlushMode(flushModeOld);
            //                    log.info("A~K~.berechneKombinationen(): Flush bei Anzahl Akkorde: " + String.valueOf(anzahlAkkorde));
            //
            //                  }

            if ((anzahlAkkorde % 1000 == 0) && (anzahlAkkorde < 10000)) {
              log.info("A~K~.berechneKombinationen(): Anzahl Akkorde: " + String.valueOf(anzahlAkkorde));
            } else if ((anzahlAkkorde > 9999) && (anzahlAkkorde % 10000 == 0) && (anzahlAkkorde < 100000)) {
              log.info("A~K~.berechneKombinationen(): Anzahl Akkorde: " + String.valueOf(anzahlAkkorde));
            } else if ((anzahlAkkorde > 99999) && (anzahlAkkorde % 100000 == 0) && (anzahlAkkorde < 1000000)) {
              log.info("A~K~.berechneKombinationen(): Anzahl Akkorde: " + String.valueOf(anzahlAkkorde));
            } else if ((anzahlAkkorde > 999999) && (anzahlAkkorde % 1000000 == 0)) {
              log.info("A~K~.berechneKombinationen(): Anzahl Akkorde: " + String.valueOf(anzahlAkkorde));
            }

            // TODO DAO!
            // ((anzahlAkkorde != 0 && anzahlAkkorde % 1000 == 0) ||
            if (akkordCursor.isLast()) {
              // TODO ggf Rollback hier
              // log.info("Vorm Kombinationsberechnungs-Flush: Zeilennummer des Akkord Cursors ist: " + (akkordCursor.getRowNumber()+1) + " - Anzahl Akkorde sind: " + anzahlAkkorde);
              Integer temporaereBasisAkkordKlangschaerfe = akkordFromCursor.getKlangschaerfe();
              kombinationsberechnung.setLetzteBasisAkkordKlangschaerfe(temporaereBasisAkkordKlangschaerfe);
              kombinationsberechnung.setLetzteBasisAkkordId(temporaereBasisAkkordId);
              kombinationsberechnung.setLetzteAkkordId(temporaereAkkordId);
              kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
              currentSession.flush();

            }
            //            session.evict(akkordFromCursor);
            //            LogFactory.getFactory().release();
          }
          finalAkkord = null;

        }
      }
    } // end while

    akkordCursor.close();
    //    entityManager.unwrap(SessionFactory.class).getCurrentSession().evict(akkordCursor);

    //    ThreadLocalUtil.cleanupThreadLocals(null, "main", Thread.currentThread().getContextClassLoader());
    akkordCursor = null;

    // Check
    kombinationsberechnung.setBereitsBerechneteToene(incrementorToene);
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    currentSession.setFlushMode(flushModeOld);
    //    internalSession.flush();
    //    internalSession.clear();
    //    tx.commit();
    //    internalSession.close();
    kombinationsberechnung = null;
    //      log.info(incrementorAkkorde + " Kombinationen berechnet, davon " + anzahlAkkorde + " gültig.");
    return anzahlAkkorde;
  }

  // Diese Dokumentation stehen lassen: propagationLevel = REQUIRES_NEW entfernt wegen Problemen mit kombinationsberechnung.
  @Transactional(propagation = Propagation.NESTED)
  public int runIncrementorToeneZwei() {
    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    // TODO Prüfen, ob Zweitonklänge bereits berechnet worden sind??? Ggf. löschen???
    Ton basisTon = kombinationsberechnung.getBasisTon();
    if (null == basisTon || (null == basisTon.getAbstandZumEingestrichenenC() || StringUtils.isEmpty(basisTon.getTonName()) || null == basisTon
      .getOktavlage())) {
      basisTon = kombinationsberechnung.getDefaultBasisTon();
      log.warn("Akkordkombinationen.runIncrementorToeneZwei(): Basiston auf Default gesetzt!!!");
    }
    //    int incrementorToene = -1; // Anzahl der Töne: äußere Schleife
    int incrementorAkkorde = 0; // Gibt die Zahl sämtlicher bearbeiteter Akkorde an, auch der ungültigen.
    int incrementorIntervalle = -1; // Für Anzahl der Intervalle => Wozu Töne und Intervalle?
    // TODO Default überschreibbar machen.
    int _anzahlIntervalle = 11; // = aesthetischeGewichtung.getSize();
    int anzahlAkkorde = 0; // die Anzahl der _hinzugefügten_ Akkorde, also der gültigen Akkorde. Zugleich Akkord-ID.;
    //    TreeSet<Akkord> _akkordSet = null;
    //    _akkordSet = getAkkordSet(_akkordSet);
    // if (!laden)
    // {
    if (log.isDebugEnabled()) {
      log.debug("A~K~.berechneKombinationen(): Zwei Töne!");
    }
    // Erstelle eine List aus Akkorden - anzahlIntervalle sollte eigentlich konstant 11 sein (wenn nicht vierteltönig etc.).
    log.info("A~K~.berechneKombinationen(): Neue AkkordList erstellt mit Anzahl Intervallen: " + String.valueOf(_anzahlIntervalle));
    // TODO XXX Auf- oder absteigend!!!
    //    Transaction transaction = entityManager.unwrap(SessionFactory.class).getCurrentSession().beginTransaction();
    for (incrementorIntervalle = 1; incrementorIntervalle < _anzahlIntervalle + 1; incrementorIntervalle++) {
      incrementorAkkorde++;
      // TODO: Weshalb zum Basiston??? Korrekterweise doch nur zum obersten/untersten Ton
      if (log.isDebugEnabled()) {
        log.debug("A~K~.berechneKombinationen(): In Schleife zwei (zwei Töne): " + String.valueOf(incrementorIntervalle));
      }
      List<Ton> _tonList = null;
      _tonList = new LinkedList<Ton>(); // zwei Töne
      _tonList.add(tonDao.find(basisTon.getId())); // Ton #2 unten in iii-Schleife.
      //      _tonList.get(0).setAbstandZumBasisTon(0);
      Ton _ton = null;
      Integer abstandZumBasiston = AesthetischeGewichtung.getGewichtungSortierung().get(Integer.valueOf(incrementorIntervalle));
      _ton = tonDao.find((basisTon.getAbstandZumEingestrichenenC() + abstandZumBasiston));
      //      _ton.setAbstandZumBasisTon(abstandZumBasiston);
      // _tonList.add(_ton);
      _tonList.add(tonDao.find(_ton.getId()));
      Akkord _akkord = null;
      _akkord = new Akkord();
      _akkord.setTonList(_tonList);
      _akkord.setKlangschaerfe(kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(abstandZumBasiston));
      _akkord.setAnzahlToene(_tonList.size());
      _tonList = null;
      if (AkkordkombinationenBerechnungServiceHelper.istEinErlaubterAkkord(_akkord)) {
        // Mit Generate-ID wird oft kein Batch unterstützt
        _akkord.setId(anzahlAkkorde + 1);
        kombinationsberechnung.setLetzteAkkordId(_akkord.getId());
        //        _akkordSet.add(_akkord);
        if (log.isDebugEnabled()) {
          log.debug("A~K.berechneKombinationen(): Anzahl Töne:" + _akkord.getAnzahlToene() + " - Klangschärfe:" + _akkord.getKlangschaerfe());
        }
        // TODO: DAO!
        if (kombinationsberechnung.getHatPersistenzSchreiben()) {
          akkordDao.makePersistentReadOnly(_akkord);
        }
        anzahlAkkorde++;
        _akkord = null;
      } else {
        _akkord = null;
        continue;
      }
    }
    //    transaction.commit();
    // TODO DAO!
    kombinationsberechnung.setBereitsBerechneteToene(2);
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    FlushMode flushModeOld = entityManager.unwrap(SessionFactory.class).getCurrentSession().getFlushMode();
    entityManager.unwrap(SessionFactory.class).getCurrentSession().setFlushMode(FlushMode.MANUAL);
    entityManager.unwrap(SessionFactory.class).getCurrentSession().flush();
    entityManager.unwrap(SessionFactory.class).getCurrentSession().setFlushMode(flushModeOld);
    // Nicht mehr benötigt...
    //    akkordKombinationen.put(Integer.valueOf(incrementorToene), _akkordSet);
    // }
    // if (laden)
    // {
    // anzahlAkkorde = 11;
    // }

    return anzahlAkkorde;
  }

  // XXX Remove if not needed!
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveAkkord(Akkord akkord) {
    akkordDao.makePersistentReadOnly(akkord);
  }

  // TODO Asserts für die Parameter!
  // TODO Prüfen: In Util?
  //  @Transactional(propagation=Propagation.NESTED) => Check if needed (i.e. otherwise not threadsafe).
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public List<Ton> transponiere(List<Ton> xTonList, Integer xTranspositionsIntervall) {
    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    Ton basisTon = kombinationsberechnung.getBasisTon();
    List<Ton> _tonArrayBuffer = new LinkedList<Ton>();
    Ton _ton;
    int i;

    if (null == xTonList) {
      log.error("AK.transponiere(): xTonList war null!");
      throw new RuntimeException();
    } else if (xTonList.size() < 2) {
      log.error("AK.transponiere(): xTonList war zu klein: " + xTonList.size() + "!");
      throw new RuntimeException();
    }

    if (null == xTranspositionsIntervall) {
      log.error("AK.transponiere(): xTranspositionsinterwall war null!");
      throw new RuntimeException();
    }

    if (xTranspositionsIntervall > 0) {
      log.error("AK.transponiere(): xTranspositionsintervall war zu groß: " + xTranspositionsIntervall);
      //      log.error("Töne waren: \n" + (new Akkord().setTonList(xTonList)).toStringBuilder().toString());
      xTranspositionsIntervall *= -1;
      // throw new RuntimeException();
    }
    Integer _abstand = xTranspositionsIntervall * -1;
    if (null == basisTon || null == basisTon.getId()) {
      basisTon = kombinationsberechnung.getDefaultBasisTon();
      log.warn("Akkordkombinationen.transponiere(): Basiston auf Default gesetzt!!! #3");
    }

    for (i = 0; i < xTonList.size(); i++) {
      _ton = null;
      if (i == 0) {
        _ton = tonDao.find(basisTon.getId());
      } else {

        if (null == xTonList.get(i)) {
          log.error("AK.transponiere(): xTonList.get(i) war 'null' fuer: " + i);
          throw new RuntimeException();
        }

        try {
          _ton = tonDao.find((xTonList.get(i).getAbstandZumEingestrichenenC() + _abstand));
        } catch (Exception e) {
          log.error("Schwerer Fehler beim Transponieren: " + e.getMessage() + "\nProgramm beendet sich!");
          System.exit(-1);
        }
        //        _ton.setAbstandBasisTonDurchAbstandZumEingestrichenenC(basisTon);
      }

      // häßlich
      //      if (null == _ton.getId())
      //      {
      //        _ton.setId((tonService.getTonByExample(_ton)).getId());
      //      }

      // Transaction _transaction = entityManager.unwrap(SessionFactory.class).getCurrentSession().beginTransaction();
      _tonArrayBuffer.add(_ton);
      // _transaction.commit();
    }
    // TODO Hier vielleicht für absteigende Klangschärfe Töne reversieren.
    LinkedList<Ton> returnValue = (LinkedList<Ton>) _tonArrayBuffer;
    _tonArrayBuffer = null;
    return returnValue;
  }

  //  public BasisTon getBasisTon()
  //  {
  //    return basisTon;
  //  }
  //
  //  public void setBasisTon(BasisTon basisTon)
  //  {
  //    this.basisTon = basisTon;
  //  }

  //  public AesthetischeGewichtung getAesthetischeGewichtung()
  //  {
  //    return aesthetischeGewichtung;
  //  }
  //
  //  public void setAesthetischeGewichtung(AesthetischeGewichtung aesthetischeGewichtung)
  //  {
  //    this.aesthetischeGewichtung = aesthetischeGewichtung;
  //  }

  /* ####### ###### ####### HELPER METHODS START ####### ###### ####### */
  //  private TreeSet<Akkord> getAkkordSet(TreeSet<Akkord> _akkordSet)
  //  {
  //    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
  //    if (!(null == _akkordSet))
  //    {
  //      _akkordSet = null;
  //    }
  //    if (kombinationsberechnung.getHatAbsteigendeKlangschaerfe())
  //    {
  //      _akkordSet = new TreeSet<Akkord>(new AbsteigenderKlangschaerfenComparator());
  //    }
  //    else
  //    {
  //      _akkordSet = new TreeSet<Akkord>(new AufsteigenderKlangschaerfenComparator());
  //    }
  //    return _akkordSet;
  //  }
  //  public int getAnzahlToene()
  //  {
  //    return anzahlToene;
  //  }
  //
  //  public void setAnzahlToene(int anzahlToene)
  //  {
  //    this.anzahlToene = anzahlToene;
  //  }
  //  @Override
  //  public int berechneUndPersistiereKombinationen(int akkordIdStart, int akkordIdEnde, int maxAnzahlToene) throws Exception
  //  {
  //    // TODO Auto-generated method stub
  //    throw new RuntimeException("Noch nicht implementiert.");
  //
  //  }
  /* ####### ###### ####### HELPER METHODS END ####### ###### ####### */

}
