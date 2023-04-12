package com.pk.ing;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.pk.ing.utils.AppConsts.APP_PORT;
import static com.pk.ing.utils.AppConsts.ATMS_CALCULATE_ORDER_URL;
import static com.pk.ing.utils.AppConsts.EMPTY;
import static com.pk.ing.utils.AppConsts.NEW_LINE;
import static com.pk.ing.utils.AppConsts.ONLINEGAME_CALCULATE_URL;
import static com.pk.ing.utils.AppConsts.SPACE;
import static com.pk.ing.utils.AppConsts.TRANSACTIONS_REPORT_URL;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

class IngSparkAppTest {
    private IngSparkApp app;
    private CloseableHttpClient client;

    @BeforeEach
    void init() {
        app = new IngSparkApp();
        app.init();
        client = HttpClientBuilder.create().build();
    }

    @AfterEach
    void cleanup() {
        app.stop();
    }

    @Test
    void onlineGameEndpointE2ETest() throws IOException {
        String input = readJsonFromResources("json/onlinegame/example_request.json");
        String expected = readJsonFromResources("json/onlinegame/example_response.json");

        HttpEntity response = postJsonToLocalHost(ONLINEGAME_CALCULATE_URL, input);
        String responseContent = new String(response.getContent().readAllBytes());

        Assertions.assertEquals(expected, responseContent);
    }

    @Test
    void atmServiceEndpointE2ETest1() throws IOException {
        String input = readJsonFromResources("json/atmservice/example_1_request.json");
        String expected = readJsonFromResources("json/atmservice/example_1_response.json");

        HttpEntity response = postJsonToLocalHost(ATMS_CALCULATE_ORDER_URL, input);
        String responseContent = new String(response.getContent().readAllBytes());

        Assertions.assertEquals(expected, responseContent);
    }


    @Test
    void atmServiceEndpointE2ETest2() throws IOException {
        String input = readJsonFromResources("json/atmservice/example_2_request.json");
        String expected = readJsonFromResources("json/atmservice/example_2_response.json");

        HttpEntity response = postJsonToLocalHost(ATMS_CALCULATE_ORDER_URL, input);
        String responseContent = new String(response.getContent().readAllBytes());

        Assertions.assertEquals(expected, responseContent);
    }

    @Test
    void transactionsEndpointE2ETest() throws IOException {
        String input = readJsonFromResources("json/transactions/example_request.json");
        String expected = readJsonFromResources("json/transactions/example_response.json");

        HttpEntity response = postJsonToLocalHost(TRANSACTIONS_REPORT_URL, input);
        String responseContent = new String(response.getContent().readAllBytes());

        Assertions.assertEquals(expected, responseContent);
    }

    private HttpEntity postJsonToLocalHost(String path, String content) throws IOException {

        HttpPost httpPost = new HttpPost(String.format("http://localhost:%s%s", APP_PORT, path));
        httpPost.setHeader(ACCEPT, APPLICATION_JSON.toString());
        httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
        httpPost.setEntity(new StringEntity(content));

        return client.execute(httpPost).getEntity();
    }

    private String readJsonFromResources(String path) throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (Objects.isNull(resourceAsStream)) {
            throw new RuntimeException("couldn't read json: " + path);
        }
        return new String(resourceAsStream.readAllBytes())
                .replace(SPACE, EMPTY)
                .replace(NEW_LINE, EMPTY);
    }
}