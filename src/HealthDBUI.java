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
    private JPanel panelUserClassInfo;
    private JPanel panelAdministrator;
    private JPanel panelDoctor;
    private JPanel panelPharmacist;
    private JPanel panelLabTech;
    private JPanel panelPatient;
    private JPanel panelInvoice;
    private JLabel lblAdministrator;
    private JLabel lblDoctor;
    private JLabel lblPharmacist;
    private JLabel lblLabTech;
    private JLabel lblPatient;
    private JLabel lblInvoice;
    private JPanel panelEmpty;
    private JButton btnClear;
    private JPanel panelUserClassSelection;


    public static void main(String args[]) {
        hdb = new HealthDB();

        HealthDBUI ui = new HealthDBUI();
        ui.frame.setVisible(true);
    }

    public HealthDBUI() {
        initialize();

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

        /* Confirm User Class button action listener */
        btnConfirmUserClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userClass = cboxUserClass.getSelectedItem().toString();

                System.out.println();

                switch (userClass) {
                    case "Administrator":
                        System.out.println("Administrator Class Selected");

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
    }

    /**
     * initialize()
     * Initializes the window as well as the combo box selections
     */
    private void initialize() {
        frame = new JFrame("Integrated Healthcare Database");
        frame.setBounds(0, 0, width, height);
        frame.setContentPane(panelRoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Add items to the empty JComboBox */
        for (String str : userClass) {
            cboxUserClass.addItem(str);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelRoot = new JPanel();
        panelRoot.setLayout(new CardLayout(0, 0));
        panelOracleLogin = new JPanel();
        panelOracleLogin.setLayout(new GridBagLayout());
        panelOracleLogin.setEnabled(true);
        panelRoot.add(panelOracleLogin, "Card1");
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelOracleLogin.add(spacer1, gbc);
        lblUsername = new JLabel();
        lblUsername.setText("Username");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelOracleLogin.add(lblUsername, gbc);
        usernameField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(usernameField, gbc);
        lblOracleLogin = new JLabel();
        Font lblOracleLoginFont = this.$$$getFont$$$(null, -1, 18, lblOracleLogin.getFont());
        if (lblOracleLoginFont != null) lblOracleLogin.setFont(lblOracleLoginFont);
        lblOracleLogin.setHorizontalAlignment(0);
        lblOracleLogin.setHorizontalTextPosition(0);
        lblOracleLogin.setText("Oracle Database Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lblOracleLogin, gbc);
        lblPassword = new JLabel();
        lblPassword.setHorizontalAlignment(4);
        lblPassword.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelOracleLogin.add(lblPassword, gbc);
        passwordField = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(passwordField, gbc);
        btnLogin = new JButton();
        btnLogin.setHorizontalTextPosition(0);
        btnLogin.setPreferredSize(new Dimension(50, 30));
        btnLogin.setText("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(btnLogin, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelOracleLogin.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelOracleLogin.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelOracleLogin.add(spacer4, gbc);
        panelUserClass = new JPanel();
        panelUserClass.setLayout(new BorderLayout(0, 0));
        panelRoot.add(panelUserClass, "Card2");
        panelUserClassSelect = new JPanel();
        panelUserClassSelect.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClass.add(panelUserClassSelect, BorderLayout.NORTH);
        lblUserClassSelect = new JLabel();
        lblUserClassSelect.setText("Select User Class");
        panelUserClassSelect.add(lblUserClassSelect, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cboxUserClass = new JComboBox();
        panelUserClassSelect.add(cboxUserClass, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConfirmUserClass = new JButton();
        btnConfirmUserClass.setText("Confirm");
        panelUserClassSelect.add(btnConfirmUserClass, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panelUserClassSelect.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnClear = new JButton();
        btnClear.setText("Clear Selection");
        panelUserClassSelect.add(btnClear, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUserClassInfo = new JPanel();
        panelUserClassInfo.setLayout(new CardLayout(0, 0));
        panelUserClass.add(panelUserClassInfo, BorderLayout.CENTER);
        panelAdministrator = new JPanel();
        panelAdministrator.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelAdministrator, "Card1");
        lblAdministrator = new JLabel();
        lblAdministrator.setHorizontalAlignment(0);
        lblAdministrator.setHorizontalTextPosition(0);
        lblAdministrator.setText("Administrator");
        panelAdministrator.add(lblAdministrator, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panelAdministrator.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelDoctor = new JPanel();
        panelDoctor.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelDoctor, "Card2");
        lblDoctor = new JLabel();
        lblDoctor.setText("Doctor");
        panelDoctor.add(lblDoctor, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panelDoctor.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelPharmacist = new JPanel();
        panelPharmacist.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelPharmacist, "Card3");
        lblPharmacist = new JLabel();
        lblPharmacist.setText("Pharmacist");
        panelPharmacist.add(lblPharmacist, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panelPharmacist.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelLabTech = new JPanel();
        panelLabTech.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelLabTech, "Card4");
        lblLabTech = new JLabel();
        lblLabTech.setText("Lab Technician");
        panelLabTech.add(lblLabTech, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        panelLabTech.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelPatient = new JPanel();
        panelPatient.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelPatient, "Card5");
        lblPatient = new JLabel();
        lblPatient.setText("Patient");
        panelPatient.add(lblPatient, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        panelPatient.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelInvoice = new JPanel();
        panelInvoice.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelInvoice, "Card6");
        lblInvoice = new JLabel();
        lblInvoice.setText("Invoice");
        panelInvoice.add(lblInvoice, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        panelInvoice.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelEmpty = new JPanel();
        panelEmpty.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelUserClassInfo.add(panelEmpty, "Card7");
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        }
        else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            }
            else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelRoot;
    }
}
