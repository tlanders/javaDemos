package biz.lci.stockfighter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

        String account = "LIH57979377";
        String exchange = "QPMCEX";
        String stock = "IVUX";
        String apiKey = "83725b0407ce7fa3066aea6586fe49e1dc09dd6a";

        String url = "https://api.stockfighter.io/ob/api/venues/QPMCEX/stocks/IVUX/quote";

        // X-Starfighter-Authorization:83725b0407ce7fa3066aea6586fe49e1dc09dd6a
//
//        String url = "https://api.stockfighter.io/ob/api/heartbeat";
//
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");
        getRequest.addHeader("X-Starfighter-Authorization", apiKey);

        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String output = "";

        while((output = br.readLine()) != null) {
            System.out.println(output);
        }
//        ObjectMapper mapper = new ObjectMapper();
//
//        // Get-Capture Complete application/xml body response
//        HeartbeatResponse hr = mapper.readValue(response.getEntity().getContent(), HeartbeatResponse.class);
//
//        System.out.println("Heartbeat Output: " + hr);
//
//        // calling test venue heartbeat
//        String venueUrl = "https://api.stockfighter.io/ob/api/venues/TESTEX/heartbeat";
//        HttpGet get2 = new HttpGet(venueUrl);
//
//        response = httpClient.execute(get2);
//        if (response.getStatusLine().getStatusCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//        }
//
//        hr = mapper.readValue(response.getEntity().getContent(), HeartbeatResponse.class);
//
//        System.out.println("Venue Heartbeat Output: " + hr);

        System.out.println("First steps done.");
    }
}
