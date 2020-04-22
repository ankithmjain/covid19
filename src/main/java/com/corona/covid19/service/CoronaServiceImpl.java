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
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoronaServiceImpl implements CoronaService {

    Logger logger = LoggerFactory.getLogger(CoronaServiceImpl.class);

    @Override
    public ResponseEntity<Object> findAllStatistics() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("covid-193.p.rapidapi.com")
                .addPathSegment("statistics")
                .build();

        logger.info("findAllStatistics URL {}",httpUrl.uri());

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
    public ResponseEntity<Object> findStatisticsByCountry(String country) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("covid-193.p.rapidapi.com")
                .addPathSegment("statistics")
                .addQueryParameter("country", country)
                .build();

        logger.info("findStatisticsByCountry URL {}",httpUrl.uri());

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
        JsonNode resultNode = rootNode.path("results");
        System.out.println("Corona JSON NODE " + responseNode.toString());
        System.out.println("Corona total Results NODE " + resultNode);
        String prettyPrintEmployee1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseNode);
        System.out.println("Corona PRETTY  JSON " + prettyPrintEmployee1);

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findHistoryByCountry(String country) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("covid-193.p.rapidapi.com")
                .addPathSegment("history")
                .addQueryParameter("country", country)
                .build();
        logger.info("findHistoryByCountry URL {}",httpUrl.uri());

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
//        JsonNode resultNode = rootNode.path("results");
//        System.out.println("Corona JSON NODE " + responseNode.toString());
//        System.out.println("Corona total Results NODE " + resultNode);
//        String prettyPrintEmployee1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseNode);
//        System.out.println("Corona PRETTY  JSON " + prettyPrintEmployee1);

        return new ResponseEntity<>(responseNode, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findAllIndianState() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sheets.googleapis.com/v4/spreadsheets/1pjeE5fA3pNnAo3XObTmpLP5FXoMCSiSEbibw6S0oK7A/values/sheet1?key=AIzaSyCaWYwwu1-BJSXrPvHyYIZBiKP2PqrHqQA")
                .build();
        logger.info("findByState URL {}",request.url());

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonCell = new JSONObject(response.body().string());
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonCell));

        JsonNode responseNode = rootNode.path("values");
       // logger.info("Google sheet values node {}",responseNode.toString());

        ArrayNode arrayNode = (ArrayNode) rootNode.get("values");

        List<Map<String, String>> listofStates = new ArrayList<Map<String, String>>();

        Map<String, String> individualStateMapping = null;

        for (int i = 1; i < arrayNode.size(); i++) {
            individualStateMapping = new LinkedHashMap<>();
            ArrayNode valuesArray = (ArrayNode) arrayNode.get(i);
            individualStateMapping.put("State", valuesArray.get(0).textValue());
            individualStateMapping.put("Confirmed", valuesArray.get(1).textValue());
            individualStateMapping.put("Recovered", valuesArray.get(2).textValue());
            individualStateMapping.put("Deaths", valuesArray.get(3).textValue());
            individualStateMapping.put("Active", valuesArray.get(4).textValue());
            individualStateMapping.put("Last_Updated_Time", valuesArray.get(5).textValue());
            listofStates.add(individualStateMapping);
        }
        // logger.info("Corona state wise mapping" + listofStates.toString());
        String stateJson = new Gson().toJson(listofStates);
        return new ResponseEntity<>(stateJson, HttpStatus.OK);
    }


    public ResponseEntity<Object> findAllCovidVideos() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sheets.googleapis.com/v4/spreadsheets/1pjeE5fA3pNnAo3XObTmpLP5FXoMCSiSEbibw6S0oK7A/values/URLS?key=AIzaSyCaWYwwu1-BJSXrPvHyYIZBiKP2PqrHqQA")
                .build();

        logger.info("findAllCovidVideos URL {}",request.url());

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonCell = new JSONObject(response.body().string());
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonCell));
        JsonNode responseNode = rootNode.path("values");

        ArrayNode arrayNode = (ArrayNode) rootNode.get("values");

        List<Map<String, String>> listOfVideos = new ArrayList<Map<String, String>>();

        Map<String, String> individualVideoMapping = null;


        for (int i = 1; i < arrayNode.size(); i++) {
            individualVideoMapping = new LinkedHashMap<>();
            ArrayNode valuesArray = (ArrayNode) arrayNode.get(i);
            individualVideoMapping.put("Title", valuesArray.get(0).textValue());
            individualVideoMapping.put("Url", valuesArray.get(1).textValue());
            listOfVideos.add(individualVideoMapping);
        }


        String videoJson = new Gson().toJson(listOfVideos);
        return new ResponseEntity<>(videoJson, HttpStatus.OK);

    }


    @Override
    public ResponseEntity<Object> findAllIndianStateByApi() throws IOException, JSONException {

        ArrayList<Object> resultNode = new ArrayList<Object>();

        String url = "https://api.covid19india.org/data.json";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);

        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);

        String mapArray[] = new String[map.size()];
        System.out.println("Items found: " + mapArray.length);

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            if (entry.getKey().equalsIgnoreCase("statewise")) {
                resultNode = (ArrayList<Object>) entry.getValue();
            }
        }

        return new ResponseEntity<>(resultNode, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findAllTimeSeriesDetailsApi() throws IOException, JSONException {

        ArrayList<Object> resultNode = new ArrayList<Object>();

        String url = "https://api.covid19india.org/data.json";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);

        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);

        String mapArray[] = new String[map.size()];
        System.out.println("Items found: " + mapArray.length);

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            if (entry.getKey().equalsIgnoreCase("cases_time_series")) {
                resultNode = (ArrayList<Object>) entry.getValue();
            }
        }

        return new ResponseEntity<>(resultNode, HttpStatus.OK);
    }

}
