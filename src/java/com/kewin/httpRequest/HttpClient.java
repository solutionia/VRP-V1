/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.httpRequest;

import com.kewin.entities.Distance_Matrix;
import com.kewin.utils.Constans;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.kewin.interfaces.HttpClientListener;
import com.kewin.utils.Log;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Kelv
 */
public class HttpClient {

    private HttpClientListener clientListener;
    private OkHttpClient client;
    private Request request;
    private Response response;
    private ObjectMapper objectMapper;

    public HttpClient(HttpClientListener clientListener, String url) {
        this.clientListener = clientListener;
        this.client = new OkHttpClient();
        objectMapper = new ObjectMapper();
        try {
            this.request = new Request.Builder()
                    .url(url)
                    .build();

        } catch (Exception e) {
            this.clientListener.onError(e.getMessage());
        }

    }

    public boolean executeRequest() {
        try {
            response = client.newCall(request).execute();
            return true;
        } catch (IOException ex) {
            //Logger.getLogger(HttpClient.class.getName()).log(Level.SEVERE, null, ex);
            clientListener.onError(ex.getMessage());
            return false;
        }
    }

    public void parseObjects(Object object) {

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            String jsonMessage = response.body().string();
           Log.d("MATRIX",jsonMessage.toString());
            object = objectMapper.readValue(jsonMessage, object.getClass());
            clientListener.onSuccess(object);
        } catch (IOException ex) {
            clientListener.onError(ex.getMessage());
            Logger.getLogger(HttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
