package com.bmdb.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	@Column(name="IsAdmin")
	private boolean admin;
	
	public User() {
		userName = "";
		password = "";
		
	}
	
	public User(int id, String userName, String password, String firstName, String lastName, String phoneNumberNumber,
			String email, boolean reviewer, boolean admin) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumberNumber;
		this.admin = admin;
	}

	public User(String un, String pw, String fn, String ln, String pn, String e, boolean m, boolean a) {
		setUserName(un);
		setPassword(pw);
		setFirstName(fn);
		setLastName(ln);
		setPhoneNumber(pn);
		setAdmin(a);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumberNumber) {
		this.phoneNumber = phoneNumberNumber;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean inAdmin) {
		this.admin = inAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", admin=" + admin+"]";
	}
	
}