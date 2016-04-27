package biz.lci.stockfighter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tlanders on 4/16/2016.
 */
public class ChockABlock {
    private static final String account = "KAH21267612";
    private static final String exchange = "IQYPEX";
    private static final String apiKey = "83725b0407ce7fa3066aea6586fe49e1dc09dd6a";

    private static final HttpClient httpClient = HttpClientBuilder.create().build();
    private static final ObjectMapper mapper = new ObjectMapper();

    private static QuoteResponse getQuote(String stock) throws Exception {
        String quoteUrl = "https://api.stockfighter.io/ob/api/venues/"
                + exchange + "/stocks/"
                + stock + "/quote";

        HttpGet getRequest = new HttpGet(quoteUrl);
        getRequest.addHeader("accept", "application/json");
        getRequest.addHeader("X-Starfighter-Authorization", apiKey);

        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("getQuote HTTP error code: " + response.getStatusLine().getStatusCode());
            return null;
        }

        return mapper.readValue(new InputStreamReader(response.getEntity().getContent()), QuoteResponse.class);
    }

    private static OrderResponse placeOrder(String stock, int qty) throws Exception {
        return placeOrder(stock, qty, 0, OrderType.market);
    }

    private static OrderResponse getOrderStatus(String orderId, String stock) throws Exception {
        String statusUrl = "https://api.stockfighter.io/ob/api/venues/"
                + exchange + "/stocks/"
                + stock + "/orders/"
                + orderId;

        HttpGet getRequest = new HttpGet(statusUrl);
        getRequest.addHeader("accept", "application/json");
        getRequest.addHeader("X-Starfighter-Authorization", apiKey);

        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        return mapper.readValue(new InputStreamReader(response.getEntity().getContent()), OrderResponse.class);
//                "ts": "2015-08-10T16:10:32.987288+09:00",
//                "fills": [
//            {
//                "price": 366,
//                    "qty": 45,
//                    "ts": "2015-08-10T16:10:32.987292+09:00"
//            }
//            ],

    }
    private static OrderResponse placeOrder(String stock, int qty, int price, OrderType orderType) throws Exception {
        String orderUrl = "https://api.stockfighter.io/ob/api/venues/"
                + exchange + "/stocks/"
                + stock + "/orders";
        Order order = new Order();
        order.setAccount(account);
        order.setVenue(exchange);
        order.setStock(stock);
        order.setQty(qty);
        order.setPrice(price);  // not relevant for market order
        order.setDirection(Direction.buy);
        order.setOrderType(orderType);

        String orderData = mapper.writeValueAsString(order);

        HttpPost post = new HttpPost(orderUrl);
        post.addHeader("X-Starfighter-Authorization", apiKey);
        StringEntity orderEntity = new StringEntity(orderData);
        orderEntity.setContentType("application/json");
        post.setEntity(orderEntity);

        HttpResponse response = httpClient.execute(post);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

//        System.out.println("orderResponse:");
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String output = "";
//
//        while((output = br.readLine()) != null) {
//            System.out.println(output);
//        }

        return mapper.readValue(new InputStreamReader(response.getEntity().getContent()), OrderResponse.class);
    }

    public static void main(String [] args) throws Exception {
        System.out.println("Chock a block starting.");

        String stock = "LRC";

       // OrderResponse resp = placeOrder(stock, 100);

        int sharesPurchased = 0;
        final int sharesDesired = 100000;
        int targetPrice = 6248;
//        while(initialPrice <= 0) {
//            QuoteResponse initialQuote = getQuote(stock);
//            if (initialQuote.isOk() && initialQuote.getAsk() > 0) {
//                initialPrice = initialQuote.getAsk();
//            } else {
//                Thread.sleep(500);
//            }
//        }

        System.out.println("targetPrice=" + targetPrice);

        while(sharesPurchased < sharesDesired) {
            QuoteResponse currentQuote = getQuote(stock);
            if(currentQuote != null && currentQuote.isOk()) {
                if(currentQuote.getAsk() > 0 && currentQuote.getAskSize() > 0
                        && (currentQuote.getAsk() <= targetPrice
                        || ((currentQuote.getAsk() - targetPrice) / ((float) targetPrice)) <= 0.05)) {
                    // buy if the price is less than the initial price or if it is no higher than 5%
                    // of the original price
                    int sharesToBuy = Math.min(currentQuote.getBidSize(), sharesDesired - sharesPurchased) / 5;

                    if(sharesToBuy >= sharesDesired / 500) {
                        System.out.println("price looks good, current ask=" + currentQuote.getAsk()
                                + ", target=" + targetPrice
                                + ", sharesToBuy=" + sharesToBuy);

                        OrderResponse orderResponse = placeOrder(stock, sharesToBuy);
                        System.out.println("orderResponse=" + orderResponse);

                        // XXX - assume all were purchased for now
                        sharesPurchased += sharesToBuy;

                        Thread.sleep(1000);
                    } else {
                        System.out.println("don't like ask size, current ask=" + currentQuote.getAsk()
                                + ", askSize=" + currentQuote.getAskSize()
                                + ", target=" + targetPrice);
                    }
                } else {
                 System.out.println("don't like price, current ask=" + currentQuote.getAsk()
                            + ", target=" + targetPrice);
                }
            } else {
                // wait?
                System.out.println("error getting quote, quote response=" + currentQuote);
            }

            Thread.sleep(500);
        }

//
//
//        // Check for HTTP response code: 200 = success
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String output = "";
//
//        System.out.println("quoteResponse:");
//        while((output = br.readLine()) != null) {
//            System.out.println(output);
//        }
//
//
//        System.out.println("Chock a block done.");
    }
}
