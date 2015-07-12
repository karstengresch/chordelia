package org.gresch.quintett.renderer;

import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_DB_ERSTELLEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_MAX_ANZAHL_TOENE;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_PERSISTENZ_LADEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.kombination.AkkordIdRangeZwoelftonklaenge;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.service.AkkordKombinationenService;
import org.gresch.quintett.service.AkkordkombinationenBerechnungService;
import org.gresch.quintett.service.KombinationsberechnungService;
import org.gresch.quintett.service.TonService;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
// If rollback set to false, tests will fail
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RendererTest
{

  @Resource(name = "akkordkombinationenBerechnungService")
  AkkordkombinationenBerechnungService akkordkombinationenBerechnungService;

  @Resource(name = "akkordKombinationenService")
  AkkordKombinationenService akkordKombinationenService;

  @Resource(name = "kombinationsberechnungService")
  KombinationsberechnungService kombinationsberechnungService;

  @Resource(name = "tonService")
  TonService tonService;

  @Resource(name = "sessionFactory")
  SessionFactory sessionFactory;
  
  @Resource(name = "lilypondRenderer")
  QuintettRenderer lilyPondRenderer;

  @Before
  public void setup()
  {
    kombinationsberechnungService.verzeichnisseVorbereiten();
  }
  
  @Test
  public void testSetup()
  {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'akkordkombinationenBerechnungService' sollte instantiiert sein.", akkordkombinationenBerechnungService);
    
  }

  @Test
  public void testBerechneUndRendereZweitonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "2",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    // Mindestens AesthetischeGewichtung und Basiston
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    FlushMode flushModeOld = sessionFactory.getCurrentSession().getFlushMode();
    sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
    sessionFactory.getCurrentSession().flush();
    int anzahlZweitonklaenge = akkordkombinationenBerechnungService.runIncrementorToeneZwei();
    assertTrue("Genau elf Klaenge sollten berechnet worden sein.", anzahlZweitonklaenge == 11);
    sessionFactory.getCurrentSession().flush();
    kombinationsberechnung = null;
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 2 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 2);
    // TODO Klangschärfe prüfen
    // TODO Weitere Prüfungen, insb. korrekte Akkorde.
    // Cleanup;
//    sessionFactory.getCurrentSession().evict(kombinationsberechnung);
    sessionFactory.getCurrentSession().flush();
    sessionFactory.getCurrentSession().setFlushMode(flushModeOld);
    
    lilyPondRenderer.rendereKombinationenNachAkkordIdRange(1, 11, "");
    
  }

  @Test
  public void testBerechneUndRendereDreitonIntervalle() throws Exception
  {
    // Delete previous
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "3",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    FlushMode flushModeOld = sessionFactory.getCurrentSession().getFlushMode();
    sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
    sessionFactory.getCurrentSession().flush();
    assertNotNull("Kombinationsberechnung sollte gespeichert worden sein.", kombinationsberechnungService.getKombinationsBerechnung());
    int anzahlDreitonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    sessionFactory.getCurrentSession().flush();
    sessionFactory.getCurrentSession().setFlushMode(flushModeOld);
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertEquals("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", Integer.valueOf(3), Integer.valueOf(kombinationsberechnung.getBereitsBerechneteToene()));
    assertEquals("Genau 110 Klaenge sollten berechnet worden sein.", Integer.valueOf(110), Integer.valueOf(anzahlDreitonklaenge - 11));
    lilyPondRenderer.rendereKombinationenNachAkkordIdRange(AkkordIdRangeZwoelftonklaenge.minIdZuAnzahlToene(3), AkkordIdRangeZwoelftonklaenge.maxIdZuAnzahlToene(3), "");
  }

  @Test
  public void testBerechneUndRendereViertonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "4",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlViertonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    FlushMode flushModeOld = sessionFactory.getCurrentSession().getFlushMode();
    sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
    sessionFactory.getCurrentSession().flush();
    sessionFactory.getCurrentSession().setFlushMode(flushModeOld);
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 4 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 4);
    assertEquals("Genau 990 Klaenge sollten berechnet worden sein.", Integer.valueOf(990), Integer.valueOf(anzahlViertonklaenge - 110 - 11));
    lilyPondRenderer.rendereKombinationenNachAkkordIdRange(AkkordIdRangeZwoelftonklaenge.minIdZuAnzahlToene(4), AkkordIdRangeZwoelftonklaenge.maxIdZuAnzahlToene(4), "");
  }

  @Test
  public void testBerechneUndRendereFuenftonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "5",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlFuenftonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 5 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 5);
    assertEquals("Genau 9.031 Klaenge sollten berechnet worden sein.", Integer.valueOf(9031), Integer.valueOf(anzahlFuenftonklaenge));
    lilyPondRenderer.rendereKombinationenNachAkkordIdRange(AkkordIdRangeZwoelftonklaenge.minIdZuAnzahlToene(5), AkkordIdRangeZwoelftonklaenge.maxIdZuAnzahlToene(5), "");
  }

  //  @Test
  public void testBerechneUndRendereSechstonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "6",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlSechstonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 6 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 6);
    assertEquals("Genau 55.440 Klaenge sollten berechnet worden sein.", Integer.valueOf(55440), Integer.valueOf(anzahlSechstonklaenge));
  }

  //  @Test
  public void testBerechneUndRendereSiebentonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "7",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlSiebentonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 7);
    assertEquals("Genau 332.640 Klaenge sollten berechnet worden sein.", Integer.valueOf(332640), Integer.valueOf(anzahlSiebentonklaenge));
  }

  //  @Test
  public void testBerechneUndRendereAchttonIntervalle() throws Exception
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { CLI_PARAMETER_MAX_ANZAHL_TOENE, "8",
                                                                                                                     CLI_PARAMETER_DB_ERSTELLEN, "j",
                                                                                                                     CLI_PARAMETER_PERSISTENZ_LADEN, "n" });
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    int anzahlAchttonklaenge = akkordKombinationenService.berechneUndPersistiereKombinationsberechnung();
    kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertTrue("Wert für bereits berechnete Töne sollte auf 3 erhöht sein.", kombinationsberechnung.getBereitsBerechneteToene().intValue() == 8);
    assertEquals("Genau 1.663.200 Klaenge sollten berechnet worden sein.", Integer.valueOf(1663200), Integer.valueOf(anzahlAchttonklaenge));
  }

}