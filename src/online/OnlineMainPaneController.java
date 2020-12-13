/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import Mixed.LoginBase;
import Mixed.RegisterBase;
import Mixed.Signup;
import Models.Player;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
//import static online.ClientInstance.client;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class OnlineMainPaneController implements Initializable {
    
    
    
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    

    private static String MyEmail = "Abd@aa.com";

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label onlineMainPaneTitle;
    @FXML
    private Button btn_onlineLogout;
    @FXML
    private ListView<String> playerList;

    ObservableList list = FXCollections.observableArrayList();

    //private Socket client;
    //private DataInputStream dis;
    //private PrintStream ps;

    //***************************************************************************//
    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {

        handleClientISClosing();
        Parent root = FXMLLoader.load(getClass().getResource("/localAi/HomePane.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    private void handleCloseWindowButton() throws IOException {
        handleClientISClosing();
    }

    private void handleClientISClosing() throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TYPE", "CLIENT_IS_CLOSING");
        jsonObject.put("EMAIL", MyEmail);

        String jsonText = JSONValue.toJSONString(jsonObject);
        //ClientInstance.getInstance().getPrintStream().println(jsonText);
        //ClientInstance.getInstance().getPrintStream().flush();

    }

    //**************************************************************************//
    @FXML

    // send play request
    //********************************************************************// 
    private void handleSelectedPlayer(MouseEvent event) throws IOException {
        String selected = playerList.getSelectionModel().getSelectedItem();

        if (selected != null && !selected.isEmpty()) {
            sendInvitationPopUp(selected);
        }
    }

    private void sendInvitationPopUp(String message) throws IOException {
        Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
        showWinner.setTitle("Invite Friend");
        showWinner.setHeaderText(null);
        showWinner.setContentText("Send invitation to " + message);
        showWinner.initModality(Modality.APPLICATION_MODAL);

        ButtonType btnSend = new ButtonType("Send");

        showWinner.getButtonTypes().setAll(btnSend);

        Optional<ButtonType> choices = showWinner.showAndWait();
        if (choices.get() == btnSend) {
            //   if click ok
            sendPlayRequest();
        }

    }

    private void sendPlayRequest() throws IOException {

        String player1email = "";
        String player2email = "";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TYPE", "PLAYER_1_ASK_TO_PLAY");
        jsonObject.put("EMAIL", player1email);
        jsonObject.put("EMAIL_TARGET", player2email);

        String jsonText = JSONValue.toJSONString(jsonObject);
        ClientInstance.getInstance().getPrintStream().println(jsonText);
        ClientInstance.getInstance().getPrintStream().flush();

    }
    //*************************************************************************************//

    // recive play request
    //****************************************************************************************//
    private boolean recievePlayRequest(String friendEmail) {

        boolean key = false;

        Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
        showWinner.setTitle("Play Invitation");
        showWinner.setHeaderText(null);
        showWinner.setContentText(friendEmail + "Want to play with you");
        showWinner.initModality(Modality.APPLICATION_MODAL);

        ButtonType btnAccept = new ButtonType("Accept");
        ButtonType btnRefuse = new ButtonType("Refuse");

        showWinner.getButtonTypes().setAll(btnAccept, btnRefuse);

        Optional<ButtonType> choices = showWinner.showAndWait();
        if (choices.get() == btnAccept) {
            key = true;
            // in less than 2 second
        }
        if (choices.get() == btnRefuse) {
            key = false;
        }
        // handle more than one second 
        // close x button of alert

        return key;
    }

    // response to play requset
    private void responeToPlayrequest(String inComingjsonText) throws ParseException, IOException {

        Object obj = new JSONParser().parse(inComingjsonText);
        JSONObject jo2 = (JSONObject) obj;
        String email = (String) jo2.get("EMAIL");
        String emailTarget = (String) jo2.get("EMAIL_TARGET");
        if (MyEmail == emailTarget) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TYPE", "PLAYER_1_ASK_TO_PLAY");
            jsonObject.put("EMAIL", MyEmail);
            jsonObject.put("EMAIL_TARGET", email);

            if (recievePlayRequest(email)) {
                jsonObject.put("KEY", "OK");
            } else {
                jsonObject.put("KEY", "NO");
            }

            String jsonText = JSONValue.toJSONString(jsonObject);
            //ClientInstance.getInstance().getPrintStream().println(jsonText);
            //ClientInstance.getInstance().getPrintStream().flush();

        }

    }
    //******************************************************************************//

    //**********************************************************************************//
    // recieve response to play request
    private void recieveResponseToPlayRequest(String inComingjsonText) throws ParseException {

        Object obj = new JSONParser().parse(inComingjsonText);
        JSONObject jo2 = (JSONObject) obj;
        String email = (String) jo2.get("EMAIL");
        String emailTarget = (String) jo2.get("EMAIL_TARGET");
        String key = (String) jo2.get("KEY");

        if (MyEmail == emailTarget) {
            if (key == "OK") {
                //
                // open new screen
            } else if (key == "No") {
                Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
                showWinner.setTitle("Sorry !!");
                showWinner.setHeaderText(null);
                showWinner.setContentText("Your Invitation is refused");
                showWinner.initModality(Modality.APPLICATION_MODAL);

                ButtonType btnOk = new ButtonType("ok");
                showWinner.showAndWait();

            }
        }

    }

    //*******************************************************************************//
   
    private void handleIncomingMessage(String jsonText) throws ParseException, IOException {

        Object obj = new JSONParser().parse(jsonText);
        JSONObject jo2 = (JSONObject) obj;
        String type = (String) jo2.get("Type");

        switch (type) {
            case "RECIEVE_PLAY_REQUEST":
                //someone ask to play with me
                // handle the response
                //   done
                responeToPlayrequest(jsonText);
                break;

            case "RECIEVE_RESPONSE_TO_PLAY_REQUEST":
                // i ask someone to play
                // handle his response
                recieveResponseToPlayRequest(jsonText);
                break;

            case "SERVER_IS_CLOSING":
                // handle closing the server
                //server about to close
                handleClosetheServer();
                break;

            case "ONLINEPLAYERS":
                // recieve online players data
                getOnlinePlayer(jsonText);
                break;

        }

    }
    
    private void getOnlinePlayer(String jsonText) throws ParseException
    {
        ArrayList<String> jsonArray = new ArrayList<>();

        Object obj = new JSONParser().parse(jsonText);
        JSONObject jo2 = (JSONObject) obj;
        String key = (String) jo2.get("KEY");

        for (int i = 0; i < jo2.size(); i++) {
            String name = (String) jo2.get("NAME");
            String email = (String) jo2.get("EMAIL");
            String score = (String) jo2.get("SCORE");

            System.out.println("name= "+name );
            
            //list.removeAll(list);
            list.addAll(name,email,score);
        }

          playerList.getItems().addAll(list);
        
    }

    private void handleClosetheServer() {
        Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
        showWinner.setTitle("OOPS !!!");
        showWinner.setHeaderText(null);
        showWinner.setContentText("Sorry! The Server is Down ");
        showWinner.initModality(Modality.APPLICATION_MODAL);

        ButtonType btnOk = new ButtonType("Ok");
        showWinner.showAndWait();

    }

//    void initalizeConnection() {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    client = new Socket("127.0.0.1", 5005);
//                    ds = new DataInputStream(client.getInputStream());
//                    ps = new PrintStream(client.getOutputStream());
//                    String inComing=ds.readLine();
//                    System.out.println(inComing.toString());
//
//                    //String inComing =  ClientInstance.getInstance().getDataInputStream().readLine();
//                    handleIncomingMessage(inComing);
//
//                } catch (IOException ex) {
//                    stop();
//                } catch (ParseException ex) {
//                    Logger.getLogger(RegisterBase.class.getName()).log(Level.SEVERE, null, ex);
//                } finally {
//                    closeConnections();
//                }
//
//            }
//
//        }.start();
//    }

    void closeConnections() {
        try {
            
             ps.close();
            dis.close();
            mySocket.close();
        //ClientInstance.getInstance().getPrintStream().close();
        //ClientInstance.getInstance().getDataInputStream().close();
        //ClientInstance.getInstance().getPrintStream().close();


        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initializeConn();
        list.add("Mohamed Abdallah \t isPlaying\t125");
        list.add("Mohamed Abdallah Ahmed\t online\t363");
        list.add("Mohamed Abdallah \t isPlaying\t125");
        list.add("Mohamed Abdallah Ahmed\t online\t363");
      
        playerList.getItems().addAll(list);

    }
    private void initializeConn()
    {
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            
        } catch (IOException ex) {
            
        } 
//            
//            new Thread() {
//                @Override
//                public void run() {
//                    while(true)   
//                    {      
//                            String replyMsg;
//                        try {
//                            replyMsg = dis.readLine();
//                            System.out.println(replyMsg);
//
//                        } catch (IOException ex) {
//                            Logger.getLogger(OnlineMainPaneController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
////                            txtRecived.appendText(replyMsg+"\n");
//                        
//                    }
//                }
//            }.start();
    
    }
      
    }

    

