import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

class Terrain{
  //private Place startPlace;
  private int antallPlaces = 0;
  private ArrayList<Place> places = new ArrayList<Place>();
  private Scanner readfile;
  private TreasureChest[] chestlist;

  public Terrain(String filename){

    try{
      readfile = new Scanner(new File(filename));

      while (readfile.hasNextLine()){
        String line = readfile.nextLine();
        Place place = new Place(line);
        places.add(place);
      }
    }
    catch(FileNotFoundException e){
          System.out.println(e);
          System.out.println("Error in the file name.");
      }

      setNexts();

      createChests();
  }

  public int GetAntallPlaces(){return places.size();}

  public Place GetStartPlace(){return places.get(0);}

  public ArrayList<Place> GetPlaces(){return places;}


  public void setNexts(){
    for (int i = 0; i < places.size() - 1 ; i++){
        places.get(i).setNext(places.get(i+1));
        //System.out.println(places.get(i).moveForward());
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
