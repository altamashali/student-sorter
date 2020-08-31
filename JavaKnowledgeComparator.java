/* Description: 
 * The following is comparator class which is used by TeamBuilder in order to sort the students by Java Knowledge.
 * It simply contains one compare functions which compares the values of two CSCE314Students.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

import java.util.Comparator;

public class JavaKnowledgeComparator implements Comparator<CSCE314Student> {
	// makes use of a comparator in order to compare CSCE314Student objects by their java knowledge
	// also used to sort students by java knowledge to help in creating balanced teams
	public int compare(CSCE314Student a, CSCE314Student b)
	{
		if (a.getJavaKnowledge() < b.getJavaKnowledge()) {
			return -1;
		}
		else if (a.getJavaKnowledge() > b.getJavaKnowledge()) {
			return 1;
		}
		else {
			return 0;	
		}
	}
}
