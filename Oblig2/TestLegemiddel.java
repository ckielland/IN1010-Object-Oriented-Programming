class TestLegemiddel{
  public static void main(String[] args){
    Narkotisk nark = new Narkotisk("narkotisk", 129.0, 67.9, 6);
    Vanedannende vane = new Vanedannende("vanedannende", 299.9, 79.5, 4);
    Vanlig vanl = new Vanlig("vanlig", 349.5, 23.6);

    System.out.println("Tester den narkotiske legemidlen:");
    strTest(nark.hentNavn(), "narkotisk");
    doubleTest(nark.hentPris(), 129.0);
    doubleTest(nark.hentVirkestoff(), 67.9);
    intTest(nark.HentNarkotiskStyrke(), 6);

    System.out.println("\nTester den vanedannende legemidlen:");
    strTest(vane.hentNavn(), "vanedannende");
    doubleTest(vane.hentPris(), 299.9);
    doubleTest(vane.hentVirkestoff(), 79.5);
    intTest(vane.HentVaneDannendeStyrke(), 4);

    System.out.println("\nTester den vanlige legemidlen:");
    strTest(vanl.hentNavn(), "vanlig");
    doubleTest(vanl.hentPris(), 349.5);
    doubleTest(vanl.hentVirkestoff(), 23.6);
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
        //Konvertering av double til int for aa kunne bruke == operatoren
            if (faktiskResultat.intValue() == forventetResultat.intValue()) {
                System.out.println("Riktig ");
            } else {
                System.out.println("Feil ");
            }
            return faktiskResultat.intValue() == forventetResultat.intValue();
        }
}
