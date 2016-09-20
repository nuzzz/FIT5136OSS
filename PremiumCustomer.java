
/**
 * Write a description of class PremiumCustomer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PremiumCustomer extends Customer
{
    private int credit;
    
    public PremiumCustomer(String id, String name, String username, String password, 
                           String email, String address, String cardNumber, 
                           String phoneNumber){
        super(id, name, username, password, email, address, cardNumber, phoneNumber, true);
    }
    
    public void addCredit(int amount){
        this.credit+=amount;
    }
    
    public void setCredit(int amount){
        this.credit=amount;
    }
   
    public void clearCredit(){
        this.credit=0;
    }
}
