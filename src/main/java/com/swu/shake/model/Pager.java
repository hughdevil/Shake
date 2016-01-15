package com.swu.shake.model;

import java.io.Serializable;

import org.hibernate.transform.ToListResultTransformer;

public class Pager implements Serializable {
	private long totalRows; // 总行数
	private int pageSize; // 每页显示的行数
	private int currentPage; // 当前页号

	private int startRow; // 当前页在数据库中的起始行

	private int totalPages; // 总页数
	private int nextPage; // 下一页
	private int prePage; // 上一页

	public Pager() {
	}

	/**
	 * 
	 * @param _totalRows
	 * @param _pageSize
	 * @param _currentPage
	 */
	public Pager(long _totalRows, int _pageSize, int _currentPage) {
		this.totalRows = _totalRows;
		this.pageSize = _pageSize;
		this.currentPage = _currentPage;

		this.totalPages = (int) (totalRows / pageSize);
		this.totalPages += (totalRows % pageSize) > 0 ? 1 : 0;
		this.startRow = (_currentPage - 1) * _pageSize;
		this.prePage = (currentPage > 1 ? currentPage - 1 : currentPage);
		this.nextPage = (currentPage < totalPages ? currentPage + 1
				: currentPage);
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

}
