package biz.lci.stockfighter;

import java.util.Date;
import java.util.List;

/**
 * Created by tlanders on 4/17/2016.
 */
public class OrderResponse extends BaseResponse {
    private List<OrderFill> fills;
    private String symbol;
    private String venue;
    private Direction direction;
    private int originalQty;
    private int qty;                // this is the quantity *left outstanding*
    private int price;              // the price on the order -- may not match that of fills!
    private OrderType orderType;
    private String id;              // guaranteed unique *on this venue*
    private String account;
    private String ts;                // ISO-8601 timestamp for when we received order
    private int totalFilled;
    private boolean open;

    public List<OrderFill> getFills() {
        return fills;
    }

    public void setFills(List<OrderFill> fills) {
        this.fills = fills;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "symbol='" + symbol + '\'' +
                ", venue='" + venue + '\'' +
                ", direction=" + direction +
                ", originalQty=" + originalQty +
                ", qty=" + qty +
                ", price=" + price +
                ", orderType=" + orderType +
                ", id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", ts=" + ts +
                ", totalFilled=" + totalFilled +
                ", open=" + open +
                ", fills=" + fills +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getOriginalQty() {
        return originalQty;
    }

    public void setOriginalQty(int originalQty) {
        this.originalQty = originalQty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getTotalFilled() {
        return totalFilled;
    }

    public void setTotalFilled(int totalFilled) {
        this.totalFilled = totalFilled;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}