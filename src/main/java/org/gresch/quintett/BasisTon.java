package org.gresch.quintett;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gresch.quintett.domain.tonmodell.Intervalle;
import org.gresch.quintett.domain.tonmodell.Name;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.domain.tonmodell.Tonumfang;

// XXX Table???
// Sollte so sein (2009-04-08): Basiston ist nichts anderes als Ton, lediglich verlinkt.
// FIXME Muss das unbedingt ein separates Objekt sein?
public class BasisTon {
  private static Logger _logger = Logger.getLogger(Ton.class);

  private BasisTon() {
//    this.setOktavlage(Oktavlage.GROSZE);
    // this.setId((Tonumfang.getTon(this.getOktavlage(), this.getTonName()).getId())) ;
  }

//  public BasisTon(Oktavlage xOktavlage, String xTonName) {
//    super(xOktavlage, xTonName);
//    // ???
//    if ( this.getOktavlage() != Oktavlage.GROSZE )
//    {
//      this.setOktavlage(Oktavlage.GROSZE);
//    }
//    this.setId((Tonumfang.getTon(this.getOktavlage(), this.getTonName()).getId())) ;
//  }

//  public static boolean istG
  
  public static boolean istGueltigerBasisTon(Ton xTon)
  {
    boolean istGueltig = false;
    if (xTon.getOktavlage().equals(Oktavlage.GROSZE) && istGueltigerBasisTonName(xTon.getTonName()))
    {
      istGueltig = true;
    }
    return istGueltig;
  }
  
  public static boolean istGueltigerBasisTonName(String xTonName) {
    boolean istGueltig = false;
    if (StringUtils.isEmpty(xTonName))
    {
      // Ueberfluessig, aber lesbarer...
      istGueltig = false;
    }
    else {
      istGueltig = (Name.C.equals(xTonName) || xTonName.equals(Name.D) || xTonName.equals(Name.E) || xTonName.equals(Name.F) || xTonName.equals(Name.G) || xTonName.equals(Name.A) || xTonName.equals(Name.H));
    }
    
    
    return istGueltig;
  }

//  /**
//   * @param tonName
//   *  Der Tonname.
//   *  Setzt gleichzeitig auch den Abstand zum eingestrichenen C!
//   */
//  public void setTonName(String tonName) {
//    if (_logger.isDebugEnabled())
//    {
//      _logger.debug("BasisTon.setTonName(): " + tonName);
//    }
//    if ( istGueltigerBasisTonName(tonName) )
//    {
//      super.setTonName(tonName);
//      super.setOktavlageByTonName(tonName);
//      super.setTonNameVollstaendig(Name.getVollstaendigerTonName(super.getOktavlage(), tonName));
//      // TODO: Alles andere als selbsterklärend.
//      super.setAbstandZumEingestrichenenC(Intervalle.getAbstandCbyTonname(tonName, super.getOktavlage()));
//      this.setId((Tonumfang.getTon(this.getOktavlage(), this.getTonName()).getId())) ;
//    }
//    else
//    {
//      // TODO Eigene Exception-Klasse
//      throw (new RuntimeException("Ungültiger Tonname für Basiston!: " + tonName));
//    }
//  }
}
