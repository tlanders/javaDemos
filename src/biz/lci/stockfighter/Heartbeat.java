package biz.lci.stockfighter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by tlanders on 4/16/2016.
 */
public class Heartbeat {
    public static void main(String [] args) throws Exception {
        System.out.println("Heartbeat starting.");

        String url = "https://api.stockfighter.io/ob/api/heartbeat";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        ObjectMapper mapper = new ObjectMapper();

        // Get-Capture Complete application/xml body response
        HeartbeatResponse hr = mapper.readValue(response.getEntity().getContent(), HeartbeatResponse.class);

        System.out.println("Heartbeat Output: " + hr);

        // calling test venue heartbeat
        String venueUrl = "https://api.stockfighter.io/ob/api/venues/TESTEX/heartbeat";
        HttpGet get2 = new HttpGet(venueUrl);

        response = httpClient.execute(get2);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        hr = mapper.readValue(response.getEntity().getContent(), HeartbeatResponse.class);

        System.out.println("Venue Heartbeat Output: " + hr);

        System.out.println("Heartbeat done.");
    }
}
