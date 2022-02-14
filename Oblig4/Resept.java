abstract class Resept{

  private static int counter = 0;
  protected int resept_id = 0;
  protected Legemiddel legemiddel;
  protected Lege legen;
  protected Pasient pasient;
  protected int reit;
  protected int bruk_counter = 0; //for aa holde redde paa hvor mange ganger resepten ble opprettet

  public Resept(Legemiddel legemiddel, Lege legen, Pasient pasient, int reit) {
    this.legemiddel = legemiddel;
    this.legen = legen;
    this.pasient = pasient;
    resept_id = counter;
    counter ++;
    this.reit = reit;

    if (reit == 0){
      System.out.println("Resepten er ugyldig!");
      return;} //Return for aa slutte med klasse instansen her. Er det riktig?
  }

//Konstruktor nr 2 uten aa ta reit som argument. Brukes senere fra Presept subklasse.
    public Resept(Legemiddel legemiddel, Lege legen, Pasient pasient) {
      this.legemiddel = legemiddel;
      this.legen = legen;
      this.pasient = pasient;
      resept_id = counter;
      counter ++;}

  public int hentID(){
    return resept_id;
  }

  public String hentLegemiddel(){
    return legemiddel.hentNavn();
  }

  public boolean narkotisk(){
    return legemiddel instanceof Narkotisk;
  }

  public boolean vanedannende(){
    return legemiddel instanceof Vanedannende;
  }

  public boolean vanlig(){
    return legemiddel instanceof Vanlig;
  }

  public boolean erGyldig(){
    boolean gyldig = true;
    if (reit == 0){
      gyldig = false;
    }
    return gyldig;
  }

  public String hentLege(){
    return legen.hentNavn();
  }

  public int hentPasientID(){
    return pasient.hentID();
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    if (bruk_counter < reit){
      bruk_counter ++;
      reit --;
      return true;
    }
    else {return false;}
  }

  public String hentType(){
    if (this instanceof Bla){
      return "blaa";
  }else if (this instanceof Presept){
    return "p";
  }else if (this instanceof Militarresept){
    return "militaer";
  }else{return "hvit";}
}

  abstract public String farge();

  abstract public Double prisAabetale();


  @Override
  public String toString(){
    return "Resept ID: " + hentID() + ", Lege: " + hentLege() + ", Legemiddel: " + hentLegemiddel()
    + ", Pasient ID: " + hentPasientID() + ", Reit: " + hentReit() + ", Farge: " + farge()
    + ", Pris: " + prisAabetale();
  }
}
