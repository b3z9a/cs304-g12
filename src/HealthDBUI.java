import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HealthDBUI extends JFrame {

    static HealthDB hdb;
    String username;
    String password;

    private String userClass[] = {"Administrator", "Doctor", "Pharmacist", "LabTechnician", "Patient", "Invoice"};

    private static JFrame frame;
    private static int width = 720;
    private static int height = 900;

    GridBagConstraints gbc;

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

    private JPanel panelDoctorFinder;
    private JPanel panelDoctorInfo;
    private JPanel panelDoctorPrescriptions;
    private JPanel panelDoctorTests;
    private JPanel panelDoctorReferrals;
    private JPanel panelDoctorActions;

    JTextField txtDocName;
    JTextField txtDocPID;
    JTextField txtDocAddr;
    JTextField txtDocMobileNum;
    JTextField txtDocHomeNum;

    private HashMap<String, String> doctorMap;


    public static void main(String args[]) {
        hdb = new HealthDB();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HealthDBUI ui = new HealthDBUI();
                ui.frame.setVisible(true);
            }
        });
    }

    public HealthDBUI() {
        initialize();
        setPanelOracleLogin();
        setPanelUserClass();
        setPanelUserClassSelect();
        setPanelUserClassInfo();

        setPanelDoctor();
        setPanelDoctorFinder();
        setPanelDoctorInfo();
        setPanelDoctorPrescriptions();
        setPanelDoctorTests();
        setPanelDoctorReferrals();
        setPanelDoctorActions();
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

        doctorMap = new HashMap<String, String>();
    }

    /**
     * Initializes the Oracle Login panel
     */
    private void setPanelOracleLogin() {
        panelOracleLogin = new JPanel();
        panelOracleLogin.setLayout(new GridBagLayout());
        panelOracleLogin.setEnabled(true);
        panelRoot.add(panelOracleLogin, "Card1");



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

                    JOptionPane.showMessageDialog(frame, "Login Failed: Username or Password missing!", "Login Failed Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    System.out.println("Login credentials: " + username + " " + password);

                    hdb.setOracleCredentials(username, password);

                    /* Switch to next view only if database connection is made */
                    if (hdb.connectToDB(username, password)) {
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
                        System.out.println("Login Failed!");
                        JOptionPane.showMessageDialog(frame, "Login Failed: Database could not connect!", "Login Failed Error", JOptionPane.ERROR_MESSAGE);
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
        panelDoctor.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelDoctor, "Card2");

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

    private void setPanelDoctor() {
        lblDoctor = new JLabel("Doctor");
        lblDoctor.setFont(new Font("Arial", Font.BOLD, 20));
        lblDoctor.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelDoctor.add(lblDoctor, gbc);

        /* Row 1 */
        panelDoctorFinder = new JPanel();
        panelDoctorFinder.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelDoctor.add(panelDoctorFinder, gbc);

        /* Row 2 */
        JLabel lblPersonalInfo = new JLabel("Personal Information", SwingConstants.LEADING);
        lblPersonalInfo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelDoctor.add(lblPersonalInfo, gbc);

        /* Row 3 */
        panelDoctorInfo = new JPanel();
        panelDoctorInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelDoctor.add(panelDoctorInfo, gbc);

        /* Row 4 */
        JLabel lblPrescriptions = new JLabel("Prescriptions", SwingConstants.LEADING);
        lblPrescriptions.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelDoctor.add(lblPrescriptions, gbc);


        /* Row 5 */
        panelDoctorPrescriptions = new JPanel();
        panelDoctorPrescriptions.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelDoctor.add(panelDoctorPrescriptions, gbc);

        /* Row 6 */
        JLabel lblTests = new JLabel("Tests", SwingConstants.LEADING);
        lblTests.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 6;
        panelDoctor.add(lblTests, gbc);

        /* Row 7 */
        panelDoctorTests = new JPanel();
        panelDoctorTests.setSize(new Dimension(width-20, 150));
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 7;
        panelDoctor.add(panelDoctorTests, gbc);

        /* Row 8 */
        JLabel lblReferrals = new JLabel("Referrals", SwingConstants.LEADING);
        lblReferrals.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 8;
        panelDoctor.add(lblReferrals, gbc);

        /* Row 9 */
        panelDoctorReferrals = new JPanel();
        panelDoctorReferrals.setSize(new Dimension(width-20, 200));
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 9;
        panelDoctor.add(panelDoctorReferrals, gbc);

        /* Row 10 */
        panelDoctorActions= new JPanel();
        panelDoctorActions.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 10;
        panelDoctor.add(panelDoctorActions, gbc);
    }

    private void setPanelDoctorFinder() {
        JLabel lblPID = new JLabel("PID:", SwingConstants.LEADING);
        panelDoctorFinder.add(lblPID, gbc);

        JTextField txtPID = new JTextField(10);
        panelDoctorFinder.add(txtPID);

        JLabel lblName = new JLabel("Name:", SwingConstants.LEADING);
        panelDoctorFinder.add(lblName);

        JTextField txtName = new JTextField(12);
        panelDoctorFinder.add(txtName);

        JButton btnFindDoctor = new JButton();
        btnFindDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorMap = hdb.findDoctor(txtPID.getText(), txtName.getText());

                System.out.println(doctorMap.get("docPID") + ", " + doctorMap.get("docName"));

                txtDocName.setText(doctorMap.get("Name"));
                txtDocPID.setText(doctorMap.get("PID"));
                txtDocAddr.setText(doctorMap.get("Addr"));
                txtDocHomeNum.setText(doctorMap.get("HomeNum"));
                txtDocMobileNum.setText(doctorMap.get("MobileNum"));

                /* TODO Update prescription, test and referral panels */
                hdb.getPrescriptions(doctorMap.get("PID"));
                hdb.getTests(doctorMap.get("PID"));
                hdb.getReferrals(doctorMap.get("PID"));
            }
        });
        btnFindDoctor.setText("Find Doctor");
        panelDoctorFinder.add(btnFindDoctor);
    }

    private void setPanelDoctorInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelDoctorInfo.add(lbl, gbc);

        txtDocName = new JTextField(12);
        txtDocName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelDoctorInfo.add(txtDocName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelDoctorInfo.add(lbl, gbc);

        txtDocPID = new JTextField(10);
        txtDocPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelDoctorInfo.add(txtDocPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelDoctorInfo.add(lbl, gbc);

        txtDocAddr = new JTextField(26);
        txtDocAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelDoctorInfo.add(txtDocAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelDoctorInfo.add(lbl, gbc);

        txtDocMobileNum = new JTextField(10);
        txtDocMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelDoctorInfo.add(txtDocMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelDoctorInfo.add(lbl, gbc);

        txtDocHomeNum = new JTextField(10);
        txtDocHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelDoctorInfo.add(txtDocHomeNum, gbc);
    }

    private void setPanelDoctorPrescriptions() {

        JLabel lbl = new JLabel("ID");
        lbl.setPreferredSize(new Dimension(50, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);

        lbl = new JLabel("Date");
        lbl.setPreferredSize(new Dimension(75, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);

        lbl = new JLabel("Medication");
        lbl.setPreferredSize(new Dimension(150, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);

        lbl = new JLabel("Dosage");
        lbl.setPreferredSize(new Dimension(75, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 3; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);

        lbl = new JLabel("Quantity");
        lbl.setPreferredSize(new Dimension(75, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 4; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);

        lbl = new JLabel("Status");
        lbl.setPreferredSize(new Dimension(50, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 5; gbc.gridy = 1;
        panelDoctorPrescriptions.add(lbl, gbc);
    }

    private void setPanelDoctorTests() {

        JLabel lbl = new JLabel("ID");
        lbl.setPreferredSize(new Dimension(50, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        panelDoctorTests.add(lbl, gbc);

        lbl = new JLabel("Date");
        lbl.setPreferredSize(new Dimension(75, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 0;
        panelDoctorTests.add(lbl, gbc);

        lbl = new JLabel("Status");
        lbl.setPreferredSize(new Dimension(150, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2; gbc.gridy = 0;
        panelDoctorTests.add(lbl, gbc);

    }

    private void setPanelDoctorReferrals() {

        JLabel lbl = new JLabel("Doctor");
        lbl.setPreferredSize(new Dimension(150, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        panelDoctorReferrals.add(lbl, gbc);

        lbl = new JLabel("Specialization");
        lbl.setPreferredSize(new Dimension(100, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 0;
        panelDoctorReferrals.add(lbl, gbc);

        lbl = new JLabel("Date");
        lbl.setPreferredSize(new Dimension(75, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2; gbc.gridy = 0;
        panelDoctorReferrals.add(lbl, gbc);

    }

    private void setPanelDoctorActions() {
        JButton btnCreatePrescription = new JButton();
        btnCreatePrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hdb.createPrescription();
            }
        });
        btnCreatePrescription.setText("Create New Prescription");
        panelDoctorActions.add(btnCreatePrescription);

        JButton btnRenewPrescription = new JButton();
        btnRenewPrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hdb.renewPrescription();
            }
        });
        btnRenewPrescription.setText("Renew Prescription");
        panelDoctorActions.add(btnRenewPrescription);

        JButton btnCreateTest= new JButton();
        btnCreateTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hdb.createTest();
            }
        });
        btnCreateTest.setText("Create New Test");
        panelDoctorActions.add(btnCreateTest);

        JButton btnCreateReferral = new JButton();
        btnCreateReferral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hdb.createReferral();
            }
        });
        btnCreateReferral.setText("Create New Referral");
        panelDoctorActions.add(btnCreateReferral);

    }

}
