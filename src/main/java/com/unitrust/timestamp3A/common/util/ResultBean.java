
package com.unitrust.timestamp3A.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
  * @ClassName: ResultBean
  *
  * @Description:
  *
  * @author tsa.HanY
  * @date 2016年7月27日 下午2:33:07
  */
@SuppressWarnings("rawtypes")
public class ResultBean implements Serializable {
	/**
	  * @Fields serialVersionUID : 
	  */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private Map data;
	public ResultBean(){
		this.code="0";
		this.data = new HashMap();
	}

	public ResultBean(String msg){
		this.msg = msg;
	}
	public ResultBean(String code, String msg){
		this.code= code;
		this.msg = msg;
	}
	/**
	 * getter method
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * setter method
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * getter method
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * setter method
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * getter method
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * setter method
	 * @param data the data to set
	 */
	public void setData(Map map) {
		this.data = map;
	}

	/**
	 * 追加结果 method
	 * @param data the data to set
	 */
	@SuppressWarnings("unchecked")
	public void putAllData(Map map) {
		this.data.putAll(map);
	}

	/**
	 * setter method
	 * @param data the data to set
	 */
	@SuppressWarnings("unchecked")
	public void putData(String key, Object value) {
		this.data.put(key, value);
	}
	
}
