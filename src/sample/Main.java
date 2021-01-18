package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Schiffe Versenken ©OneCentProduction");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

//        Player zisPlayer = new Player("OneCentProduction TestUser");
//        zisPlayer.getBoard().placeShip(1,1,5,'H');
//        zisPlayer.getBoard().placeShip(0,4,4,'V');
//        zisPlayer.getBoard().printBoard();
//
//        zisPlayer.getBoard().explode(1,1);
//        zisPlayer.getBoard().printBoard();
//
//        zisPlayer.getBoard().explode(2,1);
//        zisPlayer.getBoard().printBoard();
    }
}
