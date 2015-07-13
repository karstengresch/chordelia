/*
 * Erstellt am 12.01.2005
 */
package org.gresch.quintett.domain.tonmodell;

import org.apache.log4j.Logger;

/**
 * @author Karsten
 *         Hilfsklasse für die Darstellung von Tonnamen.
 *         Unterstützter Namensumfang reicht von CES² (Subkontra) bis zu h''''.
 *         Für bestimmte Renderer
 */
// TODO Problem-Pattern "Challenges that stink". Hier: *Wo* die Lilypond-Namen(skonvertierung) implementieren?
// In dieser Klasse, obwohl sie nichts vom Renderer weiß? Ableiten? In der Renderer-Klasse selbst? Und dann ggf.
// als interne Klasse?
public abstract class Name {
  public static final String SUBKONTRA_SPEZIFIZIERER = "²";
  public static final String KONTRA_SPEZIFIZIERER = "¹";
  public static final String LILYPOND_SUBKONTRA_SPEZIFIZIERER = ",,,";
  public static final String LILYPOND_KONTRA_SPEZIFIZIERER = ",,";
  public static final String LILYPOND_GROSZE_SPEZIFIZIERER = ",";
  public static final String EINGESTRICHEN_SPEZIFIZIERER = "'";
  public static final String ZWEIGESTRICHEN_SPEZIFIZIERER = "''";
  public static final String DREIGESTRICHEN_SPEZIFIZIERER = "'''";
  public static final String VIERGESTRICHEN_SPEZIFIZIERER = "''''";
  public static final String FUENFGESTRICHEN_SPEZIFIZIERER = "'''''";
  public static final String SECHSGESTRICHEN_SPEZIFIZIERER = "''''''";
  public static final String SIEBENGESTRICHEN_SPEZIFIZIERER = "'''''''";
  public static final String ACHTGESTRICHEN_SPEZIFIZIERER = "''''''''";
  public static final String NEUNGESTRICHEN_SPEZIFIZIERER = "'''''''''";
  public static final String ZEHNGESTRICHEN_SPEZIFIZIERER = "''''''''''";
  public static final String ELFGESTRICHEN_SPEZIFIZIERER = "'''''''''''";
  public static final String ZWOELFGESTRICHEN_SPEZIFIZIERER = "''''''''''''";

  public static final String CESES = "Ceses";
  public static final String CES = "Ces";
  public static final String C = "C";
  public static final String CIS = "Cis";
  public static final String CISIS = "Cisis";

  public static final String DESES = "Deses";
  public static final String DES = "Des";
  public static final String D = "D";
  public static final String DIS = "Dis";
  public static final String DISIS = "Disis";

  public static final String ESES = "Eses";
  public static final String ES = "Es";
  public static final String E = "E";
  public static final String EIS = "Eis";
  public static final String EISIS = "Eisis";

  public static final String FESES = "Feses";
  public static final String FES = "Fes";
  public static final String F = "F";
  public static final String FIS = "Fis";
  public static final String FISIS = "Fisis";

  public static final String GESES = "Geses";
  public static final String GES = "Ges";
  public static final String G = "G";
  public static final String GIS = "Gis";
  public static final String GISIS = "Gisis";

  public static final String ASAS = "Asas";
  public static final String AS = "As";
  public static final String A = "A";
  public static final String AIS = "Ais";
  public static final String AISIS = "Aisis";

  public static final String HESES = "Heses";
  public static final String B = "B";
  public static final String H = "H";
  public static final String HIS = "His";
  public static final String HISIS = "Hisis";

  public static final String UNBEKANNT = "Unbekannt!";
  private static String lilypondTonName;
  private static Logger _logger = Logger.getLogger(Name.class);
  private static String enharmonischVerwechselterString;

  public static Boolean istKorrekterName(String xTonName) {
    Boolean _istKorrekterName = false;
    xTonName = xTonName.toUpperCase();
    xTonName = xTonName.replace("'", "");
    xTonName = xTonName.replace(String.valueOf(KONTRA_SPEZIFIZIERER), ""); // weshalb das .valueOf ??
    _istKorrekterName = (
      xTonName.equals(CESES)
        || xTonName.equals(CES)
        || xTonName.equals(C)
        || xTonName.equals(CIS)
        || xTonName.equals(CISIS)
        || xTonName.equals(DESES)
        || xTonName.equals(DES)
        || xTonName.equals(D)
        || xTonName.equals(DIS)
        || xTonName.equals(DISIS)
        || xTonName.equals(ESES)
        || xTonName.equals(ES)
        || xTonName.equals(E)
        || xTonName.equals(EIS)
        || xTonName.equals(EISIS)
        || xTonName.equals(FESES)
        || xTonName.equals(FES)
        || xTonName.equals(F)
        || xTonName.equals(FIS)
        || xTonName.equals(FISIS)
        || xTonName.equals(GESES)
        || xTonName.equals(GES)
        || xTonName.equals(G)
        || xTonName.equals(GIS)
        || xTonName.equals(GISIS)
        || xTonName.equals(ASAS)
        || xTonName.equals(AS)
        || xTonName.equals(A)
        || xTonName.equals(AIS)
        || xTonName.equals(AISIS)
        || xTonName.equals(HESES)
        || xTonName.equals(B)
        || xTonName.equals(H)
        || xTonName.equals(HIS)
        || xTonName.equals(HISIS)
    )
    ;
    return _istKorrekterName;
  }

  public static Boolean istKleinbuchstabe(String xTonName) {
    Boolean _istKleinbuchstabe = false;
    xTonName = xTonName.replace("'", "");
    xTonName = xTonName.replace(String.valueOf(KONTRA_SPEZIFIZIERER), "");
    xTonName = xTonName.replace(String.valueOf(SUBKONTRA_SPEZIFIZIERER), "");
    // xTonName = xTonName.replace("�", "");

    _istKleinbuchstabe = (
      xTonName.equals(CES.toLowerCase())
        || xTonName.equals(C.toLowerCase())
        || xTonName.equals(CIS.toLowerCase())
        || xTonName.equals(DES.toLowerCase())
        || xTonName.equals(D.toLowerCase())
        || xTonName.equals(DIS.toLowerCase())
        || xTonName.equals(ES.toLowerCase())
        || xTonName.equals(E.toLowerCase())
        || xTonName.equals(EIS.toLowerCase())
        || xTonName.equals(FES.toLowerCase())
        || xTonName.equals(F.toLowerCase())
        || xTonName.equals(FIS.toLowerCase())
        || xTonName.equals(GES.toLowerCase())
        || xTonName.equals(G.toLowerCase())
        || xTonName.equals(GIS.toLowerCase())
        || xTonName.equals(AS.toLowerCase())
        || xTonName.equals(A.toLowerCase())
        || xTonName.equals(AIS.toLowerCase())
        || xTonName.equals(B.toLowerCase())
        || xTonName.equals(H.toLowerCase())
        || xTonName.equals(HIS.toLowerCase())
    )
    ;
    return _istKleinbuchstabe;
  }

  public static String getVollstaendigerTonName(Oktavlage xOktavlage, String xTonName) {
    String _vollstaendigerTonName = "";

    if (xOktavlage == Oktavlage.SUBKONTRA) {
      _vollstaendigerTonName = xTonName.toUpperCase() + SUBKONTRA_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.KONTRA) {
      _vollstaendigerTonName = xTonName.toUpperCase() + KONTRA_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.GROSZE) {
      _vollstaendigerTonName = xTonName.toUpperCase();
    } else if (xOktavlage == Oktavlage.KLEINE) {
      _vollstaendigerTonName = xTonName.toLowerCase();
    } else if (xOktavlage == Oktavlage.EINGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + EINGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZWEIGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + ZWEIGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.DREIGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + DREIGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.VIERGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + VIERGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.FUENFGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + FUENFGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.SECHSGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + SECHSGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.SIEBENGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + SIEBENGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ACHTGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + ACHTGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.NEUNGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + NEUNGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZEHNGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + ZEHNGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ELFGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + ELFGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZWOELFGESTRICHEN) {
      _vollstaendigerTonName = xTonName.toLowerCase() + ZWOELFGESTRICHEN_SPEZIFIZIERER;
    } else {
      _logger.error("Name.getVollstaendigerTonName(): Kann keinen vollständigen Tonnamen bilden für folgende Argumente: Oktavlage:'" + xOktavlage
        + "' - Tonname: '" + xTonName + "'!");
    }

    return _vollstaendigerTonName;

  }

  // TODO Nicht unbedingt die richtige Stelle. Flyweight wäre außerdem besser für einzelne Namen.
  // TODO Subclassing für LiliypondName!
  public static String getLilyPondTonName(Ton xTon) {
    return getLilyPondTonName(xTon.getOktavlage(), xTon.getTonName());
  }

  public static String getLilyPondTonName(Oktavlage xOktavlage, String xTonName) {
    String _lilypondTonName = konvertiereZuLilypondTonName(xTonName);

    if (xOktavlage == Oktavlage.SUBKONTRA) {
      _lilypondTonName = _lilypondTonName + LILYPOND_SUBKONTRA_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.KONTRA) {
      _lilypondTonName = _lilypondTonName + LILYPOND_KONTRA_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.GROSZE) {
      _lilypondTonName = _lilypondTonName + LILYPOND_GROSZE_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.KLEINE) {
      // überflüssig: _lilypondTonName = _lilypondTonName;
    } else if (xOktavlage == Oktavlage.EINGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + EINGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZWEIGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + ZWEIGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.DREIGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + DREIGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.VIERGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + VIERGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.FUENFGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + FUENFGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.SECHSGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + SECHSGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.SIEBENGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + SIEBENGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ACHTGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + ACHTGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.NEUNGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + NEUNGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZEHNGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + ZEHNGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ELFGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + ELFGESTRICHEN_SPEZIFIZIERER;
    } else if (xOktavlage == Oktavlage.ZWOELFGESTRICHEN) {
      _lilypondTonName = _lilypondTonName + ZWOELFGESTRICHEN_SPEZIFIZIERER;
    } else {
      // nichts, ggf. Exception.
      _logger.error("Name.getVollstaendigerTonName(): Kann keinen vollständigen Lilypond-Tonnamen bilden für folgende Argumente: Oktavlage:'" + xOktavlage
        + "' - Tonname: '" + xTonName + "'!");
    }

    return _lilypondTonName;

  }

  private static String konvertiereZuLilypondTonName(String xTonName) {
    lilypondTonName = "";
    lilypondTonName = xTonName.toLowerCase();

    if (xTonName.toLowerCase().equals(H.toLowerCase())) {
      lilypondTonName = "b";
    }

    if (xTonName.toLowerCase().equals(B.toLowerCase())) {
      lilypondTonName = "bes";
    }

    return lilypondTonName;

  }

  @SuppressWarnings("unused")
  private static String enharmonischVerwechselnHalbtonHoeher(String xTonName) {
    enharmonischVerwechselterString = "";

    return enharmonischVerwechselterString;

  }

}
