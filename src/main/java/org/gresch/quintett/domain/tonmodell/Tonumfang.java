package org.gresch.quintett.domain.tonmodell;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

// TODO Klassenmethode, mit der Referenz auf den richtigen Ton zurückgegeben wird.
public class Tonumfang {
  
  private Tonumfang()
  {
    // Constant class.
  }
  
  enum SUBKONTRATOENE { };
  public final static Integer MIN_ABSTAND_C_EINGESTRICHEN = -48;
  // TODO Der maximale Abstand sollte auch geprüft werden!
  public final static Integer MAX_ABSTAND_C_EINGESTRICHEN = 143;
  // SUBKONTRA
  public final static Ton C_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.C);
  public final static Ton CIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.CIS);
  public final static Ton DES_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.DES);
  public final static Ton D_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.D);
  public final static Ton DIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.DIS);
  public final static Ton ES_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,  Name.ES);
  public final static Ton E_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.E);
  public final static Ton EIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.EIS);
  public final static Ton FES_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.FES);
  public final static Ton F_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.F);
  public final static Ton FIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.FIS);
  public final static Ton GES_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.GES);
  public final static Ton G_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.G);
  public final static Ton GIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.GIS);
  public final static Ton AS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,  Name.AS);
  public final static Ton A_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.A);
  public final static Ton AIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.AIS);
  public final static Ton B_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.B);
  public final static Ton H_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA,   Name.H);
  public final static Ton HIS_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.HIS);
  public final static Ton CES_SUBKONTRA = new Ton(Oktavlage.SUBKONTRA, Name.CES);
  // KONTRA
  public final static Ton C_KONTRA = new Ton(Oktavlage.KONTRA,   Name.C);
  public final static Ton CIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.CIS);
  public final static Ton DES_KONTRA = new Ton(Oktavlage.KONTRA, Name.DES);
  public final static Ton D_KONTRA = new Ton(Oktavlage.KONTRA,   Name.D);
  public final static Ton DIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.DIS);
  public final static Ton ES_KONTRA = new Ton(Oktavlage.KONTRA,  Name.ES);
  public final static Ton E_KONTRA = new Ton(Oktavlage.KONTRA,   Name.E);
  public final static Ton EIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.EIS);
  public final static Ton FES_KONTRA = new Ton(Oktavlage.KONTRA, Name.FES);
  public final static Ton F_KONTRA = new Ton(Oktavlage.KONTRA,   Name.F);
  public final static Ton FIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.FIS);
  public final static Ton GES_KONTRA = new Ton(Oktavlage.KONTRA, Name.GES);
  public final static Ton G_KONTRA = new Ton(Oktavlage.KONTRA,   Name.G);
  public final static Ton GIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.GIS);
  public final static Ton AS_KONTRA = new Ton(Oktavlage.KONTRA,  Name.AS);
  public final static Ton A_KONTRA = new Ton(Oktavlage.KONTRA,   Name.A);
  public final static Ton AIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.AIS);
  public final static Ton B_KONTRA = new Ton(Oktavlage.KONTRA,   Name.B);
  public final static Ton H_KONTRA = new Ton(Oktavlage.KONTRA,   Name.H);
  public final static Ton HIS_KONTRA = new Ton(Oktavlage.KONTRA, Name.HIS);
  public final static Ton CES_KONTRA = new Ton(Oktavlage.KONTRA, Name.CES);
  // GROSZE
  public final static Ton C_GROSZ = new Ton(Oktavlage.GROSZE,   Name.C);
  public final static Ton CIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.CIS);
  public final static Ton DES_GROSZ = new Ton(Oktavlage.GROSZE, Name.DES);
  public final static Ton D_GROSZ = new Ton(Oktavlage.GROSZE,   Name.D);
  public final static Ton DIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.DIS);
  public final static Ton ES_GROSZ = new Ton(Oktavlage.GROSZE,  Name.ES);
  public final static Ton E_GROSZ = new Ton(Oktavlage.GROSZE,   Name.E);
  public final static Ton EIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.EIS);
  public final static Ton FES_GROSZ = new Ton(Oktavlage.GROSZE, Name.FES);
  public final static Ton F_GROSZ = new Ton(Oktavlage.GROSZE,   Name.F);
  public final static Ton FIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.FIS);
  public final static Ton GES_GROSZ = new Ton(Oktavlage.GROSZE, Name.GES);
  public final static Ton G_GROSZ = new Ton(Oktavlage.GROSZE,   Name.G);
  public final static Ton GIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.GIS);
  public final static Ton AS_GROSZ = new Ton(Oktavlage.GROSZE,  Name.AS);
  public final static Ton A_GROSZ = new Ton(Oktavlage.GROSZE,   Name.A);
  public final static Ton AIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.AIS);
  public final static Ton B_GROSZ = new Ton(Oktavlage.GROSZE,   Name.B);
  public final static Ton H_GROSZ = new Ton(Oktavlage.GROSZE,   Name.H);
  public final static Ton HIS_GROSZ = new Ton(Oktavlage.GROSZE, Name.HIS);
  public final static Ton CES_GROSZ = new Ton(Oktavlage.GROSZE, Name.CES);
  // KLEINE
  public final static Ton C_KLEIN = new Ton(Oktavlage.KLEINE,   Name.C);
  public final static Ton CIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.CIS);
  public final static Ton DES_KLEIN = new Ton(Oktavlage.KLEINE, Name.DES);
  public final static Ton D_KLEIN = new Ton(Oktavlage.KLEINE,   Name.D);
  public final static Ton DIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.DIS);
  public final static Ton ES_KLEIN = new Ton(Oktavlage.KLEINE,  Name.ES);
  public final static Ton E_KLEIN = new Ton(Oktavlage.KLEINE,   Name.E);
  public final static Ton EIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.EIS);
  public final static Ton FES_KLEIN = new Ton(Oktavlage.KLEINE, Name.FES);
  public final static Ton F_KLEIN = new Ton(Oktavlage.KLEINE,   Name.F);
  public final static Ton FIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.FIS);
  public final static Ton GES_KLEIN = new Ton(Oktavlage.KLEINE, Name.GES);
  public final static Ton G_KLEIN = new Ton(Oktavlage.KLEINE,   Name.G);
  public final static Ton GIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.GIS);
  public final static Ton AS_KLEIN = new Ton(Oktavlage.KLEINE,  Name.AS);
  public final static Ton A_KLEIN = new Ton(Oktavlage.KLEINE,   Name.A);
  public final static Ton AIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.AIS);
  public final static Ton B_KLEIN = new Ton(Oktavlage.KLEINE,   Name.B);
  public final static Ton H_KLEIN = new Ton(Oktavlage.KLEINE,   Name.H);
  public final static Ton HIS_KLEIN = new Ton(Oktavlage.KLEINE, Name.HIS);
  public final static Ton CES_KLEIN = new Ton(Oktavlage.KLEINE, Name.CES);
  // EINGESTRICHENE
  public final static Ton C_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.C);
  public final static Ton CIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.CIS);
  public final static Ton DES_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.DES);
  public final static Ton D_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.D);
  public final static Ton DIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.DIS);
  public final static Ton ES_1 = new Ton(Oktavlage.EINGESTRICHEN,  Name.ES);
  public final static Ton E_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.E);
  public final static Ton EIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.EIS);
  public final static Ton FES_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.FES);
  public final static Ton F_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.F);
  public final static Ton FIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.FIS);
  public final static Ton GES_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.GES);
  public final static Ton G_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.G);
  public final static Ton GIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.GIS);
  public final static Ton AS_1 = new Ton(Oktavlage.EINGESTRICHEN,  Name.AS);
  public final static Ton A_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.A);
  public final static Ton AIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.AIS);
  public final static Ton B_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.B);
  public final static Ton H_1 = new Ton(Oktavlage.EINGESTRICHEN,   Name.H);
  public final static Ton HIS_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.HIS);
  public final static Ton CES_1 = new Ton(Oktavlage.EINGESTRICHEN, Name.CES);
  // ZWEIGESTRICHENE
  public final static Ton C_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.C);
  public final static Ton CIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.CIS);
  public final static Ton DES_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.DES);
  public final static Ton D_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.D);
  public final static Ton DIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.DIS);
  public final static Ton ES_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,  Name.ES);
  public final static Ton E_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.E);
  public final static Ton EIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.EIS);
  public final static Ton FES_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.FES);
  public final static Ton F_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.F);
  public final static Ton FIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.FIS);
  public final static Ton GES_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.GES);
  public final static Ton G_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.G);
  public final static Ton GIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.GIS);
  public final static Ton AS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,  Name.AS);
  public final static Ton A_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.A);
  public final static Ton AIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.AIS);
  public final static Ton B_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.B);
  public final static Ton H_2 = new Ton(Oktavlage.ZWEIGESTRICHEN,   Name.H);
  public final static Ton HIS_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.HIS);
  public final static Ton CES_2 = new Ton(Oktavlage.ZWEIGESTRICHEN, Name.CES);
  // DREIGESTRICHENE
  public final static Ton C_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.C);
  public final static Ton CIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.CIS);
  public final static Ton DES_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.DES);
  public final static Ton D_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.D);
  public final static Ton DIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.DIS);
  public final static Ton ES_3 = new Ton(Oktavlage.DREIGESTRICHEN,  Name.ES);
  public final static Ton E_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.E);
  public final static Ton EIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.EIS);
  public final static Ton FES_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.FES);
  public final static Ton F_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.F);
  public final static Ton FIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.FIS);
  public final static Ton GES_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.GES);
  public final static Ton G_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.G);
  public final static Ton GIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.GIS);
  public final static Ton AS_3 = new Ton(Oktavlage.DREIGESTRICHEN,  Name.AS);
  public final static Ton A_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.A);
  public final static Ton AIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.AIS);
  public final static Ton B_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.B);
  public final static Ton H_3 = new Ton(Oktavlage.DREIGESTRICHEN,   Name.H);
  public final static Ton HIS_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.HIS);
  public final static Ton CES_3 = new Ton(Oktavlage.DREIGESTRICHEN, Name.CES);
  // VIERGESTRICHENE
  public final static Ton C_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.C);
  public final static Ton CIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.CIS);
  public final static Ton DES_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.DES);
  public final static Ton D_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.D);
  public final static Ton DIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.DIS);
  public final static Ton ES_4 = new Ton(Oktavlage.VIERGESTRICHEN,  Name.ES);
  public final static Ton E_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.E);
  public final static Ton EIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.EIS);
  public final static Ton FES_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.FES);
  public final static Ton F_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.F);
  public final static Ton FIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.FIS);
  public final static Ton GES_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.GES);
  public final static Ton G_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.G);
  public final static Ton GIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.GIS);
  public final static Ton AS_4 = new Ton(Oktavlage.VIERGESTRICHEN,  Name.AS);
  public final static Ton A_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.A);
  public final static Ton AIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.AIS);
  public final static Ton B_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.B);
  public final static Ton H_4 = new Ton(Oktavlage.VIERGESTRICHEN,   Name.H);
  public final static Ton HIS_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.HIS);
  public final static Ton CES_4 = new Ton(Oktavlage.VIERGESTRICHEN, Name.CES);
  // FUENFGESTRICHENE
  public final static Ton C_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.C);
  public final static Ton CIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.CIS);
  public final static Ton DES_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.DES);
  public final static Ton D_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.D);
  public final static Ton DIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.DIS);
  public final static Ton ES_5 = new Ton(Oktavlage.FUENFGESTRICHEN,  Name.ES);
  public final static Ton E_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.E);
  public final static Ton EIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.EIS);
  public final static Ton FES_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.FES);
  public final static Ton F_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.F);
  public final static Ton FIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.FIS);
  public final static Ton GES_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.GES);
  public final static Ton G_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.G);
  public final static Ton GIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.GIS);
  public final static Ton AS_5 = new Ton(Oktavlage.FUENFGESTRICHEN,  Name.AS);
  public final static Ton A_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.A);
  public final static Ton AIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.AIS);
  public final static Ton B_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.B);
  public final static Ton H_5 = new Ton(Oktavlage.FUENFGESTRICHEN,   Name.H);
  public final static Ton HIS_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.HIS);
  public final static Ton CES_5 = new Ton(Oktavlage.FUENFGESTRICHEN, Name.CES);
  // SECHSGESTRICHENE
  public final static Ton C_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.C);
  public final static Ton CIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.CIS);
  public final static Ton DES_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.DES);
  public final static Ton D_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.D);
  public final static Ton DIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.DIS);
  public final static Ton ES_6 = new Ton(Oktavlage.SECHSGESTRICHEN,  Name.ES);
  public final static Ton E_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.E);
  public final static Ton EIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.EIS);
  public final static Ton FES_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.FES);
  public final static Ton F_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.F);
  public final static Ton FIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.FIS);
  public final static Ton GES_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.GES);
  public final static Ton G_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.G);
  public final static Ton GIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.GIS);
  public final static Ton AS_6 = new Ton(Oktavlage.SECHSGESTRICHEN,  Name.AS);
  public final static Ton A_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.A);
  public final static Ton AIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.AIS);
  public final static Ton B_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.B);
  public final static Ton H_6 = new Ton(Oktavlage.SECHSGESTRICHEN,   Name.H);
  public final static Ton HIS_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.HIS);
  public final static Ton CES_6 = new Ton(Oktavlage.SECHSGESTRICHEN, Name.CES);
  // SIEBENGESTRICHENE
  public final static Ton C_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.C);
  public final static Ton CIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.CIS);
  public final static Ton DES_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.DES);
  public final static Ton D_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.D);
  public final static Ton DIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.DIS);
  public final static Ton ES_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,  Name.ES);
  public final static Ton E_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.E);
  public final static Ton EIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.EIS);
  public final static Ton FES_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.FES);
  public final static Ton F_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.F);
  public final static Ton FIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.FIS);
  public final static Ton GES_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.GES);
  public final static Ton G_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.G);
  public final static Ton GIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.GIS);
  public final static Ton AS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,  Name.AS);
  public final static Ton A_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.A);
  public final static Ton AIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.AIS);
  public final static Ton B_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.B);
  public final static Ton H_7 = new Ton(Oktavlage.SIEBENGESTRICHEN,   Name.H);
  public final static Ton HIS_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.HIS);
  public final static Ton CES_7 = new Ton(Oktavlage.SIEBENGESTRICHEN, Name.CES);
  // ACHTGESTRICHENE
  public final static Ton C_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.C);
  public final static Ton CIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.CIS);
  public final static Ton DES_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.DES);
  public final static Ton D_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.D);
  public final static Ton DIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.DIS);
  public final static Ton ES_8 = new Ton(Oktavlage.ACHTGESTRICHEN,  Name.ES);
  public final static Ton E_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.E);
  public final static Ton EIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.EIS);
  public final static Ton FES_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.FES);
  public final static Ton F_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.F);
  public final static Ton FIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.FIS);
  public final static Ton GES_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.GES);
  public final static Ton G_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.G);
  public final static Ton GIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.GIS);
  public final static Ton AS_8 = new Ton(Oktavlage.ACHTGESTRICHEN,  Name.AS);
  public final static Ton A_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.A);
  public final static Ton AIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.AIS);
  public final static Ton B_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.B);
  public final static Ton H_8 = new Ton(Oktavlage.ACHTGESTRICHEN,   Name.H);
  public final static Ton HIS_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.HIS);
  public final static Ton CES_8 = new Ton(Oktavlage.ACHTGESTRICHEN, Name.CES);
  //NEUNGESTRICHENE
  public final static Ton C_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.C);
  public final static Ton CIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.CIS);
  public final static Ton DES_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.DES);
  public final static Ton D_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.D);
  public final static Ton DIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.DIS);
  public final static Ton ES_9 = new Ton(Oktavlage.NEUNGESTRICHEN,  Name.ES);
  public final static Ton E_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.E);
  public final static Ton EIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.EIS);
  public final static Ton FES_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.FES);
  public final static Ton F_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.F);
  public final static Ton FIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.FIS);
  public final static Ton GES_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.GES);
  public final static Ton G_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.G);
  public final static Ton GIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.GIS);
  public final static Ton AS_9 = new Ton(Oktavlage.NEUNGESTRICHEN,  Name.AS);
  public final static Ton A_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.A);
  public final static Ton AIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.AIS);
  public final static Ton B_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.B);
  public final static Ton H_9 = new Ton(Oktavlage.NEUNGESTRICHEN,   Name.H);
  public final static Ton HIS_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.HIS);
  public final static Ton CES_9 = new Ton(Oktavlage.NEUNGESTRICHEN, Name.CES);
  // ZEHNGESTRICHENE
  public final static Ton C_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.C);
  public final static Ton CIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.CIS);
  public final static Ton DES_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.DES);
  public final static Ton D_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.D);
  public final static Ton DIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.DIS);
  public final static Ton ES_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,  Name.ES);
  public final static Ton E_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.E);
  public final static Ton EIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.EIS);
  public final static Ton FES_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.FES);
  public final static Ton F_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.F);
  public final static Ton FIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.FIS);
  public final static Ton GES_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.GES);
  public final static Ton G_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.G);
  public final static Ton GIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.GIS);
  public final static Ton AS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,  Name.AS);
  public final static Ton A_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.A);
  public final static Ton AIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.AIS);
  public final static Ton B_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.B);
  public final static Ton H_10 = new Ton(Oktavlage.ZEHNGESTRICHEN,   Name.H);
  public final static Ton HIS_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.HIS);
  public final static Ton CES_10 = new Ton(Oktavlage.ZEHNGESTRICHEN, Name.CES);
  // ELFGESTRICHENE
  public final static Ton C_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.C);
  public final static Ton CIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.CIS);
  public final static Ton DES_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.DES);
  public final static Ton D_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.D);
  public final static Ton DIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.DIS);
  public final static Ton ES_11 = new Ton(Oktavlage.ELFGESTRICHEN,  Name.ES);
  public final static Ton E_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.E);
  public final static Ton EIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.EIS);
  public final static Ton FES_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.FES);
  public final static Ton F_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.F);
  public final static Ton FIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.FIS);
  public final static Ton GES_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.GES);
  public final static Ton G_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.G);
  public final static Ton GIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.GIS);
  public final static Ton AS_11 = new Ton(Oktavlage.ELFGESTRICHEN,  Name.AS);
  public final static Ton A_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.A);
  public final static Ton AIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.AIS);
  public final static Ton B_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.B);
  public final static Ton H_11 = new Ton(Oktavlage.ELFGESTRICHEN,   Name.H);
  public final static Ton HIS_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.HIS);
  public final static Ton CES_11 = new Ton(Oktavlage.ELFGESTRICHEN, Name.CES);
  // ZWOELFGESTRICHENE
  public final static Ton C_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.C);
  public final static Ton CIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.CIS);
  public final static Ton DES_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.DES);
  public final static Ton D_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.D);
  public final static Ton DIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.DIS);
  public final static Ton ES_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,  Name.ES);
  public final static Ton E_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.E);
  public final static Ton EIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.EIS);
  public final static Ton FES_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.FES);
  public final static Ton F_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.F);
  public final static Ton FIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.FIS);
  public final static Ton GES_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.GES);
  public final static Ton G_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.G);
  public final static Ton GIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.GIS);
  public final static Ton AS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,  Name.AS);
  public final static Ton A_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.A);
  public final static Ton AIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.AIS);
  public final static Ton B_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.B);
  public final static Ton H_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN,   Name.H);
  public final static Ton HIS_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.HIS);
  public final static Ton CES_12 = new Ton(Oktavlage.ZWOELFGESTRICHEN, Name.CES);

  private static final Ton UNBEKANNTER_TON = new Ton(Oktavlage.UNBEKANNT, Name.UNBEKANNT);
  private final static Logger _logger = Logger.getLogger(Tonumfang.class);

//  public static Ton getTonDurchKorrektenNamen()
//  {
//
//  }
  private static Map<Integer, Ton> tonVorratMap = new TreeMap<Integer, Ton>();


  // Ggf. in TonService auslagern.
//  public static Ton getTon(Integer xAbstandZumEingestrichenenC) throws Exception {
//    if (null == xAbstandZumEingestrichenenC)
//    {
//      throw new Exception("Tonumfang.getTon(): FEHLER: Abstand zum c' war null!");
//
//    }
//
//    // Sollte ebenfalls nicht erforderlich sein.
////    if ( null == tonVorratMap || tonVorratMap.isEmpty())
////    {
////      tonVorratInitialisieren();
////    }
//
//    Ton theTon = tonVorratMap.get(xAbstandZumEingestrichenenC);
//    if (null == theTon)
//    {
//      throw new Exception("Tonumfang.getTon(): Ton nicht in Tonvorrat-Map vorhanden! Abstand zu c' sollte sein: " + xAbstandZumEingestrichenenC);
//    }
//    return theTon;
//  }


  // Wrapper.
//  public static Ton getTon(Ton xTon)
//  {
//    // Sollte nicht erforderlich sein. Ggf. Exception.
////    if ( null == tonVorratMap || tonVorratMap.isEmpty())
////    {
////      tonVorratInitialisieren();
////    }
//    return tonVorratMap.get((getTon(xTon.getOktavlage(), xTon.getTonName()).getAbstandZumEingestrichenenC()));
//  }

  public static Ton getTon(Oktavlage xOktavlage, String xTonName)
  {
    if (xOktavlage == Oktavlage.SUBKONTRA)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_SUBKONTRA;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_SUBKONTRA;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_SUBKONTRA;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_SUBKONTRA;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_SUBKONTRA;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_SUBKONTRA;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_SUBKONTRA;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_SUBKONTRA;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_SUBKONTRA;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_SUBKONTRA;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_SUBKONTRA;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_SUBKONTRA;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_SUBKONTRA;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_SUBKONTRA;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_SUBKONTRA;
      }
    }
    else if(xOktavlage == Oktavlage.KONTRA)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_KONTRA;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_KONTRA;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_KONTRA;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_KONTRA;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_KONTRA;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_KONTRA;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_KONTRA;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_KONTRA;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_KONTRA;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_KONTRA;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_KONTRA;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_KONTRA;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_KONTRA;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_KONTRA;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_KONTRA;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_KONTRA;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_KONTRA;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_KONTRA;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_KONTRA;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_KONTRA;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_KONTRA;
      }
    }
    else if(xOktavlage == Oktavlage.GROSZE)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_GROSZ;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_GROSZ;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_GROSZ;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_GROSZ;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_GROSZ;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_GROSZ;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_GROSZ;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_GROSZ;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_GROSZ;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_GROSZ;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_GROSZ;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_GROSZ;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_GROSZ;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_GROSZ;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_GROSZ;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_GROSZ;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_GROSZ;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_GROSZ;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_GROSZ;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_GROSZ;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_GROSZ;
      }
    }
    else if(xOktavlage == Oktavlage.KLEINE)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_KLEIN;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_KLEIN;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_KLEIN;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_KLEIN;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_KLEIN;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_KLEIN;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_KLEIN;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_KLEIN;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_KLEIN;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_KLEIN;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_KLEIN;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_KLEIN;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_KLEIN;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_KLEIN;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_KLEIN;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_KLEIN;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_KLEIN;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_KLEIN;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_KLEIN;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_KLEIN;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_KLEIN;
      }
    }
    else if(xOktavlage == Oktavlage.EINGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_1;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_1;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_1;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_1;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_1;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_1;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_1;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_1;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_1;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_1;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_1;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_1;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_1;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_1;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_1;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_1;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_1;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_1;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_1;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_1;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_1;
      }
    }
    else if(xOktavlage == Oktavlage.ZWEIGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_2;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_2;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_2;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_2;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_2;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_2;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_2;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_2;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_2;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_2;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_2;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_2;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_2;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_2;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_2;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_2;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_2;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_2;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_2;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_2;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_2;
      }
    }
    else if(xOktavlage == Oktavlage.DREIGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_3;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_3;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_3;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_3;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_3;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_3;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_3;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_3;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_3;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_3;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_3;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_3;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_3;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_3;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_3;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_3;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_3;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_3;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_3;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_3;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_3;
      }
    }
    else if(xOktavlage == Oktavlage.VIERGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_4;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_4;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_4;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_4;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_4;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_4;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_4;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_4;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_4;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_4;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_4;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_4;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_4;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_4;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_4;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_4;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_4;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_4;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_4;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_4;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_4;
      }
    }
    else if(xOktavlage == Oktavlage.FUENFGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_5;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_5;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_5;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_5;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_5;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_5;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_5;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_5;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_5;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_5;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_5;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_5;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_5;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_5;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_5;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_5;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_5;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_5;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_5;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_5;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_5;
      }
    }
    else if(xOktavlage == Oktavlage.SECHSGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_6;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_6;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_6;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_6;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_6;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_6;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_6;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_6;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_6;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_6;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_6;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_6;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_6;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_6;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_6;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_6;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_6;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_6;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_6;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_6;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_6;
      }
    }
    else if(xOktavlage == Oktavlage.SIEBENGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_7;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_7;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_7;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_7;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_7;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_7;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_7;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_7;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_7;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_7;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_7;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_7;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_7;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_7;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_7;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_7;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_7;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_7;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_7;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_7;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_7;
      }
    }

    else if(xOktavlage == Oktavlage.ACHTGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_8;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_8;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_8;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_8;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_8;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_8;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_8;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_8;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_8;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_8;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_8;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_8;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_8;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_8;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_8;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_8;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_8;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_8;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_8;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_8;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_8;
      }
    }

    else if(xOktavlage == Oktavlage.NEUNGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_9;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_9;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_9;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_9;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_9;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_9;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_9;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_9;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_9;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_9;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_9;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_9;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_9;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_9;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_9;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_9;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_9;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_9;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_9;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_9;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_9;
      }
    }

    else if(xOktavlage == Oktavlage.ZEHNGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_10;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_10;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_10;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_10;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_10;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_10;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_10;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_10;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_10;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_10;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_10;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_10;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_10;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_10;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_10;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_10;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_10;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_10;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_10;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_10;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_10;
      }
    }

    else if(xOktavlage == Oktavlage.ELFGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_11;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_11;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_11;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_11;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_11;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_11;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_11;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_11;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_11;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_11;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_11;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_11;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_11;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_11;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_11;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_11;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_11;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_11;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_11;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_11;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_11;
      }
    }

    else if(xOktavlage == Oktavlage.ZWOELFGESTRICHEN)
    {
      if ( xTonName.equals(Name.CES))
      {
        return CES_12;
      }
      if ( xTonName.equals(Name.C))
      {
        return C_12;
      }
      if ( xTonName.equals(Name.CIS))
      {
        return CIS_12;
      }
      if ( xTonName.equals(Name.DES))
      {
        return DES_12;
      }
      if ( xTonName.equals(Name.D))
      {
        return D_12;
      }
      if ( xTonName.equals(Name.DIS))
      {
        return DIS_12;
      }
      if ( xTonName.equals(Name.ES))
      {
        return ES_12;
      }
      if ( xTonName.equals(Name.E))
      {
        return E_12;
      }
      if ( xTonName.equals(Name.EIS))
      {
        return EIS_12;
      }
      if ( xTonName.equals(Name.FES))
      {
        return FES_12;
      }
      if ( xTonName.equals(Name.F))
      {
        return F_12;
      }
      if ( xTonName.equals(Name.FIS))
      {
        return FIS_12;
      }
      if ( xTonName.equals(Name.GES))
      {
        return GES_12;
      }
      if ( xTonName.equals(Name.G))
      {
        return G_12;
      }
      if ( xTonName.equals(Name.GIS))
      {
        return GIS_12;
      }
      if ( xTonName.equals(Name.AS))
      {
        return AS_12;
      }
      if ( xTonName.equals(Name.A))
      {
        return A_12;
      }
      if ( xTonName.equals(Name.AIS))
      {
        return AIS_12;
      }
      if ( xTonName.equals(Name.B))
      {
        return B_12;
      }
      if ( xTonName.equals(Name.H))
      {
        return H_12;
      }
      if ( xTonName.equals(Name.HIS))
      {
        return HIS_12;
      }
    }
    // Schlecht:
    return UNBEKANNTER_TON;
  }

//  public static Ton getTonDurchAbstandC(Integer xAbstandC)
//  {
//
//  }

}
