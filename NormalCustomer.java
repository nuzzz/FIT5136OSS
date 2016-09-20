
/**
 * Write a description of class PremiumCustomer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalCustomer extends Customer
{
    public NormalCustomer(String id, String name, String username, String password, 
                           String email, String address, String cardNumber, 
                           String phoneNumber){
        super(id, name, username, password, email, address, cardNumber, phoneNumber, false);
    }
}
