
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML SharedController class
 *
 * @author Menna
 */
public class SinglePlayerGameController implements Initializable {

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

    private String theWinnerName = "";
    private String player1Seq = "";
    private String pcSeq = "";

    private Button[] boardButtons = new Button[3 * 3];

    private boolean isGameEnds;

    private boolean isFirstPlayerTurn = true;
    private boolean isDraw = false;

    private int XOCounter = 0;

    private Random random = new Random();
    private int randomNumber;

    private final int MAX_DEPTH = 6;
    private final int MAX_DEPTH_com = 12;

    EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
        actionPerformed(e);
    };

    @FXML
    private void handlBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayer.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);

        stage.show();
    }

    @FXML
    private void handleNewGameAction(ActionEvent event) throws IOException {

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
        if (!anyMovesAvailable() && isGameEnds == false) {
            isGameEnds = true;
            isDraw = true;

        }
        //isDraw=false;

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

        storeRecords();
        theWinnerName = null;
        isGameEnds = false;
        isDraw = false;
        setCurrentPlayerSymbol();

        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color: none; -fx-cursor: hand;");
        }
        if(SharedController.minMaxAlgorithm==true)
        {
                randomNumber = getBestMovecom(boardButtons);
           if (boardButtons[randomNumber].getText().equals("")) {
               boardButtons[randomNumber].setTextFill(Color.RED);
               boardButtons[randomNumber].setText("O");
               
           }
        }
        

    }

    private void setCurrentPlayerSymbol() {

        if (isFirstPlayerTurn == true) {
            currentPlayerSymbol.setText("X");
            currentPlayerSymbol.setTextFill(Color.BLUE);
        } else {
            currentPlayerSymbol.setText("X");
            currentPlayerSymbol.setTextFill(Color.BLUE);
        }

    }

    private void storeRecords() {
        String theFirstPlayer;
        if (isFirstPlayerTurn) {
            theFirstPlayer = firstPlayerName.getText();
        } else {
            theFirstPlayer = secondPlayerName.getText();
        }

        //Database.getInstance().insert(firstPlayerName.getText(),secondPlayerName.getText(),
        //   player1Seq,player2Seq,theFirstPlayer,theWinnerName);
        // store Data into database 
        // if success
        player1Seq = "";
        pcSeq = "";
        theWinnerName = "";
    }

    private void getTheWinnerName() {
        if (isDraw) {
            Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
            showWinner.setTitle("Game Results");
            showWinner.setHeaderText(null);
            showWinner.setContentText("Tie!!");
            showWinner.initModality(Modality.APPLICATION_MODAL);
            //options.initOwner(this);

            ButtonType btnOk = new ButtonType("ok");

            showWinner.getButtonTypes().setAll(btnOk);

            Optional<ButtonType> choices = showWinner.showAndWait();
            if (choices.get() == btnOk) {
            }
        } else {
            Alert showWinner = new Alert(Alert.AlertType.INFORMATION);
            showWinner.setTitle("Game Results");
            showWinner.setHeaderText(null);
            showWinner.setContentText("The Winner Is " + theWinnerName);
            showWinner.initModality(Modality.APPLICATION_MODAL);
            //options.initOwner(this);

            ButtonType btnOk = new ButtonType("ok");

            showWinner.getButtonTypes().setAll(btnOk);

            Optional<ButtonType> choices = showWinner.showAndWait();
            if (choices.get() == btnOk) {
            }
        }
    }

    private void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();

        if (isGameEnds == false && clickedButton.getText().equals("")) 
        {
            if (SharedController.minMaxAlgorithm == false) 
            {
                XOCounter++;
                isFirstPlayerTurn = true;
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

                checkIfGameEnds();

                if (isGameEnds == false) {
                    for (Button boardButton : boardButtons) {
                        boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
                    }

                    XOCounter++;
                    isFirstPlayerTurn = false;
                    /////////////////////////////////////////////
                    for (;;) {
                        randomNumber = random.nextInt(9);
                        if (boardButtons[randomNumber].getText().equals("")) 
                        {
                            boardButtons[randomNumber].setTextFill(Color.RED);
                            boardButtons[randomNumber].setText("O");
                            

                            if (SharedController.allowRecord == true) {
                                if (randomNumber == 0) {
                                    pcSeq = pcSeq.concat("0");
                                }
                                if (randomNumber == 1) {
                                    pcSeq = pcSeq.concat("1");
                                }
                                if (randomNumber == 2) {
                                    pcSeq = pcSeq.concat("2");
                                }
                                if (randomNumber == 3) {
                                    pcSeq = pcSeq.concat("3");
                                }
                                if (randomNumber == 4) {
                                    pcSeq = pcSeq.concat("4");
                                }
                                if (randomNumber == 5) {
                                    pcSeq = pcSeq.concat("5");
                                }
                                if (randomNumber == 6) {
                                    pcSeq = pcSeq.concat("6");
                                }
                                if (randomNumber == 7) {
                                    pcSeq = pcSeq.concat("7");
                                }
                                if (randomNumber == 8) {
                                    pcSeq = pcSeq.concat("8");
                                }

                                System.out.println("player2Seq " + pcSeq);

                            }
                            break;
                        }
                    }
                    checkIfGameEnds();

                    for (Button boardButton : boardButtons) {
                        boardButton.addEventHandler(ActionEvent.ACTION, eventHandler);
                    }
                }
            }

            if (SharedController.minMaxAlgorithm == true) {
                System.out.println("minmaxalgorithm");
                XOCounter++;
                isFirstPlayerTurn = true;
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

                checkIfGameEnds();

                if (isGameEnds == false) {
                    for (Button boardButton : boardButtons) {
                        boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
                    }

                    XOCounter++;
                    isFirstPlayerTurn = false;

                    int point = getBestMovecom(boardButtons);
                    boardButtons[point].setTextFill(Color.RED);
                    boardButtons[point].setText("O");

                    if (SharedController.allowRecord == true) {
                        if (point == 0) {
                            pcSeq = pcSeq.concat("0");
                        }
                        if (point == 1) {
                            pcSeq = pcSeq.concat("1");
                        }
                        if (point == 2) {
                            pcSeq = pcSeq.concat("2");
                        }
                        if (point == 3) {
                            pcSeq = pcSeq.concat("3");
                        }
                        if (point == 4) {
                            pcSeq = pcSeq.concat("4");
                        }
                        if (point == 5) {
                            pcSeq = pcSeq.concat("5");
                        }
                        if (point == 6) {
                            pcSeq = pcSeq.concat("6");
                        }
                        if (point == 7) {
                            pcSeq = pcSeq.concat("7");
                        }
                        if (point == 8) {
                            pcSeq = pcSeq.concat("8");
                        }

                        System.out.println("pcSeq " + pcSeq);

                    }

                    checkIfGameEnds();

                    for (Button boardButton : boardButtons) {
                        boardButton.addEventHandler(ActionEvent.ACTION, eventHandler);
                    }
                }
            }

        }
    }

    public int minimax(Button[] board, int depth, boolean isMaximizing) {
        int boardVal = evaluateboard();
        if (Math.abs(boardVal) == 10 || depth == 0
                || anyMovesAvailable()) {
            return boardVal;
        }

        if (isMaximizing) {
            int highestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (boardButtons[i].getText().equals("")) {
                    boardButtons[i].setTextFill(Color.RED);
                    boardButtons[i].setText("X");
                    highestVal = Math.max(highestVal, minimax(board, depth - 1, false));
                    boardButtons[i].setText("");
                }
            }
            return highestVal;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (boardButtons[i].getText().equals("")) {
                    boardButtons[i].setTextFill(Color.BLUE);
                    boardButtons[i].setText("O");
                    lowestVal = Math.min(lowestVal, minimax(board, depth - 1, true));
                    boardButtons[i].setText("");

                }
                
                
                
                
                
            }
            return lowestVal;
        }

    }

    /**
     * Evaluate every legal move on the board and return the best one.
     *
     * @param board Board to evaluate
     * @return place of best move
     */
    public int getBestMove(Button[] board) {
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < 9; i++) {

            if (boardButtons[i].getText().equals("")) {
                boardButtons[i].setTextFill(Color.BLUE);
                boardButtons[i].setText("X");
                int moveValue = minimax(board, MAX_DEPTH, false);
                boardButtons[i].setText("");
                if (moveValue > bestValue) {
                    bestMove = i;
                    bestValue = moveValue;
                }
            }

        }
        return bestMove;
    }

    private int evaluateboard() {

        String t00 = boardButtons[0].getText();
        String t01 = boardButtons[1].getText();
        String t02 = boardButtons[2].getText();
        String t10 = boardButtons[3].getText();
        String t11 = boardButtons[4].getText();
        String t12 = boardButtons[5].getText();
        String t20 = boardButtons[6].getText();
        String t21 = boardButtons[7].getText();
        String t22 = boardButtons[8].getText();
        String[][] temp = new String[][]{{t00, t01, t02}, {t10, t11, t12}, {t01, t21, t22}};
        String rowSum = "";
        String Xwin = "XXX";
        String Owin = "OOO";
        // Check rows for winner.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                rowSum += temp[row][col];
            }
            if (rowSum.equals(Xwin)) {
                return 10;
            } else if (rowSum.equals(Owin)) {
                return -10;
            }
            rowSum = "";
        }
        // Check columns for winner.
        rowSum = "";
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                rowSum += temp[row][col];
            }
            if (rowSum.equals(Xwin)) {
                return 10;
            } else if (rowSum.equals(Owin)) {
                return -10;
            }
            rowSum = "";
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSum = "";
        for (int i = 0; i < 3; i++) {
            rowSum += temp[i][i];
        }
        if (rowSum.equals(Xwin)) {
            return 10;
        } else if (rowSum.equals(Owin)) {
            return -10;
        }

        // Top-right to bottom-left diagonal.
        rowSum = "";
        rowSum = temp[0][2] + temp[0][1] + temp[2][0];

        if (rowSum.equals(Xwin)) {
            return 10;
        } else if (rowSum.equals(Owin)) {
            return -10;
        }

        return 0;
    }

    public boolean anyMovesAvailable() {

        for (int i = 0; i < 9; i++) {
            if (boardButtons[i].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    public int minimaxcom(Button[] board, int depth, int alpha, int beta, boolean isMaximizing) {
        int boardVal = evaluateboardcom(depth);
        if (Math.abs(boardVal) == 10 || depth == 0
                || anyMovesAvailable()) {
            return boardVal;
        }

        if (isMaximizing) {
            int highestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (boardButtons[i].getText().equals("")) {
                    boardButtons[i].setTextFill(Color.RED);
                    boardButtons[i].setText("X");
                    highestVal = Math.max(highestVal, minimaxcom(board,
                            depth - 1, alpha, beta, false));
                    boardButtons[i].setText("");
                    alpha = Math.max(alpha, highestVal);
                    if (alpha >= beta) {
                        return highestVal;
                    }
                }
            }
            return highestVal;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (boardButtons[i].getText().equals("")) {
                    boardButtons[i].setTextFill(Color.BLUE);
                    boardButtons[i].setText("O");
                    lowestVal = Math.min(lowestVal, minimaxcom(board,
                            depth - 1, alpha, beta, true));
                    boardButtons[i].setText("");
                    beta = Math.min(beta, lowestVal);
                    if (beta <= alpha) {
                        return lowestVal;
                    }

                }
            }
            return lowestVal;
        }

    }

    /**
     * Evaluate every legal move on the board and return the best one.
     *
     * @param board Board to evaluate
     * @return place of best move
     */
    public int getBestMovecom(Button[] board) {
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < 9; i++) {

            if (boardButtons[i].getText().equals("")) {
                boardButtons[i].setTextFill(Color.BLUE);
                boardButtons[i].setText("X");
                int moveValue = minimaxcom(board, MAX_DEPTH_com, Integer.MIN_VALUE,
                        Integer.MAX_VALUE, false);
                boardButtons[i].setText("");
                if (moveValue > bestValue) {
                    bestMove = i;
                    bestValue = moveValue;
                }
            }

        }
        return bestMove;
    }

    private int evaluateboardcom(int depth) {

        String t00 = boardButtons[0].getText();
        String t01 = boardButtons[1].getText();
        String t02 = boardButtons[2].getText();
        String t10 = boardButtons[3].getText();
        String t11 = boardButtons[4].getText();
        String t12 = boardButtons[5].getText();
        String t20 = boardButtons[6].getText();
        String t21 = boardButtons[7].getText();
        String t22 = boardButtons[8].getText();
        String[][] temp = new String[][]{{t00, t01, t02}, {t10, t11, t12}, {t01, t21, t22}};
        String rowSum = "";
        String Xwin = "XXX";
        String Owin = "OOO";
        // Check rows for winner.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                rowSum += temp[row][col];
            }
            if (rowSum.equals(Xwin)) {
                return 10 + depth;
            } else if (rowSum.equals(Owin)) {
                return -10 - depth;
            }
            rowSum = "";
        }
        // Check columns for winner.
        rowSum = "";
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                rowSum += temp[row][col];
            }
            if (rowSum.equals(Xwin)) {
                return 10 + depth;
            } else if (rowSum.equals(Owin)) {
                return -10 - depth;
            }
            rowSum = "";
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSum = "";
        for (int i = 0; i < 3; i++) {
            rowSum += temp[i][i];
        }
        if (rowSum.equals(Xwin)) {
            return 10 + depth;
        } else if (rowSum.equals(Owin)) {
            return -10 - depth;
        }

        // Top-right to bottom-left diagonal.
        rowSum = "";
        rowSum = temp[0][2] + temp[0][1] + temp[2][0];

        if (rowSum.equals(Xwin)) {
            return 10 + depth;
        } else if (rowSum.equals(Owin)) {
            return -10 - depth;
        }

        return 0;
    }

}
