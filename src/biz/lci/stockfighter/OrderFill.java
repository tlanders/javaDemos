package biz.lci.stockfighter;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by tlanders on 4/17/2016.
 */
public class OrderFill extends BaseResponse {
    private int price;
    private int qty;

    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
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

    public Date getTimestamp() throws ParseException {
        return convertToDate(ts);
    }
}