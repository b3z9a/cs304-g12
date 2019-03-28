import java.sql.*;
import java.util.*;

import javax.swing.JTextField;

import java.text.*;

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
	static Integer prescriptionIDCounter;
	static Integer testIDCounter;
	static Integer invoiceIDCounter;
	static Integer paymentIDCounter;
	private DateFormat format = new SimpleDateFormat("MMMM dd yyyy");

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


			// Set counters
			String maxTest = "select max(testID) as max from labtest";
			String maxInvoice = "select max(invoiceID) as max from invoice";
			String maxPayment = "select max(paymentID) as max from invoice";
			String maxPrescription = "select max(prescriptionID) as max from prescription";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(maxTest);
			if(rs.next()){
				testIDCounter = rs.getInt("max") + 1;
			}

			rs = stmt.executeQuery(maxInvoice);
			if(rs.next()){
				invoiceIDCounter = rs.getInt("max") +1;
			}

			rs = stmt.executeQuery(maxPayment);
			if(rs.next()){
				paymentIDCounter = rs.getInt("max") +1;
			}

			rs = stmt.executeQuery(maxPrescription);
			if(rs.next()){
				prescriptionIDCounter = rs.getInt("max") + 1;
			}
			System.out.println("invoiceIDCounter: " + invoiceIDCounter);
			System.out.println("paymentIDCounter: " + paymentIDCounter);
			System.out.println("prescriptionIDCounter: " + prescriptionIDCounter);
			System.out.println("testIDCounter: " + testIDCounter);

			stmt.close();

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
			System.out.println("PrescriptionID Counter pre: " + prescriptionIDCounter);
			String query ="insert into prescription (prescriptionID, medication, dosage, quantity, patientID,"
							+ " drHID, prescribedDate) values (" + prescriptionIDCounter + ",'" + medication + "', " + dosage
							+ ", " + quantity +", " + patientID +", " + drHID + "," + today() + ")";
			prescriptionIDCounter++;
			System.out.println("prescriptionID Counter post: " + prescriptionIDCounter);
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			System.out.println("Prescription successfully created");
			return !checkInteraction(patientID, medication);
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
			System.out.println("TestID Counter pre: " + testIDCounter);
			String query = "insert into labtest (testID, patientID, drHID, orderedDate) values (" + testIDCounter + ", "
							+ patientID + ", " + drHID + ", " + today() + ")";
			testIDCounter++;
			System.out.println("TestID Counter post: " + testIDCounter);
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
			String query = "insert into referral (patientID, referrerHID, referreeHID, referredDate) values ("
							+ patientID + ", " + referrerHID + ", " + referreeHID + ", " + today() + ")";
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
	 * @param planID
	 *
	 * Creates an unpaid/fully paid invoice with current date as creation date
	 */
	public boolean createInvoice(String patientID, String invoiceItem, String dueDate, String paymentStatus,
														String paymentDate, String paymentMethod, String amountOwing, String planID) {
        try {
            System.out.println("invoiceID Counter pre: " + invoiceIDCounter);
            System.out.println("paymentID Counter pre: " + paymentIDCounter);

            String paymentDateValue = "''";
            String paymentMethodValue = "''";
            String paymentIDValue = "''";
            if(!paymentDate.isEmpty()){
                paymentDateValue = "to_date('" + paymentDate + "', 'yyyy-MM-dd')";
            }
            if (!paymentDate.isEmpty() && !paymentMethod.isEmpty())
            {
            	paymentIDValue = paymentIDCounter.toString();
            }

            // Oracle will insert null if you insert an empty string. Therefore do not need to check if optional values are empty strings
            String query = "insert into invoice (invoiceID, patientID, invoiceItem, creationDate, dueDate, paymentStatus, "
                    + "paymentDate, paymentMethod, amountOwing, paymentID, planID) values (" + invoiceIDCounter + ", "
                    + patientID + ", '" + invoiceItem + "', " + today() + ", " + "to_date('" + dueDate + "', 'yyyy-MM-dd'), '" + paymentStatus + "', "
                    + paymentDateValue + ", '" + paymentMethod + "', " + amountOwing + ", " + paymentIDValue + ", " + planID + ")";
            System.out.println(query);
            invoiceIDCounter++;
            if (!paymentDate.isEmpty() && !paymentMethod.isEmpty())
            {
            	paymentIDCounter++;
            }
            System.out.println("invoiceID Counter post: " + invoiceIDCounter);
            System.out.println("paymentID Counter post: " + paymentIDCounter);
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
							+ "p.homePhone, p.mobilePhone from patient p left join postalcode pc "
							+ "on p.postalcode = pc.postalcode " + "where (lower(p.firstName) like '%"
							+ name.toLowerCase() + "%'" + " or lower(p.lastName) like '%" + name.toLowerCase() + "%')" ;
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
	public ArrayList<ArrayList<String>> getPrescriptions(String pid) {
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
				if (rs.getDate("prescribedDate")!=null){
					tuple.add(format.format(rs.getDate("prescribedDate")));
			  } else{
					tuple.add("");
			  }
				tuple.add(rs.getString("medication"));
				tuple.add(rs.getString("dosage"));
				tuple.add(rs.getString("dosageMeasure"));
				tuple.add(rs.getString("quantity"));
				if (rs.getDate("filledDate")!=null){
					tuple.add(format.format(rs.getDate("filledDate")));
				} else{
					tuple.add("");
				}

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
				if (rs.getDate("orderedDate")!=null){
					tuple.add(format.format(rs.getDate("orderedDate")));
				} else{
					tuple.add("");
				}
				if (rs.getDate("performedDate")!=null){
					tuple.add(format.format(rs.getDate("performedDate")));
			  } else{
					tuple.add("");
			  }

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
				if (rs.getDate("referredDate")!=null){
					tuple.add(format.format(rs.getDate("referredDate")));
				} else{
					tuple.add("");
				}
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
			String query = "select planID, policyType, startDate, endDate from "+
										 "ProvincialHealthPlan where patientID = " + pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("planID"));
				tuple.add(rs.getString("policyType"));
				if (rs.getDate("startDate")!=null){
					tuple.add(format.format(rs.getDate("startDate")));
				} else{
					tuple.add("");
				}
				if (rs.getDate("endDate")!=null){
					tuple.add(format.format(rs.getDate("endDate")));
			  } else {
					tuple.add("");
			  }
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
			String query = "select chiropractic, chiropracticAnnualLimit, chiropracticYTD,"+
										 " physiotherapy, physiotherapyAnnualLimit, physiotherapyYTD,"+
										 " nonSurgicalPodiatry, nonSurgicalPodiatryAnnualLimit, "+
										 "nonSurgicalPodiatryYTD, acupuncture, acupunctureAnnualLimit,"+
										 " acupunctureYTD, medication, medicationAnnualLimit, "+
										 "medicationYTD from ExtendedBenefitsPlan ebp, ProvincialHealthPlan php where ebp.planID = php.planID and php.patientID = "+ pid;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(rs.getString("chiropractic"));
				tuple.add(rs.getString("chiropracticAnnualLimit"));
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
	public double getAmountOwing(String pid) {
		double amountOwing = 0;
		try{
			String query = "select sum(amountOwing) as amountOwing from Invoice where "+
										 "patientID = " + pid + " and paymentStatus = 'Unpaid'";
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				amountOwing = rs.getDouble("amountOwing");
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get amount owing " + ex.getMessage());
		}
		return amountOwing;
	}

	/**
	 * Returns total OVERDUE unpaid amount owing for specified patient.
	 *
	 * @param pid - the PID of the selected Patient
	 * @return total OVERDUE unpaid amount owing
	 */
	public double getOverdueAmountOwing(String pid) {
		double amountOverdue = 0;
		try{
			String query = "select sum(amountOwing) as overdueAmountOwing from Invoice "+
										 "where patientID = " + pid + "and paymentStatus = 'Unpaid' and dueDate < " + today();
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				amountOverdue = rs.getDouble("overdueAmountOwing");
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
			return amountOverdue;
		} catch (SQLException ex){
			System.out.println("Failed to get overdue amount owing " + ex.getMessage());
		}
		return amountOverdue;
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
				if (rs.getDate("creationDate")!=null){
					tuple.add(format.format(rs.getDate("creationDate")));
				} else{
					tuple.add("");
				}
				if (rs.getDate("dueDate")!=null){
					tuple.add(format.format(rs.getDate("dueDate")));
				} else{
					tuple.add("");
				}
				tuple.add(rs.getString("paymentStatus"));
				tuple.add(rs.getString("amountOwing"));
				tuples.add(tuple);
			}

			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get invoice information " + ex.getMessage());
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
	 * Finds tuple for given prescriptionID
	 * @param prescriptionID: ID of the prescription
	 * @return tuple for given prescriptionID
	 					 string if no prescription is found.
	 */
	public ArrayList<String> findPrescription(String prescriptionID) {
		ArrayList<String> tuple = new ArrayList<>();
		try{
			String query = "select pr.prescriptionID, pr.prescribedDate, m.medication, pr.dosage, "
					+ "m.dosageMeasure, pr.quantity, pr.filledDate from prescription pr, medication m "
					+ "where pr.medication = m.medication and pr.prescriptionID = " + prescriptionID;
			Statement stmt = con.createStatement();
			// Execute each query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("prescriptionID"));
				if (rs.getDate("prescribedDate")!=null){
					tuple.add(format.format(rs.getDate("prescribedDate")));
				} else{
					tuple.add("");
				}
				tuple.add(rs.getString("medication"));
				tuple.add(rs.getString("dosage"));
				tuple.add(rs.getString("dosageMeasure"));
				tuple.add(rs.getString("quantity"));
				if (rs.getDate("filledDate")!=null){
					tuple.add(format.format(rs.getDate("filledDate")));
				} else{
					tuple.add("");
				}
				if(rs.getString("filledDate") == null) {
                    tuple.add("No");
                }
				else {
				    tuple.add("Yes");
                }

			}
			stmt.close();
		} catch (SQLException ex){
				System.out.println("Error finding prescription. " + ex.getMessage());
		}
		return tuple;
	}

	/**
	 * Finds the patient ID associated with a prescription.
	 * @param prescriptionID: ID of the prescription
	 * @return PID of patient associated with prescription. Returns the empty
	string if no prescription is found.
	 */
	public String findPIDfromPrescription(String prescriptionID) {
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
	 * Finds the patient ID associated with an invoice and returns it.
	 * @param invoiceID: ID of the invoice
	 * @return PID of patient associated with invoice. Returns the empty
	string if no invoice is found.
	 */
	public String findPIDfromInvoice(String invoiceID) {
		try{
			String query = "select patientID from Invoice where invoiceID = " + invoiceID;
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
	 * Finds the patientID associated with a test and returns it
	 * @param testID: ID of the test to be found
	 * @return the tuple of the test with the ID provided if no tuple is found
	 * 				returns the empty string.
	 */
	public String findPIDfromTest(String testID) {
		try{
			String query = "select patientID from LabTest where testID = " + testID;
			Statement stmt = con.createStatement();
			// Execute each query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				return rs.getString("patientID");
			}

		} catch (SQLException ex){
			System.out.println("Error finding test. " + ex.getMessage());
		}
		return "";
	}

	/**
	 * Finds the patientID associated with a test and returns it
	 * @param testID: ID of the test to be found
	 * @return the tuple of the test with the ID provided if no tuple is found
	 * 				returns the empty string.
	 */
	public ArrayList<String> findTest(String testID) {
		ArrayList<String> test = new ArrayList<>();
		try{
			String query = "select cholesterol, HDLcholesterol, LDLcholesterol, triglycerides,"+
					"whiteBloodCellCount, redBloodCellCount, hematocrit, plateletCount,"+
					"NRBCPercent, NRBCAbsolute, sodium, glucose, phosphorus, labTechHID "+
					"from labtest where testID=" + testID;

			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				if(rs.getString("cholesterol")!=null){
					test.add(rs.getString("cholesterol") + "mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("HDLcholesterol")!=null){
					test.add(rs.getString("HDLcholesterol") + "mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("LDLcholesterol")!=null){
					test.add(rs.getString("LDLcholesterol") + "mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("triglycerides")!=null){
					test.add(rs.getString("triglycerides") + "mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("whiteBloodCellCount")!=null){
					test.add(rs.getString("whiteBloodCellCount") + "/mcL");
				} else{
					test.add("");
				}
				if(rs.getString("redBloodCellCount")!=null){
					test.add(rs.getString("redBloodCellCount") + "/mcL");
				} else{
					test.add("");
				}
				if(rs.getString("hematocrit")!=null){
					test.add(rs.getString("hematocrit") + "%");
				} else{
					test.add("");
				}
				if(rs.getString("plateletCount")!=null){
					test.add(rs.getString("plateletCount") + "/mcL");
				} else{
					test.add("");
				}
				if(rs.getString("NRBCPercent")!=null){
					test.add(rs.getString("NRBCPercent") + "%");
				} else{
					test.add("");
				}
				if(rs.getString("NRBCAbsolute")!=null){
					test.add(rs.getString("NRBCAbsolute"));
				} else{
					test.add("");
				}
				if(rs.getString("sodium")!=null){
					test.add(rs.getString("sodium") + " mEq/dL");
				} else{
					test.add("");
				}
				if(rs.getString("glucose")!=null){
					test.add(rs.getString("glucose") + " mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("phosphorus")!=null){
					test.add(rs.getString("phosphorus") + " mg/dL");
				} else{
					test.add("");
				}
				if(rs.getString("labTechHID")!=null){
					test.add(rs.getString("labTechHID"));
				} else{
					test.add("");
				}

			}
			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get test summary. " + ex.getMessage());
		}
		return test;
	}


	// patientID, invoiceItem, dueDate, paymentStatus, paymentDate, paymentMethod, amountOwing, paymentID , planID, creationDate


	/**
     * Finds an invoice and returns it
     * @param invoiceID
     * @return
     */
    public ArrayList<String> findInvoice(String invoiceID) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select patientID, invoiceItem, dueDate, paymentStatus, paymentDate, paymentMethod, amountOwing, paymentID, planID, creationDate"
					+ " from Invoice where invoiceID = " + invoiceID;
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				if(rs.getString("patientID")!=null){
					tuple.add(rs.getString("patientID"));
				} else{
					tuple.add("");
				}
				if(rs.getString("invoiceItem")!=null){
					tuple.add(rs.getString("invoiceItem"));
				} else{
					tuple.add("");
				}
				if(rs.getString("dueDate")!=null){
					tuple.add(rs.getString("dueDate"));
				} else{
					tuple.add("");
				}
				if(rs.getString("paymentStatus")!=null){
					tuple.add(rs.getString("paymentStatus"));
				} else{
					tuple.add("");
				}
				if(rs.getString("paymentDate")!=null){
					tuple.add(rs.getString("paymentDate"));
				} else{
					tuple.add("");
				}
				if(rs.getString("paymentMethod")!=null){
					tuple.add(rs.getString("paymentMethod"));
				} else{
					tuple.add("");
				}
				// amountOwing, paymentID , planID, creationDate
				if(rs.getString("amountOwing")!=null){
					tuple.add(rs.getString("amountOwing"));
				} else{
					tuple.add("");
				}
				if(rs.getString("paymentID")!=null){
					tuple.add(rs.getString("paymentID"));
				} else{
					tuple.add("");
				}
				if(rs.getString("planID")!=null){
					tuple.add(rs.getString("planID"));
				} else{
					tuple.add("");
				}
				if(rs.getString("creationDate")!=null){
					tuple.add(rs.getString("creationDate"));
				} else{
					tuple.add("");
				}
			}
			// Close the statement, the result set will be closed in the process.
			stmt.close();
		} catch (SQLException ex){
			System.out.println("Failed to get plan summary. " + ex.getMessage());
		}
		return tuple;

    }

	/**
	 * findPlanNum
	 * Finds a plan number in the database, stores tuple information in a data structure
	 * tuple[] = {0 planID, 1 policyType, 2 startDate, 3 endDate, 4 patientID}
	 * @param planID
	 * @return the single tuple for the plan with the given planID
	 */
	public ArrayList<String> findPlan(String planID) {
		ArrayList<String> tuple = new ArrayList<String>();
		try{
			String query = "select policyType, startDate, endDate, patientID from ProvincialHealthPlan where planID = " + planID;
			System.out.println(query);
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				tuple.add(rs.getString("policyType"));
				tuple.add(rs.getString("startDate"));
				tuple.add(rs.getString("endDate"));
				tuple.add(rs.getString("patientID"));
			}
			stmt.close();

		} catch (SQLException ex){
			System.out.println("Failed to get plan info. " + ex.getMessage());
		}
		return tuple;
	}

	/**
     * updateX methods: Updates an existing X tuple with given data
     */

	/**
	* Allows pharmacists to mark a prescription as filled.
	* @param hid: HID of the pharmacist filling the prescription
	* @param prescriptionID: the ID of the prescription being filled
	*/
	public boolean updatePrescription(String hid, String prescriptionID){
		boolean success = false;
		try{
			String prescription = "update prescription set pharmHID=" + hid +
														", filledDate=" + today() + " where prescriptionID="
														+ prescriptionID;

			Statement stmt = con.createStatement();
			stmt.executeUpdate(prescription);
			success = true;

		} catch (SQLException ex){
				System.out.println("Error updating prescription. " + ex.getMessage());
		}
		return success;
	}

	/**
	* Updates an existing test.
	* Returns true if update/creation was sucessful.
	* @param testID: ID of the test
	* @param labTechHID: HID of the lab tech filling in the test info.
	* @param Rest: Lab test values
	*/
	public boolean updateTest(String testID, String cholesterol, String HDLcholesterol,
													 String LDLcholesterol, String triglycerides, String whiteBloodCellCount,
													 String redBloodCellCount, String hematocrit, String plateletCount,
													 String NRBCPercent, String NRBCAbsolute, String sodium, String glucose,
													 String phosphorus, String labTechHID) {
	 boolean success = false;
	 try{
	 	StringBuilder test = new StringBuilder();
		test.append("update labtest set ");
		if(!cholesterol.isEmpty()){
			test.append("cholesterol=" + cholesterol + ", ");
		}
		if(!HDLcholesterol.isEmpty()){
			test.append("HDLcholesterol=" + HDLcholesterol + ", ");
		}
		if(!LDLcholesterol.isEmpty()){
			test.append("LDLcholesterol=" + LDLcholesterol + ", ");
		}
		if(!triglycerides.isEmpty()){
			test.append("triglycerides=" + triglycerides + ", ");
		}
		if(!whiteBloodCellCount.isEmpty()){
			test.append("whiteBloodCellCount=" + whiteBloodCellCount + ", ");
		}
		if(!redBloodCellCount.isEmpty()){
			test.append("redBloodCellCount=" + redBloodCellCount + ", ");
		}
		if(!hematocrit.isEmpty()){
			test.append("hematocrit=" + hematocrit + ", ");
		}
		if(!plateletCount.isEmpty()){
			test.append("plateletCount=" + plateletCount + ", ");
		}
		if(!NRBCPercent.isEmpty()){
			test.append("NRBCpercent=" + NRBCPercent + ", ");
		}
		if(!NRBCAbsolute.isEmpty()){
			test.append("NRBCabsolute=" + NRBCAbsolute + ", ");
		}
		if(!sodium.isEmpty()){
			test.append("sodium=" + sodium + ", ");
		}
		if(!glucose.isEmpty()){
			test.append("glucose=" + glucose + ", ");
		}
		if(!phosphorus.isEmpty()){
			test.append("phosphorus=" + phosphorus + ", ");
		}
		test.append( "labTechHID=" + labTechHID + ", performedDate=" + today() + " where testID=" + testID);

	 	Statement stmt = con.createStatement();
	 	stmt.executeUpdate(test.toString());
	 	success = true;

	 } catch (SQLException ex){
	 		System.out.println("Error updating test. " + ex.getMessage());
	 }
	 return success;
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
			boolean success = false;
			// If there is nothing to update, return true. Otherwise, causes sql error.
			if (dueDate.isEmpty() && invoiceItem.isEmpty() && paymentStatus.isEmpty() &&
			 	 paymentDate.isEmpty() && paymentMethod.isEmpty() && amountOwing.isEmpty()){
					 return true;
				 }
			try{
				StringBuilder invoice = new StringBuilder();
				invoice.append("update invoice set ");
				if(!dueDate.isEmpty()){
					invoice.append("dueDate= to_date('" + dueDate + "', 'yyyy-MM-dd'), ");
				}
				if(!invoiceItem.isEmpty()){
					invoice.append("invoiceItem=" + invoiceItem + ", ");
				}
				if(!paymentStatus.isEmpty()){
					invoice.append("paymentStatus=" + paymentStatus + ", ");
				}
				if(!paymentMethod.isEmpty()){
					invoice.append("paymentMethod=" + paymentMethod + ", ");
				}
				if(!amountOwing.isEmpty()){
					invoice.append("amountOwing=" + amountOwing + ", ");
				}
				if(!paymentDate.isEmpty()){
					invoice.append("paymentDate=to_date('" + paymentDate + "', 'yyyy-MM-dd'), ");
				}
				invoice.delete(invoice.length()-2, invoice.length()-1);
				invoice.append("where invoiceID=" + invoiceID);
				Statement stmt = con.createStatement();
				stmt.executeUpdate(invoice.toString());
				success = true;

			} catch (SQLException ex){
					System.out.println("Error updating invoice. " + ex.getMessage());
			}
			return success;
    }

    /**
     * Get monthly summary for average unapaid balance owing per invoice item
     * tuple[] = {0 invoiceItem, 1 month, 2 , 3 balanceSumAvg}
     * @return
     */
    public ArrayList<ArrayList<String>> getOwingInvoicesMonthlySummary(String pid)
    {
    	ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
    	try {
            // basic query just to test if hooked up properly with ui
            String query = "select i.invoiceItem, i.dueDate, sum(i.amountOwing) as balanceSum "
            + "from invoice i where i.patientID = " + pid + " group by i.invoiceItem, i.dueDate, i.paymentStatus";

            System.out.println(query);
			// Create a statement
			Statement stmt = con.createStatement();
			// Execute the query.
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> tuple = new ArrayList<String>();
                // default results to test for now
				tuple.add(rs.getString("invoiceItem"));
                tuple.add("January");
                tuple.add("100");
                tuples.add(tuple);
			
                // monthNum returns the number representing month
				
			}
			stmt.close();
    	} catch (SQLException ex){
			System.out.println("Error getting owing invoices monthly summary " + ex.getMessage());
    	}
    	return tuples;
    }

		/**
		*  Checks if a new medication would cause an interaction. If so,
		*  deletes it.
		*  @param patientID
		 * @param medication
		*  @return true if the medication would cause an interction, false
		*          otherwise.
		*/
	public boolean checkInteraction(String patientID, String medication){
		boolean interaction = false;
		try{
			String query = "select p.patientID from patient p where not exists " +
										 "(select * from medInteraction med where not exists " +
										 "(select * from prescription pr where p.patientID=" +
										 "pr.patientID and pr.medication=med.medication and p.patientID=" + patientID + "))";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next() && !rs.getString("patientID").isEmpty()){
				System.out.println(rs.getString("patientID"));
				String update = "delete from Prescription where patientID =" + patientID + " and medication='" + medication + "'";
				stmt.executeUpdate(update);
				return true;
			}

		} catch (SQLException ex){
				System.out.println("Error checking for medication interaction. " + ex.getMessage());
		}
		return interaction;
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

/**
* Helper method that generates todays date in the proper format to submit in a query.
* @return today's date as a string in SQL query format.
*/
	static String today(){
		DateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		return "TO_DATE('"+ sqlDate.format(today) +"','YYYY-MM-DD')";
	}
}
