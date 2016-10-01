import javax.swing.DefaultComboBoxModel;
import java.util.Vector;

class CustomComboModel extends DefaultComboBoxModel<String> {
    public CustomComboModel() {}
    public CustomComboModel(Vector<String> items) {
        super(items);
    }
    @Override
    public void setSelectedItem(Object item) {
        //if string is surrounded by [] it is a category
        if (item.toString().startsWith("[")&&item.toString().endsWith("]")){
            return;
        }
        super.setSelectedItem(item);
    };
}