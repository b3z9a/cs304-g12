/**
 * <h2> Integrated Healthcare Database </h2>
 * The application models a healthcare application and interacts
 * with an Oracle database hosted on the UBC CS Ugrad servers.
 * 
 * @author Jenna Bains
 * @author Laura Green
 * @author Michelle Kong
 * @author Jan Louis Evangelista
 * 
 * @version 0.1
 * 
 * Note: The Oracle DB + JDBC example provided by the CS department, branch.java
 * has been used as a reference for building this application.
 */
public class HealthDB
{
    /**
     * HealthDB Constructor
     */
    public HealthDB()
    {
        System.out.println("HealthDB App Started");

        /* Generate a login window to get user credentials */
        // TODO Create a login window

        /* Try to login to the Oracle DB using credentials */
        // TODO Call the dbLogin() method
    }

    /**
     * Method for logging into the Oracle Database
     * 
     * @param String username - the username of the user logging in
     * @param String password - the password of the user
     */
    private void dbLogin()
    {
        // TODO Create login process
    }

    public static void main(String args[])
    {
        HealthDB app = new HealthDB();
    }
}