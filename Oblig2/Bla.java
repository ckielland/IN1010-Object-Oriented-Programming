class Bla extends Resept{

  private Double nypris;
  private String farge = "Bla";

  public Bla(Legemiddel legemiddel, Lege legen, int pasient_id, int reit){
    super(legemiddel,legen,pasient_id, reit);
  }

//Abstrakt metoden maa implementeres her.
  @Override
  public String farge(){
    return farge;
  }

  //Abstrakt metoden maa implementeres her.
  @Override
  public Double prisAabetale(){
    nypris = legemiddel.hentPris() * 0.25;
    return nypris;
}
}
