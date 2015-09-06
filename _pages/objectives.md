---
layout: page
title: Objectives
permalink: /objectives/
---
  
  
  
  
## chordelia's objectives
  
### What it is meant for
  
  
  
    * Provide musicians (composers) with a reservoir of chords for writing any sort of music.
    * chordelia does neither force nor prohibit writing functional or non functional music.
    * chordelia's based on notes/tones and not based on noises/sounds.
  
### How it works
  
First you need to put the eleven intervals within an octave space into an order. The order could be based on your individual feeling of the dissonance/consonance grade of each interval when be played as a chord. E.g. you might feel the small second the most dissonant interval within the octave space, maybe followed by a tritonus, then followed by the great seventh, then followed by the great second etc. etc.
  
Based on your decision, chordelia will create permutations of all possible chords based on your interval in the following manner: Based on the intervals (two-tone chords) it will iterate through these chords and then add to each chord an interval based on your initial order. Here, all resulting chords containing an octave duplication will be eliminated.
  
You can decide if your initial order shall be interpreted as starting with the most or less dissonant/spicy chord/interval (in your opinion). Additionally you can decide if chordelia shall add the next interval to the top or to the bottom of the iterated chord. 
  
In the following picture you see five chords, starting with a great seventh as the most "dissonant"/spicy interval. In the next line (3-tone chords) the most spicy intervall are two great sevenths, one great seventh added to the top. You also see that the next interval cannot be a great seventh with an added small second as these are complementary intervals and would result in octave doubling. Therefor, the next chord is a great second with a tritonus added to the top.
  
Finally, in the third score the most dissonant 4-tone chord the 3-tone chord #22 from the second score with another great seventh added to the top.
  
![Spicy Intervalls](/img/spicy_intervalls.png)
  
Chordelia calculates all the permutations, stores them in a database for you and - at the moment - creates the Lilypond files for you so you can print them out or load and use them with tools like Frescobaldi.
  
As we're speaking about more than 68 Mio chords this will not only take some time, but also take some free space from your harddisk.
  
Next planned stage would be a useful UI that would allow you to browse the chords, eventually add them, put interesting chords to a scratchpad and export them for further use in notation software like Sibelius or Capella.
  
### Criticism or unwanted answers to the FUDders
  
    * *This approach is artificial*: Sure it is. Being artificial belongs to being art.
    * *One cannot hear real dissonance differences in 9-/10-/11-tone chords*: You could try changing your orchestration. But in general it'll always be difficult to differenciate 11-tone chords. Anyway, at least the more cluster-like chords will differenciate the better, the more instruments will play.
    * *One cannot hear that chord #xxx should be more dissonant/spicy than chord #yyy*: Showing and playing my concept to different people, all were quite convinced when listening to strong "dissonance contrasts" (e.g. most dissonant, midst dissonant lowest dissonant chord of 5-tone chords). As in life, there are grayscales where it gets difficult to differenciate. Chordelia's intention is not to force you writing music that doesn't convince yourself.