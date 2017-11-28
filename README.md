## Bowling Scoring System

#### How to run
* mvn clean install(optional only to make modifications) Jar file is included.
* From /bowling-score-system/ run java -jar ./target/bowling-1.0.jar


### Design Structure
* BowlingGame.java - Main game object, create new instance of this to create new game
* GameState.java - private field of BowlingGame, contains the complete state of the game including score at any point during the game
* Frame.java - Represents a frame in bowling. This includes logic to determine the following attributes of a frame. Strike frame, Spare frame, and Last frame. This includes the logic for the current score of the frame excluding any bonus logic. The game state is responsible for totaling the score. The reason for this is a score for a given frame is a function of an adjacent frame.
* Roll.java - Represents a turn for the given player. This is defined as the knocking down of n pins where 0<=n<=10
* BowlingGammeRunner.java - contains main method for use in command line application. Contains the sample case from the assignment. Please note this is just for a sample. All tests are included below in BowlingTest.java
### Test Cases
* All test cases are present in the test folder under BowlingTest.java. This includes 15 test cases all covering various rules
Please pay special attention to the tests in this file to confirm expected functionality


###Known Issues and Comments
* Even though this bowling system handles all ten pin rules including bonus turns in the last frame, The scoring logic in Gamestate regarding the handling of the last frame is not too readable and should be made more elegant.
* Exception handling needs some work. Right now if illegal turns will result in an illegalArgumentException. The calling class is responsible for catching and handling this. This may need to be refined.
