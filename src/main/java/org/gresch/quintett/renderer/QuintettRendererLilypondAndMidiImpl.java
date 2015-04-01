package org.gresch.quintett.renderer;

import java.io.File;
import java.util.List;

import org.gresch.quintett.domain.tonmodell.Akkord;

public class QuintettRendererLilypondAndMidiImpl extends QuintettRendererLilyPondImpl {
  private static final String LILYPOND_AND_MIDI_SWITCH = " \\midi { \\tempo 4=72 }\n   \\layout { }";

  public QuintettRendererLilypondAndMidiImpl() {
  }
  public File rendereKombinationenInDatei(List<Integer> akkordIdList)
  {
    return super.rendereKombinationenInDatei(akkordIdList, LILYPOND_AND_MIDI_SWITCH);
  }

}
