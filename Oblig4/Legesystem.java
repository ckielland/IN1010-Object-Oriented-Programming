import java.util.*;
import java.io.*;

class Legesystem{
  ArrayList<Pasient> pasient_list = new ArrayList<Pasient>();
  ArrayList<Legemiddel> legemidler_list = new ArrayList<Legemiddel>();
  ArrayList<Lege> leger_list = new ArrayList<Lege>();
  Scanner input = new Scanner(System.in);


  public void LesFraFil(String filnavn) throws UlovligUtskrift{

    Scanner fil;
    try {
           fil = new Scanner(new File(filnavn));
           String linje = fil.nextLine();
             if ((linje != null) && (linje.contains("Pasienter"))){
               linje = fil.nextLine();
                }
                while (!(linje.contains("Legemidler"))){
                  String [] ord = linje.trim().split(",");
                  String navn = ord[0];
                  String foedselsnummer = ord[1];

                  Boolean add = true;
                  if (foedselsnummer.length()!= 11){
                    System.out.println("Foedselsnummeret" + foedselsnummer + " bestaar ikke av 11 siffrer. Feil i filen.");
                    add = false;
                  }
                  if (add){
                  Pasient pasient = new Pasient(navn, foedselsnummer);
                  pasient_list.add(pasient);
                }
                  linje = fil.nextLine();
                }

                linje = fil.nextLine();
                while (!(linje.contains("Leger"))){
                  String[] ord = linje.trim().split(",");
                  String navn = ord[0];
                  String type = ord[1];

                  //Do not ask for pris and virkestoff here, to avoid getting an NumberFormatException here
                  // and proceed with the rest of the objects.

                  if (type.equals("narkotisk")){
                    Double pris = Double.parseDouble(ord[2]);
                    Double virkestoff = Double.parseDouble(ord[3]);
                    int styrke = Integer.parseInt(ord[4]);
                    Narkotisk narkotisk_legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
                    legemidler_list.add(narkotisk_legemiddel);
                    }

                  else if(type.equals("vanedannende")){
                    Double pris = Double.parseDouble(ord[2]);
                    Double virkestoff = Double.parseDouble(ord[3]);
                    int styrke = Integer.parseInt(ord[4]);
                    Vanedannende vanedannende_legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
                    legemidler_list.add(vanedannende_legemiddel);
                    }
                  else if(type.equals("vanlig")){
                    Double pris = Double.parseDouble(ord[2]);
                    Double virkestoff = Double.parseDouble(ord[3]);
                    Vanlig vanlig_legemiddel = new Vanlig(navn, pris, virkestoff);
                    legemidler_list.add(vanlig_legemiddel);
                    }
                 else{System.out.println("Feil type legemiddel.");
                      System.out.println("\nKan ikke legge denne: " + navn + ", til systemet.");
                    }

                  linje = fil.nextLine();
              }

                linje = fil.nextLine();
                while (!(linje.contains("Resepter"))){
                  String []ord = linje.trim().split(",");
                  String navn = ord[0];
                  int spesialist_id = Integer.parseInt(ord[1]);

                  if (spesialist_id != 0){
                    Spesialist spesialist_lege = new Spesialist(navn, spesialist_id);
                    leger_list.add(spesialist_lege);
                  }
                  else {
                    Lege lege = new Lege(navn);
                    leger_list.add(lege);
                  }
                  linje = fil.nextLine();
                }
                linje = fil.nextLine();
                while ((linje != null) && (fil.hasNextLine())){
                  String [] ord = linje.trim().split(",");
                  int legemiddelNummer = Integer.parseInt(ord[0]);

                  String legeNavn = ord[1];
                  int pasientID = Integer.parseInt(ord[2]);
                  String type_string = ord[3];

                  boolean valid = true;
                  Legemiddel legemiddel_resept = null;
                  Pasient pasient_resept = null;
                  Lege legen_resept = null;

                   if (legemiddelNummer > legemidler_list.size() || legemiddelNummer < 0){
                     System.out.println("Legemiddelnummer " + legemiddelNummer + " er feil.");
                     System.out.println("Det er ikke mulig aa opprette resepten.");
                     valid = false;
                   }
                   else {
                     legemiddel_resept = legemidler_list.get(legemiddelNummer);
                   }
                   if (pasientID > pasient_list.size() || pasientID < 0){
                     System.out.println("Pasientnummer " + pasientID + " er feil.");
                     System.out.println("Det er ikke mulig aa opprette resepten.");
                     valid = false;
                    }
                  else {
                    pasient_resept = pasient_list.get(pasientID);
                  }

                  boolean legeOK = false;
                     for (Lege l : leger_list){
                       if(l.hentNavn().equals(legeNavn)){
                         legeOK = true;
                         legen_resept = l;
                       }
                     }
                     if (!legeOK){
                       System.out.println("Det finnes ikke noe lege som heter " + legeNavn);
                       System.out.println("Det er ikke mulig aa opprette resepten.");
                     }

                  if (valid && legeOK){
                  int type_int = 0;
                  int reit = 0;
                  if (type_string.equals("hvit")){
                    type_int = 1;
                    reit = Integer.parseInt(ord[4]);
                  }
                  else if (type_string.equals("p")){
                    type_int = 2;
                    reit = 3;
                  }
                  else if(type_string.equals("militaer")){
                    type_int = 3;
                    reit = Integer.parseInt(ord[4]);
                  }
                  else if(type_string.equals("blaa")){
                    type_int = 4;
                    reit = Integer.parseInt(ord[4]);
                  }
                  else{System.out.println("Ugyldig type resept. Feil i filen");
                  System.out.println("Kan ikke legge den til systemet.");
                  }

                  try{
                  if ((type_int == 1) || (type_int == 2) ||(type_int == 3) ||(type_int == 4)){
                  Resept r = this.skrivResept(legen_resept, legemiddel_resept, pasient_resept, reit, type_int);
                  pasient_resept.LeggTilResept(r);
                  }
                }
                catch(UlovligUtskrift e){
                  System.out.println(e);
                }
                }

                  linje = fil.nextLine();
              }

                fil.close();
           }

     catch(FileNotFoundException e){
           System.out.println(e);
           System.out.println("Feil fil navn.");
       }
    catch(ArrayIndexOutOfBoundsException e){
      System.out.println(e);
      System.out.println("Feil i fil format.");
    }
    catch (NumberFormatException e){
      System.out.println(e);
      System.out.println("Feil i fil format.");
    }
  }

  public void Hovedmeny() throws UlovligUtskrift{
    int valg;
    boolean loekke = true;

    while (true){
    System.out.println("\n** Legesystem hovedmeny: **");
    System.out.println("1. Skrive ut en fullstendig oversikt over pasienter, leger, legemidler og resepter.");
    System.out.println("2. Opprette og legge til nye elementer i systemet.");
    System.out.println("3. Bruke en gitt resept fra listen til en pasient.");
    System.out.println("4. Skrive ut forskjellige former for statistikk.");
    System.out.println("5. Skrive alle data til fil.");
    System.out.println("6. Avslutte. ");
    System.out.println(" ");
    System.out.println("Angi et tall mellom 1 - 6: ");

    valg = input.nextInt();

    switch(valg){

      case 1:
      System.out.println("\nListen av leger: ");
      //To print the elements in alphabetical order
      Collections.sort(leger_list);
      for (Lege l: leger_list){
        System.out.println(l);
      }
      System.out.println("\nListen av pasienter:");
      for (Pasient p: pasient_list){
        System.out.println(p);
      }
      System.out.println("\nListen av legemidler:");
      for (Legemiddel lg: legemidler_list){
        System.out.println(lg);
      }
      System.out.println("\nListen av resepter: ");
      for (Lege l: leger_list){
        l.SkrivUtListen();
      }
      break;

      case 2:
      this.LeggeTilElementer();
      break;

      case 3:
      this.BrukeResepter();
      break;

      case 4:
      this.SkrivUtStatistikk();
      break;

      case 5:
      this.SkrivDataTilFil();
      break;

      case 6:
      System.out.println("Avslutter programmet.");
        System.exit(0);
         break;
    default :
             System.out.println("Det var ikke gyldig input. Angi et tall fra 1 til 6.");
             break;
      }
    }
  }

  public void LeggeTilElementer() throws UlovligUtskrift{
    int valg;
    boolean loekke = true;

    while (true){
    System.out.println("\n** Meny for aa legge til nye elementer: **");
    System.out.println("1. Lege");
    System.out.println("2. Pasient");
    System.out.println("3. Resept");
    System.out.println("4. Legemiddel");
    System.out.println("5. Gaa tilbake til hovedmenyen");
    System.out.println("\nAngi et tall mellom 1 - 5: ");


    valg = input.nextInt();

    switch(valg){
      case 1:
      Lege legen = this.LeggTilLege();
      System.out.println(" ");
      break;

      case 2:
      Pasient pasient = this.LeggTilPasient();
      System.out.println(" ");
      break;

      case 3:
      Lege legen_resept = null; Legemiddel legemidlen_resept = null; Pasient pasienten_resept = null;
      input.nextLine();
      System.out.println("For aa legge til en resept maa du ogsaa velge hvilken lege som skal utskrive den.");
      System.out.println("Oensker du aa se listen av leger?");
      System.out.println("Svar ja eller nei.");
      String svar = input.nextLine();
      while ((!(svar.equals("ja"))) && (!(svar.equals("nei")))){
        System.out.println("Ugyldig input.");
        System.out.println("Svar ja eller nei.");
        svar = input.nextLine();
      }
      if(svar.equals("ja")){
        for (Lege l: leger_list){
          System.out.println(l);
            }
          }

        System.out.println("\nHvilken lege vil du bruke? ");
        System.out.println("Angi navnet på legen på formen (Dr. Navn Mellomnavn Etternavn)");
        String lege_navn = input.nextLine();
        legen_resept = this.velgeLege(lege_navn);
        while (legen_resept == null){
        System.out.println("Det finnes ikke noen lege som heter " + lege_navn);
        System.out.println("Angi leges navn paa nytt:");
        lege_navn = input.nextLine();
        legen_resept = this.velgeLege(lege_navn);
        }

      System.out.println("\nDu trenger ogsaa aa velge en legemiddel.");
      System.out.println("Oensker du aa se listen av legemidler?");
      System.out.println("Svar ja eller nei.");
      String svar_legemiddel = input.nextLine();
      while ((!(svar_legemiddel.equals("ja"))) && (!(svar_legemiddel.equals("nei")))){
        System.out.println("Ugyldig input.");
        System.out.println("Angi ja eller nei.");
        svar_legemiddel = input.nextLine();
      }

      if(svar_legemiddel.equals("ja")){
        for (Legemiddel lg: legemidler_list){
          System.out.println(lg);
            }
          }

        System.out.println("\nHvilken legemiddel vil du bruke? ");
        System.out.println("Angi legemiddel sin ID");
        int legemiddel_ID = input.nextInt();
        legemidlen_resept = this.velgeLegemiddel(legemiddel_ID);
        while (legemidlen_resept == null){
            System.out.println("Det finnes ikke noen legemiddel med denne IDen.");
            System.out.println("Angi legemiddel sin ID paa nytt:");
            legemiddel_ID = input.nextInt();
            legemidlen_resept = this.velgeLegemiddel(legemiddel_ID);
          }

      input.nextLine(); //need to change line here.
      System.out.println("\nDu trenger ogsaa aa velge en pasient.");
      System.out.println("Oensker du aa se listen av pasienter?");
      System.out.println("Svar ja eller nei.");
      String svar_pasient = input.nextLine();
      while ((!(svar_pasient.equals("ja"))) && (!(svar_pasient.equals("nei")))){
        System.out.println("Ugyldig input.");
        System.out.println("Angi ja eller nei.");
        svar_pasient = input.nextLine();
      }

      if(svar_pasient.equals("ja")){
        for (Pasient p: pasient_list){
          System.out.println(p);
            }
          }

        System.out.println("Hvilken pasient vil du bruke? ");
        System.out.println("Angi pasient sin ID");
        int pasient_ID = input.nextInt();
        pasienten_resept = this.velgePasient(pasient_ID);
        while (pasienten_resept == null){
            System.out.println("Det finnes ikke noen pasient som heter " + pasient_ID);
            System.out.println("Angi pasient sin ID paa nytt:");
            pasient_ID = input.nextInt();
            pasienten_resept = this.velgePasient(pasient_ID);
          }

      if ((legen_resept == null) || (legemidlen_resept == null) || (pasienten_resept == null)){
        System.out.println("Noe gikk galt.");
        System.out.println("Gaa tilbakke til legge til menyen og start paa nytt.");
        break;
      }


      Resept r = null;
      System.out.println(" ");
      int type = this.VelgeTypeResept();

      if (type != 2){
        int reit;
        System.out.println("Angi hvor mange reit resepten har.");
        reit = input.nextInt();
        r = this.skrivResept(legen_resept, legemidlen_resept, pasienten_resept, reit, type);
      }
      else{
        r = legen_resept.skrivPResept(legemidlen_resept,pasienten_resept);
      }
      pasienten_resept.LeggTilResept(r);
      System.out.println("Resepten er lagt i systemet.");

      break;

      case 4:
      Legemiddel legemiddel = this.LeggTilLegemiddel();
      System.out.println(" ");
      break;

      case 5:
      System.out.println("Gaar tilbake til hovedmenyen.");
      this.Hovedmeny();
         break;

    default :
             System.out.println("Det var ikke gyldig input. Angi et tall fra 1 til 5.");
             break;
      }
    }
  }


  public void BrukeResepter(){
    try{
    Scanner input = new Scanner(System.in);
    System.out.println("Hvilken pasient vil du se resepter for? \n");
    for (Pasient p : pasient_list){
      System.out.println(p);
    }

    int valg = input.nextInt();

    while (valg >= pasient_list.size()){
      System.out.println("Ugyldig input.");
      System.out.println("Angi pasienten sin ID paa nytt.");
      valg = input.nextInt();
    }

    Pasient p_valgt = null;
    for (Pasient p: pasient_list){
      if (p.hentID() == valg){
        p_valgt = p;
      }
    }

    System.out.println("Valgt pasient: " + p_valgt);
    System.out.println("Hvilken resept vil du bruke?");
    p_valgt.skrivUt();
    valg = input.nextInt();
    Resept resept_to_be_used = p_valgt.hent(valg);
    p_valgt.brukResept(resept_to_be_used);
    System.out.println("Ferdig.");
    }

    catch(InputMismatchException e){
      System.out.println(e);
    }
  }

  public void SkrivUtStatistikk() throws UlovligUtskrift{
    try{
    int valg;
    boolean loekke = true;

    while (true){
    System.out.println("\n** Statistikk meny: **");
    System.out.println("Se statistikk om: ");
    System.out.println("\n1. Totalt antall utskrevne resepter paa vanlige legemidler.");
    System.out.println("2. Totalt antall utskrevne resepter paa vanedannende legemidler.");
    System.out.println("3. Totalt antall utskrevne resepter paa narkotiske legemidler.");
    System.out.print("4. Gaa tilbakke til hovedmenyen.");
    System.out.println("\nAngi et tall mellom 1-4");

    valg = input.nextInt();

    switch(valg){
      case 1:
      System.out.println("\nLeger som har skrevet ut minst en resept paa vanlige legemidler: \n");
      Collections.sort(leger_list);
      for (Lege l : leger_list){
        if (l.Vanlig()){
          System.out.println(l);
          System.out.println("Antall resepter paa vanlige legemidler " + l.AntallVanlig());
        }
      }
      System.out.println("\nPasienter som har minst en gyldig resept paa vanlige legemidler:\n");
      for (Pasient p : pasient_list){
        if (p.Vanlig()){
          System.out.println(p);
          System.out.println(": Antall resepter paa vanlige legemidler " + p.AntallVanlig());
        }
      }
      break;

      case 2:
      System.out.println("\nLeger som har skrevet ut minst en resept paa vanedannende legemidler: \n");
      for (Lege l : leger_list){
        if (l.Vanedannende()){
          System.out.println(l);
          System.out.println("Antall resepter paa vanedannende legemidler " + l.AntallVanedannende());
        }
      }
      System.out.println("\nPasienter som har minst en gyldig resept paa vanedannende legemidler:\n");
      for (Pasient p : pasient_list){
        if (p.Vanedannende()){
          System.out.println(p);
          System.out.println("Antall resepter paa narkostiske legemidler " + p.AntallVanedannende());
        }
      }
      break;

      case 3:
      System.out.println("\nLeger som har skrevet ut minst en resept paa narkotiske legemidler: \n");
      for (Lege l : leger_list){
        if (l.Narkotisk()){
          System.out.println(l);
          System.out.println("Antall resepter paa narkostiske legemidler " + l.AntallNarkotisk());
        }
      }

      System.out.println("\nPasienter som har minst en gyldig resept paa narkotiske legemidler: \n");
      for (Pasient p : pasient_list){
        if (p.Narkotisk()){
          System.out.println(p);
          System.out.println("Antall resepter paa narkostiske legemidler " + p.AntallNarkotisk());
        }
      }
      break;

      case 4:
      System.out.println("Gaar tilbake til hovedmenyen.");
      this.Hovedmeny();
         break;

    default :
             System.out.println("Det var ikke gyldig input. Angi et tall fra 1 til 5.");
             break;
        }
      }
    }
  catch(InputMismatchException e){
    System.out.println(e);
    }
  }

  public Legemiddel LeggTilLegemiddel(){
    Double pris; Double virkestoff; String type; int styrke; Legemiddel legemiddel;
    Scanner input = new Scanner(System.in);
    try{
    System.out.println("Angi navn: ");
    String navn = input.nextLine();

    for (Legemiddel lg : legemidler_list){
      if (lg.hentNavn().equals(navn)){
        System.out.println("Dette legemiddelet finnes allerede i listen.");
        System.out.println("Derfor trenger du ikke aa legge den til paa nytt.");
        legemiddel = lg;
        return legemiddel;
      }
    }

    System.out.println("Angi type: (narkotisk, vanedannende, vanlig): ");
    type = input.nextLine();
    while ((!(type.equals("narkotisk"))) && (!(type.equals("vanedannende"))) && (!(type.equals("vanlig")))){
      System.out.println("Feil input. Typen maa vaere narkotisk, vanedannende eller vanlig");
      System.out.println("Angi type paa nytt.");
      type = input.nextLine();
    }
    System.out.println("Angi pris: ");
    pris = input.nextDouble();
    System.out.println("Angi virkestoff: ");
    virkestoff = input.nextDouble();

    if (type.equals("narkotisk")){
      System.out.println("Angi styrke: ");
      styrke = input.nextInt();
      legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
      legemidler_list.add(legemiddel);
      System.out.println("Legemiddelet er naa lagt i systemet.");
      System.out.println("Gaar tilbake til legge til menyen.");
      return legemiddel;
    }
    else if(type.equals("vanedannende")){
      System.out.println("Angi styrke: ");
      styrke = input.nextInt();
      legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
      legemidler_list.add(legemiddel);
      System.out.println("Legemiddelet er naa lagt i systemet.");
      System.out.println("Gaar tilbake til legge til menyen.");
      return legemiddel;
    }
    else if(type.equals("vanlig")){
      legemiddel = new Vanlig(navn, pris, virkestoff);
      legemidler_list.add(legemiddel);
      System.out.println("Legemiddelet er naa lagt i systemet.");
      System.out.println("Gaar tilbake til legge til menyen.");
      return legemiddel;
    }
  }
    catch(InputMismatchException e){
      System.out.println(e);
    }
    finally{
      return legemiddel = null;
    }
  }

    public Lege LeggTilLege(){
      String legeNavn; int KontrollId; Lege legen;
      Scanner input = new Scanner(System.in);
      try{
      System.out.println("Angi legens navn på formen (Dr. Navn Mellomnavn Etternavn )");
      legeNavn = input.nextLine();
      for (Lege l: leger_list){
        if (l.hentNavn().equals(legeNavn)){
          System.out.println("Det finnes allerede en lege som heter "+ legeNavn);
          System.out.println("Derfor trenger du ikke aa legge den til paa nytt.");
          legen = l;
          return l;
        }
      }
      System.out.println("Er legen Spesialist? (ja/nei)");
      String spesialist_svar = input.nextLine();

      while ((!(spesialist_svar.equals("ja"))) && (!(spesialist_svar.equals("nei")))){
        System.out.println("Ugyldig input.");
        System.out.println("Svar ja eller nei.");
        spesialist_svar = input.nextLine();
      }

      if (spesialist_svar.equals("ja")){
        System.out.println("Angi KontrollId: ");
        KontrollId = input.nextInt();
        legen = new Spesialist(legeNavn, KontrollId);
        leger_list.add(legen);
        System.out.println("\nLegen er naa lagt i systemet.");
        return legen;
      }
      legen = new Lege(legeNavn);
      leger_list.add(legen);
      System.out.println("\nLegen er naa lagt i systemet.");
      return legen;
      }

    catch(InputMismatchException e){
    System.out.println(e);
      }
  finally{
    return legen = null;
    }
  }

    public Pasient LeggTilPasient(){
      Pasient pasient;
      Scanner input = new Scanner(System.in);
      try{
      System.out.println("Angi pasientens navn: ");
      String pasientNavn = input.nextLine();
      System.out.println("Angi pasientens foedselsnummer: ");
      String foedselsnummer = input.nextLine();
      while (foedselsnummer.length() != 11){
        System.out.println("Foedselsnummer maa ha 11 siffrer.");
        System.out.println("Proev aa taste inn foedselsnummer paa nytt.");
        foedselsnummer = input.nextLine();
      }
      for (Pasient p: pasient_list){
        if (p.HentFoedselsnummer().equals(foedselsnummer)){
          System.out.println("Det finnes allerede en pasient som har foelgende foedselsnummer "+foedselsnummer);
          System.out.println("Derfor kan " + pasientNavn + " ikke legges til i systemet.\n");
          pasient = p;
          return pasient;
        }
      }
      pasient = new Pasient(pasientNavn, foedselsnummer);
      pasient_list.add(pasient);
      System.out.println("\nPasienten er naa lagt i systemet.");
      return pasient;
      }
    catch(InputMismatchException e){
      System.out.println(e);
    }
    finally{
      return pasient = null;
    }
  }

  public Lege velgeLege(String legenavn){
    Lege legen;
      for (Lege l: leger_list){
        if (l.hentNavn().equals(legenavn)){
          legen = l;
          System.out.println("\nLegen er valgt.");
          return legen;
          }
        }
        return legen = null;
      }


    public Legemiddel velgeLegemiddel(int legemiddel_ID){
      Legemiddel legemiddel;
        for (Legemiddel lg: legemidler_list){
          if (lg.hentID() == legemiddel_ID){
            legemiddel = lg;
            System.out.println("\nLegemiddelet er valgt.");
            return legemiddel;
            }
          }
          return legemiddel = null;
      }


      public Pasient velgePasient(int pasient_ID){
        Pasient pasient;
          for (Pasient p: pasient_list){
            if (p.hentID() == pasient_ID){
              pasient = p;
              System.out.println("\nPasienten er valgt.");
              return pasient;
              }
            }
            return pasient = null;
        }

    public int VelgeTypeResept(){
      int type;
      Scanner input = new Scanner(System.in);
      System.out.println("Velge hvilken type resept du vil lage: ");
      System.out.println("Du kan velge mellom: ");
      System.out.println("1. hvit");
      System.out.println("2. p");
      System.out.println("3. militaer");
      System.out.println("4. blaa");
      System.out.println("\nAngi et tall mellom 1 - 4.");

      type = input.nextInt();

      while ((type != 1) && (type != 2) && (type != 3) && (type!= 4)){
        System.out.println("Ugyldig input");
        System.out.println("Angi et tall mellom 1 - 4.");
        type = input.nextInt();
      }

      return type;
    }

    public Resept skrivResept(Lege l, Legemiddel lg, Pasient p, int reit, int type) throws UlovligUtskrift{
      Resept r;
    if (!(l instanceof Spesialist) && (lg instanceof Narkotisk)){
      throw new UlovligUtskrift(l, lg);
    }
      if (type == 1){
      r = l.skrivHvitResept(lg, p, reit);
      return r;
    }
    else if (type == 4){
      r = l.skrivBlaaResept(lg, p, reit);
      return r;
    }
    else if (type == 2){
      r = l.skrivPResept(lg, p);
      return r;
    }
    else if (type == 3){
      r = l.skrivMillitaerResept(lg, p, reit);
      return r;
    }
    else{System.out.println("Type resept er ugyldig.");
        return r = null;}
    }

    public void SkrivDataTilFil(){

      String filename = "utfil.txt";

      PrintWriter pw = null;
      try {
        pw = new PrintWriter(filename);

      } catch (FileNotFoundException e) {
        System.err.println(e.getMessage());
      }


      pw.println("# Pasienter (navn, fnr)");
      for (Pasient p : pasient_list){
        pw.println(p.hentNavn() + ", " + p.HentFoedselsnummer());
      }

      pw.println("# Legemidler (navn, type, pris, virkestoff, [styrke])");
      for (Legemiddel l : legemidler_list){
        if (l instanceof Vanlig){
          pw.println(l.hentNavn() + "," + "vanlig" + "," + l.hentPris() + "," + l.hentVirkestoff());}
        else if (l instanceof Vanedannende){
          pw.println(l.hentNavn() + "," + "vanedannende" + "," + l.hentPris() + "," + l.hentVirkestoff() + ","
              + l.HentStyrke());
        }else{
          pw.println(l.hentNavn() + "," + "narkotisk" + "," + l.hentPris() + "," + l.hentVirkestoff() + ","
              + l.HentStyrke());}
      }

      pw.println("# Leger (navn, kontrollid / 0 hvis vanlig lege)");
      for (Lege l : leger_list){
        pw.println(l.hentNavn() + "," + l.hentKontrollId());
      }

      pw.println("# Resepter (legemiddelNummer, legeNavn, pasientID, type, [reit])");
      for (Lege l : leger_list){
        Lenkeliste<Resept> reseptListe = l.hentReseptliste();
        for (Resept r: reseptListe){
          if (r instanceof Presept){
            pw.println(r.hentLegemiddel()+ "," + r.hentLege() + "," + r.hentPasientID() + "," + "p" );
          }
        else{
          pw.println(r.hentLegemiddel()+ "," + r.hentLege() + "," + r.hentPasientID() + "," + r.hentType()+ "," + r.hentReit());
        }
      }
    }
    pw.close();
    System.out.println("Alle data er naa skrevet i " + filename);
    System.out.println("Gaar tilbake til hovedmenyen.");
  }
}
