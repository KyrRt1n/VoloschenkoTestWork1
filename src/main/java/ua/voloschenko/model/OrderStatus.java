package ua.voloschenko.model;

public enum OrderStatus {

    NEW, PAID, SHIPPED, DELIVERED, CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case NEW:
                yield next == PAID || next == CANCELLED;
            case PAID:
                yield next == SHIPPED;
            case SHIPPED:
                yield next ==  DELIVERED;
            default:
                yield false;
        };
    }


}
