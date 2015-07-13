package org.gresch.quintett.service.util;

import org.gresch.quintett.service.TonService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("tonumfangInitialisierer")
public class TonumfangInitialisiererImpl implements TonumfangInitialisierer {

  @PersistenceContext
  EntityManager entityManager;

  @Resource(name = "tonService")
  TonService tonService;

  public TonumfangInitialisiererImpl() {
    // Beany baby.
  }

  @Override
  @PostConstruct
  @Transactional
  public void initialisiereTonumfang() {
    tonService.tonvorratInitialisieren();
  }

}
