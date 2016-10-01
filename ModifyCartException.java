public class ModifyCartException extends Exception {
    public ModifyCartException(String message) {
        super("Modify cart failed: "+message);
    }
    
}
