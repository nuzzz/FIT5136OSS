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

public class AddItemDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    
    private JTextField ItemName;
    private JTextField Price;
    private JTextField brand;
    private JTextField colour;
    private JTextField season;
    private JTextField size;
    private JTextField image;

    public static void display(ShopController c){
        AddItemDialog dialog = new AddItemDialog(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    /*
    //not done
    public Item toItem(Item user){
            //Customer(String username, String password, String name, String colour, String address, String Price, String seasonber, boolean premiumStatus){
        return new Customer(user.getUsername(), user.getPassword(), ItemName.getText(), colour.getText(), homeAddr.getText(), Price.getText() , season.getText(), user.getPremiumStatus());
    }
    */
    public AddItemDialog(ShopController c) {
        Customer user = c.getCurrentUserDetails();
        
        setTitle("Add new item");
        setBounds(100, 100, 300, 300);
        
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0,0,0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0,0};
        gbl_contentPanel.columnWeights = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{1.0, 0.5,0.5,0.5,0.5,0.5,1.0, Double.MIN_VALUE};
   
        contentPanel.setLayout(gbl_contentPanel);
      // contentPanel.pack();
        //Item name
        {
            JLabel lblItemName = new JLabel("Item name:");
            lblItemName.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblItemName = new GridBagConstraints();
            gbc_lblItemName.anchor = GridBagConstraints.EAST;
            gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
            gbc_lblItemName.gridx = 1;
            gbc_lblItemName.gridy = 0;
            contentPanel.add(lblItemName, gbc_lblItemName);
        }
        {
            ItemName = new JTextField();
            GridBagConstraints gbc_ItemName = new GridBagConstraints();
            gbc_ItemName.insets = new Insets(0, 0, 5, 5);
            gbc_ItemName.fill = GridBagConstraints.HORIZONTAL;
            gbc_ItemName.gridx = 2;
            gbc_ItemName.gridy = 0;
            contentPanel.add(ItemName, gbc_ItemName);
            ItemName.setColumns(10);

        }
        //price
        {
            JLabel lblPrice = new JLabel("Price($):");
            GridBagConstraints gbc_lblPrice = new GridBagConstraints();
            gbc_lblPrice.anchor = GridBagConstraints.EAST;
            gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
            gbc_lblPrice.gridx = 1;
            gbc_lblPrice.gridy = 1;
            contentPanel.add(lblPrice, gbc_lblPrice);
        }
        {
            Price = new JTextField();
            GridBagConstraints gbc_Price = new GridBagConstraints();
            gbc_Price.insets = new Insets(0, 0, 5, 5);
            gbc_Price.fill = GridBagConstraints.HORIZONTAL;
            gbc_Price.gridx = 2;
            gbc_Price.gridy = 1;
            contentPanel.add(Price, gbc_Price);
            Price.setColumns(10);
        }
        //Brand name
        {
            JLabel lblBrand = new JLabel("Brand name:");
            lblBrand.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblBrand = new GridBagConstraints();
            gbc_lblBrand.anchor = GridBagConstraints.EAST;
            gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
            gbc_lblBrand.gridx = 1;
            gbc_lblBrand.gridy = 2;
            contentPanel.add(lblBrand, gbc_lblBrand);
        }
        {
            brand = new JTextField();
            GridBagConstraints gbc_brand = new GridBagConstraints();
            gbc_brand.insets = new Insets(0, 0, 5, 5);
            gbc_brand.fill = GridBagConstraints.HORIZONTAL;
            gbc_brand.gridx = 2;
            gbc_brand.gridy = 2;
            contentPanel.add(brand, gbc_brand);
            brand.setColumns(10);
        }
        //Colour
        {
            JLabel lblColour = new JLabel("Colour:");
            lblColour.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_lblColour = new GridBagConstraints();
            gbc_lblColour.anchor = GridBagConstraints.EAST;
            gbc_lblColour.insets = new Insets(0, 0, 5, 5);
            gbc_lblColour.gridx = 1;
            gbc_lblColour.gridy = 3;
            contentPanel.add(lblColour, gbc_lblColour);
        }
        {
            colour = new JTextField();
            GridBagConstraints gbc_colour = new GridBagConstraints();
            gbc_colour.insets = new Insets(0, 0, 5, 5);
            gbc_colour.fill = GridBagConstraints.HORIZONTAL;
            gbc_colour.gridx = 2;
            gbc_colour.gridy = 3;
            contentPanel.add(colour, gbc_colour);
            colour.setColumns(15);
        }
        
        //season
        {
            JLabel lblSeason = new JLabel("season:");
            GridBagConstraints gbc_lblSeason = new GridBagConstraints();
            gbc_lblSeason.anchor = GridBagConstraints.EAST;
            gbc_lblSeason.insets = new Insets(0, 0, 0, 5);
            gbc_lblSeason.gridx = 1;
            gbc_lblSeason.gridy = 4;
            contentPanel.add(lblSeason, gbc_lblSeason);
        }
        {
            season = new JTextField();
            GridBagConstraints gbc_season = new GridBagConstraints();
            gbc_season.insets = new Insets(0, 0, 0, 5);
            gbc_season.fill = GridBagConstraints.HORIZONTAL;
            gbc_season.gridx = 2;
            gbc_season.gridy = 4;
            contentPanel.add(season, gbc_season);
            season.setColumns(10);
        }
 
        
        //Size
        {
            JLabel lblSize = new JLabel("Size:");
            GridBagConstraints gbc_lblSize = new GridBagConstraints();
            gbc_lblSize.anchor = GridBagConstraints.EAST;
            gbc_lblSize.insets = new Insets(0, 0, 0, 5);
            gbc_lblSize.gridx = 1;
            gbc_lblSize.gridy = 5;
            contentPanel.add(lblSize, gbc_lblSize);
        }
        {
            size = new JTextField();
            GridBagConstraints gbc_size = new GridBagConstraints();
            gbc_size.insets = new Insets(0, 0, 0, 5);
            gbc_size.fill = GridBagConstraints.HORIZONTAL;
            gbc_size.gridx = 2;
            gbc_size.gridy = 5;
            contentPanel.add(size, gbc_size);
            size.setColumns(10);
        }
        
        
        //Image
        {
            JLabel lblImage = new JLabel("Image:");
            GridBagConstraints gbc_lblImage = new GridBagConstraints();
            gbc_lblImage.anchor = GridBagConstraints.EAST;
            gbc_lblImage.insets = new Insets(0, 0, 0, 5);
            gbc_lblImage.gridx = 1;
            gbc_lblImage.gridy = 6;
            contentPanel.add(lblImage, gbc_lblImage);
        }
        {
            image = new JTextField();
            GridBagConstraints gbc_image = new GridBagConstraints();
            gbc_image.insets = new Insets(0, 0, 0, 5);
            gbc_image.fill = GridBagConstraints.HORIZONTAL;
            gbc_image.gridx = 2;
            gbc_image.gridy = 6;
            contentPanel.add(image, gbc_image);
            image.setColumns(10);
        }
        
        
        
        //buttons
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
            {
                JDialog me = this;
                JButton okButton = new JButton("Add");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        boolean isValid = false;
                        //isValid = c.verifyUserDetails(ItemName.getText(), Price.getText(), brand.getText(), colour.getText(), image.getText());
                        if(isValid){
                        //    c.updateUserDetails(toCustomer(user));
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

