public abstract class Legemiddel{

  private static int teller = 0;
  protected int legemiddel_id = 0;
  protected String navn;
  protected Double pris;
  protected Double virkestoff;

  public Legemiddel(String navn, Double pris, Double virkestoff) {
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    legemiddel_id = teller;
    teller++;
  }

  public int hentID(){
    return legemiddel_id;
  }

  public String hentNavn(){
    return navn;
  }

  public Double hentPris(){
    return pris;
  }

  public Double hentVirkestoff(){
    return virkestoff;
  }

  public Double SettNyPris(Double nypris){
    return pris = nypris;
  }

  abstract public int HentStyrke();

  @Override
  public String toString(){
    return "Legemiddel ID: " + legemiddel_id + ", Navn: " + navn + ", Pris: " + pris + ", Virkestoff: " + virkestoff;
  }

}
