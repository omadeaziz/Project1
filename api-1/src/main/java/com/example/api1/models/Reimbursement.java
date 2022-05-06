package com.example.api1.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String reimbType;
    private double amount;
    private String status;

}
