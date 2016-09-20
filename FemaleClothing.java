
/**
 * Write a description of class FemaleClothing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FemaleClothing extends Clothing
{
    private String size;
   private String[] sizeList = {"8","10","12","14","16","18","20"};
   
   public FemaleClothing(){
       super();
   }
   
   public FemaleClothing(String size){
       super();
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
