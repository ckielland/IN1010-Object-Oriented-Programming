class Player{

  protected Place startingPoint;
  protected User user;
  protected TreasureChest backpack;
  protected int myFortune;
  protected int playerid;
  protected static int counter = 1;


  public Player(Place startingPoint, Terminal user, TreasureChest backpack){
    this.startingPoint = startingPoint;
    this.user = user;
    this.backpack = backpack;
    playerid = counter;
    counter++;
  }

  public Player(Place startingPoint, Robot user, TreasureChest backpack){
    this.startingPoint = startingPoint;
    this.user = user;
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

  public int getFortune(){
    return myFortune;
  }

  public int getId(){
    return playerid;
  }

  public void newMove(){
    updateFortune();
    move(startingPoint, myFortune);
  }

  public void move(Place start, int fortune){
    //This is a recursive function, so a basis case is necessary to end the looping over the function.
    user.giveStatus("Player nr: " + getId());
    user.giveStatus("\nCurrent position: ");
    start.printDescription();
    //This is a recursive function, so a basis case is necessary to end the looping over the function.
    if (start.isExit()){
      user.giveStatus("\nI have now reached the end!");
      user.giveStatus("The result is the following:");
      user.giveStatus("Player: " + getId() + ", fortune: " + getFortune());
      user.giveStatus("");
      return;
    }
    //I assume that the game is over when the player has sold all of his fortune and has no fortune left.
    if (backpack.getSize() == 0){
      user.giveStatus("\nMy backpack is empty. The game is over for me");
      user.giveStatus("Player: " + getId() + ", fortune: 0");
      user.giveStatus("");
      return;
    }

    user.giveStatus("\nThe treasures I carry with me are: ");
    for (Item it : backpack){
      System.out.println(it);
    }
    user.giveStatus("\nMy summed fortune is: " + myFortune);

    String [] options = {"1. Yes", "2. No"};
    int choise = user.askForCommand("\nShould I sell anything to the board? ", options);
    if (choise != 1 && choise != 2){user.giveStatus("Invalid input. Please try again");}

    if (choise == 1){
      if (!(start.getTreasures().full())){
      Item hasbeenremoved = backpack.remove();
      int sellValue = start.getTreasures().add(hasbeenremoved);
      hasbeenremoved.updateValue(sellValue);
      user.giveStatus("\n" + hasbeenremoved.getName() + " sold for " + sellValue +
      "\nThere are " + backpack.getSize() + " items left in my backpack.");
      updateFortune();
      user.giveStatus("\nMy fortune now is: " + myFortune);
      }
      else{user.giveStatus("The board chest is full.");}
    }

    if (backpack.full()){
      user.giveStatus("\nMy backpack is full. I cannot add more items.");
    }
    else if(start.getTreasures().getSize()==0){
      user.giveStatus("\nThe board chest is empty. I cannot buy something.");
    }
    else {
        int buyChoise = user.askForCommand("\nThere is still space left. Should I buy more items from the chest?", options);
        if (buyChoise == 1){
          Item tobeadded = start.getTreasures().remove();
          int buyValue = backpack.add(tobeadded);
          tobeadded.updateValue(buyValue);
          user.giveStatus(tobeadded.getName() + ", bought for " + buyValue + "\nThere are now " + backpack.getSize() +
          " items in my backpack.");
          updateFortune();
          for (Item it : backpack){
            System.out.println(it);
          }
          user.giveStatus("\nMy fortune now is: " + myFortune);
        }
      }

    move(start.moveForward(), myFortune);
    }
  }
