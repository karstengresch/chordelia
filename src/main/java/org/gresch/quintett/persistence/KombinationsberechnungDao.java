package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface KombinationsberechnungDao<T, ID extends Serializable> extends Repository<T, ID> {

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

  T findOne(ID id);


  /**
   * Gibt eine Referenz auf die aktuelle Berechnung.
   *
   * @return
   */
  Kombinationsberechnung getKombinationsberechnung();

  boolean saveOrUpdate(Kombinationsberechnung kombinationsberechnung);

  boolean merge(Kombinationsberechnung kombinationsberechnung);

  // @Override
  //void merge(Kombinationsberechnung kombinationsberechnung);

}
