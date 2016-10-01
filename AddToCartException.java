public class AddToCartException extends Exception {
    public AddToCartException(String message) {
        super("Add to cart failed: "+message);
    }
    
}
