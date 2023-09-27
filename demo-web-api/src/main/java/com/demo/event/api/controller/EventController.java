package com.demo.event.api.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.common.rest.response.BaseResponse;
import com.demo.event.api.dto.EventRequestDto;
import com.demo.event.api.dto.EventResDto;
import com.demo.event.domain.model.Event;
import com.demo.event.domain.service.IEventService;
import com.demo.event.domain.service.IMetadataService;
import com.demo.event.infrastructure.repository.EventRepository;
import com.demo.event.infrastructure.util.ExcelExport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.common.exception.DefaultErrorCode.DEFAULT_BAD_REQUEST;
import static com.demo.common.exception.DefaultErrorCode.DEFAULT_NOT_FOUND;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EventController {

    private final IEventService eventService;

    private final IMetadataService metadataService;

    private final ExcelExport excelExport;

    private final EventRepository eventRepository;

    @GetMapping("/events")
    public BaseResponse<Page<EventResDto>> getAll(Pageable pageable) {
        return BaseResponse.ofSucceeded(eventService.getEvents(pageable));
    }

    @GetMapping("/events/{id}")
    public BaseResponse<?> getById(@PathVariable Long id) {
        try {
            EventResDto eventResDto = eventService.getById(id).get();
            return BaseResponse.ofSucceeded(eventResDto);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DEFAULT_NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    public BaseResponse<?> deEvent(@PathVariable Long id) {
        Boolean del = eventService.delete(id);
        if (del) return BaseResponse.ofSucceeded("");

        return BaseResponse.ofFailed(DEFAULT_NOT_FOUND);
    }

    @PutMapping("/events/{id}")
    public BaseResponse<?> updateById(@PathVariable Long id,
                                      @RequestBody @Valid EventRequestDto eventRequestDto) {
        try {
            Event event = eventService.update(id, eventRequestDto);
            return BaseResponse.ofSucceeded(event);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DEFAULT_NOT_FOUND);
        }
    }

    @PostMapping("/events")
    public BaseResponse<?> insert(@RequestBody @Valid Event eventRequestDto) {
        try {
            Event event = eventService.insert(eventRequestDto);
            return BaseResponse.ofSucceeded(event);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DEFAULT_BAD_REQUEST);
        }

    }

    @DeleteMapping("/events")
    public BaseResponse<?> deleteAll() {
        Boolean delAll = eventService.clean();
        if (delAll) return BaseResponse.ofSucceeded("");

        return BaseResponse.ofFailed(DEFAULT_BAD_REQUEST);
    }

    @GetMapping("/events/export-to-excel")
    public void exportExcel(HttpServletResponse response, Pageable pageable) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("HH-mm_dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=events_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Event> eventResDtos = eventRepository.findAll();
        ExcelExport generator = new ExcelExport(eventResDtos);
        generator.generateExcelFile(response);
    }

    @PostMapping("/events/export-to-excel")
    public void upload(MultipartFile file) throws IOException {
        metadataService.upload(file);
    }

    @GetMapping("/events/download/{id}")
    public HttpEntity<byte[]> download(@PathVariable Long id, HttpServletResponse response) throws
            IOException {

        S3Object s3Object = metadataService.download(id);
        String contentType = s3Object.getObjectMetadata().getContentType();
        var bytes = s3Object.getObjectContent().readAllBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(contentType));
        header.setContentLength(bytes.length);

        return new HttpEntity<>(bytes, header);
    }

    @PostMapping("/events/upload-to-s3")
    public BaseResponse<?> uploadToS3() {
        try {
            excelExport.generateExcelFile();
            byte[] excelBytes = convertListToExcel(eventRepository.findAll());
            String s3FileUrl = metadataService.uploadAndGenerateDownloadLink(excelBytes);

            return BaseResponse.ofSucceeded(s3FileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.ofFailed(DEFAULT_BAD_REQUEST);
        }
    }

    public byte[] convertListToExcel(List<Event> events) throws IOException {
        ExcelExport excelExport = new ExcelExport(events);
        excelExport.generateExcelFile();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        excelExport.workbook.write(outputStream);
        excelExport.workbook.close();
        return outputStream.toByteArray();
    }

    @GetMapping("/event/del-file")
    public BaseResponse<?> delFile(@RequestParam("filename") String fileName) {
        if (metadataService.deleteFile(fileName)) {
            return BaseResponse.ofSucceeded("Delete success file: " + fileName);
        }
        return BaseResponse.ofFailed(DEFAULT_NOT_FOUND);
    }

    @GetMapping("/event/all-file-bucket")
    public BaseResponse<?> getAllBucket() {
        return BaseResponse.ofSucceeded(metadataService.listFiles());
    }



}
