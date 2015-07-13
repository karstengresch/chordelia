package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;

public interface KombinationsberechnungDao extends com.googlecode.genericdao.dao.jpa.GenericDAO<Kombinationsberechnung, Integer> {

  Integer getMaxAkkordIdZuBasisAkkordId(Integer xBasisAkkordId);

  Integer getMaxAnzahlAkkordToeneAusBerechnungsInformation();

  Integer getMaxAnzahlAkkordToeneAusAkkorden();

  Long getAnzahlBerechneterAkkorde();

  Long getAnzahlBerechneterAkkordeZuAnzahlAkkordToene(Integer xAkkordToene);

  Integer getMaxIdZuAnzahlAkkordToene(Integer xAkkordToene);

  Integer getMinIdZuAnzahlAkkordToene(Integer xAkkordToene);

  //  Boolean ladenAusDatenbankNuetzlich();
  Integer getBerechnungsId();

  Integer getLetzteBasisAkkordKlangschaerfe();

  Integer getLetzteAkkordId();

  Integer getLetzteBasisAkkordId();

  /**
   * Gibt eine Referenz auf die aktuelle Berechnung.
   *
   * @return
   */
  Kombinationsberechnung getKombinationsberechnung();

  boolean saveOrUpdate(Kombinationsberechnung kombinationsberechnung);

  // @Override
  //void merge(Kombinationsberechnung kombinationsberechnung);

}
