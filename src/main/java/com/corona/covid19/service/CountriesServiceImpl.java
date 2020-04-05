package com.corona.covid19.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {

    Logger logger = LoggerFactory.getLogger(CountriesServiceImpl.class);

    @Override
    public ResponseEntity<Object> findAllCountriesList() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("covid-193.p.rapidapi.com")
                .addPathSegment("countries")
                .build();

        logger.info("findAllCountries List URL {}", httpUrl.uri());

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "25b7ba3629mshe33cad59adc33f8p12512fjsn512a9a726856")
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonCell = new JSONObject(response.body().string());
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonCell));
        JsonNode responseNode = rootNode.path("response");

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findAllIndianStatesList() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sheets.googleapis.com/v4/spreadsheets/1pjeE5fA3pNnAo3XObTmpLP5FXoMCSiSEbibw6S0oK7A/values/sheet1?key=AIzaSyCaWYwwu1-BJSXrPvHyYIZBiKP2PqrHqQA")
                .build();
        logger.info("findByState URL {}", request.url());

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonCell = new JSONObject(response.body().string());
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonCell));

        JsonNode responseNode = rootNode.path("values");
        // logger.info("Google sheet values node {}",responseNode.toString());

        ArrayNode arrayNode = (ArrayNode) rootNode.get("values");

        List<String> listofStates = new ArrayList<>();

        for (int i = 2; i < arrayNode.size(); i++) {
            ArrayNode valuesArray = (ArrayNode) arrayNode.get(i);
            listofStates.add(valuesArray.get(0).textValue());
        }
        String stateJson = new Gson().toJson(listofStates);
        return new ResponseEntity<>(stateJson, HttpStatus.OK);
    }

}
