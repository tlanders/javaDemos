package biz.lci.stockfighter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by tlanders on 4/17/2016.
 */
public class QuoteResponse extends BaseResponse {
    private String symbol;
    private String venue;
    private int bid;    // best price currently bid for the stock
    private int ask;    // best price currently offered for the stock
    private int bidSize;    // aggregate size of all orders at the best bid
    private int askSize;    // aggregate size of all orders at the best ask
    private int bidDepth;   // aggregate size of *all bids*
    private int askDepth;   // aggregate size of *all asks*
    private int last;       // price of last trade
    private int lastSize;   // quantity of last trade
    private String lastTrade;   // timestamp of last trade
    private String quoteTime;   // ts we last updated quote at (server-side)}

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "ok=" + ok +
                ", error=" + error +
                ", symbol='" + symbol + '\'' +
                ", venue='" + venue + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", bidSize=" + bidSize +
                ", askSize=" + askSize +
                ", bidDepth=" + bidDepth +
                ", askDepth=" + askDepth +
                ", last=" + last +
                ", lastSize=" + lastSize +
                ", lastTrade='" + lastTrade + '\'' +
                ", quoteTime='" + quoteTime + '\'' +
                '}';
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getAsk() {
        return ask;
    }

    public void setAsk(int ask) {
        this.ask = ask;
    }

    public int getBidSize() {
        return bidSize;
    }

    public void setBidSize(int bidSize) {
        this.bidSize = bidSize;
    }

    public int getAskSize() {
        return askSize;
    }

    public void setAskSize(int askSize) {
        this.askSize = askSize;
    }

    public int getBidDepth() {
        return bidDepth;
    }

    public void setBidDepth(int bidDepth) {
        this.bidDepth = bidDepth;
    }

    public int getAskDepth() {
        return askDepth;
    }

    public void setAskDepth(int askDepth) {
        this.askDepth = askDepth;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getLastSize() {
        return lastSize;
    }

    public void setLastSize(int lastSize) {
        this.lastSize = lastSize;
    }

    public String getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(String lastTrade) {
        this.lastTrade = lastTrade;
    }

    public Date getLastTradeTimestamp() throws ParseException {
        return convertToDate(lastTrade);
    }
    public String getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime(String quoteTime) {
        this.quoteTime = quoteTime;
    }

    public Date getQuoteTimestamp() throws ParseException {
        return convertToDate(quoteTime);
    }
}