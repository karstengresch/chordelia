package org.gresch.quintett.renderer;

import java.util.List;

/**
 * QuintettRenderer gibt die drei wesentlichen Schnittstellen für die Visualisierung der Akkordkombinationen vor.
 *
 * @author Karsten
 */
public interface QuintettRenderer {
  String LILYPOND_RENDERER = "l";
  String KONSOLEN_RENDERER = "k";
  String MIDI_RENDERER = "m";
  String LILYPOND_AND_MIDI_RENDERER = "lm";

  /**
   * Grundmethode: Sollte von allen Folgemethoden verwendet werden
   *
   * @param minAkkordId
   * @param maxAkkordId
   * @param switchString   TODO
   */
  void rendereKombinationenNachAkkordIdRange(int minAkkordId, int maxAkkordId, String switchString);

  /**
   * Für unsortierte, unbestimmte Anzahl von Akkorden, normalerweise unterschiedlicher Tonanzahl.
   *
   * @param switchString   TODO
   */
  void rendereKombinationenNachAkkordIdList(List<Integer> akkordIdList, String switchString);

  /**
   * Komfortmethode, die es ermöglicht, alle Akkorde mit der bestimmten Tonanzahl
   * (z.B. Fünftonklänge) zu rendern.
   *
   * @param switchString   TODO
   */
  void rendereKombinationenFuerAnzahlToene(Integer anzahlToene, String switchString);

  /**
   * Komfortmethode, die es ermöglicht, alle Akkorde bis zu einer bestimmten Tonanzahl
   * (z.B. ab Zwei- bis Fünftonklänge) zu rendern.
   *
   * @param switchString   TODO
   */
  void rendereKombinationenBisAnzahlToene(Integer maxAnzahlToene, String switchString);
  // /**
  // * Rendere die Kombinationen. Das 'Wohin' (z.B. Konsole) ist dabei gleichgültig.
  // * @param xAkkordKombinationen
  // */
  // public void rendereKombinationen(Akkordkombinationen xAkkordKombinationen);
  // /**
  // * Rendere die Kombinationen in ein File-Objekt und gib dieses zurück. Achtung! Kann ggf. <b>null</b> zurückgeben!
  // * @param xAkkordKombinationen
  // */
  // public File rendereKombinationenInDatei(Akkordkombinationen xAkkordKombinationen);
  // /**
  // * Rendere die Kombinationen in das übergebene File-Objekt.
  // * @param xAkkordKombinationen
  // * @param xFile
  // */
  // public void rendereKombinationenInDatei(Akkordkombinationen xAkkordKombinationen, File xFile);
}
