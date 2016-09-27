/**
 * <pre>
 * This class represents a User of the Online Shopping System.
 * It holds information about a potential user of the System.
 * </pre>
 */
public class User {
    private String name;
    private String username;
    private String password;
    
    public User(){
    }
    
    public User(String newUsername, String newPassword, String newName){
        this.username = newUsername;
        this.password = newPassword;
        this.name = newName;    
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String toString(){
        return "username: " + getUsername() + " " + "password: " + getPassword() + " " +  "name: " + getName() + " ";
    }
}