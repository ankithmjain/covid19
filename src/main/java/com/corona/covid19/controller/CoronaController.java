package com.corona.covid19.controller;

import com.corona.covid19.service.CoronaService;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/")
@Api(description = "Covid19 related endpoints")
public class CoronaController {

    @Autowired
    CoronaService coronaService;

    @RequestMapping(method = RequestMethod.GET, value = "history/{country}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find History By Country",
            notes = "Return History By Country or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findHistoryByCountry(
            @ApiParam(value = "id of the country", required = true) @PathVariable("country") String country) throws IOException, JSONException {
        return coronaService.findHistoryByCountry(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "statistics",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find All Countries Statistics",
            notes = "Return All Countries Statistics or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findAllStatistics() throws IOException, JSONException {
        return coronaService.findAllStatistics();
    }

    @RequestMapping(method = RequestMethod.GET, value = "statistics/{country}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find Statistics By Country",
            notes = "Return Statistics By Country or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findStatisticsByCountry(
            @ApiParam(value = "id of the country", required = true) @PathVariable("country") String country) throws IOException, JSONException {
        return coronaService.findStatisticsByCountry(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "states",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find History By Country",
            notes = "Return History By Country or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findAllIndianStates() throws IOException, JSONException {
        return coronaService.findAllIndianState();
    }



    @RequestMapping(method = RequestMethod.GET, value = "videos",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find Covid videos",
            notes = "Return Covid videos or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findAllCovidVideos() throws IOException, JSONException {

        return coronaService.findAllCovidVideos();

    }


    @RequestMapping(method = RequestMethod.GET, value = "states-api",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "State details from the api",
            notes = "Return History By Country or throws 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findAllIndianStateByApi() throws IOException, JSONException {
        return coronaService.findAllIndianStateByApi();
    }

    @RequestMapping(method = RequestMethod.GET, value = "india-time-series",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Time Series info",
            notes = "Returns Time sereies info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Object> findAllTimeSeriesDetailsApi() throws IOException, JSONException {
        return coronaService.findAllTimeSeriesDetailsApi();
    }
}
