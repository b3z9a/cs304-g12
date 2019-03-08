import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class MainAppWindow
{

    static HealthDB hdb;
    
    private String username;
    private String password;
    
    private String userClass[] = {"Administrator", "Doctor", "Pharmacist", "Lab Technicial", "Healthcare Admin Staff"};
    
    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    
    /* JPanels are different views within the program window */
    private JPanel panelOracleLogin;
    private JPanel panelUserClass;
    private JPanel panelAdministrator;
    private JPanel panelDoctor;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        hdb = new HealthDB();
        
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    MainAppWindow window = new MainAppWindow();
                    window.frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainAppWindow()
    {
        initialize();
        
        oracleLoginPanel();
        userClassPanel();
        adminClassPanel();
        doctorClassPanel();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(0, 0, 1280, 720);
        frame.setTitle("Integrated Healthcare Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
    }
    
    private void oracleLoginPanel()
    {
    	 panelOracleLogin = new JPanel();
         frame.getContentPane().add(panelOracleLogin, "name_846939466916758");
         panelOracleLogin.setLayout(null);
         panelOracleLogin.setVisible(true);
         
         JLabel lblOracleDatabaseLogin = new JLabel("Oracle Database Login");
         lblOracleDatabaseLogin.setBounds(458, 153, 300, 30);
         lblOracleDatabaseLogin.setHorizontalAlignment(SwingConstants.CENTER);
         lblOracleDatabaseLogin.setVerticalAlignment(SwingConstants.CENTER);
         lblOracleDatabaseLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
         panelOracleLogin.add(lblOracleDatabaseLogin);
         
         JLabel lblUsername = new JLabel("Username");
         lblUsername.setBounds(573, 194, 75, 20);
         lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
         panelOracleLogin.add(lblUsername);
         
         txtUsername = new JTextField();
         txtUsername.setBounds(533, 215, 152, 20);
         txtUsername.setColumns(10);
         panelOracleLogin.add(txtUsername);
         
         JLabel lblPassword = new JLabel("Password");
         lblPassword.setBounds(573, 252, 75, 20);
         lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
         panelOracleLogin.add(lblPassword);
         
         pwdPassword = new JPasswordField();
         pwdPassword.setBounds(533, 271, 152, 20);
         pwdPassword.setEchoChar('*');
         panelOracleLogin.add(pwdPassword);
         
         JButton btnLogin = new JButton("Login");
         btnLogin.setBounds(573, 308, 75, 25);
         btnLogin.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 
                 username = txtUsername.getText();
                 password = String.valueOf(pwdPassword.getPassword()); /* Not secure at all! */
                 
                 /* Error check for empty fields */
                 if(username.equals("") || password.equals(""))
                 {
                     System.out.println("Login credentials invalid");
                     
                     /* TODO Generate error dialog box */
                 }
                 else
                 {
                     System.out.println("Login credentials: " + username + " " + password);
                     
                     hdb.setOracleCredentials(username, password);
                     
                     /* Switch to next view only if database connection is made */
                     if(hdb.isConnected())
                     {
                         /* Switch to User Class panel when login achieved*/
                         panelOracleLogin.setVisible(false);
                         panelUserClass.setVisible(true);
                         panelAdministrator.setVisible(false);
                     }
                     else
                     {
                         /* TODO Generate error dialog box */
                         System.out.println("Login Failed!");
                     }
                 }     
             }
         });
         panelOracleLogin.add(btnLogin);
    }
    
    private void userClassPanel()
    {
        panelUserClass = new JPanel();
        frame.getContentPane().add(panelUserClass, "name_846941917719548");
        panelUserClass.setLayout(null);
        panelUserClass.setVisible(false);
        
        JLabel lblUserClass = new JLabel("User Class");
        lblUserClass.setBounds(10, 11, 100, 23);
        panelUserClass.add(lblUserClass);
        
        JComboBox comboBox = new JComboBox(userClass);
        comboBox.setBounds(79, 12, 169, 20);
        panelUserClass.add(comboBox);
        
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
            	String userClass = comboBox.getSelectedItem().toString();
            	
                System.out.println();
                
                switch(userClass)
                {
                	case "Administrator": 
                		System.out.println("Administrator Class Selected");
                		
                		/* Switch to Administrator Class panel */
                        panelOracleLogin.setVisible(false);
                        panelUserClass.setVisible(false);
                        panelAdministrator.setVisible(true);
                        panelDoctor.setVisible(false);
                        
                		break;
                	case "Doctor": 
                		System.out.println("Doctor Class Selected");
                		
                		/* Switch to Administrator Class panel */
                        panelOracleLogin.setVisible(false);
                        panelUserClass.setVisible(false);
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(true);
                        
                		break;
                		
                	/* TODO Fill out other user classes */
                		
                	default:
                		break;
                }
            }
        });
        btnConfirm.setBounds(261, 11, 89, 23);
        panelUserClass.add(btnConfirm);
        
        
    }
    
    private void adminClassPanel()
    {
    	panelAdministrator = new JPanel();
        frame.getContentPane().add(panelAdministrator, "name_1380417227282800");
        panelAdministrator.setLayout(null);
        
        JLabel lblDatabaseAdministrator = new JLabel("Database Administrator");
        lblDatabaseAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatabaseAdministrator.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDatabaseAdministrator.setBounds(528, 0, 270, 36);
        panelAdministrator.add(lblDatabaseAdministrator);
        
        JButton button = new JButton("Select User Class");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/* Switch to User Class panel */
                panelOracleLogin.setVisible(false);
                panelUserClass.setVisible(true);
                panelAdministrator.setVisible(false);
                panelDoctor.setVisible(false);
        	}
        });
        button.setBounds(10, 10, 140, 25);
        panelAdministrator.add(button);
    }
    
    private void doctorClassPanel()
    {
    	panelDoctor = new JPanel();
        frame.getContentPane().add(panelDoctor, "name_1380421390345600");
        panelDoctor.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Doctor");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(507, 12, 123, 25);
        panelDoctor.add(lblNewLabel);
        
        JButton btnSelClass = new JButton("Select User Class");
        btnSelClass.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/* Switch to User Class panel */
                panelOracleLogin.setVisible(false);
                panelUserClass.setVisible(true);
                panelAdministrator.setVisible(false);
                panelDoctor.setVisible(false);
        	}
        });
        btnSelClass.setBounds(10, 10, 140, 25);
        panelDoctor.add(btnSelClass);
    }
}
