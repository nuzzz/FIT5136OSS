import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * <pre>
 * This class represents a product in the store.
 * Each product must have a name and may contain an image.
 * 
 * Products may also contain properties that are set through the "setProperty(String, String, Object)" method.
 * Properties can be used to represent various bits of data about the product, some properties could be:
 * - Unit price
 * - Sale price
 * - Warranty information
 * - Sale "% off"
 *</pre>
 */
public class Product {

    private HashMap<String, Object> props = new HashMap<String, Object>();
    private HashMap<String, String> dispNames = new HashMap<String, String>();
    
    private String id;
    private String name;
    private Float price;
    private ImageIcon image = null;
    private String imagePath;
    
    public Product(){
    }

    /**
     * <pre>
     * Product constructor.
     * </pre>
     * @param name The name of the product
     */
    //public Product(String id, String name, String productImageString){
    public Product(String id, String name, Float price, String imagePath){
        this.name = name;
        this.id = id;
        this.price = price;
        this.imagePath = imagePath;
    }
    
    /**
     * <pre>
     * Sets the image to be used for the product.
     * If this method is never called, a default "no image" icon is used.
     * </pre>
     * @param url The URL of the image, it will be loaded from the internet and resized.
     */
    public void setImage(String url){
        this.image = ShopController.generateIcon(url, 150, 150);
    }
    
    /**
     * <pre>
     * Sets the name of this product.
     * </pre>
     * @param name The new product name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * <pre>
     * Returns the name of this product.
     * </pre>
     * @return The name of this product
     */
    public String getName(){
        return name;
    }
    
        /**
     * <pre>
     * Sets the id of this product.
     * </pre>
     * @param name The new product name
     */
    public void setId(String id){
        this.id = id;
    }
    
    /**
     * <pre>
     * Returns the id of this product.
     * </pre>
     * @return The id of this product
     */
    public String getId(){
        return id;
    }
    
    public float getPrice(){
        return price;
    }
    
    public void setPrice(float p){
        this.price = p;
    }
    
    public String getImagePath(){
        return this.imagePath;
    }
    
    public void setImagePath(String path){
        this.imagePath = path;
    }
    
    
    /**
     * <pre>
     * Sets a property of this product.
     * 
     * A property consists of an ID, a display name  and a value.
     * The ID is the name used to refer to the property via the "getPropertyValue(id)" method.
     * 
     * The display name and value is shown to the user in the form of "{display name}: {value}" on the product details page.
     * Each property is displayed to the user on it's own line and formatting is taken care of.
     * 
     * EG:
     *      This:          setProperty("unitPrice", "Unit price ($) ", 100f)
     *      Results in:   "Unit price ($): 100.0"
     * 
     * IMPORTANT: IF PROPERTIES ARE BEING USED TO CALCULATE PRICE, THEY MUST BE FLOATS AS THE "calculatePrice" METHOD IN "Model" EXPECTS FLOATS!
     * </pre>
     * @param id
     * @param displayName
     * @param value
     */
    public void setProperty(String pid, String displayName, Object value){
        this.props.put(pid, value);
        this.dispNames.put(pid, displayName);
    }
    
    /**
     * <pre>
     * Returns the value of the specified property.
     * IMPORTANT: MAKE SURE THAT YOU CAST THE VALUE TO THE TYPE YOU NEED!
     * </pre>
     * @param id The property to get the value of
     * @return The value of the property.
     */
    public Object getPropertyValue(String pid){
        return props.get(pid);
    }
    
    /**
     * <pre>
     * Returns the display name of the specified property.
     * </pre>
     * @param id The property
     * @return The display name of the property
     */
    public String getPropertyDisplayName(String pid){
        return dispNames.get(pid);
    }
    
    /**
     * <pre>
     * This method is used internally to generate the text seen in the "ProductDetails" dialog.
     * </pre>
     * @return A formatted string representation of all properties this product has.
     */
    public String getPropertiesAsText(){
        String out = "<html>";
        for(String key : this.props.keySet()) out += (getPropertyDisplayName(key) + ": " + getPropertyValue(key).toString()) + "<br/>";
        out += "</html>";
        return out;
    }
    
    /**
     * <pre>
     * Checks if this products contains a specific property.
     * </pre>
     * @param name The property ID
     * @return Returns true if this product contains the property, false otherwise.
     */
    public boolean hasProperty(String id){
        return props.containsKey(id);
    }
    
    /**
     * <pre>
     * Returns the image associated with this product.
     * </pre>
     * @return The image associated with this product
     */
    public ImageIcon getImage(){
        if(this.image == null) return ShopController.NO_IMAGE_ICON;
        else return this.image;
    }
    
    public String toString(){
        return "id: " + getId() + " " + "name: " + getName() + " " + "price: " + this.price + " " + "imagePath: " + this.imagePath + " ";
    }
}
