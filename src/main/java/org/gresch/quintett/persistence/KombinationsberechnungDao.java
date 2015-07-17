package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface KombinationsberechnungDao<T, ID extends Serializable> extends CrudRepository<Kombinationsberechnung, Integer> {

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

  boolean merge(Kombinationsberechnung kombinationsberechnung);

  // @Override
  //void merge(Kombinationsberechnung kombinationsberechnung);

}
