import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
public class ShopController {

    /*
     * STATIC FIELDS
     */
    
    /**
     * <pre>
     * The image root folder
     * </pre>
     */
    public static String IMAGE_FOLDER = "images/";
    
    /**
     * <pre>
     * The default product icon.
     * </pre>
     */
    //public static ImageIcon NO_IMAGE_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);
    public static String NO_IMAGE_PATH = "no_image.png";
    public static ImageIcon NO_IMAGE_ICON = generateIcon(NO_IMAGE_PATH, 150, 150);
    /**
     * <pre>
     * The store logo.
     * </pre>
     */
    public static String LOGO_PATH = "logo_final.png";
    public static ImageIcon LOGO_ICON = new ImageIcon(ShopController.class.getResource(IMAGE_FOLDER+LOGO_PATH));
    /**
     * <pre>
     * The image cache that is used to save time and speed up loading.
     * </pre>
     */
    public static HashMap<String, ImageIcon> IMAGE_CACHE;
    
    /**
     * <pre>
     * Generates an icon that can be used elsewere in the application.
     * </pre>
     * @param imgLoc The URL of the image
     * @param width The desired icon width
     * @param height The desired icon height
     * @return The generated icon
     */
    public static ImageIcon generateIcon(String imgLoc, int width, int height){
        if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
        if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
        try{
            String imagePath = IMAGE_FOLDER+imgLoc;
            ImageIcon icon = new ImageIcon((new ImageIcon(ShopController.class.getResource(imagePath))).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            IMAGE_CACHE.put(imgLoc, icon);
            return icon;
        } catch (Exception e) {
            System.out.println("Problem loading image: " + e);
        }
        return null;
    }
    
    
    /*
     * END STATIC
     */
    
    private JFrame window = new JFrame();
    private Model backend;
    private List<Product> searchProductList = new ArrayList<Product>(); 
    private List<Purchase> searchPurchaseList = new ArrayList<Purchase>();
    //current user details
    private Cart cart;
    //username of current user
    private String currentUser;
    private String searchQuery;
    
    /**
     * <pre>
     * Creates a new instance of ShopController.
     * Make sure to call the "init" method after this!
     * </pre>
     * @param b The Model with all of the back-end links that the store is to use
     */
    public ShopController(Model b){
        this.backend = b;
        cart = new Cart();
        currentUser = "";
        searchProductList = b.getProducts();
        //searchPurchaseList = b.getPurchases();
        window.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                try{
                    getBackend().saveDatabases();
                }
                catch (Exception e){
                    showPopup("Error saving databases: "+ e);
                }

                System.out.println("Window closing pressed");
            }
        });
    }
    
    /**
     * <pre>
     * Sets the store's current view after setting the view's controller and initializing it.
     * </pre>
     * @param view The view to set
     */
    public void setView(View view){
        view.setController(this);
        view.initialize();
        window.getContentPane().removeAll();
        window.repaint();
        window.revalidate();
        window.setContentPane(view);
        window.repaint();
        window.revalidate();
    }
    
    /**
     * @return The JFrame that holds the store.
     */
    public JFrame getWindow(){
        return window;
    }
    
    /**
     * @return The Model instance controlling the store.
     */
    public SimpleModel getBackend(){
        return (SimpleModel) this.backend;
    }
    
    /**
     * <pre>
     * Initialize and show the store window.
     * Also displays the LoginView.
     * </pre>
     */
    public void init(){
        window.setResizable(false);
        window.setTitle("Shop");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setView(new LoginView2());
    }
    
    /**
     * <pre>
     * Shows a popup message.
     * </pre>
     * @param message The text in the popup window.
     */
    public void showPopup(String message){
        JOptionPane.showMessageDialog(window, message);
    }
   
    
    /*
     * LOGIN AND USERS
     * ------------------------------------------------
     */

    /**
     * @return The customer details received from the Model instance.
     */
    public Customer getCurrentUserDetails(){
        return getBackend().getCustomerInfo(getCurrentUser());
    }
    
    public String getCurrentUser(){
        return this.currentUser;
    }
    
    public void setCurrentUser(String username){
        this.currentUser = username;
    }
    
    /**
     * <pre>
     * Attempts to sign up a user.
     * 
     * This will display an error to the user if any of the following are true:
     * - The user ID is less than 3 chars long
     * - The password is less than 5 chars long
     * - The two passwords do not match.
     * 
     * If all of the above tests succeed then the back-end will be asked to create a user.
     * If the back-end succeeds in creating an account, the user will be logged in, if not, they will be shown an error.
     * </pre>
     * @param username The entered user ID.
     * @param pass The entered password.
     * @param confPass The entered confirmed password.
     */
    public void signup(String name, String username, String pass, String confPass, String email, String address, String phone, String cardNumber){
        
        // Ensuring length
        if(username.length() < 3) {
            showPopup("Your user ID must be at least 3 chars long!");
            return;
        }
        else if(pass.length() < 5){
            showPopup("Your password must be at least 5 chars long!");
            return;
        }
        else if(!pass.equals(confPass)){
            showPopup("The passwords do not match");
            return;
        }
        else if(email.length() < 8){
            showPopup("Your email must be at least 8 chars long!");
            return;
        }
        else if(address.length() < 10){
            showPopup("Your address must be at least 10 chars long!");
            return;
        }
        else if(phone.length() < 10){
            showPopup("Your phone number must be at least 10 chars long!");
            return;
        }
        else if(cardNumber.length() < 12){
            showPopup("Your phone number must be at least 12 chars long!");
            return;
        }
        
        boolean success = getBackend().signup(name, username, pass, email, address, phone, cardNumber);
        
        if(!success){
            showPopup("Signup failed, that username may already be in use!");
        } else {
            showPopup("Your account has been created, please edit your details by clicking 'My account' in the top right.");
            this.setView(new LoginView());
            //attemptLogin(username, pass);
        }
    }
    
    /**
     * <pre>
     * Attempts to log a user into the store.
     * 
     * If the login succeeds, they will be presented with the product list, if not, they will be shown an error.
     * </pre>
     * @param username The supplied user ID
     * @param password The supplied password
     */
    public void attemptLogin(String username, String password){
        if(backend.login(username, password)){
            setCurrentUser(username);
            System.out.println("Current user is: " + username);
            showProductList();
        } else {
            showPopup("Login failed! Please ensure that your user ID and password are correct.");
        }
    }
    
    public void logout(){
        getCart().setItems(new ArrayList<CartItem>());
        setCurrentUser("");
        this.setView(new LoginView());
    }
    
    public boolean verifyUserDetails(String fullName, String phone, String addr, String email, String cardNumber){
        if(email.length() < 8){
            showPopup("Your email must be at least 8 chars long!");
            return false;
        }
        else if(addr.length() < 10){
            showPopup("Your address must be at least 10 chars long!");
            return false;
        }
        else if(phone.length() < 10){
            showPopup("Your phone number must be at least 10 chars long!");
            return false;
        }
        else if(cardNumber.length() < 12){
            showPopup("Your phone number must be at least 12 chars long!");
            return false;
        }
        return true;
    }
    
    public boolean verifyPassword(String oldpw, String newpw, String newpw2){
        String msg = "";
        if(getCurrentUserDetails().getPassword().equals(oldpw)){
            if (newpw2.equals(newpw)){
                return true;
            }else{
                msg+="New passwords don't match\n";
                showPopup(msg);
            }
        }else{
            msg+="Old password incorrect";
            showPopup(msg);
        }
        return false;
    }
    
    /**
     * <pre>
     * Calls the appropriate methods on the Model instance to update the information about the current user.
     * 
     * If no user is logged in, an error message will be displayed in the console.
     * </pre>
     * @param c The new user details.
     */
    public void updateUserDetails(Customer c){
        if(this.currentUser != null){
            boolean success = getBackend().setCustomerInfo(this.currentUser, c);
            if(success){
                 showPopup("User details updated successfully");
                 SimpleModel sm = (SimpleModel) getBackend();
                 for(User u: sm.getUsers()){
                     if (this.currentUser.equals(u.getUsername())){
                         System.out.println(u);
                     }
                 }
            }else{
                 showPopup("There was an error saving your information! Please try again later.");
            }
        } else {
            System.err.println("Can't update user info, no one is signed in!");
        }
    }

    
    /*
     * PRODUCTS
     * ------------------------------------------------
     */

    /**
     * <pre>
     * Shows the checkout dialog.
     * </pre>
     */
    public void showCheckout(){
        ConfirmCheckoutDialog.display(this);
    }
    
    /**
     * @return The current user's cart.
     */
    public Cart getCart(){
         return cart;
    }
    
    /**
     * <pre>
     * Adds a product to the cart. See "Cart.addToCart" for more information.
     * </pre>
     * @param p The product
     * @param quantity The quantity to add
     */
    public void addToCart(Product p, float qty){
        //fail if addedQty is negative or zero
        if(qty<=0){
            showPopup("Added quantity cannot be 0 or negative");
            return;
        }
        try{
            if(getBackend().addToCart(cart, p, qty)){
                cart.add(p, qty);
                showPopup("Added " + qty + " of " +  p.getName() + " to Cart");
            }else{
                showPopup("Failed to add to cart");
            }
        }catch(AddToCartException e){
            showPopup(e.getMessage());
            return;
        }
    }
    
    public void modifyCartQuantity(Product p, float qty){
        //fail if updateQty is negative
        if(qty<0){
            showPopup("Updated quantity cannot be negative");
            return;
        }
        else if(qty==0){
            showPopup("Item removed");
            return;
        }
        else{
            try{
                if(getBackend().modifyCartQuantity(cart, p, qty)){
                    cart.setQuantity(p, qty);
                    showPopup("Cart updated: " + qty + " of " +  p.getName() + " to Cart");
                }else{
                    showPopup("Failed to modify cart");
                }
            }catch(ModifyCartException e){
                showPopup(e.getMessage());
                return;
            }
        }
    }
    
    /**
     * <pre>
     * Shows the cart view.
     * </pre>
     */
    public void showCartView(){
        setView(new CartView());
    }
    
    /**
     * <pre>
     * See "Model.getPrice(Cart)" for more information.
     * </pre>
     * @return The total price of all item in the cart
     */
    public float getTotalCartPrice(){
        return getBackend().getPrice(cart);
    }

    /**
     * <pre>
     * Shows the product list view.
     * </pre>
     */
    public void showProductList() {
        setView(new ProductListView());
    }
    
    public void showPurchases(){
        setView(new PurchaseTableView());
    }
    
    public ArrayList<Product> searchProductList(String searchQuery){
        ArrayList<Product> results = new ArrayList<Product>();
        for(Product item: getBackend().getProducts()){
            if(item.getSearchTerms().contains(searchQuery)){
                results.add(item);
            }
        }
        return results;
    }
    
    public List<Product> getSearchProductList(){
        return this.searchProductList;
    }
    
    public void setSearchProductList(List<Product> newProductList){   
        this.searchProductList = newProductList;
    }
    
    public void resetSearchProductList(){
        this.searchProductList = getBackend().getProducts();
    }
    
    public void setSearchQuery(String s){
        this.searchQuery = s;
    }
    
    public String getSearchQuery(){
        return this.searchQuery;
    }

    
    
    /**
     * <pre>
     * Shows the product list view.
     * </pre>
     */
    public void showUseCredit() {
        CreditDialog.display(this);
    }
    
    /**
     * <pre>
     * Attempts a transaction using the current user's details and the current cart.
     * </pre>
     */
    public void attemptTransaction() {
        Customer c = (Customer) getBackend().getUserFromDB(getCurrentUser());
        if(c!=null){
            String prefix = "Order failed! ";
            if(c.getName().trim().equals("")){
                showPopup(prefix + "You have not entered your full name!");
                return;
            }
            else if(c.getAddress().trim().equals("")){
                showPopup(prefix + "You have not entered your home address!");
                return;
            }
            else if(c.getPhoneNumber().trim().equals("")){
                showPopup(prefix + "You have not entered your phone number!");
                return;
            }
            else if(c.getCardNumber().trim().equals("")){
                showPopup(prefix + "You have not entered your card number!");
                return;
            }
            else if(c.getEmail().trim().equals("")){
                showPopup(prefix + "You have not entered your email!");
                return;
            }
        
            boolean success = getBackend().processOrder(getCurrentUser(), getCart().getItems());
        
            if(!success){
                showPopup("Sorry, your order could not be placed! Please ensure that all of your information is correct.");
            }
            else {
                showPopup("Your order has been placed successfully! Have a nice day!");
                this.cart.clear();
                this.showCartView();
            }
        }
        else{
            showPopup("An error occured with transction");
            return;
        }
    }
}
