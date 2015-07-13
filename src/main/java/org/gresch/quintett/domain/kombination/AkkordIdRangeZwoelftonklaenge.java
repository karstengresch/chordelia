package org.gresch.quintett.domain.kombination;

public enum AkkordIdRangeZwoelftonklaenge {
  ZWEITONKLAENGE(2, 1, 11),
  DREITONKLAENGE(3, 12, 121),
  VIERTONKLAENGE(4, 122, 1111),
  FUENFTONKLAENGE(5, 1112, 9031),
  SECHSTONKLAENGE(6, 9032, 64471),
  SIEBENTONKLAENGE(7, 64472, 399111),
  ACHTTONKLAENGE(8, 399112, 2062311),
  NEUNTONKLAENGE(9, 2062312, 8715111),
  ZEHNTONKLAENGE(10, 8715112, 28673511),
  ELFTONKLAENGE(11, 28673512, 68590311);

  private final int anzahlToene;
  private final int minId;
  private final int maxId;

  AkkordIdRangeZwoelftonklaenge(int anzahlToene,
                                int minId,
                                int maxId) {
    this.anzahlToene = anzahlToene;
    this.minId = minId;
    this.maxId = maxId;
  }

  public static int anzahlToeneZuId(int akkordId) {

    for (AkkordIdRangeZwoelftonklaenge akkordIdRange : AkkordIdRangeZwoelftonklaenge.values()) {
      if (akkordId >= akkordIdRange.minId && akkordId <= akkordIdRange.maxId) {
        return akkordIdRange.anzahlToene;
      }
    }
    // TODO Exception?
    return -1;

  }

  public static int maxIdZuAnzahlToene(int anzahlToene) {
    for (AkkordIdRangeZwoelftonklaenge akkordIdRange : AkkordIdRangeZwoelftonklaenge.values()) {
      if (anzahlToene == akkordIdRange.anzahlToene) {
        return akkordIdRange.maxId;
      }
    }
    // TODO Exception?
    return -1;

  }

  public static int minIdZuAnzahlToene(int anzahlToene) {
    for (AkkordIdRangeZwoelftonklaenge akkordIdRange : AkkordIdRangeZwoelftonklaenge.values()) {
      if (anzahlToene == akkordIdRange.anzahlToene) {
        return akkordIdRange.minId;
      }
    }
    // TODO Exception?
    return -1;

  }

  public int minId() {
    return minId;
  }

  public int maxId() {
    return maxId;
  }

}
