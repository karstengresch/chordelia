package org.gresch.quintett.service;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public interface KombinationsberechnungService {

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  static void flushManually(EntityManager entityManager) {
    Session session = entityManager.unwrap(Session.class);
    FlushMode flushModeOld = session.getFlushMode();
    session.setFlushMode(FlushMode.MANUAL);
    session.flush();
    session.setFlushMode(flushModeOld);
  }

  void kombinationenBerechnen() throws Exception;

  void kombinationenAusgeben();

  void verzeichnisseVorbereiten();

  /**
   * Gibt die aktuelle Kombinationsberechnung zurück. Wenn keine vorhanden ist, wird sie erstellt.
   */
  Kombinationsberechnung getKombinationsBerechnung();

  /**
   * Speichert eine Kombinationsberechnung
   *
   * @param kombinationsberechnung
   */
  void saveKombinationsBerechnung(Kombinationsberechnung kombinationsberechnung);

  Integer getLetzteBasisAkkordKlangschaerfe();

  Integer getLetzteBasisAkkordId();

  Integer getLetzteAkkordId();

  Long getAnzahlBerechneterAkkorde();

  Integer getMaxAnzahlAkkordToeneAusAkkorden();

  Integer getMaxAkkordIdZuBasisAkkordId(Integer letzteBasisAkkordIdInteger);

  /**
   * Geht davon aus, dass eine bereits vorhandene Kombinationsberechnung vorhanden ist.
   *
   * @param kombinationsberechnung
   */
  void updateKombinationsberechnung(EntityManager entityManager, Kombinationsberechnung kombinationsberechnung);

}