
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Tom Wulf Tom.Wulf@uc.edu - for original JFileChooserDemo
 * @author Matt Bennett matt.bennett@uc.edu Lab 12
 */

public class FileInspector {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Initialize variables
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        String[] words = null;

                //Create a java program FileInspector.java that uses the JFileChooser dialog to let the user specify a text file from the system.
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        //(Code the JFileChooser to open in the src directory of the IntelliJ project.)
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try // Code that might trigger the exception goes here
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);

                while(inFile.hasNextLine())
                {
                    //The program should then open and read the file line by line
                    line = inFile.nextLine();

                    //echo the lines to the screen
                    System.out.println(line);

                    //Read each line one at a time keeping track of how many lines you read.
                    lineCount++;

                    //As you read each line, count how many words there are.
                    //You can use the split function to return an array of the words and the length of the array is how many words there are.
                    String[] words = line.split(" ")
                    wordCount += words.length();

                    //Finally, as you read each line use the String length property to count how many characters are in the line and keep track of the total length of the file.
                    charCount += line.length();

                    //Then, the program should print a summary report of the file to the screen.
                    //The name of the file the user chose to process
                    System.out.printf("File: %s\n", target.getFileName());
                    //Number of lines in the file
                    System.out.println( "Number of lines: " + lineCount);
                    //Number of words in the file
                    System.out.println( "Number of words: " + wordCount);
                    //Number of characters in the file
                    System.out.println( "Number of characters: " + charCount);
                }

                inFile.close();
            }
            // User did not pick a file, closed the chooser
            else
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) // code to handle this exception
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}

//Screenshots
//Paste a screenshot here that shows the JFileChooser running.
//Paste 2 screenshots here that show your output stats for 2 successful program runs on different files.
//
//Criteria:
//Program uses JFileChooser to allow the user to pick a file.
//It reads the file, echoing it to the screen and then displays
//the file info number of lines,
//number of words,
//and number of characters