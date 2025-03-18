package com.tu.etl.poc2.controller;

import com.tu.etl.poc2.service.GcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DataController {

    @Autowired
    private GcsService gcsService;

    @GetMapping("/fetch-and-process")
    public String fetchAndProcessData(@RequestParam String bucketName, @RequestParam String blobName) throws IOException {
        gcsService.fetchAndProcessData(bucketName, blobName);
        return "Data fetched and processed successfully!";
    }
}