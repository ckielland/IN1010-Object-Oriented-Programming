class Presept extends Hvit{

  private Double nypris;
  private int reit = 3;


  public Presept(Legemiddel legemiddel, Lege legen, Pasient pasient){
    super(legemiddel,legen,pasient);
  }

//Override metoden for aa fa ny implementasjon. Her ny reit.
   @Override
   public int hentReit(){
     return reit;
   }

//Override metoden for aa fa ny implementasjon.
  @Override
  public Double prisAabetale(){
    if (legemiddel.hentPris() < 108){
    nypris = 0.0;}
    else {nypris = (legemiddel.hentPris() - 108);}
  return nypris;
  }
}
