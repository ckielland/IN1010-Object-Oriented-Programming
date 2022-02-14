import java.io.File;
import java.io.IOException;

class Hovedprogram{
  public static void main(String [] arg){
    Labyrint lb = null;
    try{
    File fil = new File("provefil.txt");
    lb = lb.lesFraFil(fil);
    //lb.hentNaboer(3,5);
    lb.finnUtveiFra(5,3);
    lb.skrivUtutveier();

    //System.out.println(lb);
  }
  catch (IOException e){
    System.out.println(e);
  }
}
}
