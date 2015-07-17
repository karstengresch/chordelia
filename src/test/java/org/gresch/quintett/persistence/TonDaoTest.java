package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Name;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.domain.tonmodell.Tonumfang;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-main-test.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class})
// @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TonDaoTest {

  @PersistenceContext
  EntityManager entityManager;

  @Resource(name = "tonDao")
  private TonDao tonDao;


  @Test
  public void testSetup() {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'tonDao' sollte instantiiert sein.", tonDao);
    assertNotNull("Bean 'entityManager' sollte instantiiert sein.", entityManager);
  }

  @Test
  public void testMakePersistentReadOnly() {
    //    String gewichtungsString = "11,10,09,08,07,06,05,04,03,02,01"; // Unrealistisch, aber gut zu prüfen
    //    Kombinationsberechnung kombinationsberechnung = Kombinationsberechnung.getInstance();
    //    kombinationsberechnung.setHatAbsteigendeIntervallinformationen(true);
    //    AesthetischeGewichtung testGewichtung = new AesthetischeGewichtung(gewichtungsString);

    Ton basisTon = new Ton(Oktavlage.GROSZE, Name.C);
    Ton ton1 = Tonumfang.getTon(Oktavlage.GROSZE, Name.C);
    ton1.setId(ton1.getAbstandZumEingestrichenenC());
    tonDao.makePersistentReadOnly(ton1);
    entityManager.unwrap(SessionFactory.class).getCurrentSession().flush();
    ton1.setAbstandZumEingestrichenenC(5);
    assertFalse(entityManager.unwrap(SessionFactory.class).getCurrentSession().isDirty());
    // tonDao.(ton1);
    assertFalse("Trotz Aenderung (auszer bei der Id) sollte Hibernate das Entity-Objekt vom Dirty-Checking ausgeschlossen haben.",
      entityManager.unwrap(SessionFactory.class).getCurrentSession().isDirty());
  }

  @Test
  public void testFindByExample() {
    Ton c1 = tonDao.findByExample(Tonumfang.C_1);
    assertEquals("Lokaler c1 sollte dem gefundenen entsprechen", Tonumfang.C_1, c1);
  }

}
