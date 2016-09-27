
/**
 * Write a description of class MaleClothing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaleClothing extends Clothing
{
   private String size;
   private String[] sizeList = {"S","M","L","XL"};
   
   public MaleClothing(){
       super();
   }
   
   public MaleClothing(String id, String name, Float price, String imagePath, String brand, String colour, String season, String size){
       super(id, name, price, imagePath, brand, colour, season);
       this.size = size;
   }
   
   public String[] getSizeList(){
       return sizeList;
   }
   
   public String getSize(){
       return this.size;
   }
   
   public void setSize(String size){
       this.size = size;
   }
}
