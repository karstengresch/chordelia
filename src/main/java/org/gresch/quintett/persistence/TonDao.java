package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.springframework.data.repository.CrudRepository;

public interface TonDao extends CrudRepository<Ton, Integer> {
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass spezielle Nicht-CRUD-Methoden nötig werden.
  void makePersistentReadOnly(Ton xTon);

  Ton findByExample(Ton ton);
}
