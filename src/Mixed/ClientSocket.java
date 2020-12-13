/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mixed;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ClientSocket {

    public static ClientSocket instance;
    private static Socket client;
    public static String ip = "";
    public static Button btn;
    public static TextField ipTextField;
    public static DataInputStream dis;
    public static PrintStream ps;

    public ClientSocket() {

        System.out.println("Constructor" + ip);
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("try to connect to server");
                    client = new Socket(ip, 5005);
                    dis = new DataInputStream(client.getInputStream());
                    ps = new PrintStream(client.getOutputStream());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            _alertConnected();
                            btn.setText("Connect");
                            btn.setDisable(false);
                            ipTextField.clear();
                            Manager.viewPane(Manager.login);
                            System.out.println("connection ocurr");
                        }
                    });

                } catch (IOException ex) {
                    // isConnected = false;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("connection failed");
                            _alertFailed();
                            btn.setText("Connect");
                            btn.setDisable(false);
                            // instance = null;
                        }
                    });

                    stop();
                    Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }.start();

    }

    public static void getInstance() {
        new ClientSocket();

    }

    void _alertFailed() {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error in connection ");
        alertError.setHeaderText("please ,enter the accurate ip of the server");
        alertError.showAndWait();
    }

    void _alertConnected() {
        Alert alretSuccess = new Alert(Alert.AlertType.NONE, "connection is achieved", ButtonType.OK);
        alretSuccess.setTitle("Connection");
        alretSuccess.showAndWait();
    }

}
