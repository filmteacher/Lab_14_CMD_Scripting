
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Tom Wulf Tom.Wulf@uc.edu - for original JFileChooserDemo
 * @author Matt Bennett matt.bennett@uc.edu Lab 14
 */

public class FileScan {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try // Code that might trigger the exception goes here
        {
            //Initialize variables
            Scanner inFile = null;
            String fileName = "";
            String[] words = new String[0];
            String line;
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            //The main code will now check to see if there are any cmd line arguments
            if (args.length > 0) 
            {
                //which if present should be the name of a text file in the current directory to scan
                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve("src");
                String filePath = target + File.separator + args[0];
                target = new File(filePath).toPath();
                inFile = new Scanner(target);
                fileName = target.getFileName().toString();
            } 
            else
            {
                //If no argument is present, the program should run as before and launch the JFileChooser to allow the user to interactively pick the file to be scanned.
                JFileChooser chooser = new JFileChooser();

                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve("src");
                chooser.setCurrentDirectory(target.toFile());

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    target = chooser.getSelectedFile().toPath();  // this is a File object, not a String filename
                    inFile = new Scanner(target);
                    fileName = target.getFileName().toString();
                }
                // User did not pick a file, closed the chooser
                else
                {
                    System.out.println("Sorry, you must select a file! Terminating!");
                    System.exit(0);
                }
            }

            while (inFile.hasNextLine()) 
            {
                //The program should then open and read the file line by line
                line = inFile.nextLine();

                //echo the lines to the screen
                System.out.println(line);

                //Read each line one at a time keeping track of how many lines you read.
                lineCount++;

                //As you read each line, count how many words there are.
                //You can use the split function to return an array of the words, and the length of the array is how many words there are.
                words = line.split(" ");
                wordCount += words.length;

                //Finally, as you read each line, use the String length property to count how many characters are in the line and keep track of the total length of the file.
                charCount += line.length();
            }

            //Then, the program should print a summary report of the file to the screen.
            //The name of the file the user chose to process
            System.out.printf("File: %s\n", fileName);

            //Number of lines in the file
            System.out.println("Number of lines: " + lineCount);

            //Number of words in the file
            System.out.println("Number of words: " + wordCount);

            //Number of characters in the file
            System.out.println("Number of characters: " + charCount);

            inFile.close();
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