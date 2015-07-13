/*
 * Erstellt am 11.01.2005
 */
package org.gresch.quintett.domain.tonmodell;

import org.apache.log4j.Logger;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Karsten Skalenbezogene Repräsentation eines Tons, wobei C/Deses = 0, Cis/Des=1 etc. entspricht. Wird mit der Notennamenklasse kombiniert.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@DynamicUpdate
@SelectBeforeUpdate
@Entity
@Table(name = "ton")
public class Ton {
  private static Logger _logger = Logger.getLogger(Ton.class);
  private Integer id;
  private String tonName = "";
  private String tonNameVollstaendig = "";
  //  private Integer abstandZumBasisTon = null;
  private Integer abstandZumEingestrichenenC = null;
  private Oktavlage oktavlage;

  public Ton() {
  }

  public Ton(Oktavlage xOktavlage,
             String xTonName) {
    // TODO Auf korrekten Namen prüfen.
    // TODO Auf leeren Namen prüfen.
    this.oktavlage = xOktavlage;
    this.tonName = xTonName;
    this.tonNameVollstaendig = Name.getVollstaendigerTonName(xOktavlage, xTonName);
    this.abstandZumEingestrichenenC = Intervalle.getAbstandCbyTonname(xTonName, xOktavlage);
    // XXX Ganz schlechte Vermischung mit der Kombinationsberechnung, sollte über Berechnung erfolgen

    // XXX Ist auskommentiert, derzeit noch ohne Ersatz!
    // this.setAbstandBasisTonDurchAbstandZumEingestrichenenC((BasisTon) Kombinationsberechnung.getBasisTon());
  }

  @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer getId() {
    if (null == this.id) {
      if (!(null == this.abstandZumEingestrichenenC)) {
        this.id = this.abstandZumEingestrichenenC;
      }
    }
    return id;
  }

  public void setId(Integer xId) {
    id = xId;
  }

  //  /**
  //   * @return Returns the abstandZumBasisTon.
  //   */
  //  @Transient
  //  public Integer getAbstandZumBasisTon()
  //  {
  //    assert (!(null == abstandZumBasisTon));
  //    return abstandZumBasisTon;
  //  }
  //
  //  /**
  //   * @return Returns the abstandZumBasisTon.
  //   */
  //  @Transient
  //  public Integer getAbstandZumBasisTon(BasisTon basisTon)
  //  {
  //    assert (!(null == basisTon));
  //
  //    if (abstandZumBasisTon == null)
  //    {
  //
  //      // return this.getAbstandZumEingestrichenenC() - Kombinationsberechnung.getDefaultBasisTon().getAbstandZumEingestrichenenC();
  //      throw new RuntimeException("Der Abstand zum Basiston ist unbekannt!");
  //    }
  //    else
  //    {
  //      return abstandZumBasisTon;
  //    }
  //  }
  //
  //  /**
  //   * @param abstandZumBasisTon
  //   *          The abstandZumBasisTon to set.
  //   */
  //  public void setAbstandZumBasisTon(Integer xAbstandZumBasisTon)
  //  {
  //    this.abstandZumBasisTon = xAbstandZumBasisTon;
  //    // Hier Tonnamen setzen?
  //  }

  /**
   * @return Returns the oktavlage.
   */
  @Column(name = "oktavlage", unique = false, nullable = false)
  public Oktavlage getOktavlage() {
    return oktavlage;
  }

  /**
   * @param oktavlage The oktavlage to set.
   */
  public void setOktavlage(Oktavlage oktavlage) {
    this.oktavlage = oktavlage;
  }

  /**
   * @return Returns the tonName.
   */
  @Column(name = "ton_name", unique = false, nullable = false)
  // @org.hibernate.annotations.Index(name = "IDX_TON_NAME")
  public String getTonName() {
    return tonName;
  }

  /**
   * @param tonName The tonName to set.
   */
  public void setTonName(String tonName) {
    this.tonName = tonName;
  }

  @Column(name = "abstand_c_eingestrichen", unique = false, nullable = false)
  public Integer getAbstandZumEingestrichenenC() {
    return abstandZumEingestrichenenC;
  }

  // TODO vom Ton oder Tonnamen ausgehen
  public void setAbstandZumEingestrichenenC(Integer xAbstandC) {
    this.abstandZumEingestrichenenC = xAbstandC;
  }

  public void initialisiereDurchAbstandZumEingestrichenenC(Integer xAbstandC) {
    this.abstandZumEingestrichenenC = xAbstandC;
    this.setOktavlageDurchAbstandZumEingestrichenenC(xAbstandC);
    // TODO Abstand zum Basiston???
    this.setTonName(Intervalle.getTonnameByAbstandC(xAbstandC));
    this.setTonNameVollstaendig(Name.getVollstaendigerTonName(this.getOktavlage(), this.getTonName()));
  }

  /* Prüft, ob Ton gültig ist und parst Oktavlage. */
  public void setOktavlageByTonName(String xTonName) {
    int _tonNamenLaenge = xTonName.length();
    // TODO auf null und Leerstring prüfen.
    // TODO Hochkommata und Hochnummern berücksichtigen
    if (!Name.istKorrekterName(xTonName)) {
      // TODO exception
    }
    String lagenSpezifizierer = "";
    // TODO switch
    switch (_tonNamenLaenge) {
      // abcdefgh/ABCDEFGH
      case 1:
        if (Name.istKleinbuchstabe(xTonName)) {
          oktavlage = Oktavlage.KLEINE;
        } else {
          oktavlage = Oktavlage.GROSZE;
        }
        break;
      // a'b'c'd'e'f'g'h'
      // AS,ES
      case 2:
        lagenSpezifizierer = xTonName.substring(1);
        // TODO endsWith vielleicht besser?
        if (lagenSpezifizierer.equals(Name.SUBKONTRA_SPEZIFIZIERER)) {
          oktavlage = Oktavlage.SUBKONTRA;
        } else if (lagenSpezifizierer.equals(Name.KONTRA_SPEZIFIZIERER)) {
          oktavlage = Oktavlage.KONTRA;
        } else if (lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.EINGESTRICHEN;
        } else {
          if (Name.istKleinbuchstabe(xTonName)) {
            oktavlage = Oktavlage.KLEINE;
          } else {
            oktavlage = Oktavlage.GROSZE;
          }
        }
        break;
      // a''b''c''d''e''f''g''h''
      // as'es'
      // ais,ces,cis,des,dis,eis,fes,fis,ges,gis,his
      // AIS,CES,CIS,DES,DIS,EIS,FES,FIS,GES,GIS,HIS
      case 3:
        lagenSpezifizierer = xTonName.substring(2);
        if (lagenSpezifizierer.equals(Name.SUBKONTRA_SPEZIFIZIERER)) {
          oktavlage = Oktavlage.SUBKONTRA;
        } else if (lagenSpezifizierer.equals(Name.KONTRA_SPEZIFIZIERER)) {
          oktavlage = Oktavlage.KONTRA;
        } else if (lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.ZWEIGESTRICHEN;
        } else {
          if (Name.istKleinbuchstabe(xTonName)) {
            oktavlage = Oktavlage.KLEINE;
          } else {
            oktavlage = Oktavlage.GROSZE;
          }
        }
        break;
      // a'''b'''c'''d'''e'''f'''g'''h'''
      // as'',es''
      // ais',ces',cis',des',dis',eis',fes',fis',ges',gis',his'
      case 4:
        // Bleibt so, damit man ermitteln kann, ob drei-oder eingestrichen
        lagenSpezifizierer = xTonName.substring(2);
        if (!lagenSpezifizierer.equals("'")) {
          if (xTonName.endsWith(Name.SUBKONTRA_SPEZIFIZIERER)) {
            oktavlage = Oktavlage.SUBKONTRA;
          } else if (xTonName.endsWith(Name.KONTRA_SPEZIFIZIERER)) {
            oktavlage = Oktavlage.KONTRA;
          } else if (xTonName.endsWith("'")) {
            oktavlage = Oktavlage.EINGESTRICHEN;
          }
        } else if (!xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.ZWEIGESTRICHEN;
        } else if (xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.DREIGESTRICHEN;
        }
        break;
      // a''''b''''c''''d''''e''''f''''g''''h''''
      // as''',es'''
      // ais'',ces'',cis'',des'',dis'',eis'',fes'',fis'',ges'',gis'',his''
      case 5:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("''''")) {
          oktavlage = Oktavlage.VIERGESTRICHEN;
        } else if (xTonName.endsWith("'''") && !xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.DREIGESTRICHEN;
        } else if (xTonName.endsWith("'''") && !lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.ZWEIGESTRICHEN;
        }
        break;
      // a'''''b'''''c'''''d'''''e'''''f'''''g'''''h'''''
      // as'''',es''''
      // ais''',ces''',cis''',des''',dis''',eis''',fes''',fis''',ges''',gis''',his'''
      case 6:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("'''''")) {
          // TODO TonumfangUeberschrittenException
        } else if (xTonName.endsWith("''''") && !xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.VIERGESTRICHEN;
        } else if (xTonName.endsWith("'''") && !lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.DREIGESTRICHEN;
        }
        break;
      // a''''''b''''''c''''''d''''''e''''''f''''''g''''''h''''''
      // as''''',es'''''
      // ais'''',ces'''',cis'''',des'''',dis'''',eis'''',fes'''',fis'''',ges'''',gis'''',his''''
      case 7:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("''''''")) {
          oktavlage = Oktavlage.SECHSGESTRICHEN;
        } else if (xTonName.endsWith("'''''") && !xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.FUENFGESTRICHEN;
        } else if (xTonName.endsWith("''''") && !lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.VIERGESTRICHEN;
        }
        break;
      case 8:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("'''''''")) {
          oktavlage = Oktavlage.SIEBENGESTRICHEN;
        } else if (xTonName.endsWith("''''''") && !xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.SECHSGESTRICHEN;
        } else if (xTonName.endsWith("'''''") && !lagenSpezifizierer.equals("'")) {
          oktavlage = Oktavlage.FUENFGESTRICHEN;
        }
        break;
      case 9:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("''''''''")) {
          oktavlage = Oktavlage.ACHTGESTRICHEN;
        } else if (xTonName.endsWith("'''''''")) {
          oktavlage = Oktavlage.SIEBENGESTRICHEN;
        } else if (xTonName.endsWith("''''''") && !xTonName.substring(1).equals("'")) {
          oktavlage = Oktavlage.SECHSGESTRICHEN;
        }
        break;
      case 10:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("'''''''''")) {
          oktavlage = Oktavlage.NEUNGESTRICHEN;
        } else if (xTonName.endsWith("''''''''")) {
          oktavlage = Oktavlage.ACHTGESTRICHEN;
        } else if (xTonName.endsWith("'''''''")) {
          oktavlage = Oktavlage.SIEBENGESTRICHEN;
        }
        break;
      case 11:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("''''''''''")) {
          oktavlage = Oktavlage.ZEHNGESTRICHEN;
        } else if (xTonName.endsWith("'''''''''")) {
          oktavlage = Oktavlage.NEUNGESTRICHEN;
        } else if (xTonName.endsWith("''''''''")) {
          oktavlage = Oktavlage.ACHTGESTRICHEN;
        }
        break;
      case 12:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("'''''''''''")) {
          oktavlage = Oktavlage.ELFGESTRICHEN;
        } else if (xTonName.endsWith("''''''''''")) {
          oktavlage = Oktavlage.ZEHNGESTRICHEN;
        } else if (xTonName.endsWith("'''''''''")) {
          oktavlage = Oktavlage.NEUNGESTRICHEN;
        }
        break;
      case 13:
        lagenSpezifizierer = xTonName.substring(2);
        if (xTonName.endsWith("''''''''''''")) {
          oktavlage = Oktavlage.ZWOELFGESTRICHEN;
        } else if (xTonName.endsWith("'''''''''''")) {
          oktavlage = Oktavlage.ELFGESTRICHEN;
        } else if (xTonName.endsWith("''''''''''")) {
          oktavlage = Oktavlage.ZEHNGESTRICHEN;
        }
        break;
      default:
        // TODO ? Vielleicht OktavlageNichtErmittelbarExeption
        break;
    }
  }

  public void setOktavlageDurchAbstandZumEingestrichenenC(Integer xAbstandC) {
    if (xAbstandC >= 0 && xAbstandC < 12) {
      this.oktavlage = Oktavlage.EINGESTRICHEN;
    } else if (xAbstandC < 0 && xAbstandC >= -12) {
      this.oktavlage = Oktavlage.KLEINE;
    } else if (xAbstandC < -12 && xAbstandC >= -24) {
      this.oktavlage = Oktavlage.GROSZE;
    } else if (xAbstandC < -24 && xAbstandC >= -36) {
      this.oktavlage = Oktavlage.KONTRA;
    } else if (xAbstandC < -36 && xAbstandC >= -48) {
      this.oktavlage = Oktavlage.SUBKONTRA;
    } else if (xAbstandC >= 12 && xAbstandC < 24) {
      this.oktavlage = Oktavlage.ZWEIGESTRICHEN;
    } else if (xAbstandC >= 24 && xAbstandC < 36) {
      this.oktavlage = Oktavlage.DREIGESTRICHEN;
    } else if (xAbstandC >= 36 && xAbstandC < 48) {
      this.oktavlage = Oktavlage.DREIGESTRICHEN;
    } else if (xAbstandC <= -48) {
      // TODO LowerIndexExceededException
      _logger.error("Ton.setOktavlageByAbstandC(): Unterer Tonbereich überschritten. : " + this.toString());
    } else if (xAbstandC >= 48 && xAbstandC < 60) {
      this.oktavlage = Oktavlage.VIERGESTRICHEN;
    } else if (xAbstandC >= 60 && xAbstandC < 72) {
      this.oktavlage = Oktavlage.FUENFGESTRICHEN;
    } else if (xAbstandC >= 72 && xAbstandC < 84) {
      this.oktavlage = Oktavlage.SECHSGESTRICHEN;
    } else if (xAbstandC >= 84 && xAbstandC < 96) {
      this.oktavlage = Oktavlage.SIEBENGESTRICHEN;
    } else if (xAbstandC >= 96 && xAbstandC < 108) {
      this.oktavlage = Oktavlage.ACHTGESTRICHEN;
    } else if (xAbstandC >= 108 && xAbstandC < 120) {
      this.oktavlage = Oktavlage.NEUNGESTRICHEN;
    } else if (xAbstandC >= 120 && xAbstandC < 132) {
      this.oktavlage = Oktavlage.ZEHNGESTRICHEN;
    } else if (xAbstandC >= 132 && xAbstandC < 144) {
      this.oktavlage = Oktavlage.ELFGESTRICHEN;
    } else if (xAbstandC >= 144 && xAbstandC < 156) {
      this.oktavlage = Oktavlage.ZWOELFGESTRICHEN;
    } else if (xAbstandC > 156) {
      // TODO UpperIndexExceededException
      _logger.error("Ton.setOktavlageByAbstandC(): Oberer Tonbereich überschritten. : " + this.toString());
    }
    // log.error("Ton.setOktavlageByAbstandC(): Oberer Tonbereich überschritten. : " + this.toString());
    else {
      // TODO UnknownToneIndexException
      _logger.error("TonsetOktavlageDurchAbstandZumEingestrichenenC): Unbekannter Tonumfangsfehler. : " + this.toString());
    }
  }

  /**
   * Die für den Ton interessanten Informationen. Überschreibt die Object.toString()-Methode.
   */
  public String toString() {
    StringBuilder _toStringOutput = new StringBuilder("\n******* TON *******");
    _toStringOutput.append("\nTonname............   : " + this.getTonName());
    _toStringOutput.append("\nVollständiger Tonname : " + this.getTonNameVollstaendig());
    //    _toStringOutput.append("\nAbstand zum Basiston  : " + this.getAbstandZumBasisTon());
    _toStringOutput.append("\nAbstand zum c'.....   : " + this.getAbstandZumEingestrichenenC());
    _toStringOutput.append("\nOktavlage..........   : " + this.getOktavlage());
    _toStringOutput.append("\n");
    return _toStringOutput.toString();
  }

  public int getAbstandBasisTonDurchAbstandZumEingestrichenenC(Ton xBasiston) {
    Integer _abstandBasisTonZumEingestrichenenC = xBasiston.getAbstandZumEingestrichenenC();
    return this.getAbstandZumEingestrichenenC() - _abstandBasisTonZumEingestrichenenC;
  }

  @Column(name = "tonname_vollstaendig", unique = false, nullable = false)
  public String getTonNameVollstaendig() {
    return tonNameVollstaendig;
  }

  public void setTonNameVollstaendig(String tonNameVollstaendig) {
    this.tonNameVollstaendig = tonNameVollstaendig;
  }

  // public boolean onSave(Session arg0) throws CallbackException {
  // log.debug("Ton-Hibernate: onSave");
  // return false;
  // }
  //
  // public boolean onUpdate(Session arg0) throws CallbackException {
  // log.debug("Ton-Hibernate: onUpdate");
  // return false;
  // }
  //
  // public boolean onDelete(Session arg0) throws CallbackException {
  // log.debug("Ton-Hibernate: onDelete");
  // return false;
  // }
  //
  // public void onLoad(Session arg0, Serializable arg1) {
  // log.debug("Ton-Hibernate: onLoad");
  // }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (!(other instanceof Ton))
      return false;
    final Ton that = (Ton) other;
    // Muss dieselbe Anzahl an Tönen haben
    if (!this.getTonName().equals(that.getTonName())) {
      return false;
    }
    // Muss vom selben Akkord abgeleitet sein - allerdings problematisch, wenn Konfusion in Akkord-Ids
    if (!this.getOktavlage().equals(that.getOktavlage())) {
      return false;
    }
    // Muss dieselbe Klangschärfe haben
    if (!this.getTonNameVollstaendig().equals(that.getTonNameVollstaendig())) {
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
    result = 29 * result + getOktavlage().hashCode();
    result = 29 * result + getTonName().hashCode();
    result = 29 * result + getTonNameVollstaendig().hashCode();
    return result;

  }

}
