import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthDBUI extends JFrame {

    static HealthDB hdb;
    String username;
    String password;

    private String userClass[] = {"Administrator", "Doctor", "Pharmacist", "LabTechnician", "Patient", "Invoice"};

    private static JFrame frame;
    private static int width = 1280;
    private static int height = 720;


    private JPanel panelRoot;
    private JPanel panelOracleLogin;
    private JPanel panelUserClass;
    private JPanel panelUserClassInfo;
    private JPanel panelAdministrator;
    private JPanel panelDoctor;
    private JPanel panelPharmacist;
    private JPanel panelLabTech;
    private JPanel panelPatient;
    private JPanel panelInvoice;
    private JPanel panelEmpty;


    private JLabel lblOracleLogin;
    private JLabel lblUsername;
    private JTextField usernameField;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JButton btnLogin;

    private JPanel panelUserClassSelect;
    private JLabel lblUserClassSelect;
    private JComboBox cboxUserClass;
    private JButton btnConfirmUserClass;

    private JLabel lblAdministrator;
    private JLabel lblDoctor;
    private JLabel lblPharmacist;
    private JLabel lblLabTech;
    private JLabel lblPatient;
    private JLabel lblInvoice;
    private JButton btnClear;


    public static void main(String args[]) {
        hdb = new HealthDB();

        HealthDBUI ui = new HealthDBUI();
        ui.frame.setVisible(true);
    }

    public HealthDBUI() {
        initialize();
        setPanelOracleLogin();
        setPanelUserClass();
        setPanelUserClassSelect();
        setPanelUserClassInfo();
    }

    /**
     * initialize()
     * Initializes the window as well as the combo box selections
     */
    private void initialize() {
        frame = new JFrame("Integrated Healthcare Database");
        frame.setBounds(0, 0, width, height);
        panelRoot = new JPanel();
        panelRoot.setLayout(new CardLayout(0, 0));
        frame.setContentPane(panelRoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cboxUserClass = new JComboBox(userClass);
    }

    /**
     * Initializes the Oracle Login panel
     */
    private void setPanelOracleLogin() {
        panelOracleLogin = new JPanel();
        panelOracleLogin.setLayout(new GridBagLayout());
        panelOracleLogin.setEnabled(true);
        panelRoot.add(panelOracleLogin, "Card1");

        GridBagConstraints gbc;

        lblOracleLogin = new JLabel();
        lblOracleLogin.setHorizontalAlignment(0);
        lblOracleLogin.setHorizontalTextPosition(0);
        lblOracleLogin.setText("Oracle Database Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lblOracleLogin, gbc);

        lblUsername = new JLabel();
        lblUsername.setHorizontalAlignment(4);
        lblUsername.setText("Username  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lblUsername, gbc);

        usernameField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(usernameField, gbc);

        lblPassword = new JLabel();
        lblPassword.setHorizontalAlignment(4);
        lblPassword.setText("Password  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lblPassword, gbc);

        passwordField = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(passwordField, gbc);

        btnLogin = new JButton();
        btnLogin.setHorizontalTextPosition(0);
        btnLogin.setPreferredSize(new Dimension(50, 30));
        btnLogin.setText("Login");
        /* Login button action listener */
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                password = String.valueOf(passwordField.getPassword()); /* Not secure at all! */

                /* Error check for empty fields */
                if (username.equals("") || password.equals("")) {
                    System.out.println("Login credentials invalid");

                    /* TODO Generate error dialog box */
                }
                else {
                    System.out.println("Login credentials: " + username + " " + password);

                    hdb.setOracleCredentials(username, password);

                    /* Switch to next view only if database connection is made */
                    if (hdb.connectToDB()) {
                        /* Switch to User Class panel when login achieved */
                        panelOracleLogin.setVisible(false);
                        panelUserClass.setVisible(true);
                        panelEmpty.setVisible(true);
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                    }
                    else {
                        /* TODO Generate error dialog box */
                        System.out.println("Login Failed!");
                    }
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(btnLogin, gbc);
    }

    /**
     * Initializes the main container for both User Class Selection and User Class Info panels
     */
    private void setPanelUserClass() {
        panelUserClass = new JPanel();
        panelUserClass.setLayout(new BorderLayout(0, 0));
        panelRoot.add(panelUserClass, "Card2");
    }

    /**
     * Initializes the User Class Selection panel
     */
    private void setPanelUserClassSelect() {
        panelUserClassSelect = new JPanel();
        panelUserClassSelect.setLayout(new FlowLayout());
        panelUserClass.add(panelUserClassSelect, BorderLayout.NORTH);

        lblUserClassSelect = new JLabel();
        lblUserClassSelect.setText("Select User Class");

        btnConfirmUserClass = new JButton();
        /* Confirm User Class button action listener */
        btnConfirmUserClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userClass = cboxUserClass.getSelectedItem().toString();

                System.out.println();

                switch (userClass) {
                    case "Administrator":
                        System.out.println("Administrator Class Selected");

                        hdb.setUserClass("Administrator");

                        /* Switch to Administrator Class panel */
                        panelAdministrator.setVisible(true);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Doctor":
                        System.out.println("Doctor Class Selected");

                        hdb.setUserClass("Doctor");

                        /* Switch to Doctor Class panel */
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(true);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Pharmacist":
                        System.out.println("Pharmacist Class Selected");

                        hdb.setUserClass("Pharmacist");

                        /* Switch to Pharmacist Class panel */
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(true);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "LabTechnician":
                        System.out.println("LabTechnician Class Selected");

                        hdb.setUserClass("LabTechnician");

                        /* Switch to LabTechnician Class panel */
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(true);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Patient":
                        System.out.println("Patient Class Selected");

                        hdb.setUserClass("Patient");

                        /* Switch to Patient Class panel */
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(true);
                        panelInvoice.setVisible(false);
                        break;
                    case "Invoice":
                        System.out.println("Invoice Class Selected");

                        hdb.setUserClass("Invoice");

                        /* Switch to Patient Class panel */
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(true);
                        break;
                    default:
                        panelAdministrator.setVisible(false);
                        panelDoctor.setVisible(false);
                        panelPharmacist.setVisible(false);
                        panelLabTech.setVisible(false);
                        panelPatient.setVisible(false);
                        panelInvoice.setVisible(false);
                        panelEmpty.setVisible(true);
                        break;
                }
            }
        });
        btnConfirmUserClass.setText("Confirm");

        btnClear = new JButton();
        /* Clear Selections button action listener */
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEmpty.setVisible(true);
                panelAdministrator.setVisible(false);
                panelDoctor.setVisible(false);
                panelPharmacist.setVisible(false);
                panelLabTech.setVisible(false);
                panelPatient.setVisible(false);
                panelInvoice.setVisible(false);

                /* TODO see if anything else needs to be done when clear is pressed */
            }
        });
        btnClear.setText("Clear Selection");

        panelUserClassSelect.add(lblUserClassSelect);
        panelUserClassSelect.add(cboxUserClass);
        panelUserClassSelect.add(btnConfirmUserClass);
        panelUserClassSelect.add(btnClear);

    }

    /**
     * Initializes the User Class Info panel
     */
    private void setPanelUserClassInfo(){
        panelUserClassInfo = new JPanel();
        panelUserClassInfo.setLayout(new CardLayout(0, 0));
        panelUserClass.add(panelUserClassInfo, BorderLayout.CENTER);

        panelAdministrator = new JPanel();
        panelUserClassInfo.add(panelAdministrator, "Card1");
        lblAdministrator = new JLabel();
        lblAdministrator.setText("Administrator");
        panelAdministrator.add(lblAdministrator);

        panelDoctor = new JPanel();
        panelUserClassInfo.add(panelDoctor, "Card2");
        lblDoctor = new JLabel();
        lblDoctor.setText("Doctor");
        panelDoctor.add(lblDoctor);

        panelPharmacist = new JPanel();
        panelUserClassInfo.add(panelPharmacist, "Card3");
        lblPharmacist = new JLabel();
        lblPharmacist.setText("Pharmacist");
        panelPharmacist.add(lblPharmacist);

        panelLabTech = new JPanel();
        panelUserClassInfo.add(panelLabTech, "Card4");
        lblLabTech = new JLabel();
        lblLabTech.setText("Lab Technician");
        panelLabTech.add(lblLabTech);

        panelPatient = new JPanel();
        panelUserClassInfo.add(panelPatient, "Card5");
        lblPatient = new JLabel();
        lblPatient.setText("Patient");
        panelPatient.add(lblPatient);

        panelInvoice = new JPanel();
        panelUserClassInfo.add(panelInvoice, "Card6");
        lblInvoice = new JLabel();
        lblInvoice.setText("Invoice");
        panelInvoice.add(lblInvoice);

        panelEmpty = new JPanel();
        panelUserClassInfo.add(panelEmpty, "Card7");
    }

}
