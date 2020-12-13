package test;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Menna
 */
public class Loginhandle {
    
    public void login(String jsonStr){
        
        try {
            JSONObject jsonObject = new JSONObject();
            
            jsonObject.put("TYPE", "LOGIN_REPLAY");
            jsonObject.put("KEY", "SUCCESS");           
            String jsonText = JSONValue.toJSONString(jsonObject);
                        
            Object obj = new JSONParser().parse(jsonText);
            JSONObject jo2 = (JSONObject) obj;
            
            String type = (String) jo2.get("Type");
            String key = (String) jo2.get("Key"); 
           
            if(key=="SUCCESS"){
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("Login Successfully");
                alert.showAndWait();
            }
            else if (key=="INCORRECT_PASSWORD")
            {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Erorr");
                alert.setContentText("The Passwprd you Entered is Wrong ");
                alert.showAndWait();
 
            }
            else if (key=="EMAIL_NOT_FOUND")
            {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Erorr");
                alert.setContentText("Email Not Found  \n May be you didn't Register or Wrote the email Incorrectly");
                alert.showAndWait();
 
            }
            } catch (ParseException ex) {
                Logger.getLogger(Loginhandle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
}
