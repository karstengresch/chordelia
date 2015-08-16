package org.gresch.quintett.service;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;

import javax.persistence.EntityManager;

public interface KombinationsberechnungService {

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