/* Description: 
 * The following is the Driver class which is responsible for the successful start and completion of the program.
 * Since we want to build teams, we simply create a TeamBuilder object which can then be used to access the utility
 * and comparator classes in addition to the sorting functions it already contains. Using the TeamBuilder object,
 * I call the Utility object in order to read my file, then I use the TeamBuilder object to sort students and make
 * pairs using two separate functions. Finally, once the teams have been make, I use the TeamBuilder object to call
 * the Utility object in order to write the results to a text file. The only output in the console is a boolean value
 * which displays whether or not the reading of and writing to a file was successful. Aside from this, a text file
 * called results will be created displaying the final output of the teams.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

public class Driver {

	public static void main(String[] args) {
		// Creates an object to begin the team building process
		TeamBuilder teamMaker = new TeamBuilder();
		
		// This uses the teamMaker object to call the read file function in the Utility class
		// This function is responsible for reading in and parsing students from the csv file
		// It also returns whether a file has been successfully detected or not
		System.out.print("File read: ");
		System.out.println(teamMaker.getUtil().readFile());
		
		// This sorts the students read in from the csv file
		teamMaker.sort_students();
		// This puts the students into balanced pairs
		teamMaker.make_pairs();
		
		// This uses the teamMaker object to call the write results function in the Utility class
		// This function is responsible for writing the pairs created to the results text file in a set format
		// It also returns whether a file has been successfully written to or not
		System.out.print("File written: ");
		System.out.println(teamMaker.getUtil().writeResults());
	}
}
