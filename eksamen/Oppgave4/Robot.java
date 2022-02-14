import java.util.Random;

class Robot extends User{

private Random rand = new Random();

@Override
public void giveStatus(String status){
  System.out.println(status);
}

@Override
public int askForCommand(String question, String[] options){
  int i = rand.nextInt(options.length) + 1;
  giveStatus("I have chosen: " + i);
  return i;
}
}
