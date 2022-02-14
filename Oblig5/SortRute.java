class SortRute extends Rute{

  public SortRute(int kolonne, int rad){
    super(kolonne, rad);
  }

  @Override
  public void gaa(Rute previous, String path){
    return;
  }

  public String charTilTegn(){
    tegn ="#";
    return tegn;
  }
}
