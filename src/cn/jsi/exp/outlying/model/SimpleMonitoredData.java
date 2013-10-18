package cn.jsi.exp.outlying.model;

import java.util.Date;

/**
 * This is the basic monitored data with four dimension
 * 
 * This bean is used for two times, 
 * 1. A List of this bean forms the historical data.
 * 2. This type of data would also continously arrive 
 * 	  to the system when performing real time monitoring. 
 * 
 * @author Robin
 *
 */
public class SimpleMonitoredData extends BaseMonitoredData{
	private int d1;
	private double d2;
	private float d3;
	private String d4;
	private Date arriveTime;
	public int getD1() {
		return d1;
	}
	public void setD1(int d1) {
		this.d1 = d1;
	}
	public double getD2() {
		return d2;
	}
	public void setD2(double d2) {
		this.d2 = d2;
	}
	public float getD3() {
		return d3;
	}
	public void setD3(float d3) {
		this.d3 = d3;
	}
	public String getD4() {
		return d4;
	}
	public void setD4(String d4) {
		this.d4 = d4;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	

}
