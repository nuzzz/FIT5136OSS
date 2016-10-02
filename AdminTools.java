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

public class AdminTools extends JDialog {
    
    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();

    public static void display(ShopController c){
        AdminTools dialog = new AdminTools(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    
    public AdminTools(ShopController c) {
        setTitle("Admin Tools");
        setBounds(100, 100, 300, 150);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        //View Stock Button
        {
            JButton viewStockButton = new JButton("View Stock");
            viewStockButton.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints gbc_viewStockButton = new GridBagConstraints();
            gbc_viewStockButton.anchor = GridBagConstraints.WEST;
            gbc_viewStockButton.insets = new Insets(5, 5, 5, 5);
            gbc_viewStockButton.gridx = 0;
            gbc_viewStockButton.gridy = 0;
            gbc_viewStockButton.fill = GridBagConstraints.HORIZONTAL;
            contentPanel.add(viewStockButton, gbc_viewStockButton);
        }
         //View Purchases
        {
            JDialog me = this;
            JButton viewPurchasesButton = new JButton("View Purchase");
            viewPurchasesButton.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints gbc_viewPurchasesButton = new GridBagConstraints();
            gbc_viewPurchasesButton.anchor = GridBagConstraints.WEST;
            gbc_viewPurchasesButton.insets = new Insets(5, 5, 5, 5);
            gbc_viewPurchasesButton.gridx = 1;
            gbc_viewPurchasesButton.gridy = 0;
            gbc_viewPurchasesButton.fill = GridBagConstraints.HORIZONTAL;
            viewPurchasesButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						c.showPurchases();
						me.dispose();
					}
				});
            contentPanel.add(viewPurchasesButton, gbc_viewPurchasesButton);
        }
         //View Customers
        {
            JButton viewCustomersButton = new JButton("View Customers");
            viewCustomersButton.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints gbc_viewCustomersButton = new GridBagConstraints();
            gbc_viewCustomersButton.anchor = GridBagConstraints.EAST;
            gbc_viewCustomersButton.insets = new Insets(5, 5, 5, 5);
            gbc_viewCustomersButton.gridx = 0;
            gbc_viewCustomersButton.gridy = 1;
            gbc_viewCustomersButton.fill = GridBagConstraints.HORIZONTAL;
            contentPanel.add(viewCustomersButton, gbc_viewCustomersButton);
        }
         //Add new Product
        {
            JButton addProductButton = new JButton("Add new Product");
            addProductButton.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints gbc_addProductButton = new GridBagConstraints();
            gbc_addProductButton.anchor = GridBagConstraints.EAST;
            gbc_addProductButton.insets = new Insets(5, 5, 5, 5);
            gbc_addProductButton.gridx = 1;
            gbc_addProductButton.gridy = 1;
            gbc_addProductButton.fill = GridBagConstraints.HORIZONTAL;
            contentPanel.add(addProductButton, gbc_addProductButton);
        }
        
        //buttons
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
