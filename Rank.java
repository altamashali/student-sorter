/* Description: 
 * The following is the Rank enum which is used by Student in order to initialize the rank property of
 * the CSCE314Student. The Rank enum is used to signify where a student is a Freshmen, Sophomore, Junior,
 * or Senior based off of the numerical value (1-4) they submitted in the form.
 * 
 * Name: Altamash Ali
 * UIN: 427004880
 * Email: altamashali@tamu.edu 
 */

public enum Rank {
	Freshmen(1),
	Sophomore(2),
	Junior(3),
	Senior(4);
	
	private int rankValue;
	
	private Rank(int rankValue) {
		this.rankValue = rankValue;
	}
	// getter function for enum Rank
	public int getRank() {
		return this.rankValue;
	}
	// this functions returns the desired enum value for the number (1-4) read in from the csv file
	public static Rank parseRank(String rank_S) {
		// TODO Auto-generated method stub
		if (rank_S.equals("1")) {
			return Freshmen;
		}
		else if (rank_S.equals("2")) {
			return Sophomore;
		}
		else if (rank_S.equals("3")) {
			return Junior;
		}
		else if (rank_S.equals("4")) {
			return Senior;
		}
		// Null is returned when a rank 1-4 is not entered, then written to error log
		else {
			return null;
		}
	}
}
