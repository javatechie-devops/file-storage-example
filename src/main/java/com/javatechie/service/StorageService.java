package com.javatechie.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StorageService {

    public static final String BUCKET = "myapp-bucket8";
    @Autowired
    private AmazonS3 amazonS3Client;

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        var putObjectResult = amazonS3Client.putObject(BUCKET, keyName, file.getInputStream(), null);
        log.info(String.valueOf(putObjectResult.getMetadata()));
    }

    public S3Object getFile(String keyName) {
        return amazonS3Client.getObject(BUCKET, keyName);
    }

    public List<String> listFiles() {
        ObjectListing objectListing = amazonS3Client.listObjects(BUCKET);
        return objectListing.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(BUCKET, fileName));
    }

}
