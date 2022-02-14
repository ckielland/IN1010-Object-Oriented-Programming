import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

class SortRute extends Rute{

  public SortRute(int kolonne, int rad){
    super(kolonne, rad);
    setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
  }



  @Override
  public void gaa(Rute previous, String path){
    return;
  }

  public String charTilTegn(){
    tegn ="#";
    return tegn;
  }
}
