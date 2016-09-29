import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CreditDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    JPanel panel;
    JPanel creditInputPanel;
    JLabel lblTitle;
    JLabel lblCreditInput;
    JLabel spacer;
    JTextField creditInputTextField;
    JButton confirmButton;
    JButton cancelButton;
    
    
    public static void display(ShopController c){
        CreditDialog dialog = new CreditDialog(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    
    public CreditDialog(ShopController c) {
        JDialog dialog = this;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                lblTitle = new JLabel("Add Credit:");
                panel.add(lblTitle);
            }
            {
                spacer = new JLabel("  ");
                panel.add(spacer);
            }
            {
                creditInputPanel = new JPanel();
                creditInputPanel.setLayout(new GridLayout(3,3));
                
                lblCreditInput = new JLabel("How much credit would you like to use?");
                creditInputPanel.add(lblCreditInput);
                
                creditInputTextField = new JTextField();
                creditInputPanel.add(creditInputTextField);
                
                panel.add(creditInputPanel);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                confirmButton = new JButton("Confirm add credit");
                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        c.showPopup(creditInputTextField.getText() + " Credit added to transaction");
                        dialog.dispose();
                    }
                });
                confirmButton.setActionCommand("OK");
                buttonPane.add(confirmButton);
                getRootPane().setDefaultButton(confirmButton);
            }
            {
                cancelButton = new JButton("Cancel");
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
