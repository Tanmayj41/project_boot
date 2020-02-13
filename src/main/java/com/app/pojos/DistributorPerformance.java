package com.app.pojos;

public class DistributorPerformance implements Comparable<DistributorPerformance>
{
	private Integer distId;
	private String distName;
	private int performance;
	private String distEmail;
	public DistributorPerformance() {
		// TODO Auto-generated constructor stub
	}
	public DistributorPerformance(Integer distId, String distName, int performance, String distEmail) {
		super();
		this.distId = distId;
		this.distName = distName;
		this.performance = performance;
		this.distEmail = distEmail;
	}
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public int getPerformance() {
		return performance;
	}
	public void setPerformance(int performance) {
		this.performance = performance;
	}
	public String getDistEmail() {
		return distEmail;
	}
	public void setDistEmail(String distEmail) {
		this.distEmail = distEmail;
	}
	@Override
	public String toString() {
		return "DistributorPerformance [distId=" + distId + ", distName=" + distName + ", performance=" + performance
				+ ", distEmail=" + distEmail + "]";
	}
	@Override
	public int compareTo(DistributorPerformance dh) 
	{
		return dh.performance-this.performance;
	}
	
}
