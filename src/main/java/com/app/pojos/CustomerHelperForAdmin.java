package com.app.pojos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerHelperForAdmin {

	private Integer userId;
	private String userName, userEmail;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	private String area;
	private int pincode;
	private CylinderType cylinderType;
	private String distName;

	public CustomerHelperForAdmin() {
		// TODO Auto-generated constructor stub
	}

	public CustomerHelperForAdmin(Integer userId,String userName, String userEmail, Date registrationDate,
								String area, int pincode,CylinderType cylinderType,String distName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.registrationDate = registrationDate;
		this.area = area;
		this.pincode = pincode;
		this.cylinderType = cylinderType;
		this.distName = distName;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public CylinderType getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(CylinderType cylinderType) {
		this.cylinderType = cylinderType;
	}

	
	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	@Override
	public String toString() {
		return "CustomerHelperForAdmin [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", registrationDate=" + registrationDate + ", area=" + area + ", pincode=" + pincode
				+ ", cylinderType=" + cylinderType + ", distName=" + distName + "]";
	}

	
}
