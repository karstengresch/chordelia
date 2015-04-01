/*
 * Created on 11.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.gresch.quintett.domain.kombination;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.gresch.quintett.domain.tonmodell.Akkord;

/*
 * TODO: Persistenz: - herausfinden, wie man Anzahl begrenzt (worst case: eigene Numerierung) - herausfinden, welches Objekt am sinnvollsten
 *
 *
 */
/**
 * @author Karsten <br>
 *         Akkordkombinationen sind entsprechend einer ästhetischen Gewichtung sortierte, gültige (Tonverdopplungen vermeidende) Akkorde. Zur Nutzung einer
 *         AkkordKombination sind folgende Information notwendig:
 *         <ul>
 *         <li>Anzahl der Töne, aus denen die jeweiligen Akkorde bestehen sollen.</li>
 *         <li>Sortierung der ästhetischen Gewichtung (aufsteigend: spannungsreichste Töne zuletzt oder absteigend: spannungsreichste Töne zuerst.</li>
 *         <li>Der Basiston.</li>
 *         </ul>
 *         <br>
 *         <b>TODO</b> <br>
 *         <ul>
 *         <li>via DAO ansprechen.</li>
 *         <li>Datenhaltung von Berechnung (Hilfsklasse) trennen.</li>
 *         <li>Zu Singleton umwandeln - immer nur eine Berechnung zu einer Zeit... => ???</li>
 *         <li>ID über Counter setzen, nicht auto.</li>
 *         <li>Akkorde wieder freigeben und später aus der DB holen.</li>
 */
@Entity
@Table(name = "akkordkombinationen")
public class Akkordkombinationen
{
  private static Integer anzahlToene = 0;
  //  private static AesthetischeGewichtung aesthetischeGewichtung;
  //  private static BasisTon basisTon;
  // TODO ggf. eigenen Datentyp für Akkordkombinationen oder nur DAO?
  // Integer: Anzahl Töne; TreeSet: Akkorde
  // TODO Oder Hibernate-Collection-Mapping
  private static Map<Integer, TreeSet<Akkord>> akkordKombinationen = new TreeMap<Integer, TreeSet<Akkord>>();
  //  private final static Logger log = Logger.getLogger(Akkordkombinationen.class);
  //  // private static Ton _ton = null;
  //  private static StopWatch mainLoopStopWatch = new StopWatch();
  //  private static StopWatch cursorLoopStopWatch = new StopWatch();

  //  private KombinationsberechnungService kombinationsberechnungService = null;

  // private Boolean laden;// = kombinationsberechnungsInformationen.ladenAusDatenbankNuetzlich();
  @SuppressWarnings("unused")
  private Integer maxIdVonLetzerBerechnung = -1;
  @SuppressWarnings("unused")
  private Integer minIdFuerMaxAnzahlVonLetzerBerechnung = -1;
  @SuppressWarnings("unused")
  private Integer letzteBasisAkkordKlangschaerfe; // = kombinationsberechnungsInformationen.getLetzteBasisAkkordKlangschaerfe();
  //  private Integer letzteBasisAkkordIdInteger; // = kombinationsberechnungsInformationen.getLetzteBasisAkkordId();
  //  private Integer letzteAkkordIdInteger; // = kombinationsberechnungsInformationen.getLetzteAkkordId();
  @SuppressWarnings("unused")
  private Long anzahlBerechneterAkkordeLong; // = kombinationsberechnungsInformationen.getAnzahlBerechneterAkkorde();
  //  private Integer maxAnzahlToeneZuBerechnetenAkkordenInteger; // = kombinationsberechnungsInformationen.getMaxAnzahlAkkordToeneAusAkkorden();
  //  private Integer maxAkkordIdZuBasisAkkordId;
  //  private static Integer temporaereBasisAkkordKlangschaerfe = -1;
  //  private static Integer temporaereBasisAkkordId = -1;
  //  private static Integer temporaereAkkordId = -1;
  //  private Transaction transaction = null;

  // TODO inject

  /**
   * Default-Konstruktor. Die Anzahl der Töne wird angegeben, für die permutierte Akkorde berechnet werden sollen.
   *
   * @param anzahlToene
   */
  public Akkordkombinationen()
  {

  }

  // Zugriffsmethoden
  /**
   * @return Returns the anzahlToene.
   */
  public Integer getAnzahlToene()
  {
    return anzahlToene;
  }

  /**
   *
   * @return Map<Integer, TreeSet<Akkord>> - Alle Akkordkombinationen - Key gibt die Anzahl Töne im darauffolgenden TreeSet der Akkorde an.
   */
  public static Map<Integer, TreeSet<Akkord>> getAkkordKombinationen()
  {
    return akkordKombinationen;
  }

  /**
   *
   * @return Map<Integer, TreeSet<Akkord>> - Alle Akkordkombinationen - Key gibt die Anzahl Töne im darauffolgenden TreeSet der Akkorde an.
   */
  public Map<Integer, TreeSet<Akkord>> get()
  {
    return akkordKombinationen;
  }

  /**
   *
   * @return Map<Integer, TreeSet<Akkord>> - Alle Akkordkombinationen - Key gibt die Anzahl Töne im darauffolgenden TreeSet der Akkorde an.
   */
  public static TreeSet<Akkord> getAkkordeZuAnzahlToene(Integer xAnzahlToene)
  {
    return akkordKombinationen.get(xAnzahlToene);
  }

  //  @Override
  //  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
  //  {
  //    this.kombinationsberechnungService = (KombinationsberechnungService) applicationContext.getBean("kombinationsberechungService");
  //    this.kombinationsberechnung = kombinationsberechnungService.getKombinationsBerechnung();
  //
  //  }

}
