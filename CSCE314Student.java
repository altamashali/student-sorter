/* Description:
 * The following is the CSCE314Student class which creates the objects which we are going to sort. It provides
 * the student with two final properties: JavaKnowledge and section number. It contains a comparable which is
 * used to sort the students section. It also contains basic setter/getter/toString functions in order to access
 * the private variables. 
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

public class CSCE314Student extends Student implements Comparable<CSCE314Student> {
	private int JavaKnowledge;
	private int section;
	
	// constructor calls super for the Student class
	public CSCE314Student(String firstName, String lastName, String UIN, Rank rank, int javaKnowledge, int section) {
		super(firstName, lastName, UIN, rank);
		this.JavaKnowledge = javaKnowledge;
		this.section = section;
	}
	// uses a comparable in order compare CSCE314Student objects by section number
	// also used to sort students by section
    public int compareTo(CSCE314Student student1) {
        if(section > student1.getSection()) {
            return 1;
        }
        else if(section < student1.getSection()) {
            return -1;
        }
        return 0;
    }
	// getter functions for CSCE314Student class
	public int getJavaKnowledge() {
		return JavaKnowledge;
	}
	public int getSection() {
		return section;
	}
	// setter functions for CSCE314Student class
	public void setJavaKnowledge(int javaKnowledge) {
		JavaKnowledge = javaKnowledge;
	}
	public void setSection(int section) {
		this.section = section;
	}
	// toString function for CSCE314Student class
	@Override
	public String toString() {
		return super.toString() + "JavaKnowledge=" + JavaKnowledge + ", section=" + section + "]";
	}
}
