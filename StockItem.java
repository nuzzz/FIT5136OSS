/**
 * <pre>
 * This class represents a group of a specific product in stock inside the online store.
 * 
 * For example, "5 paint cans" would be represented in the Store as a StockItem object with the product as "paint can" and the quantity as "5".
 * This system allows the cart to know the sub-total of each item.
 * </pre>
 */
public class StockItem {

    /**
     * <pre>
     * The product that is represented by this StockItem
     * </pre>
     */
    public Product product;
    
    /**
     * <pre>
     * The amount of the product that this StockItem represents
     * </pre>
     */
    public float quantity;

    public StockItem(Product product, float quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    /**
     * <pre>
     * Adds 1 to the current quantity.
     * </pre>
     */
    public void add1(){
        add(1);
    }
    
    /**
     * <pre>
     * Adds the desired amount to the quantity.
     * </pre>
     * @param quantity The quantity to add
     */
    public void add(float quantity){
        this.quantity += quantity;
    }
    
    public void setQuantity(float quantity){
        this.quantity = quantity;
    }
    
    
    public Product getProduct(){
        return this.product;
    }
    
    public float getQuantity(){
        return this.quantity;
    }
    
    public String toString(){
        return this.product.toString() + "quantity: " + this.quantity;
    }
}
