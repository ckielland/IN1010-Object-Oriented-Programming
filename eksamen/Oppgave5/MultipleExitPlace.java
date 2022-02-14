class MultipleExitPlace extends Place{

  private MultipleExitPlace[] exits = new MultipleExitPlace[3];
  private String[] exitDescription = new String[3];
  private MultipleExitPlace right = null;
  private MultipleExitPlace left = null;
  private MultipleExitPlace straight = null;
  private boolean gameOver = false; //I have decided that the game will end when the players will reach a randomly allocated
  //Game over sign

  public MultipleExitPlace(String description){
    super(description);
  }

  public String[] getExitDescription(){
  return exitDescription;
  }

  public void setGameOver(){
    gameOver = true;
  }

  public void setRight(MultipleExitPlace rgt){
    right = rgt;
    exits[0] = right;
    exitDescription[0] = "1. Right";
  }

  public void setLeft(MultipleExitPlace lft){
    left = lft;
    exits[1] = left;
    exitDescription[1] = "2. Left";
  }

  public void setStraight(MultipleExitPlace str){
    straight = str;
    exits[2] = straight;
    exitDescription[2] = "3. Straight";
  }

  public MultipleExitPlace moveRight(){
    return right;
  }

  public MultipleExitPlace moveLeft(){
    return left;
  }

  public MultipleExitPlace moveStraight(){
    return straight;
  }

  @Override
  public boolean isExit(){
    return gameOver;
  }

  @Override
  public int antallExits(){
    int counter = 0;
    for (int i = 0; i < exits.length; i ++){
      if (exits[i] != null){
        counter++;
      }
    }
    return counter;
  }
}
