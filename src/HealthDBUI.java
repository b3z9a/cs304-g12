import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * TODO
 * Create a dialog box to select from multiple patients
 * Format dates as MM-DD-YYYY (SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");)
 * Create a dropdown menu for Invoice panel for payment status and methods
 */

public class HealthDBUI extends JFrame {

    static HealthDB hdb;
    private String username;
    private String password;

    private final String drHID = "52731";   // Dr. Melissa Clark
    private String patientID;

    private String userClass[] = {"Patient Summary", "Plan Summary", "Prescriptions", "Tests"};

    private static JFrame frame;
    private static int width = 720;
    private static int height = 720;

    GridBagConstraints gbc;

    private JPanel panelRoot = new JPanel();
    private JPanel panelOracleLogin;
    private JPanel panelUserClass;
    private JPanel panelUserClassInfo;
    private JPanel panelPatientSummary;
    private JPanel panelPrescription;
    private JPanel panelTest;
    private JPanel panelPlanSummary;
    private JPanel panelInvoice;
    private JPanel panelEmpty;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JPanel panelUserClassSelect;
    private JComboBox cboxUserClass = new JComboBox(userClass);
    private JButton btnConfirmUserClass;

    private JPanel panelPatientSummaryFinder;
    private JPanel panelPatientSummaryInfo;
    private JPanel panelPatientSummaryPrescriptions;
    private JPanel panelPatientSummaryTests;
    private JPanel panelPatientSummaryReferrals;
    private JPanel panelPatientSummaryActions;

    private JPanel panelPrescriptionFinder;
    private JPanel panelPrescriptionInfo;
    private JPanel panelPrescriptionPrescriptions;

    private JPanel panelTestFinder;
    private JPanel panelTestInfo;
    private JPanel panelTestTests;

    private JPanel panelInvoiceFinder;
    private JPanel panelInvoiceInfo;
    private JPanel panelInvoiceSubmit;
    
    private JPanel panelPlanSummaryFinder;
    private JPanel panelPlanSummaryInfo;
    private JPanel panelPlanSummaryTests;
    private JPanel panelPlanSummaryPrescriptions;
    private JPanel panelPlanSummaryReferrals;

    private JTextField txtInvoiceID;
    private JTextField txtInvoicePlanID;
    private JTextField txtInvoicePatientName;
    
    private JTextField txtPatientName;
    private JTextField txtPatientPID;
    private JTextField txtPatientAddress;
    private JTextField txtPatientMobilePhone;
    private JTextField txtPatientHomePhone;

    private JTextField txtDocName;
    private JTextField txtDocPID;
    private JTextField txtDocAddr;
    private JTextField txtDocMobileNum;
    private JTextField txtDocHomeNum;

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

        setPanelPatientSummary();
        setPanelPatientSummaryFinder();
        setPanelPatientSummaryInfo();
        setPanelPatientSummaryPrescriptions();
        setPanelPatientSummaryTests();
        setPanelPatientSummaryReferrals();
        setPanelPatientSummaryActions();

        setPanelPrescription();
        setPanelPrescriptionFinder();
        setPanelPrescriptionInfo();
        setPanelPrescriptionPrescriptions();

        setPanelTest();
        setPanelTestFinder();
        setPanelTestInfo();
        setPanelTestTests();

        setPanelInvoice();
        setPanelInvoiceFinder();
        setPanelInvoiceInfo();
        setPanelInvoiceSubmit();
        
        setPanelPlanSummary();
        setPanelPlanSummaryFinder();
        setPanelPlanSummaryInfo();
        setPanelPlanSummaryTests();
        setPanelPlanSummaryPrescriptions();
        setPanelPlanSummaryReferrals();
    }

    /**
     * initialize()
     * Initializes the window as well as the combo box selections
     */
    private void initialize() {
        frame = new JFrame("Integrated Healthcare Database");
        frame.setBounds(0, 0, width, height);
        panelRoot.setLayout(new CardLayout(0, 0));
        frame.setContentPane(panelRoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(0);
        lbl.setHorizontalTextPosition(0);
        lbl.setText("Oracle Database Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lbl, gbc);

        lbl = new JLabel();
        lbl.setHorizontalAlignment(4);
        lbl.setText("Username  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lbl, gbc);

        usernameField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(usernameField, gbc);

        lbl = new JLabel();
        lbl.setHorizontalAlignment(4);
        lbl.setText("Password  ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lbl, gbc);

        passwordField = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(passwordField, gbc);

        JButton btnLogin = new JButton();
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

        JLabel lbl = new JLabel();
        lbl.setText("Select Page to View: ");

        btnConfirmUserClass = new JButton();
        /* Confirm User Class button action listener */
        btnConfirmUserClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userClass = cboxUserClass.getSelectedItem().toString();

                System.out.println();

                switch (userClass) {
                    case "Patient Summary":
                        System.out.println("Patient Summary");

                        /* Switch to Patient Summary panel */
                        panelPatientSummary.setVisible(true);
                        panelPrescription.setVisible(false);
                        panelTest.setVisible(false);
                        panelPlanSummary.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Prescriptions":
                        System.out.println("Prescriptions");

                        /* Switch to Pharmacist Class panel */
                        panelPatientSummary.setVisible(false);
                        panelPrescription.setVisible(true);
                        panelTest.setVisible(false);
                        panelPlanSummary.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Tests":
                        System.out.println("Tests");

                        /* Switch to Tests panel */
                        panelPatientSummary.setVisible(false);
                        panelPrescription.setVisible(false);
                        panelTest.setVisible(true);
                        panelPlanSummary.setVisible(false);
                        panelInvoice.setVisible(false);
                        break;
                    case "Plan Summary":
                        System.out.println("Plan Summary");

                        /* Switch to Plan Summary panel */
                        panelPatientSummary.setVisible(false);
                        panelPrescription.setVisible(false);
                        panelTest.setVisible(false);
                        panelPlanSummary.setVisible(true);
                        panelInvoice.setVisible(false);
                        break;
                    default:
                        panelPatientSummary.setVisible(false);
                        panelPrescription.setVisible(false);
                        panelTest.setVisible(false);
                        panelPlanSummary.setVisible(false);
                        panelInvoice.setVisible(false);
                        panelEmpty.setVisible(true);
                        break;
                }
            }
        });
        btnConfirmUserClass.setText("Confirm");

        JButton btnClear = new JButton();
        /* Clear Selections button action listener */
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEmpty.setVisible(true);
                panelPatientSummary.setVisible(false);
                panelPrescription.setVisible(false);
                panelTest.setVisible(false);
                panelPlanSummary.setVisible(false);
                panelInvoice.setVisible(false);

                /* TODO see if anything else needs to be done when clear is pressed */
            }
        });
        btnClear.setText("Clear Selection");

        panelUserClassSelect.add(lbl);
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

        panelPatientSummary = new JPanel();
        panelPatientSummary.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelPatientSummary, "Card2");

        panelPrescription = new JPanel();
        panelPrescription.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelPrescription, "Card3");

        panelTest = new JPanel();
        panelTest.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelTest, "Card4");

        panelPlanSummary = new JPanel();
        panelPlanSummary.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelPlanSummary, "Card5");

        panelInvoice = new JPanel();
        panelInvoice.setLayout(new GridBagLayout());
        panelUserClassInfo.add(panelInvoice, "Card6");

        panelEmpty = new JPanel();
        panelUserClassInfo.add(panelEmpty, "Card7");
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

    private void setPanelPatientSummary() {
        JLabel lbl = new JLabel("Patient Summary");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelPatientSummary.add(lbl, gbc);

        /* Row 1 */
        panelPatientSummaryFinder = new JPanel();
        panelPatientSummaryFinder.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelPatientSummary.add(panelPatientSummaryFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Personal Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelPatientSummary.add(lbl, gbc);

        /* Row 3 */
        panelPatientSummaryInfo = new JPanel();
        panelPatientSummaryInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelPatientSummary.add(panelPatientSummaryInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Prescriptions", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelPatientSummary.add(lbl, gbc);


        /* Row 5 */
        panelPatientSummaryPrescriptions = new JPanel();
        panelPatientSummaryPrescriptions.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelPatientSummary.add(panelPatientSummaryPrescriptions, gbc);

        /* Row 6 */
        lbl = new JLabel("Tests", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 6;
        panelPatientSummary.add(lbl, gbc);

        /* Row 7 */
        panelPatientSummaryTests = new JPanel();
        panelPatientSummaryTests.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 7;
        panelPatientSummary.add(panelPatientSummaryTests, gbc);

        /* Row 8 */
        lbl = new JLabel("Referrals", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 8;
        panelPatientSummary.add(lbl, gbc);

        /* Row 9 */
        panelPatientSummaryReferrals = new JPanel();
        panelPatientSummaryReferrals.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 9;
        panelPatientSummary.add(panelPatientSummaryReferrals, gbc);

        /* Row 10 */
        panelPatientSummaryActions= new JPanel();
        panelPatientSummaryActions.setLayout(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 10;
        panelPatientSummary.add(panelPatientSummaryActions, gbc);
    }
    private void setPanelPatientSummaryFinder() {
        JLabel lblPID = new JLabel("PID:", SwingConstants.LEADING);
        panelPatientSummaryFinder.add(lblPID, gbc);

        JTextField txtPID = new JTextField(10);
        panelPatientSummaryFinder.add(txtPID);

        JLabel lblName = new JLabel("Name:", SwingConstants.LEADING);
        panelPatientSummaryFinder.add(lblName);

        JTextField txtName = new JTextField(12);
        panelPatientSummaryFinder.add(txtName);

        JButton btnFindDoctor = new JButton();
        btnFindDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorArr = hdb.findDoctor(txtPID.getText(), txtName.getText());

                patientID = doctorArr.get(2);
                String name = doctorArr.get(0) + " " + doctorArr.get(1);
                String addr = doctorArr.get(3) + " " + doctorArr.get(4) + " " + doctorArr.get(6) + " " + doctorArr.get(5);

                System.out.println(doctorArr.get(2) + ", " + name);

                txtDocName.setText(name);
                txtDocPID.setText(doctorArr.get(2));
                txtDocAddr.setText(addr);
                txtDocHomeNum.setText(doctorArr.get(7));
                txtDocMobileNum.setText(doctorArr.get(8));

                /* TODO Update prescription, test and referral panels */
                prescriptions = hdb.getPrescriptionWithPIDName(doctorArr.get(2), name);
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
        btnFindDoctor.setText("Find Patient");
        panelPatientSummaryFinder.add(btnFindDoctor);
    }
    private void setPanelPatientSummaryInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelPatientSummaryInfo.add(lbl, gbc);

        txtDocName = new JTextField(12);
        txtDocName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelPatientSummaryInfo.add(txtDocName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelPatientSummaryInfo.add(lbl, gbc);

        txtDocPID = new JTextField(10);
        txtDocPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelPatientSummaryInfo.add(txtDocPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelPatientSummaryInfo.add(lbl, gbc);

        txtDocAddr = new JTextField(26);
        txtDocAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelPatientSummaryInfo.add(txtDocAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelPatientSummaryInfo.add(lbl, gbc);

        txtDocMobileNum = new JTextField(10);
        txtDocMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelPatientSummaryInfo.add(txtDocMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelPatientSummaryInfo.add(lbl, gbc);

        txtDocHomeNum = new JTextField(10);
        txtDocHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelPatientSummaryInfo.add(txtDocHomeNum, gbc);
    }
    private void setPanelPatientSummaryPrescriptions() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelPatientSummaryPrescriptions.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPatientSummaryPrescriptions.add(presTable, BorderLayout.CENTER);
    }
    private void setPanelPatientSummaryTests() {

        String cols[] = {"ID", "Date", "Status"};
        String data[][] = {};
        JTable testTable = new JTable(data, cols);
        panelPatientSummaryTests.add(testTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPatientSummaryTests.add(testTable, BorderLayout.CENTER);

    }
    private void setPanelPatientSummaryReferrals() {

        String cols[] = {"Doctor", "Specialization", "Date"};
        String data[][] = {};
        JTable refTable = new JTable(data, cols);
        panelPatientSummaryReferrals.add(refTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPatientSummaryReferrals.add(refTable, BorderLayout.CENTER);

    }
    private void setPanelPatientSummaryActions() {
        JButton btnCreatePrescription = new JButton();
        btnCreatePrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medication = "";
                String dosage = "";
                String qty = "";
                hdb.createPrescription(patientID, drHID, medication, dosage, qty);
            }
        });
        btnCreatePrescription.setText("Create New Prescription");
        panelPatientSummaryActions.add(btnCreatePrescription);

        JButton btnCreateTest= new JButton();
        btnCreateTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hdb.createEmptyTest(drHID, patientID);
            }
        });
        btnCreateTest.setText("Create New Test");
        panelPatientSummaryActions.add(btnCreateTest);

        JButton btnCreateReferral = new JButton();
        btnCreateReferral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String referreeHID = "54337"; // TODO create dialog to ask for referree

                hdb.createReferral(patientID, drHID, referreeHID);
            }
        });
        btnCreateReferral.setText("Create New Referral");
        panelPatientSummaryActions.add(btnCreateReferral);

        JButton btnDeletePatient = new JButton();
        btnDeletePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Add error dialog box when empty patient
                hdb.deletePatient(patientID);
            }
        });
        btnDeletePatient.setText("Delete Patient");
        panelPatientSummaryActions.add(btnDeletePatient);

    }

    private void setPanelPrescription() {
        JLabel lbl = new JLabel("Prescription Information");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrescription.add(lbl, gbc);

        /* Row 1 */
        panelPrescriptionFinder = new JPanel();
        panelPrescriptionFinder.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrescription.add(panelPrescriptionFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Personal Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelPrescription.add(lbl, gbc);

        /* Row 3 */
        panelPrescriptionInfo = new JPanel();
        panelPrescriptionInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelPrescription.add(panelPrescriptionInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Prescriptions", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelPrescription.add(lbl, gbc);


        /* Row 5 */
        panelPrescriptionPrescriptions = new JPanel();
        panelPrescriptionPrescriptions.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelPrescription.add(panelPrescriptionPrescriptions, gbc);
    }
    private void setPanelPrescriptionFinder() {
        JLabel lbl = new JLabel("Prescription #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrescriptionFinder.add(lbl, gbc);

        JTextField txtPrescNum = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 0;
        panelPrescriptionFinder.add(txtPrescNum, gbc);

        JButton btnFindPrescNum = new JButton();
        btnFindPrescNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.getPrescriptionWithNumber(txtPrescNum.getText());

                /* TODO get prescriptions */
            }
        });
        btnFindPrescNum.setText("Find by Prescription Number");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 4; gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrescriptionFinder.add(btnFindPrescNum, gbc);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrescriptionFinder.add(lbl, gbc);

        JTextField txtPID = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1; gbc.gridy = 1;
        panelPrescriptionFinder.add(txtPID, gbc);

        lbl = new JLabel("Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 2; gbc.gridy = 1;
        panelPrescriptionFinder.add(lbl, gbc);

        JTextField txtName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 3; gbc.gridy = 1;
        panelPrescriptionFinder.add(txtName, gbc);

        JButton btnFindPrescPatient = new JButton();
        btnFindPrescPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object obj = hdb.getPrescriptionWithPIDName(txtPID.getText(), txtName.getText());
            }
        });
        btnFindPrescPatient.setText("Find by Patient ID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 4; gbc.gridy = 1;
        panelPrescriptionFinder.add(btnFindPrescPatient, gbc);
    }
    private void setPanelPrescriptionInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelPrescriptionInfo.add(lbl, gbc);

        txtPharmName = new JTextField(12);
        txtPharmName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelPrescriptionInfo.add(txtPharmName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelPrescriptionInfo.add(lbl, gbc);

        txtPharmPID = new JTextField(10);
        txtPharmPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelPrescriptionInfo.add(txtPharmPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelPrescriptionInfo.add(lbl, gbc);

        txtPharmAddr = new JTextField(26);
        txtPharmAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelPrescriptionInfo.add(txtPharmAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelPrescriptionInfo.add(lbl, gbc);

        txtPharmMobileNum = new JTextField(10);
        txtPharmMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelPrescriptionInfo.add(txtPharmMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelPrescriptionInfo.add(lbl, gbc);

        txtPharmHomeNum = new JTextField(10);
        txtPharmHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelPrescriptionInfo.add(txtPharmHomeNum, gbc);

    }
    private void setPanelPrescriptionPrescriptions() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status", "Fill"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelPrescriptionPrescriptions.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPrescriptionPrescriptions.add(presTable, BorderLayout.CENTER);
    }

    private void setPanelTest() {
        JLabel lbl = new JLabel("Test Information");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelTest.add(lbl, gbc);

        /* Row 1 */
        panelTestFinder = new JPanel();
        panelTestFinder.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        panelTest.add(panelTestFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Personal Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelTest.add(lbl, gbc);

        /* Row 3 */
        panelTestInfo = new JPanel();
        panelTestInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        panelTest.add(panelTestInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Tests", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelTest.add(lbl, gbc);


        /* Row 5 */
        panelTestTests = new JPanel();
        panelTestTests.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelTest.add(panelTestTests, gbc);
    }
    private void setPanelTestFinder() {
        JLabel lbl = new JLabel("Test #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0; gbc.gridy = 0;
        panelTestFinder.add(lbl, gbc);

        JTextField txtTest = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 0;
        panelTestFinder.add(txtTest, gbc);

        JButton btnFindTestNum = new JButton();
        btnFindTestNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.getTests(txtTest.getText());

                /* TODO get tests */
            }
        });
        btnFindTestNum.setText("Find by Test Number");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 4; gbc.gridy = 0;
        panelTestFinder.add(btnFindTestNum, gbc);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0; gbc.gridy = 1;
        panelTestFinder.add(lbl, gbc);

        JTextField txtPID = new JTextField(8);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1; gbc.gridy = 1;
        panelTestFinder.add(txtPID, gbc);

        lbl = new JLabel("Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 2; gbc.gridy = 1;
        panelTestFinder.add(lbl, gbc);

        JTextField txtName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 3; gbc.gridy = 1;
        panelTestFinder.add(txtName, gbc);

        JButton btnFindTests = new JButton();
        btnFindTests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.getTests(txtPID.getText());

                /* TODO get tests */
            }
        });
        btnFindTests.setText("Find by Patient ID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 4; gbc.gridy = 1;
        panelTestFinder.add(btnFindTests, gbc);
    }
    private void setPanelTestInfo() {
        JLabel lbl = new JLabel("Name");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelTestInfo.add(lbl, gbc);

        txtLabName = new JTextField(12);
        txtLabName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelTestInfo.add(txtLabName, gbc);

        lbl = new JLabel("PID");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelTestInfo.add(lbl, gbc);

        txtLabPID = new JTextField(10);
        txtLabPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelTestInfo.add(txtLabPID, gbc);

        lbl = new JLabel("Address");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelTestInfo.add(lbl, gbc);

        txtLabAddr = new JTextField(26);
        txtLabAddr.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelTestInfo.add(txtLabAddr, gbc);

        lbl = new JLabel("Mobile Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 6;
        panelTestInfo.add(lbl, gbc);

        txtLabMobileNum = new JTextField(10);
        txtLabMobileNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 1; gbc.gridy = 6;
        panelTestInfo.add(txtLabMobileNum, gbc);

        lbl = new JLabel("Home Num");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 6;
        panelTestInfo.add(lbl, gbc);

        txtLabHomeNum = new JTextField(10);
        txtLabHomeNum.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 6;
        panelTestInfo.add(txtLabHomeNum, gbc);

    }
    private void setPanelTestTests() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status", "Fill"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelTestTests.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelTestTests.add(presTable, BorderLayout.CENTER);
    }

    private void setPanelInvoice() {
        JLabel lbl = new JLabel("Invoice");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelInvoice.add(lbl, gbc);

        /* Row 1 */
        panelInvoiceFinder = new JPanel();
        panelInvoiceFinder.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 1;
        panelInvoice.add(panelInvoiceFinder, gbc);

        /* Row 2 */
        lbl = new JLabel("Invoice Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelInvoice.add(lbl, gbc);

        /* Row 3 */
        panelInvoiceInfo = new JPanel();
        panelInvoiceInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 3;
        panelInvoice.add(panelInvoiceInfo, gbc);

        /* Row 4 */
        lbl = new JLabel("Submit Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelInvoice.add(lbl, gbc);

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
    
    
    private void setPanelPlanSummary() {
        JLabel lbl = new JLabel("Plan Summary");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelPlanSummary.add(lbl, gbc);
        
        /* Row 1 */
        panelPlanSummaryFinder = new JPanel();
        panelPlanSummaryFinder.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 1;
        panelPlanSummary.add(panelPlanSummaryFinder, gbc);
        

        /* Row 2 */
        lbl = new JLabel("Patient Information", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        panelPlanSummary.add(lbl, gbc);

        /* Row 3 */
        panelPlanSummaryInfo = new JPanel();
        panelPlanSummaryInfo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0; gbc.gridy = 3;
        panelPlanSummary.add(panelPlanSummaryInfo, gbc);
        
        /* Row 4 */
        lbl = new JLabel("Tests", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 4;
        panelPlanSummary.add(lbl, gbc);

        /* Row 5 */
        panelPlanSummaryTests = new JPanel();
        panelPlanSummaryTests.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 5;
        panelPlanSummary.add(panelPlanSummaryTests, gbc);
        
        /* Row 6 */
        lbl = new JLabel("Prescriptions", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 6;
        panelPlanSummary.add(lbl, gbc);

        /* Row 7 */
        panelPlanSummaryPrescriptions = new JPanel();
        panelPlanSummaryPrescriptions.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 7;
        panelPlanSummary.add(panelPlanSummaryPrescriptions, gbc);

        /* Row 8 */
        lbl = new JLabel("Referrals", SwingConstants.LEADING);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 8;
        panelPlanSummary.add(lbl, gbc);

        /* Row 9 */
        panelPlanSummaryReferrals = new JPanel();
        panelPlanSummaryReferrals.setLayout(new BorderLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 9;
        panelPlanSummary.add(panelPlanSummaryReferrals, gbc);
    }
    private void setPanelPlanSummaryFinder() {
        JLabel lbl = new JLabel("HID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 0;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtHID = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 0;
        panelPlanSummaryFinder.add(txtHID, gbc);

        lbl = new JLabel("Staff Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 0;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtStaffName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 0;
        panelPlanSummaryFinder.add(txtStaffName, gbc);

        lbl = new JLabel("Invoice #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 4; gbc.gridy = 0;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtInvoiceNum = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 5; gbc.gridy = 0;
        panelPlanSummaryFinder.add(txtInvoiceNum, gbc);

        lbl = new JLabel("PID:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0; gbc.gridy = 1;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtPID = new JTextField(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 1; gbc.gridy = 1;
        panelPlanSummaryFinder.add(txtPID, gbc);

        lbl = new JLabel("Name:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2; gbc.gridy = 1;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtPatientName = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 3; gbc.gridy = 1;
        panelPlanSummaryFinder.add(txtPatientName, gbc);

        
        lbl = new JLabel("Plan #:", SwingConstants.LEADING);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 4; gbc.gridy = 1;
        panelPlanSummaryFinder.add(lbl, gbc);

        JTextField txtPlanNum = new JTextField(12);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 5; gbc.gridy = 1;
        panelPlanSummaryFinder.add(txtPlanNum, gbc);

        JButton btnFindPatient = new JButton();
        btnFindPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = hdb.findPatient(txtHID.getText(), txtStaffName.getText(), txtInvoiceNum.getText(),
                        txtPID.getText(), txtPatientName.getText(), txtPlanNum.getText());

                /* TODO get tests */
            }
        });
        btnFindPatient.setText("Find Patient");
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 2; gbc.gridy = 2;
        panelPlanSummaryFinder.add(btnFindPatient, gbc);
    }
    private void setPanelPlanSummaryInfo() {
        JLabel lbl = new JLabel("Name:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 3;
        panelPlanSummaryInfo.add(lbl, gbc);

        txtPatientName = new JTextField(12);
        txtPatientName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 3;
        panelPlanSummaryInfo.add(txtPatientName, gbc);

        lbl = new JLabel("PID:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 3;
        panelPlanSummaryInfo.add(lbl, gbc);

        txtPatientPID = new JTextField(12);
        txtPatientPID.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 3;
        panelPlanSummaryInfo.add(txtPatientPID, gbc);

        lbl = new JLabel("Address:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 4;
        panelPlanSummaryInfo.add(lbl, gbc);

        txtPatientAddress = new JTextField(12);
        txtPatientAddress.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 4;
        panelPlanSummaryInfo.add(txtPatientAddress, gbc);

        // mobile
        lbl = new JLabel("Mobile Phone:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 0; gbc.gridy = 5;
        panelPlanSummaryInfo.add(lbl, gbc);

        txtPatientMobilePhone = new JTextField(12);
        txtPatientMobilePhone.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 5;
        panelPlanSummaryInfo.add(txtPatientMobilePhone, gbc);

        // home
        lbl = new JLabel("Home Phone:");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 2; gbc.gridy = 5;
        panelPlanSummaryInfo.add(lbl, gbc);

        txtPatientHomePhone = new JTextField(12);
        txtPatientHomePhone.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets= new Insets(0,5,0,0);
        gbc.gridx = 3; gbc.gridy = 5;
        panelPlanSummaryInfo.add(txtPatientHomePhone, gbc);
        

    }
    private void setPanelPlanSummaryTests() {

        String cols[] = {"ID", "Date", "Status"};
        String data[][] = {};
        JTable testTable = new JTable(data, cols);
        panelPlanSummaryTests.add(testTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPlanSummaryTests.add(testTable, BorderLayout.CENTER);

    }
    private void setPanelPlanSummaryPrescriptions() {
        String cols[] = {"ID", "Date", "Medication", "Dosage", "Quantity", "Status"};
        String data[][] = {};
        JTable presTable = new JTable(data, cols);
        panelPlanSummaryPrescriptions.add(presTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPlanSummaryPrescriptions.add(presTable, BorderLayout.CENTER);
    }
    private void setPanelPlanSummaryReferrals() {

        String cols[] = {"Doctor", "Specialization", "Date"};
        String data[][] = {};
        JTable refTable = new JTable(data, cols);
        panelPlanSummaryReferrals.add(refTable.getTableHeader(), BorderLayout.PAGE_START);
        panelPlanSummaryReferrals.add(refTable, BorderLayout.CENTER);

    }
    
    private void switchToUserSelectPanel() {
        /* Switch to User Class panel when login achieved */
        panelOracleLogin.setVisible(false);
        panelUserClass.setVisible(true);
        panelEmpty.setVisible(true);
        panelPatientSummary.setVisible(false);
        panelPrescription.setVisible(false);
        panelTest.setVisible(false);
        panelPlanSummary.setVisible(false);
        panelInvoice.setVisible(false);
    }

}
