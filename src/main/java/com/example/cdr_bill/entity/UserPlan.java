package com.example.cdr_bill.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_plans")
public class UserPlan {

    @Id
    private Integer id; // 1 for Basic, 2 for Premium
    @NotBlank
    private String planName;
    private Double freeMinutes;
    private Double ratePerMinute;
}
