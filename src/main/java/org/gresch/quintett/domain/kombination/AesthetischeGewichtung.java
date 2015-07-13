/*
 * Created on 11.01.2005 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package org.gresch.quintett.domain.kombination;

import org.apache.log4j.Logger;
import org.gresch.quintett.service.KombinationsberechnungService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Karsten
 *         Gibt die Gewichtung der einzelnen Intervalle in der übergebenen
 *         Reihenfolge an, wobei der höchsten Stufe die höchste
 *         Dissonanzempfindung entspricht.
 *         ??? -> Je nach aufsteigend/absteigend.
 */
@Entity
@Table(name = "aesthetische_gewichtung")
public class AesthetischeGewichtung implements ApplicationContextAware {

  @ElementCollection
  @MapKeyColumn(name = "klangschaerfe")
  @Column(name = "gewichtung_klangschaerfe_intervall")
  // @CollectionTable(name="gewichtung_klangschaerfe_intervall", joinColumns=@JoinColumn(name="id"))
  private static Map<Integer, Integer> gewichtungKlangschaerfeIntervall = new HashMap<Integer, Integer>();
  /**
   * Erster Integer/Key: Sortierung/Reihenfolge
   * Zweiter Integer/Value: Intervall
   */
  @ElementCollection
  @MapKeyColumn(name = "sortierung")
  @Column(name = "gewicht_sortierung_intervall")
  private static Map<Integer, Integer> gewichtungSortierungIntervall = new HashMap<Integer, Integer>();
  private static Logger _logger = Logger.getLogger(AesthetischeGewichtung.class);
  private static String gewichtungsString = "";

  // Map und nicht Set, um Klangschärfe den Intervallen zuordnen zu können.
  /**
   * Erster Integer/Key: Klangschärfe
   * Zweiter Integer/Value: Intervall
   */
  //@ElementCollection
  // @MapKeyJoinColumn
  private Kombinationsberechnung kombinationsberechnung = null;
  // see getter!
  private Integer id;

  public AesthetischeGewichtung() {
    // leer
  }

  public AesthetischeGewichtung(String intervallinformationenString,
                                Kombinationsberechnung xKombinationsberechnung) {
    this.kombinationsberechnung = xKombinationsberechnung;
    gewichtungsStringAuswerten(intervallinformationenString);

  }

  public static Map<Integer, Integer> getGewichtungSortierung() {
    return gewichtungSortierungIntervall;
  }

  private void gewichtungsStringAuswerten(String intervallinformationenString) {
    gewichtungsString = intervallinformationenString;
    // TODO hier auch schon Array-Länge

    String[] _stringArray;
    _stringArray = intervallinformationenString.split(",");

    if (_stringArray.length != 11) {
      _logger.error("AesthetischeGewichtung{}: Nicht genau 11 Intervalle angegeben!");
      System.exit(-1);
    }
    int[] _intArray;
    _intArray = new int[11];

    for (int i = 0; i < 11; i++) {
      if (_logger.isDebugEnabled()) {
        _logger.debug("AesthetischeGewichtung{}: Intervall: " + _stringArray[i]);
      }
      _intArray[i] = Integer.parseInt(_stringArray[i]);
    }

    // gewichtungKlangschaerfeIntervall = new TreeMap<Integer, Integer>();
    // gewichtungSortierungIntervall = new TreeMap<Integer, Integer>();
    werteEinbinden(_intArray);
  }

  // public String getGewichtungsString() {
  //    return gewichtungsString;
  //  }

  private void werteEinbinden(int[] xGewichtung) {
    // Array in die "echte Welt der Objekte" mappen.
    // Wichtig: Gewichtung darf später nicht mehr verändert werden.
    int i = -1;
    int ii = 0;

    if (kombinationsberechnung.getHatAbsteigendeIntervallinformationen() == true) {
      ii = 10;
      for (i = 1; i < 12; i++) {
        gewichtungKlangschaerfeIntervall.put(new Integer(i), new Integer(xGewichtung[ii]));
        gewichtungSortierungIntervall.put(new Integer(i), new Integer(xGewichtung[i - 1]));
        ii--;
      }
    } else     // Erstes Intervall hat die niedrigste Klangschärfe
    {
      for (i = 0; i < 11; i++) {
        gewichtungKlangschaerfeIntervall.put(new Integer(i + 1), new Integer(xGewichtung[i]));
        // Sortierung ist gleich Klangschärfe 1 Sortierung = 1 Klangschärfe
        gewichtungSortierungIntervall.put(new Integer(i + 1), new Integer(xGewichtung[i]));
      }
    }
  }

  // TODO Typesafe!

  public String toString() {
    return gewichtungsString + " - " + (kombinationsberechnung.getHatAbsteigendeIntervallinformationen() ? "absteigend" : "aufsteigend");
  }

  /**
   * @param xIntervall Das Intervall, für das die Klangschärfe angegeben werden soll. Z.B. 1 für kl. Sekunde oder 11 für große Septime.
   */
  public Integer getKlangschaerfe(Integer xIntervall) {

    Set<Entry<Integer, Integer>> gewichtungEntrySet = gewichtungKlangschaerfeIntervall.entrySet();
    Integer returnValue = 0;

    if (xIntervall < 0 || xIntervall > 12) {
      if (xIntervall < 0) {
        xIntervall *= -1;
      }
      xIntervall = xIntervall % 12;
    }

    if (xIntervall == 0 || xIntervall == 12) {
      return 0;
    }
    // TODO Ginge das nicht auch statisch? Das sollte doch ohnehin einmalig in einer Map vorliegen??? Weshalb die Schleife?
    for (Entry<Integer, Integer> entry : gewichtungEntrySet) {
      Integer key = entry.getKey();
      Integer value = entry.getValue();
      if (value.compareTo(xIntervall) == 0) {
        returnValue = key;
        break;
      } else {
        // TODO Exception!
        //return -1;
      }
    }

    return returnValue;
  }

  public int getSize() {

    try {
      if (gewichtungKlangschaerfeIntervall.size() != gewichtungSortierungIntervall.size()) {
        throw new Exception("Die Größe der ästhetischen Gewichtung wurde falsch berechnet. Bitte den Quellcode ändern! \nDas Programm endet jetzt.");
      }

    } catch (Exception e) {
      System.exit(-1);
    }

    return gewichtungSortierungIntervall.size();
  }

  // TODO XXX annoying
  public void setSize(int xSize) {
    // nothing
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // @GeneratedValue
  @Column(name = "id")
  public java.lang.Integer getId() {
    if (null == id) {
      id = 1;
    }
    return id;
  }

  public void setId(java.lang.Integer xId) {

    id = xId;
  }

  @Override
  @PostConstruct
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.kombinationsberechnung = ((KombinationsberechnungService) applicationContext.getBean("kombinationsberechnungService")).getKombinationsBerechnung();
  }

}