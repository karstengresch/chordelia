package org.gresch.quintett.renderer;

import java.io.File;
import java.util.List;

import org.gresch.quintett.domain.kombination.Akkordkombinationen;

/**
 * QuintettRenderer gibt die drei wesentlichen Schnittstellen für die Visualisierung der Akkordkombinationen vor.
 * 
 * @author Karsten
 * 
 */
public interface QuintettRenderer
{
  public final static String LILYPOND_RENDERER = "l";
  public final static String KONSOLEN_RENDERER = "k";
  public final static String MIDI_RENDERER = "m";
  public final static String LILYPOND_AND_MIDI_RENDERER = "lm";

  /**
   * Grundmethode: Sollte von allen Folgemethoden verwendet werden
   * 
   * @param minAkkordId
   * @param maxAkkordId
   * @param vollerPfadname
   * @param switchString TODO
   */
  public void rendereKombinationenNachAkkordIdRange(int minAkkordId, int maxAkkordId, String switchString);

  /**
   * Für unsortierte, unbestimmte Anzahl von Akkorden, normalerweise unterschiedlicher Tonanzahl.
   * @param vollerPfadname
   * @param switchString TODO
   * 
   */
  public void rendereKombinationenNachAkkordIdList(List<Integer> akkordIdList, String switchString);

  /**
   * Komfortmethode, die es ermöglicht, alle Akkorde mit der bestimmten Tonanzahl 
   * (z.B. Fünftonklänge) zu rendern.
   * @param vollerPfadname
   * @param switchString TODO
   * @param akkordIdList
   */
  public void rendereKombinationenFuerAnzahlToene(Integer anzahlToene, String switchString);

  /**
   * Komfortmethode, die es ermöglicht, alle Akkorde bis zu einer bestimmten Tonanzahl 
   * (z.B. ab Zwei- bis Fünftonklänge) zu rendern.
   * @param vollerPfadname
   * @param switchString TODO
   * @param akkordIdList
   */
  public void rendereKombinationenBisAnzahlToene(Integer maxAnzahlToene, String switchString);
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
