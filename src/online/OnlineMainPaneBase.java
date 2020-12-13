package online;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public  class OnlineMainPaneBase extends AnchorPane {

    protected final Pane pane;
    protected final Label onlineMainPaneTitle;
    protected final Button btn_onlineLogout;
    protected final VBox vBox;
    protected final ListView playerList;

    public OnlineMainPaneBase() {

        pane = new Pane();
        onlineMainPaneTitle = new Label();
        btn_onlineLogout = new Button();
        vBox = new VBox();
        playerList = new ListView();

        setId("AnchorPane");
        setPrefHeight(800.0);
        setPrefWidth(700.0);

        pane.setLayoutX(-1.0);
        pane.setLayoutY(1.0);
        pane.setPrefHeight(800.0);
        pane.setPrefWidth(700.0);

        onlineMainPaneTitle.setLayoutX(144.0);
        onlineMainPaneTitle.setLayoutY(48.0);
        onlineMainPaneTitle.setText("Plaese Select Player to play with him");
        onlineMainPaneTitle.setTextFill(javafx.scene.paint.Color.valueOf("#e41010"));
        onlineMainPaneTitle.setFont(new Font(25.0));

        btn_onlineLogout.setLayoutX(274.0);
        btn_onlineLogout.setLayoutY(719.0);
        btn_onlineLogout.setMnemonicParsing(false);
     //   btn_onlineLogout.setOnAction(this::handleLogoutAction);
        btn_onlineLogout.setPrefHeight(38.0);
        btn_onlineLogout.setPrefWidth(140.0);
        btn_onlineLogout.setText("Logout");

        vBox.setLayoutX(61.0);
        vBox.setLayoutY(97.0);
        vBox.setPrefHeight(599.0);
        vBox.setPrefWidth(575.0);

     //   playerList.setOnMouseClicked(this::handleSelectedPlayer);
        playerList.setPrefHeight(601.0);
        playerList.setPrefWidth(575.0);

        pane.getChildren().add(onlineMainPaneTitle);
        pane.getChildren().add(btn_onlineLogout);
        vBox.getChildren().add(playerList);
        pane.getChildren().add(vBox);
        getChildren().add(pane);

    }

   // protected abstract void handleLogoutAction(javafx.event.ActionEvent actionEvent);

//    protected abstract void handleSelectedPlayer(javafx.scene.input.MouseEvent mouseEvent);

}
