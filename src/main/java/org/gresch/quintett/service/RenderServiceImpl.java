package org.gresch.quintett.service;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service("renderService")
public class RenderServiceImpl implements RenderService {

  @Resource(name = "kombinationsberechnungService")
  private KombinationsberechnungService kombinationsberechnungService;
  private File templateKopf = null;
  private File templateOberesSystem = null;
  private File templateUnteresSystem = null;

  public RenderServiceImpl() {
    // Beany baby
  }
  //private static final File _TEMPLATE_KOPF = new File(kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "resources" + kombinationsberechnungService.getKombinationsBerechnung().S + "kopf.lyt");
  //private static final File _TEMPLATE_OBERES_SYSTEM = new File(kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "resources" + kombinationsberechnungService.getKombinationsBerechnung().S + "oberes_system.lyt");
  //private static final File _TEMPLATE_UNTERES_SYSTEM = new File(kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "resources" + kombinationsberechnungService.getKombinationsBerechnung().S + "unteres_system.lyt");
  //private static final String _OBERES_SYSTEM_PFAD = kombinationsberechnungService.getKombinationsBerechnung().AUSGABEPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "oberes_system.temp";
  //private static final String _UNTERES_SYSTEM_PFAD = kombinationsberechnungService.getKombinationsBerechnung().AUSGABEPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "unteres_system.temp";
  //private static final String _ERGEBNIS_PFAD = kombinationsberechnungService.getKombinationsBerechnung().AUSGABEPFAD + kombinationsberechnungService.getKombinationsBerechnung().S + "Akkordkombinationen_";

  public String getErgebnisSystemPfad() {
    return Kombinationsberechnung.AUSGABEPFAD + Kombinationsberechnung.S + "Akkordkombinationen_";
  }

  public String getOberesSystemPfad() {
    return Kombinationsberechnung.AUSGABEPFAD + Kombinationsberechnung.S + "oberes_system.temp";
  }

  public File getTemplateKopf() {
    if (null == templateKopf) {
      templateKopf = new File(
        kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" + Kombinationsberechnung.S
          + "kopf.lyt");
    }
    return templateKopf;
  }

  public File getTemplateOberesSystem() {

    if (null == templateOberesSystem) {
      templateOberesSystem = new File(
        kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" + Kombinationsberechnung.S
          + "oberes_system.lyt");
    }

    return templateOberesSystem;
  }

  public File getTemplateUnteresSystem() {

    if (null == templateUnteresSystem) {
      templateUnteresSystem = new File(
        kombinationsberechnungService.getKombinationsBerechnung().ANWENDUNGSPFAD + Kombinationsberechnung.S + "resources" + Kombinationsberechnung.S
          + "unteres_system.lyt");
    }
    return templateUnteresSystem;
  }

  public String getUnteresSystemPfad() {
    return Kombinationsberechnung.AUSGABEPFAD + Kombinationsberechnung.S + "unteres_system.temp";

  }

}
