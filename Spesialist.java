class Spesialist extends Lege implements Godkjenningsfritak{

  private String navn;
  private int KontrollId;

  public Spesialist(String navn, int KontrollId){
    super(navn);
    this.KontrollId = KontrollId;
  }

  public int hentKontrollId(){
  return KontrollId;
  }

  @Override
  public String toString(){
    return super.toString() + ", spesialist kontroll Id: " + hentKontrollId();
  }
}
