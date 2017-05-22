package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anthony Emerson on 5/16/2017.
 */
public class PoemToFile {

    private String fileName;
    private File newFile;

    public void savePoem(String text, int poemNum) throws FileNotFoundException {
        if(poemNum < 10){
            fileName = ("Poem0" + poemNum + ".txt");
        }
        else {
            fileName = ("Poem" + poemNum + ".txt");
        }
        newFile = new File("src/main/resources/TestPoems/"+fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter out = new PrintWriter(newFile)){
            out.println( text );
        }
    }
}
