/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Mohamed
 */
public class ClientInstance {
   

    public static Socket client = null;
    private static DataInputStream ds;
    private static PrintStream ps;  
    
    public ClientInstance() throws IOException {
        
        client = new Socket("127.0.0.1", 5005);
        ds = new DataInputStream(client.getInputStream());
        ps = new PrintStream(client.getOutputStream());
    }

    public static ClientInstance getInstance() throws IOException {
        if (client == null) {
           new ClientInstance();
        }
        return new ClientInstance();
    }
    public DataInputStream getDataInputStream()
    {
        return ds;
    }
    
    
    public PrintStream getPrintStream()
    {
        return ps;
    }
   
}