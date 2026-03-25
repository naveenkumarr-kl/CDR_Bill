package com.example.cdr_bill.service;


import com.example.cdr_bill.entity.CallRecord;
import com.example.cdr_bill.repository.CdrRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class CsvService {

    private final CdrRepository cdrRepository;

    public CsvService(CdrRepository cdrRepository) {
        this.cdrRepository = cdrRepository;
    }

    @Transactional
    public String calculateBill(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("CSV file is empty.");
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            CsvToBean<CallRecord> csvToBean = new CsvToBeanBuilder<CallRecord>(reader)
                    .withType(CallRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withSeparator(',')
                    .build();

            List<CallRecord> records = csvToBean.parse();
            if (records.isEmpty()) {
                return "No records found in CSV.";
            }



            cdrRepository.saveAll(records);
            return "Saved " + records.size() + " records.";


        } catch (Exception ex) {
            throw new IllegalStateException("Failed to process CSV file.", ex);
        }
    }
}
