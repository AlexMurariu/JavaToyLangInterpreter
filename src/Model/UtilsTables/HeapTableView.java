package Model.UtilsTables;

import javafx.beans.property.SimpleIntegerProperty;

public class HeapTableView {

    private SimpleIntegerProperty address, value, ba;

    public HeapTableView(Integer address, Integer value){
        this.address = new SimpleIntegerProperty(address);
        this.value = new SimpleIntegerProperty(value);
    }

    public SimpleIntegerProperty addressProperty() {
        return address;
    }

    public void setAddress(int address) {
        this.address.set(address);
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public SimpleIntegerProperty baProperty() {
        return ba;
    }
}
