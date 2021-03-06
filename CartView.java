import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.text.*;

public class CartView extends View {

    private static final long serialVersionUID = 1L;
    
    JPanel scrollPanel;
    JButton btnClear;
    JButton btnBack;
    JButton btnCheckout;
    JButton logoutButton;
    JButton btnUseCredit;
    JLabel lblNetTotal;
    JLabel lblTotal;
    JScrollPane scroll;

    public CartView() {

        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        add(panel, BorderLayout.NORTH);
        
        btnBack = new JButton("Back to products");
        panel.add(btnBack);
        
        btnClear = new JButton("Remove all from cart");
        panel.add(btnClear);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);
        
        lblTotal = new JLabel("Total: ");
        panel.add(lblTotal);
        
        lblNetTotal = new JLabel();
        panel.add(lblNetTotal);
        
        logoutButton = new JButton("Logout");
        panel.add(logoutButton);
        
        btnCheckout = new JButton("Checkout");
        panel.add(btnCheckout);

        scrollPanel = new JPanel();
        scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);
        
    }

    public void initialize() {
        scrollPanel.removeAll();
        if(getController().getCart().getItems().size() == 0){
            JPanel panel = new JPanel();
            JLabel emptyCartMessageLabel = new JLabel("YOUR CART IS EMPTY");
            panel.add(emptyCartMessageLabel);
            emptyCartMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
            emptyCartMessageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
            panel.add(emptyCartMessageLabel);
            scrollPanel.add(panel);
        }
        else{
            for(CartItem item : getController().getCart().getItems()){
                JPanel itemPanel = new JPanel();
                itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                itemPanel.setAlignmentX(LEFT_ALIGNMENT);
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                
                JPanel titlePanel = new JPanel();
                itemPanel.add(titlePanel);
                titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
                

                Clothing c = (Clothing) item.getProduct();
                JLabel lblProductName = new JLabel(c.getName()+ "       ");
                lblProductName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                titlePanel.add(lblProductName);

                JLabel lblProductBrand = new JLabel("Brand: " + c.getBrand()+ "       ");
                titlePanel.add(lblProductBrand);

                JLabel lblProductColour = new JLabel("Colour: " + c.getColour()+ "       ");
                titlePanel.add(lblProductColour);

                JLabel lblProductSeason = new JLabel("Season: " + c.getSeason()+ "       ");
                titlePanel.add(lblProductSeason);

                Component horizontalGlue_1 = Box.createHorizontalGlue();
                titlePanel.add(horizontalGlue_1);

                JPanel propertiesPanel = new JPanel();
                itemPanel.add(propertiesPanel);
                propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.X_AXIS));

                JPanel quantityPanel = new JPanel();
                propertiesPanel.add(quantityPanel);
                quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
                
                JLabel lblQuantity = new JLabel("Quantity: ");
                quantityPanel.add(lblQuantity);
                JLabel lblNewLabel_1 = new JLabel(item.quantity + "");
                quantityPanel.add(lblNewLabel_1);
                
                JLabel spacer_1 = new JLabel("          ");
                quantityPanel.add(spacer_1);
                
                JLabel lblNewLabel = new JLabel("Price per item($):   ");
                quantityPanel.add(lblNewLabel);
                Cart thisItemInACart = new Cart();
                thisItemInACart.add(item);
                //JLabel lblNewLabel_2 = new JLabel("$"+getController().getBackend().getPrice(thisItemInACart));
                JLabel lblNewLabel_2 = new JLabel("$"+item.getProduct().getPrice());
                quantityPanel.add(lblNewLabel_2);
                
                JLabel spacer_2 = new JLabel("          ");
                quantityPanel.add(spacer_2);            
                
                JLabel lblNewLabel3 = new JLabel("Price all item($):   ");
                quantityPanel.add(lblNewLabel3);
                JLabel lblNewLabel_4 = new JLabel("$"+item.getProduct().getPrice()*item.getQuantity());
                quantityPanel.add(lblNewLabel_4);
                
                JLabel spacer_3 = new JLabel("     ");
                quantityPanel.add(spacer_3);            
                
                JLabel lblNewLabel4 = new JLabel("Update quantity: ");
                quantityPanel.add(lblNewLabel4);
                JTextField qtyTF = new JTextField();
                qtyTF.setColumns(2);
                qtyTF.setText("1"); 
                qtyTF.setMaximumSize(qtyTF.getPreferredSize() );
                quantityPanel.add(qtyTF);
    
                JButton btnUpdateCart = new JButton("Update cart");
                btnUpdateCart.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        try{
                            int qty = Integer.parseInt(qtyTF.getText());
                            if(qty == 0){
                                getController().getCart().remove(item);
                            }
                            if(qty < 0){
                                getController().showPopup("Quantity must be greater than 0");
                                return;
                            }
                            getController().modifyCartQuantity(item.getProduct(), (float)qty);
                        }
                        catch (NumberFormatException e2){
                            getController().showPopup("Updated quantity must be a number try again");
                        }
                        getController().showCartView();
                    }
                });
                propertiesPanel.add(btnUpdateCart);
                
                JButton btnRemove = new JButton("Remove item");
                btnRemove.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        getController().getCart().remove(item);
                        getController().showCartView();
                    }
                });
                propertiesPanel.add(btnRemove);
  
                Component horizontalGlue = Box.createHorizontalGlue();
                propertiesPanel.add(horizontalGlue);
                JSeparator separator = new JSeparator();
                itemPanel.add(separator);
                scrollPanel.add(itemPanel);
            }
            lblNetTotal.setText("$"+getController().getTotalCartPrice());       
        }
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showProductList();
            }
        });
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().getCart().setItems(new ArrayList<CartItem>());
                getController().showCartView();
            }
        });
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { 
                getController().logout();
            }
        });
        btnCheckout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(getController().getCurrentUser().equals("admin")){
                    getController().showPopup("Administrator cannot checkout");
                    return;
                }
                else if(getController().getCart().getItems().size() == 0){
                    getController().showPopup("Cannot checkout an empty cart");
                }
                else{
                    getController().showCheckout();
                }
            }
        });
        Component verticalGlue = Box.createVerticalGlue();
        scrollPanel.add(verticalGlue);
    }
}
