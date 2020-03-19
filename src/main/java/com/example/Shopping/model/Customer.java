package com.example.Shopping.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
@Entity

@Table(name="customer")
@Validated

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	@Column(name="name")
	@Pattern(regexp="\\w+" , message="please enter valid name")
	private String name;
	
	@Size(min=4,max=15 ,message="username should be between 4 to 15 chars")
	private String username;
	
	@Column(nullable=true)
	private String contact;
	
	@Column (nullable=true)
	@Email
	private String email;
	
	@Size(min=6,max=10 ,message="password should be between 6 to 10 chars")
private String password;
	
	
	
	
	
	public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany
	@JoinTable(name="customer_product",
			joinColumns=@JoinColumn(name="customer_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
	)
	@JsonIgnore
	private List<Product> product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
}
