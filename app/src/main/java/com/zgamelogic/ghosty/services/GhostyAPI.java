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

    public static final String LOG_API = "GhostyAPI";
    private static final String API_URL = "https://zgamelogic.com/ghosty";

    public static Thread getGhosts(NetworkGhostSuccess success) {
        String url = API_URL + "/Ghosts3";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return new Thread(() -> {
            try {
            CloseableHttpResponse response = httpClient.execute(request);
            ObjectMapper om = new ObjectMapper();
            Ghost[] ghostsArray = om.readValue(EntityUtils.toString(response.getEntity()), Ghost[].class);
            Log.i(LOG_API, "We finished reading in the stuff");
            success.success(new LinkedList<>(Arrays.asList(ghostsArray)));
            } catch(IOException e) {
                Log.e(LOG_API, e.getMessage());
            }
        });
    }

    public static Thread getEvidence(NetworkEvidenceSuccess success) {
        String url = API_URL + "/Evidence2";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return new Thread(() -> {
            try {
                CloseableHttpResponse response = httpClient.execute(request);
                ObjectMapper om = new ObjectMapper();
                String[] evidenceArray = om.readValue(EntityUtils.toString(response.getEntity()), String[].class);
                Log.i(LOG_API, "We finished reading in the stuff");
                success.success(new LinkedList<>(Arrays.asList(evidenceArray)));
            } catch(IOException e) {
                Log.e(LOG_API, e.getMessage());
            }
        });
    }

    public interface NetworkGhostSuccess {
        void success(LinkedList<Ghost> ghosts);
    }

    public interface NetworkEvidenceSuccess {
        void success(LinkedList<String> evidence);
    }
}
