package org.gresch.quintett.service.util;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.gresch.quintett.service.TonService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tonumfangInitialisierer")
public class TonumfangInitialisiererImpl implements TonumfangInitialisierer
{

  @Resource(name = "tonService")
  TonService tonService;

  public TonumfangInitialisiererImpl()
  {
    // Beany baby.
  }

  @Override
  @PostConstruct
  @Transactional
  public void initialisiereTonumfang()
  {
    tonService.tonvorratInitialisieren();
  }

}
