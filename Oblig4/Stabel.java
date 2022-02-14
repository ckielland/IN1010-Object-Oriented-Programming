class Stabel<T> extends Lenkeliste<T>{
  public Stabel(){
    super();
  }

  public void leggPaa(T x){
    super.leggTil(x);
  }

  public T taAv(){
      int pos = this.stoerrelse() - 1;
    return super.fjern(pos);
  }

  public T HentSist(){
    return slutt.data;
  }
}
