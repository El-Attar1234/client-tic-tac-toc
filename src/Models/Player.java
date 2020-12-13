/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Mohamed
 */
public class Player {
    
    
    private int id;
    private String name;
    private String email;
    private String password;
    private int score;
    private int status;
    
    public Player()
    {
        
    }
    
    public Player(int id,String name,String email,String password,int score,int status)
    {
        this.id=id;
        this.email=email;
        this.name=name;
        this.score=score;
        this.status=status;
        
    } 
    public Player(String name,String email,String password,int score,int status)
    {
        this.email=email;
        this.name=name;
        this.score=score;
        this.status=status;
        
    }
public Player(String name,String email,int score)
    {
        this.email=email;
        this.name=name;
        this.score=score;
        this.status=status;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
