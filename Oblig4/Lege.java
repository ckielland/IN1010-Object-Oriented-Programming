class Lege implements Comparable<Lege>{

  protected String navn;
  Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();

public Lege(String navn){
  this.navn = navn;
}

public String hentNavn(){
  return navn;
}

public int compareTo(Lege annen){
  return navn.compareTo(annen.navn);
}

public void SkrivUtListen(){
  for (Resept r : utskrevedeResepter){
    System.out.println(r);
  }
}

public Lenkeliste<Resept> hentReseptliste(){
  return utskrevedeResepter;
}

public int hentKontrollId(){
  return 0;
}

public boolean Narkotisk(){
  boolean narkotisk = false;
  for (Resept r: utskrevedeResepter){
    if (r.narkotisk()){
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
  for (Resept r: utskrevedeResepter){
    if (r.vanlig()){
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
  for (Resept r: utskrevedeResepter){
    if (r.vanedannende()){
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
  for (Resept r: utskrevedeResepter){
    if (r.narkotisk()){
      teller ++;
    }
  }
  return teller;
}

public int AntallVanlig(){
  int teller = 0;
  for (Resept r: utskrevedeResepter){
    if (r.vanlig()){
      teller ++;
    }
  }
  return teller;
}

public int AntallVanedannende(){
  int teller = 0;
  for (Resept r: utskrevedeResepter){
    if (r.vanedannende()){
      teller ++;
    }
  }
  return teller;
}


public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasientID, int reit)
throws UlovligUtskrift{
  Hvit hvitResept = new Hvit(legemiddel, this, pasientID, reit);
  utskrevedeResepter.leggTil(hvitResept);
  return hvitResept;
}

public Militarresept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasientID, int reit)
throws UlovligUtskrift{
  Militarresept militaerResept = new Militarresept(legemiddel, this, pasientID, reit);
  utskrevedeResepter.leggTil(militaerResept);
  return militaerResept;
}

public Presept skrivPResept(Legemiddel legemiddel, Pasient pasientID) throws UlovligUtskrift{
  Presept PResept = new Presept(legemiddel, this, pasientID);
  utskrevedeResepter.leggTil(PResept);
  return PResept;
}

public Bla skrivBlaaResept(Legemiddel legemiddel, Pasient pasientID, int reit) throws UlovligUtskrift{
  Bla Blaa = new Bla(legemiddel, this, pasientID, reit);
  utskrevedeResepter.leggTil(Blaa);
  return Blaa;
}

@Override
public String toString(){
  return "Navn: " + hentNavn();
}
}
