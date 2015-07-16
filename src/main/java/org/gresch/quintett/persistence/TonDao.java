package org.gresch.quintett.persistence;

import org.gresch.quintett.domain.tonmodell.Ton;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface TonDao<T, ID extends Serializable> extends Repository<T, ID> {
  // Nichts zu tun zur Zeit, nur der Struktur halber und für den Fall, dass spezielle Nicht-CRUD-Methoden nötig werden.
  void makePersistentReadOnly(Ton xTon);

  Ton findByExample(Ton ton);

  T findOne(ID id);
}
