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

//    String orderUrl = "https://api.stockfighter.io/ob/api/venues/"
//            + exchange + "/stocks/"
//            + stock + "/orders";

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

    public static void main(String [] args) throws Exception {
        System.out.println("Chock a block starting.");

        String stock = "SLH";

        int sharesPurchased = 0;
        int initialPrice = 0;
        QuoteResponse initialQuote = getQuote(stock);
        if(initialQuote.isOk()) {
            initialPrice = initialQuote.getAsk();
        }

        System.out.println("initialQuote=" + initialQuote);
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
//        Order order = new Order();
//        order.setAccount(account);
//        order.setVenue("");
//        order.setStock("");
//        order.setQty(100);
//        order.setPrice(0);  // not relevant for market order
//        order.setDirection(Order.Direction.buy);
//        order.setOrderType(Order.OrderType.market);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String orderData = mapper.writeValueAsString(order);
//
//        HttpPost post = new HttpPost(orderUrl);
//        post.addHeader("X-Starfighter-Authorization", apiKey);
//        StringEntity orderEntity = new StringEntity(orderData);
//        orderEntity.setContentType("application/json");
//        post.setEntity(orderEntity);
//
//        response = httpClient.execute(post);
//
//        // Check for HTTP response code: 200 = success
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//
//        System.out.println("orderResponse:");
//
//        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        output = "";
//
//        while((output = br.readLine()) != null) {
//            System.out.println(output);
//        }
//
//        System.out.println("Chock a block done.");
    }
}
