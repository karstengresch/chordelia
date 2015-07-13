package org.gresch.quintett.service;

import org.gresch.quintett.domain.tonmodell.Akkord;
import org.hibernate.ScrollableResults;

import java.util.List;
import java.util.Set;

public interface AkkordKombinationenService {
  //  /**
  //   * Erstellt die Basisakkordkombinationen. Wenn zum zweiten Mal aufgerufen, wird eine Fehlermeldung
  //   * im sysout ausgegeben und der Rückgabewert ist der von {@link #getAkkordKombinationenZuBasisAkkord()}
  //   * Alle Parameter können null sein. Für jeden einzelnen wird im sysout angezeigt, welcher Defaultwert
  //   * verwendet wird.
  //   * @param xAnzahlToene Die Anzahl an Tönen, für die Akkordkombinationen erstellt werden sollen. Defaultwert wird angenommen, wenn null.
  //   * @param xAesthetischeGewichtung Die ästhetische Gewichtung. Wenn null, wird ein Defaultwert angenommen.
  //   * @param xBasisTon Der Basiston. Defaultwert wird angenommen.
  //   * @return Die Referenz auf die aktuellen Akkordkombinationen.
  //   */
  //  Akkordkombinationen createAkkordKombinationen(Integer xAnzahlToene, AesthetischeGewichtung xAesthetischeGewichtung, BasisTon xBasisTon);

  /**
   * Ermittelt die bereits berechneten Akkordkombinationen zum übergebenen Basisakkord.
   *
   * @return
   */
  Set<Akkord> getAkkordkombinationenZuBasisAkkord(Akkord basisAkkord);

  ScrollableResults getScrollableResultByBasisAkkordRange(Integer minBlockId, Integer maxBlockId, int fetchBlockGroesze, boolean absteigend);

  /**
   * Berechnet die Akkordkombinationen anhand der Angaben für die Kombinationsberechnung.
   *
   * @throws Exception
   */
  int berechneUndPersistiereKombinationsberechnung() throws Exception;
  //  int getAnzahlToeneBasisAkkordByAkkordId(int akkordIdStart);

  Akkord getAkkordById(Integer akkordId);

  List<Integer> getAkkordIdsByAnzahlToene(int anzahlToene, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend);

  List<Integer> getAkkordIdsByRange(int minId, int maxId, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend);
}
