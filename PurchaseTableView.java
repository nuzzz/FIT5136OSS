import java.util.ArrayList;

import java.awt.Dimension;
// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PurchaseTableView extends View {

    private static final long serialVersionUID = 1L;
    private JPanel topPanel;
    private JPanel btmPanel;
    
    private JTable table;
    private PurchaseTableModel ptm;
    
    private JLabel titleLabel;
    private JLabel totalForTableLabel;
    private JTextField searchTF;
    
    private JLabel userLabel;
    private JLabel itemNameLabel;
    private JRadioButton userRB;
    private JRadioButton itemNameRB;
    private ButtonGroup buttonGroup;
    
    private JLabel searchMessageLabel;
    
    private JButton btnFilter;
    private JButton btnReset;
    private JButton btnLogout;
    private JButton btnBack;
    
    private JScrollPane scrollPane;
    
    
    public PurchaseTableView() {      
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();

        FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        add(topPanel,BorderLayout.NORTH);
        
        titleLabel = new JLabel("Purchase history");
        topPanel.add(titleLabel);
        
        searchTF = new JTextField();
        searchTF.setColumns(10);
        topPanel.add(searchTF);
        
        userRB = new JRadioButton();
        userRB.setSelected(true);
        topPanel.add(userRB);
        userLabel = new JLabel("Username");
        topPanel.add(userLabel);
       

        itemNameRB = new JRadioButton();        
        topPanel.add(itemNameRB);
        itemNameLabel = new JLabel("Item name");
        topPanel.add(itemNameLabel);
 
        buttonGroup = new ButtonGroup();
        buttonGroup.add(userRB);
        buttonGroup.add(itemNameRB);
        
        btnFilter = new JButton("Filter");
        topPanel.add(btnFilter);
        
        btnReset = new JButton("Reset");
        topPanel.add(btnReset);
        
        btnBack = new JButton("Back to products");
        topPanel.add(btnBack);
        
        btnLogout = new JButton("Logout");
        topPanel.add(btnLogout);
        
        
        ptm = new PurchaseTableModel();
        table = new JTable(ptm);

        table.getColumnModel().getColumn(0).setCellRenderer(FormatRenderer.getDateTimeRenderer());
        table.getColumnModel().getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        table.getColumnModel().getColumn(8).setCellRenderer(NumberRenderer.getCurrencyRenderer());
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        
        scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        add(scrollPane, BorderLayout.CENTER);
        
        btmPanel = new JPanel();
        FlowLayout flowLayout2 = (FlowLayout) btmPanel.getLayout();
        flowLayout2.setAlignment(FlowLayout.RIGHT);
        add(btmPanel,BorderLayout.SOUTH);
        
        searchMessageLabel = new JLabel("");
        btmPanel.add(searchMessageLabel);
        
        JLabel spacer = new JLabel("           ");
        btmPanel.add(spacer);
        
        totalForTableLabel = new JLabel("");
        btmPanel.add(totalForTableLabel);

    }
    
    public void initialize(){
        searchMessageLabel.setText(getController().getPurchaseSearchQuery());
        
        float total = 0f;
        for(Purchase p : getController().getSearchPurchaseList()){
            ptm.addRow(p);
            total+=p.totalForPurchase();
        }
        totalForTableLabel.setText("Grand Total for data in table: $"+total);
        
        btnFilter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //filter by username
                if(userRB.isSelected()){
                    ArrayList<Purchase> res = getController().searchPurchaseList(searchTF.getText(),ShopController.PurchaseSearchType.USER_USERNAME);
                    String item = searchTF.getText();
                    getController().setPurchaseSearchQuery("Searching for User: " + item + "... ");
                    getController().setSearchPurchaseList(res);
                    getController().showPurchases();
                    revalidate();
                }
                //filter by item name
                if(itemNameRB.isSelected()){
                    ArrayList<Purchase> res = getController().searchPurchaseList(searchTF.getText(),ShopController.PurchaseSearchType.PRODUCT_NAME);
                    String item = searchTF.getText();
                    getController().setPurchaseSearchQuery("Searching for Product name: " + item + "... ");
                    getController().setSearchPurchaseList(res);
                    getController().showPurchases();
                    revalidate();
                }
                getController().resetSearchPurchaseList();
            }
        });
        
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showProductList();
            }
        });
        btnLogout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { 
                getController().logout();
            }
        });
        btnReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().resetSearchPurchaseList();
                getController().showPurchases();
                revalidate();
            }
        });
    }

}
