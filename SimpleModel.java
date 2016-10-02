import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashSet;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleModel implements Model {
    public final boolean DEBUG = false;    
    
    private final String clothingPath = "clothing.csv";
    private final String customersPath = "customers.csv";
    private final String purchasesPath = "purchases.csv";
    private ArrayList<String> systemDatabaseList = new ArrayList<String>();
    
    private ArrayList<User> userDB = new ArrayList<User>();
    private ArrayList<Product> productDB = new ArrayList<Product>();
    private ArrayList<Purchase> purchaseDB = new ArrayList<Purchase>();
    private ArrayList<StockItem> storeStock = new ArrayList<StockItem>();
    
    private Set<String> brands;
    private Set<String> colours;
    private Set<String> genders;
    private Set<String> seasons;
    private int highestProductID;
    private int highestPurchaseID;
    
    public SimpleModel(){
        highestProductID = 0;
        highestPurchaseID = 0;
        systemDatabaseList.add(clothingPath);
        systemDatabaseList.add(customersPath);
        systemDatabaseList.add(purchasesPath);
        brands = new HashSet<String>();
        colours = new HashSet<String>();
        genders = new HashSet<String>();
        seasons = new HashSet<String>();
        
        
        try{
            loadDatabases();
        }
        catch (Exception e){
            System.out.println("Error occured: " + e);
            System.exit(0);
        }
        
        Administrator admin = new Administrator();
        userDB.add(admin);
    }
    
    public ArrayList<Product> getProducts() {
        return productDB;
    }
    
    public ArrayList<StockItem> getStoreStock(){
        return storeStock;
    }
    
    public float getProductStock(Product p){
        float stock = -1f;
        for(StockItem item : storeStock){
            if (p.getId().equals(item.getProduct().getId())){
                stock = item.getQuantity();
            }    
        }
        return stock;
    }
    
    public ArrayList<User> getUsers() {
        return userDB;
    }
    
    public ArrayList<Purchase> getPurchases(){
        return purchaseDB;
    }
    
    public Product getProductFromDB(String id){
        for(Product p: productDB){
            if(p.getId().equals(id))
                return p;
        }
        return new Product();
    }
    
    public User getUserFromDB(String username){
        for(User u: userDB){
            if(u.getUsername().equals(username))
                return u;
        }
        return null;
    }
    
    public boolean login(String username, String password) {
        //find user in userDB
        for(User u: userDB){
            //if this user is in the database, try logging in
            if(u.getUsername().equals(username)){
                if(u.getPassword().equals(password))
                    return true;

            }
        }
        return false;
    }

    public boolean signup(String name, String username, String password, String email, String address, String phoneNumber, String cardNumber) {
        Customer newCustomer = new Customer(username, password, name, email, address, phoneNumber, cardNumber);
        //Search user for database, check if the username is already used
        for(User u: userDB){
            //If customer exists in the database, return false, customer username already exists
            if(newCustomer.getUsername().equals(u.getUsername())){
                return false;
            }
        }
        userDB.add(newCustomer);
        return true;
    }

    public Customer getCustomerInfo(String username) {
        Customer c = new Customer();
        for(User u: userDB){
            if(username.equals(u.getUsername()))
            {
                return (Customer) u;
            }
        }
        return c;
    }

    public boolean setCustomerInfo(String username, Customer info) {
        for(User u: userDB){
            if(username.equals(u.getUsername())){
                userDB.remove(u);
                userDB.add(info);
                return true;
            }
        }
        return false;
    }
    
    public int getHighestProductID(){
        return this.highestProductID;
    }
    
    public int getNextProductID(){
        this.highestProductID=+1;
        return highestProductID;
    }
    
    public void setHighestProductID(int newID){
        this.highestProductID = newID;
    }
    
    public int getHighestPurchaseID(){
        return this.highestPurchaseID;
    }
    
    public int getNextPurchaseID(){
        highestPurchaseID+=1;
        return highestPurchaseID;
    }
    
    public void setHighestPurchaseID(int newID){
        this.highestPurchaseID = newID;
    }

    public float getPrice(Cart cart) {
        float total = 0;
        for(CartItem item : cart.getItems()) 
            total += ((float) item.getProduct().getPrice() * item.quantity);
        return total;
    }

    public boolean addItem(){
        return true;
    }
    
    public boolean processOrder(String currentUserID, ArrayList<CartItem> items) {
        //Purchase(int id, ArrayList<CartItem> items, String username)
        ArrayList<CartItem> cloneItems = new ArrayList<CartItem>();
        for(CartItem ci: items){
            cloneItems.add(ci);
        }
        
        for(CartItem ci2: cloneItems){
            for(StockItem si: getStoreStock()){
                if(ci2.getProduct().getId()==si.getProduct().getId()){
                    si.setQuantity(si.getQuantity()-ci2.getQuantity());
                }
            }
        }
        Purchase p = new Purchase(getNextPurchaseID(), cloneItems, currentUserID);
        purchaseDB.add(p);
        System.out.println(items);
        System.out.println(p.getItems());
        System.out.println(purchaseDB);
        return true;
    }
    
    public Set<String> getBrands(){
        return brands;
    }
    public Set<String> getColours(){
        return colours;
    }
    public Set<String> getGenders(){
        return genders;
    }
    public Set<String> getSeasons(){
        return seasons;
    }
    
    //Loading and saving db
    public boolean existDB(String databasePath){
        File tempFile = new File(databasePath);
        return tempFile.exists();
    }
    
    public boolean loadDatabases() throws Exception{
        for(String dbPath : systemDatabaseList){
            if(existDB(dbPath))
            {
               switch(dbPath){
                   case clothingPath:
                       productDB = loadClothingDB(dbPath);                      
                       break;
                   case customersPath:
                       userDB = loadCustomerDB(dbPath);        
                       break;
                   case purchasesPath:
                       purchaseDB = loadPurchaseDB(dbPath);
                       break;
                   default:
                       throw new Exception("Invalid database path found, program stopped");
               }
            }else{
               System.out.println("Database does not exist: " + dbPath);
               return false;
            }
        }
        //Databases have loaded successfully
        if(DEBUG){
            System.out.println("Products");
            System.out.println("Number of products: " + productDB.size());
            for(Product p: productDB){
                System.out.println(p);
            }
            System.out.println();
            
            System.out.println("Users");
            System.out.println("Number of users: " + userDB.size());
            for(User u: userDB){
                System.out.println(u);
            }
            System.out.println();
            
            System.out.println("Purchases");
            System.out.println("Number of purchases: " + purchaseDB.size());
            for(Purchase p: purchaseDB){
                System.out.println(p);
            }
            System.out.println();   
            
            System.out.println("Store Stock");
            System.out.println("Number of items in store: " + storeStock.size());
            for(StockItem p: storeStock){
                System.out.println(p);
            }
            System.out.println();   
        }
        return true;
    }
    
    //Completed
    public boolean saveDatabases() throws Exception{
        boolean productsSaved = false;
        boolean usersSaved = false;
        boolean purchasesSaved = false;
        
        for(String dbPath : systemDatabaseList){
            if(existDB(dbPath))
            {
               switch(dbPath){
                   case clothingPath:
                       productsSaved = saveProductDB(clothingPath, productDB);
                       break;
                   case customersPath:
                       usersSaved = saveUserDB(customersPath, userDB);
                       break;
                   case purchasesPath:
                       purchasesSaved = savePurchaseDB(purchasesPath, purchaseDB);
                       break;
                   default:
                       throw new Exception("Invalid database path found, program stopped");
               }
            }else{
               System.out.println("Database does not exist: " + dbPath);
               return false;
            }
        }
        return productsSaved && usersSaved && purchasesSaved;
    }

    //Completed and tested
    public ArrayList<Product> loadClothingDB(String databasePath){
        ArrayList<Product> products = new ArrayList<Product>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(databasePath))) {
            List<List<String>> readLines= reader.lines()
                                    .map(line -> Arrays.asList(line.split(",")))
                                    .collect(Collectors.toList());
                                    
            for(List<String> line: readLines){
                if(getHighestProductID()<Integer.parseInt(line.get(0))){
                    setHighestPurchaseID(Integer.parseInt(line.get(0)));
                }
                //Clothing(String id, String name, Float price, String imagePath, String brand, String colour, String season)
                Product p = new Clothing(line.get(0),line.get(1), Float.parseFloat(line.get(2)),line.get(3),line.get(4),line.get(5),line.get(6));
                brands.add(line.get(4));
                colours.add(line.get(5));
                seasons.add(line.get(6));
                //TODO waiting for gender to be implmented
                //genders.add(line.get(7));
                products.add(p);
                float qty = Float.parseFloat(line.get(7));
                StockItem stockItem = new StockItem(p, qty);
                storeStock.add(stockItem);
            }
        }
        catch (IOException e) {
            System.out.println("IO Exception Occured: "+ e);
        }
        catch (NumberFormatException e) {
            System.out.println("NumberFormat exception occured in database: "+ e); 
        }
        return products;
    }
    
    //Completed
    public ArrayList<User> loadCustomerDB(String databasePath){
        ArrayList<User> customers = new ArrayList<User>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(databasePath))) {
            List<List<String>> readLines= reader.lines()
                                    .map(line -> Arrays.asList(line.split(",")))
                                    .collect(Collectors.toList());
            for(List<String> line: readLines){
                //Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber, boolean premiumStatus)
                Customer c = new Customer(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5), line.get(6), line.get(7).equals("false"));
                customers.add(c); 
            } 
        }
        catch (IOException e) {
            System.out.println("IO Exception Occured: "+ e);
        }
        catch (NumberFormatException e) {
            System.out.println("NumberFormat exception occured in database: "+ e); 
        }
        return customers;
    }
    
    //Completed and tested.
    public ArrayList<Purchase> loadPurchaseDB(String databasePath) throws Exception{
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        int newId = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(databasePath))) {
            List<List<String>> readLines= reader.lines()
                                    .map(line -> Arrays.asList(line.split(",")))
                                    .collect(Collectors.toList());
            for(List<String> line: readLines){
                try{
                    newId = Integer.parseInt(line.get(0));
                }catch (NumberFormatException e){
                    System.out.println("Load purchase: Error parsing id number, " + line.get(0) + " not an integer");
                }

                if(getHighestPurchaseID()<newId){
                    setHighestPurchaseID(newId);
                }

                //purchid, date, buyer, id, qty, id2, qty2, id3, qty3, ...
                if(line.size()<5 || line.size()%2==0){
                    throw new Exception ("Purchase DB not loaded: " + databasePath + " is not in the correct format");
                }
                //extract first two values, these are date and buyer, then handle the cartitems

                //extract date [07/06/2013] format
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date newDate = formatter.parse(line.get(1));
                
                //extract username
                User newUser = getUserFromDB(line.get(2));
                
                //extract cart items
                ArrayList<CartItem> items = new ArrayList<CartItem>();
                
                //starting from 4th item of each line, create new cart items and add them to items list
                for(int i=3;i<line.size();i+=2){
                    Product newProduct = getProductFromDB(line.get(i));
                    float qty = Float.parseFloat(line.get(i+1));
                    CartItem newCartItem = new CartItem(newProduct, qty);
                    items.add(newCartItem);
                }
                //Purchase(int id, ArrayList<CartItem> items, Date purchDate, String username){    
                Purchase purch = new Purchase(newId, items, newDate, newUser.getUsername());
                purchases.add(purch);              
            }
        }
        catch (IOException e) {
            System.out.println("IO Exception Occured: "+ e);
        }
        catch (NumberFormatException e) {
            System.out.println("NumberFormat exception occured in database loading: "+ e); 
        }
        catch (ParseException e) {
            System.out.println("Parse Exception: " + e);
        }
        
        return purchases;
    }
    
    //Completed and tested
    public boolean saveUserDB(String databasePath, ArrayList<User> users){
        //userDB        
        ArrayList<String> lines = new ArrayList<String>();
        for(User u: users){
            String line = "";
            String comma = ",";
            if(!"admin".equals(u.getUsername())){
                Customer c = (Customer) u;
                //Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber, boolean premiumStatus)
                line += c.getUsername() + comma +  c.getPassword() + comma +  c.getName() + comma +  c.getEmail() + comma  +  c.getAddress() + comma
                             + c.getPhoneNumber() + comma  +  c.getCardNumber() + comma  +  c.getPremiumStatus();
                lines.add(line);
            }
        }
        try{
            Files.write(Paths.get(databasePath), lines);
        }catch (IOException e){
            System.out.println("Java IO exception occured");
            return false;
        }
        return true;
    }

    //Completed and tested
    public boolean saveProductDB(String databasePath, ArrayList<Product> products){
        //userDB        
        ArrayList<String> lines = new ArrayList<String>();
        for(StockItem item : getStoreStock()){
            String line = "";
            String comma = ",";
            Clothing c = (Clothing) item.getProduct();
            //Clothing(String id, String name, Float price, String imagePath, String brand, String colour, String season)
            line += c.getId() + comma +  c.getName() + comma + c.getPrice() + comma + c.getImagePath() + comma + c.getBrand() + comma + c.getColour() + comma + c.getSeason();
            //Quantity is the last attribute on this line
            line += comma + item.getQuantity();
            lines.add(line);
        }
//         for(Product p: products){
//             String line = "";
//             String comma = ",";
//             Clothing c = (Clothing) p;
//             //Clothing(String id, String name, Float price, String imagePath, String brand, String colour, String season)
//             line += c.getId() + comma +  c.getName() + comma + c.getPrice() + comma + c.getImagePath() + comma + c.getBrand() + comma + c.getColour() + comma + c.getSeason();
//             lines.add(line);
//         }
        try{
            Files.write(Paths.get(databasePath), lines);
        }catch (IOException e){
            System.out.println("Java IO exception occured");
            return false;
        }
        return true;
    }
    
    //Completed and tested
    public boolean savePurchaseDB(String databasePath, ArrayList<Purchase> purchases){
        ArrayList<String> lines = new ArrayList<String>();
        try{
            for(Purchase p: purchases){
                String line = "";
                String comma = ",";
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String newDate = formatter.format(p.getPurchaseDate());
                
                //purchid, date, buyer, id, qty, id2, qty2, id3, qty3, ...
                line += p.getId() + comma + newDate + comma + p.getBuyer();
                System.out.println("Before: " + line);
                for(CartItem cartitem : p.getItems()){
                    line += comma + cartitem.getProduct().getId() + comma + cartitem.getQuantity();
                }
                System.out.println("After: " + line);
                lines.add(line);
            }
            Files.write(Paths.get(databasePath), lines);
        }catch (IOException e){
            System.out.println("Java IO exception occured");
            return false;
        }catch (Exception e){
            System.out.println("Save Database error : " + e);
            return false;
        }
        return true;
    }
    // Loading and saving DB end
    
    //check quantity
    public boolean addToCart(Cart cart, Product p, float addedQty) throws AddToCartException{
        if(addedQty <= 0){
            return false;
        }
        //look for item in store stock
        for(StockItem item : getStoreStock()){
            //item found
            if(p.getId()==item.getProduct().getId()){
                //fail add if item stock = 0
                if(item.getQuantity()==0){
                    throw new AddToCartException("Item stock is 0");          
                }
                //if product not in cart
                if(!cart.inCart(p)){
                    //fail if added quantity is greater than stock
                    if(item.getQuantity()<addedQty){
                        throw new AddToCartException("Not enough stock. Stock left: "+ item.getQuantity());
                    }
                }
                //if product in cart
                if(cart.inCart(p)){
                    //fail add if item stock < cart.getQty(p)+addedQty 
                    if(item.getQuantity()<cart.getQuantity(p)+addedQty){
                        throw new AddToCartException("Not enough stock. Stock left: "+ (item.getQuantity()-cart.getQuantity(p)));
                    }
                }
            }
        }
        return true;
    }
    
    public boolean modifyCartQuantity(Cart cart, Product p, float updatedQty) throws ModifyCartException{
        //look for item in store stock
        for(StockItem item : getStoreStock()){
            //item found
            if(p.getId()==item.getProduct().getId()){
                //check stock and updated quantity amount
                if(item.getQuantity() < updatedQty){
                    throw new ModifyCartException("Not enough stock. Stock for this item: " + item.getQuantity());
                }
            }
        }
        return true;
    }
}
