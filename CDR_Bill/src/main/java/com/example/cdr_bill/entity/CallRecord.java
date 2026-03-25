package com.example.cdr_bill.entity;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
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

    @CsvBindByName(column = "call_start_time")
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    @Column(name = "call_start_time")
    private LocalDateTime callStartTime;

    @CsvBindByName(column = "call_duration")
    @Column(name = "call_duration")
    private Integer callDuration;

    @CsvBindByName(column = "call_type")
    @Column(name = "call_type")
    private String callType;

    @CsvBindByName(column = "cell_tower_id")
    @Column(name = "cell_tower_id")
    private String cellTowerId;

    @Column(name = "call_cost")
    private Double callCost;
}
