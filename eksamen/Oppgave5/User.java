abstract class User implements UserInterface{

@Override
abstract public void giveStatus(String status);

@Override
abstract public int askForCommand(String question, String[] options);
}

//I have decided to create an abstact superclass, so that in Player class I create
//one instance variable and two constructors to initiate either a terminal or a robot.
