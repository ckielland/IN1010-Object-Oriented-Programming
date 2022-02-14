class Player implements Runnable{

  protected Place startingPoint;
  protected int myFortune;
  protected int playerid;
  protected static int counter = 1;
  protected PlayerMonitor monitor;
  protected TreasureChest backpack;
  protected boolean finished = false;


  public Player(Place startingPoint, PlayerMonitor monitor, TreasureChest backpack){
    this.startingPoint = startingPoint;
    this.monitor = monitor;
    this.backpack = backpack;
    playerid = counter;
    counter++;
  }

  public int updateFortune(){
    myFortune = 0;
    for (Item it : backpack){
      myFortune = myFortune + it.getValue();
    }
    return myFortune;
  }

  public Place getMyPlace(){
    return startingPoint;
  }

  public int getFortune(){
    return myFortune;
  }

  public int getId(){
    return playerid;
  }

  public TreasureChest getCommonChest(){
    return startingPoint.getTreasures();
  }

  public TreasureChest Getbackpack(){
    return backpack;
  }

  public void run(){
    try{
    updateFortune();
    move(startingPoint, myFortune);
    }
    catch(InterruptedException e){
      System.out.println("Got interrupted");
    }
  }

  public boolean setFinished(){
    return finished = true;
  }


  public void move(Place start, int fortune) throws InterruptedException{

    monitor.move(this);

    while(!finished){
    move(start.moveForward(), myFortune);
    }

    }
  }
