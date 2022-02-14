class TestResepter{

  public static void main(String[] args){
    Narkotisk nark = new Narkotisk("narkotisk", 129.5, 67.9, 6);
    Vanedannende vane = new Vanedannende("vanedannende", 99.9, 79.5, 4);
    Lege lege1 = new Lege("Aristoteles");

    Hvit hvit1 = new Hvit(nark, lege1, 12, 2);
    System.out.println("Tester en hvit resept: ");
    doubleTest(hvit1.prisAabetale(), 129.5);
    strTest(hvit1.hentLegemiddel(), "narkotisk");
    strTest(hvit1.hentLege(), "Aristoteles");
    intTest(hvit1.hentReit(), 2);
    intTest(hvit1.hentPasientID(), 12);
    strTest(hvit1.farge(), "Hvit");
    System.out.println(hvit1.bruk());
    System.out.println(hvit1.bruk());
    System.out.println(hvit1.bruk()); //Burde naa vaere false

    Presept pres1 = new Presept(nark, lege1, 9);
    System.out.println("\nTester en presept: ");
    doubleTest(pres1.prisAabetale(), (129.5-108));
    strTest(pres1.hentLegemiddel(), "narkotisk");
    intTest(pres1.hentReit(), 3);

    Militarresept mili1 = new Militarresept(vane, lege1, 14, 1);
    System.out.println("\nTester en militarresepten: ");
    doubleTest(mili1.prisAabetale(), 0.0);

    Presept pres2 = new Presept(vane, lege1, 19);
    System.out.println("\nTester en ny presept for aa sjekke om prisen (burde vaere grattis) er riktig: ");
    doubleTest(pres2.prisAabetale(), 0.0);
    intTest(pres2.hentReit(), 3);

    Bla bla1 = new Bla(vane, lege1, 2, 4);
    System.out.println("\nTester en blaa resept: ");
    doubleTest(bla1.prisAabetale(), (99.9*0.25));
    strTest(bla1.hentLegemiddel(), "vanedannende");
    strTest(bla1.hentLege(), "Aristoteles");
    intTest(bla1.hentReit(), 4);
    intTest(bla1.hentPasientID(), 2);
    strTest(bla1.farge(), "Bla");
}
public static boolean intTest(int faktiskResultat, int forventetResultat) {
      if (faktiskResultat == forventetResultat) {
          System.out.println("Riktig ");
      } else {
          System.out.println("Feil ");
      }
      return faktiskResultat == forventetResultat;
  }

  public static boolean strTest(String faktiskResultat, String forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig ");
        } else {
            System.out.println("Feil ");
        }
        return faktiskResultat == forventetResultat;
    }

    public static boolean doubleTest(Double faktiskResultat, Double forventetResultat) {
          if (faktiskResultat.intValue() == forventetResultat.intValue()) {
              System.out.println("Riktig ");
          } else {
              System.out.println("Feil ");
          }
          return faktiskResultat.intValue() == forventetResultat.intValue();
      }
}
