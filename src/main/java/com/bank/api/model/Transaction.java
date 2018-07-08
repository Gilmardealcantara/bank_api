package com.bank.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
/*
 * request:
{
	"value": 100.00,
	"send": {"id": 1},
	"rcv": {"id": 2}
}
 * */

@Entity
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, 
        allowGetters = true)

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double value;

    @ManyToOne
    @JoinColumn(name = "cli_send_id")
    private Client send;

    @ManyToOne
    @JoinColumn(name = "cli_rcv_id")
    private Client rcv;
    
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

	public Client getSend() {
		return send;
	}

	public void setSend(Client send) {
		this.send = send;
	}
	
	public Client getRcv() {
		return rcv;
	}

	public void setRcv(Client rcv) {
		this.rcv = rcv;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
