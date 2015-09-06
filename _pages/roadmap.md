---
layout: page
title: Roadmap
permalink: /roadmap/
---

<!-- Future plans, support needed! -->

## Road Map for chordelia

### Server side
  * Refactoring: German stuff out, English in.
  * Refactoring: Use Lambdas/1.8-Java features instead of plain old 1.5+. Move to Scala/Ruby/Clojure later on.
  * Extract properties of _spring-main.xml_ to _*.properties_ files.
  * Enhance test cases.
  * Introduce code coverage.
  * Support individual projects.
  * Support multi-reservoirs (i.e. several results of permutations).
  * Support interruption of calculation.
  * Support integrity checks.
  * Check performance enhancements (especially batch runs, transactional stuff, parallel actions).
  * Implementation that is not dependend on index based locking.

### Client side
  * Custom UI: Create an UI able to
    * start a chord calculation
    * play chords
    * select chords and group them for creating material for a composition