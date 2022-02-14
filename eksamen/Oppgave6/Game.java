import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.concurrent.CountDownLatch;

//Due to lack of time, I have implemented this exercise only on the single way Terrain and only human players (i.e oppgave 2)
class Game{

  private static ArrayList<Item> itemlist = new ArrayList<Item>();
  private static Random rand = new Random();
  private static Scanner input = new Scanner(System.in);
  private static Terminal terminal;
  private static Place startPlace;
  private static Player player;
  private static Terrain terrain;
  private static CountDownLatch count;


  public static void main(String[] args)throws InterruptedException{

    terrain = new Terrain("steder.txt");
    startPlace = terrain.GetStartPlace();

    //Create items
    createItems("gjenstander.txt");

    System.out.println("Welcome to THE GAME!");

    //  Fill each chest with 6 items
    for (int i = 0; i < terrain.antallChests(); i++){
      for (int j = 0; j < 5; j++){
        terrain.getChestlist()[i].FillInn(itemlist.get(rand.nextInt(itemlist.size())));
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

    //In this exercise as I have to work with parallel programming, I will give a NEW identical backpack to each player
    //instead of creating one and updating it before the next player starts (as in exercise 4-5)
    TreasureChest[] backpacklist = new TreasureChest[totalPlayers];

    for (int j = 0; j < totalPlayers; j++){
      TreasureChest backpack = new TreasureChest();
      backpacklist[j] = backpack;
      if (itemlist.size() > 5){
        for (int i = 0; i < 5; i++){
          backpack.FillInn(itemlist.get(i));
        }
      }
    }

    count = new CountDownLatch(totalPlayers);
    terminal = new Terminal(input);
    PlayerMonitor monitor = new PlayerMonitor(terminal, totalPlayers, count);



    for (int i = 0 ; i < totalPlayers; i ++){
        TreasureChest b = backpacklist[i];
        Runnable player = new Player(startPlace, monitor, b);
        Thread t = new Thread(player);
        t.start();
      }

    count.await();
    monitor.printResult();
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
