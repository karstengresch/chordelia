#(set-default-paper-size "a4" 'landscape)
#(set-global-staff-size 11)
\version "2.18.0"
\layout
  {
    indent = 0.35\cm
  }
\header
  {
    title = "Akkordkombinationen"
    tagline = "Automatisch erzeugt mit KGs 'Quintett' Programm - Aufs Entzückendste gerendert mit 'GNU Lilypond'."
  }

\new StaffGroup
  <<
    \new Staff \with { \remove "Time_signature_engraver" }
       { \clef treble
               \bar "||" <cis' dis'>1^"Anzahl Töne" \bar "||" <cis' dis'>1
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
               \bar "||" <cis' dis'>1   \bar "||" <cis' dis'>1    \bar "||" <cis' dis'>1    \bar "||"
       }

    \new Staff \with {  \remove "Time_signature_engraver" }
       { \clef bass
               \bar "||" <c es>1_"0001" \bar "||" <c es>1_"0002"
               \bar "||" <c es>1_"0003" \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
               \bar "||" <c es>1        \bar "||" <c es>1         \bar "||" <c es>1         \bar "||"
       }
  >>


