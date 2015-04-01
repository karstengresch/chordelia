package org.gresch.quintett.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
public class KombinationsberechnungDaoTest
{
  @Resource(name = "kombinationsberechnungDao")
  KombinationsberechnungDao kombinationsberechnungDao;

  @Test
  public void testSetup()
  {
    assertTrue(true);
    assertNotNull(kombinationsberechnungDao);
  }

  //   @Test
  public void testGetAnzahlBerechneterAkkorde()
  {
    // TODO Berechne einige Akkorde (mitzählen), dann überprüfen, ob in DB

  }

  //  @Test
  public void testGetAnzahlBerechneterAkkordeZuAnzahlAkkordToene()
  {
    // TODO Wenigstens bis 4-Ton-Klänge hochberechnene und mit den statischen Zahlen vergleichen.
  }

  //  @Test
  public void testGetMaxAnzahlAkkordToeneAusBerechnungsInformation()
  {
    // TODO Erst einmal ermitteln, was die Methode selbst soll.
  }

  //  @Test
  public void testGetMaxAnzahlAkkordToeneAusAkkorden()
  {
    // TODO Erst einmal ermitteln, was die Methode selbst soll.
  }

  //  @Test
  public void testGetMaxIdZuAnzahlAkkordToene()
  {
    // TODO Erst zu einer bestimmten Anzahl Töne berechnen lassen, abbrechen, letzte Id merken und mit KombinationsberechnungsInformation-Wert vergleichen.
  }

  //  @Test
  public void testGetMinIdZuAnzahlAkkordToene()
  {
    //    TODO Erst zu einer bestimmten Anzahl Töne berechnen lassen, abbrechen, letzte Id merken und mit KombinationsberechnungsInformation-Wert vergleichen.
  }

  //  @Test
  //  public void testUpdateKombinationsberechnung()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetBerechnungsId()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetLetzteBasisAkkordKlangschaerfe()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetLetzteAkkordId()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetLetzteBasisAkkordId()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetMaxAkkordIdZuBasisAkkordId()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  @Test
  //  public void testGetKombinationsberechnung()
  //  {
  //    fail("Not yet implemented");
  //  }

}
