import java.util.Random;
import java.util.Iterator;

class TreasureChest implements Iterable<Item>{

  class Node{
    Node next = null;
    Item item;

    Node(Item it) {item = it;}
  }

  class LenkelisteIterator implements Iterator<Item>{
    private Node temporary;

    public LenkelisteIterator(){
      temporary = start;
    }

   public boolean hasNext(){
      return temporary != null;
    }

    public Item next(){
     Node current = temporary;
     temporary = temporary.next;
     return current.item;
   }
}

  private Node start = null;
  private int size;
  private int maxCapacity = 10;
  private Random rand = new Random();


  public TreasureChest(){
    size = 0;
  }

  public Iterator<Item> iterator(){
     return new LenkelisteIterator();
   }

  //It returns the Item to be removed
  public Item remove(){
    if (start == null){
      System.out.println("There are no items left to be removed");
      return null;
      }

    int pos = rand.nextInt(size);
    Node temporary = start;
    if (pos == 0){
      start = temporary.next;
      size --;
      return temporary.item;
    }

    for (int i = 0; i < pos - 1; i ++){
      temporary = temporary.next;
    }
      Node next = temporary.next.next;
      Node toberemoved = temporary.next;
      temporary.next = next;
      size --;
      return toberemoved.item;
  }

  public int add(Item x){
    if (size > maxCapacity){
      System.out.println("Sorry, this chest is full.");
      return 0;}

    Node newNode = new Node(x);

    if (start == null){
      start = newNode;
      size ++;
    }
   else {
     Node temporary = start;
     for (int i = 0; i < size - 1; i ++){
       temporary = temporary.next;
      }
      newNode.next = temporary.next;
      temporary.next = newNode;
      size ++;
     }

    int minprice = (int)Math.round(x.getValue() * 0.95);
    int maxprice = (int)Math.round(x.getValue() * 1.05);
    int i = rand.nextInt((maxprice - minprice) + 1) + minprice;
    return i;
  }

  public boolean full(){
    boolean isfull = false;
    if (size == maxCapacity){
      isfull = true;
    }
    return isfull;
  }

  //I need a method to fill in the treasure chest with items without returning anything.
  //Here I just place everything in the back.
  public void FillInn(Item x){
    if (size > maxCapacity){
      System.out.println("Sorry, this chest is full.");
      return;}

      Node newNode = new Node(x);
      if (start == null){
        start = newNode;
        size++;
      }
      else{
      Node temporary = start;
      for (int i = 0; i < size - 1; i ++){
        temporary = temporary.next;
      }
      newNode.next = temporary.next;
      temporary.next = newNode;
      size ++;
      }
    }

  public int getSize(){
    return size;
  }
}
