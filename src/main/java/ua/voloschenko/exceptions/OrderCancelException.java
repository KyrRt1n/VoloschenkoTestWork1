package ua.voloschenko.exceptions;

public class OrderCancelException extends RuntimeException {
    public OrderCancelException(String message) {
        super("Cannot cancel order in any status except NEW");
    }
}
