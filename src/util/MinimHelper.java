package util;

//This is a class to load the audio from the minim library, so it is easy to access the audio from the Burger Panel. A general class
import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

    public String sketchPath( String fileName ) {
        return "assets/"+fileName;
    }

    public InputStream createInput(String fileName) {
        InputStream is = null;
        try{
            is = new FileInputStream(sketchPath(fileName));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return is;
    }
}