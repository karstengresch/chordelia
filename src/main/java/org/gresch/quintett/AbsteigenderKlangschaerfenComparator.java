package org.gresch.quintett;

import org.apache.log4j.Logger;
import org.gresch.quintett.domain.tonmodell.Akkord;

public class AbsteigenderKlangschaerfenComparator implements java.util.Comparator<Akkord>
{
  private static Logger _logger = Logger.getLogger(AbsteigenderKlangschaerfenComparator.class);

  public int compare(Akkord ersterAkkord, Akkord zweiterAkkord)
  {
    Integer anzahlToene = ersterAkkord.getAnzahlToene();
    if (zweiterAkkord.getAnzahlToene().compareTo(anzahlToene) == 0)
    {
      // TODO prüfen, ob zwei-Ton-Akkord, dann nach aesthetischer Gewichtung sortieren!
      if (anzahlToene > 2 && zweiterAkkord.getAnzahlToene() > 2)
      {
        if (!ersterAkkord.getKlangschaerfe().equals(zweiterAkkord.getKlangschaerfe()))
        {
          return (zweiterAkkord.getKlangschaerfe().compareTo(ersterAkkord.getKlangschaerfe()));
        }
        else
        {
          int i = 0;
          for (i = anzahlToene; i >= 1; i--)
          {
            Integer intervallErsterAkkord = 0 - ersterAkkord.getTonZuNummer(i).getAbstandZumEingestrichenenC();
            Integer intervallZweiterAkkord = 0 - zweiterAkkord.getTonZuNummer(i).getAbstandZumEingestrichenenC();
            Integer result = intervallZweiterAkkord.compareTo(intervallErsterAkkord);
            if (result != 0)
            {
              return result;
            }
            else if (i != 1)
            {
              continue;
            }
            // Sollte nur erreicht werden können, wenn letzte Schleife durchlaufen wird und alle Intervalle gleich waren. 
            _logger.warn("AbsteigenderKlangschaerfenComparator.compare(): Gleiche Akkorde zum Vergleich erhalten!!!");
            return result;
          }
        }
      }
      else
      // Zwei-Ton-Akkorde nur nach Klangschärfe sortieren.
      {
        // TODO Sortierung nach aesthetischer Gewichtung
        return zweiterAkkord.getKlangschaerfe().compareTo(ersterAkkord.getKlangschaerfe());
      }
    }
    else
    {
      _logger.warn("AbsteigenderKlangschaerfenComparator.compare(): Unterschiedliche Tonanzahl vorgefunden!");
      // TODO Prüfen - Exception oder zulassen oder wie folgend???
      return zweiterAkkord.getAnzahlToene().compareTo(ersterAkkord.getAnzahlToene());
    }
    throw new RuntimeException("Unbekannte Sortierungskonstellation vorgefunden!");
  }
}
