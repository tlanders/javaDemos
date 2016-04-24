package biz.lci.stockfighter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BufferedHeader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by tlanders on 4/16/2016.
 */
public class FirstSteps {
    public static void main(String [] args) throws Exception {
        System.out.println("First steps starting.");

        String account = "MRB74035505";
        String exchange = "YTOMEX";
        String stock = "MFS";
        String apiKey = "83725b0407ce7fa3066aea6586fe49e1dc09dd6a";

        String quoteUrl = "https://api.stockfighter.io/ob/api/venues/"
                + exchange + "/stocks/"
                + stock + "/quote";

        System.out.println("quoteUrl=" + quoteUrl);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(quoteUrl);
        getRequest.addHeader("accept", "application/json");
        getRequest.addHeader("X-Starfighter-Authorization", apiKey);

        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String output = "";

        System.out.println("quoteResponse:");
        while((output = br.readLine()) != null) {
            System.out.println(output);
        }

        Order order = new Order();
        order.setAccount(account);
        order.setVenue("");
        order.setStock("");
        order.setQty(100);
        order.setPrice(0);  // not relevant for market order
        order.setDirection(Direction.buy);
        order.setOrderType(OrderType.market);

        ObjectMapper mapper = new ObjectMapper();
        String orderData = mapper.writeValueAsString(order);

        String orderUrl = "https://api.stockfighter.io/ob/api/venues/"
                + exchange + "/stocks/"
                + stock + "/orders";

        System.out.println("orderUrl=" + orderUrl);

        HttpPost post = new HttpPost(orderUrl);
        post.addHeader("X-Starfighter-Authorization", apiKey);
        StringEntity orderEntity = new StringEntity(orderData);
        orderEntity.setContentType("application/json");
        post.setEntity(orderEntity);

        response = httpClient.execute(post);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        System.out.println("orderResponse:");

        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        output = "";

        while((output = br.readLine()) != null) {
            System.out.println(output);
        }

        System.out.println("First steps done.");
    }
}
