package org.gresch.quintett;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.AesthetischeGewichtung;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.renderer.QuintettRenderer;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class KombinationsberechnungParameter {

  /* ******* DEFAULTWERTE ******* */
  public static final String CLI_DEFAULTWERT_MAX_ANZAHL_TOENE = "5";
  public static final String CLI_DEFAULTWERT_SORTIERUNG_INTERVALLINFORMATIONEN = "j";
  public static final String CLI_DEFAULTWERT_SORTIERUNG_KLANGSCHAERFE = "j";
  public static final String CLI_DEFAULTWERT_SORTIERUNG_AUSGABE = "j";
  public static final String CLI_DEFAULTWERT_GRUNDTON = "G";
  public static final String CLI_DEFAULTWERT_INTERVALLINFORMATIONEN = "11,01,06,02,05,10,07,04,03,08,09";
  public static final String CLI_DEFAULTWERT_DEBUG = "false";
  public static final String CLI_DEFAULTWERT_RENDERER = QuintettRenderer.LILYPOND_RENDERER;
  // private static final String CLI_DEFAULTWERT_RENDERER = QuintettRenderer.MIDI_RENDERER;
  public static final String CLI_DEFAULTWERT_PERSISTENZ_LADEN = "j";
  public static final String CLI_DEFAULTWERT_PERSISTENZ_SCHREIBEN = "j";
  public static final String CLI_DEFAULTWERT_DB_ERSTELLEN = "n";
  public static final String CLI_WARN_INTERVALLINFORMATIONEN =
    "Keine Intervallinformationen angegeben! Verwende Default: " + CLI_DEFAULTWERT_INTERVALLINFORMATIONEN;
  /* ******* CLI-INFORMATIONEN ******* */
  public static final String CLI_AUSGABE_INTERVALLINFORMATIONEN =
    "Gewichtung in Kommata angeben.\r\n Den Intervallen sind dabei folgende Schlüssel zugeordnet:\r\n\r\n" + " 01   =   KLEINE SEKUNDE\n"
      + " 02   =   GROSZE SEKUNDE\n" + " 03   =   KLEINE TERZ\n" + " 04   =   GROSZE TERZ\n" + " 05   =   QUARTE\n"
      + " 06   =   TRITONUS\n" + " 07   =   QUINTE\n" + " 08   =   KLEINE SEXTE\r\n" + " 09   =   GROSZE SEXTE\r\n"
      + " 10   =   KLEINE SEPTIME\r\n" + " 11   =   GROSZE SEPTIME\r\n\r\n\n" + ">>> \r\n"
      + "Beispiel (absteigend): 06,01,11,02,10,05,07,04,03,08,09\r\n\r\n" + ">>> \r\n" + "(Default: "
      + CLI_DEFAULTWERT_INTERVALLINFORMATIONEN + ")\r\n";
  public static final String CLI_AUSGABE_SORTIERUNG_INTERVALLINFORMATIONEN =
    "Absteigende Sortierung der Gewichtungsangabe? \r\nb   =   Absteigend:  \'zarteste\' Töne zuletzt (Default)\r\n"
      + "f   =   Aufsteigend: \'zarteste\' Töne zuerst\r\nn=nein=aufsteigend, j=ja=absteigend (Default = j).";
  public static final String CLI_AUSGABE_SORTIERUNG_KLANGSCHAERFE = "Absteigende Sortierung der Klangschärfe? Von Basstönen aufwärts oder von Soprantönen abwärts? n=nein=aufsteigend, j=ja=absteigend (Default = j)";
  public static final String CLI_AUSGABE_SORTIERUNG_AUSGABE = "Sortierung der Akkorde in der Ausgabe? Absteigend von den Akkorden höchster Klangschärfe oder aufsteigend, von den Akkorden niedrigster Klangschärfe. n=nein=aufsteigend, j=ja=absteigend (Default = j).";
  public static final String CLI_AUSGABE_MAX_ANZAHL_TOENE = "Maximale Anzahl Töne im Mehrklang. (Default: 5)";
  public static final String CLI_AUSGABE_GRUNDTON =
    "Grundton, wenn nicht angegeben. (Default: " + CLI_DEFAULTWERT_GRUNDTON + "). Möglichkeiten: C-D-E-F-G-A-H";
  public static final String CLI_AUSGABE_RENDERER = "Renderer. Konsole (=k), MIDI (=m), Lilypond (=l). (Default: " + CLI_DEFAULTWERT_RENDERER + ")\r\n";
  public static final String CLI_AUSGABE_PERSISTENZ_LADEN =
    "Sollen die Akkorde aus einer Datenbank geladen werden? n=nein, j=ja (Default = " + CLI_DEFAULTWERT_PERSISTENZ_LADEN + ").";
  public static final String CLI_AUSGABE_DB_ERSTELLEN =
    "Wenn die Akkorde gespeichert werden sollen - soll eine neue Datenbank erstellt werden? \nIn diesem Fall würde eine bestehende Datenbank überschrieben werden! n=nein, j=ja (Default = "
      + CLI_DEFAULTWERT_DB_ERSTELLEN + ").";
  public static final String CLI_AUSGABE_PERSISTENZ_SCHREIBEN =
    "Sollen die Akkorde in einer Datenbank gespeichert werden (zum späteren Laden)? n=nein, j=ja (Default = " + CLI_DEFAULTWERT_PERSISTENZ_SCHREIBEN
      + ").";
  /* ******* PARAMETER ******* */
  public static final String CLI_PARAMETER_MAX_ANZAHL_TOENE = "-t";
  public static final String CLI_PARAMETER_SORTIERUNG_INTERVALLINFORMATIONEN = "-si";
  public static final String CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE = "-sk";
  public static final String CLI_PARAMETER_SORTIERUNG_AUSGABE = "-su";
  public static final String CLI_PARAMETER_GRUNDTON = "-g";
  public static final String CLI_PARAMETER_INTERVALLINFORMATIONEN = "-a";
  public static final String CLI_PARAMETER_DEBUG = "-d";
  public static final String CLI_PARAMETER_RENDERER = "-r";
  public static final String CLI_PARAMETER_PERSISTENZ_LADEN = "-pl";
  public static final String CLI_PARAMETER_PERSISTENZ_SCHREIBEN = "-ps";
  public static final String CLI_PARAMETER_DB_ERSTELLEN = "-db";
  private final static Log log = LogFactory.getLog(KombinationsberechnungParameter.class);

  // Das alles mit Kohsukes Tamaguchis args4j
  Kombinationsberechnung kombinationsberechnung;

  //  private static final String CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE = "sk";
  //  private static final String CLI_PARAMETER_SORTIERUNG_AUSGABE = "su";
  //  private static final String CLI_PARAMETER_GRUNDTON = "g";
  //  private static final String CLI_PARAMETER_INTERVALLINFORMATIONEN = "a";
  //  private static final String CLI_PARAMETER_DEBUG = "d";
  //  private static final String CLI_PARAMETER_RENDERER = "r";
  //  private static final String CLI_PARAMETER_PERSISTENZ_LADEN = "pl";
  //  private static final String CLI_PARAMETER_PERSISTENZ_SCHREIBEN = "ps";
  //  private static final String CLI_PARAMETER_DB_ERSTELLEN = "db";

  @Option(name = CLI_PARAMETER_MAX_ANZAHL_TOENE, usage = "Maximale Anzahl Töne im Mehrklang. (Default: " + CLI_DEFAULTWERT_MAX_ANZAHL_TOENE + ")\r\n")
  int maxAnzahlToene = 5;

  @Option(name = CLI_PARAMETER_SORTIERUNG_KLANGSCHAERFE, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_SORTIERUNG_KLANGSCHAERFE)
  boolean hatAbsteigendeKlangschaerfeInformationen = CLI_DEFAULTWERT_SORTIERUNG_KLANGSCHAERFE.equalsIgnoreCase("j") ? true : false;

  @Option(name = CLI_PARAMETER_SORTIERUNG_INTERVALLINFORMATIONEN, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_SORTIERUNG_INTERVALLINFORMATIONEN)
  boolean hatAbsteigendeIntervallInformationen = CLI_DEFAULTWERT_SORTIERUNG_INTERVALLINFORMATIONEN.equalsIgnoreCase("j") ? true : false;

  @Option(name = CLI_PARAMETER_SORTIERUNG_AUSGABE, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_SORTIERUNG_AUSGABE)
  boolean hatAbsteigendeAusgabeInformationen = CLI_DEFAULTWERT_SORTIERUNG_AUSGABE.equalsIgnoreCase("j") ? true : false;

  @Option(name = CLI_PARAMETER_GRUNDTON, usage = CLI_AUSGABE_GRUNDTON)
  String grundtonString = CLI_DEFAULTWERT_GRUNDTON;

  @Option(name = CLI_PARAMETER_INTERVALLINFORMATIONEN, usage = CLI_AUSGABE_INTERVALLINFORMATIONEN)
  String intervallinformationenString = CLI_DEFAULTWERT_INTERVALLINFORMATIONEN;

  @Option(name = CLI_PARAMETER_RENDERER, usage = CLI_AUSGABE_RENDERER)
  String rendererString = CLI_DEFAULTWERT_RENDERER;

  @Option(name = CLI_PARAMETER_PERSISTENZ_LADEN, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_PERSISTENZ_LADEN)
  boolean hatPersistenzLaden = CLI_DEFAULTWERT_PERSISTENZ_LADEN.equalsIgnoreCase("f") ? true : false;

  @Option(name = CLI_PARAMETER_PERSISTENZ_SCHREIBEN, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_PERSISTENZ_SCHREIBEN)
  boolean hatPersistenzSchreiben = CLI_DEFAULTWERT_PERSISTENZ_SCHREIBEN.equalsIgnoreCase("j") ? true : false;

  @Option(name = CLI_PARAMETER_DB_ERSTELLEN, handler = KombinationsberechnungParameterJaNeinOptionHandler.class, usage = CLI_AUSGABE_DB_ERSTELLEN)
  boolean hatDatenbankErstellen = CLI_DEFAULTWERT_DB_ERSTELLEN.equalsIgnoreCase("j") ? true : false;

  // receives other command line parameters than options
  @Argument
  private List<String> arguments = new ArrayList<String>();

  public KombinationsberechnungParameter() {
    //    throw new RuntimeException("Wie auch immer. Diese Klasse darf nicht instantiiert werden.");
  }

  //  public static Kombinationsberechnung parameterAuswerten

  public static Kombinationsberechnung createDefaultKombinationsberechnung() {
    //    return parameterAuswerten(args);
    throw new RuntimeException("Noch nicht analysiert.");
  }

  public static Kombinationsberechnung parameterAuswerten(String[] args) {
    log.info("KombinationsberechnungParameter() - Parameter: " + StringUtils.stripAll(args));
    return new KombinationsberechnungParameter().processArguments(args);
  }

  /**
   * @param options
   */
  static void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("Kombinationsberechnung -a [-s -g -t]", options);
  }

  public Kombinationsberechnung processArguments(String[] args) {
    CmdLineParser parser = new CmdLineParser(this);
    // parser.setUsageWidth(180);

    try {
      // parse the arguments.
      parser.parseArgument(args);

      // you can parse additional arguments if you want.
      // parser.parseArgument("more","args");

      // after parsing arguments, you should check
      // if enough arguments are given.
      //      if (arguments.isEmpty())
      //        throw new CmdLineException("No argument is given");

    } catch (CmdLineException e) {
      // if there's a problem in the command line,
      // you'll get this exception. this will report
      // an error message.
      System.err.println(e.getMessage());
      // System.err.println("java SampleMain [options...] arguments...");
      // print the list of available options
      parser.printUsage(System.err);
      System.err.println();
      return null;
    }

    Kombinationsberechnung kombinationsberechnung = new Kombinationsberechnung();
    kombinationsberechnung.setMaxAnzahlToene(maxAnzahlToene);
    kombinationsberechnung.setHatAbsteigendeKlangschaerfe(hatAbsteigendeKlangschaerfeInformationen);
    kombinationsberechnung.setHatAbsteigendeIntervallinformationen(hatAbsteigendeIntervallInformationen);
    kombinationsberechnung.setHatAbsteigendeAusgabe(hatAbsteigendeAusgabeInformationen);
    kombinationsberechnung.setIntervallInformationen(intervallinformationenString);
    // Abgeleitet.
    kombinationsberechnung.setAesthetischeGewichtung(new AesthetischeGewichtung(intervallinformationenString, kombinationsberechnung));
    kombinationsberechnung.setBasisTon(new Ton(Oktavlage.GROSZE, grundtonString));
    kombinationsberechnung.setRenderer(rendererString);
    kombinationsberechnung.setHatPersistenzLaden(hatPersistenzLaden);
    kombinationsberechnung.setHatPersistenzSchreiben(hatPersistenzSchreiben);
    kombinationsberechnung.setHatDbErstellen(hatDatenbankErstellen);
    // Sonstiges
    kombinationsberechnung.setArguments(args);

    return kombinationsberechnung;
  }

}
