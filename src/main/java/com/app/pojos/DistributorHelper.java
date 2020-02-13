package com.app.pojos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class DistributorHelper {

	private Integer userId;
	private String userName,userEmail,userPassword;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	private String userPhone;
	private String area,city,district;
	private int pincode;
	private int commercialStock,domesticStock;

	public DistributorHelper() {
		// TODO Auto-generated constructor stub
	}

	public DistributorHelper(String userName, String userEmail, String userPassword, Date registrationDate,
			String userPhone, String area, String city, String district, int pincode, int commercialStock,
			int domesticStock) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.registrationDate = registrationDate;
		this.userPhone = userPhone;
		this.area = area;
		this.city = city;
		this.district = district;
		this.pincode = pincode;
		this.commercialStock = commercialStock;
		this.domesticStock = domesticStock;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getCommercialStock() {
		return commercialStock;
	}

	public void setCommercialStock(int commercialStock) {
		this.commercialStock = commercialStock;
	}

	public int getDomesticStock() {
		return domesticStock;
	}

	public void setDomesticStock(int domesticStock) {
		this.domesticStock = domesticStock;
	}

	@Override
	public String toString() {
		return "DistributorHelper [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", registrationDate=" + registrationDate + ", userPhone="
				+ userPhone + ", area=" + area + ", city=" + city + ", district=" + district + ", pincode=" + pincode
				+ ", commercialStock=" + commercialStock + ", domesticStock=" + domesticStock + "]";
	}
	
	
	
	}
