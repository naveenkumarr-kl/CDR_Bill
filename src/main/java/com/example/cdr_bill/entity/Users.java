package com.example.cdr_bill.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Integer planId; // 1 for Basic, 2 for Premium
}
