/*
 * Created on 11.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.gresch.quintett.domain.tonmodell;

import org.apache.log4j.Logger;

/**
 * @author Karsten
 * Grundlage für alle Intervalle, "wohltemperiert" angenommen.
 *
 *  */
public class Intervalle {
  private static Logger logger = Logger.getLogger(Intervalle.class);
  public static final Integer PRIME           =  new Integer(0);
  public static final Integer KLEINE_SEKUNDE  =  new Integer(1);
  public static final Integer GROSZE_SEKUNDE  =  new Integer(2);
  public static final Integer KLEINE_TERZ     =  new Integer(3);
  public static final Integer GROSZE_TERZ     =  new Integer(4);
  public static final Integer QUARTE          =  new Integer(5);
  public static final Integer TRITONUS        =  new Integer(6);
  public static final Integer QUINTE          =  new Integer(7);
  public static final Integer KLEINE_SEXTE    =  new Integer(8);
  public static final Integer GROSZE_SEXTE    =  new Integer(9);
  public static final Integer KLEINE_SEPTIME  =  new Integer(10);
  public static final Integer GROSZE_SEPTIME  =  new Integer(11);
  public static final Integer OKTAVE          =  new Integer(12);

  public static final Integer CES_ABSTAND  = 11;
  public static final Integer C_ABSTAND    = 0;
  public static final Integer CIS_ABSTAND  = 1;
  public static final Integer DES_ABSTAND  = 1;
  public static final Integer D_ABSTAND    = 2;
  public static final Integer DIS_ABSTAND  = 3;
  public static final Integer ES_ABSTAND   = 3;
  public static final Integer E_ABSTAND    = 4;
  public static final Integer EIS_ABSTAND  = 5;
  public static final Integer FES_ABSTAND  = 4;
  public static final Integer F_ABSTAND    = 5;
  public static final Integer FIS_ABSTAND  = 6;
  public static final Integer GES_ABSTAND  = 6;
  public static final Integer G_ABSTAND    = 7;
  public static final Integer GIS_ABSTAND  = 8;
  public static final Integer AS_ABSTAND   = 8;
  public static final Integer A_ABSTAND    = 9;
  public static final Integer AIS_ABSTAND  = 10;
  public static final Integer B_ABSTAND    = 10;
  public static final Integer H_ABSTAND    = 11;
  public static final Integer HIS_ABSTAND  = 0;

  public static Integer getAbstandCbyTon(Ton xTon)
  {
    return getAbstandCbyTonname(xTon.getTonName(), xTon.getOktavlage());
  }

  public static Integer getAbstandCbyTonname(String xTonname, Oktavlage xOktavlage)
  {
    Integer _oktavabstand = 0;
    if (xOktavlage.equals( Oktavlage.SUBKONTRA ))
    {
      _oktavabstand = -48;
    }
    else if (xOktavlage == Oktavlage.KONTRA)
    {
      _oktavabstand = -36;
    }
    else if (xOktavlage == Oktavlage.GROSZE)
    {
      _oktavabstand = -24;
    }
    else if (xOktavlage == Oktavlage.KLEINE)
    {
      _oktavabstand = -12;
    }
    else if (xOktavlage == Oktavlage.EINGESTRICHEN)
    {
      _oktavabstand = 0;
    }
    else if (xOktavlage == Oktavlage.ZWEIGESTRICHEN)
    {
      _oktavabstand = 12;
    }
    else if (xOktavlage == Oktavlage.DREIGESTRICHEN)
    {
      _oktavabstand = 24;
    }
    else if (xOktavlage == Oktavlage.VIERGESTRICHEN)
    {
      _oktavabstand = 36;
    }
    else if (xOktavlage == Oktavlage.FUENFGESTRICHEN)
    {
      _oktavabstand = 48;
    }
    else if (xOktavlage == Oktavlage.SECHSGESTRICHEN)
    {
      _oktavabstand = 60;
    }
    else if (xOktavlage == Oktavlage.SIEBENGESTRICHEN)
    {
      _oktavabstand = 72;
    }
    else if (xOktavlage == Oktavlage.ACHTGESTRICHEN)
    {
      _oktavabstand = 84;
    }
    else if (xOktavlage == Oktavlage.NEUNGESTRICHEN)
    {
      _oktavabstand = 96;
    }
    else if (xOktavlage == Oktavlage.ZEHNGESTRICHEN)
    {
      _oktavabstand = 108;
    }
    else if (xOktavlage == Oktavlage.ELFGESTRICHEN)
    {
      _oktavabstand = 120;
    }
    else if (xOktavlage == Oktavlage.ZWOELFGESTRICHEN)
    {
      _oktavabstand = 132;
    }
    else
    {
      // TODO OktavlageNichtErkanntException
      logger.error("Intervalle.getAbstandCbyTonname(): Oktavlage nicht erkannt! Oktavlage: '" + xOktavlage + "' - Tonname: '" + xTonname + "'!");
    }


    if (xTonname.equalsIgnoreCase(Name.C))
      {
        return Intervalle.C_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.CES))
      {
        return Intervalle.CES_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.CIS))
      {
        return Intervalle.CIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.DES))
      {
        return Intervalle.DES_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.D))
      {
        return Intervalle.D_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.DIS))
      {
        return Intervalle.DIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.ES))
      {
        return Intervalle.ES_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.E))
      {
        return Intervalle.E_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.EIS))
      {
        return Intervalle.EIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.FES))
      {
        return Intervalle.FES_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.F))
      {
        return Intervalle.F_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.FIS))
      {
        return Intervalle.FIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.GES))
      {
        return Intervalle.GES_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.G))
      {
        return Intervalle.G_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.GIS))
      {
        return Intervalle.GIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.AS))
      {
        return Intervalle.AS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.A))
      {
        return Intervalle.A_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.AIS))
      {
        return Intervalle.AIS_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.B))
      {
        return Intervalle.B_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.H))
      {
        return Intervalle.H_ABSTAND + _oktavabstand;
      }
      else if (xTonname.equalsIgnoreCase(Name.HIS))
      {
        return Intervalle.HIS_ABSTAND + _oktavabstand;
      }
      else
      {
        logger.error("Intervalle.getAbstandCbyTonname(): Unbekanntes Intervall! xTonname war: " + xTonname);
        return -1;
      }
  }
  // TODO Enharmonische Unterschiedlichlichkeit der Tonnamen nicht berücksichtigt.
  public static String getTonnameByAbstandC(Integer xAbstandC)
  {
    Integer _abstandC = xAbstandC; // Fürs bessere Debuggen.
    // TODO Etwas "eleganter" ginge das aber schon...
    if ((_abstandC >= 144) && (_abstandC < 156))
    {
      _abstandC -= 144;
    }
    if ((_abstandC >= 132) && (_abstandC < 144))
    {
      _abstandC -= 132;
    }
    if ((_abstandC >= 120) && (_abstandC < 132))
    {
      _abstandC -= 120;
    }
    if ((_abstandC >= 108) && (_abstandC < 120))
    {
      _abstandC -= 108;
    }
    if ((_abstandC >= 96) && (_abstandC < 108))
    {
      _abstandC -= 96;
    }
    if ((_abstandC >= 84) && (_abstandC < 96))
    {
      _abstandC -= 84;
    }
    if ((_abstandC >= 72) && (_abstandC < 84))
    {
      _abstandC -= 72;
    }

    if ((_abstandC >= 60) && (_abstandC < 72))
    {
      _abstandC -= 60;
    }

    if ((_abstandC >= 48) && (_abstandC < 60))
    {
      _abstandC -= 48;
    }

    if ((_abstandC >= 36) && (_abstandC < 48))
    {
      _abstandC -= 36;
    }

    if ((_abstandC >= 24) && (_abstandC < 36))
    {
      _abstandC -= 24;
    }
    else if ((_abstandC >= 12) && (_abstandC < 24))
    {
      _abstandC -= 12;
    }
    else if ((_abstandC < 0) && (_abstandC >= -12))
    {
      _abstandC += 12;
    }

    else if ((_abstandC < -12) && (_abstandC >= -24))
    {
      _abstandC += 24;
    }

    else if ((_abstandC < -24) && (_abstandC >= -36))
    {
      _abstandC += 36;
    }
    else if ((_abstandC < -36) && (_abstandC >= -48))
    {
      _abstandC += 48;
    }
    else if ((_abstandC < -48) && (_abstandC >= -60))
    {
      _abstandC += 60;
    }
    else if ((_abstandC < -60) && (_abstandC >= -72))
    {
      _abstandC += 72;
    }
    else if ((_abstandC < -72) && (_abstandC >= -84))
    {
      _abstandC += 84;
    }
    else if ((_abstandC < -84) && (_abstandC >= -96))
    {
      _abstandC += 96;
    }
    else if ((_abstandC < -96) && (_abstandC >= -108))
    {
      _abstandC += 108;
    }
    else if ((_abstandC < -108) && (_abstandC >= -120))
    {
      _abstandC += 120;
    }
    else if ((_abstandC < -120) && (_abstandC >= -132))
    {
      _abstandC += 132;
    }
    else if ((_abstandC < -132) && (_abstandC >= -144))
    {
      _abstandC += 144;
    }

    // else sollte 0 sein!

    switch (_abstandC)
      {
        case 0:
          return Name.C;
        case 1:
          return Name.CIS;
        case 2:
          return Name.D;
        case 3:
          return Name.ES;
        case 4:
          return Name.E;
        case 5:
          return Name.F;
        case 6:
          return Name.FIS;
        case 7:
          return Name.G;
        case 8:
          return Name.AS;
        case 9:
          return Name.A;
        case 10:
          return Name.B;
        case 11:
          return Name.H;

        default:
          logger.error("Intervalle.getTonnameByAbstandC(): Unbekannter Tonname für Abstand: " + _abstandC + "!");
          return "?";
      }
  }



}
