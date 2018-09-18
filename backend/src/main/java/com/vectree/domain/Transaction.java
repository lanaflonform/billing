package com.vectree.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "donor_id")
    private User donor;

    @OneToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    public Transaction(User donor, User recipient, BigDecimal amount) {
        this.id = -1L;
        this.donor = donor;
        this.recipient = recipient;
        this.amount = amount;
        this.status = TransactionStatus.CREATED;
    }

    public Transaction(User donor, User recipient, BigDecimal amount, TransactionStatus status) {
        this(donor, recipient, amount);
        this.status = status;
    }

}
