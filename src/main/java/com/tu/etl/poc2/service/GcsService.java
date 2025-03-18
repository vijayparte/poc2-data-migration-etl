package com.tu.etl.poc2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.tu.etl.poc2.model.DataModel;
import com.tu.etl.poc2.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class GcsService {

    @Autowired
    private DataRepository dataRepository;

    public void fetchAndProcessData(String bucketName, String blobName) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = storage.get(bucketName, blobName);
        String jsonData = new String(blob.getContent(), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        List<DataModel> dataModels = Arrays.asList(objectMapper.readValue(jsonData, DataModel[].class));

        dataRepository.saveAll(dataModels);
    }
}