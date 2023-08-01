package com.highradius.model;

import java.util.Date;

public class POJO {
    private int customerOrderID;
    /**
	 * @param customerOrderID
	 * @param salesOrg
	 * @param distributionChannel
	 * @param customerNumber
	 * @param companyCode
	 * @param orderCurrency
	 * @param amountUSD
	 * @param orderCreationDate
	 */
	public POJO(int customerOrderID, String salesOrg, String distributionChannel, int customerNumber,
			String companyCode, String orderCurrency, double amountUSD, Date orderCreationDate) 
	{
		super();
		this.customerOrderID = customerOrderID;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.customerNumber = customerNumber;
		this.companyCode = companyCode;
		this.orderCurrency = orderCurrency;
		this.amountUSD = amountUSD;
		this.orderCreationDate = orderCreationDate;
	}
	
	private String salesOrg;
    private String distributionChannel;
    private int customerNumber;
    private String companyCode;
    private String orderCurrency;
    private double amountUSD;
    private Date orderCreationDate;
	/**
	 * @return the customerOrderID
	 */
	public int getCustomerOrderID() {
		return customerOrderID;
	}
	/**
	 * @param customerOrderID the customerOrderID to set
	 */
	public void setCustomerOrderID(int customerOrderID) {
		this.customerOrderID = customerOrderID;
	}
	/**
	 * @return the salesOrg
	 */
	public String getSalesOrg() {
		return salesOrg;
	}
	/**
	 * @param salesOrg the salesOrg to set
	 */
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	/**
	 * @return the distributionChannel
	 */
	public String getDistributionChannel() {
		return distributionChannel;
	}
	/**
	 * @param distributionChannel the distributionChannel to set
	 */
	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}
	/**
	 * @return the customerNumber
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the orderCurrency
	 */
	public String getOrderCurrency() {
		return orderCurrency;
	}
	/**
	 * @param orderCurrency the orderCurrency to set
	 */
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	/**
	 * @return the amountUSD
	 */
	public double getAmountUSD() {
		return amountUSD;
	}
	/**
	 * @param amountUSD the amountUSD to set
	 */
	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}
	/**
	 * @return the orderCreationDate
	 */
	public Date getOrderCreationDate() {
		return orderCreationDate;
	}
	/**
	 * @param orderCreationDate the orderCreationDate to set
	 */
	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

}