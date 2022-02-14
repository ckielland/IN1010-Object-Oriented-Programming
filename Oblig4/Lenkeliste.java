import java.util.Iterator;

class Lenkeliste<T> implements Liste<T>{
  class Node {
    Node next = null;
    T data;
    Node(T x){
      data = x;
    }
  }

  class LenkelisteIterator implements Iterator<T>{
    private Node temporary;

    public LenkelisteIterator(){
      temporary = start;
    }

   public boolean hasNext(){
      return temporary != null;
    }

    public T next(){
     Node current = temporary;
     temporary = temporary.next;
     return current.data;
   }
}

  protected Node start = null;
  protected Node slutt = null;
  protected int stoerrelse;

  public Lenkeliste(){
    stoerrelse = 0;
  }

  public Iterator<T> iterator(){
     return new LenkelisteIterator();
   }

  public int stoerrelse(){
    return stoerrelse;
  }

  public void leggTil(int pos, T x){
    if (pos <0 || pos > stoerrelse + 1 || pos > stoerrelse){
      throw new UgyldigListeIndeks(pos);
    }

    Node ny = new Node(x);
    Node temporary;

    if ((pos == 0) && (start != null)){
      temporary = start;
      start = ny;
      ny.next = temporary;
      stoerrelse ++;
    }
    else if ((pos == 0) && (start == null)){
      start = ny;
      slutt = ny;
      stoerrelse ++;
    }
    else if (pos == stoerrelse){
      slutt.next = ny;
      slutt = ny;
      stoerrelse ++;
    }
    else {
      temporary = start;
      for (int i = 0; i < pos - 1; i ++){
        temporary = temporary.next;
    }
    ny.next = temporary.next;
    temporary.next = ny;
    stoerrelse ++;
    }
  }


  public void leggTil(T x){
    Node ny = new Node(x);
    if (start == null){
      start = ny;
      slutt = ny;
      stoerrelse ++;
    }
    else {
      slutt.next = ny;
      slutt = ny;
      stoerrelse ++;
    }
  }


  public void sett(int pos, T x){
    if (start == null || pos <0 || pos >= stoerrelse){
        throw new UgyldigListeIndeks(pos);
      }

    Node ny = new Node(x);
    Node temporary = start;
    for (int i = 0; i < pos; i++ ){
      temporary = temporary.next;
    }
    if (pos == stoerrelse -1){
      temporary.next = ny;
      ny = slutt;}
    else{
      temporary.data = ny.data;
      ny.next = temporary.next;
  }
  }

  public T hent(int pos){
    if (start == null || pos <0 || pos >= stoerrelse){
        throw new UgyldigListeIndeks(pos);
      }

      if (pos == stoerrelse - 1){
      return slutt.data;
    }

    Node temporary = start;
    for (int i = 0; i <pos; i++){
      temporary = temporary.next;
    }
    return temporary.data;
  }

  public T fjern(int pos){
    if (start == null || pos <0 || pos >= stoerrelse){
        throw new UgyldigListeIndeks(pos);
      }

    Node temporary = start;
    if (pos == 0){
      start = temporary.next;
      stoerrelse --;
      return temporary.data;
    }

    for (int i = 0; i < pos - 1; i ++){
      temporary = temporary.next;
    }
    if (pos == stoerrelse -1){
      Node skipped = temporary.next;
      temporary.next = null;
      temporary = slutt;
      stoerrelse --;
      return skipped.data;
    }
    else{
      Node next = temporary.next.next;
      Node skipped = temporary.next;
      temporary.next = next;
      stoerrelse --;
      return skipped.data;
      }
  }


  public T fjern(){
    if (start == null){
      int pos = 0;
        throw new UgyldigListeIndeks(pos);
      }

    Node fjern = start;
    start = fjern.next;
    stoerrelse --;
    return fjern.data;
  }
}
