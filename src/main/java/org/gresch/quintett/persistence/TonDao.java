package org.gresch.quintett.persistence;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import org.gresch.quintett.domain.tonmodell.Ton;

public interface TonDao extends GenericDAO<Ton, Integer> {
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass spezielle Nicht-CRUD-Methoden nötig werden.
  void makePersistentReadOnly(Ton xTon);

  Ton findByExample(Ton ton);
}
