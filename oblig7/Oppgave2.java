import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.event.*;


public class Oppgave2 extends Application {

    TextField f1, f2;
    Text t;

    public void start(Stage teater){

        Font font = new Font(30);

        f1 = new TextField("Tall 1");
        f1.setFont(font);
        f2 = new TextField("Tall 2");
        f2.setFont(font);
        HBox linje1 = new HBox();
        linje1.getChildren().addAll(f1, f2);

        AritmetikkBehandler a = new AritmetikkBehandler();
        Button b1 = new Button("+");
        Button b2 = new Button("-");
        Button b3 = new Button("/");
        Button b4 = new Button("*");
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);
        b4.setFont(font);

        b1.setOnAction(a);
        b2.setOnAction(a);
        b3.setOnAction(a);
        b4.setOnAction(a);

        HBox linje2 = new HBox();
        linje2.getChildren().addAll(b1, b2, b3, b4);

        t = new Text("Resultat");
        t.setFont(font);

        VBox rootPane = new VBox();
        rootPane.getChildren().addAll(linje1, linje2, t);

        Scene scene = new Scene(rootPane);
        teater.setScene(scene);
        teater.show();
    }


    class AritmetikkBehandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){

            int tall1;
            int tall2;

            try {
                tall1 = Integer.parseInt(f1.getText());
                tall2 = Integer.parseInt(f2.getText());
            }
            catch(NumberFormatException f){
                t.setText("Ugyldig input!");
                return;
            }

            Button knapp = (Button) e.getSource();
            String tegn = knapp.getText();

            int svar = 0;

            switch(tegn){
                case "+":
                    svar = tall1 + tall2;
                    break;
                case "-":
                    svar = tall1 - tall2;
                    break;
                case "/":
                    svar = tall1 / tall2;
                    break;
                case "*":
                    svar = tall1 * tall2;
                    break;
            }

            t.setText(svar+"");
        }
    }
}
