package org.gresch.quintett.persistence;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import org.gresch.quintett.domain.tonmodell.Akkord;
import org.hibernate.ScrollableResults;

import java.util.List;
import java.util.Set;

public interface AkkordDao extends GenericDAO<Akkord, Integer> {

  void makePersistentReadOnly(Akkord akkord2);

  Set<Akkord> getAkkordkombinationenZuBasisAkkord(Akkord basisAkkord);

  // Gibt die aktuellen (Basis-)akkorde zum Iterieren zurück.
  ScrollableResults getScrollableResultByBasisAkkordRange(Integer minBlockId, Integer maxBlockId, int fetchBlockGroesze, boolean absteigend);

  List<Integer> getAkkordIdsByAnzahlToene(int anzahlToene, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend);

  List<Integer> getAkkordIdsByRange(int minId, int maxId, boolean klangschaerfeAbsteigend, boolean akkordIdAbsteigend);
}
