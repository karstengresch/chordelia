package org.gresch.quintett.renderer;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.Akkordkombinationen;
import org.gresch.quintett.domain.tonmodell.Akkord;


public class QuintettRendererConsoleImpl implements QuintettRenderer
{
  private static Log log = LogFactory.getLog(QuintettRendererConsoleImpl.class);
  public QuintettRendererConsoleImpl()
  {
    // Beany baby
  }

  public void rendereKombinationenInDatei(Akkordkombinationen akkordkombinationen, File file)
  {
    log.info("\n\n******* Akkordkombinationen.loggeKombinationen(): STARTE KONSOLEN-RENDERER! *******");
    // Ausgabe
    // TODO: Alle Akkorde loggen!
    // StringBuilder _stringBuilder = new StringBuilder(500);
    Integer akkordZaehler = 0;

    Set<Entry<Integer, TreeSet<Akkord>>> _akkordSet = akkordkombinationen.get().entrySet();

    for (Entry<Integer, TreeSet<Akkord>> entry : _akkordSet)
    {
      log.info("Ausgabe für " + entry.getKey().toString() + "-Ton-Klänge");
      log.info("Anzahl Kombinationen: " + _akkordSet.size());
      akkordZaehler++;
      for (Akkord akkord : entry.getValue())
      {
        if (log.isDebugEnabled())
        {
          log.debug("Akkord Nr.: " + akkordZaehler);
          log.debug(akkord.toString());
        }
      }
    }

  }

  @Override
  public void rendereKombinationenBisAnzahlToene(Integer maxAnzahlToene, String switchString)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rendereKombinationenFuerAnzahlToene(Integer anzahlToene, String switchString)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rendereKombinationenNachAkkordIdList(List<Integer> akkordIdList, String switchString)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rendereKombinationenNachAkkordIdRange(int minAkkordId, int maxAkkordId, String switchString)
  {
    // TODO Auto-generated method stub
    
  }


}
