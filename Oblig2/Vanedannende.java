class Vanedannende extends Legemiddel{
  private int styrke;

  public Vanedannende(String navn, Double pris, Double virkestoff, int styrke){
    super(navn,pris,virkestoff);
    this.styrke = styrke;
  }

  public int HentVaneDannendeStyrke(){
    return styrke;
  }
  @Override
  public String toString(){
    return super.toString() + "\n - Vannedannende Styrke:" + styrke;
  }
}
