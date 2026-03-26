package com.example.cdr_bill.controller;


import com.example.cdr_bill.service.CsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cdr/v1")
public class CdrController {

    CsvService csvService;

    public CdrController(CsvService csvService)
    {
        this.csvService=csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            String result = csvService.calculateBill(file);
            return ResponseEntity.ok(result);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
