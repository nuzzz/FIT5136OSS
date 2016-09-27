import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Purchase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Purchase
{

    private int id; 
    /**
     * List of product and quantity */
    private ArrayList<CartItem> items = new ArrayList<>();
    private Date purchaseDate;
    private String buyer;
    
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public Purchase(){
    }
    
    public Purchase(int id, ArrayList<CartItem> items, String username){
        this.id = id;
        this.items = items;
        purchaseDate = new Date();
        this.buyer = username;
    }
    
    public Purchase(int id, ArrayList<CartItem> items, Date purchDate, String username){
		this.id = id;
        this.items = items;
        this.purchaseDate = purchDate;
        this.buyer = username;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
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
    
    public String toString(){
        String res = "[Purchase " + getId() + "]\n" ;
        res += "buyer: " + getBuyer() + "\n";
        res += "purchase date: " + dateFormat.format(getPurchaseDate()) + "\n" ;
        for(CartItem ci: getItems()){
            res+= "item: " + ci.getProduct().getName() + " quantity: " +  ci.getQuantity() + "\n";
        }
        
        res += "Purchase total: $" + totalForPurchase() + "\n"; 
        
        return res;
    }
    
    public float totalForPurchase(){
        float total = 0f;
        for(CartItem ci: getItems()){
            total += ci.getProduct().getPrice() * ci.getQuantity();
        }
        return total;
    }
}
