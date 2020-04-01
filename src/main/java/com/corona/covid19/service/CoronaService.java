package com.corona.covid19.service;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CoronaService {

    ResponseEntity<Object> findAllStatistics() throws IOException, JSONException;

    ResponseEntity<Object> findStatisticsByCountry(String country) throws IOException, JSONException;

    ResponseEntity<Object> findHistoryByCountry(String country) throws IOException, JSONException;


}
