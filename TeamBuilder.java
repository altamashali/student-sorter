/* Description: 
 * The following is the TeamBuilder class which is responsible for taking an unsorted ArrayList of students
 * and then sorting it by the students java knowledge and section number. It then takes this sorted list
 * and creates pairs based upon the students level of java knowledge. Students will be put into pairs so that
 * there is a good balance among all of the teams in the combined java knowledge between both partners. In,
 * sections that have an odd number of students, there will be one student, typically the one with the most 
 * java knowledge, left without a partner. These singles will serve as substitutes for when a student is absent.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TeamBuilder {
	// 3 primary variables responsible in the team building process
	private static ArrayList<ArrayList<CSCE314Student>> teams;
	private static Utility util;
	private static JavaKnowledgeComparator compare;
	
	// constructor which initializes new component when a TeamBuilder object is created in Driver
	public TeamBuilder() {
		 teams = new ArrayList<ArrayList<CSCE314Student>>();
		 util = new Utility();
		 compare = new JavaKnowledgeComparator();
	}
	
	// additional helper variables, mostly lists, which further assist in the sorting of students by section
	private static ArrayList<CSCE314Student> unsorted_students = new ArrayList<CSCE314Student>();
	private static ArrayList<Integer> all_sections = new ArrayList<Integer>();
    private static ArrayList<Integer> only_sections;
    private int [] section_sizes;
	private int number_of_teams = 0;

	// getter functions responsible for returning important variables when calling by Driver or Utility 
	public Utility getUtil() {
		return util;
	}
	public ArrayList<CSCE314Student> getUnsorted_students() {
		return unsorted_students;
	}
	public static ArrayList<ArrayList<CSCE314Student>> getTeams() {
		return teams;
	}
	public ArrayList<Integer> getAll_sections() {
		return all_sections;
	}
	public int getNumber_of_teams() {
		return number_of_teams;
	}
	public ArrayList<Integer> getListWithoutDuplicates() {
		return only_sections;
	}
	// end of getter functions
	// setter functions responsible for appending new student objects and their section number to helper lists
	public static void set_All_sections(int section_number) {
		all_sections.add(section_number);
	}
	public static void set_Unsorted_students(CSCE314Student add_1) {
		unsorted_students.add(add_1);
	}
	
	// this is the primary function which sorts students in the unsorted list
	public void sort_students(){
		
		Collections.sort(unsorted_students, compare); // sorts by java knowledge
		Collections.sort(unsorted_students); // sorts by section number to avoid pairs with conflicting section numbers
	    // This compiles a list of all possible section numbers found in the csv
		only_sections = (ArrayList<Integer>) all_sections.stream().distinct().collect(Collectors.toList());
	    
		// This compiles a list of the size of each of the possible sections found
	    section_sizes = new int[only_sections.size()];
	    for (int i = 0; i < unsorted_students.size(); i++) {
	    	for (int j = 0; j < only_sections.size(); j++) {
		    	if (unsorted_students.get(i).getSection() == only_sections.get(j)) {
		    		section_sizes[j]++;
		    	}
	    	}
	    }
	}
	// This is a complicated algorithm I designed in order to create pairs based off of the sorted list of students
	// If a section has an even number of students, a perfect amount of pairs are created
	// If a section has an odd number of students, everyone but one student, typically the smartest one, is left without a partner
	// It makes sure not to pair students who are in different sections
	public void make_pairs() {
		// This for loop creates pairs based on each section it has detected in the csv
		for (int i = 0; i < only_sections.size(); i++) {
			// These variables are used to properly place students in their spot in the teams ArrayList
			int section_total_number = section_sizes[i];
			int stop_here = 99999;
			int current_index = -1;
			int teams_index = number_of_teams-1;
			int middle_index = 0;
			// This detects the end of a specified section in the csv
			if (section_total_number % 2 == 0) {
				middle_index = (section_total_number/2)-1;
			}
			else {
				middle_index = ((section_total_number-1)/2)-1;
				stop_here = section_total_number-2;
			}
			// Given that the sections are sorted from least java knowledge to most java knowledge
			// The if statement first adds the top half of the students into empty pairs
			// The else statement then adds the bottom half of students to the previously added ones who complement their knowledge
			while((unsorted_students.get(0).getSection() == only_sections.get(i)) && (current_index != stop_here)) {
				if (current_index < middle_index) {
					teams.add(new ArrayList<CSCE314Student>());
					teams_index++;
					teams.get(teams_index).add(unsorted_students.get(0));

				}
				else {
					teams.add(new ArrayList<CSCE314Student>());
					teams.get(teams_index).add(unsorted_students.get(0));
					teams_index--;
					number_of_teams++;
					
				}
				current_index++;
				unsorted_students.remove(0);
			}
			// After all even pairs have been made, the lone student left without a partner is added at the end
			if (current_index == stop_here) {
				teams.add(new ArrayList<CSCE314Student>());
				teams.get(number_of_teams).add(unsorted_students.get(0));
				unsorted_students.remove(0);
				number_of_teams++;
			}
		}
	}
}
