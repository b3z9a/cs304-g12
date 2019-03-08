/**
 * <h2> Integrated Healthcare Database </h2>
 * The application models a healthcare application and interacts
 * with an Oracle database hosted on the UBC CS Ugrad servers.
 * <br>
 * CPSC 304 Group 12
 * 
 * @author Jenna Bains
 * @author Laura Green
 * @author Michelle Kong
 * @author Jan Louis Evangelista
 * 
 * <br>
 * Note: The Oracle DB + JDBC example provided by the CS department, branch.java
 * has been used as a reference for building this application.
 */
public class HealthDB
{
    private String username;
    private String password;
    
    /**
     * HealthDB Constructor
     */
    public HealthDB()
    {
        System.out.println("HealthDB App Started");
    }

    /**
     * setOracleCredentials
     * Sets the username and password to be used to log into the Oracle database
     * @param username - the username to log into the Oracle DB
     * @param password - the password to log into the Oracle DB
     */
    public void setOracleCredentials(String username, String password)
    {
        this.username = username;
        this.password = password;
        
        System.out.println(this.username + " " + this.password);
    }
    
    /**
     * isConnected
     * Checks if database is connected
     * @return true - if Oracle database is connected to the app, false otherwise
     */
    public Boolean isConnected()
    {
        /* TODO proper test if app connected to the Oracle DB */
        
        return true;
    }


}