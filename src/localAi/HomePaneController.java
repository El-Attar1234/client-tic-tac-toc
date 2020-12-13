/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localAi;

import Mixed.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mohamed
 */
public class HomePaneController implements Initializable {

    @FXML
    private Label FavoriteMode;

    @FXML
    private Pane StartPane;

    @FXML
    private void handlePlayLocalAction(ActionEvent event) throws IOException, ParseException {

        Parent root = FXMLLoader.load(getClass().getResource("MultiPlayer.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();

        
        

  

    }

    @FXML
    private void handlePlayAiAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayer.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();

    }

    @FXML
    private void handlePlayNetworkAction(ActionEvent event) {

        Pane root = new Pane();

        root.getChildren().add(Manager.login);
        root.getChildren().add(Manager.signup);
          root.getChildren().add(Manager.ipscreen);
          root.getChildren().add(Manager.online);

        Manager.viewPane(Manager.ipscreen);

        //Manager.viewPane(Manager.login);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();

    }

    @FXML
    private void handleExitAction(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    private void handleHistoryAction(ActionEvent event) {

        FavoriteMode.setText("Hello");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
