---
layout: page
title: Setup
permalink: /setup/
---

<!-- Setup of chordelia. -->

## Setup chordelia
This page gives you information on how to setup chordelia so you can let it calculate chord permutations based on your individual taste.

###  Prerequisites
  * [http://maven.apache.org Apache Maven (2.x)] needs to be installed. Check the instructions [http://maven.apache.org/download.html#Installation here].
  * You should use a JDK 1.6+ (latest stable version normally preferred).
  * MySQL or other index-based locking database (not H2/HSQL-DB, not Derby).
  * Make sure you have enough free disk space (min. ~50 GByte).
  * Get a computer that is either superfast or can run for days without being interrupted.

### git checkout
  * Get the repository from .
  * Run ```mvn clean compile -Dmaven.test.skip=true```

### DB Setup (for MySQL)
  * Either create a database and user according to the settings in _spring-main.xml_ or adjust _spring-main.xml_.
  * With the included Vagrantfile you can easily setup the DB w/ one command (assumed, Vagrant is installed: ```vagrant up```)

###Create a build with Maven
  * Run mvn ```assembly:assembly```.
  * Make sure the created jar is fully functional.

###Move the build to another directory
  * TO DO: Describe _run.sh_.

### Run chordelia
  * TO DO: Describe command line parameters. (In the meantime read the command output.)
