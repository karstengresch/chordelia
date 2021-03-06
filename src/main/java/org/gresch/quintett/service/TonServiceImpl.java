package org.gresch.quintett.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gresch.quintett.KombinationsberechnungParameter;
import org.gresch.quintett.domain.tonmodell.Oktavlage;
import org.gresch.quintett.domain.tonmodell.Ton;
import org.gresch.quintett.persistence.TonDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.gresch.quintett.domain.tonmodell.Tonumfang.MAX_ABSTAND_C_EINGESTRICHEN;
import static org.gresch.quintett.domain.tonmodell.Tonumfang.MIN_ABSTAND_C_EINGESTRICHEN;

@Service("tonService")
public class TonServiceImpl implements TonService {
  @PersistenceContext
  EntityManager entityManager;
  private Log log = LogFactory.getLog(TonServiceImpl.class);
  @Resource(name = "tonDao")
  private TonDao tonDao;

  @Resource(name = "kombinationsberechnungService")
  private KombinationsberechnungService kombinationsberechnungService;

  public TonServiceImpl() {
    // Beany baby
  }

  @Transactional(readOnly = true)
  public Ton getTon(Integer abstandZumEingestrichenenC) throws Exception {
    return (Ton) tonDao.findOne(abstandZumEingestrichenenC);
  }

  @Transactional
  @Override
  public void tonvorratInitialisieren() {
    tonvorratInitialisieren(null);
  }

  @Override
  @Transactional
  public Ton getTonByExample(Ton xTon) {
    return tonDao.findByExample(xTon);
  }

  @Override
  @Transactional
  public void tonvorratInitialisieren(Ton basisTon) {
    SessionFactory sessionFactory = (entityManager.unwrap(Session.class)).getSessionFactory();
    log.info("Tonumfang: Initialisiere...");
    int tonId = 0;
    int i = 0;
    Ton _basisTon = basisTon;
    if (null == _basisTon) {
      _basisTon = new Ton(Oktavlage.GROSZE, KombinationsberechnungParameter.CLI_DEFAULTWERT_GRUNDTON);
      //      _basisTon.setAbstandZumBasisTon(0);
      // TODO WARNING
    }
    Session session = sessionFactory.getCurrentSession();
    for (i = MIN_ABSTAND_C_EINGESTRICHEN; i < MAX_ABSTAND_C_EINGESTRICHEN + 1; i++) {
      Ton ton = new Ton();
      ton.initialisiereDurchAbstandZumEingestrichenenC(i);
      ton.setId(i);
      //      if (!(null == basisTon))
      //      {
      //        ton.setAbstandBasisTonDurchAbstandZumEingestrichenenC(basisTon);
      //      }
      //      else
      //      {
      //        ton.setAbstandBasisTonDurchAbstandZumEingestrichenenC(_basisTon);
      //      }

      tonDao.makePersistentReadOnly(ton, entityManager);
      // FIXME XXX Das muss unbedingt getan werden
      // tonVorratMap.put(i, ton);
      tonId = i;
    }
    log.info("Tonumfang: Initialisierung beendet: " + tonId + " Töne initialisiert.");

  }

}
