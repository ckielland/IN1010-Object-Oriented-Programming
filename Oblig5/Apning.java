class Apning extends HviteRute{

  public Apning(int kolonne, int rad){
    super(kolonne, rad);
  }

  @Override
  public String extendmyPath(){
    return "(" + hentkolonne() + ", " + hentrad() + ")";
  }

@Override
  public void gaa(Rute previous, String path){
    path = path + extendmyPath();
    labyrint.leggTilUtvei(path);
    return;
  }

}
