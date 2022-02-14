class Militarresept extends Hvit{

  private Double nypris;


  public Militarresept(Legemiddel legemiddel, Lege legen, int pasient_id, int reit){
    super(legemiddel,legen,pasient_id, reit);
  }

  //Override metoden for aa fa ny implementasjon.
  @Override
  public Double prisAabetale(){
  nypris = 0.0;
  return nypris;
  }

}
