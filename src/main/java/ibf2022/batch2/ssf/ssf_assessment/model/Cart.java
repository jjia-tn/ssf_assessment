package ibf2022.batch2.ssf.ssf_assessment.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Cart implements Serializable {

    private List<Item> contents = new LinkedList<>();

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item) {
        this.contents.add(item);
    }

    @Override
    public String toString() {
        return "Cart [contents=" + contents + "]";
    }
    
}
