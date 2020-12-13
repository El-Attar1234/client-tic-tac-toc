package Mixed;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;

public class Login extends BorderPane {

    String _emailText, _passwordtext, _ipText;
    boolean _isEmail, _isPassword, _isIp;
    Socket client;
    DataInputStream dis;
    PrintStream ps;

    protected final Pane pane;
    protected final Label label;
    protected final Pane pane0;
    protected final Pane pane1;
    protected final Button btnLogin;
    protected final Button btnBack;
    protected final Label label0;
    protected final Button btnSignup;
    protected final Pane pane2;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final RowConstraints rowConstraints4;
    protected final Label label1;
    protected final Label label2;
    protected final TextField emailField;
    protected final Label label3;
    protected final TextField ipField;
    protected final PasswordField passwordField;
    protected final Label emailError;
    protected final Label passwordeError;
    protected final Label iperror;

    public Login() {

        pane = new Pane();
        label = new Label();
        pane0 = new Pane();
        pane1 = new Pane();
        btnLogin = new Button();
        btnBack = new Button();
        label0 = new Label();
        btnSignup = new Button();
        pane2 = new Pane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        label1 = new Label();
        label2 = new Label();
        emailField = new TextField();
        label3 = new Label();
        ipField = new TextField();
        passwordField = new PasswordField();
        emailError = new Label();
        passwordeError = new Label();
        iperror = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(562.0);
        setPrefWidth(431.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(66.0);
        pane.setPrefWidth(431.0);

        label.setLayoutX(63.0);
        label.setLayoutY(11.0);
        label.setPrefHeight(47.0);
        label.setPrefWidth(129.0);
        label.setText("Login forum");
        setTop(pane);

        BorderPane.setAlignment(pane0, javafx.geometry.Pos.CENTER);
        pane0.setPrefHeight(201.0);
        pane0.setPrefWidth(34.0);
        setLeft(pane0);

        BorderPane.setAlignment(pane1, javafx.geometry.Pos.CENTER);
        pane1.setPrefHeight(197.0);
        pane1.setPrefWidth(431.0);

        btnLogin.setLayoutX(192.0);
        btnLogin.setLayoutY(29.0);
        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(40.0);
        btnLogin.setPrefWidth(82.0);
        btnLogin.setText("Login");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _getData();

                _isEmail = _validateEmail(_emailText);
                _isPassword = _validatePassword(_passwordtext);
                _isIp = _validateIp(_ipText);
                if (!_isEmail || !_isPassword || !_isIp) {
                } else {
                    String[] userInf = {_emailText, _passwordtext};
                    _connectServer(userInf, _ipText);
                }

            }

        });

        btnBack.setLayoutX(191.0);
        btnBack.setLayoutY(100.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(38.0);
        btnBack.setPrefWidth(83.0);
        btnBack.setText("Back");

        label0.setLayoutX(73.0);
        label0.setLayoutY(161.0);
        label0.setPrefHeight(31.0);
        label0.setPrefWidth(151.0);
        label0.setText("Don't  have an account ?");

        btnSignup.setLayoutX(246.0);
        btnSignup.setLayoutY(161.0);
        btnSignup.setMnemonicParsing(false);
        btnSignup.setText("Signup");
        setBottom(pane1);
        btnSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Manager.viewPane(Manager.signup);

            }

        });

        BorderPane.setAlignment(pane2, javafx.geometry.Pos.CENTER);
        pane2.setPrefHeight(201.0);
        pane2.setPrefWidth(30.0);
        setRight(pane2);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(150.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(129.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(240.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(238.0);

        rowConstraints.setMaxHeight(60.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(60.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(60.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(60.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(99.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(79.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(60.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(60.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMaxHeight(89.0);
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(52.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMaxHeight(89.0);
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(52.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label1.setPrefHeight(43.0);
        label1.setPrefWidth(116.0);
        label1.setText(" E-Mail");

        GridPane.setRowIndex(label2, 2);
        label2.setPrefHeight(42.0);
        label2.setPrefWidth(122.0);
        label2.setText(" Password");

        GridPane.setColumnIndex(emailField, 1);
        emailField.setPrefHeight(38.0);
        emailField.setPrefWidth(238.0);

        GridPane.setRowIndex(label3, 4);
        label3.setPrefHeight(42.0);
        label3.setPrefWidth(120.0);
        label3.setText(" IP");

        GridPane.setColumnIndex(ipField, 1);
        GridPane.setRowIndex(ipField, 4);
        ipField.setPrefHeight(38.0);
        ipField.setPrefWidth(238.0);

        GridPane.setColumnIndex(passwordField, 1);
        GridPane.setRowIndex(passwordField, 2);
        passwordField.setPrefHeight(40.0);
        passwordField.setPrefWidth(238.0);

        GridPane.setColumnIndex(emailError, 1);
        GridPane.setRowIndex(emailError, 1);
        emailError.setPrefHeight(33.0);
        emailError.setPrefWidth(216.0);

        GridPane.setColumnIndex(passwordeError, 1);
        GridPane.setRowIndex(passwordeError, 3);
        passwordeError.setPrefHeight(37.0);
        passwordeError.setPrefWidth(221.0);

        GridPane.setColumnIndex(iperror, 1);
        GridPane.setRowIndex(iperror, 5);
        iperror.setPrefHeight(33.0);
        iperror.setPrefWidth(233.0);
        setCenter(gridPane);

        pane.getChildren().add(label);
        pane1.getChildren().add(btnLogin);
        pane1.getChildren().add(btnBack);
        pane1.getChildren().add(label0);
        pane1.getChildren().add(btnSignup);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getRowConstraints().add(rowConstraints4);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(label2);
        gridPane.getChildren().add(emailField);
        gridPane.getChildren().add(label3);
        gridPane.getChildren().add(ipField);
        gridPane.getChildren().add(passwordField);
        gridPane.getChildren().add(emailError);
        gridPane.getChildren().add(passwordeError);
        gridPane.getChildren().add(iperror);

    }

    void _getData() {
        _emailText = emailField.getText();
        _passwordtext = passwordField.getText();
        _ipText = ipField.getText();

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
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);

        if (!matcher.matches()) {
            passwordeError.setText("at least contain one(digit,upp,lowerand \nspecial char)and no whitespace");
            return false;
        } else {
            passwordeError.setText("");
            return true;
        }

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
            iperror.setText("IP address must be in the form (A.B.C.D),where \nthe value of A, B, C, and D may range from 0 to 255 \nand Leading zeros are allowed.");
            return false;
        } else {
            iperror.setText("");
            return true;
        }

    }

    void _connectServer(String[] userInf, String ip) {
        new Thread() {
            @Override
            public void run() {
                try {
                    client = new Socket(ip, 5005);
                    dis = new DataInputStream(client.getInputStream());
                    ps = new PrintStream(client.getOutputStream());
                    String msg = "L";
                    for (int i = 0; i < userInf.length; i++) {
                        msg = msg + "##" + userInf[i];
                    }
                    ps.println(msg);
                    ps.flush();
                    boolean inComing = dis.readBoolean();
                    System.out.println(inComing);

                } catch (IOException ex) {
                    stop();
                 
                }

            }

        }.start();
    }

}
