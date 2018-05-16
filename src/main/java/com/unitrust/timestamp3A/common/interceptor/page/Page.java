package com.unitrust.timestamp3A.common.interceptor.page;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Page<T> implements Map {

	private int pageNum = 1;
	private int pageSize = 10;
	private int totalRecords;
	private int totalPage;// 总的页码
	private Map searchCondition;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		int totalPage = totalRecords / pageSize;
		if (totalRecords % pageSize > 0) {
			totalPage = totalPage + 1;
		}
		this.setTotalPage(totalPage);
		this.totalRecords = totalRecords;
	}

	public Map getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(Map searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public Object get(Object key) {
		if ("pageNum".equals(key)) {
			return this.pageNum;
		}
		if ("pageSize".equals(key)) {
			return this.pageSize;
		}
		if ("totalRecords".equals(key)) {
			return this.totalRecords;
		}
		if ("searchCondition".equals(key)) {
			return this.searchCondition;
		}
		if ("totalPage".equals(key)) {
			return this.totalPage;
		}
		return searchCondition.get(key);
	}

	@Override
	public int size() {
		return searchCondition.size();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public Object put(Object key, Object value) {
		return null;
	}

	@Override
	public Object remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set keySet() {
		return null;
	}

	@Override
	public Collection values() {
		return null;
	}

	@Override
	public Set entrySet() {
		return searchCondition.entrySet();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
