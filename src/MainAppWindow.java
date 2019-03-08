import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;

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
    private JPanel panelUserLogin;
    

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
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(0, 0, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        
        panelOracleLogin = new JPanel();
        frame.getContentPane().add(panelOracleLogin, "name_846939466916758");
        
        JLabel lblOracleDatabaseLogin = new JLabel("Oracle Database Login");
        lblOracleDatabaseLogin.setBounds(458, 153, 300, 30);
        lblOracleDatabaseLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblOracleDatabaseLogin.setVerticalAlignment(SwingConstants.CENTER);
        lblOracleDatabaseLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(573, 194, 75, 20);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(533, 215, 152, 20);
        txtUsername.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(573, 252, 75, 20);
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        
        pwdPassword = new JPasswordField();
        pwdPassword.setBounds(533, 271, 152, 20);
        pwdPassword.setEchoChar('*');
        
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
                        panelUserLogin.setVisible(true); 
                    }
                    else
                    {
                        /* TODO Generate error dialog box */
                        System.out.println("Login Failed!");
                    }
                }     
            }
        });
        panelOracleLogin.setLayout(null);
        panelOracleLogin.add(lblOracleDatabaseLogin);
        panelOracleLogin.add(lblUsername);
        panelOracleLogin.add(txtUsername);
        panelOracleLogin.add(lblPassword);
        panelOracleLogin.add(pwdPassword);
        panelOracleLogin.add(btnLogin);
        
        panelUserLogin = new JPanel();
        frame.getContentPane().add(panelUserLogin, "name_846941917719548");
        panelUserLogin.setLayout(null);
        
        JLabel lblUserClass = new JLabel("User Class");
        lblUserClass.setBounds(10, 11, 100, 23);
        panelUserLogin.add(lblUserClass);
        
        JComboBox comboBox = new JComboBox(userClass);
        comboBox.setBounds(68, 12, 169, 20);
        panelUserLogin.add(comboBox);
        
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(comboBox.getSelectedItem().toString());
            }
        });
        btnConfirm.setBounds(247, 11, 89, 23);
        panelUserLogin.add(btnConfirm);
    }
}
