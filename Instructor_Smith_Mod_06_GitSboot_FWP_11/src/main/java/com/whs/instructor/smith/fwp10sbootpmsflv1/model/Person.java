package com.whs.instructor.smith.fwp10sbootpmsflv1.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table( name ="Person" )
public class Person {
	
	@Id
	@Column( name="id", nullable=false, unique=true) 
	private Long id;
	@Column( name = "fname" )
	private String fname;
	@Column( name = "lname" )
	private String lname;
	@Column( name = "street" )
	private String street;
	@Column( name = "city" )
	private String city;
	@Column( name = "state" )
	private String state;
	@Column( name = "zip") 
	@BatchSize(size = 5)
	private String zip;
	@Column( name = "phone" )
	private String phone;   
	
	public Person(Long id,String fname, String lname, 
			String street, String city, String state, String zip,String phone) {
		super();
		this.id=id;
		this.fname = fname;
		this.lname = lname;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	public Person() {
		// TODO Auto-generated constructor stub
	}
 
	
	public Person(Person object) {

		this.id=0L;
		this.fname = "n/a";
		this.lname = "n/a";
		this.street = "n/a";
		this.city = "n/a";
		this.state = "n/a";
		this.zip = "n/a";
		this.phone  = "n/a";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	} 
	@Override
	public String toString() {
		return "Person [id=" + id + ", fname=" + fname + ", lname=" + lname + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phone=" + phone +   "]";
	} 
}
