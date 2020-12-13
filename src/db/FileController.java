
package db;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

                       
/**
 *
 * @author Menna
 */
public class FileController {
    
    private static FileOutputStream fos;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private static FileInputStream fis;
    
    private static FileWriter file;
    private static File fi;
   
    public FileController(){
        
    }  
    private static void createFile()
    {
        try {        
            fos=new FileOutputStream("Myfile.txt");
            fi=new File("Myfile.txt");
            try {
                file=new FileWriter(fi);
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void write(String str)
    {
        
        try {
            if(fos == null){
                createFile();
            }
            //dos=new DataOutputStream(fos);
            //dos.writeUTF(str);
            file.append(str);
            file.close();
           
            System.out.println("write is done");
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void read()
    {
        try {
            fis=new FileInputStream("Myfile.txt");
            dis=new DataInputStream(fis);
            
            while(dis.available()>0){
                System.out.println(dis.readUTF());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static void close()
    {
//        try {
//            dis.close();
//        } catch (IOException ex) {
//            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
