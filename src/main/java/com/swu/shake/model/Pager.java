package com.swu.shake.model;

import java.io.Serializable;

import org.hibernate.transform.ToListResultTransformer;

public class Pager implements Serializable {
	private long totalRows; // 总行数
	private int pageSize; // 每页显示的行数
	private int currentPage; // 当前页号
	private int totalPages; // 总页数
	private int startRow; // 当前页在数据库中的起始行

	public Pager() {
	}

	public Pager(int _pageSize, int _currentPage) {
		this.pageSize = _pageSize;
		this.currentPage = _currentPage;
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

	/**
	 * 第一页
	 */
	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	/**
	 * 前一页
	 */
	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	/**
	 * 下一页
	 */
	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	/**
	 * 尾页
	 */
	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}

	/**
	 * 跳转
	 * 
	 * @param _currentPage
	 */
	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage < 1) {
			first();
			return;
		}
		if (currentPage > totalPages) {
			last();
			return;
		}

	}
}
