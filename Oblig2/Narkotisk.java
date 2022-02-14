class Narkotisk extends Legemiddel{
  private int styrke;

  public Narkotisk(String navn, Double pris, Double virkestoff, int styrke){
    super(navn,pris,virkestoff);
    this.styrke = styrke;
  }

  public int HentNarkotiskStyrke(){
    return styrke;
  }
  @Override
  public String toString(){
    return super.toString() + "\n - Narkotisk Styrke:" + styrke;
  }
}
