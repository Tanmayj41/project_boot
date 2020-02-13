package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
public class Customer 
{
	private Integer custId;
	private String custEmail,custAadhar;
	private byte[] aadharImage;
	private CylinderType cylinderType;
	private int pin;
	private Distributor distributor;
	private List<Booking> bookingList=new ArrayList<>();
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer(String custEmail, String custAadhar,int pin, byte[] aadharImage,
			CylinderType cylinderType) {
		super();
		this.custEmail = custEmail;
		this.custAadhar = custAadhar;
		this.pin = pin;
		this.aadharImage = aadharImage;
		this.cylinderType = cylinderType;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	@Column(length = 40,unique = true,nullable = false)
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	@Column(length = 20)
	public String getCustAadhar() {
		return custAadhar;
	}
	public void setCustAadhar(String custAadhar) {
		this.custAadhar = custAadhar;
	}
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	public byte[] getAadharImage() {
		return aadharImage;
	}
	public void setAadharImage(byte[] aadharImage) {
		this.aadharImage = aadharImage;
	}

	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	@Enumerated(EnumType.STRING)
	public CylinderType getCylinderType() {
		return cylinderType;
	}
	public void setCylinderType(CylinderType cylinderType) {
		this.cylinderType = cylinderType;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	public List<Booking> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}
	public void addBooking(Booking b)
	{
		this.bookingList.add(b);
		b.setCustomer(this);
	}
	public void removeBooking(Booking b)
	{
		this.bookingList.remove(b);
		b.setCustomer(null);
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dist_id")
	public Distributor getDistributor() {
		return distributor;
	}
	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custEmail=" + custEmail + ",custPin=" + pin + ", custAadhar=" + custAadhar + ", aadharImage=" + aadharImage + ", cylinderType="
				+ cylinderType + "]";
	}
	

}
