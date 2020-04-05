package com.corona.covid19.service;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface CountriesService {
    ResponseEntity<Object> findAllCountriesList() throws IOException, JSONException;

    ResponseEntity<Object> findAllIndianStatesList() throws IOException, JSONException;

}
