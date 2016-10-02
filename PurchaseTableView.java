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
import javax.swing.JTable;
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
        
        btnBack = new JButton("Back to products");
        topPanel.add(btnBack);
        
        btnLogout = new JButton("Logout");
        topPanel.add(btnLogout);
        
        btnReset = new JButton("Reset");
        topPanel.add(btnReset);
        
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
        totalForTableLabel = new JLabel("");
        btmPanel.add(totalForTableLabel);

    }
    
    public void initialize(){
        ArrayList<Purchase> purchases = getController().getBackend().getPurchases();
        ArrayList<Purchase> purchasesTableFormat = new ArrayList<Purchase>();
        for(Purchase p : purchases){
            for(CartItem ci: p.getItems()){
                //Purchase(int id, ArrayList<CartItem> items, Date purchDate, String username)
                ArrayList<CartItem> tempItemList = new ArrayList<CartItem>();
                tempItemList.add(ci);
                Purchase newPurchase =  new Purchase(p.getId(), tempItemList, p.getPurchaseDate(), p.getBuyer());
                purchasesTableFormat.add(newPurchase);
            }
        }
        float total = 0f;
        for(Purchase p : purchasesTableFormat){
            ptm.addRow(p);
            total+=p.totalForPurchase();
        }
        totalForTableLabel.setText("Grand Total for data in table: $"+total);
        
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
                getController().showPurchases();
            }
        });
    }

}
