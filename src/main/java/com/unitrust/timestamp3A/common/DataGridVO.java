package com.unitrust.timestamp3A.common;

/**   
 * 
 * @Title: DataGridVO.java 
 * @Prject: payment-console
 * @Package: com.fy.payment.console.vo 
 * @Description: TODO
 * @version: V1.0   
 */


import java.util.List;

/** 
 * @ClassName: DataGridVO 
 * @Description: TODO
 */
public class DataGridVO<E extends Object> {
	private int total;
	
	private List<E> rows;

	/** 
	 * @Title:DataGridVO
	 * @Description:TODO 
	 */
	public DataGridVO() {
	}
	
	/** 
	 * @Title:DataGridVO
	 * @Description:TODO 
	 * @param total
	 * @param rows 
	 */
	public DataGridVO(int total, List<E> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<E> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	
	
	
}





