import java.util.Arrays;
import java.awt.BorderLayout;
import java.util.Date;
//import javax.swing.
public class PurchaseTableModel extends RowTableModel<Purchase>
{
    private static String[] COLUMN_NAMES ={
        "Purchase Date",
        "Buyer",
        "Purchase ID",
        "Item Name",
        "Brand",
        "Season",
        "Quantity",
        "Price per unit",
        "Total price"
    };
    
    PurchaseTableModel(){
        super(Arrays.asList(COLUMN_NAMES));
        setRowClass(Purchase.class);
        setColumnClass(0, Date.class);
        setColumnClass(1, String.class);
        setColumnClass(2, Integer.class);
        setColumnClass(3, String.class);
        setColumnClass(4, String.class);
        setColumnClass(5, String.class);
        setColumnClass(6, Float.class);
        setColumnClass(7, Float.class);
        setColumnClass(8, Float.class);
    }
    
    @Override
    public Object getValueAt(int row, int column)
    {
        Purchase p = getRow(row);
        Clothing c = (Clothing) p.getItems().get(0).getProduct();
        switch (column)
        {
            case 0: return p.getPurchaseDate();
            case 1: return p.getBuyer();
            case 2: return p.getId();
            case 3: return c.getName();
            case 4: return c.getBrand();
            case 5: return c.getSeason();
            case 6: return p.getItems().get(0).getQuantity();
            case 7: return c.getPrice();
            case 8: return p.totalForPurchase();
            default: return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Purchase p = getRow(row);

        switch (column)
        {
            case 0: break;
            case 1: break;
            case 2: break;
            default: return;
        }

        fireTableCellUpdated(row, column);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
}
