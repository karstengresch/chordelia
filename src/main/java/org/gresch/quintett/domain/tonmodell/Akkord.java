/*
 * Created on 11.01.2005
 *
 */
package org.gresch.quintett.domain.tonmodell;

import org.apache.log4j.Logger;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "akkord")
public class Akkord {
  private static Logger _logger = Logger.getLogger(Akkord.class);
  // TODO Implikationen von static im Kontext prüfen.
  private static StringBuilder _stringBuilder = new StringBuilder(500);
  // private MultiKey akkordSchluessel;
  private Integer id;
  private Integer anzahlToene = -1;
  // TODO zu Set!
  // private Ton[] tonArray;
  private List<Ton> tonList;
  private Integer klangschaerfe = 0;
  //  private ApplicationContext applicationContext = null;
  //  private Kombinationsberechnung kombinationsberechnung = new Kombinationsberechnung();

  private Integer basisAkkordId = -1;

  public Akkord() {
    // Leer
  }

  //  public Akkord(List<Ton> xTonList)
  //  {
  //    // Mindestens zwei Töne!
  //    // TODO: Exception, wenn nicht.
  //    if (xTonList.size() >= 1)
  //    {
  //      boolean dirtyFlag = false;
  //      // tonArray = new Ton[xTonArray.length];
  //      tonList = new LinkedList<Ton>();
  //      int i = 0;
  //      // TODO Fehlerhaft!
  //      int abstandZumNaechstenTon = 0;
  //      for (i = 0; i < xTonList.size(); i++)
  //      {
  //        tonList.add(xTonList.get(i));
  //
  //        if (i == 0)
  //        {
  //          // TODO Abstand von c', was soll der BasisTon hier???
  //          // abstandZumNaechstenTon = tonList.get(i).getAbstandZumBasisTon();
  //          abstandZumNaechstenTon = tonList.get(i).getAbstandZumEingestrichenenC();
  //          // TODO Ästhetische Gewichtung von Kombinationsberechnung unabhängig machen.
  //          this.klangschaerfe += kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC());
  //        }
  //        else
  //        {
  //          abstandZumNaechstenTon += tonList.get(i).getAbstandZumEingestrichenenC();
  //          // Auf/Ab!
  //          // TODO Zwischenvariablen!
  //
  //          this.klangschaerfe += kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1).getAbstandZumEingestrichenenC());
  //          if (log.isDebugEnabled())
  //          {
  //            log.debug("Akkord.Akkord(): Klangschärfe für Abstand " + (tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1).getAbstandZumEingestrichenenC()) + " | Hinzugefügt: "
  //                + kombinationsberechnung.getAesthetischeGewichtung().getKlangschaerfe(tonList.get(i).getAbstandZumEingestrichenenC() - tonList.get(i - 1).getAbstandZumEingestrichenenC()));
  //            log.debug("Akkord.Akkord(): Klangschärfe insgesamt: " + klangschaerfe);
  //          }
  //        }
  //
  //        if (tonList.get(i).getAbstandZumEingestrichenenC() > 0 && dirtyFlag == false)
  //        {
  //          this.ersterTonUeberEingestrichenemC = Integer.valueOf(i);
  //          dirtyFlag = true;
  //        }
  //      }
  //      // War vorher da, aber offenbar falsch:
  //      // anzahlToene = i + 1;
  //      this.anzahlToene = i;
  //    }
  //    else
  //    {
  //      log.error("Akkord.Akkord(): Konnte Akkord nicht initialisieren!");
  //    }
  //  }

  @Transient
  public static int getErsterTonUeberEingestrichenemC(List<Ton> tonList) {
    int ersterTonUeberEingestrichenemC = -1;

    int tonZaehler = 0;

    for (Ton ton : tonList) {
      if (ton.getAbstandZumEingestrichenenC() > 0) {
        ersterTonUeberEingestrichenemC = tonZaehler;
        break;
      }
      tonZaehler++;

    }

    return ersterTonUeberEingestrichenemC;
  }

  @Id
  /* @GeneratedValue(strategy = GenerationType.AUTO) */
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return Returns the anzahlToene.
   */
  @Column(name = "anzahlToene", unique = false, nullable = false)
  public Integer getAnzahlToene() {
    return anzahlToene;
  }

  public void setAnzahlToene(Integer xAnzahlToene) {
    anzahlToene = xAnzahlToene;
  }

  // @Transient
  // public Ton[] getTonArray() {
  // return tonArray;
  // }
  // Bestimmten Ton holen - Numerierung nullbasiert.
  public Ton getTonZuNummerNullbasiert(Integer xNummer) {
    return tonList.get(xNummer);
  }

  // Bestimmten Ton holen - Numerierung mit 1 beginnend.
  public Ton getTonZuNummer(Integer xNummer) {
    return tonList.get(xNummer - 1);
  }

  // Bestimmten Ton holen - Numerierung mit 1 beginnend.
  @Transient
  public Ton getHoechstenTon() {
    return tonList.get(tonList.size() - 1);
  }

  public String toString() {
    return this.toStringBuilder().toString();
  }

  public StringBuilder toStringBuilder() {
    _stringBuilder.delete(0, _stringBuilder.length());
    int i = -1;
    _stringBuilder.append("******* AKKORD *******");
    _stringBuilder.append('\n');
    _stringBuilder.append("ID: ");
    _stringBuilder.append(this.getId());
    _stringBuilder.append('\n');
    _stringBuilder.append("Basis-Akkord-ID: ");
    _stringBuilder.append(this.getBasisAkkordId());
    _stringBuilder.append('\n');
    _stringBuilder.append("Anzahl Töne: ");
    _stringBuilder.append(this.getAnzahlToene());
    _stringBuilder.append('\n');
    _stringBuilder.append("Klangschärfe: ");
    _stringBuilder.append(this.klangschaerfe);
    _stringBuilder.append('\n');

    for (i = 0; i < this.tonList.size(); i++) {
      _stringBuilder.append('\n');
      _stringBuilder.append("Ton Nummer: " + String.valueOf(i + 1));
      _stringBuilder.append(tonList.get(i).toString());
    }

    return _stringBuilder;
  }

  @Column(name = "klangschaerfe", unique = false, nullable = false)
  public Integer getKlangschaerfe() {
    return klangschaerfe;
  }

  //  public void validate() throws ValidationFailure
  //  {
  //    if (log.isDebugEnabled())
  //    {
  //      log.debug("Akkord-Hibernate: Validate()");
  //    }
  //  }

  //  public void onLoad(Session arg0, Serializable arg1)
  //  {
  //    if (log.isDebugEnabled())
  //    {
  //      log.debug("Akkord-Hibernate: onLoad");
  //    }
  //  }

  public void setKlangschaerfe(Integer klangschaerfe) {
    this.klangschaerfe = klangschaerfe;
  }

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @JoinTable(name = "ton_akkord", joinColumns = {@JoinColumn(name = "akkord_id")}, inverseJoinColumns = {@JoinColumn(name = "ton_id")})
  @javax.persistence.OrderColumn(name = "position")
  public List<Ton> getTonList() {
    return tonList;
  }

  public void setTonList(List<Ton> tonList) {
    this.tonList = tonList;
  }

  @Column(name = "basis_akkord_id", unique = false, nullable = false)
  public Integer getBasisAkkordId() {
    return basisAkkordId;
  }

  public void setBasisAkkordId(Integer xBasisAkkordId) {
    basisAkkordId = xBasisAkkordId;

  }

  //  @Override
  //  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
  //  {
  //    this.applicationContext = applicationContext;
  //    this.kombinationsberechnung = ((KombinationsberechnungService) applicationContext.getBean("kombinationsberechnungService")).getKombinationsBerechnung();
  //  }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (!(other instanceof Akkord))
      return false;
    final Akkord that = (Akkord) other;
    // Muss dieselbe Anzahl an Tönen haben
    if (!this.getAnzahlToene().equals(that.getAnzahlToene())) {
      return false;
    }
    // Muss vom selben Akkord abgeleitet sein - allerdings problematisch, wenn Konfusion in Akkord-Ids
    if (!this.getBasisAkkordId().equals(that.getBasisAkkordId())) {
      return false;
    }
    // Muss dieselbe Klangschärfe haben
    if (!this.getKlangschaerfe().equals(that.getKlangschaerfe())) {
      return false;
    }
    // Folgende Attribute schieden aus:
    // Könnte transponiert sein, dennoch mit derselben Akkordstruktur.
    // if (!this.getErsterTonUeberEingestrichenemC().equals(that.getErsterTonUeberEingestrichenemC()))
    // {
    // return false;
    // }
    // Könnte transponiert sein, dennoch mit derselben Akkordstruktur.
    // if (!this.getHoechstenTon().equals(that.getHoechstenTon()))
    // {
    // return false;
    // }
    // Könnte transponiert sein, dennoch mit derselben Akkordstruktur.
    // if (!this.getTonList().equals(that.getTonList()))
    // {
    // return false;
    // }

    return true;
  }

  @Override
  public int hashCode() {
    int result = 14;
    result = 29 * result + getAnzahlToene().intValue();
    result = 29 * result + getBasisAkkordId().intValue();
    result = 29 * result + getKlangschaerfe().intValue();
    return result;

  }

}
