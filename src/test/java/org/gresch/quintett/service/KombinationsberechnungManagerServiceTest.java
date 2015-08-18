package org.gresch.quintett.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.kombination.AkkordIdRangeZwoelftonklaenge;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.junit.Before;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-main-test.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class KombinationsberechnungManagerServiceTest {
  private Log log = LogFactory.getLog(KombinationsberechnungManagerServiceTest.class);
  Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter
    .parameterAuswerten(new String[]{"-t", "3", "-db", "j", "-ps", "j", "-pl", "n"});



  @Resource(name = "kombinationsberechnungService")
  KombinationsberechnungService kombinationsberechnungService;

  @Resource(name = "kombinationsberechnungTestHelper")
  KombinationsberechnungTestHelper kombinationsberechnungTestHelper;

  @Before
  public void setup()
  {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'kombinationsberechnungService' sollte instantiiert sein.", kombinationsberechnungService);
    assertNotNull("Bean 'kombinationsberechnungTestHelper' sollte instantiiert sein.", kombinationsberechnungTestHelper);
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    assertTrue("Id der Kombinationsberechnung sollte 1 sein", kombinationsberechnung.getId().equals(Integer.valueOf(1)));

    //    kombinationsberechnungTestHelper.initialiseKombinationsberechnung();
    Kombinationsberechnung neueKombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
    assertEquals("Lokale und persistierte Kombinationsberechnung sollten gleich sein.", kombinationsberechnung, neueKombinationsberechnung);

  }


  //
  //  private static final  String CLI_PARAMETER_MAX_ANZAHL_TOENE =                  "t";
  //  private static final  String CLI_PARAMETER_SORTIERUNG_INTERVALLINFORMATIONEN = "si";
  //  private static final  String CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE =          "sk";
  //  private static final  String CLI_PARAMETER_SORTIERUNG_AUSGABE =                "su";
  //  private static final  String CLI_PARAMETER_GRUNDTON =                          "g";
  //  private static final  String CLI_PARAMETER_INTERVALLINFORMATIONEN =            "a";
  //  private static final  String CLI_PARAMETER_DEBUG =                             "d";
  //  private static final  String CLI_PARAMETER_RENDERER =                          "r";
  //  private static final  String CLI_PARAMETER_PERSISTENZ_LADEN =                  "pl";
  //  private static final  String CLI_PARAMETER_PERSISTENZ_SCHREIBEN =              "ps";
  //  private static final  String CLI_PARAMETER_DB_ERSTELLEN =                      "db";

  @Test
  public void testKombinationenBerechnen() {
    log.info("KombinationsberechnungManagerServiceTest.testKombinationenBerechnen()");
    //    kombinationsberechnungTestHelper.initialiseKombinationsberechnung();
    try {
      kombinationsberechnungService.kombinationenBerechnen();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Kombinationsberechnung kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();

    Integer maxIdErwartet = AkkordIdRangeZwoelftonklaenge.maxIdZuAnzahlToene(kombinationsberechnung.getMaxAnzahlToene());
    Integer letzteAkkordId = kombinationsberechnung.getLetzteAkkordId();
    assertEquals("Max. AkkordId berechnet sollte der theoretischen entsprechen.", maxIdErwartet, letzteAkkordId);
  }
  //
  //  @Test
  //  public void testKombinationenAusgeben()
  //  {
  //    fail("Not yet implemented"); // TODO
  //  }
  //
  //  @Test
  //  public void testVerzeichnisseVorbereiten()
  //  {
  //    fail("Not yet implemented"); // TODO
  //  }
  //
  //  @Test
  //  public void testGetKombinationsBerechnung()
  //  {
  //    fail("Not yet implemented"); // TODO
  //  }

}
