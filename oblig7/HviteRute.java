import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

class HviteRute extends Rute{

  public HviteRute(int kolonne, int rad){
    super(kolonne, rad);
    setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, new Insets(400))));
  }

  public String charTilTegn(){
    tegn =".";
    return tegn;
  }
}
