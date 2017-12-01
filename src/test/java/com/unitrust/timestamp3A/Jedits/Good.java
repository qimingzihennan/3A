package com.unitrust.timestamp3A.Jedits;

import java.io.Serializable;

public class Good implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4381359819206128439L;

	private String name;

	private String dev;

	private Integer num;

	private GoodExt goodExt;

	public Good(String name, String dev, Integer num, GoodExt goodExt) {
		super();
		this.name = name;
		this.dev = dev;
		this.num = num;
		this.goodExt = goodExt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public GoodExt getGoodExt() {
		return goodExt;
	}

	public void setGoodExt(GoodExt goodExt) {
		this.goodExt = goodExt;
	}

}
