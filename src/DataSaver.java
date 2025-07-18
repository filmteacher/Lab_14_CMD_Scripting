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
        String firstName = "";
        String lastName = "";
        String idNumber = "";
        String email = "";
        String yearOfBirth = "";

        //Loop the data input routine to allow the user to create as many records as they wish.
        do {
            System.out.println("Enter your record data in this format");
            System.out.println("First Name");
            System.out.println("Last Name");
            System.out.println("ID Number as a zero replaced string of 6 digits (000001, 000002, etc.)");
            System.out.println("Email");
            System.out.println("Year of Birth as a four digit integer (1978, etc.)");

            firstName = SafeInput.getNonZeroLenString(in, "Enter a first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter a last name");
            idNumber = SafeInput.getNonZeroLenString(in, "Enter an ID number");
            email = SafeInput.getNonZeroLenString(in, "Enter an email");
            yearOfBirth = SafeInput.getNonZeroLenString(in, "Enter a year of birth");

            //Save the CSV records initially into an arrayList of type <String>
            recs.add(firstName + "," + lastName + "," + idNumber + "," + email + "," + yearOfBirth);

            //Here is a sample CSV record:
            //Bilbo, Baggins, 000001, BBaggins@shire.net, 1044

            confirm = SafeInput.getYNConfirm(in, "Would you like to add another record?");

        } while (confirm);

        //Once the user has completed the input of all the records,
        //prompt for the file name (add the .csv extension)
        //(Code the JFileChooser to open in the src directory of the IntelliJ project.)
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        Path fileLocation= getOutputPath(target.toString());

        //Write the data into the csv file which should be in the src directory of the intelliJ project.
        //This was the syntx in the cookbook, couldn't get the CREATE syntax from the lecture to work
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

//Run your program and create a data file record that has at least 3 CSV records.

//Criteria:
//Program uses safe input to loop and collect record data
//which is saved as csv into an arrayList and then written to a text file on disk.
//Screenshots of the input the file on the disk and the open file in the editor.