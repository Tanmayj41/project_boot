package com.app.pojos;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User
{
	private Integer userId;
	private String userName,userEmail,userPassword;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	private String userPhone;
	private UserType userType;
	private Address address;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String userEmail, String password)
	{
		this.userEmail = userEmail;
		this.userPassword = password;
	}
	public User(String userName,  String userEmail, String userPassword, Date registrationDate,
			String userPhone, UserType userType) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.registrationDate = registrationDate;
		this.userPhone = userPhone;
		this.userType = userType;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(length = 20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length = 40 , unique = true )
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Column(length = 20)
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Column(length = 20)
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@JsonIgnore
	@OneToOne(mappedBy = "user" , orphanRemoval = true,cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void addAddress(Address a)
	{
		this.address=a;
		a.setUser(this);
	}
	public void removeAddress(Address a)
	{
		this.address=null;
		a.setUser(null);
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userPassword=" + userPassword + ", registrationDate=" + registrationDate
				+ ", userPhone=" + userPhone + ", userType=" + userType + "]";
	}
	
	
	

}
