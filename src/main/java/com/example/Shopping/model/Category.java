package com.example.Shopping.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
@javax.persistence.Transient
	@JsonInclude
	private int count=0; 
	

	
	
	
	private String name;
	
		public long getId() {
		return id;
	}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setId(long id) {
			this.id = id;
		}
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

}

