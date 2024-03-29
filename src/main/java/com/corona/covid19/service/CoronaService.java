package com.corona.covid19.service;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CoronaService {

    ResponseEntity<Object> findAllStatistics() throws IOException, JSONException;

    ResponseEntity<Object> findStatisticsByCountry(String country) throws IOException, JSONException;

    ResponseEntity<Object> findHistoryByCountry(String country) throws IOException, JSONException;

    ResponseEntity<Object> findAllIndianState() throws IOException, JSONException;

    ResponseEntity<Object> findAllIndianStateByApi() throws IOException, JSONException;

    ResponseEntity<Object> findAllTimeSeriesDetailsApi() throws IOException, JSONException;

    ResponseEntity<Object> findAllCovidVideos() throws IOException, JSONException;
}
