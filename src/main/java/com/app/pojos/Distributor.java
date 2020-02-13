
package com.app.pojos;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Distributor
{
	private Integer distId;
	private String distName;
	private int pin;
	private String distEmail;
	private int commercialStock,domesticStock;
	private List<Customer> customerList=new ArrayList<>();
	
	public Distributor() {
		// TODO Auto-generated constructor stub
	}

	public Distributor(String distName,int pin, int commercialStock, int domesticStock,String distEmail) {
		super();
		this.distName = distName;
		this.pin = pin;
		this.commercialStock = commercialStock;
		this.distEmail = distEmail;
		this.domesticStock = domesticStock;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDistId() {
		return distId;
	}

	public void setDistId(Integer distId) {
		this.distId = distId;
	}

	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Column(length = 20)
	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public int getCommercialStock() {
		return commercialStock;
	}

	public void setCommercialStock(int commercialStock) {
		this.commercialStock = commercialStock;
	}

	public String getDistEmail() {
		return distEmail;
	}

	public void setDistEmail(String distEmail) {
		this.distEmail = distEmail;
	}

	public int getDomesticStock() {
		return domesticStock;
	}

	public void setDomesticStock(int domesticStock) {
		this.domesticStock = domesticStock;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "distributor",orphanRemoval = true,cascade = CascadeType.ALL)
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public void addCustomer(Customer c)
	{
		this.customerList.add(c);
		c.setDistributor(this);
	}
	public void removeCustomer(Customer c)
	{
		this.customerList.remove(c);
		c.setDistributor(null);
	}
	@Override
	public String toString() {
		return "Distributor [distId=" + distId + ", distName=" + distName + ",distEmail=" + distEmail + ", commercialStock=" + commercialStock
				+ ", domesticStock=" + domesticStock + "]";
	}
	

}
