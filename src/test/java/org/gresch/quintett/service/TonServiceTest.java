package org.gresch.quintett.service;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.domain.tonmodell.Tonumfang;
import org.gresch.quintett.persistence.TonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.gresch.quintett.domain.tonmodell.Tonumfang.*;
import static org.junit.Assert.*;

/**
 * @author Karsten Gresch
 * @todo: Spring-Konfiguration so einrichten, dass TonumfangInitialisierer nicht instantiiert wird.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-main-test.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TonServiceTest {

  @Resource(name = "tonService")
  TonService tonService;

  @Resource(name = "tonDao")
  TonDao tonDao;

  @Test
  public void testSetup() {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'tonService' sollte instantiiert sein.", tonService);
    assertNotNull("Bean 'tonDao' sollte instantiiert sein.", tonDao);
  }

  @Test
  public void testTonvorratInitialisieren() {
    tonService.tonvorratInitialisieren();
    Ton tiefsterTon = (Ton) tonDao.findOne(MIN_ABSTAND_C_EINGESTRICHEN);
    Ton hoechsterTon = (Ton) tonDao.findOne(MAX_ABSTAND_C_EINGESTRICHEN);
    Ton es4 = (Ton) tonDao.findOne(ES_4.getAbstandZumEingestrichenenC());
    Ton c1 = (Ton) tonDao.findOne(C_1.getAbstandZumEingestrichenenC());
    assertNotNull("Tiefster Ton sollte vorhanden sein.", tiefsterTon);
    assertNotNull("Hoechster Ton sollte vorhanden sein.", hoechsterTon);
    assertNotNull("es'''' sollte vorhanden sein.", es4);
    assertNotNull("c' sollte vorhanden sein.", c1);

  }

  @Test
  public void testGetTon() {
    Ton c1 = (Ton) tonDao.findOne(0);
    assertEquals("c1 sollte c1 sein (Abstand == id == 0 fuer eingestrichenes c", C_1, c1);
    Ton c1Nr2 = tonService.getTonByExample(Tonumfang.C_1);
    assertEquals("c1 sollte c1 sein (Abstand == id == 0 fuer eingestrichenes c", c1, c1Nr2);
  }

}
