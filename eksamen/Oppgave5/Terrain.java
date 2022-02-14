import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

class Terrain{
  //private Place startPlace;
  protected int antallPlaces = 0;

  //Here my problem was that by creating Place objects in the constructor, it is unavoidable that I create Place objects in
  //MultipleExitTerrain as well. Which I don't want to do. I want to have MultipleExitPlace there.
  //So I create a String ArrayList and then create the Place or MultipleExitPlace objects outside of the
  //constructor.
  protected Place place;
  protected ArrayList<Place> places = new ArrayList<Place>();
  protected ArrayList<String> placenames = new ArrayList<String>();
  protected Scanner readfile;
  protected TreasureChest[] chestlist;
  protected Random rand = new Random();

  public Terrain(String filename){
    try{
      readfile = new Scanner(new File(filename));

      while (readfile.hasNextLine()){
        String line = readfile.nextLine();
        placenames.add(line);
      }
    }
    catch(FileNotFoundException e){
          System.out.println(e);
          System.out.println("Error in the file name.");
      }


      createPlaces();

      setNexts();

      createChests();
  }

  public void createPlaces(){
    for (int i = 0; i < placenames.size(); i++){
      place = new Place(placenames.get(i));
      places.add(place);
    }
  }

  public int GetAntallPlaces(){return places.size();}

  public Place GetStartPlace(){return places.get(0);}

  public ArrayList<Place> GetPlaces(){return places;}


  public void setNexts(){
    for (int i = 0; i < places.size() - 1 ; i++){
        places.get(i).setNext(places.get(i+1));
    }
  }

  public void createChests(){
    //Create treasurechests. Here I need one for each place.
    chestlist = new TreasureChest[places.size()];
    for (int i = 0; i < places.size(); i++){
      TreasureChest tr = new TreasureChest();
      chestlist[i] = tr;
      //Connect chests with places
      places.get(i).placeTreasures(tr);
  }
}

public int antallChests(){
  return chestlist.length;
}

  public TreasureChest[] getChestlist(){
    return chestlist;
  }

}
