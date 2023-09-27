package com.demo.event.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.demo.event.domain.model.FileMeta;
import com.demo.event.infrastructure.repository.FileMetaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetadataService implements IMetadataService {

    private static final java.util.UUID UUID = java.util.UUID.randomUUID();
    private final IAmazons3Service amazons3Service;

    private final AmazonS3 amazonS3;

    private final FileMetaRepository repository;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Override
    public void upload(MultipartFile file) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazons3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        // Saving metadata to db
        repository.save(new FileMeta(fileName, path, putObjectResult.getMetadata().getVersionId()));

    }

    @Override
    public S3Object download(Long id) {
        FileMeta fileMeta = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return amazons3Service.download(fileMeta.getFilePath(), fileMeta.getFileName());
    }

    @Override
    public List<FileMeta> list() {
        List<FileMeta> metas = new ArrayList<>();
        repository.findAll().forEach(metas::add);
        return metas;
    }

    @Override
    public String uploadAndGenerateDownloadLink(byte[] excelBytes) {

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        String s3FilePath = String.format("%s/%s", bucketName, UUID.randomUUID());
        String s3FileName = String.format("event_%s.xlsx", LocalDateTime.now().toString());

        ByteArrayInputStream excelInputStream = new ByteArrayInputStream(excelBytes);

        PutObjectResult putObjectResult = amazons3Service.upload(
                s3FilePath, s3FileName, Optional.of(metadata), excelInputStream);

        String s3FileUrl = amazonS3.getUrl(null, s3FilePath + "/" + s3FileName).toString();

        repository.save(new FileMeta(s3FileName, s3FilePath, putObjectResult.getMetadata().getVersionId()));

        return s3FileUrl;
    }

    @Override
    public Boolean deleteFile(String fileName) {
        try {
            amazonS3.deleteObject(bucketName, fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> listFiles() {
        ListObjectsRequest listObjectsRequest =
                new ListObjectsRequest()
                        .withBucketName(bucketName);

        List<String> keys = new ArrayList<>();

        ObjectListing objects = amazonS3.listObjects(listObjectsRequest);

        while (true) {
            List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
            if (objectSummaries.size() < 1) {
                break;
            }

            for (S3ObjectSummary item : objectSummaries) {
                if (!item.getKey().endsWith("/"))
                    keys.add(item.getKey());
            }

            objects = amazonS3.listNextBatchOfObjects(objects);
        }

        return keys;
    }
}
