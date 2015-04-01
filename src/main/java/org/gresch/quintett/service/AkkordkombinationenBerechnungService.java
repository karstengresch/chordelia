package org.gresch.quintett.service;

import java.util.List;

import org.gresch.quintett.domain.kombination.AesthetischeGewichtung;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.domain.tonmodell.Ton;

public interface AkkordkombinationenBerechnungService
{

  // FIXME Wenn es nur berechnen ist, sollte die Bezeichnung so bleiben, ansonsten berechneUndSpeichereKombinationen

  /**
   * <p>
   * Berechnet für eine fortlaufende Reihe von Akkord-IDs (z.B. 101,102,103...202) die
   * dazugehörigen Akkordkombinationen. D.h., die entsprechenden Akkorde sind die Basisakkorde.
   * </p>
   * <p>
   * Sollte keine ästhetische Gewichtung vorher festgelegt worden sein, wird
   * der entsprechende Default verwendet!
   * <br />Siehe auch {@link #berechneUndPersistiereKombinationen(int, int, int, AesthetischeGewichtung)}.

   * </p>
   *
   * @param basisAkkkordIdStart Niedrigste Akkord-Id für die jeweilige Reihe.
   * @param basisAkkordIdEnde Höchste Akkord-Id.
   * @param maxAnzahlToene Anzahl der Töne, bis zu denen Akkorde berechnet werden sollen.
   * Muss zwischen 3 und 11 liegen.
   * @throws Exception
   */
  int berechneUndPersistiereNachBasisAkkordBlock(int basisAkkkordIdStart, int basisAkkordIdEnde, int incrementorToene, int lastAkkordId) throws Exception;

  /**
   * Siehe Hinweise zu {@link #berechneUndPersistiereKombinationen(int, int, int)}. Diese Methode unterscheidet
   * sich dadurch, dass man die ästhetische Gewichtung direkt übergeben kann.
   *
   * @param akkordIdStart
   * @param akkordIdEnde
   * @param maxAnzahlToene
   * @param AesthetischeGewichtung
   * @throws Exception
   */
//  int berechneUndPersistiereKombinationen(int akkordIdStart, int akkordIdEnde, int maxAnzahlToene, AesthetischeGewichtung AesthetischeGewichtung) throws Exception;

  /**
   * Transponiert einen Akkord entsprechend dem angegebebeb Intervall
   * @param xTonList
   * @param xTranspositionsIntervall Das Intervall, um das transponiert werden soll.
   * @return
   */
  List<Ton> transponiere(List<Ton> xTonList, Integer xTranspositionsIntervall);

  /**
   * Berechnet die initialen Zweitonklänge.
   * @return
   * @throws Exception
   */
  public int runIncrementorToeneZwei() throws Exception;


  /**
   * Berechnet die Akkordkombinationen anhand der gewünschten Anzahl an Tönen. Geht von vorhandenen
   * Kombinationsberechnungsinformationen aus. Ggf. wird der Default genommen.
   * @param anzahlToene
   */
//  void berechneUndPersistiereKombinationen(int anzahlToene);

}
