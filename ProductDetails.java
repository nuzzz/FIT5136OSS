import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ProductDetails extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    JSpinner spinner;
    
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel colourLabel;
    private JLabel brandLabel;  
    private JLabel seasonLabel;

    //not done yet
    private JLabel sizeLabel;
    

    public static void display(ShopController c, Product p){
        ProductDetails dialog = new ProductDetails(c, p);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }

    public ProductDetails(ShopController c, Product p) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                JLabel lblNewLabel_3 = new JLabel();
                lblNewLabel_3.setIcon(p.getImage());
                panel.add(lblNewLabel_3);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                panel_1.setBorder(null);
                
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEFT);
                {
                    JLabel nameLabel = new JLabel(p.getName());
                    panel_1.add(nameLabel);
                    nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                    
                    
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                panel_1.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
   
                //panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
                //FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                //fl_panel_1.setAlignment(FlowLayout.LEFT);
                panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
                {
                    Clothing clothing = (Clothing) p;
                    
                    JLabel priceLabel = new JLabel("Price: $" + p.getPrice());
                    gbc.gridx = 1;
                    gbc.gridy = 4;
                    panel_1.add(priceLabel,gbc);
                    priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    
                    JLabel brandLabel = new JLabel("Brand: " + clothing.getBrand());
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    panel_1.add(brandLabel,gbc);
                    
                    JLabel colourLabel = new JLabel("Colour: " + clothing.getColour());
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    panel_1.add(colourLabel,gbc);
                    
                    JLabel seasonLabel = new JLabel("Season: " + clothing.getSeason());
                    gbc.gridx = 1;
                    gbc.gridy = 2;
                    panel_1.add(seasonLabel,gbc);
                
                    //JLabel lblNewLabel_2 = new JLabel(p.getPropertiesAsText());
                    //panel_1.add(lblNewLabel_2);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JLabel lblNewLabel = new JLabel("Quantity:");
                buttonPane.add(lblNewLabel);
            }
            {
                spinner = new JSpinner();
                spinner.setModel(new SpinnerNumberModel(1, 0, 100000, 1));
                spinner.setPreferredSize(new Dimension(100, 30));
                buttonPane.add(spinner);
            }
            {
                JDialog me = this;
                JButton okButton = new JButton("Add to cart");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        c.addToCart(p, Float.parseFloat(spinner.getValue() + ""));
                        me.dispose();
                    }
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                JDialog me = this;
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }
    }

}
