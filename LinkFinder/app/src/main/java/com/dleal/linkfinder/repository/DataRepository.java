package com.dleal.linkfinder.repository;

import com.dleal.linkfinder.repository.responses.ErrorBundle;
import com.dleal.linkfinder.repository.responses.WebResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.dleal.linkfinder.utils.Constants.CONNECTION_TIMEOUT;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class DataRepository {

    public WebResponse getWebsite(URL url) {
        try {
            Connection.Response response = Jsoup.connect(url.toString()).timeout(CONNECTION_TIMEOUT).followRedirects(true).execute();
            Document doc = response.parse();
            return new WebResponse(doc);
        } catch (IOException e) {
            return WebResponse.withErrorBundle(new ErrorBundle(e.getMessage()));
        }
    }

    public WebResponse getWebsiteContent(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            String html = readStream(con.getInputStream());
            Document document = Jsoup.parse(html);
            return new WebResponse(document);
        } catch (Exception e) {
            return WebResponse.withErrorBundle(new ErrorBundle(e.getMessage()));
        }
    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
