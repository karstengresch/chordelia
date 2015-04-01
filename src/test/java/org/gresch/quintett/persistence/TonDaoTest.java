package org.gresch.quintett.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.gresch.quintett.BasisTon;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.domain.tonmodell.Name;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.domain.tonmodell.Tonumfang;
import org.gresch.quintett.service.TonService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-main.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TonDaoTest
{

  @Resource(name = "tonDao")
  private TonDao tonDao;

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Test
  public void testSetup()
  {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'tonDao' sollte instantiiert sein.", tonDao);
    assertNotNull("Bean 'sessionFactory' sollte instantiiert sein.", sessionFactory);
  }

  @Test
  public void testMakePersistentReadOnly()
  {
    //    String gewichtungsString = "11,10,09,08,07,06,05,04,03,02,01"; // Unrealistisch, aber gut zu prüfen
//    Kombinationsberechnung kombinationsberechnung = Kombinationsberechnung.getInstance();
//    kombinationsberechnung.setHatAbsteigendeIntervallinformationen(true);
    //    AesthetischeGewichtung testGewichtung = new AesthetischeGewichtung(gewichtungsString);

    Ton basisTon = new Ton(Oktavlage.GROSZE, Name.C);
    Ton ton1 = Tonumfang.getTon(Oktavlage.GROSZE, Name.C);
    ton1.setId(ton1.getAbstandZumEingestrichenenC());
    tonDao.makePersistentReadOnly(ton1);
    sessionFactory.getCurrentSession().flush();
    ton1.setAbstandZumEingestrichenenC(5);
    assertFalse(sessionFactory.getCurrentSession().isDirty());
    tonDao.refresh(ton1);
    assertFalse("Trotz Aenderung (auszer bei der Id) sollte Hibernate das Entity-Objekt vom Dirty-Checking ausgeschlossen haben.", sessionFactory.getCurrentSession().isDirty());
  }

  @Test
  public void testFindByExample()
  {
    Ton c1 = tonDao.findByExample(Tonumfang.C_1);
    assertEquals("Lokaler c1 sollte dem gefundenen entsprechen", Tonumfang.C_1, c1);
  }

}
