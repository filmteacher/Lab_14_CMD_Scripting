public class FileInspector {
}



//Create a java program FileInspector.java that uses the JFileChooser dialog to let the user specify a text file from the system.
//(Code the JFileChooser to open in the src directory of the IntelliJ project.)
//The program should then open and read the file line by line and echo the lines to the screen.
//Then, the program should print a summary report of the file to the screen.
//
// The summary report should contain the following:
//•	The name of the file the user chose to process
//•	Number of lines in the file
//•	Number of words in the file
//•	Number of characters in the file
//
//Suggested process:
//1.	Open the file after the user specifies it with JFileChooser. Read each line one at a time keeping track of how many lines you read.
//2.	As you read each line, count how many words there are. You can use the split function to return an array of the words and the length of the array is how many words there are.
//3.	Finally, as you read each line use the String length property to count how many characters are in the line and keep track of the total length of the file.
//
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