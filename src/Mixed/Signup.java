
package Mixed;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Signup extends BorderPane {

    String _nameText, _emailText, _passwordtext, _ipText;
    boolean _isName, _isEmail, _isPassword, _isIp;
    Socket client;
    DataInputStream dis;
    PrintStream ps;


    protected final Pane pane;
    protected final Label label;
    protected final Pane pane0;
    protected final Pane pane1;
    protected final Pane pane2;
    protected final Button btnSignup;
    protected final Button btnBack;
    protected final Label label0;
    protected final Button btnLogin;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final RowConstraints rowConstraints4;
    protected final RowConstraints rowConstraints5;
    protected final RowConstraints rowConstraints6;
    protected final Label label1;
    protected final Label nameError;
    protected final Label label2;
    protected final Label emailError;
    protected final Label label3;
    protected final Label passworderror;
    protected final Label label4;
    protected final Label ipError;
    protected final TextField nameField;
    protected final TextField emailField;
    protected final PasswordField passwordField;
    protected final TextField ipField;

    public Signup() {

        pane = new Pane();
        label = new Label();
        pane0 = new Pane();
        pane1 = new Pane();
        pane2 = new Pane();
        btnSignup = new Button();
        btnBack = new Button();
        label0 = new Label();
        btnLogin = new Button();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        rowConstraints5 = new RowConstraints();
        rowConstraints6 = new RowConstraints();
        label1 = new Label();
        nameError = new Label();
        label2 = new Label();
        emailError = new Label();
        label3 = new Label();
        passworderror = new Label();
        label4 = new Label();
        ipError = new Label();
        nameField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        ipField = new TextField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(637.0);
        setPrefWidth(509.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(52.0);
        pane.setPrefWidth(509.0);

        label.setLayoutX(68.0);
        label.setLayoutY(7.0);
        label.setPrefHeight(38.0);
        label.setPrefWidth(140.0);
        label.setText("Sign_up Form ");
        setTop(pane);

        BorderPane.setAlignment(pane0, javafx.geometry.Pos.CENTER);
        pane0.setPrefHeight(511.0);
        pane0.setPrefWidth(49.0);
        setLeft(pane0);

        BorderPane.setAlignment(pane1, javafx.geometry.Pos.CENTER);
        pane1.setPrefHeight(511.0);
        pane1.setPrefWidth(39.0);
        setRight(pane1);

        BorderPane.setAlignment(pane2, javafx.geometry.Pos.CENTER);
        pane2.setPrefHeight(203.0);
        pane2.setPrefWidth(509.0);

        btnSignup.setLayoutX(229.0);
        btnSignup.setLayoutY(29.0);
        btnSignup.setMnemonicParsing(false);
        btnSignup.setText("Singup");
       
        btnSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _getData();
                _isName = _validateName(_nameText);
                _isEmail = _validateEmail(_emailText);
                _isPassword = _validatePassword(_passwordtext);
                _isIp = _validateIp(_ipText);
                if (!_isName || !_isEmail || !_isPassword || !_isIp) {
                } else {
                    String[] userInf = {_nameText, _emailText, _passwordtext};
                    _connectServer(userInf,_ipText);
                }

            }

        });

        btnBack.setLayoutX(229.0);
        btnBack.setLayoutY(82.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(21.0);
        btnBack.setPrefWidth(53.0);
        btnBack.setText("Back");

        label0.setLayoutX(109.0);
        label0.setLayoutY(149.0);
        label0.setPrefHeight(30.0);
        label0.setPrefWidth(134.0);
        label0.setText("Already have an email?");

        btnLogin.setLayoutX(268.0);
        btnLogin.setLayoutY(151.0);
        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(25.0);
        btnLogin.setPrefWidth(66.0);
        btnLogin.setText("login");
        setBottom(pane2);
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
         Manager.viewPane(Manager.login);

            }

        });

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(390.0);
        gridPane.setPrefWidth(421.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(205.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(162.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(262.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(259.0);

        rowConstraints.setMaxHeight(49.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(47.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(51.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(51.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(30.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(30.0);
        rowConstraints5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints6.setMinHeight(10.0);
        rowConstraints6.setPrefHeight(30.0);
        rowConstraints6.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label1.setPrefHeight(43.0);
        label1.setPrefWidth(136.0);
        label1.setText("Enter your name");

        GridPane.setColumnIndex(nameError, 1);
        GridPane.setRowIndex(nameError, 1);
        nameError.setPrefHeight(38.0);
        nameError.setPrefWidth(221.0);

        GridPane.setRowIndex(label2, 2);
        label2.setPrefHeight(35.0);
        label2.setPrefWidth(148.0);
        label2.setText("Enter your email");

        GridPane.setColumnIndex(emailError, 1);
        GridPane.setRowIndex(emailError, 3);
        emailError.setPrefHeight(40.0);
        emailError.setPrefWidth(237.0);

        GridPane.setRowIndex(label3, 4);
        label3.setPrefHeight(38.0);
        label3.setPrefWidth(147.0);
        label3.setText("Enter password");

        GridPane.setColumnIndex(passworderror, 1);
        GridPane.setRowIndex(passworderror, 5);
        passworderror.setPrefHeight(37.0);
        passworderror.setPrefWidth(243.0);

        GridPane.setRowIndex(label4, 6);
        label4.setPrefHeight(38.0);
        label4.setPrefWidth(150.0);
        label4.setText("Enter IP");

        GridPane.setColumnIndex(ipError, 1);
        GridPane.setRowIndex(ipError, 7);
        ipError.setPrefHeight(38.0);
        ipError.setPrefWidth(239.0);

        GridPane.setColumnIndex(nameField, 1);
        nameField.setPrefHeight(31.0);
        nameField.setPrefWidth(259.0);

        GridPane.setColumnIndex(emailField, 1);
        GridPane.setRowIndex(emailField, 2);
        emailField.setPrefHeight(32.0);
        emailField.setPrefWidth(259.0);

        GridPane.setColumnIndex(passwordField, 1);
        GridPane.setRowIndex(passwordField, 4);
        passwordField.setPrefHeight(34.0);
        passwordField.setPrefWidth(259.0);

        GridPane.setColumnIndex(ipField, 1);
        GridPane.setRowIndex(ipField, 6);
        ipField.setPrefHeight(35.0);
        ipField.setPrefWidth(259.0);
        setCenter(gridPane);

        pane.getChildren().add(label);
        pane2.getChildren().add(btnSignup);
        pane2.getChildren().add(btnBack);
        pane2.getChildren().add(label0);
        pane2.getChildren().add(btnLogin);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getRowConstraints().add(rowConstraints4);
        gridPane.getRowConstraints().add(rowConstraints5);
        gridPane.getRowConstraints().add(rowConstraints6);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(nameError);
        gridPane.getChildren().add(label2);
        gridPane.getChildren().add(emailError);
        gridPane.getChildren().add(label3);
        gridPane.getChildren().add(passworderror);
        gridPane.getChildren().add(label4);
        gridPane.getChildren().add(ipError);
        gridPane.getChildren().add(nameField);
        gridPane.getChildren().add(emailField);
        gridPane.getChildren().add(passwordField);
        gridPane.getChildren().add(ipField);

    }

    void _getData() {
        _nameText = nameField.getText();
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

    boolean _validateName(String name) {
        final String _namePattern = "[A-Za-z0-9_]+";
        Pattern pattern = Pattern.compile(_namePattern);
        Matcher matcher = pattern.matcher(name);

        if (name.isEmpty() || !matcher.matches()) {
            nameError.setText("your name must be valid and must has length between 6 and 30 char");
            return false;
        } else {
            nameError.setText("");
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
            passworderror.setText("at least contain one(digit,upp,lowerand \nspecial char)and no whitespace");
            return false;
        } else {
            passworderror.setText("");
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
            ipError.setText("IP address must be in the form (A.B.C.D),where \nthe value of A, B, C, and D may range from 0 to 255 \nand Leading zeros are allowed.");
            return false;
        } else {
            ipError.setText("");
            return true;
        }

    }

    void _connectServer(String[] userInf,String ip) {
        new Thread() {
            @Override
            public void run() {
                try {
                    client = new Socket(userInf[3], 5005);
                    dis = new DataInputStream(client.getInputStream());
                    ps = new PrintStream(client.getOutputStream());
                    String msg="R";
                    for (int i=0;i<userInf.length;i++) {
                      msg=msg+"##"+userInf[i];
                    }
                      ps.println(msg);
                        ps.flush();
                    boolean inComing = dis.readBoolean();
                    System.out.println(inComing);

                } catch (IOException ex) {

                    stop();
                    // Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
//                    _closing();
                }

            }

        }.start();
    }

    void _closing() {
        try {
            ps.close();
            dis.close();
            client.close();

        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
