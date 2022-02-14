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

      TreasureChest backpack = new TreasureChest();
      backpack.FillInn(itemlist.get(rand.nextInt(itemlist.size()))); //Fill the backpack with some initial items
      backpack.FillInn(itemlist.get(rand.nextInt(itemlist.size())));
      backpack.FillInn(itemlist.get(rand.nextInt(itemlist.size())));

      //Check if it is a player or a robot that plays.
      //System.out.println("Welcome to THE GAME!");
      System.out.println("Proceed as human? ");
      System.out.println("If you are a human type: yes ");
      input.nextLine();
      String answer = input.nextLine();
      if (answer.equals("yes") && multiple == false){
        terminal = new Terminal(input);
        player = new Player(startPlace, terminal, backpack);
      }
      else if (answer.equals("yes") && multiple == true){
        terminal = new Terminal(input);
        player = new MultipleExitPlayer(startPlace, terminal, backpack);
      }
      else if (!(answer.equals("yes")) && multiple == false){
        robot = new Robot();
        player = new Player(startPlace, robot, backpack);
      }
      else if (!(answer.equals("yes")) && multiple == true){
        robot = new Robot();
        player = new MultipleExitPlayer(startPlace, robot, backpack);
      }
      else{System.out.println("Wrong input");}
      play();
    }

    public static void play(){

      player.newMove();

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
