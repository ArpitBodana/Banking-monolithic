package com.banking.user_service.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_details")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionTypes type;
    @Column(name="transaction_date")
    private String date;
    private Long sendsTo;
    private Long accountId;

}
