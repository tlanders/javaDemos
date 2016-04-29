package biz.lci.stockfighter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by tlanders on 4/18/2016.
 */
public enum OrderType {
    limit("limit"),
    market("market"),
    fill_or_kill("fill-or-kill"),   // limit order that is either immediately fully filled or canceled
    immediate_or_cancel("immediate-or-cancel"); // limit order that is immediately completely or partially filled
                                                // and then the remainder is canceled

    private String value;

    OrderType(String v) {
        value = v;
    }

    @JsonCreator
    public static OrderType create(@JsonProperty("orderType") String t) {
        //OrderType typeVal = Arrays.asList(values()).stream().findFirst(val -> val.getValue().equals(t));

        for(OrderType ot : values()) {
            if(ot.getValue().equals(t)) {
                return ot;
            }
        }
        throw new IllegalArgumentException("Cannot find matching OrderType for val=" + t);
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
