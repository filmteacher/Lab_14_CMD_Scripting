import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tom Wulf Tom.Wulf@uc.edu - for original JFileChooserDemo
 * @author Matt Bennett matt.bennett@uc.edu Lab 12
 */

//In the same IntelliJ project, create a java program called DataSaver.java and add it to GitHub src control.
//The program collects data from the user and saves it to a text file.
//Use the SafeInput library for getting the data.
//Write the data in the CSV record format.

public class DataSaver {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Initialize variables
        ArrayList<String> recs = new ArrayList<>();
        boolean confirm = true;
        Scanner in = new Scanner(System.in);
        String filename = "";

        do {
            //Test Data
            recs.add("Sample data for our file writing example.");
            recs.add("Sample data Line 2.");
            recs.add("Sample data Line 3.");
            recs.add("Sample data Line 4.");
            recs.add("Sample data Line 5.");

            //Data:
            //•	First Name
            //•	Last Name
            //•	ID Number (a zero replaced string of 6 digits 000001, 000002, etc.)
            //•	Email
            //•	Year of Birth (a four digit integer 1978, etc.)
            //
            //Here is a sample CSV record:
            //Bilbo, Baggins, 000001, BBaggins@shire.net, 1044
            //Loop the data input routine to allow the user to create as many records as they wish.
            //Save the CSV records initially into an arrayList of type <String>
            //and then once the user has completed the input of all the records
            //prompt for the file name (add the .csv extension)
            //and write the data into the csv file which should be in the src directory of the intelliJ project.
            //Run your program and create a data file record that has at least 3 CSV records.

            confirm = SafeInput.getYNConfirm(in, "Would you like to add another record?");

        } while (confirm);

        Path target = new File(System.getProperty("user.dir")).toPath();
        //(Code the JFileChooser to open in the src directory of the IntelliJ project.)
        target = target.resolve("src");
        Path fileLocation= getOutputPath(target.toString());

        // This was the syntx in the cookbook, couldn't get the CREATE syntax from the lecture to work
        try (BufferedWriter writer =
                     Files.newBufferedWriter(fileLocation, Charset.forName("UTF-8"))) {
            for (String rec : recs) {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line
            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        // This was also a little different in the cookbook - ex instead of e
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Path getOutputPath(String s) {
        JFileChooser jd = s == null ? new JFileChooser() : new JFileChooser(s);
        jd.setDialogTitle("Filename to save to:");
        int returnVal = jd.showSaveDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        else {
            return jd.getSelectedFile().toPath();
        }

    }

}
//Screenshots
//Paste a screenshot here that shows the Data Input.
//Paste a screenshot here that shows the saved file in the src directory of IntelliJ.
//Paste a screenshot here that shows the file opened in the IntelliJ editor.
//
//Criteria:
//Program uses safe input to loop and collect record data
//which is saved as csv into an arrayList and then written to a text file on disk.
//Screenshots of the input the file on the disk and the open file in the editor.