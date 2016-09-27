
/**
 * Write a description of class PremiumCustomer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PremiumCustomer extends Customer
{
    private int credit;
    
    public PremiumCustomer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber){
        super(username, password, name, email, address, phoneNumber, cardNumber, true);
        credit = 0;
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
