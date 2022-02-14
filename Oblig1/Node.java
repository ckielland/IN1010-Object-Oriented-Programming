class Node{
  private int minne;
  private int antPros;

  public Node(int minne_konst, int antPros_konst){
    minne = minne_konst;
    antPros = antPros_konst;
  }

  public int antPros(){
    return antPros;
  }

  public boolean nokMinne(int paakrevdMinne){
    if (paakrevdMinne <= minne){
      return true;
    }
    else {return false;}
  }
}
