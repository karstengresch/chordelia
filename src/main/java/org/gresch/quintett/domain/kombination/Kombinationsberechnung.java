package org.gresch.quintett.domain.kombination;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.BasisTon;
import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.tonmodell.Ton;

import javax.persistence.*;

/**
 * @author Karsten Führt die Berechnung der möglichen Kombinationen durch. Ausgabe je nach übergebenem Renderer. <br>
 *         Notationshinweis: _[Variablenname, z.B. _ton] weist auf ein Feld/Member hin (mind. mit Getter versehen). <br>
 *         TODO Lilypond-Einbindung - Java 1.6 native verwenden. "Headless" mode in Lilypond prüfen.<br>
 *         TODO Pdf-Erstellung.<br>
 *         TODO Enharmonisch transponieren, um möglichst mehrere Noten auf einer Linie/Stelle zu vermeiden.<br>
 *         TODO Auflösungszeichen, wenn in vorhergegangenem Akkord Vorzeichen auftrat. => LilyPond Einstellung?<br>
 *         TODO Sortierung LilypondRenderer/Comparator: Weshalb folgt bei gleicher Klangschärfe und absteigender Sortierung nicht der Ton mit der höheren
 *         Klangschärfe nicht zuerst?<br>
 *         TODO Numerierung LilypondRenderer ist nicht sehr schön: Zahl kleiner, besser noch Zähler (absteigend) nach Klangschärfe.<br>
 *         TODO Absteigende Sortierung: Vorher prüfen, inwiefern originale Permutation einen anderen Algorithmus hatte, in jedem Fall basierte die Sortierung
 *         auf der Summierung aller Intervalle, dann folgte eine weitere Sortierung. <br>
 *         TODO Gesondertes Flag für die Ausgabe der Sortierung (klangschärfste zuerst/zuletzt).<br>
 *         TODO Option für Ausgabe: Für jede Anzahl Töne separate Datei oder eine Datei?<br>
 *         TODO Speicherung in DB - bevorzugt db4o.<br>
 *         TODO Bei Persistenzlösung auch die Möglichkeit, unterschiedliche Sortierungen zu bilden, ohne Ergebnisse anzutasten ("views").<br>
 *         TODO Ansicht in extra Programm mit klingendem Beispiel.<br>
 *         TODO Prüfen, ob es nicht sinnvoller ist, den gesamten Tonvorrate als Töne zu speichern und Gleichheit etc. anhand des Tonvorrats zu ermitteln. -> Ja!<br>
 *         TODO Arrays zu Collections, mehr OO -> "Nur" noch TonArray: Sollte Set werden.<br>
 *         TODO Option, auch "ungültige" Akkorde aufzunehmen.<br>
 *         TODO Konfiguration mit java.util.prefs<br>
 *         TODO Lilypond: Ab Nr. 301 falsche Aufteilung in Systemen!<br>
 *         TODO Einheitliche Unterscheidung Member/Blockvariable - Unterstrichkonventionen (GNU?) anschauen.<br>
 *         TODO Aufräumen: Variablennamen (s.o.), Checkstyle.<br>
 *         TODO Konsolenausgabe explizit unterdrücken, ansonsten immer zusätzlich.<br>
 */
@Entity
@Table(name = "kombinationsberechnung")
@Access(AccessType.FIELD)
public class Kombinationsberechnung {
  @Transient
  public final static Log log = LogFactory.getLog(Kombinationsberechnung.class);
  @Transient
  public final static String S = System.getProperty("file.separator");
  @Transient
  public final static String USER_DIR = System.getProperty("user.dir");
  // TODO change temporary directory pfad to local application path default + parameterizeable
  @Transient
  public final static String AUSGABEPFAD = USER_DIR + (USER_DIR.endsWith(S) ? "" : S) + "target" + S + "render";
  @Transient
  public final String ANWENDUNGSPFAD = System.getProperty("user.dir");
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @JoinTable(name = "basiston_kombinationsberechnung", joinColumns = {@JoinColumn(name = "kombinationsberechnung_id")}, inverseJoinColumns = {
    @JoinColumn(name = "ton_id")})
  public Ton basisTon = null;
  @Column(name = "anzahl_toene", unique = false, nullable = false)
  public Integer maxAnzahlToene = -1;
  @Transient
  public AesthetischeGewichtung aesthetischeGewichtung;
  @Transient
  public Akkordkombinationen akkordkombinationen;

  @Column(name = "renderer", unique = false, nullable = false)
  public String renderer = "?";
  // FIXME Sollte das nötig sein? Eigentlich müsste sinnvolle hashCode/equals-Implementierung ausreichen.
  // FIXME hashCode und equals in jedem Fall!
  // FIXME ggf. Timestamp, ggf. sogar Update
  //  private static Kombinationsberechnung theInstance;
  //  private static boolean instantiated = false;
  @Id
  private Integer id;
  // @Transient
  private String[] argumentsArray;
  // @Transient
  @Column(name = "aufrufparameter", unique = false, nullable = true)
  private String argumentsString;
  // Statische Member
  @SuppressWarnings("unused")
  @Transient
  private boolean isDebugModus = false;
  //  private String absteigendeIntervallOption;
  @Transient
  private Boolean hatAbsteigendeIntervallinformationen = true;
  //  private String absteigendeKlangschaerfeOption;
  @Column(name = "klangschaerfe_absteigend", unique = true, nullable = false)
  private Boolean hatAbsteigendeKlangschaerfe = true;
  //  private String absteigendeAusgabeOption;
  @Column(name = "ausgabe_absteigend", unique = false, nullable = false)
  private Boolean hatAbsteigendeAusgabe = true;
  @Column(name = "intervallinformationen", unique = false, nullable = false)
  private String intervallInformationen = "?";
  private Boolean hatPersistenzSchreiben = false;
  private Boolean hatPersistenzLaden = false;
  private Boolean hatDbErstellen = false;
  //@Column(name = "intervallinformationen_absteigend", unique = false, nullable = false)
  @Column(name = "bereits_berechnete_toene", unique = false, nullable = true)
  private Integer bereitsBerechneteToene = -1;
  @Column(name = "letzte_basis_akkord_klangschaerfe", unique = false, nullable = true)
  private Integer letzteBasisAkkordKlangschaerfe = -1;
  @Column(name = "letzte_akkord_id", unique = false, nullable = true)
  private Integer letzteAkkordId = -1;
  @Column(name = "letzte_basis_akkord_id", unique = false, nullable = true)
  private Integer letzteBasisAkkordId = -1;

  public Kombinationsberechnung() {
    //    log.info("Kombinationsberechnung erzeugt!");
    //    Kombinationsberechnung.getInstance();
    if (this.id == null)
    {
      this.id = 1;
    }
  }

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(java.lang.Integer xId) {
    id = xId;
  }


  public AesthetischeGewichtung getAesthetischeGewichtung() {
    if (null == aesthetischeGewichtung) {
      aesthetischeGewichtung = new AesthetischeGewichtung(KombinationsberechnungParameter.CLI_DEFAULTWERT_INTERVALLINFORMATIONEN, this);
    }
    return aesthetischeGewichtung;
  }

  //  public String getAbsteigendeIntervallOption()
  //  {
  //    return absteigendeIntervallOption;
  //  }
  //
  //  public void setAbsteigendeIntervallOption(String absteigendeIntervallOption)
  //  {
  //    this.absteigendeIntervallOption = absteigendeIntervallOption;
  //    if (absteigendeIntervallOption.equals("j"))
  //    {
  //      this.setHatAbsteigendeIntervallinformationen(true);
  //    }
  //    else
  //    {
  //      this.setHatAbsteigendeIntervallinformationen(false);
  //    }
  //  }
  //
  //  public String getAbsteigendeKlangschaerfeOption()
  //  {
  //    return absteigendeKlangschaerfeOption;
  //  }
  //
  //  public void setAbsteigendeKlangschaerfeOption(String absteigendeKlangschaerfeOption)
  //  {
  //    this.absteigendeKlangschaerfeOption = absteigendeKlangschaerfeOption;
  //  }
  //
  //  public String getAbsteigendeAusgabeOption()
  //  {
  //    return absteigendeAusgabeOption;
  //  }
  //
  //  public void setAbsteigendeAusgabeOption(String absteigendeAusgabeOption)
  //  {
  //    this.absteigendeAusgabeOption = absteigendeAusgabeOption;
  //  }

  public void setAesthetischeGewichtung(AesthetischeGewichtung aesthetischeGewichtung) {
    this.aesthetischeGewichtung = aesthetischeGewichtung;
  }

  // @Column(name="basis_ton", unique=true, nullable=false)

  public Ton getBasisTonAsTon() {
    return basisTon;
  }

  //  @Transient

  public Ton getBasisTon() {
    return basisTon;
  }

  public void setBasisTon(Ton xBasisTon) {
    if (BasisTon.istGueltigerBasisTon(xBasisTon)) {
      basisTon = xBasisTon;
    } else {
      // Ggf. auf Default setzen.
      throw new RuntimeException("Kann ohne gueltigen Basiston keine Kombinationsberechnung durchfuehren!");
    }
  }


  public String getIntervallInformationen() {
    return intervallInformationen;
  }

  
  public Kombinationsberechnung getKombinationsberechnung() {
    return this;
  }

  public void setIntervallInformationen(String intervallInformationen) {
    this.intervallInformationen = intervallInformationen;
  }


  public Boolean getHatAbsteigendeIntervallinformationen() {
    return hatAbsteigendeIntervallinformationen;
  }

  public void setHatAbsteigendeIntervallinformationen(Boolean istAbsteigend) {
    this.hatAbsteigendeIntervallinformationen = istAbsteigend;
  }


  public Integer getMaxAnzahlToene() {
    return maxAnzahlToene;
  }

  public void setMaxAnzahlToene(Integer maxAnzahlToene) {
    this.maxAnzahlToene = maxAnzahlToene;
  }

  public String[] getArgumentsArray() {
    return argumentsArray;
  }

  public void setArgumentsArray(String[] xArgumentsArray) {
    argumentsArray = xArgumentsArray;
  }


  public String getArgumentsString() {
    return argumentsString;
  }

  public void setArgumentsString(String xArgumentsString) {
    argumentsString = xArgumentsString;
  }

  public void setArguments(String[] xArguments) {
    argumentsArray = xArguments;
    setArgumentsString(StringUtils.join(xArguments, ';'));
  }


  public String getRenderer() {
    return renderer;
  }

  public void setRenderer(String renderer) {
    this.renderer = renderer;
  }


  public Boolean getHatAbsteigendeKlangschaerfe() {
    return hatAbsteigendeKlangschaerfe;
  }

  public void setHatAbsteigendeKlangschaerfe(Boolean hatAbsteigendeKlangschaerfe) {
    this.hatAbsteigendeKlangschaerfe = hatAbsteigendeKlangschaerfe;
  }

  @Transient
  public Ton getDefaultBasisTon() {
    if (null == basisTon) {
      basisTon = new Ton();
      basisTon.setTonName(KombinationsberechnungParameter.CLI_DEFAULTWERT_GRUNDTON);
      // basisTon.setAbstandZumBasisTon(0);
      basisTon.setOktavlageByTonName(KombinationsberechnungParameter.CLI_DEFAULTWERT_GRUNDTON);
      basisTon.setId(basisTon.getAbstandZumEingestrichenenC());
    }
    return basisTon;
  }


  public Boolean getHatAbsteigendeAusgabe() {
    return hatAbsteigendeAusgabe;
  }

  public void setHatAbsteigendeAusgabe(Boolean hatAbsteigendeAusgabe) {
    this.hatAbsteigendeAusgabe = hatAbsteigendeAusgabe;
  }

  public Boolean getHatPersistenzLaden() {
    return hatPersistenzLaden;
  }

  public void setHatPersistenzLaden(Boolean hatPersistenzLaden) {
    this.hatPersistenzLaden = hatPersistenzLaden;
  }

  // public void setHatPersistenzLaden(Boolean hatPersistenzLaden) {
  // this.hatPersistenzLaden = hatPersistenzLaden;
  // }

  public Boolean getHatPersistenzSchreiben() {
    return hatPersistenzSchreiben;
  }

  public void setHatPersistenzSchreiben(Boolean hatPersistenzSchreiben) {
    this.hatPersistenzSchreiben = hatPersistenzSchreiben;
  }

  // TODO Könnte man auch verknüpfen, wenn eine DB mehrere AesthetischeGewichtungen enthalten soll
  @Transient
  public Akkordkombinationen getAkkordKombinationen() {
    return akkordkombinationen;
  }



  public Integer getBereitsBerechneteToene() {
    return bereitsBerechneteToene;
  }

  //  // FIXME So nicht! Kombinationsberechnung über KombinationsberechnungInformationService holen!
  //  @Transient
  //  public static Kombinationsberechnung getInstance()
  //  {
  //    if (null == theInstance && (instantiated == false))
  //    {
  //      // Häßlicher gehts nimmer.
  //      instantiated = true;
  //      theInstance = null;
  //      theInstance = new Kombinationsberechnung();
  //    }
  //    return theInstance;
  //  }

  public void setBereitsBerechneteToene(Integer xBereitsBerechneteToene) {
    bereitsBerechneteToene = xBereitsBerechneteToene;
  }


  public Integer getLetzteBasisAkkordKlangschaerfe() {
    return letzteBasisAkkordKlangschaerfe;
  }

  public void setLetzteBasisAkkordKlangschaerfe(Integer xLetzteBasisAkkordKlangschaerfe) {
    letzteBasisAkkordKlangschaerfe = xLetzteBasisAkkordKlangschaerfe;
  }

  public Boolean getHatDbErstellen() {
    return hatDbErstellen;
  }

  public void setHatDbErstellen(Boolean xHatDbErstellen) {
    hatDbErstellen = xHatDbErstellen;
  }


  public Integer getLetzteAkkordId() {
    return letzteAkkordId;
  }

  public void setLetzteAkkordId(Integer xId) {
    letzteAkkordId = xId;

  }


  public Integer getLetzteBasisAkkordId() {
    return letzteBasisAkkordId;
  }

  public void setLetzteBasisAkkordId(Integer xLetzteBasisAkkordId) {
    letzteBasisAkkordId = xLetzteBasisAkkordId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (!(other instanceof Kombinationsberechnung))
      return false;
    final Kombinationsberechnung that = (Kombinationsberechnung) other;
    // Muss dieselbe Anzahl an Tönen haben

    if (!this.getBasisTon().equals(that.getBasisTon())) {
      return false;
    }

    if (!this.getMaxAnzahlToene().equals(that.getMaxAnzahlToene())) {
      return false;
    }

    if (!this.getArgumentsString().equals(that.getArgumentsString())) {
      return false;
    }

    if (!this.getBereitsBerechneteToene().equals(that.getBereitsBerechneteToene())) {
      return false;
    }

    if (!this.getLetzteAkkordId().equals(that.getLetzteAkkordId())) {
      return false;
    }

    return this.getLetzteBasisAkkordId().equals(that.getLetzteBasisAkkordId());

  }

  // public void setHatPersistenzSchreiben(Boolean hatPersistenzSchreiben) {
  // this.hatPersistenzSchreiben = hatPersistenzSchreiben;
  // }

  @Override
  public int hashCode() {
    int result = 14;
    result = 29 * result + getArgumentsString().hashCode();
    result = 29 * result + getLetzteAkkordId().intValue();
    result = 29 * result + getLetzteBasisAkkordId().intValue();
    result = 29 * result + getBereitsBerechneteToene().intValue();
    return result;

  }

  public enum Ausgabeformat {
    LILYPOND,
    MUSICML,
    MIDI,
    POSTSCRIPT,
    TEXT
  }

}