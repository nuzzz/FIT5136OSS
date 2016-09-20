import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;

import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleModel implements Model {
    ArrayList<User> userDB = new ArrayList<User>();
    ArrayList<Product> productDB = new ArrayList<Product>();
    ArrayList<Purchase> purchaseDB = new ArrayList<Purchase>();
    
    private final String clothingPath = "clothing.csv";
    private final String customersPath = "customers.csv";
    private final String purchasesPath = "purchases.csv";
    
    private int nextUserID;
    
    private final ArrayList<String> systemDatabaseList = new ArrayList<String>();
    
    public SimpleModel(){
        nextUserID = 0;
        systemDatabaseList.add(clothingPath);
        systemDatabaseList.add(customersPath);
        systemDatabaseList.add(purchasesPath);
        
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
    
    public List<Product> getProducts() {
        return productDB;
    }
    
    public Product getProductFromDB(String id){
        for(Product p: productDB){
            if(p.getId().equals(id))
                return p;
        }
        return new Product();
    }
    
    public User getUserFromDB(String id){
        for(User u: userDB){
            if(u.getId().equals(id))
                return u;
        }
        return new User();
    }
    
    public Customer getCustomerFromDB(String username){
        for(User u: userDB){
            if(u.getUsername().equals(username))
                return (Customer) u;
        }
        return new Customer();
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

        Customer newCustomer = new Customer(name, Integer.toString(getNextUserID()), username, password, email, address, phoneNumber, cardNumber);
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

    public Customer getUserInfo(String username) {
        return new Customer();
    }

    public boolean setUserInfo(String username, Customer info) {
//         details.put(username, info);
        return false;
    }

    public float getPrice(Cart cart) {
        float total = 0;
        for(CartItem item : cart.getList()) 
            total += ((float) item.getProduct().getPrice() * item.quantity);
        return total;
    }
    
    public int getNextUserID(){
        return this.nextUserID;
    }
    
    public void setNextUserID(int newID){
        this.nextUserID = newID;
    }

    public boolean processOrder(String currentUserID, Cart cart) {
        return true;
    }

    //Completed and tested
    public ArrayList<Product> loadClothingDB(String databasePath){
        ArrayList<Product> products = new ArrayList<Product>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(databasePath))) {
            List<List<String>> readLines= reader.lines()
                                    .map(line -> Arrays.asList(line.split(",")))
                                    .collect(Collectors.toList());
            for(List<String> line: readLines){
                //Clothing(String id, String name, Float price, String imagePath, String brand, String colour, String season){
                Product p = new Clothing(line.get(0),line.get(1), Float.parseFloat(line.get(2)),line.get(3),line.get(4),line.get(5),line.get(6));
                products.add(p);
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
                if(Integer.parseInt(line.get(0))>getNextUserID()){
                    setNextUserID(Integer.parseInt(line.get(0)));
                }
                
                
                User c = new Customer(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),line.get(6), line.get(7));
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
    
    //Partially completed untested.
    public ArrayList<Purchase> loadPurchaseDB(String databasePath) throws Exception{
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(databasePath))) {
            List<List<String>> readLines= reader.lines()
                                    .map(line -> Arrays.asList(line.split(",")))
                                    .collect(Collectors.toList());
            for(List<String> line: readLines){
                //date, buyer, id, qty, id2, qty2, id3, qty3
                if(line.size()<4 || line.size()%2!=0){
                    throw new Exception ("Purchase DB loaded incorrectly, not enough arguments");
                }
                //extract first two values, these are date and buyer, then handle the cartitems
                
                //extract date [07/06/2013] format
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date newDate = formatter.parse(line.get(0));
                
                //extract username
                User newUser = getUserFromDB(line.get(1));

                line.remove(1);
                line.remove(0);
                
                //extract cart items
                ArrayList<CartItem> items = new ArrayList<CartItem>();
                
                for(int i=0;i<line.size();i+=2){
                    Product newProduct = getProductFromDB(line.get(i));
                    float qty = Float.parseFloat(line.get(i+1));
                    CartItem newCartItem = new CartItem(newProduct, qty);
                    items.add(newCartItem);
                }
                Purchase purch = new Purchase(items, newDate, newUser.getUsername());
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
//         System.out.println(productDB.size());
//         System.out.println(userDB.size());
//         System.out.println(purchaseDB.size());
//         for(Product p: productDB){
//             System.out.println(p);
//         }
//         
//         for(User u: userDB){
//             System.out.println(u);
//         }
        return true;
    }
    
    //Not finished
    public boolean saveDatabases(){
//         for(String dbPath : systemDatabaseList){
//             if(existDB(dbPath))
//             {
//                switch(dbPath){
//                    case productPath:
//                        productDB = saveClothingDB(dbPath);
//                        break;
//                    case userPath:
//                        userDB = loadUserDB(dbPath);
//                        break;
//                    case purchasePath:
//                        purchaseDB = loadPurchaseDB(dbPath);
//                        break;
//                    default:
//                        break;
//                        //throw new Exception("Invalid database path found, program stopped");
//                }
//             }else{
//                System.out.println("Database does not exist: " + dbPath);
//                return false;
//             }
//         }
    return false;
    }
}
