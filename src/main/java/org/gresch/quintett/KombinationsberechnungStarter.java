/**
 *
 */
package org.gresch.quintett;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.service.KombinationsberechnungService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Karsten
 *         <p>
 *         TODO Konstanten in Enums
 */
public class KombinationsberechnungStarter {

  public static PropertyConfigurator loggingConfiguration = null;
  // FIXME log4j2 verwenden.
  static Log log = LogFactory.getLog(KombinationsberechnungStarter.class);

  // TODO Static access! Ggf. noch springiger.
  //  private static Kombinationsberechnung kombinationsberechnung = null;
  private static StopWatch stoppuhr = new StopWatch();

  /**
   * @param args
   */
  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    stoppuhr.start();
    System.out.println(System.getProperty("user.dir"));
    loggingConfiguration.configureAndWatch("log4j.properties", 300000);
    log.info("KombinationsberechnungStarter.main(): Starte...");

    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(args);
    // TODO Arrays entfernen und durch Collections ersetzen
    // TODO Unit-Tests!
    if (log.isDebugEnabled()) {
      log.debug("kombinationsberechnung.main(): Starte...");
    }
    if (null == kombinationsberechnung.getBasisTon()) {
      kombinationsberechnung.setBasisTon(kombinationsberechnung.getDefaultBasisTon());
      log.info("KombinationsberechnungStarter: Default-Basiston erstellt!");
    }
    // Argumente der Kommandozeile auswerten.

    // Hier den Spring-Kontext laden und auf den Berechnungsmanagementservice referieren.
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-main.xml");

    KombinationsberechnungService kombinationsberechnungService = (KombinationsberechnungService) applicationContext
      .getBean("kombinationsberechnungService");
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
    kombinationsberechnungService.verzeichnisseVorbereiten();

    try {
      kombinationsberechnungService.kombinationenBerechnen();
      //      kombinationsberechnungService.kombinationenAusgeben();
    } catch (Exception e) {
      log.error("Kombinationsberechnung-Exception: ", e);
    }

    stoppuhr.stop();
    log.info("kombinationsberechnung.main(): Programmausführung beendet. Verbrauchte Zeit: " + (stoppuhr.getTime() / 1000) + " Sekunden.");
    System.exit(0);
    // TODO Bericht über ausgesparte Akkorde
  }

  /**
   * @param options
   */
  static void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("Kombinationsberechnung -a [-s -g -t]", options);
  }
}
