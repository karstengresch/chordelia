package org.gresch.quintett.renderer;

import java.io.File;
import java.util.List;

public class QuintettRendererMidiImpl extends QuintettRendererLilyPondImpl {
  private static final String MIDI_SWITCH = " \\midi { \\tempo 4=72 }";

  public QuintettRendererMidiImpl() {
  }

  public File rendereKombinationenInDatei(List<Integer> akkordIdList) {
    return super.rendereKombinationenInDatei(akkordIdList, MIDI_SWITCH);
  }

}
