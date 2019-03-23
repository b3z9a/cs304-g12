import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class HealthDBUI extends JFrame {

    static HealthDB hdb;
    String username;
    String password;

    private String userClass[] = {"Administrator", "Doctor", "Pharmacist", "LabTechnician", "Patient", "Invoice"};

    private static JFrame frame;
    private static int width = 720;
    private static int height = 720;

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

    private JPanel panelPharmacistFinder;
    private JPanel panelPharmacistInfo;
    private JPanel panelPharmacistPrescriptions;

    private JPanel panelLabFinder;
    private JPanel panelLabInfo;
    private JPanel panelLabTests;

    private JPanel panelInvoiceFinder;
    private JPanel panelInvoiceInfo;
    private JPanel panelInvoiceSubmit;

    JTextField txtInvoiceID;
    JTextField txtInvoicePID;
    JTextField txtInvoicePlanID;
    JTextField txtInvoicePatientName;



    // private HashMap<String, String> invoiceMap;

    JTextField txtDocName;
    JTextField txtDocPID;
    JTextField txtDocAddr;
    JTextField txtDocMobileNum;
    JTextField txtDocHomeNum;

    private ArrayList<String> doctorArr;
    private ArrayList<ArrayList<String>> prescriptions;
    private ArrayList<ArrayList<String>> tests;
    private ArrayList<ArrayList<String>> referrals;

    private JTextField txtPharmName;
    private JTextField txtPharmPID;
    private JTextField txtPharmAddr;
    private JTextField txtPharmHomeNum;
    private JTextField txtPharmMobileNum;

    private JTextField txtLabName;
    private JTextField txtLabPID;
    private JTextField txtLabAddr;
    private JTextField txtLabHomeNum;
    private JTextField txtLabMobileNum;

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

        setPanelPharmacist();
        setPanelPharmacistFinder();
        setPanelPharmacistInfo();
        setPanelPharmacistPrescriptions();

        setPanelLabTech();
        setPanelLabFinder();
        setPanelLabInfo();
        setPanelLabTests();

        setPanelInvoice();
        setPanelInvoiceFinder();
        setPanelInvoiceInfo();
        setPanelInvoiceSubmit();
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

        doctorArr = new ArrayList<String>();
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
                else if (username.equals("1") || password.equals("1")) {

                    /* Bypass Oracle DB connection for local testing */
                    switchToUserSelectPanel();
                }
                else {
                    //System.out.println("Login credentials: " + username + " " + password);

                    hdb.setOracleCredentials(username, password);

                    /* Switch to next view only if database connection is made */
                    if (hdb.connectToDB(username, password)) {

                        /* Switch to User Class panel when login achieved */
                        switchToUserSelectPanel();
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
        panelPharmacist.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelPharmacist, "Card3");

        panelLabTech = new JPanel();
        panelLabTech.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelLabTech, "Card4");

        panelPatient = new JPanel();
        panelUserClassInfo.add(panelPatient, "Card5");
        lblPatient = new JLabel();
        lblPatient.setText("Patient");
        panelPatient.add(lblPatient);

        panelInvoice = new JPanel();
        panelInvoice.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelInvoice, "Card6");

        // panelInvoice = new JPanel();
        // panelUserClassInfo.add(panelInvoice, "Card6");
        //  lblInvoice = new JLabel();
        // lblInvoice.setText("Invoice");
        //panelInvoice.add(lblInvoice);

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
        panelDoctorPrescriptions.setLayout(new BorderLayout());
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
        panelDoctorTests.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
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
        panelDoctorReferrals.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
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
                doctorArr = hdb.findDoctor(txtPID.getText(), txtName.getText());
                String name = doctorArr.get(0) + " " + doctorArr.get(1);
                String addr = doctorArr.get(3) + " " + doctorArr.get(4) + " " + doctorArr.get(6) + " " + doctorArr.get(5);

                System.out.println(doctorArr.get(2) + ", " + name);

                txtDocName.setText(name);
                txtDocPID.setText(doctorArr.get(2));
                txtDocAddr.setText(addr);
                txtDocHomeNum.setText(doctorArr.get(7));
                txtDocMobileNum.setText(doctorArr.get(8));

                /* TODO Update prescription, test and referral panels */
                prescriptions = hdb.getPrescriptions(doctorArr.get(2));
                System.out.println("Prescriptions:");
                printTuples(prescriptions);

                System.out.println("Tests:");
                tests = hdb.getTests(doctorArr.get(2));
                printTuples(tests);

                System.out.println("Referrals:");
                referrals = hdb.getTests(doctorArr.get(2));
                printTuples(referrals);
            }
        });
        btnFindDoctor.setText("Find Doctor");
        panelDoctorFinder.add(btnFindDoctor);
    }

    private void printTuples(ArrayList<ArrayList<String>> tuples){
      StringBuilder sb = new StringBuilder();
      for (ArrayList<String> list : tuples){
        for (String s : list){
          sb.append(s);
        }
        sb.append("\n");
      }
      System.out.println(sb.toString());
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
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelDoctorPrescriptions.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelDoctorPrescriptions.add(presTable, BorderLayout.CENTER);
    }

    private void setPanelDoctorTests() {

        String cols[] = {"ID", "Date", "Status"};
        String data[][] = {};
        JTable testTable = new JTable(data, cols);
        panelDoctorTests.add(testTable.getTableHeader(), BorderLayout.PAGE_START);
        panelDoctorTests.add(testTable, BorderLayout.CENTER);

    }

    private void setPanelDoctorReferrals() {

        String cols[] = {"Doctor", "Specialization", "Date"};
        String data[][] = {};
        JTable refTable = new JTable(data, cols);
        panelDoctorReferrals.add(refTable.getTableHeader(), BorderLayout.PAGE_START);
        panelDoctorReferrals.add(refTable, BorderLayout.CENTER);

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

    private void setPanelPharmacist() {
        JLabel lbl = new JLabel("Pharmacist");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelPharmacist.add(lbl, gbc);

        /* Row 1 */
        panelPharmacistFinder = new JPanel();
        panelPharmacistFinder.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelPharmacist.add(panelPharmacistFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Personal Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelPharmacist.add(lbl, gbc);

        /* Row 3 */
        panelPharmacistInfo = new JPanel();
        panelPharmacistInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelPharmacist.add(panelPharmacistInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Prescriptions", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelPharmacist.add(lbl, gbc);


        /* Row 5 */
        panelPharmacistPrescriptions = new JPanel();
        panelPharmacistPrescriptions.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelPharmacist.add(panelPharmacistPrescriptions, gbc);
    }

    private void setPanelPharmacistFinder() {
        JLabel lbl = new JLabel("Prescription #:", SwingConstants.LEADING);
        panelPharmacistFinder.add(lbl);

        JTextField txtPrescription = new JTextField(10);
        panelPharmacistFinder.add(txtPrescription);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        panelPharmacistFinder.add(lbl);

        JTextField txtPID = new JTextField(8);
        panelPharmacistFinder.add(txtPID);

        lbl = new JLabel("Name:", SwingConstants.LEADING);
        panelPharmacistFinder.add(lbl);

        JTextField txtName = new JTextField(12);
        panelPharmacistFinder.add(txtName);

        JButton btnFindPrescription = new JButton();
        btnFindPrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.findPrescription(txtPrescription.getText(), txtPID.getText(), txtName.getText());

                /* TODO get prescriptions */
            }
        });
        btnFindPrescription.setText("Find Prescription");
        panelPharmacistFinder.add(btnFindPrescription);
    }

    private void setPanelPharmacistInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelPharmacistInfo.add(lbl, gbc);

        txtPharmName = new JTextField(12);
        txtPharmName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelPharmacistInfo.add(txtPharmName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelPharmacistInfo.add(lbl, gbc);

        txtPharmPID = new JTextField(10);
        txtPharmPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelPharmacistInfo.add(txtPharmPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelPharmacistInfo.add(lbl, gbc);

        txtPharmAddr = new JTextField(26);
        txtPharmAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelPharmacistInfo.add(txtPharmAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelPharmacistInfo.add(lbl, gbc);

        txtPharmMobileNum = new JTextField(10);
        txtPharmMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelPharmacistInfo.add(txtPharmMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelPharmacistInfo.add(lbl, gbc);

        txtPharmHomeNum = new JTextField(10);
        txtPharmHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelPharmacistInfo.add(txtPharmHomeNum, gbc);

    }

    private void setPanelPharmacistPrescriptions() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status", "Fill"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelPharmacistPrescriptions.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPharmacistPrescriptions.add(presTable, BorderLayout.CENTER);
    }

    private void setPanelLabTech() {
        JLabel lbl = new JLabel("Lab Technician");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelLabTech.add(lbl, gbc);

        /* Row 1 */
        panelLabFinder = new JPanel();
        panelLabFinder.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelLabTech.add(panelLabFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Personal Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelLabTech.add(lbl, gbc);

        /* Row 3 */
        panelLabInfo = new JPanel();
        panelLabInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelLabTech.add(panelLabInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Prescriptions", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelLabTech.add(lbl, gbc);


        /* Row 5 */
        panelLabTests = new JPanel();
        panelLabTests.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelLabTech.add(panelLabTests, gbc);
    }

    private void setPanelLabFinder() {
        JLabel lbl = new JLabel("Test #:", SwingConstants.LEADING);
        panelLabFinder.add(lbl);

        JTextField txtTest = new JTextField(10);
        panelLabFinder.add(txtTest);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        panelLabFinder.add(lbl);

        JTextField txtPID = new JTextField(8);
        panelLabFinder.add(txtPID);

        lbl = new JLabel("Name:", SwingConstants.LEADING);
        panelLabFinder.add(lbl);

        JTextField txtName = new JTextField(12);
        panelLabFinder.add(txtName);

        JButton btnFindTests = new JButton();
        btnFindTests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.findTest(txtTest.getText(), txtPID.getText(), txtName.getText());

                /* TODO get tests */
            }
        });
        btnFindTests.setText("Find Tests");
        panelLabFinder.add(btnFindTests);
    }

    private void setPanelLabInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelLabInfo.add(lbl, gbc);

        txtLabName = new JTextField(12);
        txtLabName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelLabInfo.add(txtLabName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelLabInfo.add(lbl, gbc);

        txtLabPID = new JTextField(10);
        txtLabPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelLabInfo.add(txtLabPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelLabInfo.add(lbl, gbc);

        txtLabAddr = new JTextField(26);
        txtLabAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelLabInfo.add(txtLabAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelLabInfo.add(lbl, gbc);

        txtLabMobileNum = new JTextField(10);
        txtLabMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelLabInfo.add(txtLabMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelLabInfo.add(lbl, gbc);

        txtLabHomeNum = new JTextField(10);
        txtLabHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelLabInfo.add(txtLabHomeNum, gbc);

    }

    private void setPanelLabTests() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status", "Fill"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelLabTests.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelLabTests.add(presTable, BorderLayout.CENTER);
    }

    private void setPanelInvoice() {
        lblInvoice = new JLabel("Invoice");
        lblInvoice.setFont(new Font("Arial", Font.BOLD, 20));
        lblInvoice.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelInvoice.add(lblInvoice, gbc);

        /* Row 1 */
        panelInvoiceFinder = new JPanel();
        panelInvoiceFinder.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 1;
        panelInvoice.add(panelInvoiceFinder, gbc);

        /* Row 2 */
        JLabel lblInvoiceInfo = new JLabel("Invoice Information", SwingConstants.LEADING);
        lblInvoiceInfo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelInvoice.add(lblInvoiceInfo, gbc);

        /* Row 3 */
        panelInvoiceInfo = new JPanel();
        panelInvoiceInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 3;
        panelInvoice.add(panelInvoiceInfo, gbc);

        /* Row 4 */
        JLabel lblSubmitInvoiceInfo = new JLabel("Submit Information", SwingConstants.LEADING);
        lblSubmitInvoiceInfo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelInvoice.add(lblSubmitInvoiceInfo, gbc);

        /* Row 5 */
        panelInvoiceSubmit = new JPanel();
        panelInvoiceSubmit.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelInvoice.add(panelInvoiceSubmit, gbc);

        /* Bottom Row, fill the space */
        JPanel panel = new JPanel();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 6;
        panelInvoice.add(panel, gbc);

    }

    private void setPanelInvoiceFinder() {
        JLabel lbl = new JLabel("HID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtHID = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 0;
        panelInvoiceFinder.add(txtHID, gbc);

        lbl = new JLabel("Staff Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 0;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtStaffName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 0;
        panelInvoiceFinder.add(txtStaffName, gbc);

        lbl = new JLabel("Invoice #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 4; gbc.gridy = 0;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtInvoiceNum = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 5; gbc.gridy = 0;
        panelInvoiceFinder.add(txtInvoiceNum, gbc);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 1;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtPID = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 1;
        panelInvoiceFinder.add(txtPID, gbc);

        lbl = new JLabel("Patient Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 1;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtPatientName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 1;
        panelInvoiceFinder.add(txtPatientName, gbc);

        lbl = new JLabel("Plan #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 4; gbc.gridy = 1;
        panelInvoiceFinder.add(lbl, gbc);

        JTextField txtPlanNum = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 5; gbc.gridy = 1;
        panelInvoiceFinder.add(txtPlanNum, gbc);

        JButton btnFindInvoice = new JButton();
        btnFindInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.findInvoice(txtHID.getText(), txtStaffName.getText(), txtInvoiceNum.getText(),
                        txtPID.getText(), txtPatientName.getText(), txtPlanNum.getText());

                /* TODO get tests */
            }
        });
        btnFindInvoice.setText("Find Invoices");
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 2; gbc.gridy = 2;
        panelInvoiceFinder.add(btnFindInvoice, gbc);
    }

    private void setPanelInvoiceInfo() {
        JLabel lbl = new JLabel("Invoice ID:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelInvoiceInfo.add(lbl, gbc);

        txtInvoiceID = new JTextField(12);
        txtInvoiceID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelInvoiceInfo.add(txtInvoiceID, gbc);

        lbl = new JLabel("Plan ID:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelInvoiceInfo.add(lbl, gbc);

        txtInvoicePlanID = new JTextField(12);
        txtInvoicePlanID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelInvoiceInfo.add(txtInvoicePlanID, gbc);

        lbl = new JLabel("Patient ID:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelInvoiceInfo.add(lbl, gbc);

        txtInvoicePlanID = new JTextField(12);
        txtInvoicePlanID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelInvoiceInfo.add(txtInvoicePlanID, gbc);

        lbl = new JLabel("Patient Name:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 4;
        panelInvoiceInfo.add(lbl, gbc);

        txtInvoicePatientName = new JTextField(12);
        txtInvoicePatientName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 4;
        panelInvoiceInfo.add(txtInvoicePatientName, gbc);


/*
        lbl = new JLabel("Creation Date:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 10;
        panelInvoiceInfo.add(lbl, gbc);

        txtInvoiceCreationDate = new JTextField(10);
        txtInvoiceCreationDate.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 10;
        panelInvoiceInfo.add(txtInvoiceCreationDate, gbc);*/

    }

    private void setPanelInvoiceSubmit() {
        JLabel lbl = new JLabel("Creation Date:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtCreationDate = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 0;
        panelInvoiceSubmit.add(txtCreationDate, gbc);

        lbl = new JLabel("Due Date:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 1;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtDueDate = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 1;
        panelInvoiceSubmit.add(txtDueDate, gbc);

        lbl = new JLabel("Invoice item:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 2;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtInvoiceItem = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 2;
        panelInvoiceSubmit.add(txtInvoiceItem, gbc);

        lbl = new JLabel("Payment Status:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 0;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtPaymentStatus = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 0;
        panelInvoiceSubmit.add(txtPaymentStatus, gbc);

        lbl = new JLabel("Payment Date:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 1;
        panelInvoiceSubmit.add(lbl, gbc);

        JTextField txtPaymentDate = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 1;
        panelInvoiceSubmit.add(txtPaymentDate, gbc);

        lbl = new JLabel("Payment Method:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 2;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtPaymentMethod = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 2;
        panelInvoiceSubmit.add(txtPaymentMethod, gbc);

        lbl = new JLabel("Amount Owing:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelInvoiceSubmit.add(lbl, gbc);

        final JTextField txtAmountOwing = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelInvoiceSubmit.add(txtAmountOwing, gbc);

        JButton btnSubmitInvoice = new JButton();
        btnSubmitInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.submitInvoice(txtCreationDate.getText(), txtDueDate.getText(), txtInvoiceItem.getText(),
                        txtPaymentStatus.getText(), txtPaymentDate.getText(), txtPaymentMethod.getText(),
                        txtAmountOwing.getText());

                // submitInvoice(String creationDate, String dueDate, String invoiceItem,
                //                              String paymentStatus, String paymentDate, String paymentMethod, String amountOwing)

                /* TODO get tests */
            }
        });
        btnSubmitInvoice.setText("Submit Invoices");
        gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 1; gbc.gridy = 4;
        panelInvoiceSubmit.add(btnSubmitInvoice, gbc);
    }

    private void switchToUserSelectPanel() {
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

}
