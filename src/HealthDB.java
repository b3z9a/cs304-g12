import java.sql.*;
import java.util.*;

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
	private Connection con;

	/**
	 * HealthDB Constructor
	 */
	public HealthDB() {
		System.out.println("HealthDB App Started");
		try{ // Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Oracle driver loaded.");}
		catch (SQLException ex){
				System.out.println("Error loading Oracle driver: " + ex.getMessage());
				System.exit(-1);}
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
		String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
		try {
			con = DriverManager.getConnection(connectURL,username,password);
			System.out.println("\nConnected to Oracle!");
			return true;}
		catch (SQLException ex){
			System.out.println("Error connecting to Oracle: " + ex.getMessage());
			return false;}
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
	 * tuple[] = {0 firstname, 1 lastname, 2 pid, 3 street, 4 city, 5 postalcode, 6 country, 7 homephone, 8 mobilephone}
	 *
	 * @param PID
	 * @param name
	 * @return
	 */
	public ArrayList<String> findDoctor(String PID, String name) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select p.firstName, p.lastName, p.patientID, p.street, pc.city, pc.province, pc.postalcode, pc.country, p.homePhone, p.mobilePhone from patient p, postalcode pc where p.postalcode = pc.postalcode and p.patientID = " + PID;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("firstName"));
				tuple.add(rs.getString("lastName"));
				tuple.add(rs.getString("patientID"));
				tuple.add(rs.getString("street"));
				tuple.add(rs.getString("city"));
				tuple.add(rs.getString("postalcode"));
				tuple.add(rs.getString("country"));
				tuple.add(rs.getString("homePhone"));
				tuple.add(rs.getString("mobilePhone"));
			}

	} catch (SQLException ex){
		System.out.println("Failed to get patient personal info. " + ex.getMessage());
	}
	return tuple;
}

	/**
	 * Creates a prescription
	 */
	public void createPrescription() {

		/* TODO Create a prescription */
		System.out.println("Create a Prescription");
	}

	/**
	 * Renews a prescription
	 */
	public void renewPrescription() {

		/* TODO Renew a prescription */
		System.out.println("Renew a Prescription");
	}

	/**
	 * Creates a test
	 */
	public void createTest() {

		/* TODO Create a test */
		System.out.println("Create a Test");
	}

	/**
	 * Creates a referral
	 */
	public void createReferral() {

		/* TODO Create a referral */
		System.out.println("Create a Referral");
	}

	/**
	 * Returns the prescriptions of the specified patient
	 *
	 * @param pid- the PID of the selected Patient
	 * @return prescription data
	 */
	public ArrayList<ArrayList<String>> getPrescriptions(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select pr.prescriptionID, pr.prescribedDate, m.medication, pr.dosage, m.dosageMeasure, pr.quantity, pr.filledDate from prescription pr, medication m where pr.medication = m.medication and pr.patientID = "+ pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("prescriptionID"));
				tuple.add(rs.getString("prescribedDate"));
				tuple.add(rs.getString("medication"));
				tuple.add(rs.getString("dosage"));
				tuple.add(rs.getString("dosageMeasure"));
				tuple.add(rs.getString("quantity"));
				tuple.add(rs.getString("filledDate"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get prescriptions. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns the tests of the specified doctor
	 *
	 * @param pid - the PID of the selected Patient
	 * @return test data
	 */
	public ArrayList<ArrayList<String>> getTests(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select testID, orderedDate, performedDate from LabTest where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("testID"));
				tuple.add(rs.getString("orderedDate"));
				tuple.add(rs.getString("performedDate"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get test summary. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns the referrals of the specified patient
	 	 *
	 * @param pid - the PID of the selected Patient
	 * @return referral data
	 */
	public ArrayList<ArrayList<String>> getReferrals(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select h.firstName, h.lastName, d.specialization, r.referredDate from Referral r, HealthcareProfessional h, Doctor d where r.referreeHID = h.HID and d.HID = h.hid and r.patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("firstName"));
				tuple.add(rs.getString("lastName"));
				tuple.add(rs.getString("specialization"));
				tuple.add(rs.getString("referredDate"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get referrals. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns provincial plan information for specified patient
	 	 *
	 * @param pid - the PID of the selected Patient
	 * @return provincial plan information
	 */
	public ArrayList<String> getPlan(String pid) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select planID, planType, startDate, endDate from ProvincialHealthPlan where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("planID"));
				tuple.add(rs.getString("planType"));
				tuple.add(rs.getString("startDate"));
				tuple.add(rs.getString("endDate"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get provincial plan information " + ex.getMessage());
		}
		return tuple;
	}

	/**
	 * Returns extended benefits information for specified patient
	 	 *
	 * @param pid - the PID of the selected Patient
	 * @return extended benefits information
	 */
	public ArrayList<ArrayList<String>> getExtendedBenefits(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select chiropractic, chiroracticAnnualLimit, chiropracticYTD, physiotherapy, physiotherapyAnnualLimit, physiotherapyYTD, nonSurgicalPodiatry, nonSurgicalPodiatryAnnualLimit, nonSurgicalPodiatryYTD, acupuncture, acupunctureAnnualLimit, acupunctureYTD, medication, medicationAnnualLimit, medicationYTD from ExtendedBenefitsPlan where patientID = "+ pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("chiropractic"));
				tuple.add(rs.getString("chiroracticAnnualLimit"));
				tuple.add(rs.getString("chiropracticYTD"));
				tuple.add(rs.getString("physiotherapy"));
				tuple.add(rs.getString("physiotherapyAnnualLimit"));
				tuple.add(rs.getString("physiotherapyYTD"));
				tuple.add(rs.getString("nonSurgicalPodiatry"));
				tuple.add(rs.getString("nonSurgicalPodiatryAnnualLimit"));
				tuple.add(rs.getString("nonSurgicalPodiatryYTD"));
				tuple.add(rs.getString("acupuncture"));
				tuple.add(rs.getString("acupunctureAnnualLimit"));
				tuple.add(rs.getString("acupunctureYTD"));
				tuple.add(rs.getString("medication"));
				tuple.add(rs.getString("medicationAnnualLimit"));
				tuple.add(rs.getString("medicationYTD"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get extended benefits information " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Finds a prescription and returns it
	 * @param presNum
	 * @param pid
	 * @param name
	 * @return
	 */
	public Object findPrescription(String presNum, String pid, String name) {

		/* TODO Return prescriptions */
		return new Object();
	}

	/**
	 * Returns a single tuple containing provincial healthcare plan info for
	 * the specified patient
	 *
	 * @param pid - the PID of the selected Patient
	 * @return plan data
	 */
	public ArrayList<String> getPlan(String pid){
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select planID, planType, startDate, endDate from ProvincialHealthPlan where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("planID"));
				tuple.add(rs.getString("policyType"));
				tuple.add(rs.getString("startDate"));
				tuple.add(rs.getString("endDate"));
			}

	} catch (SQLException ex){
		System.out.println("Failed to get plan information. " + ex.getMessage());
	}
	return tuple;
	}

	/**
	 * Returns tuples containing extended benefit plan info for the specified patient
	 *
	 * @param pid - the PID of the selected Patient
	 * @return EPB data
	 */
	public ArrayList<ArrayList<String>> getEBPs(String pid){
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select h.firstName, h.lastName, d.specialization, r.referredDate from Referral r, HealthcareProfessional h, Doctor d where r.referreeHID = h.HID and d.HID = h.hid and r.patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("firstName"));
				tuple.add(rs.getString("lastName"));
				tuple.add(rs.getString("specialization"));
				tuple.add(rs.getString("referredDate"));
				tuples.add(tuple);
			}

			// Close the stament, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get extended benefits plan(s). " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Finds tests and returns it
	 * @param testNum
	 * @param pid
	 * @param name
	 * @return
	 */
	public Object findTest(String testNum, String pid, String name) {

		/* TODO Return tests */
		return new Object();
	}

	/**
     * Finds invoice and returns it
     * @param hid
     * @param staffName
     * @param invoiceNumber
     * @param pid
     * @param name
     * @param planNumber
     * @return
     */
    public Object findInvoice(String hid, String staffName, String invoiceNumber,
                              String pid, String name, String planNumber) {

        /* TODO Return tests */
        return new Object();
    }

    public Object submitInvoice(String creationDate, String dueDate, String invoiceItem,
                                String paymentStatus, String paymentDate, String paymentMethod, String amountOwing) {

        /* TODO Return tests */
        return new Object();
    }
}
