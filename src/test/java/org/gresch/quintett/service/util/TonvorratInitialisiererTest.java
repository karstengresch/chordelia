package org.gresch.quintett.service.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.gresch.quintett.persistence.TonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TonvorratInitialisiererTest
{

  @Resource(name = "tonumfangInitialisierer")
  TonumfangInitialisierer tonumfangInitialisierer;

  @Resource(name = "tonDao")
  TonDao tonDao;

  @Test
  public void testSetup()
  {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'tonumfangInitialisierer' sollte instantiiert sein.", tonumfangInitialisierer);
    assertNotNull("Bean 'tonDao' sollte instantiiert sein.", tonDao);
  }

}
