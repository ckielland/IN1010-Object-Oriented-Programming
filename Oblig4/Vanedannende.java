class Vanedannende extends Legemiddel{
  private int styrke;

  public Vanedannende(String navn, Double pris, Double virkestoff, int styrke){
    super(navn,pris,virkestoff);
    this.styrke = styrke;
  }

  @Override
  public int HentStyrke(){
    return styrke;
  }
  
  @Override
  public String toString(){
    return super.toString() + ", vannedannende Styrke:" + styrke;
  }
}
