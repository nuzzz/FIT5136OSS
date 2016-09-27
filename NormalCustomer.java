
/**
 * Write a description of class PremiumCustomer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalCustomer extends Customer
{
    public NormalCustomer(String username, String password, String name, String email, String address, String phoneNumber , String cardNumber){
        super(username, password, name, email, address, cardNumber, phoneNumber, false);
    }
}
