package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML Label lbl_maxShips;
    @FXML Button btn_ready,btn_h,btn_v;
    @FXML GridPane GP_Feld;
    Button btn_close = new Button("Schließen"), btn_surrender = new Button("Aufgeben"), btn_next = new Button("Next");
    Label lbl_status = new Label("I bin a Status."), lbl_score0 = new Label(),lbl_score1 = new Label();
    Button [][] fireldButtons=new Button[Game.FIRELDSIZE][Game.FIRELDSIZE];

    Game zisGame = new Game();
    char hv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int idCounter=0;
        for (int i = 0; i < Game.FIRELDSIZE; i++) {
            for (int j = 0; j < Game.FIRELDSIZE; j++) {
                fireldButtons[i][j]=new Button("");
                fireldButtons[i][j].setMinWidth(50);
                fireldButtons[i][j].setMinHeight(50);
                fireldButtons[i][j].setStyle("-fx-background-color: white; -fx-border-color: black;");
                fireldButtons[i][j].setId(idCounter+"");
                fireldButtons[i][j].setFont(Font.font("Verdana", 18));
                fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToPlace);
                idCounter++;
                this.GP_Feld.add(fireldButtons[i][j],i+3,j);
            }
        }
        zisGame.getGamers()[1] = Player.botPlayer();
        zisGame.getGamers()[0] = new Player(txt_name.getText());
        lbl_maxShips.setText("Schiffe: "+zisGame.getGamers()[0].getBoard().getPlaceableShips());
        hv = 'H';
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
    public void clickOnSurrender(ActionEvent av) {
        zisGame.getGamers()[zisGame.getBoardIndex()].setScore(Game.MAX_SHIPS);
        updateStats();
    }

    //Wenn man Bereit klickt, so werden alle TextFields und Buttons disabled.
    //Der Button Aufgeben soll erscheinen.
    public void clickOnReady(){
        if (!txt_name.getText().equals("")&&zisGame.getGamers()[zisGame.getPlayerIndex()].getBoard().getPlaceableShips() == 0) {
            zisGame.getGamers()[zisGame.getPlayerIndex()].setName(txt_name.getText());
            txt_name.setDisable(true);
            GP_Feld.getChildren().removeAll(txt_shipSize, lbl_maxShips, btn_v, btn_h, btn_ready);

            btn_next.setMinSize(100,50);
            btn_next.setOnAction(this::clickOnNext);
            btn_surrender.setMinSize(100,50);
            btn_surrender.setOnAction(this::clickOnSurrender);
            btn_close.setMinSize(100,50);
            btn_close.setOnAction(this::clickOnClose);

            lbl_score0.setText(zisGame.getGamers()[0].getName()+"'s Punkte: "+zisGame.getGamers()[0].getScore());
            lbl_score1.setText(zisGame.getGamers()[1].getName()+"'s Punkte: "+zisGame.getGamers()[1].getScore());

            GP_Feld.add(lbl_status, 0, 1, 3, 1);
            GP_Feld.add(lbl_score0,0,2,3,1);
            GP_Feld.add(lbl_score1,0,3,3,1);
            GP_Feld.add(btn_next,0,4,2,1);
            GP_Feld.add(btn_surrender,0,6,2,1);
            GP_Feld.add(btn_close,0,7,2,1);

            btn_next.setDisable(true);

            for (int i = 0; i < Game.FIRELDSIZE; i++) {
                for (int j = 0; j < Game.FIRELDSIZE; j++) {
                    fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToPlace);
                    changeButtonsState(true);
                }
            }

            zisGame.setBoardIndex(zisGame.getBoardIndex()+1);
            updateBoard();
            updateStats();
        }
    }

    //Wenn auf den Button Next geklickt wird, Werden die Funktionen von den Buttons genommen.
    //Nächster Spieler wird geladen, View aktualisiert, Bot greift an.
    //Es wird auch nach Gewinner gesucht.
    public void clickOnNext(ActionEvent actionEvent) {
        btn_next.setDisable(true);
        zisGame.nextRound();
        updateBoard();
        updateStats();

        //Wenn der Bot dran ist.
        if (zisGame.getPlayerIndex() == 1) {
            zisGame.botGame();
            btn_surrender.setDisable(true);
            updateBoard();
            updateStats();
            btn_next.setDisable(false);
        } else {
            changeButtonsState(true);
            btn_surrender.setDisable(false);
            btn_next.setDisable(true);
        }
    }

    //Wenn man sein eigenes Board sieht, kann man seine Schiffe platzieren.
    EventHandler<MouseEvent> clickOnButtonToPlace = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button input = (Button) mouseEvent.getSource();
            for (int i = 0; i < Game.FIRELDSIZE; i++) {
                for (int j = 0; j < Game.FIRELDSIZE; j++) {
                    if (input == fireldButtons[i][j]) {
                        try {
                            zisGame.getGamers()[zisGame.getPlayerIndex()].getBoard().placeShip(i, j, Math.abs(Integer.parseInt(txt_shipSize.getText())), hv);
                        } catch (NumberFormatException ignored){}
                    }
                }
            }
            lbl_maxShips.setText("Schiffe: "+zisGame.getGamers()[zisGame.getPlayerIndex()].getBoard().getPlaceableShips());
            updateBoard();
        }
    };

    //Wenn man das Board des Gegners sieht, kann man ein Feld angreifen.
    EventHandler<MouseEvent> clickOnButtonToDestroy = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button input = (Button) mouseEvent.getSource();
            int tempScore = zisGame.getGamers()[zisGame.getPlayerIndex()].getScore();
            for (int i = 0; i < Game.FIRELDSIZE; i++) {
                for (int j = 0; j < Game.FIRELDSIZE; j++) {
                    if (input == fireldButtons[i][j]) {
                        zisGame.attackField(i,j);
                    }
                }
            }
            updateBoard();
            updateStats();
            changeButtonsState(true);

            //Wenn er nicht trifft, dann Buttons werden deaktiviert und NEXT Button wird aktiviert.
            if (tempScore == zisGame.getGamers()[zisGame.getPlayerIndex()].getScore() && zisGame.checkForWinner() < 0) {
                changeButtonsState(false);
                btn_next.setDisable(false);
            }else if (zisGame.checkForWinner() >= 0) {
                changeButtonsState(false);
            }
        }
    };

    //Zeigt das Board an, welches mitgegeben wurde.
    public void updateBoard() {
        for (int i = 0; i < Game.FIRELDSIZE; i++) {
            for (int j = 0; j < Game.FIRELDSIZE; j++) {
                fireldButtons[i][j].setText("");
                fireldButtons[i][j].setStyle("-fx-background-color: white; -fx-border-color: black;");
            }
        }
        for (int i = 0; i < Game.FIRELDSIZE; i++) {
            for (int j = 0; j < Game.FIRELDSIZE; j++) {
                if (zisGame.getBoardIndex() == 0) {
                    if (zisGame.getGamers()[0].getBoard().getFireld()[i][j] == 1) {
                        fireldButtons[j][i].setText("ʘ");
                    }else if (zisGame.getGamers()[0].getBoard().getFireld()[i][j] == 404) {
                        fireldButtons[j][i].setText("֍");
                        fireldButtons[j][i].setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                    }else if (zisGame.getGamers()[0].getBoard().getFireld()[i][j] == 501) {
                        fireldButtons[j][i].setText("⃠");
                        fireldButtons[j][i].setStyle("-fx-background-color: lightpink; -fx-border-color: black;");
                    }
                }else if (zisGame.getBoardIndex() == 1) {
                    if (zisGame.getGamers()[1].getBoard().getFireld()[i][j] == 404) {
                        fireldButtons[j][i].setText("֍");
                        fireldButtons[j][i].setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                    }else if (zisGame.getGamers()[1].getBoard().getFireld()[i][j] == 501) {
                        fireldButtons[j][i].setText("⃠");
                        fireldButtons[j][i].setStyle("-fx-background-color: lightpink; -fx-border-color: black;");
                    }
                }
            }
        }
    }

    //Aktualisiert die StatusSpalte.
    public void updateStats() {
        txt_name.setText(zisGame.getGamers()[zisGame.getBoardIndex()].getName());
        lbl_score0.setText(zisGame.getGamers()[0].getName()+"'s Punkte: "+zisGame.getGamers()[0].getScore());
        lbl_score1.setText(zisGame.getGamers()[1].getName()+"'s Punkte: "+zisGame.getGamers()[1].getScore());
        if (zisGame.checkForWinner() < 0) {
            lbl_status.setText(zisGame.getGamers()[zisGame.getPlayerIndex()].getName()+" ist dran.");
        }else {
            lbl_status.setText(zisGame.getGamers()[zisGame.checkForWinner()].getName()+" gewinnt.");
            changeButtonsState(false);
            btn_surrender.setDisable(true);
            btn_next.setDisable(true);
        }
    }

    //Je nachdem, ob true oder false, wird den Buttons der Eventhandler zugewiesen,
    //oder abgenommen.
    void changeButtonsState(boolean activate) {
        for (int i = 0; i < Game.FIRELDSIZE; i++) {
            for (int j = 0; j < Game.FIRELDSIZE; j++) {
                if (activate) {
                    if (zisGame.getGamers()[zisGame.getBoardIndex()].getBoard().getFireld()[j][i] != 501 && zisGame.getGamers()[zisGame.getBoardIndex()].getBoard().getFireld()[j][i] != 404) {
                        fireldButtons[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
                    }else {
                        fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED, clickOnButtonToDestroy);
                    }
                }else {
                    fireldButtons[i][j].removeEventFilter(MouseEvent.MOUSE_CLICKED,clickOnButtonToDestroy);
                }
            }
        }
    }
}
