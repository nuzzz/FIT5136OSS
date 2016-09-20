import org.w3c.dom.Comment;
/**
 * Write a description of class Clothing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Clothing extends Product
{
    private String brand;
    private String colour;
    private String season;
    
    public Clothing(){
    }
    
    public Clothing(String id, String name, Float price, String imagePath){
        super(id, name, price, imagePath);
    }
    
    public Clothing(String brand, String colour, String season){
        super();
        this.brand = brand;
        this.colour = colour;
        this.season = season;
    }
    
    
    public Clothing(String id, String name, Float price, String imagePath, String brand, String colour, String season){
        super(id, name, price, imagePath);
        this.brand = brand;
        this.colour = colour;
        this.season = season;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public String getColour(){
        return colour;
    }
   
    public void setColour(String colour){
        this.colour = colour;
    }
    
    public String getSeason(){
        return season;
    }
    
    public void setSeason(String season){
        this.season = season;
    }
    
    public String toString(){
        return super.toString() + "brand: " + getBrand() + " " +  "colour: " + getColour() + " " + "season: " + getSeason() + " ";
    }
}
