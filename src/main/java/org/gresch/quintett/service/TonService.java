package org.gresch.quintett.service;

import org.gresch.quintett.BasisTon;
import org.gresch.quintett.domain.tonmodell.Ton;

public interface TonService
{

  /**
   * Achtung! Setzt nicht den Abstand zum Basiston!
   */
  void tonvorratInitialisieren();

  /**
   * Initialisiert den Tonumfang und setzt dabei den Abstand jedes Tons zum Basiston.
   * @param basisTon
   */
  void tonvorratInitialisieren(Ton basisTon);

  Ton getTon(Integer xAbstandZumEingestrichenenC) throws Exception;

  Ton getTonByExample(Ton xTon);

}
