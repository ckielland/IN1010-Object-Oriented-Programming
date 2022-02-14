import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.stage.FileChooser;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


import java.io.File;
import java.util.Scanner;

public class Hovedprogram extends Application{
  private Text statusinfo;
  private Rute labyrint[][];
  private int rader; int kolonner;
  private String path = "";
  private ArrayList<String> utveier;
  private GridPane gridpane;
  private boolean[][] tabell;

  @Override
  public void start(Stage teater){

    gridpane = new GridPane();

    File file = new FileChooser().showOpenDialog(teater);
    try{
      Scanner input = new Scanner(file);
      rader = input.nextInt(); kolonner = input.nextInt();
      labyrint = new Rute[kolonner][rader];

      input.nextLine();
      int radCounter = 0;
      while(input.hasNextLine()){
        String line = input.nextLine();
      for (int i = 0; i < line.length(); i ++){
        if(line.charAt(i) == '#'){
          SortRute sort = new SortRute(i,radCounter);
          labyrint[i][radCounter] = sort;
          gridpane.add(sort, i, radCounter);
        }
        else if (line.charAt(i) == '.'){
          if ((i == 0) || (i == line.length() -1)){
            Apning apning = new Apning(i,radCounter);
            labyrint[i][radCounter] = apning;
            gridpane.add(apning, i, radCounter);
          }
        else if ((radCounter == 0) || (radCounter == rader - 1)){
          Apning apning = new Apning(i,radCounter);
          labyrint[i][radCounter] = apning;
          gridpane.add(apning, i, radCounter);
        }
          else{HviteRute hvit = new HviteRute(i,radCounter);
          labyrint[i][radCounter] = hvit;
          gridpane.add(hvit, i, radCounter);}
        }
      }
      radCounter ++;
    }

    } catch (Exception e){}

      for (int i = 0; i < labyrint.length; i ++){
        for (int j = 0; j < labyrint[0].length; j++){
          finnNabo(i,j);
        }
      }

      for (int i = 0; i < labyrint.length; i ++){
        for (int j = 0; j < labyrint[0].length; j++){
          labyrint[i][j].settBeholder(this);
        }
      }

      Klikkbehandler klikk = new Klikkbehandler();
      for (int i = 0; i < labyrint.length; i ++){
        for (int j = 0; j < labyrint[0].length; j++){
          labyrint[i][j].setOnAction(klikk);
        }
      }

  statusinfo = new Text("Velg startruten");
  statusinfo.setFont(new Font(14));
  statusinfo.setX(10);  statusinfo.setY(60);

  Button nullstill = new Button("Nullstille");
  nullstill.setLayoutX(10);  nullstill.setLayoutY(10);
  Nullstillbehandler nullstille = new Nullstillbehandler();
  nullstill.setOnAction(nullstille);

  Button stoppknapp = new Button("Avslutte");
  stoppknapp.setLayoutX(80);  stoppknapp.setLayoutY(10);
  Stoppbehandler stopp = new Stoppbehandler();
  stoppknapp.setOnAction(stopp);

  gridpane.setGridLinesVisible(true);
	gridpane.setLayoutX(10);  gridpane.setLayoutY(70);
  gridpane.setPrefSize(rader * 25, kolonner * 25);
  gridpane.setMaxSize(850*0.95, 630 *0.95);
  gridpane.setMinSize(300*0.95, 300*0.95);

	Pane kulisser = new Pane();
	kulisser.setPrefSize(rader * 30, kolonner * 30);
  kulisser.setMaxSize(850, 630);
  kulisser.setMinSize(300,300);
	kulisser.getChildren().add(gridpane);
	kulisser.getChildren().add(statusinfo);
	kulisser.getChildren().add(stoppknapp);
  kulisser.getChildren().add(nullstill);

	Scene scene = new Scene(kulisser);

  teater.setTitle("Labyrinth");
  teater.setScene(scene);
  teater.show();
  }

  class Stoppbehandler implements EventHandler<ActionEvent> {
  @Override
  public void handle(ActionEvent e) {
    Platform.exit();
     }
   }

      class Klikkbehandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {

      ArrayList<String> utveier =  finnUtveiFra((Rute)e.getSource());
      if (utveier.size() != 0) {
        if (utveier.size() == 1){
          statusinfo.setText("Det finnes en utvei.");
        }
        else{
        statusinfo.setText("Totalt finnes det " + utveier.size() + " utveier. Vises bare den foerste.");
      }

        tabell = losningStringTilTabell(utveier.get(0), kolonner, rader);
        for (int i = 0; i < tabell.length; i ++){
          for (int j = 0; j < tabell[0].length; j++){
            if (tabell[i][j] == true){
              labyrint[j][i].setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
            }
          }
        }

      } else {
          statusinfo.setText("Ingen utveier.");
          }
        }
      }

      class Nullstillbehandler implements EventHandler<ActionEvent> {
      @Override
      public void handle(ActionEvent e) {
          utveier.clear();
          for (int i = 0; i < tabell.length; i ++){
            for (int j = 0; j < tabell[0].length; j++){
              if (tabell[i][j] == true){
                labyrint[j][i].setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, new Insets(400))));
              }
            }
          }
          statusinfo.setText("Velg startruten.");
         }
       }


  public static void main(String[] args) {
    Application.launch(args);
    }

        public void finnNabo(int kolonne, int rad){
          if (rad == 0){
            labyrint[kolonne][rad].settNord(null);
            labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
            if (kolonne == 0){
              labyrint[kolonne][rad].settOest(null);
              labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            }
            else if (kolonne == kolonner -1){
              labyrint[kolonne][rad].settVest(null);
              labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
            }
            else{
              labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
              labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            }
          }
          else if(rad == rader - 1){
            labyrint[kolonne][rad].settSyd(null);
            labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
            if (kolonne == 0){
              labyrint[kolonne][rad].settOest(null);
              labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            }
            else if (kolonne == kolonner -1){
              labyrint[kolonne][rad].settVest(null);
              labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
            }
            else{
              labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
              labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            }
          }
          if ((kolonne == 0) && (rad != 0) && (rad!= rader - 1)){
            labyrint[kolonne][rad].settOest(null);
            labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
            labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
          }
          else if((kolonne == kolonner - 1) && (rad != 0) && (rad!= rader - 1)){
            labyrint[kolonne][rad].settVest(null);
            labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
            labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
            labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
          }
          else if((kolonne != 0) && (rad != 0) && (rad!= rader - 1) && (kolonne != kolonner -1)){
            labyrint[kolonne][rad].settVest(labyrint[kolonne+1][rad]);
            labyrint[kolonne][rad].settOest(labyrint[kolonne-1][rad]);
            labyrint[kolonne][rad].settSyd(labyrint[kolonne][rad+1]);
            labyrint[kolonne][rad].settNord(labyrint[kolonne][rad-1]);
          }
        }


public void leggTilUtvei(String nypath){
  utveier.add(nypath);
  }


public ArrayList<String> finnUtveiFra(Rute r){
  utveier = new ArrayList<String>();
  r.finnUtvei();
  return utveier;
  }

static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));
        losning[y][x] = true;
    }
    return losning;
  }

}
