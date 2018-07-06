package com.bank.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)

public class Client {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "cli")
	    private Account account;
	   
	    @NotBlank
	    @Size(max = 255)
	    private Long name;

	    @NotNull
	    private Integer age;
	    
	    @OneToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "addr_id", nullable = false)
	    private Address addr;
	    
	    @OneToMany(mappedBy = "send", targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private List<Transaction> trans_send;

	    @OneToMany(mappedBy = "rcv", targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private List<Transaction> trans_rcv;
	    
	    @Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date createdAt;

	    @Column(nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @LastModifiedDate
	    private Date updatedAt;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getName() {
			return name;
		}

		public void setName(Long name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Address getAddr() {
			return addr;
		}

		public void setAddr(Address addr) {
			this.addr = addr;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
}
