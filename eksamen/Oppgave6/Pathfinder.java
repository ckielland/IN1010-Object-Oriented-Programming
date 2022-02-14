class Pathfinder implements Runnable{

  Place startPlace;
  Place next;
  int fortune;
  Player player; //I will handle the Player as a monitor
  String name;

  public Pathfinder(Place startPlace, Place next, int fortune, Player player){
    this.startrute = startrute;
    this.previous = previous;
    this.path = path;
    this.labyrint = labyrint;
  }

  public void run(){
    try{
    startPlace.move(previous,path);
  }
  catch (InterruptedException e){
            System.out.println(e);
        }
  }
}
