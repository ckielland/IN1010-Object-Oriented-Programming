import java.util.ArrayList;

class Rack{
  //Create a new ArrayList to place the node objects.
  //ArrayList and not Array because we don't know how many spots we need.
  private ArrayList<Node> noder = new ArrayList<Node>();

  public void settInn(Node node){
    noder.add(node);
  }

  public int getAntNoder(){
    return noder.size();
  }

  public int antProssesorer(){
    int sum = 0;
    for (Node node : noder)
    sum = sum + node.antPros();
    return sum;
  }

  public int noderMedNokMinne(int paakrevdMinne){
    int noder_mednok = 0;
    for (Node node : noder)
    if (node.nokMinne(paakrevdMinne)){
      noder_mednok = noder_mednok + 1;
    }
    return noder_mednok;
  }
}
