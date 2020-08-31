/* Description: 
 * The following is the Utility class which is responsible for reading the csv file, parsing its contents,
 * and adding the student objects it detects to a list of students in the order that it reads them in.
 * After this, the students are sorted and made into pairs in the TeamBuilder class. Finally, the write 
 * results function is called in which the list with the new teams is written in a specific format to a
 * text file called results. 
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Utility {
	private String fullName, firstName, lastName;
	private String fullUIN, UIN;
	private String fullSection;
	private int section, javaKnowledge;
	private Rank rank;
	private Scanner infile;
	private PrintWriter outfile;
	private PrintWriter errfile;
	
	// this function reads in data from the csv and parses it
	public boolean readFile(){
		// try-catch implemented in the case that file is not found
		try {
			infile = new Scanner(new FileReader("./survey.csv"));
			errfile = new PrintWriter("./ErrorLog.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace(); // prints error(s)
			return false;
		}
		infile.nextLine(); // removes column header
		
		// while loop responsible for parsing each row and column from the csv
		while (infile.hasNextLine()) {
			String line = infile.nextLine(); // reads in the entire row from the csv, represents one student response
			String [] tokenizer = line.split(","); // splits up the students responses in tokenizer[]
			
			// javaKnowledge
			String javaKnowledge_S = tokenizer[1];
			javaKnowledge = Integer.parseInt(javaKnowledge_S);
			
			// if statement is responsible for circumventing the error in reading in an extra comma
			if (tokenizer[3].equals("\"No")) {
				// firstName, lastName are first acquired as part of the full name, then split
				fullName = tokenizer[5];
				String [] name_split = fullName.split(" ");
				firstName = name_split[0];
				lastName = name_split[1];

				// section is acquired with the time included
				fullSection = tokenizer[6];
				String [] section_split = fullSection.split(" "); // removes the time from the section column
				String section_S = section_split[0];
				section = Integer.parseInt(section_S);
				
				// UIN is acquired as part of an email address
				fullUIN = tokenizer[7];
				String [] UIN_split = fullUIN.split("@"); // removes the email address part from the UIN
				UIN = UIN_split[0];
			
				// rank is sent to the Rank enum to be parses so that it stores value as a Rank enum, not int rank
				String rank_S = tokenizer[8];
				rank = Rank.parseRank(rank_S);
			}
			else {
				// firstName, lastName are first acquired as part of the full name, then split
				fullName = tokenizer[4];
				String [] name_split = fullName.split(" ");
				firstName = name_split[0];
				lastName = name_split[1];

				// section is acquired with the time included
				fullSection = tokenizer[5];
				String [] section_split = fullSection.split(" "); // removes the time from the section column
				String section_S = section_split[0];
				section = Integer.parseInt(section_S);
				
				// UIN is acquired as part of an email address
				fullUIN = tokenizer[6];
				String [] UIN_split = fullUIN.split("@"); // removes the email address part from the UIN
				UIN = UIN_split[0];
				
				// rank is sent to the Rank enum to be parses so that it stores value as a Rank enum, not int rank
				String rank_S = tokenizer[7];
				rank = Rank.parseRank(rank_S);
			}
			// this checks to see if value of rank was valid, if not, it writes to the ErrorLog file
			if (rank == null) {
				errfile.println("**Error in Utility.readFile()** " + firstName + " " + lastName + " has invalid rank: " + tokenizer[7]);
			}
			
			TeamBuilder.set_All_sections(section); // this compiles a list of all possible sections
			// this creates a new student by calling the CSCE314 constructor with the parses values
			CSCE314Student new_student = new CSCE314Student(firstName, lastName, UIN, rank, javaKnowledge, section);
			TeamBuilder.set_Unsorted_students(new_student); // this adds the student to a list of unsorted CSCE314Student objects
	
		}
		infile.close();
		errfile.close();
		
		return true;
	}		
	
	// Writes the pairs to results.txt in the form: 502 - Bowen Lupoli (4) Jamila Booth (2)
	public boolean writeResults(){
		// try-catch implemented in the case that file is not found
		try
		{
			outfile = new PrintWriter("./results.txt");
		}
		catch (FileNotFoundException e1)
		{
			System.out.println("File not found");
			e1.printStackTrace(); // prints error(s)
			return false;
		}
		// runs a loop through the list of pairs creates and prints them
		for (int i = 0; i < TeamBuilder.getTeams().size(); i++) {	
			// if an empty pair is detected, it skips over it
			if (TeamBuilder.getTeams().get(i).size() == 0) {
				continue;
			}
			// teams of size 1 indicate a singleton, typically the most knowledgeable person who will serve as a substitute
			else if(TeamBuilder.getTeams().get(i).size() == 1) {
				outfile.print(TeamBuilder.getTeams().get(i).get(0).getSection() + " - ");
				outfile.println(TeamBuilder.getTeams().get(i).get(0).getFirstName() + " " + TeamBuilder.getTeams().get(i).get(0).getLastName() + " (" + TeamBuilder.getTeams().get(i).get(0).getJavaKnowledge() + ") ");

			}
			// teams of size 2 indicate a normally balanced team
			else {	
				outfile.print(TeamBuilder.getTeams().get(i).get(0).getSection() + " - ");
				outfile.print(TeamBuilder.getTeams().get(i).get(0).getFirstName() + " " + TeamBuilder.getTeams().get(i).get(0).getLastName() + " (" + TeamBuilder.getTeams().get(i).get(0).getJavaKnowledge() + ") ");
				outfile.println(TeamBuilder.getTeams().get(i).get(1).getFirstName() + " " + TeamBuilder.getTeams().get(i).get(1).getLastName() + " (" + TeamBuilder.getTeams().get(i).get(1).getJavaKnowledge() + ") ");
			}
		}
		outfile.close();
		return true;	
	}
}
