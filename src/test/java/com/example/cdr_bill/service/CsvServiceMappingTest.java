package com.example.cdr_bill.service;

import com.example.cdr_bill.entity.CallRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvServiceMappingTest {

    @Test
    void callDurationIsParsedFromSampleCsv() {
        String csv = "call_id,caller_number,receiver_number,call_start_time,call_duration,call_type,cell_tower_id\n" +
                "C1001,9.20E+11,9.20E+11,2026-03-18 09:15:00,120,OUTGOING,TN-CHN-001\n";

        List<CallRecord> records = new CsvToBeanBuilder<CallRecord>(new StringReader(csv))
                .withType(CallRecord.class)
                .build()
                .parse();

        assertEquals(1, records.size());
        CallRecord record = records.get(0);
        assertNotNull(record.getCallDuration());
        assertEquals(120, record.getCallDuration());
    }

    @Test
    void callDurationIsParsedFromSpreadsheetExport() {
        String csv = "call_id,caller_number,receiver_number,call_start_time,call_duration,call_type,cell_tower_id\n" +
                "C1002,9.20E+11,9.20E+11,3/18/2026 9:22,45,INCOMING,TN-CHN-004\n";

        List<CallRecord> records = new CsvToBeanBuilder<CallRecord>(new StringReader(csv))
                .withType(CallRecord.class)
                .build()
                .parse();

        assertEquals(1, records.size());
        CallRecord record = records.get(0);
        assertNotNull(record.getCallStartTime());
        assertEquals(45, record.getCallDuration());
    }
}
