package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML TextField txt_name,txt_shipSize;
    @FXML Label lbl_score,lbl_maxShips;
    @FXML Button btn_ready,btn_surrender;
    @FXML Button btn_h,btn_v;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
