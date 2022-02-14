class MultipleExitPlayer extends Player{

  public MultipleExitPlayer(Place startingPoint, Terminal user, TreasureChest backpack){
    super(startingPoint, user, backpack);
  }

  public MultipleExitPlayer(Place startingPoint, Robot user, TreasureChest backpack){
    super(startingPoint, user, backpack);
  }

  @Override
  public void move(Place start, int fortune){
      MultipleExitPlace start1 = (MultipleExitPlace) start;
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
        Item hasbeenremoved = backpack.remove();
        int sellValue = start.getTreasures().add(hasbeenremoved);
        hasbeenremoved.updateValue(sellValue);
        user.giveStatus("\n" + hasbeenremoved.getName() + " sold for " + sellValue +
        "\nThere are " + backpack.getSize() + " items left in my backpack.");
        updateFortune();
        user.giveStatus("\nMy fortune now is: " + myFortune);
      }

      if (backpack.full()){
        user.giveStatus("\nMy backpack is full. I cannot add more items.");
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

     String [] exits = start1.getExitDescription();
     int exitchoise = user.askForCommand("Which way should I choose next?", exits);
     if (exitchoise == 1){
       move(start1.moveRight(), myFortune);
     }
     else if (exitchoise == 2){
       move(start1.moveLeft(), myFortune);
     }
     else if (exitchoise == 3){
       move(start1.moveStraight(), myFortune);
     }
     else {System.out.println("Wrong input.");}
  }
}
