import java.util.Scanner;
import java.io.FileNotFoundException;

class Legesystemprogram{

    public static void main(String[] args) throws UlovligUtskrift, FileNotFoundException{

      Legesystem legesystem = new Legesystem();
      try{
      legesystem.LesFraFil("MyeInndata.txt");
      legesystem.Hovedmeny();
    }
      catch(UlovligUtskrift e){
        System.out.println(e);
        legesystem.Hovedmeny();

      }

  }
}
