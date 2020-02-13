package com.app.pojos;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking {
	private Integer bookingId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate,deliveryDate;
	private CylinderType cylinderType;
	private double bookingAmount;
	private Customer customer;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}
	public Booking(Date bookingDate, Date deliveryDate, CylinderType cylinderType, double bookingAmount) {
		super();
		this.bookingDate = bookingDate;
		this.deliveryDate = deliveryDate;
		this.cylinderType = cylinderType;
		this.bookingAmount = bookingAmount;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	@Temporal(TemporalType.DATE)
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Enumerated(EnumType.STRING)
	public CylinderType getCylinderType() {
		return cylinderType;
	}
	public void setCylinderType(CylinderType cylinderType) {
		this.cylinderType = cylinderType;
	}
	public double getBookingAmount() {
		return bookingAmount;
	}
	public void setBookingAmount(double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cust_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", deliveryDate=" + deliveryDate
				+ ", cylinderType=" + cylinderType + ", bookingAmount=" + bookingAmount + "]";
	}
	
	

}
