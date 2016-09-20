import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ProductListView extends View {
    
    private static final long serialVersionUID = 1L;
    
    private JPanel scrollPanel;
    private JPanel main_panel;
    private JPanel sub_panel;
    private JLabel lblWelcomeMsg;
    
    public ProductListView() {
        setLayout(new BorderLayout(0, 0));
        
        main_panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) main_panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(main_panel, BorderLayout.NORTH);
        
        lblWelcomeMsg = new JLabel("");
        main_panel.add(lblWelcomeMsg);
        
        JButton myInfoButton = new JButton("My account");
        
        myInfoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                UserDetails.display(getController());
            }
        });
        
        main_panel.add(myInfoButton);
        
        JButton cartButton = new JButton("View cart");
        
        cartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCartView();
            }
        });
        
        main_panel.add(cartButton);
        
        JButton logoutButton = new JButton("Logout");
        
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().logout();
            }
        });
        
        main_panel.add(logoutButton);
        
        
        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    public void initialize() {
        scrollPanel.removeAll();
        lblWelcomeMsg.setText("Welcome " + getController().getCurrentUser());

        List<Product> list = getController().getBackend().getProducts();
        for(Product p : list){
            scrollPanel.add(new ProductThumbnail(getController(), p));
        }
        revalidate();
    }

}
