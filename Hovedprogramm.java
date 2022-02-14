class Hovedprogram{
  public static void main(String[] args){
  Dataklynge abel = new Dataklynge("dataklynge.txt");
  System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
  System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
  System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
  System.out.println("Antall prosessorer: " + abel.antProsessorer());
  System.out.println("Antall rack: " + abel.antRacks());
  }
}
