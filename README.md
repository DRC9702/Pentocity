Explanation of Game:
http://www.cs.columbia.edu/~kar/4444f16/node19.html

My Team was Team 6 so in order to run our player with a visual gui:
javac pentos/sim/*.java
java pentos.sim.Simulator [--gui] [--fps arg] -g g6

Modify The "g6" argument for other strategies from other teams (try "g5") to see how we compare. 

---------------------------------------------------------------------------------------------------------------

Author:  Kevin Shi, Orestis Polychroniou
Email:   kshi@cs.columbia.edu, orestis@cs.columbia.edu


Player

The players must extend the "pentos/sim/Player.java" interface. An example
dummy player is provided in "pentos/g0/Player.java". The player code must
be on a separate directory/package and the basic class must be in "Player.java".
For example, group 4 should have the main class in "pentos/g4/Player.java" under the pacakge pentos.g4

The interface for a player specifies two methods, an "init()" method to
initialize the player object and a "play()" method to return the next
movement of the player. The simulator first calls the default constructor
with no arguments and then calls the "init()" method. As the game is
played, the "play()" method is called on every turn until the game ends.

The "play()" method should return a boolean specifying whether the building is accepted or rejected. If the request is rejected, the player is not allowed to specify anything else. The simulator builds all roads/park/water before the building for each move request, so there is no need to construct anything else until the next accepted building request. If the request is accepted, the player must specify a location for the building, which is a cell position for the top left corner of the building, and a rotation, which is an integer from 0 to 3 labeling one of the four 90 degree rotations the building can take. The player can then specify three sets of cells for locations to build roads/park/water. 

Sequencer

The default sequencer generates random shapes for buildings and factories. Additional sequencer behaviors can be specified by creating a new folder within the main directory and implementing the "pentos/sim/Sequencer.java" interface. The sequencer contains an init() function which is called once at the beginning and a next() function which is called every turn. 

Simulator

To run or compile the simulator, cd into the folder above pentos.
To (re)compile the simulator on Unix & Mac OS X:   javac pentos/sim/*.java
To (re)compile the simulator on Windows:           javac pentos\sim\*.java
To run the simulator:  java pentos.sim.Simulator <arguments>

The simulator is written and tested using Java version 8 (jre/jdk 1.8).
To check the Java virtual machine version:     java -version
To check the Java bytecode compiler version:   javac -version
Earlier versions of Java may (will probably) not work. The simulator is
operating system (OS) independent and has been (successfully) tested on:

  Apple Mac OS X Yosemite (10.10)
  GNU/Linux Ubuntu 16.04
  Microsoft Windows 10 (running through Cygwin)

The simulator arguments are:
 -g, --groups <group name, e.g. g0>
 -s, --sequencer <folder name containing sequencer, e.g. random>
     --gui
     --verbose

If the GUI option is enabled, the simulator creates a visualization of
the game as a dynamic HTML page. To view the GUI, open the browser on
localhost using the port displayed in a terminal message. For example,
if the simulator shows port 54321 in the terminal, open the browser and
use the address "http://localhost:54321" (or "http://127.0.0.1:54321").
Javascript must be enabled. The GUI uses HTML 5 canvas and the state
updates use AJAX. Page reloads are supported (used when FPS is set to 0).
