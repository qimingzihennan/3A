package com.unitrust.timestamp3A.Jedits;

import java.io.Serializable;

public class GoodExt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3773958929504895078L;

	public GoodExt(Integer id) {
		super();
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
