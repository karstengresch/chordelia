package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.kombination.Kombinationsberechnung;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface KombinationsberechnungDao<T, ID extends Serializable> extends CrudRepository<Kombinationsberechnung, Integer> {


 // @Query("select p from Product p where p.attributes[?1] = ?2")
 //  List<Product> findByAttributeAndValue(String attribute, String value);

  @Query(value = "select max(id) from akkord where basis_akkord_id = :basisakkord", nativeQuery = true)
  Integer getMaxAkkordIdZuBasisAkkordId(@Param("basisakkord") Integer xBasisAkkordId);

  @Query(value = "select bereits_berechnete_toene from berechnungs_informationen", nativeQuery = true)
  Integer getMaxAnzahlAkkordToeneAusBerechnungsInformation();

  @Query(value = "select (max(position)+1) from TON_AKKORD where AKKORD_ID = ( select max(akkord_id) from ton_akkord)", nativeQuery = true)
  Integer getMaxAnzahlAkkordToeneAusAkkorden();

  @Query("select max(id) from Akkord")
  Long getAnzahlBerechneterAkkorde();

  @Query("select max(id) from Akkord a where a.anzahlToene = :akkord_toene")
  Long getAnzahlBerechneterAkkordeZuAnzahlAkkordToene(@Param("akkord_toene") Integer xAkkordToene);

  @Query("select max(a.id) from Akkord a where a.anzahlToene = :akkord_toene")
  Integer getMaxIdZuAnzahlAkkordToene(@Param("akkord_toene") Integer xAkkordToene);

  @Query("select min(a.id) from Akkord a where a.anzahlToene = :akkord_toene")
  Integer getMinIdZuAnzahlAkkordToene(@Param("akkord_toene") Integer xAkkordToene);

  //  Boolean ladenAusDatenbankNuetzlich();
  @Query("select max(b.id) from Kombinationsberechnung b")
  Integer getBerechnungsId();

  @Query(value = "select letzte_basis_akkord_klangschaerfe from berechnungs_informationen", nativeQuery = true)
  Integer getLetzteBasisAkkordKlangschaerfe();

  @Query(value = "select letzte_akkord_id from berechnungs_informationen", nativeQuery = true)
  Integer getLetzteAkkordId();

  @Query(value = "select letzte_basis_akkord_id from berechnungs_informationen", nativeQuery = true)
  Integer getLetzteBasisAkkordId();


  /**
   * Gibt eine Referenz auf die aktuelle Berechnung.
   *
   * @return
   */
  // @Query(value = "select k from Kombinationsberechnung k")
//  Kombinationsberechnung getKombinationsberechnung() {
//    Kombinationsberechnung kombinationsberechnung = (Kombinationsberechnung) sessionFactory.getCurrentSession().get(Kombinationsberechnung.class, 1);
//    Kombinationsberechnung kombinationsberechnung = find(1);
//    if (null == kombinationsberechnung)
//    {
//      log.error("******* Konnte keine Kombinationsberechnung zurueckgeben!");
//    }
//    return kombinationsberechnung;
//  }

  // void setKombinationsberechnung(Kombinationsberechnung kombinationsberechnung);
  // @Modifying
  // @Query(value = "update Kombinationsberechnung k set k = :kombinationsberechnung where id = 0")
  //Integer saveOrUpdate(@Param("kombinationsberechnung") Kombinationsberechnung kombinationsberechnung);

  default boolean saveOrUpdate(EntityManager entityManager, Kombinationsberechnung kombinationsberechnung) throws Exception {

    boolean successfullyUpdated = false;
    try {
      entityManager.unwrap(SessionFactory.class).getCurrentSession().saveOrUpdate(kombinationsberechnung);
      successfullyUpdated = true;
    } catch (Exception e) {
      throw new Exception(e);
    }

    return successfullyUpdated;

  }


  // boolean merge(Kombinationsberechnung kombinationsberechnung);

  // @Override
  //void merge(Kombinationsberechnung kombinationsberechnung);

}
