import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class Dataklynge{
  private String filnavn;
  private int AntNoderPerRack;
  private int AntNoder;
  private int MinnePerNode;
  private int AntProsPerNode;
  private ArrayList<Rack> racks = new ArrayList <Rack> (); //Array to place the Rack objects. Array - we know how many racks we will place.

  public Dataklynge(String filnavn_konstr){
  filnavn = filnavn_konstr;

  Scanner lesfil = null;
  //try catch to handle a possible checked exception.
  try{
    lesfil = new Scanner(new File(filnavn));
  }
  catch(Exception e){
            System.out.println("Er du sikker at filnavnet var riktig?");
            System.out.println(e);
        }
  AntNoderPerRack = lesfil.nextInt();

  lesfil.nextLine();
  while (lesfil.hasNextLine()){
    String line = lesfil.nextLine();
    String[] biter = line.split(" ");
    AntNoder = Integer.parseInt(biter[0]);
    MinnePerNode = Integer.parseInt(biter[1]);
    AntProsPerNode = Integer.parseInt(biter[2]);
    for (int i = 0; i < AntNoder; i++){
      Node node = new Node(MinnePerNode,AntProsPerNode);
      this.settInnNode(node); //Calling the settInnNode method to place each of the created nodes into racks
    }
  }
}
  public void settInnNode(Node node){
    boolean satt = false;
    if (racks.isEmpty()) {
      Rack rack = new Rack();
      racks.add(rack);
    }
    for ( Rack rack : racks) {
    if (rack.getAntNoder() < AntNoderPerRack && satt == false){
      rack.settInn(node);
      satt = true;
    }
  }
  if (satt == false){
  Rack rack2 = new Rack();
  rack2.settInn(node);
  racks.add(rack2);
}}

  public int antProsessorer(){
    int sum = 0;
    for (Rack rack : racks)
    sum = sum + rack.antProssesorer();
    return sum;
  }
  public int noderMedNokMinne(int paakrevdMinne){
    int noder_mednok = 0;
    for (Rack rack : racks)
    noder_mednok = noder_mednok + rack.noderMedNokMinne(paakrevdMinne);
    return noder_mednok;
  }
  public int antRacks(){
    return racks.size();
  }
}
