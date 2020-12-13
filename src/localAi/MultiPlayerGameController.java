package localAi;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import db.Database;
import db.FileController;


/**
 * FXML SharedController class
 *
 * @author Mohamed
 */
public class MultiPlayerGameController implements Initializable {

    @FXML
    private Label firstPlayerName;
    @FXML
    private Label secondPlayerName;
    @FXML
    private Label firstPlayerScore;
    @FXML
    private Label secondPlayerScore;
    @FXML
    private Label currentPlayerSymbol;

    @FXML
    private GridPane boardPane;
    @FXML
    private Button back;
    @FXML
    private Button newGame;

    private static String theWinnerName = "null";
    private String player1Seq = "";
    private String player2Seq = "";
    
    private String theFirstPlayer;

    private Button[] boardButtons = new Button[3 * 3];

    private boolean isGameEnds;

    private boolean isFirstPlayerTurn = true;

    private int XOCounter = 0;

    private Random random = new Random();
    private int randomNumber;

    EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
        actionPerformed(e);
    };

    @FXML
    private void handlBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MultiPlayer.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();
    }

    @FXML
    private void handleNewGameAction(ActionEvent event) throws IOException {

        player1Seq = "";
        player2Seq = "";
        theWinnerName = "null";
        theFirstPlayer="";
        startNewGame();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstPlayerName.setText(SharedController.firstPlayerName);
        secondPlayerName.setText(SharedController.secondPlayerName);

        firstPlayerScore.setText("0");
        secondPlayerScore.setText("0");
        createGameBoard();
        startNewGame();

    }

    private void colorBackgroundWinnerButtons(Button b1, Button b2, Button b3) {
        b1.setStyle("-fx-background-color: Green;");
        b2.setStyle("-fx-background-color: Green;");
        b3.setStyle("-fx-background-color: Green;");
    }

    private void createGameBoard() {

        int row = 0;
        int column = 0;

        for (int i = 0; i < boardButtons.length; i++) {

            boardButtons[i] = new Button();

            boardButtons[i].setPrefSize(165, 160);

            boardButtons[i].setFont(Font.font("Arial", FontWeight.BOLD, 50));

            boardPane.add(boardButtons[i], column, row);

            boardButtons[i].addEventHandler(ActionEvent.ACTION, e -> {
                actionPerformed(e);
            });

            column++;
            if (column == 3) {
                row++;
                column = 0;
            }
        }

    }

    private void checkIfGameEnds() {

        String t00 = boardButtons[0].getText();
        String t01 = boardButtons[1].getText();
        String t02 = boardButtons[2].getText();
        String t10 = boardButtons[3].getText();
        String t11 = boardButtons[4].getText();
        String t12 = boardButtons[5].getText();
        String t20 = boardButtons[6].getText();
        String t21 = boardButtons[7].getText();
        String t22 = boardButtons[8].getText();

        if (t00.equals(t01) && t00.equals(t02) && !t00.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[0], boardButtons[1], boardButtons[2]);
        }

        if (t10.equals(t11) && t10.equals(t12) && !t10.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[3], boardButtons[4], boardButtons[5]);
        }

        if (t20.equals(t21) && t20.equals(t22) && !t20.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[6], boardButtons[7], boardButtons[8]);
        }

        if (t00.equals(t10) && t00.equals(t20) && !t00.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[0], boardButtons[3], boardButtons[6]);
        }

        if (t01.equals(t11) && t01.equals(t21) && !t01.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[1], boardButtons[4], boardButtons[7]);
        }

        if (t02.equals(t12) && t02.equals(t22) && !t02.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[2], boardButtons[5], boardButtons[8]);
        }

        if (t00.equals(t11) && t00.equals(t22) && !t00.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[0], boardButtons[4], boardButtons[8]);
        }

        if (t02.equals(t11) && t02.equals(t20) && !t02.equals("")) {
            isGameEnds = true;
            storeRecords();
            colorBackgroundWinnerButtons(boardButtons[2], boardButtons[4], boardButtons[6]);
        }

        if (XOCounter >= 9) {
            
            storeRecords();
            isGameEnds = true;
            isFirstPlayerTurn = true;
            XOCounter = 0;
            
        }

        if (isGameEnds == true) {
            if (isFirstPlayerTurn) {
                firstPlayerScore.setText(Integer.valueOf(firstPlayerScore.getText()) + 1 + "");
                theWinnerName = firstPlayerName.getText();
                getTheWinnerName();
            } else {
                secondPlayerScore.setText(Integer.valueOf(secondPlayerScore.getText()) + 1 + "");
                theWinnerName = secondPlayerName.getText();
                getTheWinnerName();
            }
            

            XOCounter = 0;
            newGame.requestFocus();
        }

    }

    private void startNewGame() {

        
        theWinnerName = "draw";
        isGameEnds = false;
        setCurrentPlayerSymbol();
        

        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color: none; -fx-cursor: hand;");
        }
        
                

    }

    private void setCurrentPlayerSymbol() {

        if (isFirstPlayerTurn == true) {
            currentPlayerSymbol.setText("X");
            currentPlayerSymbol.setTextFill(Color.BLUE);
        } else {
            currentPlayerSymbol.setText("O");
            currentPlayerSymbol.setTextFill(Color.RED);
        }

    }

    private void storeRecords() {
        
        
        if (isFirstPlayerTurn) {
            theFirstPlayer = firstPlayerName.getText();
        } else {
            theFirstPlayer = secondPlayerName.getText();
        }
        

        FileController.write(firstPlayerName.getText()+" "+secondPlayerName.getText()+" "
                +player1Seq+" "+player2Seq+" "+theWinnerName+" "+theFirstPlayer);
       
        //Database.getInstance().insert(firstPlayerName.getText(),secondPlayerName.getText(),
        //   player1Seq,player2Seq,theFirstPlayer,theWinnerName);
        // store Data into database 
        // if success
        player1Seq = "";
        player2Seq = "";
        theWinnerName = "null";
        theFirstPlayer="";
    }

    private void getTheWinnerName() {

        Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
        showWinner.setTitle("Game Results");
        showWinner.setHeaderText(null);
        showWinner.setContentText("The Winner Is " + theWinnerName);
        showWinner.initModality(Modality.APPLICATION_MODAL);

        ButtonType btnOk = new ButtonType("ok");

        showWinner.getButtonTypes().setAll(btnOk);

        Optional<ButtonType> choices = showWinner.showAndWait();
        if (choices.get() == btnOk) {
        }
    }

    private void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();

        if (isGameEnds == false && clickedButton.getText().equals("")) {

            if (isFirstPlayerTurn) {
                clickedButton.setTextFill(Color.BLUE);
                clickedButton.setText("X");

                if (SharedController.allowRecord == true) {
                    if (clickedButton == boardButtons[0]) {
                        player1Seq = player1Seq.concat("0");
                    }
                    if (clickedButton == boardButtons[1]) {
                        player1Seq = player1Seq.concat("1");
                    }
                    if (clickedButton == boardButtons[2]) {
                        player1Seq = player1Seq.concat("2");
                    }
                    if (clickedButton == boardButtons[3]) {
                        player1Seq = player1Seq.concat("3");
                    }
                    if (clickedButton == boardButtons[4]) {
                        player1Seq = player1Seq.concat("4");
                    }
                    if (clickedButton == boardButtons[5]) {
                        player1Seq = player1Seq.concat("5");
                    }
                    if (clickedButton == boardButtons[6]) {
                        player1Seq = player1Seq.concat("6");
                    }
                    if (clickedButton == boardButtons[7]) {
                        player1Seq = player1Seq.concat("7");
                    }
                    if (clickedButton == boardButtons[8]) {
                        player1Seq = player1Seq.concat("8");
                    }

                    System.out.println("player1Seq " + player1Seq);

                }

            } else {
                clickedButton.setTextFill(Color.RED);
                clickedButton.setText("O");

                if (SharedController.allowRecord == true) {
                    if (clickedButton == boardButtons[0]) {
                        player2Seq = player2Seq.concat("0");
                    }
                    if (clickedButton == boardButtons[1]) {
                        player2Seq = player2Seq.concat("1");
                    }
                    if (clickedButton == boardButtons[2]) {
                        player2Seq = player2Seq.concat("2");
                    }
                    if (clickedButton == boardButtons[3]) {
                        player2Seq = player2Seq.concat("3");
                    }
                    if (clickedButton == boardButtons[4]) {
                        player2Seq = player2Seq.concat("4");
                    }
                    if (clickedButton == boardButtons[5]) {
                        player2Seq = player2Seq.concat("5");
                    }
                    if (clickedButton == boardButtons[6]) {
                        player2Seq = player2Seq.concat("6");
                    }
                    if (clickedButton == boardButtons[7]) {
                        player2Seq = player2Seq.concat("7");
                    }
                    if (clickedButton == boardButtons[8]) {
                        player2Seq = player2Seq.concat("8");
                    }

                    System.out.println("player2Seq " + player2Seq);

                }
            }

            checkIfGameEnds();
            setCurrentPlayerSymbol();
            isFirstPlayerTurn = !isFirstPlayerTurn;
            setCurrentPlayerSymbol();

        }
    }

}
