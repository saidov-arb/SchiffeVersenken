package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
     * @Authors:    Arbi Saidov        (Backend.)
     *              Patrick Watzinger   (Frontend.)
     *              Marko Jezidzic      (Project Manager.)
     * @param primaryStage
     * @throws Exception
     ♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Schiffe Versenken ©OneCentProduction");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
//
//        Game zisGame = new Game();
//        zisGame.getGamers()[0] = new Player("OneCentProduction TestUser");
//        zisGame.getGamers()[1] = Player.botPlayer();
//
//        zisGame.getGamers()[0].getBoard().placeShip(0,0,5,'H');
//        zisGame.getGamers()[0].getBoard().placeShip(0,2,5,'V');
//        zisGame.getGamers()[0].getBoard().placeShip(2,2,5,'V');
//        zisGame.getGamers()[0].getBoard().placeShip(4,2,5,'V');
//        zisGame.getGamers()[0].getBoard().placeShip(6,2,5,'V');
//
//        zisGame.getGamers()[0].getBoard().printBoard();
//        zisGame.getGamers()[1].getBoard().printBoard();
//
//        for (int i = 0; i < Game.FIRELDSIZE; i++)
//        {
//            for (int j = 0; j < Game.FIRELDSIZE; j++)
//            {
//                zisGame.attackField(0);
//                zisGame.attackField(1,i,j);
//            }
//        }
//        zisGame.attackField(1,4,4);
//        zisGame.attackField(1,5,5);
//        zisGame.attackField(1,6,6);
//        zisGame.attackField(1,7,7);
//
//        zisGame.getGamers()[0].getBoard().printBoard();
//        zisGame.getGamers()[1].getBoard().printBoard();
//
//        System.out.println("\nScore G0: "+zisGame.getGamers()[0].getScore());
//        System.out.println("Score G1: "+zisGame.getGamers()[1].getScore());
//
//        zisGame.getGamers()[1].getBoard().printBoard();
//
//        zisGame.getGamers()[0].getBoard().explode();
//        zisGame.getGamers()[0].getBoard().explode();
//        zisGame.getGamers()[0].getBoard().explode();
//        zisGame.getGamers()[0].getBoard().explode();
//        zisGame.getGamers()[0].getBoard().explode();
//        zisGame.getGamers()[0].getBoard().explode();
//
//        zisGame.getGamers()[1].getBoard().printBoard();
    }
}
