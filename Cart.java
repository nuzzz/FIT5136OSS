import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * This class represents a shopping cart, the place where the selected items are held before they are purchased.
 * </pre>
 */
public class Cart {

    private ArrayList<CartItem> items = new ArrayList<>();
    
    /**
     * <pre>
     * Adds a certain quantity of a certain product to the cart.
     * </pre>
     * @param p The product to add
     * @param quantity The quantity of the product to add
     */
    public void add(Product p, float quantity){
        for(CartItem ci : items){
            if(ci.product.equals(p)){
                ci.quantity += quantity;
                return;
            }
        }
        add(new CartItem(p, quantity));
    }
    
    
    /**
     * <pre>
     * Adds a pre-formatted CartItem to the cart.
     * Preferably use add(Product, float) as that method performs checks to prevent duplicate items.
     * </pre>
     * @param item The CartItem to add
     */
    public void add(CartItem item){
        items.add(item);
    }
    
    /**
     * <pre>
     * Returns a list of all CartItems.
     * </pre>
     * @return The list of CartItems
     */
    public ArrayList<CartItem> getItems(){
        return items;
    }
    
    /**
     * <pre>
     * Removes all items from this cart.
     * </pre>
     */
    public void clear(){
        items.clear();
    }

    /**
     * <pre>
     * Replaces the list of items with a different one.
     * </pre>
     * @param items The new list of items.
     */
    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }
    
    /**
     * <pre>
     * Removes a specific item from the cart.
     * </pre>
     * @param item The item to remove
     */
    public void remove(CartItem item){
        items.remove(item);
    }
    
    public float getQuantity(Product p){
        float result = -1f;
        for(CartItem item: getItems()){
            if (p.getId().equals(item.getProduct().getId())){
                result = item.getQuantity();
            }
        }
        return result;
    }
    
    public void setQuantity(Product p, float qty){
        for(CartItem item: getItems()){
            if (p.getId().equals(item.getProduct().getId())){
                item.setQuantity(qty);
            }
        }
    }
    
    public boolean inCart(Product p){
        for(CartItem item: getItems()){
            if (p.getId().equals(item.getProduct().getId())){
                return true;
            }
        }
        return false;
    }
    
}
