class Pasient{
  private String navn;
  private String foedselsnummer;
  private static int teller = 0;
  private int pasient_id = 0;
  private Stabel<Resept> reseptliste = new Stabel<Resept>();

  public Pasient(String navn, String foedselsnummer){
    this.navn = navn;
    this.foedselsnummer = foedselsnummer;
    pasient_id = teller;
    teller++;
  }

  public void skrivUt(){
    int teller = 0;
    for (Resept r: reseptliste){
      System.out.println(teller + ": " + r.hentLegemiddel() + " " + "(" + r.hentReit() + ")");
      teller++;
    }
  }

  public Resept hent(int pos){
    return reseptliste.hent(pos);
  }

  public boolean Narkotisk(){
    boolean narkotisk = false;
    for (Resept r: reseptliste){
      if (r.narkotisk() && r.erGyldig()){
        narkotisk = true;
        if(narkotisk){
          return true;
        }
      }
    }
    return false;
  }

  public boolean Vanlig(){
    boolean vanlig = false;
    for (Resept r: reseptliste){
      if (r.vanlig() && r.erGyldig()){
        vanlig = true;
        if(vanlig){
          return true;
        }
      }
    }
    return false;
  }

  public boolean Vanedannende(){
    boolean vanedannende = false;
    for (Resept r: reseptliste){
      if (r.vanedannende() && r.erGyldig()){
        vanedannende = true;
        if(vanedannende){
          return true;
        }
      }
    }
    return false;
  }

  public int AntallNarkotisk(){
    int teller = 0;
    for (Resept r: reseptliste){
      if (r.narkotisk()){
        teller ++;
      }
    }
    return teller;
  }

  public int AntallVanlig(){
    int teller = 0;
    for (Resept r: reseptliste){
      if (r.vanlig()){
        teller ++;
      }
    }
    return teller;
  }

  public int AntallVanedannende(){
    int teller = 0;
    for (Resept r: reseptliste){
      if (r.vanedannende()){
        teller ++;
      }
    }
    return teller;
  }

  public void brukResept(Resept r){
      if (r.bruk()){
        System.out.println("Brukte resept paa" + r.hentLegemiddel() +"." + "Antall gjenvaerende reit " + r.hentReit());
      }
      else if (r.bruk() == false){
      if (reseptliste.HentSist().equals(r)){
        reseptliste.taAv();
        System.out.println("Kunne ikke bruke resept paa" + r.hentLegemiddel() + "(ingen gjenvaerende reit).");
      }
      else{
        int pos = reseptliste.stoerrelse() - 1;
        reseptliste.sett(pos, r);
        reseptliste.taAv();
        System.out.println("Kunne ikke bruke resept paa" + r.hentLegemiddel() + "(ingen gjenvaerende reit).");
      }
    }
  }

  public void LeggTilResept(Resept r){
     reseptliste.leggPaa(r);
  }

  public String HentFoedselsnummer(){
    return foedselsnummer;
  }

  public String hentNavn(){
    return navn;
  }

  public int hentID(){
    return pasient_id;
  }

  @Override
  public String toString(){
    return "pasient_id: " + pasient_id + "," +" navn: " + navn + ", (foedselsnummer: " + foedselsnummer +")";
}
}
