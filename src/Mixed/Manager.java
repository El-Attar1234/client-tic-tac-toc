/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mixed;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import online.OnlineMainPaneBase;
import online.OnlineMainPaneController;


public class Manager {
      public static LoginBase login = new LoginBase();
      public static RegisterBase signup = new  RegisterBase();
      public static IPCheckBase ipscreen = new  IPCheckBase();
        public static OnlineMainPaneBase  online= new OnlineMainPaneBase();
     
     public static void viewPane(Pane pane)
    {
        login.setVisible(false);
        signup.setVisible(false);
        ipscreen.setVisible(false);
        online.setVisible(false);
        pane.setVisible(true);
    }
    
}
