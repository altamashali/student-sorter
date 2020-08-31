/* Description: 
 * The following is an abstract class which is inherited by Student and CSCE314Student. It provides the 
 * CSCE314Student object with the first name and last name properties. It also contains basic setter/getter
 * functions in order to access the private variables.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

public abstract class Person {
	private String firstName;
	private String lastName;
	
	// base constructor which the super calls from Student and CSCE314Student resolve to
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	// getter functions for Person class
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	// setter functions for Person class
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// toString function for Person class
	@Override
	public String toString() {
		return "[firstName=" + firstName + ", lastName=" + lastName + ", ";
	}
}
