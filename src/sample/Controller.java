package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    /**♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
     * @Authors:    Arbi Saidov        (Backend.)
     *              Patrick Watzinger   (Frontend.)
     *              Marko Jezidzic      (Project Manager.)
     * @param primaryStage
     * @throws Exception
    ♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦*/

    @FXML TextField txt_name,txt_shipSize;
    @FXML Label lbl_score,lbl_maxShips;
    @FXML Button btn_ready,btn_surrender;
    @FXML Button btn_h,btn_v;
    @FXML GridPane GP_Feld;
    Button [][] fireldButtons=new Button[Game.FIRELDSIZE][Game.FIRELDSIZE];

    Game zisGame = new Game();
    char hv;
    int playerIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int idCounter=0;
        for (int i = 0; i < Game.FIRELDSIZE; i++) {
            for (int j = 0; j < Game.FIRELDSIZE; j++) {
                fireldButtons[i][j]=new Button("");
                fireldButtons[i][j].setMinWidth(50);
                fireldButtons[i][j].setMinHeight(50);
                fireldButtons[i][j].setStyle("-fx-border-color: black;");
                fireldButtons[i][j].setId(idCounter+"");
                fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToPlace);
                idCounter++;
                this.GP_Feld.add(fireldButtons[i][j],i+3,j);
            }
        }
        playerIndex = 0;
        hv = 'H';
    }


    //Je nachdem, was man wählt, also H oder V, wird H oder V in hv gespeichert.
    //(Braucht man um die Schiffe zu platzieren.)
    public void clickOnHV(ActionEvent av){
        Button input = (Button) av.getSource();
        hv = input.getText().toCharArray()[0];
    }


    //Wenn man Bereit klickt, so werden alle TextFields und Buttons disabled.
    //Der Button Aufgeben soll erscheinen.
    public void clickOnReady(){
        txt_name.setDisable(true);
    }


    //Wenn man sein eigenes Board sieht, kann man seine Schiffe platzieren.
    EventHandler<MouseEvent> clickOnButtonToPlace = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {

        }
    };


    //Wenn man das Board des Gegners sieht, kann man ein Feld angreifen.
    EventHandler<MouseEvent> clickOnButtonToDestroy = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {

        }
    };


    //Zeigt das Board an, welches mitgegeben wurde.
    public void updateView(Board iBoard)
    {
        for (int i = 0; i < Game.FIRELDSIZE; i++)
        {
            for (int j = 0; j < Game.FIRELDSIZE; j++)
            {
                fireldButtons[i][j].setText(String.valueOf(iBoard.getFireld()[i][j]));
            }
        }
    }
}
