import java.util.HashMap;

/**
 * <h2>HealthDB</h2> 
 * Handles the back end logic of the Healthcare Database, including communication
 * with the Oracle database
 * <br>
 * CPSC 304 Group 12
 * 
 * @author Jenna Bains
 * @author Laura Green
 * @author Michelle Kong
 * @author Jan Louis Evangelista
 * 
 *         <br>
 *         Note: The Oracle DB + JDBC example provided by the CS department,
 *         branch.java has been used as a reference for building this
 *         application.
 */
public class HealthDB {
	private String username;
	private String password;
	
	private Integer userClass;

	/**
	 * HealthDB Constructor
	 */
	public HealthDB() {
		System.out.println("HealthDB App Started");

		/* TODO Initialize stuff here */
	}

	/**
	 * setOracleCredentials Sets the username and password to be used to log into
	 * the Oracle database
	 * 
	 * @param username - the username to log into the Oracle DB
	 * @param password - the password to log into the Oracle DB
	 */
	public void setOracleCredentials(String username, String password) {
		this.username = username;
		this.password = password;

		// System.out.println(this.username + " " + this.password);
	}

	/**
	 * connectToDB Connects to the Oracle DB using credentials
	 * 
	 * @param username - the username to log into the Oracle DB
	 * @param password - the password to log into the Oracle DB
	 * 
	 * @return true - if Oracle database is connected to the app, false otherwise
	 */
	public Boolean connectToDB(String username, String password) {
		/* TODO Connect to the Oracle DB */

		return true;
	}
	
	/**
	 * setUserClass
	 * Sets the user class as selected by the user
	 * 1 - Administrator
	 * 2 - Doctor
	 * ... You get the idea. See MS4 document hahaha
	 * @param userClass - the user class selected by the user
	 */
	public void setUserClass(String userClass)
	{
		switch (userClass)
		{
			case "Administrator":
				this.userClass = 1;
				break;
			case "Doctor":
				this.userClass = 2;
				break;
				
			/* TODO Complete the cases for the other user classes */
				
			default:
				this.userClass = 1;
				break;
		}
	}

	/**
	 * findDoctor
	 * Finds a doctor in the database, stores tuple information in a data structure
	 *
	 * Note: If you're not using HashMap, feel free to change the return type to whatever data structure you use
	 *
	 * @param PID
	 * @param name
	 * @return
	 */
	public HashMap<String, String> findDoctor(String PID, String name) {
		HashMap<String, String> doctor = new HashMap<String, String>();

		/* TODO Find a doctor in the database */

		/* TEST ONLY - Add proper implementation */
		doctor.put("PID", PID);
		doctor.put("Name", name);
		doctor.put("Addr", "123 Driveby Road, Surrey");
		doctor.put("MobileNum", "604 - 999 - 9999");
		doctor.put("HomeNum", "778 - 898 - 6969");

		return doctor;
	}
}
