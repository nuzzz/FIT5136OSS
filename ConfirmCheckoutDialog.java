import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConfirmCheckoutDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public static void display(ShopController c){
        ConfirmCheckoutDialog dialog = new ConfirmCheckoutDialog(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    
    public ConfirmCheckoutDialog(ShopController c) {
        JDialog dialog = this;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JLabel lblNewLabel = new JLabel("Purchase details:");
                panel.add(lblNewLabel);
            }
            {
                JLabel spacer = new JLabel("  ");
                panel.add(spacer);
            }
            {
                String text = "<html>";
                
                for(CartItem item : c.getCart().getList()){
                    Cart thisItemInACart = new Cart();
                    thisItemInACart.add(item);
                    text += "Item: "+item.product.getName() +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Qty: " + item.quantity +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price: " + c.getBackend().getPrice(thisItemInACart) + "<br>";
                }
                
                text += "</html>";
                JLabel details = new JLabel(text);
                panel.add(details);
                
                panel.add(new JLabel(" "));
                panel.add(new JLabel(" "));
                
                JLabel totalPriceLabel = new JLabel("Grand Total: $" + c.getTotalCartPrice());
                panel.add(totalPriceLabel);

            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton confirmButton = new JButton("Confirm order");
                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        c.attemptTransaction();
                        dialog.dispose();
                    }
                });
                confirmButton.setActionCommand("OK");
                buttonPane.add(confirmButton);
                getRootPane().setDefaultButton(confirmButton);
            }
            {
                JButton useCreditButton = new JButton("Use Credit");
                //if c.getUser has credit
                useCreditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        c.showUseCredit();
                    }
                });
                buttonPane.add(useCreditButton);
            }
            
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
