package org.gresch.quintett.service;

import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.gresch.quintett.KombinationsberechnungParameter.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-main-test.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class})
// If rollback set to false, tests will fail
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AkkordkombinationenBerechnungServiceTest {

  @Resource(name = "akkordkombinationenBerechnungService")
  AkkordkombinationenBerechnungService akkordkombinationenBerechnungService;

  @Resource(name = "akkordKombinationenService")
  AkkordKombinationenService akkordKombinationenService;

  @Resource(name = "kombinationsberechnungService")
  KombinationsberechnungService kombinationsberechnungService;

  @Resource(name = "tonService")
  TonService tonService;

  @PersistenceContext
  EntityManager entityManager;


  @Test
  public void testSetup() {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'akkordkombinationenBerechnungService' sollte instantiiert sein.", akkordkombinationenBerechnungService);
  }

  @Test
  public void testBerechneUndPersistiereZweitonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "2",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    // Mindestens AesthetischeGewichtung und Basiston
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
  KombinationsberechnungService.flushManually(entityManager);
    int anzahlZweitonklaenge = akkordkombinationenBerechnungService.runIncrementorToeneZwei();
    assertTrue("Genau elf Klaenge sollten berechnet worden sein.", anzahlZweitonklaenge == 11);
    KombinationsberechnungService.flushManually(entityManager);
    kombinationsberechnung = null;
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 2 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 2);
    // TODO Klangschärfe prüfen
    // TODO Weitere Prüfungen, insb. korrekte Akkorde.
    // Cleanup;
    //    sessionFactory.getCurrentSession().evict(kombinationsberechnung);
    KombinationsberechnungService.flushManually(entityManager);
  }

  @Test
  public void testBerechneUndPersistiereDreitonIntervalle() throws Exception {
    // Delete previous
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "3",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    KombinationsberechnungService.flushManually(entityManager);
  assertNotNull("Kombinationsberechnung sollte gespeichert worden sein.", kombinationsberechnungService.getKombinationsBerechnung());
    int anzahlDreitonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    KombinationsberechnungService.flushManually(entityManager);
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertEquals("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", Integer.valueOf(3),
      Integer.valueOf(kombinationsberechnung.getBereitsBerechneteToene()));
    assertEquals("Genau 110 Klaenge sollten berechnet worden sein.", Integer.valueOf(110), Integer.valueOf(anzahlDreitonklaenge - 11));
  }

  // @Test
  public void testBerechneUndPersistiereViertonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "4",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlViertonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    KombinationsberechnungService.flushManually(entityManager);
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 4 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 4);
    assertEquals("Genau 990 Klaenge sollten berechnet worden sein.", Integer.valueOf(990), Integer.valueOf(anzahlViertonklaenge - 110 - 11));
  }

  //  @Test
  public void testBerechneUndPersistiereFuenftonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "5",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlFuenftonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 5 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 5);
    assertEquals("Genau 7.920 Klaenge sollten berechnet worden sein.", Integer.valueOf(7920), Integer.valueOf(anzahlFuenftonklaenge));
  }

  //  @Test
  public void testBerechneUndPersistiereSechstonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "6",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlSechstonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 6 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 6);
    assertEquals("Genau 55.440 Klaenge sollten berechnet worden sein.", Integer.valueOf(55440), Integer.valueOf(anzahlSechstonklaenge));
  }

  //  @Test
  public void testBerechneUndPersistiereSiebentonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "7",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlSiebentonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 7);
    assertEquals("Genau 332.640 Klaenge sollten berechnet worden sein.", Integer.valueOf(332640), Integer.valueOf(anzahlSiebentonklaenge));
  }

  //  @Test
  public void testBerechneUndPersistiereAchttonIntervalle() throws Exception {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{CLI_PARAMETER_MAX_ANZAHL_TOENE, "8",
      CLI_PARAMETER_DB_ERSTELLEN, "j",
      CLI_PARAMETER_PERSISTENZ_LADEN, "n"});
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlAchttonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 8);
    assertEquals("Genau 1.663.200 Klaenge sollten berechnet worden sein.", Integer.valueOf(1663200), Integer.valueOf(anzahlAchttonklaenge));
  }

}
