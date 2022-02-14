class Hvit extends Resept{

    protected String farge = "Hvit";
    protected Double pris;

    public Hvit(Legemiddel legemiddel, Lege legen, int pasient_id, int reit){
      super(legemiddel,legen,pasient_id, reit);
    }

    public Hvit(Legemiddel legemiddel, Lege legen, int pasient_id){
      super(legemiddel,legen,pasient_id);
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
