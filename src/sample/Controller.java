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
import javafx.scene.text.Font;

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
    @FXML Button btn_ready;
    @FXML Button btn_h,btn_v;
    @FXML GridPane GP_Feld;
    Button btn_close = new Button("Schließen");
    Button btn_surrender = new Button("Aufgeben");
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
                fireldButtons[i][j].setFont(Font.font("Verdana", 20));
                fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToPlace);
                idCounter++;
                this.GP_Feld.add(fireldButtons[i][j],i+3,j);
            }
        }
        playerIndex = 0;
        hv = 'H';
        zisGame.getGamers()[0] = new Player("");
        zisGame.getGamers()[1] = Player.botPlayer();
        lbl_maxShips.setText("Schiffe: "+zisGame.getGamers()[playerIndex].getBoard().getPlaceableShips());
        lbl_score.setText("Punkte: "+zisGame.getGamers()[playerIndex].getScore());
        btn_surrender.setOnAction(this::clickOnSurrender);
        btn_close.setOnAction(this::clickOnClose);
        btn_close.setMinWidth(100);
        btn_close.setMinHeight(50);
        GP_Feld.add(btn_close,0,7,2,1);
    }


    //Je nachdem, was man wählt, also H oder V, wird H oder V in hv gespeichert.
    //(Braucht man um die Schiffe zu platzieren.)
    public void clickOnHV(ActionEvent av){
        Button input = (Button) av.getSource();
        hv = input.getText().toCharArray()[0];
    }

    //Wenn man neustarten möchte.
    public void clickOnClose(ActionEvent av){
        System.exit(0);
    }

    //Wenn man aufgibt, gewinnt der, dessen Board man gerade angreift.
    //!!Bot gibt nie auf!!
    public void clickOnSurrender(ActionEvent av)
    {
        zisGame.getGamers()[playerIndex].setScore(Game.MAX_SHIPS);
        if (zisGame.checkForWinner() == 0)
        {
            System.out.println(zisGame.getGamers()[0].getName() + " hat gewonnen.");
            lbl_maxShips.setText(zisGame.getGamers()[0].getName()+" gewinnt.");
        }else if (zisGame.checkForWinner() == 1)
        {
            System.out.println(zisGame.getGamers()[1].getName() + " hat gewonnen.");
            lbl_maxShips.setText(zisGame.getGamers()[1].getName()+" gewinnt.");
        }
        else
        {
            btn_ready.setDisable(false);
        }
        btn_ready.setDisable(true);
        btn_surrender.setDisable(true);
        for (int i = 0; i < Game.FIRELDSIZE; i++)
        {
            for (int j = 0; j < Game.FIRELDSIZE; j++)
            {
                fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToDestroy);
            }
        }
    }

    //Wenn man Bereit klickt, so werden alle TextFields und Buttons disabled.
    //Der Button Aufgeben soll erscheinen.
    public void clickOnReady(){
        if (zisGame.getGamers()[playerIndex].getBoard().getPlaceableShips() == 0)
        {
            zisGame.getGamers()[playerIndex].setName(txt_name.getText());
            txt_name.setDisable(true);
            txt_shipSize.setDisable(true);
            btn_h.setDisable(true);
            btn_v.setDisable(true);
            btn_ready.setOnAction(this::clickOnNext);
            btn_ready.setText("Weiter");
            btn_ready.setDisable(true);
            GP_Feld.getChildren().removeAll(btn_h,btn_v);
            btn_surrender.setMinWidth(100);
            btn_surrender.setMinHeight(50);
            GP_Feld.add(btn_surrender,0,6,2,1);
            for (int i = 0; i < Game.FIRELDSIZE; i++)
            {
                for (int j = 0; j < Game.FIRELDSIZE; j++)
                {
                    fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToPlace);
                    fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
                }
            }
            nextPlayer();
            updateView(zisGame.getGamers()[playerIndex].getBoard());
            txt_name.setText(zisGame.getGamers()[playerIndex].getName());
            txt_shipSize.setText(zisGame.getGamers()[0].getName()+"'s zug");
            lbl_maxShips.setText("");
        }
    }

    //Wenn auf den Button Next geklickt wird, Werden die Funktionen von den Buttons genommen.
    //Nächster Spieler wird geladen, View aktualisiert, Bot greift an.
    //Es wird auch nach Gewinner gesucht.
    public void clickOnNext(ActionEvent actionEvent)
    {
        for (int i = 0; i < Game.FIRELDSIZE; i++)
        {
            for (int j = 0; j < Game.FIRELDSIZE; j++)
            {
                fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
            }
        }
        nextPlayer();
        updateView(zisGame.getGamers()[playerIndex].getBoard());
        btn_ready.setDisable(true);
        btn_surrender.setDisable(false);
        txt_name.setText(zisGame.getGamers()[playerIndex].getName());
        if (playerIndex == 0)
        {
            for (int i = 0; i < Game.FIRELDSIZE; i++)
            {
                for (int j = 0; j < Game.FIRELDSIZE; j++)
                {
                    fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
                }
            }
            zisGame.attackField(playerIndex);
            updateView(zisGame.getGamers()[playerIndex].getBoard());
            if (zisGame.checkForWinner() == 0)
            {
                System.out.println(zisGame.getGamers()[0].getName() + " hat gewonnen.");
                btn_ready.setDisable(true);
                btn_surrender.setDisable(true);
                lbl_maxShips.setText(zisGame.getGamers()[0].getName()+" gewinnt.");
            }else if (zisGame.checkForWinner() == 1)
            {
                System.out.println(zisGame.getGamers()[1].getName() + " hat gewonnen.");
                btn_ready.setDisable(true);
                btn_surrender.setDisable(true);
                lbl_maxShips.setText(zisGame.getGamers()[1].getName()+" gewinnt.");
            }
            else
            {
                btn_ready.setDisable(false);
            }
            btn_surrender.setDisable(true);
        }
        if (playerIndex == 0)
        {
            txt_shipSize.setText(zisGame.getGamers()[1].getName()+"'s zug");
        }else if (playerIndex == 1)
        {
            txt_shipSize.setText(zisGame.getGamers()[0].getName()+"'s zug");
        }
    }

    //Wenn man sein eigenes Board sieht, kann man seine Schiffe platzieren.
    EventHandler<MouseEvent> clickOnButtonToPlace = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            Button input = (Button) mouseEvent.getSource();
            if(Integer.parseInt(txt_shipSize.getText())<0){
                try {
                    txt_shipSize.setText(String.valueOf(Math.abs(Integer.parseInt(txt_shipSize.getText()))));
                }catch (NumberFormatException e) {
                    System.out.println("Sie beser geben ein eine Zahl.");
                }
            }
            for (int i = 0; i < Game.FIRELDSIZE; i++)
            {
                for (int j = 0; j < Game.FIRELDSIZE; j++)
                {
                    try
                    {
                        if (fireldButtons[i][j] == input)
                        {
                            zisGame.getGamers()[playerIndex].getBoard().placeShip(i, j, Integer.parseInt(txt_shipSize.getText()), hv);
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Sie beser geben ein eine Zahl.");
                    }
                }
            }
            updateView(zisGame.getGamers()[playerIndex].getBoard());
        }
    };


    //Wenn man das Board des Gegners sieht, kann man ein Feld angreifen.
    EventHandler<MouseEvent> clickOnButtonToDestroy = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            Button input = (Button) mouseEvent.getSource();
            boolean geschossen = false;
            if (!geschossen)
            {
                for (int i = 0; i < Game.FIRELDSIZE; i++)
                {
                    for (int j = 0; j < Game.FIRELDSIZE; j++)
                    {
                        if (fireldButtons[i][j] == input)
                        {
                            if (zisGame.getGamers()[playerIndex].getBoard().getFireld()[j][i] != 501 && zisGame.getGamers()[playerIndex].getBoard().getFireld()[j][i] != 404)
                            {
                                zisGame.attackField(playerIndex, i, j);
                                geschossen = true;
                            }
                        }
                    }
                }
            }
            if(geschossen){
                updateView(zisGame.getGamers()[playerIndex].getBoard());
                if (zisGame.checkForWinner() == 0)
                {
                    System.out.println(zisGame.getGamers()[0].getName() + " hat gewonnen.");
                    btn_ready.setDisable(true);
                    btn_surrender.setDisable(true);
                    lbl_maxShips.setText(zisGame.getGamers()[0].getName()+" gewinnt.");
                }else if (zisGame.checkForWinner() == 1)
                {
                    System.out.println(zisGame.getGamers()[1].getName() + " hat gewonnen.");
                    btn_ready.setDisable(true);
                    btn_surrender.setDisable(true);
                    lbl_maxShips.setText(zisGame.getGamers()[1].getName()+" gewinnt.");
                }
                else
                {
                    btn_ready.setDisable(false);
                }
                for (int i = 0; i < Game.FIRELDSIZE; i++)
                {
                    for (int j = 0; j < Game.FIRELDSIZE; j++)
                    {
                        fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
                    }
                }
            }
            if (playerIndex == 0)
            {
                txt_shipSize.setText(zisGame.getGamers()[1].getName()+"'s zug");
            }else if (playerIndex == 1)
            {
                txt_shipSize.setText(zisGame.getGamers()[0].getName()+"'s zug");
            }
        }


    };


    //Zeigt das Board an, welches mitgegeben wurde.
    public void updateView(Board iBoard)
    {
        for (int i = 0; i < Game.FIRELDSIZE; i++)
        {
            for (int j = 0; j < Game.FIRELDSIZE; j++)
            {
                fireldButtons[i][j].setText("");
            }
        }
        if (playerIndex == 0)
        {
            for (int i = 0; i < Game.FIRELDSIZE; i++)
            {
                for (int j = 0; j < Game.FIRELDSIZE; j++)
                {
                    if (iBoard.getFireld()[j][i] == 1)
                    {
                        fireldButtons[i][j].setText("ʘ");
                    }
                    else if (iBoard.getFireld()[j][i] == 404 ){
                        fireldButtons[i][j].setText("֍");
                    }
                    else if (iBoard.getFireld()[j][i] == 501){
                        fireldButtons[i][j].setText("⃠");
                    }
                }
            }
        }else if (playerIndex == 1){
            for (int i = 0; i < Game.FIRELDSIZE; i++)
            {
                for (int j = 0; j < Game.FIRELDSIZE; j++)
                {
                    if (iBoard.getFireld()[j][i] == 404 ){
                        fireldButtons[i][j].setText("֍");
                    }
                    else if (iBoard.getFireld()[j][i] == 501){
                        fireldButtons[i][j].setText("⃠");
                    }
                }
            }
        }
        lbl_score.setText("Punkte: "+zisGame.getGamers()[playerIndex].getScore());
        if (zisGame.getGamers()[0].getBoard().getPlaceableShips() > 0)
        {
            lbl_maxShips.setText("Schiffe: " + zisGame.getGamers()[playerIndex].getBoard().getPlaceableShips());
        }else
        {
            lbl_maxShips.setText("");
        }
    }
    //Wechselt zum nächsten Spieler. Eine große Hilfe, weil man nicht mehr
    //überlegen muss, wann man den playerIndex zurücksetzt.
    void nextPlayer()
    {
        playerIndex++;
        if (playerIndex > 1)
        {
            playerIndex = 0;
        }
    }
}
