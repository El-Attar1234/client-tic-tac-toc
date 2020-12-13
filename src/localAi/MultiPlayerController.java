/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localAi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML SharedController class
 *
 * @author Mohamed
 */
public class MultiPlayerController implements Initializable {

    @FXML
    private Label playerXLabel,playerOLabel;
  
    @FXML
    private TextField firstPlayerName,secondPlayerName;

    @FXML
    private Button start;
    
    @FXML
    private Button back;
    
    @FXML
    private CheckBox checkBoxAllowRecord;
    
    
    @FXML
    private void handleStartAction(ActionEvent event) throws IOException {
        
        
        if(checkBoxAllowRecord.isSelected())
                SharedController.allowRecord=true;
            else
                SharedController.allowRecord=false;
        SharedController.firstPlayerName=firstPlayerName.getText();
        SharedController.secondPlayerName=secondPlayerName.getText();
        
        
        Parent root = FXMLLoader.load(getClass().getResource("MultiPlayerGame.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("HomePane.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
