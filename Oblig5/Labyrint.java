import java.util.*;
import java.io.*;

class Labyrint implements Comparable<Labyrint>{
  private static int kolonner;
  private static int rader;
  private static Rute[][] labyrint;
  private static ArrayList<Apning> apningliste = new ArrayList<Apning>();
  private static String path = "";
  private static Liste<String> utveier;

  private Labyrint(int rader, int kolonner){
    this.kolonner = kolonner;
    this.rader = rader;
    labyrint = new Rute[kolonner][rader];
  }

  public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
    Scanner input = new Scanner(fil);
    int rader = input.nextInt();
    int kolonner = input.nextInt();
    Labyrint lb = new Labyrint(rader,kolonner);
    input.nextLine();
    int radCounter = 0;
    while(input.hasNextLine()){
      String line = input.nextLine();
    for (int i = 0; i < line.length(); i ++){
      if(line.charAt(i) == '#'){
        SortRute sort = new SortRute(i,radCounter);
        labyrint[i][radCounter] = sort;
        sort.charTilTegn();
      }
      else if (line.charAt(i) == '.'){
        if ((i == 0) || (i == line.length() -1)){
          Apning apning = new Apning(i,radCounter);
          labyrint[i][radCounter] = apning;
          apningliste.add(apning);
          apning.charTilTegn();
        }
      else if ((radCounter == 0) || (radCounter == rader - 1)){
        Apning apning = new Apning(i,radCounter);
        labyrint[i][radCounter] = apning;
        apningliste.add(apning);
        apning.charTilTegn();
      }
        else{HviteRute hvit = new HviteRute(i,radCounter);
        labyrint[i][radCounter] = hvit;
        hvit.charTilTegn();}
      }
    }
    radCounter ++;
  }
  input.close();
  for (int i = 0; i < labyrint.length; i ++){
    for (int j = 0; j < labyrint[0].length; j++){
      finnNabo(i,j);
    }
  }
  for (int i = 0; i < labyrint.length; i ++){
    for (int j = 0; j < labyrint[0].length; j++){
      labyrint[i][j].settLabyrint(lb);
    }
  }
  return lb;
  }

  public static String hentPath(){
    return path;
  }

  public static void leggTilUtvei(String nypath){
    utveier.leggTil(nypath);
  }

  public int compareTo(Labyrint annen){
    return hentPath().compareTo(annen.hentPath());
  }

  public static void skrivUtutveier(){
    for (String p : utveier){
      System.out.println(p);
    }
  }

  public static int AntallApninger(){
    return apningliste.size();
  }

  public static ArrayList<Apning> Apningsliste(){
    return apningliste;
  }

  public static void finnNabo(int kolonne, int rad){
    if (rad == 0){
      labyrint[kolonne][rad].settNord(null);
      labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
      if (kolonne == 0){
        labyrint[kolonne][rad].settOest(null);
        labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      }
      else if (kolonne == hentKolonner() -1){
        labyrint[kolonne][rad].settVest(null);
        labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
      }
      else{
        labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
        labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      }
    }
    else if(rad == hentRader() - 1){
      labyrint[kolonne][rad].settSyd(null);
      labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
      if (kolonne == 0){
        labyrint[kolonne][rad].settOest(null);
        labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      }
      else if (kolonne == hentKolonner() -1){
        labyrint[kolonne][rad].settVest(null);
        labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
      }
      else{
        labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
        labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      }
    }
    if ((kolonne == 0) && (rad != 0) && (rad!= hentRader() - 1)){
      labyrint[kolonne][rad].settOest(null);
      labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
      labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
    }
    else if((kolonne == hentKolonner() - 1) && (rad != 0) && (rad!= hentRader() - 1)){
      labyrint[kolonne][rad].settVest(null);
      labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
      labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
      labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
    }
    else if((kolonne != 0) && (rad != 0) && (rad!= hentRader() - 1) && (kolonne != hentKolonner() -1)){
      labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
      labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
      labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
      labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
    }
  }

  public static int hentKolonner(){
    return kolonner;
  }

  public static int hentRader(){
    return rader;
  }

  public static void ResetTegn(){
    for (int i = 0; i < labyrint.length; i ++){
      for (int j = 0; j < labyrint[0].length; j ++){
        labyrint[i][j].charTilTegn();
      }
    }
  }

  public static Liste<String> finnUtveiFra(int kolonne, int rad){
    utveier = new Lenkeliste<String>();
    labyrint[kolonne][rad].finnUtvei();
  return utveier;
  }


  @Override
  public String toString(){
    String s = " ";
    for (int i = 0; i < labyrint.length; i ++){
      for (int j = 0; j < labyrint[0].length; j ++){
        s = s + labyrint[i][j].charTilTegn();
      }
    }
    return s;
  }

}
