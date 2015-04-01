package org.gresch.quintett.domain.kombination;

import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_DB_ERSTELLEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_GRUNDTON;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_INTERVALLINFORMATIONEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_MAX_ANZAHL_TOENE;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_PERSISTENZ_LADEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_PERSISTENZ_SCHREIBEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_RENDERER;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_SORTIERUNG_AUSGABE;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_SORTIERUNG_INTERVALLINFORMATIONEN;
import static org.gresch.quintett.KombinationsberechnungParameter.CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE;
import static org.junit.Assert.assertTrue;

import org.gresch.quintett.BasisTon;
import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.renderer.QuintettRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
public class KombinationsberechnungParameterTest
{

  @Test
  public void testSetup()
  {
    assertTrue("Spring-Konfiguration sollte geladen werden können", true);
  }

  @Test
  public void testDefaultValues()
  {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] { "" });
    // unlogisch
    assertTrue("Defaultwert für DB erstellen sollte gesetzt sein.", kombinationsberechnung.getHatDbErstellen() == KombinationsberechnungParameter.CLI_DEFAULTWERT_DB_ERSTELLEN.equalsIgnoreCase("n") ? false : true);
    assertTrue("Defaultwert für max. Anzahl Toene sollte gesetzt sein.", kombinationsberechnung.getMaxAnzahlToene() == Integer.valueOf(KombinationsberechnungParameter.CLI_DEFAULTWERT_MAX_ANZAHL_TOENE).intValue());
    assertTrue("Defaultwert für absteigende Klangschaerfesortierung sollte gesetzt sein.", kombinationsberechnung.getHatAbsteigendeKlangschaerfe() == (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_KLANGSCHAERFE.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für absteigende Intervallinformationen sollte gesetzt sein.", kombinationsberechnung.getHatAbsteigendeIntervallinformationen() == (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_INTERVALLINFORMATIONEN.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für absteigende Ausgabe sollte gesetzt sein.", kombinationsberechnung.getHatAbsteigendeAusgabe() == (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_AUSGABE.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für Intervallinformationen sollte gesetzt sein.", kombinationsberechnung.getIntervallInformationen().equals(KombinationsberechnungParameter.CLI_DEFAULTWERT_INTERVALLINFORMATIONEN));
    assertTrue("Defaultwert für Basiston sollte gesetzt sein.", kombinationsberechnung.getBasisTon().equals(new Ton(Oktavlage.GROSZE, KombinationsberechnungParameter.CLI_DEFAULTWERT_GRUNDTON)));
    assertTrue("Defaultwert für Renderer sollte gesetzt sein.", kombinationsberechnung.getRenderer().equals(KombinationsberechnungParameter.CLI_DEFAULTWERT_RENDERER));
    assertTrue("Defaultwert für Persistenz laden sollte gesetzt sein.", kombinationsberechnung.getHatPersistenzLaden() == KombinationsberechnungParameter.CLI_DEFAULTWERT_PERSISTENZ_LADEN.equalsIgnoreCase("j") ? true : false);
    assertTrue("Defaultwert für Persistenz schreiben sollte gesetzt sein.", kombinationsberechnung.getHatPersistenzSchreiben() == KombinationsberechnungParameter.CLI_DEFAULTWERT_PERSISTENZ_SCHREIBEN.equalsIgnoreCase("j") ? true : false);
  }

  @Test
  public void testOverrideAllDefaultValues()
  {

    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[] {

    CLI_PARAMETER_DB_ERSTELLEN, "j", CLI_PARAMETER_GRUNDTON, "H", CLI_PARAMETER_INTERVALLINFORMATIONEN, "10,02,07,01,04,11,06,05,03,08,09", CLI_PARAMETER_MAX_ANZAHL_TOENE, "4", CLI_PARAMETER_PERSISTENZ_LADEN, "n", CLI_PARAMETER_PERSISTENZ_SCHREIBEN, "n", CLI_PARAMETER_RENDERER, QuintettRenderer.MIDI_RENDERER,
                                                                                                                     CLI_PARAMETER_SORTIERUNG_AUSGABE, "n", CLI_PARAMETER_SORTIERUNG_INTERVALLINFORMATIONEN, "n", CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE, "n" });

    assertTrue("Defaultwert für DB erstellen sollte überschrieben worden sein.", kombinationsberechnung.getHatDbErstellen() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_DB_ERSTELLEN.equalsIgnoreCase("n") ? false : true));
    assertTrue("Defaultwert für max. Anzahl Toene sollte überschrieben worden sein.", kombinationsberechnung.getMaxAnzahlToene() != Integer.valueOf(KombinationsberechnungParameter.CLI_DEFAULTWERT_MAX_ANZAHL_TOENE).intValue());
    assertTrue("Defaultwert für absteigende Klangschaerfesortierung sollte überschrieben worden sein.", kombinationsberechnung.getHatAbsteigendeKlangschaerfe() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_KLANGSCHAERFE.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für absteigende Intervallinformationen sollte überschrieben worden sein.", kombinationsberechnung.getHatAbsteigendeIntervallinformationen() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_INTERVALLINFORMATIONEN.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für absteigende Ausgabe sollte überschrieben worden sein.", kombinationsberechnung.getHatAbsteigendeAusgabe() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_SORTIERUNG_AUSGABE.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für Intervallinformationen sollte überschrieben worden sein.", kombinationsberechnung.getIntervallInformationen().equals("10,02,07,01,04,11,06,05,03,08,09"));
    assertTrue("Defaultwert für Basiston sollte überschrieben worden sein.", kombinationsberechnung.getBasisTon().equals(new Ton(Oktavlage.GROSZE, "H")));
    assertTrue("Defaultwert für Renderer sollte überschrieben worden sein.", kombinationsberechnung.getRenderer().equals(QuintettRenderer.MIDI_RENDERER));
    assertTrue("Defaultwert für Persistenz laden sollte überschrieben worden sein.", kombinationsberechnung.getHatPersistenzLaden() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_PERSISTENZ_LADEN.equalsIgnoreCase("j") ? true : false));
    assertTrue("Defaultwert für Persistenz schreiben sollte überschrieben worden sein.", kombinationsberechnung.getHatPersistenzSchreiben() != (KombinationsberechnungParameter.CLI_DEFAULTWERT_PERSISTENZ_SCHREIBEN.equalsIgnoreCase("j") ? true : false));

  }

  @Test
  public void testIncompleteAesthetischeGewichtung()
  {

  }

  @Test
  public void testWrongValues()
  {

  }

}
