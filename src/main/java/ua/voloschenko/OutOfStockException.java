package ua.voloschenko;

public class OutOfStockException extends AppException {

    private final String productId;

    public OutOfStockException(String productId) {
        super("Product out of stock: " + productId);
        this.productId = productId;
    }

    public OutOfStockException(String productId, Throwable cause) {
        super("Product out of stock: " + productId, cause);
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}