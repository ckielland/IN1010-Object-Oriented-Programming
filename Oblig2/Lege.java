class Lege {

  protected String navn;

public Lege(String navn){
  this.navn = navn;
}

public String HentNavn(){
  return navn;
}

@Override
public String toString(){
  return "Legen heter: " + HentNavn();
}
}
