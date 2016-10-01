

public class UserDetails extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    
    private JTextField fullName;
    private JTextField phoneNumber;
    private JTextField homeAddr;
    private JTextField email;
    private JTextField cardNum;

    public static void display(ShopController c){
        UserDetails dialog = new UserDetails(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    
    //not done
    public Customer toCustomer(Customer user){
            //Customer(String username, String password, String name, String email, String address, String phoneNumber, String cardNumber, boolean premiumStatus){
        return new Customer(user.getUsername(), user.getPassword(), fullName.getText(), email.getText(), homeAddr.getText(), phoneNumber.getText() , cardNum.getText(), user.getPremiumStatus());
    }
    
    public UserDetails(ShopController c) {
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
        //full name
        {
            JLabel lblFullName = new JLabel("Full name:");
            lblFullName.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblFullName = new GridBagConstraints();
            gbc_lblFullName.anchor = GridBagConstraints.EAST;
            gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
            gbc_lblFullName.gridx = 1;
            gbc_lblFullName.gridy = 0;
            contentPanel.add(lblFullName, gbc_lblFullName);
        }
        {
            fullName = new JTextField();
            GridBagConstraints gbc_fullName = new GridBagConstraints();
            gbc_fullName.insets = new Insets(0, 0, 5, 5);
            gbc_fullName.fill = GridBagConstraints.HORIZONTAL;
            gbc_fullName.gridx = 2;
            gbc_fullName.gridy = 0;
            contentPanel.add(fullName, gbc_fullName);
            fullName.setColumns(10);
            fullName.setText(user.getName());
        }
        //phone number
        {
            JLabel lblPhoneNumber = new JLabel("Phone number:");
            GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
            gbc_lblPhoneNumber.anchor = GridBagConstraints.EAST;
            gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
            gbc_lblPhoneNumber.gridx = 1;
            gbc_lblPhoneNumber.gridy = 1;
            contentPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);
        }
        {
            phoneNumber = new JTextField();
            GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
            gbc_phoneNumber.insets = new Insets(0, 0, 5, 5);
            gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
            gbc_phoneNumber.gridx = 2;
            gbc_phoneNumber.gridy = 1;
            contentPanel.add(phoneNumber, gbc_phoneNumber);
            phoneNumber.setColumns(10);
            phoneNumber.setText(user.getPhoneNumber());
        }
        //home address
        {
            JLabel lblHomeAddress = new JLabel("Home address:");
            lblHomeAddress.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblHomeAddress = new GridBagConstraints();
            gbc_lblHomeAddress.anchor = GridBagConstraints.EAST;
            gbc_lblHomeAddress.insets = new Insets(0, 0, 5, 5);
            gbc_lblHomeAddress.gridx = 1;
            gbc_lblHomeAddress.gridy = 2;
            contentPanel.add(lblHomeAddress, gbc_lblHomeAddress);
        }
        {
            homeAddr = new JTextField();
            GridBagConstraints gbc_homeAddr = new GridBagConstraints();
            gbc_homeAddr.insets = new Insets(0, 0, 5, 5);
            gbc_homeAddr.fill = GridBagConstraints.HORIZONTAL;
            gbc_homeAddr.gridx = 2;
            gbc_homeAddr.gridy = 2;
            contentPanel.add(homeAddr, gbc_homeAddr);
            homeAddr.setColumns(10);
            homeAddr.setText(user.getAddress());
        }
        //email
        {
            JLabel lblEmail = new JLabel("Email:");
            lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblEmail = new GridBagConstraints();
            gbc_lblEmail.anchor = GridBagConstraints.EAST;
            gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
            gbc_lblEmail.gridx = 1;
            gbc_lblEmail.gridy = 3;
            contentPanel.add(lblEmail, gbc_lblEmail);
        }
        {
            email = new JTextField();
            GridBagConstraints gbc_email = new GridBagConstraints();
            gbc_email.insets = new Insets(0, 0, 5, 5);
            gbc_email.fill = GridBagConstraints.HORIZONTAL;
            gbc_email.gridx = 2;
            gbc_email.gridy = 3;
            contentPanel.add(email, gbc_email);
            email.setColumns(15);
            email.setText(user.getEmail());
        }
        
        //card number
        {
            JLabel lblCardNumber = new JLabel("Card number:");
            GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
            gbc_lblCardNumber.anchor = GridBagConstraints.EAST;
            gbc_lblCardNumber.insets = new Insets(0, 0, 0, 5);
            gbc_lblCardNumber.gridx = 1;
            gbc_lblCardNumber.gridy = 4;
            contentPanel.add(lblCardNumber, gbc_lblCardNumber);
        }
        {
            cardNum = new JTextField();
            GridBagConstraints gbc_cardNum = new GridBagConstraints();
            gbc_cardNum.insets = new Insets(0, 0, 0, 5);
            gbc_cardNum.fill = GridBagConstraints.HORIZONTAL;
            gbc_cardNum.gridx = 2;
            gbc_cardNum.gridy = 4;
            contentPanel.add(cardNum, gbc_cardNum);
            cardNum.setColumns(10);
            cardNum.setText(user.getCardNumber());
        }
        
        //buttons
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
				JDialog me = this;
				JButton changePasswordButton = new JButton("Change Password");
				changePasswordButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						ChangePasswordDialog.display(c);
						me.dispose();
					}
				});
				buttonPane.add(changePasswordButton);
			}
            {
                JDialog me = this;
                JButton okButton = new JButton("Save");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        boolean isValid = false;
                        isValid = c.verifyUserDetails(fullName.getText(), phoneNumber.getText(), homeAddr.getText(), email.getText(), cardNum.getText());
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
