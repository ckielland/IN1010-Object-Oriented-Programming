abstract class Resept{

  private static int counter = 0;
  protected int resept_id = 0;
  protected Legemiddel legemiddel;
  protected Lege legen;
  protected int pasient_id;
  protected int reit;
  protected int bruk_counter = 0; //for aa holde redde paa hvor mange ganger resepten ble opprettet

  public Resept(Legemiddel legemiddel, Lege legen, int pasient_id, int reit) {
    this.legemiddel = legemiddel;
    this.legen = legen;
    this.pasient_id = pasient_id;
    resept_id = counter;
    counter ++;
    this.reit = reit;

    if (reit == 0){
      System.out.println("Resepten er ugyldig!");
      return;} //Return for aa slutte med klasse instansen her. Er det riktig?
  }

//Konstruktor nr 2 uten aa ta reit som argument. Brukes senere fra Presept subklasse.
    public Resept(Legemiddel legemiddel, Lege legen, int pasient_id) {
      this.legemiddel = legemiddel;
      this.legen = legen;
      this.pasient_id = pasient_id;
      resept_id = counter;
      counter ++;}

  public int hentID(){
    return resept_id;
  }

  public String hentLegemiddel(){
    return legemiddel.hentNavn();
  }

  public String hentLege(){
    return legen.HentNavn();
  }

  public int hentPasientID(){
    return pasient_id;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    if (bruk_counter < reit){
      bruk_counter ++;
      return true;
    }
    else {return false;}
  }

  abstract public String farge();

  abstract public Double prisAabetale();


  @Override
  public String toString(){
    return " - Resept ID: " + hentID() + "\n - Lege: " + hentLege() + "\n - Legemiddel: " + hentLegemiddel()
    + "\n - Pasient ID: " + hentPasientID() + "\n - Reit: " + hentReit() + "\n - Farge: " + farge()
    + "\n - Pris: " + prisAabetale();
  }
}
