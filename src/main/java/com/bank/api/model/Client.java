package com.bank.api.model;

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

/*
 request:
{
	"name":"Gilmar", 
	"age": 25,
	"addr": {
		"street": "",
		"number": 0,
		"city": "",
		"state": "",
		"country": "",
		"zipcode": ""
	},
	"account": {
		"number":1, 
		"balance": 50.00
	}
}
 */
@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)

public class Client {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank	
	    @Size(max = 255)
	    private String name;

	    @NotNull
	    private Integer age;
	    
	    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	    @JoinColumn(name = "addr_id")
	    private Address addr;
	    
	    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	    @JoinColumn(name = "account_id")
	    private Account account;
	    
	    @OneToMany(mappedBy = "send", targetEntity = Transaction.class, 
	    	    cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Transaction> trans_send;

	    @OneToMany(mappedBy = "rcv", targetEntity = Transaction.class, 
	    	    cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Transaction> trans_recv;
	    
	    
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
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
		
		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
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
