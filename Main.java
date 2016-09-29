
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;

public class Main {

    public static void main(String[] args){
         
        Model shopModel = new SimpleModel();
        
        ShopController c = new ShopController(shopModel);
        c.init();

    }
    
}
