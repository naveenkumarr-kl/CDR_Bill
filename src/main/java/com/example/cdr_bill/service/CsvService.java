package com.example.cdr_bill.service;


import com.example.cdr_bill.entity.CallRecord;
import com.example.cdr_bill.repository.CdrRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class CsvService {

    private final CdrRepository cdrRepository;
    Logger logger = LoggerFactory.getLogger(CsvService.class);

    public CsvService(CdrRepository cdrRepository) {
        this.cdrRepository = cdrRepository;
    }

    @Transactional
    public String calculateBill(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("CSV file is empty.");
        }

        try (InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {

            CsvToBean<CallRecord> csvToBean = new CsvToBeanBuilder<CallRecord>(reader)
                    .withType(CallRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withThrowExceptions(false)
                    .build();

            List<CallRecord> records = csvToBean.parse();

            log.info(records.toString());
            cdrRepository.saveAll(records);
            if (records.isEmpty()) {
                return "No records found in CSV.";
            }

            Double billAmount= records.stream()
                    .filter(record->"OUTGOING".equalsIgnoreCase(record.getCallType()))
                    .mapToDouble(record-> record.getCallDuration() * 0.05)
                    .sum();
            return "Saved " + records.size()+ " records. Bill Amount : "+billAmount ;


        } catch (Exception ex) {
            throw new IllegalStateException("Failed to process CSV file.", ex);
        }
    }
}
