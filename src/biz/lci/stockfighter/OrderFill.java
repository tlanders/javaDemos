package biz.lci.stockfighter;

import java.util.Date;

/**
 * Created by tlanders on 4/17/2016.
 */
public class OrderFill extends BaseResponse {
    private int price;
    private int qty;
    private String ts;

    @Override
    public String toString() {
        return "OrderFill{" +
                "price=" + price +
                ", qty=" + qty +
                ", ts=" + ts +
                '}';
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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}