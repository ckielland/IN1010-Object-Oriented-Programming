class Hvit extends Resept{

    protected String farge = "Hvit";
    protected Double pris;

    public Hvit(Legemiddel legemiddel, Lege legen, Pasient pasient, int reit){
      super(legemiddel,legen,pasient, reit);
    }

    public Hvit(Legemiddel legemiddel, Lege legen, Pasient pasient){
      super(legemiddel,legen,pasient);
    }
//Abstrakt metoden maa implementeres her.
    @Override
    public String farge(){
      return farge;
    }

    //Abstrakt metoden maa implementeres her.
    @Override
    public Double prisAabetale(){
      pris = legemiddel.hentPris();
      return pris;
  }

}
