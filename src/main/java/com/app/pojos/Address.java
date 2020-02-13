package com.app.pojos;
import javax.persistence.*;


@Entity
public class Address
{
	private Integer addressId;
	private String area,city,district;
	private int pincode;
	private User user;
	public Address() {
		// TODO Auto-generated constructor stub
	}
	public Address(String area, String city, String district, int pincode) {
		super();
		
		this.area = area;
		this.city = city;
		this.district = district;
		this.pincode = pincode;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	@Column(length = 20)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(length = 20)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(length = 20)
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
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [area=" + area + ", city=" + city + ", district=" + district + ", pincode=" + pincode + "]";
	}
	
	

}
