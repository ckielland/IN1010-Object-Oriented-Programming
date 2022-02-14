class SortertLenkeliste <T extends Comparable <T>> extends Lenkeliste<T>{

  public SortertLenkeliste(){
    super();
  }

  @Override
  public void leggTil(T x){
    if (start == null){
      super.leggTil(x);
      return;
    }

    if (this.stoerrelse == 1 && hent(0).compareTo(x) > 0){
    super.leggTil(0,x);
    return;
    }

      Node ny = new Node(x);
      Node temporary = start;
      while(temporary.next != null && temporary.next.data.compareTo(ny.data) < 0)
      temporary = temporary.next;
      if (temporary.next == null){
        ny.next = null;
        temporary.next = ny;
        slutt = ny;
        stoerrelse ++;
      }
    else{
      ny.next = temporary.next;
      temporary.next = ny;
      stoerrelse ++;
    }
  }

  @Override
  public T fjern(){
    if (start == null){
      int pos = 0;
        throw new UgyldigListeIndeks(pos);
      }
//Given that the legg til method works fine, then the element with the highest value is the last one.
      return super.fjern(stoerrelse -1);
  }

  @Override
  public void sett(int pos, T x){
    if (start == null || pos <0 || pos >= stoerrelse){
      throw new UnsupportedOperationException();
    }

    if (pos == 0 && hent(0).compareTo(x) > 0){
      super.sett(0,x);
    }

    Node temporary = start;
    for (int i = 0; i < pos -1 ; i++){
      temporary = temporary.next;
    }
    if (hent(pos).compareTo(x) > 0 && temporary.data.compareTo(x) < 0){
      super.sett(pos,x);
    }
    else {throw new UnsupportedOperationException();}
  }

  @Override
  public void leggTil (int pos, T x){
    if (pos < 0 || pos > stoerrelse + 1 || pos > stoerrelse){
      throw new UnsupportedOperationException();
    }
    if (pos == 0 && hent(0).compareTo(x) > 0){
      super.leggTil(0,x);
    }

    Node temporary = start;
    for (int i = 0; i < pos -1 ; i++){
      temporary = temporary.next;
    }
    if (hent(pos).compareTo(x) > 0 && temporary.data.compareTo(x) < 0){
      super.leggTil(pos,x);
    }
    else {throw new UnsupportedOperationException();}
  }
}
