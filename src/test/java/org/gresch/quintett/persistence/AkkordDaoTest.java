package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.kombination.AesthetischeGewichtung;
import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.gresch.quintett.domain.tonmodell.*;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-main-test.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class})
@Transactional
public class AkkordDaoTest {

  @Resource(name = "akkordDao")
  private AkkordDao akkordDao;

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Test
  public void testSetup() {
    assertTrue("Die Spring-Konfiguration sollte funktionieren.", true);
    assertNotNull("Bean 'akkordDao' sollte instantiiert sein.", akkordDao);
    assertNotNull("Bean 'sessionFactory' sollte instantiiert sein.", sessionFactory);
  }

  @Test
  public void testMakePersistentReadOnly() {

    String gewichtungsString = "11,10,09,08,07,06,05,04,03,02,01"; // Unrealistisch, aber gut zu prüfen
    //    Kombinationsberechnung kombinationsberechnung = Kombinationsberechnung.getInstance();
    //    kombinationsberechnung.setHatAbsteigendeIntervallinformationen(true);
    AesthetischeGewichtung testGewichtung = new AesthetischeGewichtung(gewichtungsString, new Kombinationsberechnung());

    Ton basisTon = new Ton(Oktavlage.GROSZE, Name.C);
    Ton ton1 = Tonumfang.getTon(Oktavlage.GROSZE, Name.C);
    //    ton1.setAbstandBasisTonDurchAbstandZumEingestrichenenC(basisTon);
    ton1.setId(10);
    Ton ton2 = Tonumfang.getTon(Oktavlage.GROSZE, Name.G);
    //    ton2.setAbstandBasisTonDurchAbstandZumEingestrichenenC(basisTon);
    ton2.setId(20);
    Ton ton3 = Tonumfang.getTon(Oktavlage.KLEINE, Name.D);
    //    ton3.setAbstandBasisTonDurchAbstandZumEingestrichenenC(basisTon);
    ton3.setId(30);
    List<Ton> tonList = new ArrayList<Ton>();
    tonList.add(ton1);
    tonList.add(ton2);
    tonList.add(ton3);
    Akkord testAkkord = new Akkord();
    testAkkord.setTonList(tonList);
    // TODO assert
    assertTrue("Akkord mit der genannten Id sollte noch nicht vorhanden sein.", null == akkordDao.find(Integer.valueOf(103)));
    testAkkord.setId(Integer.valueOf(103));
    testAkkord.setAnzahlToene(3);
    //    testAkkord.setKlangschaerfe(AkkordkombinationenBerechnungServiceHelper.getKlangschaerfe(tonList, testGewichtung));
    //    testAkkord.set
    akkordDao.makePersistentReadOnly(testAkkord);
    sessionFactory.getCurrentSession().flush();
    assertFalse(sessionFactory.getCurrentSession().isDirty());
    testAkkord.setBasisAkkordId(2);
    akkordDao.save(testAkkord);
    sessionFactory.getCurrentSession().flush();
    assertFalse("Trotz Aenderung (auszer bei der Id) sollte Hibernate das Entity-Objekt vom Dirty-Checking ausgeschlossen haben.",
      sessionFactory.getCurrentSession().isDirty());
  }

}
