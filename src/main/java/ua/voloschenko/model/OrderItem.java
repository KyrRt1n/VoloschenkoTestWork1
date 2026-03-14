package ua.voloschenko.model;

public class OrderItem {

    private final String productId;
    private final String productName;
    private final int quantity;
    private final Money itemPrice;

    public OrderItem(String productId, String productName, int quantity, Money itemPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public Money totalPrice(){
        return itemPrice.multiply(quantity);
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return productName + " x" + quantity + " = " + totalPrice();
    }
}
