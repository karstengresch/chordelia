package org.gresch.quintett.service.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.AesthetischeGewichtung;
import org.gresch.quintett.domain.tonmodell.Akkord;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.service.AkkordkombinationenBerechnungServiceImpl;

import java.util.LinkedList;
import java.util.List;

public final class AkkordkombinationenBerechnungServiceHelper {
  private static final Log log = LogFactory.getLog(AkkordkombinationenBerechnungServiceHelper.class);

  // XXX Check static
  public static boolean istEinErlaubterAkkord(Akkord xAkkord) {
    boolean _erlaubt = true;
    int _anzahlToene = xAkkord.getAnzahlToene();
    // log.debug(xAkkord.toString());
    int _ersterTonAbstandZumEingestrichenenC;
    int _zweiterTonAbstandZumEingestrichenenC;
    int _tonDifferenz;
    int i = 0;
    int ii = 0;
    for (i = 0; i < _anzahlToene; i++) {
      _ersterTonAbstandZumEingestrichenenC = xAkkord.getTonList().get(i).getAbstandZumEingestrichenenC().intValue();
      for (ii = i + 1; ii < _anzahlToene; ii++) {
        _zweiterTonAbstandZumEingestrichenenC = xAkkord.getTonList().get(ii).getAbstandZumEingestrichenenC().intValue();
        _tonDifferenz = _zweiterTonAbstandZumEingestrichenenC - _ersterTonAbstandZumEingestrichenenC;

        if (_tonDifferenz % 12 == 0) {
          if (AkkordkombinationenBerechnungServiceImpl.log.isDebugEnabled()) {
            AkkordkombinationenBerechnungServiceImpl.log.debug("******* A~K~.istEinErlaubterAkkord(): Oktavverdopplung gefunden (s.o.)! *******");
          }
          return false;
        }
      }
    }
    return _erlaubt;
  }

  public static int getKlangschaerfe(List<Ton> xTonList, AesthetischeGewichtung xAesthetischeGewichtung) {
    int klangschaerfe = 0;

    List<Ton> tonList = new LinkedList<Ton>();
    int i = 0;
    // TODO Fehlerhaft!
    int abstandZumNaechstenTon = 0;
    for (i = 0; i < xTonList.size(); i++) {
      tonList.add(xTonList.get(i));

      if (i == 0) {
        abstandZumNaechstenTon = tonList.get(i).getAbstandZumEingestrichenenC();
        // TODO Ästhetische Gewichtung von Kombinationsberechnung unabhängig machen.
        klangschaerfe += xAesthetischeGewichtung.getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC());
      } else {
        abstandZumNaechstenTon += tonList.get(i).getAbstandZumEingestrichenenC();
        // Auf/Ab!
        // TODO Zwischenvariablen!

        klangschaerfe += xAesthetischeGewichtung
          .getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1).getAbstandZumEingestrichenenC());
        if (log.isDebugEnabled()) {
          log.debug("Akkord.Akkord(): Klangschärfe für Abstand " + (tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1)
            .getAbstandZumEingestrichenenC()) + " | Hinzugefügt: "
            + xAesthetischeGewichtung
            .getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1).getAbstandZumEingestrichenenC()));
          log.debug("Akkord.Akkord(): Klangschärfe insgesamt: " + klangschaerfe);
        }
      }
    }
    tonList = null;
    return klangschaerfe;

  }

}
