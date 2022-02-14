import java.util.ArrayList;

class MultipleExitTerrain extends Terrain{

  //private ArrayList <MultipleExitPlace> places = new ArrayList<MultipleExitPlace>();
  private MultipleExitPlace p;

  public MultipleExitTerrain(String filename){
    super(filename);
  }

  @Override
  public void createPlaces(){
  //  places = new ArrayList<MultipleExitPlace>();
    for (int i = 0; i < placenames.size(); i++){
      p = new MultipleExitPlace(placenames.get(i));
      places.add(p);
    }
  }

  @Override
  public void setNexts(){
    //I have decided to give to all places 3 exits each. If I give only 1 or 2, then I might end in a deadlock
    int left; int right; int straight;
    //I have decided not to use the next reference anymore, but only the 3 exits references.
    //In addition, here I have decided to connect all of the places to 3 exits and not keep one that won't
    //have nexts (as in exercise 2). Instead now, I assign eight places to be the end of the game and it is when
    // the player comes to one of the them that the game is over.
    for (int i = 0; i < places.size(); i++){
        left = rand.nextInt(places.size());
        right = rand.nextInt(places.size());
        straight = rand.nextInt(places.size());
        while (left == right || right == straight || left == straight || i == left || i == straight || i == right){
          left = rand.nextInt(places.size());
          right = rand.nextInt(places.size());
          straight = rand.nextInt(places.size());
        }
        MultipleExitPlace p1 = (MultipleExitPlace) places.get(i);
        MultipleExitPlace p2 = (MultipleExitPlace) places.get(left);
        MultipleExitPlace p3 = (MultipleExitPlace) places.get(right);
        MultipleExitPlace p4 = (MultipleExitPlace) places.get(straight);
        p1.setLeft(p2);
        p1.setRight(p3);
        p1.setStraight(p4);
      }

      //Allocating eight gameOver signed places
      //The first one cannot be assigned as a gameOver place (for obvious reasons!)
      for (int i = 0; i < 8; i++){
      places.get(rand.nextInt(places.size()-1)+1).setGameOver();
      }
  }


}
