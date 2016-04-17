package biz.lci.stockfighter;

/**
 * Created by tlanders on 4/16/2016.
 */
public class Order {
    public enum Direction {buy, sell};
    public enum OrderType {limit, market};

    private String account;
    private String venue;
    private String stock;
    private int price;
    private int qty;
    private Direction direction;
    private OrderType orderType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "account='" + account + '\'' +
                ", venue='" + venue + '\'' +
                ", stock='" + stock + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", direction=" + direction +
                ", orderType=" + orderType +
                '}';
    }
}
