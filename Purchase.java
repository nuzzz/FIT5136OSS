import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class Purchase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Purchase
{
    /**
     * List of product and quantity
     */
    private ArrayList<CartItem> items = new ArrayList<>();
    private Date purchaseDate;
    private String buyer;
    
    public Purchase(ArrayList<CartItem> items, String username){
        this.items = items;
        purchaseDate = new Date();
        this.buyer = username;
    }
    
        public Purchase(ArrayList<CartItem> items, Date purchDate, String username){
        this.items = items;
        this.purchaseDate = purchDate;
        this.buyer = username;
    }
    
    public ArrayList<CartItem> getItems(){
        return items;
    }
    
    public void setItems (ArrayList<CartItem> items){
        this.items = items;
    }
    
    public Date getPurchaseDate(){
        return purchaseDate;
    }
    
    public String getBuyer(){
        return buyer;
    }
    
    //TODO: This is not implemented yet
    public float totalForPurchase(){
        return -1f;
    }
}
