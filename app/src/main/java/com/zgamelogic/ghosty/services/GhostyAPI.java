package com.zgamelogic.ghosty.services;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgamelogic.ghosty.data.Ghost;


import org.htmlunit.org.apache.http.client.methods.CloseableHttpResponse;
import org.htmlunit.org.apache.http.client.methods.HttpGet;
import org.htmlunit.org.apache.http.impl.client.CloseableHttpClient;
import org.htmlunit.org.apache.http.impl.client.HttpClients;
import org.htmlunit.org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class GhostyAPI {

    private static final String API_URL = "https://zgamelogic.com:2006/ghosty";

    public static Thread getGhosts(NetworkSuccess success) {
        String url = API_URL + "/Ghosts3";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return new Thread(() -> {
            try {
            CloseableHttpResponse response = httpClient.execute(request);
            ObjectMapper om = new ObjectMapper();
            Ghost[] ghostsArray = om.readValue(EntityUtils.toString(response.getEntity()), Ghost[].class);
            Log.i("GhostyAPI", "We finished reading in the stuff");
            success.success(new LinkedList<>(Arrays.asList(ghostsArray)));
            } catch(IOException e) {
                Log.e("GhostyAPI", e.getMessage());
            }
        });
    }

    public LinkedList<String> getEvidences(){
        return new LinkedList<String>();
    }

    public interface NetworkSuccess {
        void success(LinkedList<Ghost> ghosts);
    }
}
