import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignupView extends View {

    private static final long serialVersionUID = 1L;
    


    
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JPanel panel_6;
    private JPanel panel_7;
    private JPanel panel_email;
    private JPanel panel_address;
    private JPanel panel_phone;
    private JPanel panel_name;
    private JPanel panel_card;

    private JLabel lblName;
    private JLabel lblUserId;
    private JLabel lblPassword;
    private JLabel lblNewLabel;
    private JLabel lblConfirmPassword;
    private JLabel lblEmail;
    private JLabel lblAddress;
    private JLabel lblPhone;
    private JLabel lblCard;

    private Component verticalStrut;

    private JTextField userID, pwd, userEmail, userAddress, userPhone, userFullname, userCard;
    private JTextField pwdConfirm;
    
    private JButton newAccButton;
    private JButton btnNewButton;

    public SignupView() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        add(panel, gbc_panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel_5 = new JPanel();
        panel.add(panel_5);
        panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
        
        panel_6 = new JPanel();
        panel_5.add(panel_6);
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
        
        lblNewLabel = new JLabel();
        //lblNewLabel.setIcon(ShopController.LOGO_ICON);
        panel_6.add(lblNewLabel);
        
        panel_4 = new JPanel();
        panel_5.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
        
        verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);
        
        panel_name = new JPanel();
        panel_4.add(panel_name);
        FlowLayout flowLayout_0 = (FlowLayout) panel_name.getLayout();
        flowLayout_0.setVgap(0);
        flowLayout_0.setHgap(0);
        flowLayout_0.setAlignment(FlowLayout.LEFT);
        
        lblName = new JLabel("Please enter your full name");
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        panel_name.add(lblName);
        
        userFullname = new JTextField();
        panel_4.add(userFullname);
        userFullname.setColumns(20);
        
        panel_2 = new JPanel();
        panel_4.add(panel_2);
        
        panel_1 = new JPanel();
        panel_4.add(panel_1);
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        flowLayout.setAlignment(FlowLayout.LEFT);
        
        lblUserId = new JLabel("Choose a user ID");
        panel_1.add(lblUserId);
        
        userID = new JTextField();
        panel_4.add(userID);
        userID.setColumns(10);
        
        panel_3 = new JPanel();
        panel_4.add(panel_3);
        FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
        flowLayout_1.setVgap(0);
        flowLayout_1.setHgap(0);
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        
        lblPassword = new JLabel("Choose a password");
        lblPassword.setHorizontalAlignment(SwingConstants.LEFT);        
        panel_3.add(lblPassword);
        
        pwd = new JTextField();
        panel_4.add(pwd);
        pwd.setColumns(10);
        
        panel_7 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_7);
        
        lblConfirmPassword = new JLabel("Confirm your password");
        lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
        panel_7.add(lblConfirmPassword);
        
        pwdConfirm = new JTextField();
        pwdConfirm.setColumns(10);
        panel_4.add(pwdConfirm);
        
        panel_email = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_email.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_email);
        
        lblEmail = new JLabel("Please enter your email");
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        panel_email.add(lblEmail);
        
        userEmail = new JTextField();
        panel_4.add(userEmail);
        userEmail.setColumns(25);
        
        panel_address = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel_address.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_address);
        
        lblAddress = new JLabel("Please enter your address");
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        panel_address.add(lblAddress);
        
        userAddress = new JTextField();
        panel_4.add(userAddress);
        userAddress.setColumns(40);
        
        panel_phone = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel_phone.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_phone);
        
        lblPhone = new JLabel("Please enter your phone");
        lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
        panel_phone.add(lblPhone);
        
        userPhone = new JTextField();
        panel_4.add(userPhone);
        userPhone.setColumns(40);
        
        panel_card = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) panel_phone.getLayout();
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_card);
        
        lblCard = new JLabel("Please enter your Credit card number (12d)");
        lblCard.setHorizontalAlignment(SwingConstants.LEFT);
        panel_card.add(lblCard);
        
        userCard = new JTextField();
        panel_4.add(userCard);
        userCard.setColumns(15);
        
        panel_2 = new JPanel();
        panel_4.add(panel_2);
        
        
        newAccButton = new JButton("Create my account");
        newAccButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getController().signup(userFullname.getText(), userID.getText(), pwd.getText(), pwdConfirm.getText(), 
                userEmail.getText(), userAddress.getText(), userPhone.getText(), userCard.getText());
            }
        });
        
        btnNewButton = new JButton("Back to login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getController().setView(new LoginView());
            }
        });
        
        panel_2.add(btnNewButton);
        panel_2.add(newAccButton);
        
        panel_4.setMaximumSize( new Dimension(300, 420) );
        
    }

    public void initialize() {
        
    }

}
