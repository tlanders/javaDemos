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

/**
 * Created by tlanders on 4/16/2016.
 */
public class ChockABlock {
    private static final String account = "DB82764264";
    private static final String exchange = "COHKEX";
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

    private static void placeOrder(String stock, int qty) throws Exception {
        placeOrder(stock, qty, 0, OrderType.market);
    }

    private static void getOrderStatus(String orderId, String stock) throws Exception {
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

//        {
//            "ok": true,
//                "symbol": "ROBO",
//                "venue": "ROBUST",
//                "direction": "buy",
//                "originalQty": 85,
//                "qty": 40,
//                "price": 993,
//                "orderType": "immediate-or-cancel",
//                "id": 1,
//                "account": "FOO123",
//                "ts": "2015-08-10T16:10:32.987288+09:00",
//                "fills": [
//            {
//                "price": 366,
//                    "qty": 45,
//                    "ts": "2015-08-10T16:10:32.987292+09:00"
//            }
//            ],
//            "totalFilled": 85,
//                "open": true
//        }

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

        String stock = "SLH";

        int sharesPurchased = 0;
        final int sharesDesired = 100000;
        int initialPrice = 0;
        QuoteResponse initialQuote = getQuote(stock);
        if(initialQuote.isOk()) {
            initialPrice = initialQuote.getAsk();
        }

        System.out.println("initialQuote=" + initialQuote);

        while(sharesPurchased < sharesDesired) {
            QuoteResponse currentQuote = getQuote(stock);
            if(currentQuote != null && currentQuote.isOk()) {
                if(currentQuote.getAsk() <= initialPrice
                        || ((currentQuote.getAsk() - initialPrice) / ((float) initialPrice)) <= 0.10) {
                    // buy if the price is less than the initial price or if it is no higher than 10%
                    // of the original price
                    System.out.println("price looks good, current ask=" + currentQuote.getAsk() + ", initial=" + initialPrice);
                }
            } else {
                // wait?
                System.out.println("error getting quote, quote response=" + currentQuote);
            }

            Thread.sleep(2000);
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
