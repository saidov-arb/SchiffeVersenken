package sample;

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
    @FXML TextField txt_name,txt_shipSize;
    @FXML Label lbl_score,lbl_maxShips;
    @FXML Button btn_ready,btn_surrender;
    @FXML Button btn_h,btn_v;
    @FXML GridPane GP_Feld;
    Button [][] fireldButtons=new Button[Game.FIRELDSIZE][Game.FIRELDSIZE];

    Game zisGame = new Game();
    char hv;
    int shipSize;
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


    EventHandler<MouseEvent> clickOnButtonToPlace = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {

        }
    };

    EventHandler<MouseEvent> clickOnButtonToDestroy = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {

        }
    };

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
