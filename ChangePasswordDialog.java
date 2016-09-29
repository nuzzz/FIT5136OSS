import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChangePasswordDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    
    private JTextField oldPassword;
    private JTextField password;
    private JTextField confirmPassword;

    public static void display(ShopController c){
        ChangePasswordDialog dialog = new ChangePasswordDialog(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
        
    }
    
    //not done
    public Customer toCustomer(Customer user){
        //Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber, boolean premiumStatus){
        return new Customer(user.getUsername(), password.getText(), user.getName(), user.getEmail(), user.getAddress(), user.getPhoneNumber() , user.getCardNumber(), user.getPremiumStatus());
    }
    
    public ChangePasswordDialog(ShopController c) {
        Customer user = c.getCurrentUserDetails();
        
        setTitle("User Information");
        setBounds(100, 100, 450, 300);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        //old password
        {
            JLabel lblOldPassword = new JLabel("Old Password:");
            lblOldPassword.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
            gbc_lblOldPassword.anchor = GridBagConstraints.EAST;
            gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
            gbc_lblOldPassword.gridx = 1;
            gbc_lblOldPassword.gridy = 1;
            contentPanel.add(lblOldPassword, gbc_lblOldPassword);
        }
        {
            oldPassword = new JTextField();
            GridBagConstraints gbc_oldPassword = new GridBagConstraints();
            gbc_oldPassword.insets = new Insets(0, 0, 5, 5);
            gbc_oldPassword.fill = GridBagConstraints.HORIZONTAL;
            gbc_oldPassword.gridx = 2;
            gbc_oldPassword.gridy = 1;
            contentPanel.add(oldPassword, gbc_oldPassword);
            oldPassword.setColumns(10);
            oldPassword.setText(user.getPassword());
        }
        //new password
        {
            JLabel lblPassword = new JLabel("New password:");
            GridBagConstraints gbc_lblPassword = new GridBagConstraints();
            gbc_lblPassword.anchor = GridBagConstraints.EAST;
            gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
            gbc_lblPassword.gridx = 1;
            gbc_lblPassword.gridy = 2;
            contentPanel.add(lblPassword, gbc_lblPassword);
        }
        {
            password = new JTextField();
            GridBagConstraints gbc_password = new GridBagConstraints();
            gbc_password.insets = new Insets(0, 0, 5, 5);
            gbc_password.fill = GridBagConstraints.HORIZONTAL;
            gbc_password.gridx = 2;
            gbc_password.gridy = 2;
            contentPanel.add(password, gbc_password);
            password.setColumns(10);
        }
        //confirm password
        {
            JLabel lblConfirmPassword = new JLabel("Confirm New password:");
            GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
            gbc_lblConfirmPassword.anchor = GridBagConstraints.EAST;
            gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
            gbc_lblConfirmPassword.gridx = 1;
            gbc_lblConfirmPassword.gridy = 3;
            contentPanel.add(lblConfirmPassword, gbc_lblConfirmPassword);
        }
        {
            confirmPassword = new JTextField();
            GridBagConstraints gbc_confirmPassword = new GridBagConstraints();
            gbc_confirmPassword.insets = new Insets(0, 0, 5, 5);
            gbc_confirmPassword.fill = GridBagConstraints.HORIZONTAL;
            gbc_confirmPassword.gridx = 2;
            gbc_confirmPassword.gridy = 3;
            contentPanel.add(confirmPassword, gbc_confirmPassword);
            confirmPassword.setColumns(10);
        }
        
        //buttons
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JDialog me = this;
                JButton okButton = new JButton("Save new password");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        boolean isValid = false;
                        isValid = c.verifyPassword(oldPassword.getText(), password.getText(), confirmPassword.getText());
                        if(isValid){
                            c.updateUserDetails(toCustomer(user));
                            me.dispose();
                        }
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JDialog me = this;
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
