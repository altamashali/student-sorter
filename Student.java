/* Description: 
 * The following is a class which inherits properties from Person and is extended by the CSCE314Student class.
 * It provides the CSCE314Student object with the UIN and enum Rank properties. It also contains basic setter/getter
 * functions in order to access the private variables.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

public class Student extends Person {
	private String UIN;
	private Rank myRank;
	
	// constructor calls super for the Person class
	public Student(String firstName, String lastName, String UIN, Rank rank) {
		super(firstName, lastName);
		this.UIN = UIN;
		this.myRank = rank;
	}
	// getter functions for Student class
	public String getUIN() {
		return UIN;
	}
	public Rank getMyRank() {
		return myRank;
	}
	// setter functions for Student class
	public void setUIN(String uIN) {
		UIN = uIN;
	}
	public void setMyRank(Rank myRank) {
		this.myRank = myRank;
	}
	// toString function for Student class
	@Override
	public String toString() {
		return super.toString() + "UIN=" + UIN + ", myRank=" + myRank + ", ";
	}
}
