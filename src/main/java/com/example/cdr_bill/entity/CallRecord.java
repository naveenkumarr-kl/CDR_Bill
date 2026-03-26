package com.example.cdr_bill.entity;


import com.example.cdr_bill.config.LocalDateTimeConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "call_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallRecord {
    @Id
    @CsvBindByName(column = "call_id")
    @Column(name = "call_id")
    private String callId;

    @CsvBindByName(column = "caller_number")
    @Column(name = "caller_number")
    private String callerNumber;

    @CsvBindByName(column = "receiver_number")
    @Column(name = "receiver_number")
    private String receiverNumber;

    @CsvCustomBindByName(column = "call_start_time",converter = LocalDateTimeConverter.class)
    @Column(name = "call_start_time")
    private LocalDateTime callStartTime;

    @CsvCustomBindByName(column = "call_end_time",converter = LocalDateTimeConverter.class)
    @Column(name = "call_end_time")
    private LocalDateTime callEndTime;

    @CsvBindByName(column = "call_type")
    @Column(name = "call_type")
    private String callType;

    @CsvBindByName(column = "cell_tower_id")
    @Column(name = "cell_tower_id")
    private String cellTowerId;

    @Column(name="bill_amount")
    private Double billAmount;

}
