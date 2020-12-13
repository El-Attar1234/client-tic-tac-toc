package Mixed;

;
import static Mixed.ClientSocket.btn;
import static Mixed.ClientSocket.instance;
import static Mixed.ClientSocket.ip;
import static Mixed.ClientSocket.ipTextField;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;



public class IPCheckBase extends AnchorPane {

    String _ipText;
    boolean _isIp;

    protected final Pane pane;
    protected final Label label;
    protected final TextField ipTextField;
    protected final Label label0;
    protected final Label ipError;
    protected final Button btnConnect;

    public IPCheckBase() {

        pane = new Pane();
        label = new Label();
        ipTextField = new TextField();
        label0 = new Label();
        ipError = new Label();
        btnConnect = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(543.0);

        pane.setLayoutY(-1.0);
        pane.setPrefHeight(400.0);
        pane.setPrefWidth(545.0);

        label.setLayoutX(32.0);
        label.setLayoutY(108.0);
        label.setPrefHeight(42.0);
        label.setPrefWidth(85.0);
        label.setText("Enter Server Ip");
        label.setFont(new Font(13.0));

        ipTextField.setLayoutX(174.0);
        ipTextField.setLayoutY(115.0);
        ipTextField.setPrefHeight(31.0);
        ipTextField.setPrefWidth(248.0);

        label0.setLayoutX(164.0);
        label0.setLayoutY(27.0);
        label0.setPrefHeight(42.0);
        label0.setPrefWidth(400);
        label0.setText("Connection to the server");
        label0.setFont(new Font(25.0));

        ipError.setLayoutX(171.0);
        ipError.setLayoutY(155.0);
        ipError.setPrefHeight(31.0);
        ipError.setPrefWidth(350.0);
        ipError.setTextFill(javafx.scene.paint.Color.valueOf("#e11515"));
        ipError.setFont(new Font(15.0));

        btnConnect.setLayoutX(218.0);
        btnConnect.setLayoutY(272.0);
        btnConnect.setMnemonicParsing(false);
        btnConnect.setPrefHeight(42.0);
        btnConnect.setPrefWidth(115.0);
        btnConnect.setText("Connect");
        btnConnect.setFont(new Font(20.0));

        pane.getChildren().add(label);
        pane.getChildren().add(ipTextField);
        pane.getChildren().add(label0);
        pane.getChildren().add(ipError);
        pane.getChildren().add(btnConnect);
        getChildren().add(pane);
        btnConnect.setText("Connect");
        btnConnect.setDisable(false);

        btnConnect.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("btn clicked");
                _ipText = ipTextField.getText();
                _isIp = _validateIp(_ipText);
                btnConnect.setText("wait....");
                btnConnect.setDisable(true);

                if (!_isIp) {
                    btnConnect.setText("Connect");
                    btnConnect.setDisable(false);
                } else {
                   
                    ClientSocket.ip = _ipText;
                    ClientSocket.btn = btnConnect;
                    ClientSocket.ipTextField = ipTextField;
                      ClientSocket.getInstance();
               /*     new Thread() {
                        @Override
                        public void run() {
                            try {
                                client = new Socket(ip, 5005);
                                //client.close();
                                System.out.println("closed");
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
                                        _alertFailed();
                                        btn.setText("Connect");
                                        btn.setDisable(false);
                                        instance = null;
                                    }
                                });
                                System.out.println(false);
                                stop();
                                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }.start();*/
                  

                }

            }
        });

    }

    boolean _validateIp(String ip) {
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";
        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            ipError.setText("IP address must be valid");
            return false;
        } else {
            ipError.setText("");
            return true;
        }
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
