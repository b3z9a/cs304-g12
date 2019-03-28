# Integrated Healthcare Management System
### CPSC 304 Group 12
* Jenna Bains

* Laura Green

* Michelle Kong

* Jan Louis Evangelista

### Description
Our database will model an integrated healthcare database that would be used by health system professionals including doctors, pharmacists, and administrative staff. The goal would be for the database to be used at a provincial level, so a patient’s records from across the healthcare system would be stored in one database. The database will model patients, healthcare staff (doctors, nurses, pharmacists), insurance policies, prescriptions, medical tests, and invoices. By choosing these aspects of the domain, the database could be used by doctors and nurses to check patient records, by pharmacists to view and fill prescriptions, and by administrative staff to invoice insurance payments and track co-payments.  

### Running the Application in SSH and a Command Line Interface

1. SSH into the server by either running `./ssh-login.sh Your_CS_ID` login script in the root of the `cs304-g12` folder or by typing `ssh -X Your_CS_ID@thetis.ugrad.cs.ubc.ca`

2. Clone the repository into a folder of your choice.

3. Navigate to `Your_Folder\cs304-g12\src`

4. Initialize the database by running `setupdb.sql` inside SQLPlus (Only when running for the first time.)

5. Compile the .java files by running `javac HealthDBUI.java HealthDB.java`

6. Run the app by running `java HealthDBUI`

7. Use your Oracle DB credentials as provided in Tutorial 4 to log into the database and use the app.
