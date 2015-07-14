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
  private static StringBuilder logMessage = new StringBuilder(500);
  private Integer id;
  private Integer anzahlToene = -1;

  private List<Ton> tonList;
  private Integer klangschaerfe = 0;


  private Integer basisAkkordId = -1;

  public Akkord() {
    // Leer
  }


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
    logMessage.delete(0, logMessage.length());
    int i = -1;
    logMessage.append("******* AKKORD *******");
    logMessage.append('\n');
    logMessage.append("ID: ");
    logMessage.append(this.getId());
    logMessage.append('\n');
    logMessage.append("Basis-Akkord-ID: ");
    logMessage.append(this.getBasisAkkordId());
    logMessage.append('\n');
    logMessage.append("Anzahl Töne: ");
    logMessage.append(this.getAnzahlToene());
    logMessage.append('\n');
    logMessage.append("Klangschärfe: ");
    logMessage.append(this.klangschaerfe);
    logMessage.append('\n');

    for (i = 0; i < this.tonList.size(); i++) {
      logMessage.append('\n');
      logMessage.append("Ton Nummer: " + String.valueOf(i + 1));
      logMessage.append(tonList.get(i).toString());
    }

    return logMessage;
  }

  @Column(name = "klangschaerfe", unique = false, nullable = false)
  public Integer getKlangschaerfe() {
    return klangschaerfe;
  }

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
