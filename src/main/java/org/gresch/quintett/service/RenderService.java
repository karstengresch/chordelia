package org.gresch.quintett.service;

import java.io.File;

public interface RenderService
{
  File getTemplateKopf();
  File getTemplateOberesSystem();
  File getTemplateUnteresSystem();
  String getOberesSystemPfad();
  String getUnteresSystemPfad();
  String getErgebnisSystemPfad();

}
