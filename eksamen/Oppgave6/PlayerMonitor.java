import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.CountDownLatch;

class PlayerMonitor{
  private ReentrantLock laas = new ReentrantLock();
  private Condition notEmpty = laas.newCondition();
  private Condition notFull = laas.newCondition();
  private Condition myTurn = laas.newCondition();
  private User user;
  private int toPlayNext = 1; //To keep track of whose turn is it to play. An effort to synchronize the threads.
  private int totalPlayers;
  private int[] finishedPlayers; //Keep track of the players that are out of the game
  private CountDownLatch count;
  private String message = "****************";
  private int activePlayers;

  public PlayerMonitor(Terminal user, int totalPlayers, CountDownLatch count){
    this.user = user;
    this.totalPlayers = totalPlayers;
    activePlayers = totalPlayers;
    this.count = count;
    finishedPlayers = new int[totalPlayers];
  }

  public PlayerMonitor(Robot user, int totalPlayers, CountDownLatch count){
    this.user = user;
    this.totalPlayers = totalPlayers;
    activePlayers = totalPlayers;
    this.count = count;
    finishedPlayers = new int[totalPlayers];
  }

  public void setFinished(int id){
    finishedPlayers[id-1] = id;
  }

  public boolean contains(int[] array, int value){
    boolean result = false;
    for (int i = 0; i < array.length; i ++){
      if (array[i] == value){
        result = true;
        break;
      }
    }
    return result;
  }

  public void move(Player p){
    try{
    laas.lock();

    while(p.getId()!= toPlayNext){
      user.giveStatus("( " + p.getId() + " is waiting for his/her turn to play.)");
      if (allFinished()){return;}
      myTurn.await();
    }

    TreasureChest backpack = p.Getbackpack();
    user.giveStatus("Player nr: " + p.getId() + "\nCurrent position: " + p.getMyPlace().getDescription());
    if (p.getMyPlace().isExit()){
      setFinished(p.getId());
      p.setFinished();
      user.giveStatus("I have now reached the end!\nThe result is the following:Player: " + p.getId() + ", fortune: " + p.getFortune());
      updateResult("\nPlayer: " + p.getId() + ", fortune: " + p.getFortune() + "\n****************");
      count.countDown();
      activePlayers--;
      return;
    }
    TreasureChest commonChest = p.getCommonChest();
    while(commonChest.full()){
      notFull.await();
    }
    user.giveStatus("\nPlayer: " + p.getId() + " is now playing: ");

    user.giveStatus("\nThe treasures I carry with me are: ");
    for (Item it : backpack){
      System.out.println(it);
    }
    user.giveStatus("\nMy summed fortune is: " + p.getFortune());

    String [] options = {"1. Yes", "2. No"};
    int choise = user.askForCommand("\nShould I sell anything to the board? ", options);
    if (choise != 1 && choise != 2){user.giveStatus("Invalid input. Please try again");}
    if (choise == 1){
      Item hasbeenremoved = backpack.remove();
      int sellValue = commonChest.add(hasbeenremoved);
      hasbeenremoved.updateValue(sellValue);
      user.giveStatus("\n" + hasbeenremoved.getName() + " sold for " + sellValue +
      "\nThere are " + backpack.getSize() + " items left in my backpack.");
      p.updateFortune();
      notEmpty.signalAll();
      user.giveStatus("\nMy fortune now is: " + p.getFortune());
    }

      while(commonChest.getSize() == 0){
        notEmpty.await();
      }

        if (backpack.full()){
          user.giveStatus("\nMy backpack is full. I cannot add more items.");
        }
        else {
            int buyChoise = user.askForCommand("\nThere is still space left. Should I buy more items from the chest?", options);
            if (buyChoise == 1){
              Item tobeadded = commonChest.remove();
              int buyValue = backpack.add(tobeadded);
              tobeadded.updateValue(buyValue);
              user.giveStatus(tobeadded.getName() + ", bought for " + buyValue + "\nThere are now " + backpack.getSize() +
              " items in my backpack.");
              p.updateFortune();
              notFull.signalAll();
              for (Item it : backpack){
                System.out.println(it);
              }
              user.giveStatus("\nMy fortune now is: " + p.getFortune());
            }
            else if (buyChoise == 2){
              if (backpack.getSize() == 0){
                setFinished(p.getId());
                p.setFinished();
                user.giveStatus("Player nr: " + p.getId() + "My backpack is empty. The game is over for me\nPlayer: " + p.getId() + ", fortune: 0");
                updateResult("\nPlayer: " + p.getId() + ", fortune: " + p.getFortune() + "\n****************");
                count.countDown();
                activePlayers--;
                if (!allFinished()){
                updateTurn();
                System.out.println("Next:" + toPlayNext);
                }
                return;
              }
            }
          }
          if (activePlayers != 1){
           if (!allFinished()){
          updateTurn();
          System.out.println("Next:" + toPlayNext);
          }
        }
      }
    catch(InterruptedException e){
      System.out.println("I got interrupted");
    }
    finally{laas.unlock();}
  }


  public void updateTurn(){
    if (toPlayNext == totalPlayers){
      toPlayNext = 1;
    }
    else{toPlayNext ++;}
  while (contains(finishedPlayers, toPlayNext)){
    toPlayNext++;
    if (toPlayNext == totalPlayers || contains(finishedPlayers, totalPlayers)){
      toPlayNext = 1;
    }
  }
    myTurn.signalAll();
  }

  public boolean allFinished(){
    boolean result = true;
    for (int i = 0; i < finishedPlayers.length; i ++){
      if (finishedPlayers[i] != i+1){
        result = false;
        break;
      }
    }
    return result;
  }

  public void updateResult(String s){
    message = message + s;
  }

  public void printResult(){
    message = message + "\n****************";
    System.out.println(message);
    }

}
