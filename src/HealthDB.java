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
	 * Primary key constants Increment on creating the corresponding tuple to generate unique primary keys
	 *
	 * prescriptionID
	 * testID
	 * invoiceID
	 */
	private Integer prescriptionIDCounter = 400000;
	private Integer testIDCounter = 800000;
	private Integer invoiceIDCounter = 150000;

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
	}

	/**
	 * connectToDB Connects to the Oracle DB using credentials
	 *
	 * @param username - the username to log into the Oracle DB
	 * @param password - the password to log into the Oracle DB
	 *
	 * @return true - if Oracle database is connected to the app, false otherwise
	 */
	public boolean connectToDB(String username, String password) {
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
	 * createX methods: Creates a new X tuple
	 */

	/**
	 * Creates a prescription
	 *
	 * @param medication
	 * @param dosage
	 * @param quantity
	 * @param patientID
	 * @param drHID
	 *
	 * Creates a prescription with current date as prescribedDate
	 */
	public boolean createPrescription(String medication, String dosage, String quantity,
																 String patientID, String drHID) {
		try {
			java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			prescriptionIDCounter = prescriptionIDCounter++;
			String query ="insert into prescription (prescriptionID, medication, dosage, quantity, patientID,"
							+ " drHID, prescribedDate) values (" + prescriptionIDCounter + ", '" + medication + "', " + dosage
							+ ", " + quantity +", " + patientID +", " + drHID +", " + "to_date('" + today + "', 'YYYY-MM-DD'))";
			System.out.println("createPrescription query: "+ query);
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Prescription successfully created");
			return true;
		} catch (SQLException ex){
			System.out.println("Failed to create prescription" + ex.getMessage());
			return false;
		}
	}

	/**
	 * Creates a test
	 *
	 * @param patientID
	 * @param drHID
	 *
	 * Creates a lab test with current date as ordered date
	 */
	public boolean createTest(String patientID, String drHID) {
		try {
			java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			testIDCounter = testIDCounter++;
			String query = "insert into labtest (testID, patientID, drHID, orderedDate) values (" + testIDCounter + ", "
							+ patientID + ", " + drHID + ", " + "to_date('" + today + "', 'YYY-MM-DD'))";
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Test successfully created");
			return true;
		} catch (SQLException ex){
			System.out.println("Failed to create test" + ex.getMessage());
			return false;
		}
	}

	/**
	 * Creates a referral
	 *
	 * @param patientID
	 * @param referrerHID - HID of doctor making the referral
	 * @param referreeHID - HID of doctor being referred to
	 * @return returns true if the referral was successfully created
	 *
	 * Creates a referral with current date as referred date
	 */
	public boolean createReferral(String patientID, String referrerHID, String referreeHID) {
		try {
			java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			String query = "insert into referral (patientID, referrerHID, referreeHID, referredDate) values ("
							+ patientID + ", " + referrerHID + ", " + referreeHID + ", " + "to_date('" + today + "', 'YYY-MM-DD'))";
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Referral successfully created");
			return true;
		} catch (SQLException ex){
			System.out.println("Failed to create referral" + ex.getMessage());
			return false;
		}
	}

	/**
	 * Creates an invoice
	 *
	 * Required on creating a new invoice
	 * @param patientID
	 * @param invoiceItem
	 * @param dueDate
	 * @param paymentStatus
	 * @param amountOwing
	 *
	 * Optional on creating a new invoice
	 * @param paymentDate
	 * @param paymentMethod
	 * @param paymentID
	 * @param planID
	 *
	 * Creates an unpaid/fully paid invoice with current date as creation date
	 */
	public boolean createInvoice(String patientID, String invoiceItem, String dueDate, String paymentStatus,
														String paymentDate, String paymentMethod, String amountOwing, String paymentID, String planID) {
		try {
			// Oracle will insert null if you insert an empty string. Therefore do not need to check if optional values are empty strings

			java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			invoiceIDCounter = invoiceIDCounter++;
			String query = "insert into invoice (invoiceID, patientID, invoiceItem, creationDate, dueDate, paymentStatus, "
							+ "paymentDate, paymentMethod, amountOwing, paymentID, planID) values (" + invoiceIDCounter + ", "
							+ patientID + ", " + "to_date('" + today + "', 'YYY-MM-DD'), " + dueDate + ", " + paymentStatus + ", "
							+ paymentDate + ", " + paymentMethod + ", " + amountOwing + ", " + paymentID + ", " + planID + ")";
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Invoice successfully created");
			return true;
		} catch (SQLException ex){
			System.out.println("Failed to create invoice" + ex.getMessage());
			return false;
		}
	}

	/**
	 * deleteX method: Deletes an existing X tuple by specified ID
	 */

	/**
	 * Delete specified patient.
	 * @param pid
	 * @return true if the patient was successfully deleted
	 * Deletes patient and cascades delete to
	 * referral, prescription, labtest, provincialhealthplan, extendedbenefitsplan, invoice tables
	 */
	public boolean deletePatient(String pid) {
		try {
			String query = "delete from patient where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Patient successfully deleted");
			return true;
		} catch (SQLException ex){
			System.out.println("Failed to delete patient" + ex.getMessage());
			return false;
		}
	}

	/** getX methods: Returns all X tuples for specified name/ID
	 */

	/** Finds all patients with a name containing the string provided.
	* @param name: the name of the patient to be searched for
	* @return tuples of all patients whose first or last name contains the string provided.
	*/
	public ArrayList<ArrayList<String>> getPatients(String name){
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select p.firstName, p.lastName, p.patientID, p.street, "
							+ "pc.city, pc.province, pc.postalcode, pc.country, "
							+ "p.homePhone, p.mobilePhone from patient p, postalcode pc "
							+ "where (p.firstName like '" + name + "%'" + " or p.lastName like '" + name + "%') and p.postalcode = pc.postalcode" ;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("firstName"));
				tuple.add(rs.getString("lastName"));
				tuple.add(rs.getString("patientID"));
				tuple.add(rs.getString("street"));
				tuple.add(rs.getString("city"));
				tuple.add(rs.getString("province"));
				tuple.add(rs.getString("postalcode"));
				tuple.add(rs.getString("country"));
				tuple.add(rs.getString("homePhone"));
				tuple.add(rs.getString("mobilePhone"));
				tuples.add(tuple);
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get patients. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns the prescriptions of the specified patient
	 *
	 * tuple = {0 presID, 1 presDate, 2 medication, 3 dosage, 4 doseMeasure, 5 qty, 6 filledDate}
	 *
	 * @param pid- the PID of the selected Patient, cannot be null
	 * @return prescription data
	 */
	public ArrayList<ArrayList<String>> getPrescriptions(String pid, String name) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select pr.prescriptionID, pr.prescribedDate, m.medication,"+
										 " pr.dosage, m.dosageMeasure, pr.quantity, pr.filledDate"+
										 " from prescription pr, medication m where pr.medication ="+
										 " m.medication and pr.patientID = "+ pid;
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

				if(rs.getString("filledDate") == null) {
                    tuple.add("No");
                }
				else {
				    tuple.add("Yes");
                }

				tuples.add(tuple);
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get prescriptions. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns the tests of the specified doctor
	 *
	 * tuple = {0 testID, 1 orderedDate, 2 performedDate}
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

                if(rs.getString("performedDate") == null) {
                    tuple.add("No");
                }
                else {
                    tuple.add("Yes");
                }
				tuples.add(tuple);
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get test summary. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns the referrals of the specified patient
	 *
	 * tuple = {0 firstName, 1 lastName, 2 specialization, 3 referredDate}
	 *
	 * @param pid - the PID of the selected Patient
	 * @return referral data
	 */
	public ArrayList<ArrayList<String>> getReferrals(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select h.firstName, h.lastName, d.specialization,"+
										 " r.referredDate from Referral r, HealthcareProfessional h,"+
										 " Doctor d where r.referreeHID = h.HID and d.HID = h.hid"+
										 " and r.patientID = " + pid;
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

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get referrals. " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns provincial plan information for specified patient
	 *
	 * tuple = {0 planID, 1 planType, 2 startDate, 3 endDate}
	 *
	 * @param pid - the PID of the selected Patient
	 * @return provincial plan information
	 */
	public ArrayList<String> getPlan(String pid) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select planID, planType, startDate, endDate from "+
										 "ProvincialHealthPlan where patientID = " + pid;
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
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get provincial plan information " + ex.getMessage());
		}
		return tuple;
	}

	/**
	 * Returns extended benefits information for specified patient
	 *
	 * tuple = {0 chiropractic}
	 *
	 * @param pid - the PID of the selected Patient
	 * @return extended benefits information
	 */
	public ArrayList<ArrayList<String>> getExtendedBenefits(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select chiropractic, chiroracticAnnualLimit, chiropracticYTD,"+
										 " physiotherapy, physiotherapyAnnualLimit, physiotherapyYTD,"+
										 " nonSurgicalPodiatry, nonSurgicalPodiatryAnnualLimit, "+
										 "nonSurgicalPodiatryYTD, acupuncture, acupunctureAnnualLimit,"+
										 " acupunctureYTD, medication, medicationAnnualLimit, "+
										 "medicationYTD from ExtendedBenefitsPlan where patientID = "+ pid;
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

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get extended benefits information " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * Returns total unpaid amount owing for specified patient
	 *
	 * @param pid - the PID of the selected Patient
	 * @return total unpaid amount owing
	 */
	public ArrayList<String> getAmountOwing(String pid) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select sum(amountOwing) as amountOwing from Invoice where "+
										 "patientID = " + pid + " and paymentStatus = 'Unpaid";
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("amountOwing"));
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get amount owing " + ex.getMessage());
		}
		return tuple;
	}

	/**
	 * Returns total OVERDUE unpaid amount owing for specified patient
	 *
	 * @param pid - the PID of the selected Patient
	 * @return total OVERDUE unpaid amount owing
	 */
	public ArrayList<String> getOverdueAmountOwing(String pid) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			String query = "select sum(amountOwing) as overdueAmountOwing from Invoice "+
										 "where patientID = " + pid + "and paymentStatus = 'Unpaid and dueDate < " + today;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("overdueAmountOwing"));
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get overdue amount owing " + ex.getMessage());
		}
		return tuple;
	}

	/**
	 * Returns invoices for specified patient
	 *
	 * @param pid - the PID of the selected Patient
	 * @return invoices for specified patient
	 */
	public ArrayList<ArrayList<String>> getInvoices(String pid) {
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try{
			String query = "select invoiceID, invoiceItem, creationDate, dueDate, "+
										 "paymentStatus, amountOwing from Invoice where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute each query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("invoiceID"));
				tuple.add(rs.getString("invoiceItem"));
				tuple.add(rs.getString("creationDate"));
				tuple.add(rs.getString("dueDate"));
				tuple.add(rs.getString("paymentStatus"));
				tuple.add(rs.getString("amountOwing"));
				tuples.add(tuple);
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get provincial plan information " + ex.getMessage());
		}
		return tuples;
	}

	/**
	 * findX methods: Finds X tuple by its primary key
	 */

	/**
	 * findPatient
	 * Finds a patient in the database, stores tuple information in a data structure
	 * tuple[] = {0 firstname, 1 lastname, 2 pid, 3 street, 4 city, 5 postalcode, 6 country, 7 homephone, 8 mobilephone}
	 * @param PID
	 * @return the single tuple for the patient with the given PID
	 */
	public ArrayList<String> findPatient(String PID) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select p.firstName, p.lastName, p.patientID, p.street,"+
										 " pc.city, pc.province, pc.postalcode, pc.country, "+
										 "p.homePhone, p.mobilePhone from patient p left join postalcode"+
										 " pc on p.postalcode = pc.postalcode where p.patientID = " + PID;
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
				tuple.add(rs.getString("province"));
				tuple.add(rs.getString("postalcode"));
				tuple.add(rs.getString("country"));
				tuple.add(rs.getString("homePhone"));
				tuple.add(rs.getString("mobilePhone"));
			}
			stmt.close();

	} catch (SQLException ex){
		System.out.println("Failed to get patient personal info. " + ex.getMessage());
	}
	return tuple;
	}


	/**
	 * Finds the patient ID associated with a prescription.
	 * @param prescriptionID: ID of the prescription
	 * @return PID of patient associated with prescription. Returns the empty
	 					 string if no prescription is found.
	 */
	public String findPrescription(String prescriptionID) {
		try{
			String query = "select patientID from Prescription where prescriptionID = " + prescriptionID;
			Statement stmt = con.createStatement();
			// Execute each query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				return rs.getString("patientID");
		  }

		} catch (SQLException ex){
				System.out.println("Error finding prescription. " + ex.getMessage());
		}
		return "";
	}

	/**
	 * Finds a test and returns it
	 * @param testID: ID of the test to be found
	 * @return the tuple of the test with the ID provided
	 */
	public String findTest(String testID) {

		/* TODO Return test */
		return new String();
	}

	/**
     * Finds an invoice and returns it
     * @param invoiceID
     * @return
     */
    public ArrayList<String> findInvoice(String invoiceID) {
        /* TODO Return invoice */
        return new ArrayList<String>();
    }

    /**
     * updateX methods: Updates an existing X tuple with given data
     */

	/**
	* Updates a prescription in the database.
	* @param hid: HID of the doctor making the prescription
	* @param pid: PID of the patient the prescription is for
	* @param medication: name of medication being prescribed
	* @param dosage: numeric quantity for the doage without units
	* @param quantity: number of pills/units of the medication prescribed
	*/
	public boolean updatePrescription(String hid, String pid, String medication, String dosage, String quantity){
		/* TODO Updates an existing prescription. */
		return true;
	}

	/**
	* Updates an existing test.
	* Returns true if update/creation was sucessful.
	* @param testID: ID of the test
	* @param labTechID: HID of the lab tech filling in the test info.
	* @param Rest: Lab test values
	*/
	public boolean updateTest(String testID, String cholesterol, String HDLcholesterol,
													 String LDLcholesterol, String trigycerides, String whiteBloodCellCount,
													 String redBloodCellCount, String hematocrit, String plateletCount,
													 String NRBCPercent, String NRBCAbsolute, String sodium, String glucose,
													 String phosphorus, String labTechID) {

			/* TODO submit test data */
			// old createTest query to repurpose for updateTest
			//String query = "insert into labtest values (" + testID + ", " +  cholesterol +", " + HDLcholesterol +", " + LDLcholesterol +", " + triglycerides +", " + whiteBloodCellCount +", " + redBloodCellCount +", " + hematocrit +", " + plateletCount +", " + NRBCpercent +", " + NRBCabsolute +", " + sodium +", " + phosphorus +", " + glucose +", " + patientID +", " + drHID +", " + labTechHID +", " + orderedDate +", " + performedDate +")";
			return true;
	}

		/**
		* Updates an existing invoice in the database.
		* @param invoiceID: The ID of the invoice to be updated.
		* @param dueDate: Date the invoice is due.
		* @param invoiceItem: Item the invoice is for.
		* @param paymentStatus: Status of the invoice. One of: paid, unpaid
		* @param paymentMethod: Payment method if invoice has been paid. One of: Credit/Debit, Cash, Cheque
		* @param amountOwing: Amount owing on the invoice
		*/
    public boolean updateInvoice(String invoiceID, String dueDate, String invoiceItem,
                                String paymentStatus, String paymentDate, String paymentMethod,
																String amountOwing) {

        /* TODO Update invoice. */
        return true;
    }

    public Object findPlan(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	private void printTuple(ArrayList<String> tuple){
			StringBuilder sb = new StringBuilder();
				for (String s : tuple){
					sb.append(" '");
					sb.append(s);
					sb.append("', ");
				}
			System.out.println(sb.toString());
	}
}
