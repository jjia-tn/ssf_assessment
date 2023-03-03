package ibf2022.batch2.ssf.ssf_assessment.model;

import java.util.List;

public class Order {

    private final Cart cart;

    private final Shipping shipping;

    private float totalCost = -1;

    private String orderId;

    public Order(Cart cart, Shipping shipping) {
        this.cart = cart;
        this.shipping = shipping;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Item> getContents() {
        return this.cart.getContents();
    }

    public String getName() {
        return this.shipping.getName();
    }

    public String getAddress() {
        return this.shipping.getAddress();
    }
    
}
