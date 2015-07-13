package org.gresch.quintett.service;

import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component("kombinationsberechnungTestHelper")
public class KombinationsberechnungTestHelper {
  @Resource(name = "kombinationsberechnungService")
  KombinationsberechnungService kombinationsberechnungService;

  @Resource(name = "tonService")
  TonService tonService;

  public KombinationsberechnungTestHelper() {
    // Beany baby
  }

  @Transactional
  public void initialiseKombinationsberechnung() {
    Kombinationsberechnung kombinationsberechnung = KombinationsberechnungParameter.parameterAuswerten(new String[]{"-t", "3", "-db", "j"});
    kombinationsberechnungService.saveKombinationsBerechnung(kombinationsberechnung);
  }

}
