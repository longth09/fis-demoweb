package com.demo.event.infrastructure.util;

import com.demo.event.api.dto.EventResDto;
import com.demo.event.domain.model.Event;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Configuration
public class ExcelExport {

    List<Event> events;

    public XSSFWorkbook workbook;

    private XSSFSheet sheet;

    public ExcelExport(List<Event> events) {
        this.events = events;
        workbook = new XSSFWorkbook();
    }

    Integer i = 0;

    private void writeHeader() {
        sheet = workbook.createSheet("Event " + ++i);
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "NAME", style);
        createCell(row, 2, "DESCRIPTION", style);
        createCell(row, 3, "CREATE DATE", style);
        createCell(row, 4, "UPDATE DATE", style);
    }

    private void createCell(Row row, Integer coloumCount, Object valueCell, CellStyle style) {
        sheet.autoSizeColumn(coloumCount);
        Cell cell = row.createCell(coloumCount);
        if (valueCell instanceof Integer) {
            cell.setCellValue((Integer) valueCell);
        } else if (valueCell instanceof Long) {
            cell.setCellValue((Long) valueCell);
        } else if (valueCell instanceof String) {
            cell.setCellValue((String) valueCell);
        } else if (valueCell instanceof Instant) {
            cell.setCellValue(Date.from((Instant) valueCell));
            style.setDataFormat((short) 22);
        } else {
            cell.setCellValue((Boolean) valueCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        CellStyle style1 = workbook.createCellStyle();
        style1.setDataFormat((short) 22);
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setAlignment(HorizontalAlignment.CENTER);
        for (Event record : events) {
            Row row = sheet.createRow(rowCount++);
            int coloumCount = 0;
            createCell(row, coloumCount++, record.getId(), style);
            createCell(row, coloumCount++, record.getName(), style);
            createCell(row, coloumCount++, record.getDesc(), style);
            createCell(row, coloumCount++, record.getCreatedDate(), style1);
            createCell(row, coloumCount++, record.getUpdatedDate(), style1);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void generateExcelFile() throws IOException {
        writeHeader();
        write();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        byte[] excelBytes = outputStream.toByteArray();
    }

}
