abstract class Rute{
  protected int kolonne;
  protected int rad;
  protected Labyrint labyrint;
  protected Rute nord;
  protected Rute syd;
  protected Rute oest;
  protected Rute vest;
  protected String tegn;

  public Rute(int kolonne, int rad){
    this.kolonne = kolonne;
    this.rad = rad;
  }

  public int hentkolonne(){
    return kolonne;
  }

  public int hentrad(){
    return rad;
  }

  public void settLabyrint(Labyrint nyLabyrint){
    labyrint = nyLabyrint;
  }

  public void skrivUtKoordinater(){
    System.out.print(kolonne);
    System.out.print(rad);
    System.out.print(" ");
  }


  public void settNord(Rute nyNord){
  nord = nyNord;
  }

  public void settSyd(Rute nySyd){
    syd = nySyd;
  }

  public void settOest(Rute nyOest){
  oest = nyOest;
  }

  public void settVest(Rute nyVest){
  vest = nyVest;
  }

  public String extendmyPath(){
    return "(" + hentkolonne() + ", " + hentrad() + ") --> ";
  }

  public void gaa(Rute previous, String path){

    path = path + extendmyPath();

    if (!(nord.equals(previous))){
      nord.gaa(this,path);
    }

    if (!(oest.equals(previous))){
      oest.gaa(this,path);
    }

    if (!(syd.equals(previous))){
      syd.gaa(this,path);
    }

    if (!(vest.equals(previous))){
      vest.gaa(this,path);
    }
  }

  public void finnUtvei(){
    gaa(null, " ");
  }

  abstract public String charTilTegn();

  public String hentTegn(){
    return tegn;
  }


  // @Override
  // public String toString(){
  //   return "kolonne: " + kolonne + " rad: " + rad;
  // }

}
