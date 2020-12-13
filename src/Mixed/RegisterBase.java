package Mixed;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegisterBase extends AnchorPane {

    String _nameText, _emailText, _passwordtext, _passwordConfText;// _ipText;
    boolean _isName, _isEmail, _isPassword, _isPasscon;// _isIp;
     public static String ip="";
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
    protected final StackPane stackPane;
    protected final TextField passTextField;
    protected final PasswordField passPasswordField;
    protected final StackPane stackPane0;
    protected final TextField confTextField;
    protected final PasswordField confPasswordField;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final TextField nameField;
    protected final TextField emailField;
    protected final Label nameError;
    protected final Label emailError;
    protected final Label passwordError;
    protected final Button btnBack;
    protected final Button btnRegister;
    protected final Label label3;
    protected final Label confPassWordError;
    //  protected final Label label4;
    //  protected final TextField ipField;
    //protected final Label ipError;
    protected final CheckBox checkBoxId1;
    protected final CheckBox checkBoxId2;

    public RegisterBase() {

        pane = new Pane();
        stackPane = new StackPane();
        passTextField = new TextField();
        passPasswordField = new PasswordField();
        stackPane0 = new StackPane();
        confTextField = new TextField();
        confPasswordField = new PasswordField();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        nameField = new TextField();
        emailField = new TextField();
        nameError = new Label();
        emailError = new Label();
        passwordError = new Label();
        btnBack = new Button();
        btnRegister = new Button();
        label3 = new Label();
        confPassWordError = new Label();
        //  label4 = new Label();
        //   ipField = new TextField();
        //   ipError = new Label();
        checkBoxId1 = new CheckBox();
        checkBoxId2 = new CheckBox();

        setId("AnchorPane");
        setPrefHeight(800.0);
        setPrefWidth(700.0);

        pane.setPrefHeight(800.0);
        pane.setPrefWidth(700.0);

        stackPane.setLayoutX(277.0);
        stackPane.setLayoutY(337.0);
        stackPane.setPrefHeight(38.0);
        stackPane.setPrefWidth(395.0);

        passTextField.setPrefHeight(40.0);
        passTextField.setPrefWidth(395.0);

        passPasswordField.setPrefHeight(37.0);
        passPasswordField.setPrefWidth(395.0);

        stackPane0.setLayoutX(277.0);
        stackPane0.setLayoutY(460.0);
        stackPane0.setPrefHeight(38.0);
        stackPane0.setPrefWidth(395.0);

        confTextField.setPrefHeight(41.0);
        confTextField.setPrefWidth(395.0);

        confPasswordField.setPrefHeight(38.0);
        confPasswordField.setPrefWidth(395.0);

        label.setLayoutX(258.0);
        label.setLayoutY(37.0);
        label.setPrefHeight(40.0);
        label.setPrefWidth(229.0);
        label.setText("Sign Up Form");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#eb0c0c"));
        label.setFont(new Font(28.0));

        label0.setLayoutX(70.0);
        label0.setLayoutY(120.0);
        label0.setPrefHeight(30.0);
        label0.setPrefWidth(200.0);
        label0.setText("Enter Your Name");
        label0.setFont(new Font(20.0));

        label1.setLayoutX(70.0);
        label1.setLayoutY(230.0);
        label1.setPrefHeight(30.0);
        label1.setPrefWidth(200.0);
        label1.setText("Enter Your Email");
        label1.setFont(new Font(20.0));

        label2.setLayoutX(70.0);
        label2.setLayoutY(345.0);
        label2.setPrefHeight(30.0);
        label2.setPrefWidth(200.0);
        label2.setText("Enter Your Password");
        label2.setFont(new Font(20.0));

        nameField.setLayoutX(279.0);
        nameField.setLayoutY(116.0);
        nameField.setPrefHeight(38.0);
        nameField.setPrefWidth(395.0);

        emailField.setLayoutX(279.0);
        emailField.setLayoutY(226.0);
        emailField.setPrefHeight(38.0);
        emailField.setPrefWidth(395.0);

        nameError.setLayoutX(279.0);
        nameError.setLayoutY(165.0);
        nameError.setPrefHeight(26.0);
        nameError.setPrefWidth(395.0);
        nameError.setTextFill(javafx.scene.paint.Color.valueOf("#e11515"));
        nameError.setFont(new Font(15.0));

        emailError.setLayoutX(279.0);
        emailError.setLayoutY(278.0);
        emailError.setPrefHeight(26.0);
        emailError.setPrefWidth(395.0);
        emailError.setTextFill(javafx.scene.paint.Color.valueOf("#da1414"));
        emailError.setFont(new Font(15.0));

        passwordError.setLayoutX(279.0);
        passwordError.setLayoutY(393.0);
        passwordError.setPrefHeight(26.0);
        passwordError.setPrefWidth(395.0);
        passwordError.setTextFill(javafx.scene.paint.Color.valueOf("#e11414"));
        passwordError.setFont(new Font(15.0));

        btnBack.setLayoutX(413.0);
        btnBack.setLayoutY(703.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(36.0);
        btnBack.setPrefWidth(208.0);
        btnBack.setText("Back");
        btnBack.setFont(new Font(24.0));

        btnRegister.setLayoutX(90.0);
        btnRegister.setLayoutY(703.0);
        btnRegister.setMnemonicParsing(false);
        btnRegister.setPrefHeight(36.0);
        btnRegister.setPrefWidth(208.0);
        btnRegister.setText("Register");
        btnRegister.setFont(new Font(24.0));

        label3.setLayoutX(68.0);
        label3.setLayoutY(461.0);
        label3.setPrefHeight(30.0);
        label3.setPrefWidth(200.0);
        label3.setText("Confirm The Password");
        label3.setFont(new Font(20.0));

        confPassWordError.setLayoutX(278.0);
        confPassWordError.setLayoutY(509.0);
        confPassWordError.setPrefHeight(26.0);
        confPassWordError.setPrefWidth(395.0);
        confPassWordError.setTextFill(javafx.scene.paint.Color.valueOf("#e11414"));
        confPassWordError.setFont(new Font(15.0));
//
//        label4.setLayoutX(70.0);
//        label4.setLayoutY(564.0);
//        label4.setPrefHeight(30.0);
//        label4.setPrefWidth(200.0);
//        label4.setText("Server IP");
//        label4.setFont(new Font(20.0));
//
//        ipField.setLayoutX(278.0);
//        ipField.setLayoutY(561.0);
//        ipField.setPrefHeight(38.0);
//        ipField.setPrefWidth(395.0);
//
//        ipError.setLayoutX(279.0);
//        ipError.setLayoutY(612.0);
//        ipError.setPrefHeight(26.0);
//        ipError.setPrefWidth(395.0);
//        ipError.setTextFill(javafx.scene.paint.Color.valueOf("#e11414"));
//        ipError.setFont(new Font(15.0));

        checkBoxId1.setLayoutX(570.0);
        checkBoxId1.setLayoutY(389.0);
        checkBoxId1.setMnemonicParsing(false);
        checkBoxId1.setPrefHeight(26.0);
        checkBoxId1.setPrefWidth(140.0);
        checkBoxId1.setText("password");

        checkBoxId2.setLayoutX(570.0);
        checkBoxId2.setLayoutY(509.0);
        checkBoxId2.setMnemonicParsing(false);
        checkBoxId2.setPrefHeight(26.0);
        checkBoxId2.setPrefWidth(140.0);
        checkBoxId2.setText("password");

        stackPane.getChildren().add(passTextField);
        stackPane.getChildren().add(passPasswordField);
        pane.getChildren().add(stackPane);
        stackPane0.getChildren().add(confTextField);
        stackPane0.getChildren().add(confPasswordField);
        pane.getChildren().add(stackPane0);
        getChildren().add(pane);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(nameField);
        getChildren().add(emailField);
        getChildren().add(nameError);
        getChildren().add(emailError);
        getChildren().add(passwordError);
        getChildren().add(btnBack);
        getChildren().add(btnRegister);
        getChildren().add(label3);
        getChildren().add(confPassWordError);
        // getChildren().add(label4);
//        getChildren().add(ipField);
//        getChildren().add(ipError);
        getChildren().add(checkBoxId1);
        getChildren().add(checkBoxId2);

        checkBoxId1.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            String temp;
            if (checkBoxId1.isSelected()) {
                temp = passPasswordField.getText();
                passTextField.setText(temp);
                passTextField.toFront();
            } else {
                temp = passTextField.getText();
                passPasswordField.setText(passTextField.getText());
                passPasswordField.toFront();
            }
            // _passwordtext=temp;

        }));
        checkBoxId2.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (checkBoxId2.isSelected()) {
                confTextField.setText(confPasswordField.getText());
                confTextField.toFront();
            } else {
                confPasswordField.setText(confTextField.getText());
                confPasswordField.toFront();
            }

        }));

        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _getData();
                // _isIp = _validateIp(_ipText);
                _isName = _validateName(_nameText);
                _isEmail = _validateEmail(_emailText);
                _isPassword = _validatePassword(_passwordtext);

                _isPasscon = _confPass(_passwordConfText);

                if (!_isName || !_isEmail || !_isPassword || !_isPasscon) {
                } else {

                    _connectServer(_nameText, _emailText, _passwordtext);
                    //_key = "SUCCESS";

                }

            }

        });
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Manager.viewPane(Manager.login);
                _clear();

            }

        });

    }

    void _clear() {
        nameField.clear();
        nameError.setText("");
        emailField.clear();
        emailError.setText("");
        passPasswordField.clear();
        passwordError.setText("");
        confPasswordField.clear();
        confPassWordError.setText("");
//        ipField.clear();
//        ipError.setText("");
    }

    void _getData() {
        _nameText = nameField.getText();
        _emailText = emailField.getText();
        _passwordtext = !checkBoxId1.isSelected() ? passPasswordField.getText() : passTextField.getText();
        _passwordConfText = !checkBoxId2.isSelected() ? confPasswordField.getText() : confTextField.getText();
////        _ipText = ipField.getText();

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

        if (pass.isEmpty() || pass.trim().length() < 8) {
            passwordError.setText("password must be greater than 8 char");
            return false;
        } else {
            passwordError.setText("");
            return true;
        }

    }

    /* boolean _validateIp(String ip) {
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

    }*/
    boolean _confPass(String pass) {

        if (!pass.equals(_passwordtext)) {
            confPassWordError.setText("two password must be identical");
            return false;
        } else {
            confPassWordError.setText("");
            return true;
        }

    }

    void _connectServer(String name, String email, String password) {
        new Thread() {
            @Override
            public void run() {
                try {

                     // client = new Socket("127.0.0.1", 5005);
                       dis=ClientSocket.dis;
                       ps=ClientSocket.ps;
//                    dis = new DataInputStream(ClientSocket.getInstance().getInputStream());
//                    ps = new PrintStream(ClientSocket.getInstance().getOutputStream());
                    jsonObject = new JSONObject();
                    jsonObject.put("TYPE", "REGISTER");
                    jsonObject.put("NAME", name);
                    jsonObject.put("EMAIL", email);
                    jsonObject.put("PASSWORD", password);
                    jsonText = JSONValue.toJSONString(jsonObject);
                    System.out.println(jsonText);
                    ps.println(jsonText);
                    ps.flush();
                    inComing = dis.readLine();
                    obj = new JSONParser().parse(inComing);
                    jo2 = (JSONObject) obj;
                    _type = (String) jo2.get("TYPE");
                    _key = (String) jo2.get("KEY");

                    System.out.println(inComing);
                    System.out.println(_type);
                    System.out.println(_key);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (_key) {
                                case "SUCCESS":
                                    Alert alretSuccess = new Alert(Alert.AlertType.NONE, "you are successfully registered", ButtonType.OK);
                                    alretSuccess.setTitle("Succes");
                                    alretSuccess.showAndWait();
                                    Manager.viewPane(Manager.login);
                                    _clear();
                                    System.out.println("Moved");
                                    break;
                                case "EMAIL_IS_USED_BEFORE":
                                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                                    alertError.setTitle("Error ");
                                    alertError.setHeaderText("Email is already registered");
                                    alertError.showAndWait();
                                    break;

                            }
                        }
                    });
                } catch (IOException ex) {

                    stop();
                    // Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(RegisterBase.class.getName()).log(Level.SEVERE, null, ex);
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
            // client.close();

        } catch (IOException ex) {
            Logger.getLogger(Signup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
