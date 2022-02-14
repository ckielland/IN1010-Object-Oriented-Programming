import java.util.Scanner;

class Terminal extends User{

  private Scanner input;

  public Terminal(Scanner input){
    this.input = input;
  }

  @Override
  public void giveStatus(String status){
    System.out.println(status);
  }

  @Override
  public int askForCommand(String question, String[] options){
    System.out.println(question);
    System.out.println("Choose an option: ");
    System.out.println("Type a number from 1 to " + options.length);
    for (int i = 0; i < options.length; i ++){
      System.out.println(options[i]);
    }
    int choise = 0;
    choise = Integer.parseInt(input.nextLine());
    return choise;
  }
}
