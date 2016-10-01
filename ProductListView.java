import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ProductListView extends View {
    
    private static final long serialVersionUID = 1L;
    
    private JPanel scrollPanel;
    private JPanel main_panel;
    private JPanel sub_panel;
    private JPanel search_panel;
    
    private JLabel lblWelcomeMsg;
    private JLabel lblSearch;
    private JLabel searchMessage;
    
    private JTextField searchTF;
    private JComboBox<String> searchCB;
    
    private JButton myInfoButton;
    private JButton cartButton;
    private JButton logoutButton;
    private JButton searchButton;
    private JButton adminToolsButton;
    private JScrollPane scroll;
    List<Product> productList;
    
    public ProductListView() {
        setLayout(new BorderLayout(0, 0));
        
        main_panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) main_panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(main_panel, BorderLayout.NORTH);
        
        
        searchCB = new JComboBox<String>(new CustomComboModel());
        searchCB.addItem("Choose a category");
        main_panel.add(searchCB);
        
        searchTF = new JTextField();
        searchTF.setColumns(10);
        main_panel.add(searchTF);
        
        searchButton = new JButton("Search");
        main_panel.add(searchButton);
        
        lblWelcomeMsg = new JLabel("");
        main_panel.add(lblWelcomeMsg);
       

        
        cartButton = new JButton("View cart");
        main_panel.add(cartButton);
        
        logoutButton = new JButton("Logout");
        main_panel.add(logoutButton);
        
        search_panel = new JPanel();
        searchMessage = new JLabel("");
        search_panel.add(searchMessage);
        FlowLayout flowLayout2 = (FlowLayout) search_panel.getLayout();
        flowLayout2.setAlignment(FlowLayout.RIGHT);
        add(search_panel, BorderLayout.SOUTH);
        
        
        scrollPanel = new JPanel();
        scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    public void initialize() {
        scrollPanel.removeAll();
        if(getController().getCurrentUser().equals("admin")){
            adminToolsButton = new JButton("AdminTools");
            adminToolsButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //AdminTools.display(getController());
                }
            });
            main_panel.add(adminToolsButton);
        }else{
            
            myInfoButton = new JButton("My account");
            myInfoButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    UserDetails.display(getController());                    
                }
            });
            main_panel.add(myInfoButton);
        }
        
        lblWelcomeMsg.setText("Welcome " + getController().getCurrentUser());
        
        productList = getController().getSearchProductList();
        searchMessage.setText(getController().getSearchQuery());
        
        if(productList.size()==0){
            JLabel noResultLabel = new JLabel("No results");
            noResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noResultLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
            scrollPanel.add(noResultLabel);
        }
        
        
        for(Product p : productList){
            scrollPanel.add(new ProductThumbnail(getController(), p));
        } 
        
        Set<String> brands = getController().getBackend().getBrands();
        Set<String> seasons = getController().getBackend().getSeasons();
        Set<String> colours = getController().getBackend().getColours();
        Set<String> gender = getController().getBackend().getGenders();
        
        searchCB.addItem("[Brand]");
        for(String b: brands){
            searchCB.addItem(b);
        }
        searchCB.addItem("[Colour]");
        for(String c: colours){
            searchCB.addItem(c);
        }
        searchCB.addItem("[Gender]");
        for(String g: gender){
            searchCB.addItem(g);
        }
        searchCB.addItem("[Season]");
        for(String s: seasons){
            searchCB.addItem(s);
        }
        
        searchCB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<Product> res = getController().searchProductList((String)searchCB.getSelectedItem()); 
                String item = (String) searchCB.getSelectedItem();
                getController().setSearchQuery("Searching for " + item + "... ");
                getController().setSearchProductList(res);
                getController().showProductList();
                revalidate();
            }
        });
        

        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> res = getController().searchProductList(searchTF.getText());
                String item = searchTF.getText();
                getController().setSearchQuery("Searching for " + item + "... ");
                getController().setSearchProductList(res);
                getController().showProductList();
                revalidate();
            }   
        });
        cartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCartView();
            }
        });
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().logout();
            }
        });
        revalidate();
    }
    

}
