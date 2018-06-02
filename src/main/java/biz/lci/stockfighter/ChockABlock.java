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
import java.util.TreeMap;

/**
 * Created by tlanders on 4/16/2016.
 */
public class ChockABlock {
    private static final String account = "BBS37846442";
    private static final String exchange = "EQOKEX";
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

        if(args.length < 1) {
            System.out.println("Chock a block needs target price");
        }

        int targetPrice = Integer.parseInt(args[0]);
        String stock = "OGUW";

        int sharesPurchased = 0;
        final int sharesDesired = 100000;
        Map<String, Integer> orderBook = new TreeMap<>();

        System.out.println("targetPrice=" + targetPrice);

        while(sharesPurchased < sharesDesired) {
            QuoteResponse currentQuote = getQuote(stock);
            if(currentQuote != null && currentQuote.isOk()) {
                if(currentQuote.getAsk() > 0 && currentQuote.getAskSize() > 0
                        && (currentQuote.getAsk() <= targetPrice
                        || ((currentQuote.getAsk() - targetPrice) / ((float) targetPrice)) <= 0.03)) {
                    // buy if the price is less than the initial price or if it is no higher than 3%
                    // of the target price
                    int sharesToBuy = Math.min(currentQuote.getAskSize() / 3, sharesDesired - sharesPurchased);

                    if(sharesToBuy >= sharesDesired / 500) {
                        System.out.println("price looks good, current ask=" + currentQuote.getAsk()
                                + ", askSize=" + currentQuote.getAskSize()
                                + ", target=" + targetPrice
                                + ", sharesToBuy=" + sharesToBuy);

                        OrderResponse orderResponse = placeOrder(stock, sharesToBuy,
                                currentQuote.getAsk(), OrderType.ioc);
                        System.out.println("orderResponse=" + orderResponse);

                        if(orderResponse.isOk()) {
                            // add this order to the book so we can track its status
                            orderBook.put(orderResponse.getId(), orderResponse.getTotalFilled());
                        } else {
                            System.out.println("chock a block: order error=" + orderResponse.getError());
                        }

                        Thread.sleep(200);

                        // calculate total shares purchased
                        sharesPurchased = orderBook.keySet().stream().mapToInt(orderId -> {
                            try {
                                OrderResponse or = getOrderStatus(orderId, stock);
                                if(or.isOk()) {
                                    System.out.println("checking order status, id=" + orderId
                                            + ", fills=" + or.getTotalFilled());
                                    return or.getTotalFilled();
                                }
                            } catch(Exception ex) {
                                ex.printStackTrace();
                            }
                            System.out.println("checking order status, error getting fills for id=" + orderId);
                            return 0;
                        }).sum();

                        System.out.println("sharesPurchased=" + sharesPurchased);
                        Thread.sleep(800);
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
    }
}
