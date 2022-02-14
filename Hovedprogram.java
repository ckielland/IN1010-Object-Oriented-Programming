class Hovedprogram{
    public static void main(String[] args){
      Narkotisk nark = new Narkotisk("narkotisk", 129.5, 67.9, 6);
      System.out.println(nark);
      System.out.println("");
      Vanedannende vane = new Vanedannende("vanedannende", 299.9, 79.5, 4);
      System.out.println(vane);
      System.out.println("");
      Vanlig vanl = new Vanlig("vanlig", 349.5, 23.6);
      System.out.println(vanl);
      System.out.println("");
      Lege lege1 = new Lege("Aristoteles");
      System.out.println(lege1);
      System.out.println("");
      Hvit hvit1 = new Hvit(nark, lege1, 12, 2);
      System.out.println(hvit1);
      System.out.println("");
      Presept pres1 = new Presept(nark, lege1, 9);
      System.out.println(pres1);
      System.out.println("");
      Militarresept mili1 = new Militarresept(vane, lege1, 14, 1);
      System.out.println(mili1);
      System.out.println("");
      Bla bla1 = new Bla(vane, lege1, 2, 4);
      System.out.println(bla1);
      System.out.println("");
      Spesialist spesialist1 = new Spesialist("Demokretus", 1326);
      System.out.println(spesialist1);
      System.out.println("");
    }

}
