This project is derived from a musical composition, a modern "classical" string quintett.

The program's aim is
  * to calculate all possible permutations of up to 11-note chords based on a given interval order;
  * store the results in a database;
  * render the results (preferably in LilyPond format);
  * provide the user with a UI for selecting material for her/his own compositions and query the database.

The following amount of chords is calculated (ordered by number of notes per chord):

| **Notes/Chord** | **Chords** |
|:----------------|:-----------|
| 2|                    11|
| 3|                   110|
| 4|                   990|
| 5|                 7.920|
| 6|                55.440|
| 7|               332.640|
| 8|             1.663.200|
| 9|             6.650.800|
|10|            19.958.400|
|11|            39.916.800|
|Sum|           68.586.311|

## Technical overview ##
The program is written in Java (6), using a "classical" Spring Framework architecture (Services and DAOs). MySQL is currently supported as DBMS.
The execution of the program takes currently 2 days on an average computer storing the 68.586.311 chords needs a disk space of ~35 GByte.

## Further information ##
Start from here for information on...

  * [...how to setup chordelia to calculate chord permutations](ChordeliaSetup.md)
  * [...discover the objectives of chordelia](ChordeliaObjectives.md)
  * [...read the road map for further chordelia development](ChordeliaRoadMap.md)
  * [...get hints and tricks on running chordelia](ChordeliaHints.md)