# Integrated Healthcare Management System
### CPSC 304 Group 12
* Jenna Bains

* Laura Green

* Michelle Kong

* Jan Louis Evangelista

### Description
Our database will model an integrated healthcare database that would be used by health system professionals including doctors, pharmacists, and administrative staff. The goal would be for the database to be used at a provincial level, so a patient’s records from across the healthcare system would be stored in one database. The database will model patients, healthcare staff (doctors, nurses, pharmacists), insurance policies, prescriptions, medical tests, and invoices. By choosing these aspects of the domain, the database could be used by doctors and nurses to check patient records, by pharmacists to view and fill prescriptions, and by administrative staff to invoice insurance payments and track co-payments.  

### Setting up and Running the App with Eclipse

1. Clone the repository to a local directory by running `git clone https://github.com/jello24/cs304-g12.git` on a command line terminal

2. Import the project into Eclipse by using `File > Open Project from File System > Import Source` and select the clone directory.

3. Set `cs304-g12` as the root directory and click **Finish**.

4. Build the project and run it after importing the project into Eclipse.

### Setting up and Running the App without Eclipse 

1. Clone the repository to a local directory by running `git clone https://github.com/jello24/cs304-g12.git` on a command line terminal

2. Navigate to the `\cs304-g12\src\` folder

3. Compile the source code by running `javac HealthDBApp.java HealthDB.java` [Note: compile any other classes here as well.]

4. Run the application by running `java HealthDBApp`

### Running the Application in SSH and a Command Line Interface

1. SSH into the server by either running `./ssh-login.sh Your_CS_ID` login script in the root of the `cs304-g12` folder or by typing `ssh -X Your_CS_ID@thetis.ugrad.cs.ubc.ca`

2. Clone the repository into a folder of your choice.

3. Navigate to `Your_Folder\cs304-g12\src`

4. Compile the .java files by running `javac HealthDBApp.java HealthDB.java`

5. Run the app by running `java HealthDBApp`

6. Use your Oracle DB credentials as provided in Tutorial 4 to log into the database and use the app.

### Common Errors and Fixes
No issues yet.
