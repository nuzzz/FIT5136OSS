/**
 * <pre>
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * Whilst this information is not sufficient to place an order in real life, it is detailed enough to serve as a good learning example.
 * </pre>
 */
public class Customer extends User{

    /**
     * <pre>
     * The customer's email address.
     * </pre>
     */
    private String email;
    
    /**
     * <pre>
     * The customer's home address.
     * </pre>
     */
    private String address;
    
    /**
     * <pre>
     * The customer's card number.
     * </pre>
     */
    private String cardNumber;
    
    /**
     * <pre>
     * The customer's phone number.
     * </pre>
     */
    private String phoneNumber;
    
    private boolean premiumStatus;
        
    public Customer(){
        super();
    }
    
    public Customer(String username, String password, String name, String email, String address, String phoneNumber){
        super(username, password, name);
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
        premiumStatus = false;
    }
    
    public Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber){
        super(username, password, name);
        this.email = email;
        this.address = address;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.premiumStatus = false;
    }
    
    
    public Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber, boolean premiumStatus){
        super(username, password, name);
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.premiumStatus = premiumStatus;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getCardNumber(){
        return this.cardNumber;
    }
    
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public boolean getPremiumStatus(){
        return this.premiumStatus;
    }
    
    public void setPremiumStatus(boolean premiumStatus){
        this.premiumStatus = premiumStatus;
    }
    
    public String toString(){
        return super.toString() + "email: " + email + " " + "address: " + address + " " +  "cardNumber: " + cardNumber + " " +  "phoneNumber: " + phoneNumber + " " + "premiumStatus: " + premiumStatus + " ";
    }
}
