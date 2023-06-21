package com.FetchNextNumber.controller;

import com.FetchNextNumber.entity.FetchNextNumberRequest;
import com.FetchNextNumber.entity.FetchNextNumberResponse;
import com.FetchNextNumber.service.FetchNextNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FetchNextNumber")
public class FetchNextNumberController {

    private final FetchNextNumberService fetchNextNumberService;

    @Autowired
    public FetchNextNumberController(FetchNextNumberService fetchNextNumberService) {
        this.fetchNextNumberService = fetchNextNumberService;
    }

    @PostMapping
    public ResponseEntity<FetchNextNumberResponse> fetchNextNumber(@RequestBody FetchNextNumberRequest request) {
        // Delegate the request handling to the service
        FetchNextNumberResponse response = fetchNextNumberService.fetchNextNumber(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

