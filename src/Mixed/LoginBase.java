package Mixed;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginBase extends AnchorPane {

    String _emailText, _passwordtext;// _ipText;
    boolean _isEmail, _isPassword;// _isIp;
    public static String ip = "";
    Socket client;
    DataInputStream dis;
    PrintStream ps;

    String _type, _key;
    JSONObject jsonObject;
    String jsonText;
    String inComing;
    Object obj;
    JSONObject jo2;

    protected final Pane pane;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    // protected final Label label2;
    protected final TextField emailField;
    // protected final TextField ipField;
    protected final Label emailError;
    protected final Label passwordError;
    // protected final Label ipError;
    protected final Button btnLogin;
    protected final Button btnBack;
    protected final Button btnRegister;
    protected final StackPane stackPane;
    protected final TextField passTextField;
    protected final PasswordField passPasswordField;
    protected final CheckBox checkBoxId;

    public LoginBase() {

        pane = new Pane();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        //  label2 = new Label();
        emailField = new TextField();
        // ipField = new TextField();
        emailError = new Label();
        passwordError = new Label();
        // ipError = new Label();
        btnLogin = new Button();
        btnBack = new Button();
        btnRegister = new Button();
        stackPane = new StackPane();
        passTextField = new TextField();
        passPasswordField = new PasswordField();
        checkBoxId = new CheckBox();

        setId("AnchorPane");
        setPrefHeight(750.0);
        setPrefWidth(600.0);

        pane.setPrefHeight(750.0);
        pane.setPrefWidth(700.0);

        label.setLayoutX(274.0);
        label.setLayoutY(31.0);
        label.setPrefHeight(40.0);
        label.setPrefWidth(166.0);
        label.setText("Login Form");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#eb0c0c"));
        label.setFont(new Font(28.0));

        label0.setLayoutX(60.0);
        label0.setLayoutY(110.0);
        label0.setPrefHeight(30.0);
        label0.setPrefWidth(200.0);
        label0.setText("Enter Your Email");
        label0.setFont(new Font(20.0));

        label1.setLayoutX(60.0);
        label1.setLayoutY(231.0);
        label1.setPrefHeight(30.0);
        label1.setPrefWidth(200.0);
        label1.setText("Enter Your Password");
        label1.setFont(new Font(20.0));

//        label2.setLayoutX(60.0);
//        label2.setLayoutY(350.0);
//        label2.setPrefHeight(30.0);
//        label2.setPrefWidth(200.0);
//        label2.setText("Server IP");
//        label2.setFont(new Font(20.0));
        emailField.setLayoutX(269.0);
        emailField.setLayoutY(106.0);
        emailField.setPrefHeight(38.0);
        emailField.setPrefWidth(395.0);

//        ipField.setLayoutX(269.0);
//        ipField.setLayoutY(347.0);
//        ipField.setPrefHeight(38.0);
//        ipField.setPrefWidth(395.0);
        emailError.setLayoutX(269.0);
        emailError.setLayoutY(155.0);
        emailError.setPrefHeight(26.0);
        emailError.setPrefWidth(395.0);
        emailError.setTextFill(javafx.scene.paint.Color.valueOf("#e11515"));
        emailError.setFont(new Font(15.0));

        passwordError.setLayoutX(269.0);
        passwordError.setLayoutY(279.0);
        passwordError.setPrefHeight(26.0);
        passwordError.setPrefWidth(395.0);
        passwordError.setTextFill(javafx.scene.paint.Color.valueOf("#da1414"));
        passwordError.setFont(new Font(15.0));

//        ipError.setLayoutX(269.0);
//        ipError.setLayoutY(398.0);
//        ipError.setPrefHeight(26.0);
//        ipError.setPrefWidth(395.0);
//        ipError.setTextFill(javafx.scene.paint.Color.valueOf("#e11414"));
//        ipError.setFont(new Font(15.0));
        btnLogin.setLayoutX(250.0);
        btnLogin.setLayoutY(494.0);
        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(67.0);
        btnLogin.setPrefWidth(200.0);
        btnLogin.setText("Login");
        btnLogin.setFont(new Font(28.0));

        btnBack.setLayoutX(403.0);
        btnBack.setLayoutY(634.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(36.0);
        btnBack.setPrefWidth(208.0);
        btnBack.setText("Back");
        btnBack.setFont(new Font(24.0));

        btnRegister.setLayoutX(80.0);
        btnRegister.setLayoutY(634.0);
        btnRegister.setMnemonicParsing(false);
        btnRegister.setPrefHeight(36.0);
        btnRegister.setPrefWidth(208.0);
        btnRegister.setText("Register");
        btnRegister.setFont(new Font(24.0));

        stackPane.setLayoutX(269.0);
        stackPane.setLayoutY(227.0);
        stackPane.setPrefHeight(38.0);
        stackPane.setPrefWidth(395.0);

        passTextField.setPrefHeight(38.0);
        passTextField.setPrefWidth(395.0);

        passPasswordField.setPrefHeight(41.0);
        passPasswordField.setPrefWidth(395.0);

        checkBoxId.setLayoutX(560.0);
        checkBoxId.setLayoutY(275.0);
        checkBoxId.setMnemonicParsing(false);
        checkBoxId.setPrefHeight(26.0);
        checkBoxId.setPrefWidth(140.0);
        checkBoxId.setText("password");

        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        // pane.getChildren().add(label2);
        pane.getChildren().add(emailField);
        //  pane.getChildren().add(ipField);
        pane.getChildren().add(emailError);
        pane.getChildren().add(passwordError);
        // pane.getChildren().add(ipError);
        pane.getChildren().add(btnLogin);
        pane.getChildren().add(btnBack);
        pane.getChildren().add(btnRegister);
        stackPane.getChildren().add(passTextField);
        stackPane.getChildren().add(passPasswordField);
        pane.getChildren().add(stackPane);
        pane.getChildren().add(checkBoxId);
        getChildren().add(pane);
        checkBoxId.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (checkBoxId.isSelected()) {
                passTextField.setText(passPasswordField.getText());
                passTextField.toFront();
            } else {
                passPasswordField.setText(passTextField.getText());
                passPasswordField.toFront();
            }

        }));

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _getData();

                _isEmail = _validateEmail(_emailText);
                _isPassword = _validatePassword(_passwordtext);
                //     _isIp = _validateIp(_ipText);
                if (!_isEmail || !_isPassword) {
                } else {
                    _connectServer(_emailText, _passwordtext);
                    //_key="SUCCESS";

                }

            }

        });
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Manager.viewPane(Manager.signup);
                _clear();

            }

        });
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/localAi/HomePane.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.resizableProperty().setValue(Boolean.FALSE);

                    stage.show();
                    _clear();

                    //  ClientSocket.instance = null;
                } catch (IOException ex) {
                    Logger.getLogger(LoginBase.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }

    void _clear() {
        emailField.clear();
        emailError.setText("");
        passPasswordField.clear();
        passwordError.setText("");

    }

    void _getData() {
        _emailText = emailField.getText();
        _passwordtext = !checkBoxId.isSelected() ? passPasswordField.getText() : passTextField.getText();
   

    }

    boolean _validateEmail(String email) {
        final String _emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(_emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (email.isEmpty() || !matcher.matches()) {
            emailError.setText("Email isn't correct");
            return false;
        } else {
            emailError.setText("");
            return true;
        }

    }

    boolean _validatePassword(String pass) {
        if (pass.isEmpty() || pass.trim().length() < 8) {
            passwordError.setText("password must be greater than 8 char");
            return false;
        } else {
            passwordError.setText("");
            return true;
        }

    }

    void _connectServer(String email, String password) {
        //  Socket client=
        dis = ClientSocket.dis;
        ps = ClientSocket.ps;
        jsonObject = new JSONObject();
        jsonObject.put("TYPE", "LOGIN");
        jsonObject.put("EMAIL", email);
        jsonObject.put("PASSWORD", password);
        jsonText = JSONValue.toJSONString(jsonObject);
        ps.println(jsonText);
        System.out.println(jsonText);
        ps.flush();

        new Thread() {
            @Override
            public void run() {
                System.out.println("reply");
                try {

                    inComing = dis.readLine();
                    System.out.println(inComing);
                    obj = new JSONParser().parse(inComing);
                    jo2 = (JSONObject) obj;
                    _type = (String) jo2.get("TYPE");
                    _key = (String) jo2.get("KEY");

                    switch (_key) {
                        case "SUCCESS":
                            _alertSuccess();
                            System.out.println("go to online screen");

//                            try {    Parent root;
//                                root = FXMLLoader.load(getClass().getResource("/online/OnlineMainPane.fxml"));
//
//                                Scene scene = new Scene(root);
//
//                                Window window = label.getScene().getWindow();
//                                Stage stage = (Stage) label.getScene().getWindow();
//
//                                stage.setScene(scene);
//                                stage.resizableProperty().setValue(Boolean.FALSE);
//
//                                stage.show();
//                                _clear();
//
//                                ClientSocket.instance = null;
//
//                            } catch (IOException ex) {
//                                Logger.getLogger(LoginBase.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                            break;
                        case "INCORRECT_PASSWORD":
                            System.out.println("incorrect password");
                            _alertPasswordError();
                            break;
                        case "EMAIL_NOT_FOUND":
                            System.out.println("incorrect email");
                            _alertEmailError();
                            break;

                    }

                } catch (IOException ex) {
                    //   stop();

                } catch (ParseException ex) {
                    Logger.getLogger(LoginBase.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();

    }

    void _alertSuccess() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText(null);
                alert.setContentText("Login Successfully");
                //   alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
                Manager.viewPane(Manager.online);
            }
        });

    }

    void _alertEmailError() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Login Erorr");
                alert2.setContentText("Email Not Found  \n May be you didn't Register or Wrote the email Incorrectly");
                alert2.showAndWait();
            }
        });
    }

    void _alertPasswordError() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Login Erorr");
                alert1.setContentText("The Passwprd you Entered is Wrong ");
                alert1.showAndWait();
            }
        });
    }

}
