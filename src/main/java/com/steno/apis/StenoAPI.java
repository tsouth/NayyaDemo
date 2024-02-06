package com.steno.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steno.utilities.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.TestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stenoAPI {
    private final String stenoURL = "https://thomassouthworth.gosteno.com/api/v1/maintenance-requests";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger log = new Logger(getClass().getName());

    public List<String> getWorkRequests (String module) {
        String apiRequest = "{" + "requests(module:\"" + module + "\") {" + "name" + "}" + "}";

        Map <?,?> response = post(apiRequest);
        Map <?,?> data = objectMapper.convertValue(response.get("data"), Map.class);
        List<?> requests = objectMapper.convertValue(data.get("requests"), List.class);

        List<String> maintenanceRequests = new ArrayList<>();
        for (Object request : requests) {
            Map<?,?> requestMap = objectMapper.convertValue(request, Map.class);
            maintenanceRequests.add(requestMap.get("name").toString());
        }
        return maintenanceRequests;
    }

    private Map<?,?> post(String request) {
        HttpPost postRequest = new HttpPost(stenoURL);
        postRequest.addHeader("Content-Type", "application/json");
        postRequest.setEntity(new ByteArrayEntity(request.getBytes(StandardCharsets.UTF_8)));
        CloseableHttpClient connection = HttpClients.createDefault();

        CloseableHttpResponse response;
        try {
            response = connection.execute(postRequest);
        } catch (ClientProtocolException e) {
            throw new TestException("Protocol is wrong for GraphQL post request: " + postRequest.toString());
        } catch (IOException e) {
            throw new TestException("Request failed to send: " + postRequest.toString());
        }

        log.info("SENT PAYLOAD: " + request);
        log.info("Status Code: " + response.getStatusLine().getStatusCode());
        String responseBody;
        try {
            responseBody = new BasicResponseHandler().handleResponse(response);
            log.info("Status Message: " + responseBody);
        } catch (IOException e) {
            throw new TestException("GraphQL request failed: \n\n" + request);
        }
        Map<String, Object> responseBodyObject = new Gson().fromJson(
                responseBody, new TypeToken<HashMap<String, Object>>() {}.getType());

        return objectMapper.convertValue(responseBodyObject, Map.class);
    }
}
