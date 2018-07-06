package com.bank.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, 
        allowGetters = true)

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Double value;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "acc_send", nullable = false)
    private Account send;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "acc_rcv", nullable = false)
    private Account rcv;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Account getSend() {
		return send;
	}

	public void setSend(Account send) {
		this.send = send;
	}

	public Account getRcv() {
		return rcv;
	}

	public void setRcv(Account rcv) {
		this.rcv = rcv;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
