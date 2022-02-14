class Place{

  private String description;
  private TreasureChest myTreasures;
  private Place next;

  public Place(String description){
    this.description = description;
  }

  public void placeTreasures(TreasureChest tr){
    myTreasures = tr;
  }

  public void printDescription(){
    System.out.println(description);
  }

  public void printMyTreasures(){
      for (Item it : myTreasures){
        System.out.println(it);
      }
  }

  public TreasureChest getTreasures(){
    return myTreasures;
  }

  public void setNext(Place nxt){
    next = nxt;
  }

  public Place moveForward(){
    return next;
  }

  public boolean isExit(){
    return next == null;
  }

  @Override
  public String toString(){
    return "- "  +  description + " " + " - treasures: " + myTreasures;
  }
}
