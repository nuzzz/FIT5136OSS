
/**
 * Write a description of class Photo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Photo
{
    private String path;
    
    Photo(String path){
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
    
    public void setPath(String path){
        this.path = path;
    }
}
