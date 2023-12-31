package com.demo.event.domain.service;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.event.domain.model.FileMeta;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IMetadataService {
    void upload(MultipartFile file) throws IOException;

    S3Object download(Long id);

    List<FileMeta> list();

    String uploadAndGenerateDownloadLink(byte[] excelBytes);

    Boolean deleteFile(final String fileName);

    List<String> listFiles();

    File convertMultiPartToFile(MultipartFile file) throws IOException;

    String generateFileName(MultipartFile multiPart);

    void uploadFileTos3bucket(String fileName, File file);

    String uploadFile(MultipartFile multipartFile);
}
