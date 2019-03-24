/*HealthDB.java Method Signatures8?

/*General Methods:*/
HealthDB()
void setOracleCredentials(String username, String password);
boolean connectToDB(String username, String password);

/*Patient Methods:*/
ArrayList<String> findPatient(String PID);
ArrayList<ArrayList<String>> getPatients(String PID);
boolean deletePatient(String pid);

/*Prescription Methods:*/
boolean createPrescription(String medication, String dosage, String quantity, String patientID, String drHID);
ArrayList<ArrayList<String>> getPrescriptions(String pid)
ArrayList<String> findPrescription(String prescriptionID)
boolean updatePrescription(String hid, String pid, String medication, String dosage, String quantity)

/*Test Methods:*/
boolean createTest(String drHID, String patientID)
ArrayList<ArrayList<String>> getTests(String pid)
ArrayList<String> findTest(String testID)
boolean updateTest(String testID, String cholesterol, String HDLcholesterol, String LDLcholesterol, String trigycerides, String whiteBloodCellCount, String redBloodCellCount, String hematocrit, String plateletCount, String NRBCPercent, String NRBCAbsolute, String sodium, String glucose, String phosphorus, String labTechID)

/*Referral Methods:*/
boolean createReferral(String patientID, String referrerHID, String referreeHID)
ArrayList<ArrayList<String>> getReferrals(String pid)

/*Invoice Methods:*/
boolean createInvoice(String patientID, String invoiceItem, String dueDate, String paymentStatus, String paymentDate, String paymentMethod, String amountOwing, String planID)
ArrayList<ArrayList<String>> getInvoices(String pid)
ArrayList<String> findInvoice(String invoiceID)
ArrayList<String> getAmountOwing(String pid)
ArrayList<String> getOverdueAmountOwing(String pid)
boolean updateInvoice(String invoiceID, String dueDate, String invoiceItem, String paymentStatus, String paymentDate, String paymentMethod, String amountOwing)

/*Plan and EBP Methods:*/
ArrayList<String> getPlan(String pid)
ArrayList<ArrayList<String>> getExtendedBenefits(String pid)
