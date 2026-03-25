package com.example.cdr_bill.repository;

import com.example.cdr_bill.entity.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdrRepository extends JpaRepository<CallRecord, String> {
}
