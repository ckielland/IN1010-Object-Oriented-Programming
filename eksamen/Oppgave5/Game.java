import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.File;

class Game{

  private static ArrayList<Item> itemlist = new ArrayList<Item>();
  private static Random rand = new Random();
  private static Scanner input = new Scanner(System.in);
  private static Terminal terminal;
  private static Robot robot;
  private static Place startPlace;
  private static Player player;
  private static Terrain terrain;
  private static boolean multiple;
  private static Player[] players;
  private static TreasureChest backpack = new TreasureChest();

  public static void main(String[] args){

    System.out.println("Welcome to THE GAME!");
    System.out.println("How many exits do you wish to have?\n1.Single \n2.Multiple");
    int ans = input.nextInt();
    if (ans == 1){
      terrain = new Terrain("steder.txt");
      multiple = false;
    }
    else{
      terrain = new MultipleExitTerrain("steder.txt");
      multiple = true;
    }

    startPlace = terrain.GetStartPlace();


    //Create items
    createItems("gjenstander.txt");


    //  Fill each chest with 6 items
    for (int i = 0; i < terrain.antallChests(); i++){
      for (int j = 0; j < 5; j++){
        terrain.getChestlist()[i].FillInn(itemlist.get(rand.nextInt(itemlist.size())));
     }
   }

   //Since I will have multiple players I will put a bit more items in each chest to make a little bit more interesting
   //All players are starting the game with the same items, so I only create 1 backpack object which I reset before each player starts
   if (itemlist.size() > 5){
     for (int i = 0; i < 5; i++){
       backpack.FillInn(itemlist.get(i));
     }
   }

    System.out.println("How many players are playing? \nThe maximum amount of players is: 8");
    int totalPlayers = input.nextInt();

    while (totalPlayers > 8){
      System.out.println("Invalid input. Type a number smaller than 8.");
      System.out.println("How many players are playing? \nThe maximum amount of players is: 8");
      totalPlayers = input.nextInt();
    }
    input.nextLine();

    players = new Player[totalPlayers];


    System.out.println("Proceed as human for all players? ");
    System.out.println("If all players are NOT robots type: yes ");
    System.out.println("If at least one player is a robot type: no");

    String answer = input.nextLine();

    if (answer.equals("yes")){
      terminal = new Terminal(input);
      if (multiple == false){
      for (int i = 0 ; i < totalPlayers; i ++){
      player = new Player(startPlace, terminal, backpack);
      players[i] = player;
        }
      }
      else if (multiple == true){
        for (int i = 0; i < totalPlayers; i ++){
          player = new MultipleExitPlayer(startPlace, terminal, backpack);
          MultipleExitPlayer p = (MultipleExitPlayer) player;
          players[i] = p;
        }
      }
    }
    else if (!(answer.equals("yes"))){
      robot = new Robot();
      System.out.println("State how many players are robots: ");
      int totalRobots = input.nextInt();
      int restPlayers = totalPlayers - totalRobots;
      if (restPlayers > 0){
        terminal = new Terminal(input);
      }
      if (multiple == false){
      for (int i = 0; i < totalRobots; i++){
        player = new Player(startPlace, robot, backpack);
        players[i] = player;
      }
      if (restPlayers > 0){
      for (int i = totalRobots; i < totalPlayers; i ++){
        player = new Player(startPlace, terminal, backpack);
        players[i] = player;
        }
      }
    }
    else if (multiple == true){
        for (int i = 0; i < totalRobots; i++){
      player = new MultipleExitPlayer(startPlace, robot, backpack);
      MultipleExitPlayer p = (MultipleExitPlayer) player;
      players[i] = p;
      }
      if (restPlayers > 0){
      for (int i = totalRobots; i < totalPlayers; i ++){
        player = new MultipleExitPlayer(startPlace, terminal, backpack);
        MultipleExitPlayer p = (MultipleExitPlayer) player;
        players[i] = p;
        }
      }
    }
    input.nextLine();
  }
    else{System.out.println("Wrong input");}
    play();
    printResult();
  }

  public static void play(){
    System.out.println("The game is starting! \n");
    for (int i = 0; i <players.length; i ++){
    players[i].newMove();
    resetBackpack();
    }
  }

  public static void resetBackpack(){
    int size = backpack.getSize();
    for (int i = 0; i < size; i ++){
      backpack.remove();
    }
    if (itemlist.size() > 5){
      for (int i = 0; i < 5; i++){
        backpack.FillInn(itemlist.get(i));
      }
    }
  }

  //View the final result
  public static void printResult(){
    String message = "";
    System.out.println("The final results are: \n******************");
    for (int i = 0; i < players.length; i++){
      message = "";
      message = message + "Player: " + players[i].getId() + " Result: " + players[i].getFortune();
      System.out.println(message);
      System.out.println("******************");
    }
  }

  public static void createItems(String filename){
    Scanner readItemsFile;
    try{
      readItemsFile = new Scanner(new File(filename));
      while (readItemsFile.hasNextLine()){
        String line = readItemsFile.nextLine();
        String[] words = line.split(" ");
        Item it = new Item(words[0], Integer.parseInt(words[1]));
        itemlist.add(it);
      }
    }
    catch(FileNotFoundException e){
          System.out.println(e);
          System.out.println("Error in the file name.");
      }
  }

}
