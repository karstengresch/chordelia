package org.gresch.quintett.service;

import static org.gresch.quintett.domain.tonmodell.Tonumfang.C_1;
import static org.gresch.quintett.domain.tonmodell.Tonumfang.ES_4;
import static org.gresch.quintett.domain.tonmodell.Tonumfang.MAX_ABSTAND_C_EINGESTRICHEN;
import static org.gresch.quintett.domain.tonmodell.Tonumfang.MIN_ABSTAND_C_EINGESTRICHEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.domain.tonmodell.Tonumfang;
import org.gresch.quintett.persistence.TonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @todo: Spring-Konfiguration so einrichten, dass TonumfangInitialisierer nicht instantiiert wird.
 *
 * @author Karsten Gresch
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TonServiceTest
{

  @Resource(name = "tonService")
  TonService tonService;

  @Resource(name = "tonDao")
  TonDao tonDao;

  @Test
  public void testSetup()
  {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'tonService' sollte instantiiert sein.", tonService);
    assertNotNull("Bean 'tonDao' sollte instantiiert sein.", tonDao);
  }

  @Test
  public void testTonvorratInitialisieren()
  {
    tonService.tonvorratInitialisieren();
    Ton tiefsterTon = tonDao.find(MIN_ABSTAND_C_EINGESTRICHEN);
    Ton hoechsterTon = tonDao.find(MAX_ABSTAND_C_EINGESTRICHEN);
    Ton es4 = tonDao.find(ES_4.getAbstandZumEingestrichenenC());
    Ton c1 = tonDao.find(C_1.getAbstandZumEingestrichenenC());
    assertNotNull("Tiefster Ton sollte vorhanden sein.", tiefsterTon);
    assertNotNull("Hoechster Ton sollte vorhanden sein.", hoechsterTon);
    assertNotNull("es'''' sollte vorhanden sein.", es4);
    assertNotNull("c' sollte vorhanden sein.", c1);

  }

  @Test
  public void testGetTon()
  {
    Ton c1 = tonDao.find(0);
    assertEquals("c1 sollte c1 sein (Abstand == id == 0 fuer eingestrichenes c", C_1, c1);
    Ton c1Nr2 = tonService.getTonByExample(Tonumfang.C_1);
    assertEquals("c1 sollte c1 sein (Abstand == id == 0 fuer eingestrichenes c", c1, c1Nr2);
  }

}
