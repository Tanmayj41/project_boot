package com.app.pojos;

public class CustomerHelperForDistributor {
	private String name;
	private String email;
	private CylinderType type;
	private int ordersNumber;

	public CustomerHelperForDistributor() {
// TODO Auto-generated constructor stub
	}

	public CustomerHelperForDistributor(String name, String email, CylinderType type, int ordersNumber) {
		super();
		this.name = name;
		this.email = email;
		this.type = type;
		this.ordersNumber = ordersNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CylinderType getType() {
		return type;
	}

	public void setType(CylinderType type) {
		this.type = type;
	}

	public int getOrdersNumber() {
		return ordersNumber;
	}

	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}

	@Override
	public String toString() {
		return "CustomerHelperForDistributor [name=" + name + ", email=" + email + ", type=" + type + ", ordersNumber="
				+ ordersNumber + "]";
	}

}
