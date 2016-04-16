package biz.lci.stockfighter;

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

        // Get-Capture Complete application/xml body response
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        System.out.println("============Output:============");

        // Simply iterate through XML response and show on console.
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        System.out.println("Heartbeat done.");
    }
}
