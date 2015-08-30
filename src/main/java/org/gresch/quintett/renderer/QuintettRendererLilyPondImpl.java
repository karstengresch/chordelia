package org.gresch.quintett.renderer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.domain.kombination.AkkordIdRangeZwoelftonklaenge;
import org.gresch.quintett.domain.tonmodell.Akkord;
import org.gresch.quintett.domain.tonmodell.Name;
import org.gresch.quintett.service.AkkordKombinationenService;
import org.gresch.quintett.service.RenderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;

@Component("lilypondRenderer")
public class QuintettRendererLilyPondImpl implements QuintettRenderer {
  private static final String UTF_8 = "UTF-8";
  private static final String LEERZEICHEN = " ";
  private final static Log log = LogFactory.getLog(QuintettRendererLilyPondImpl.class);
  // TODO Check final
  // private static final File _TEMPLATE_KOPF = new File(Kombinationsberechnung.ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" +
  // Kombinationsberechnung.S + "kopf.lyt");
  // private static final File _TEMPLATE_OBERES_SYSTEM = new File(Kombinationsberechnung.ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" +
  // Kombinationsberechnung.S + "oberes_system.lyt");
  // private static final File _TEMPLATE_UNTERES_SYSTEM = new File(Kombinationsberechnung.ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" +
  // Kombinationsberechnung.S + "unteres_system.lyt");
  // private static final String _OBERES_SYSTEM_PFAD = Kombinationsberechnung.TEMPORAERES_VERZEICHNIS_PFAD + Kombinationsberechnung.S + "oberes_system.temp";
  // private static final String _UNTERES_SYSTEM_PFAD = Kombinationsberechnung.TEMPORAERES_VERZEICHNIS_PFAD + Kombinationsberechnung.S + "unteres_system.temp";
  // private static final String _ERGEBNIS_PFAD = Kombinationsberechnung.TEMPORAERES_VERZEICHNIS_PFAD + Kombinationsberechnung.S + "Akkordkombinationen_";
  private static final String _HIDE_NOTES = " \\hideNotes ";
  private static final String _UNHIDE_NOTES = " \\unHideNotes ";
  private static final String _LEERSTRING = "";
  private static final String _AUSRUFUNGSZEICHEN = "!";
  @SuppressWarnings("unused")
  private static final int START_ERSETZPOSITION_OBEN = 11;
  @SuppressWarnings("unused")
  private static final int START_ERSETZPOSITION_UNTEN = 11;
  @SuppressWarnings("unused")
  private static final int ENDE_ERSETZPOSITION_OBEN = 14;
  @SuppressWarnings("unused")
  private static final int ENDE_ERSETZPOSITION_UNTEN = 14;
  @SuppressWarnings("unused")
  private static BufferedReader _bufferedReaderKopf;
  @SuppressWarnings("unused")
  private static BufferedReader _bufferedReaderOberesSystem;
  @SuppressWarnings("unused")
  private static BufferedReader _bufferedReaderUnteresSystem;
  @SuppressWarnings("unused")
  private static BufferedReader _bufferedReaderErgebnis;
  private static BufferedReader _bufferedReaderTemp;
  @SuppressWarnings("unused")
  private static BufferedWriter _bufferedWriterKopf;
  private static BufferedWriter _bufferedWriterOberesSystem;
  private static BufferedWriter _bufferedWriterUnteresSystem;
  private static BufferedWriter _bufferedWriterErgebnis;
  private static StringBuilder _pufferOberesSystem = new StringBuilder(500);
  private static StringBuilder _pufferUnteresSystem = new StringBuilder(500);
  private static StringBuilder _rueckgabe = new StringBuilder(500);
  private static String _zeile = "";
  private static StringBuilder _kopfMuster = null;
  private static String ZU_ERSETZENDER_STRING = "c c c";
  private final static StringBuilder _obereToeneMuster = new StringBuilder("    \\bar \"||\" <" + ZU_ERSETZENDER_STRING + ">1");
  private final static StringBuilder _untereToeneMuster = new StringBuilder("    \\bar \"||\" <" + ZU_ERSETZENDER_STRING + ">1_");
  private static StringBuilder _fuszMuster;
  private static DecimalFormat _akkordNummernFormat = new DecimalFormat("0000");
  private static StringBuilder _tempObereToene = new StringBuilder(100);
  private static StringBuilder _tempUntereToene = new StringBuilder(100);
  private static Boolean hideNotes = false;
  private static Boolean unHideNotes = true;
  private static String hideString = _LEERSTRING;
  private static String unHideString = _LEERSTRING;

  /**
   * Spring-Beans START
   **/
  @Resource(name = "renderService")
  private RenderService renderService;

  @Resource(name = "akkordKombinationenService")
  private AkkordKombinationenService akkordKombinationenService;

  /**
   * Spring-Beans ENDe
   **/
  public QuintettRendererLilyPondImpl() {
    // Beany baby
  }

  //  // TODO Injizieren oder Kombinationen über Service
  //  @Override
  //  public void rendereKombinationen(Akkordkombinationen xAkkordKombinationen)
  //  {
  //    if (log.isDebugEnabled())
  //    {
  //      log.debug("LilyPondRenderer.rendereKombinationen(): Aufgerufen!");
  //    }
  //    // TODO: Switch, ob eine Datei für alle Kombinationen oder mehrere Dateien (default)
  //    rendereKombinationenInDatei(xAkkordKombinationen);
  //    // TODO Hier Lilipond aufrufen und pdf erstellen.
  //  }

  /**
   * Fraglich, was das bewirken soll.
   */
  private static StringBuilder getContent(File xFile) {
    _rueckgabe.delete(0, _rueckgabe.length());
    _zeile = "";
    try {
      _bufferedReaderTemp = null;
      _bufferedReaderTemp = new BufferedReader(new FileReader(xFile));
      while ((_zeile = _bufferedReaderTemp.readLine()) != null) {
        _rueckgabe.append(_zeile);
        _rueckgabe.append('\n');
      }
      _bufferedReaderTemp.close();
    } catch (IOException e) {
      log.error("LilyPondRenderer.getContent(): Fehler beim Lesen der Datei.", e);
    }
    return _rueckgabe;
  }

  //  @Override
  //  public File rendereKombinationenInDatei(Akkordkombinationen xAkkordKombinationen)
  //  {
  //    return rendereKombinationenInDatei(xAkkordKombinationen, "");
  //  }

  /**
   * Erstellt die Lilypond-Tonnamen. _AUSRUFUNGSZEICHEN für erzwungene Auflösungszeichen.
   */
  private static void getrenntenStringVorbereiten(Akkord _akkord) {
    int i = 0;
    _tempObereToene.delete(0, 100);
    _tempUntereToene.delete(0, 100);
    int splitTon = Akkord.getErsterTonUeberEingestrichenemC(_akkord.getTonList());
    // boolean klangschaerfeGesetzt = false;
    for (i = 0; i < _akkord.getTonList().size(); i++) {
      if (splitTon > -1 && (splitTon == i || i > splitTon)) // Jetzt darüber
      {
        if (hideNotes == true && splitTon == i) {
          hideNotes = false;
          unHideNotes = true;
        }
        _tempObereToene.append(Name.getLilyPondTonName(_akkord.getTonList().get(i)));
        _tempObereToene.append(_AUSRUFUNGSZEICHEN);
        _tempObereToene.append(LEERZEICHEN);

      } else {
        if (hideNotes == false) {
          hideNotes = true;
          unHideNotes = false;
        }
        _tempUntereToene.append(Name.getLilyPondTonName(_akkord.getTonList().get(i)));
        // if (! klangschaerfeGesetzt)
        // {
        //
        // klangschaerfeGesetzt = true;
        // }
        _tempUntereToene.append(_AUSRUFUNGSZEICHEN);
        _tempUntereToene.append(LEERZEICHEN);
      }

    }
    if (log.isDebugEnabled()) {
      log.debug("LilyPondRenderer.getrenntenStringVorbereiten(): _tempObereToene: " + _tempObereToene.toString());
      log.debug("LilyPondRenderer.getrenntenStringVorbereiten(): _tempUntereToene: " + _tempUntereToene.toString());
    }
  }

  @SuppressWarnings("unused")
  private static void fuegeDateienZusammen(String[] arg) {
    int nbFiles = arg.length;
    // Create output stream
    PrintWriter saveAs;
    try {
      saveAs = new PrintWriter(new FileOutputStream(arg[0]));
      // Process all files that are not the destination file
      for (int i = 1; i < nbFiles; i++) {
        System.out.print("Processing " + arg[i] + "... ");
        // Create input stream
        BufferedReader readBuff = new BufferedReader(new FileReader(arg[i]));
        // Read each line from the input file
        String line = readBuff.readLine();
        while (line != null) {
          saveAs.println(line);
          line = readBuff.readLine();
        }
        readBuff.close();
        System.out.println(" DONE");
      }
      saveAs.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void rendereKombinationenBisAnzahlToene(Integer maxAnzahlToene, String switchString) {
    // TODO Auto-generated method stub

  }

  @Override
  public void rendereKombinationenFuerAnzahlToene(Integer anzahlToene, String switchString) {
    // TODO Auto-generated method stub

  }

  @Override
  public void rendereKombinationenNachAkkordIdList(List<Integer> akkordIdList, String switchString) {
    rendereKombinationenInDatei(akkordIdList, "");

  }

  @Override
  public void rendereKombinationenNachAkkordIdRange(int minAkkordId, int maxAkkordId, String switchString) {
    //    List<Integer> akkordIdList = new ArrayList<Integer>();
    //
    //    // TODO Je nach Batchblockgröße aufteilen und via AkkordId-Liste erstellen
    //    for (int i = minAkkordId; i <= maxAkkordId; i++)
    //    {
    //      akkordIdList.add(i);
    //    }
    List<Integer> akkordIdList = akkordKombinationenService.getAkkordIdsByRange(minAkkordId, maxAkkordId, true, true);


    if(CollectionUtils.isNotEmpty(akkordIdList)) {
      rendereKombinationenInDatei(akkordIdList, switchString);
    } else {
      throw new RuntimeException("Were not able to get the akkord list!");
    }

  }

  protected File rendereKombinationenInDatei(List<Integer> xAkkordList, String xSwitchString) {
    //    TreeSet<Akkord> _akkordSet;
    // assert(null != xAkkordList, "Cannot render null list - no chords given!");
    Akkord _akkord;
    int i = 0;
    int counter = AkkordIdRangeZwoelftonklaenge.anzahlToeneZuId(xAkkordList.get(xAkkordList.size() - 1));
    String _anzahlToeneString = _LEERSTRING;
    String ergebnisPfad = renderService.getErgebnisSystemPfad();

    // Nur einmal initialisieren -> TODO: Statisch/Final?
    _kopfMuster = null;
    _kopfMuster = new StringBuilder(500);
    _kopfMuster.append(getContent(renderService.getTemplateKopf()));
    _fuszMuster = new StringBuilder(">>" + xSwitchString + "\n}");
    if (log.isDebugEnabled()) {
      log.debug("LilyPondRenderer.rendereKombinationenInDatei(): Aufgerufen!");

    }
    //    for (Integer akkordId : xAkkordList)
    //    {
    //      counter++;
    //      if (log.isDebugEnabled())
    //      {
    //        log.debug("LilyPondRenderer.rendereKombinationenInDatei(): counter: " + counter);
    //      }
    //      log.info("Ausgabe für Akkord-Id: " + akkordId);
    // ### SERVICE
    //      _akkordSet = Akkordkombinationen.getAkkordKombinationen().get(key);
    //      log.info("Anzahl Kombinationen: " + _akkordSet.size());

    // Vorlagen holen

    // Inhalt zur Vermeidung von Verdoppelungen löschen
    //
    _pufferUnteresSystem.delete(0, _pufferUnteresSystem.length());
    _pufferOberesSystem.delete(0, _pufferOberesSystem.length());
    _pufferOberesSystem.append(getContent(renderService.getTemplateOberesSystem()));
    _pufferUnteresSystem.append(getContent(renderService.getTemplateUnteresSystem()));
    // Writer intialisieren
    try {
      // TODO Format Counter -> printf!
      _bufferedWriterErgebnis = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ergebnisPfad + (counter) + "_Toene.ly"), UTF_8));
      _bufferedWriterErgebnis.append(_kopfMuster.toString());
      _bufferedWriterOberesSystem = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(renderService.getOberesSystemPfad()), UTF_8));
      _bufferedWriterOberesSystem.append(_pufferOberesSystem.toString());
      _bufferedWriterUnteresSystem = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(renderService.getUnteresSystemPfad()), UTF_8));
      _bufferedWriterUnteresSystem.append(_pufferUnteresSystem.toString());

      int _vorherigeAnzahlToeneZaehler = 0;
      int _anzahlToeneZaehler = 0;

      boolean _klangschaerfeGeaendert = false;
      int _klangschaerfe = -1;
      int _vorherigeKlangschaerfe = -1;
      int _klangschaerfenZaehler = 0;

      // ******* Akkord einfügen *******
      // for (i = 0; i < _akkordSet.length; i++)

      for (Integer akkordId : xAkkordList) {
        if (log.isDebugEnabled()) {
          log.debug("LilyPondRenderer.rendereKombinationenInDatei(): Akkord[]-Schleife-Nr.: " + i);
        }
        _akkord = akkordKombinationenService.getAkkordById(akkordId);
        if (null != _akkord) // Notlösung! TODO Prüfen, weshalb null-Wert in letztem Akkord.
        {
          _vorherigeAnzahlToeneZaehler = _akkord.getAnzahlToene();
          _klangschaerfe = _akkord.getKlangschaerfe();
          if (i == 0) {
            _anzahlToeneZaehler = _vorherigeAnzahlToeneZaehler;
            _vorherigeKlangschaerfe = _klangschaerfe;
          }

          if (_vorherigeAnzahlToeneZaehler - _anzahlToeneZaehler == 0) {
            _klangschaerfeGeaendert = (_klangschaerfe == _vorherigeKlangschaerfe) ? false : true;
            _vorherigeKlangschaerfe = _klangschaerfe;
          }

          if ((_vorherigeAnzahlToeneZaehler - _anzahlToeneZaehler) != 0) {
            hideString = _LEERSTRING;
            unHideString = _LEERSTRING;
          }
          // Ab zweitem Schleifendurchlauf.
          if (i > 0) {
            _anzahlToeneZaehler = _akkord.getAnzahlToene();
          }

          getrenntenStringVorbereiten(_akkord);

          _bufferedWriterOberesSystem.append(getOberenErsetzenString(_akkord));
          // TODO nur für Developer-Debug!
          // _bufferedWriterOberesSystem.append("_\"");
          // !!! 0-terminiert, hochsetzen
          // _bufferedWriterOberesSystem.append(_akkordNummernFormat.format(i+1));
          // _bufferedWriterOberesSystem.append("\" ");

          if ((_vorherigeAnzahlToeneZaehler - _anzahlToeneZaehler) != 0) {
            _anzahlToeneString = "^\"Anzahl Töne: " + _vorherigeAnzahlToeneZaehler + "\"" + LEERZEICHEN;

            if (log.isDebugEnabled()) {
              log.debug("LilyPondRenderer.rendereKombinationenInDatei(): _anzahlToeneString: " + _anzahlToeneString);
            }
            _bufferedWriterOberesSystem.append(_anzahlToeneString);
          }

          // _bufferedWriterOberesSystem.flush();
          _bufferedWriterUnteresSystem.append(getUnterenErsetzenString(_akkord));
          // _bufferedWriterUnteresSystem.append("\"");
          _bufferedWriterUnteresSystem.append("\\markup \\tiny { \\center-align { \"");
          // !!! 0-terminiert, hochsetzen
          _bufferedWriterUnteresSystem.append(String.valueOf(_klangschaerfe));

          // \markup { \center-align {

          _bufferedWriterUnteresSystem.append("-");

          _klangschaerfenZaehler = _klangschaerfeGeaendert ? 1 : ++_klangschaerfenZaehler;

          _bufferedWriterUnteresSystem.append(_akkordNummernFormat.format(_klangschaerfenZaehler));
          // _bufferedWriterUnteresSystem.append(")");
          // _bufferedWriterUnteresSystem.append(")");
          if (log.isDebugEnabled()) {
            log.debug("LilyPondRenderer.rendereKombinationenInDatei():  AkkordNummer:" + _akkordNummernFormat.format(i + 1));
          }
          _bufferedWriterUnteresSystem.append(" \"} }");

          // Kein Effekt beim Problem mit Abbruch
          // if(i%26 == 0)
          // {
          // _bufferedWriterOberesSystem.append(" \\break ");
          // _bufferedWriterUnteresSystem.append(" \\break ");
          // }

          // warum 8???
          if (i % 8 == 0) {
            _bufferedWriterOberesSystem.append("\n");
            _bufferedWriterUnteresSystem.append("\n");
          }
        }
        i++;
      }
      // TODO an oberes und unteres schließende geschweifte Klammer

      // TODO oberes und unteres System sowie Fußzeile an Ergebnisdatei hängen.
      // ggf. in mehrere Dateien aufteilen, wenn Lilypond ab 5-Ton-Klängen versagt.
      _bufferedWriterOberesSystem.append("\n}");
      _bufferedWriterUnteresSystem.append("\n}");
      _bufferedWriterOberesSystem.close();
      _bufferedWriterUnteresSystem.close();
      _bufferedWriterErgebnis.append(getContent(new File(renderService.getOberesSystemPfad())));
      _bufferedWriterErgebnis.append(getContent(new File(renderService.getUnteresSystemPfad())));
      _bufferedWriterErgebnis.append(_fuszMuster.toString());
      _bufferedWriterErgebnis.close();
      log.info("LilyPondRenderer.rendereKombinationenInDatei(): Ausgabe erfolgte in: " + ergebnisPfad + (counter) + "_Toene.ly");
      // FileConcatenator.concatenateFiles({}, );
      // TODO lösche temporäre Dateien
      new File(renderService.getOberesSystemPfad()).deleteOnExit();
      new File(renderService.getUnteresSystemPfad()).deleteOnExit();
    } catch (IOException e) {
      log.error("LilyPondRenderer.rendereKombinationenInDatei(): Fehler beim Erstellen der Datei!: ", e);
    }
    //    }
    // XXX In jedem Fall erst erstellen, um auf Exception reagieren zu können.
    // Ebenso verfügbaren Plattenplatz prüfen

    return new File(ergebnisPfad);
  }

  private StringBuilder getOberenErsetzenString(Akkord xAkkord) {
    _pufferOberesSystem.delete(0, _pufferOberesSystem.length());

    if (hideNotes == true && _LEERSTRING.equals(hideString)) {
      unHideNotes = false;
      unHideString = _LEERSTRING;
      hideString = _HIDE_NOTES;
      _pufferOberesSystem.append(hideString);
    }

    if (unHideNotes == true && unHideString.equals(_LEERSTRING)) {
      hideNotes = false;
      hideString = _LEERSTRING;
      unHideString = _UNHIDE_NOTES;
      _pufferOberesSystem.append(unHideString);
    }

    if (_tempObereToene.length() > 0) {
      _pufferOberesSystem.append(_obereToeneMuster.toString().replace(ZU_ERSETZENDER_STRING, _tempObereToene));
    } else {
      _pufferOberesSystem.append(_obereToeneMuster);
    }
    if (log.isDebugEnabled()) {
      log.debug("LilyPondRenderer.getOberenErsetzenString(): _pufferOberesSystem: " + _pufferOberesSystem);
    }
    return _pufferOberesSystem;
  }

  //  @Override
  //  public void rendereKombinationenInDatei(Akkordkombinationen xAkkordKombinationen, File xFile)
  //  {
  //  }

  private StringBuilder getUnterenErsetzenString(Akkord xAkkord) {
    // leeren
    _pufferUnteresSystem.delete(0, _pufferUnteresSystem.length());
    _pufferUnteresSystem.append(_untereToeneMuster.toString().replace(ZU_ERSETZENDER_STRING, _tempUntereToene));
    if (log.isDebugEnabled()) {
      log.debug("LilyPondRenderer.getUnterenErsetzenString(): _pufferUnteresSystem: " + _pufferUnteresSystem);
    }
    return _pufferUnteresSystem;
  }

  // private static void rendereKombinationen() {
  // }
}
