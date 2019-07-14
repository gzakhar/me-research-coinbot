package services;

import com.google.gson.Gson;
import domain.Summary;
import domain.Trades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connector {


    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";


    public static Trades executeGet(String targetURL) throws Exception {

        Gson gson = new Gson();
        URL obj = new URL(targetURL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        connection.setRequestProperty("User-Agent", USER_AGENT);


        System.out.println("\nSending 'GET' request to URL : " + targetURL);
        System.out.println("Response Code : " + connection.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        Trades[] result = gson.fromJson(response.toString(), Trades[].class);
        System.out.println("The String for the Trade is : " + result[0].toString());
        return result[0];
    }
}
