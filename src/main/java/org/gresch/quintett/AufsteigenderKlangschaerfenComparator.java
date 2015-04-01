package org.gresch.quintett;

import org.apache.log4j.Logger;
import org.gresch.quintett.domain.tonmodell.Akkord;

/**
 * Dient der Sortierung der Akkorde. Allgemeine Regeln sind: Akkorde mit mehr Tönen kommen <b>nach</b> Akkorden mit
 * weniger Tönen. Akkorde gleicher Tonzahl mit h�herer Klangschärfe kommen <b>nach</b> solchen mit niedrigerer
 * Klangschärfe.<br>
 * Sortiert Akkorde aufsteigend, d.h. Akkorde gleicher Klangschärfe und Tonanzahl gelten als klangschärfer, wenn sich
 * das klangschärfere Intervall näher dem Grundton befindet.
 *   
 * @author Karsten
 *
 */
public class AufsteigenderKlangschaerfenComparator implements java.util.Comparator<Akkord>
{
  private static Logger _logger = Logger.getLogger(AufsteigenderKlangschaerfenComparator.class);

  public int compare(Akkord ersterAkkord, Akkord zweiterAkkord)
  {
    Integer anzahlToene = ersterAkkord.getAnzahlToene();
    if (anzahlToene.compareTo(zweiterAkkord.getAnzahlToene()) == 0)
    {

      // TODO prüfen, ob zwei-Ton-Akkord, dann nach aesthetischer Gewichtung sortieren!
      if (anzahlToene > 2 && zweiterAkkord.getAnzahlToene() > 2)
      {

        if (!ersterAkkord.getKlangschaerfe().equals(zweiterAkkord.getKlangschaerfe()))
        {
          return (ersterAkkord.getKlangschaerfe().compareTo(zweiterAkkord.getKlangschaerfe()));
        }
        else
        {
          // TODO !!!
          /* 
            So soll der Algorithmus funktionieren
            - Suche ersten Intervallunterschied und ermittle Klangschärfe anhand
              der Position entspr. der �sthetischen Gewichtung.
            - 
            - Dann sortiere nach Gewichtung.        
          int result = -999;
          
          */
          //        Integer i = 0;
          //        Integer zwischenWertAkkord1 = 0;
          //        Integer zwischenWertAkkord2 = 0;
          //        for(i = 1; i <= anzahlToene ; i++)
          //        {
          //          zwischenWertAkkord1 += Kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(ersterAkkord.getTonZuNummer(i).getAbstandZumBasisTon());
          //          zwischenWertAkkord2 += Kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(zweiterAkkord.getTonZuNummer(i).getAbstandZumBasisTon());
          //        }
          Integer intervallErsterAkkord = ersterAkkord.getTonZuNummer(anzahlToene).getAbstandZumEingestrichenenC() - ersterAkkord.getTonZuNummer(anzahlToene - 1).getAbstandZumEingestrichenenC();
          Integer intervallZweiterAkkord = zweiterAkkord.getTonZuNummer(anzahlToene).getAbstandZumEingestrichenenC() - zweiterAkkord.getTonZuNummer(anzahlToene - 1).getAbstandZumEingestrichenenC();
          Integer result = intervallErsterAkkord.compareTo(intervallZweiterAkkord);
          if (result == 0)
          {
            _logger.warn("AufsteigenderKlangschaerfenComparator.compare(): Gleiche Akkorde zum Vergleich erhalten!!!");
          }
          return result;
        }
      }
      else
      {
        // TODO Sortierung nach aesthetischer Gewichtung
        return ersterAkkord.getKlangschaerfe().compareTo(zweiterAkkord.getKlangschaerfe());

      }
    }
    else
    {
      _logger.warn("AufsteigenderKlangschaerfenComparator.compare(): Unterschiedliche Tonanzahl vorgefunden!");
      // TODO Prüfen - Exception oder zulassen oder wie folgend???
      return ersterAkkord.getAnzahlToene().compareTo(zweiterAkkord.getAnzahlToene());
    }
  }

}
