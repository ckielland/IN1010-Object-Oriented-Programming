class Item{
  private String name;
  private int value;

  // I have decided to read the file "gjenstander.txt" in the main class (aka Game)
  public Item(String name, int value){
    this.name = name;
    this.value = value;
  }

  public String getName(){
    return name;
  }

//Set the value that the item was last sold for.
  public void updateValue(int newValue){
    value = newValue;
  }

  public int getValue(){
    return value;
  }

  public String toString(){
    return "Name: " + name + ", updated value: " + value;
  }
}
