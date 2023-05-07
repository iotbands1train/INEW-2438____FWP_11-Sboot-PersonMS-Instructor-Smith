package com.whs.instructor.smith.fwp10sbootpmsflv1.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="Admin")
public class Admin {

	@Id 
	@Column( name="id", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	@Column( name = "user" )
	private String user;
	@Column( name = "pass" )
	private String pass;
	public Admin(Long id, String user, String pass) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, pass, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(id, other.id) && Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", user=" + user + ", pass=" + pass + "]";
	}
	
	
	

}
