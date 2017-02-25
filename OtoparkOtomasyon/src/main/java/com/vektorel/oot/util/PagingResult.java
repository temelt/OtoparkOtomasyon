package com.vektorel.oot.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class PagingResult {
	private List list;
	private Long rowCount;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Long getRowCount() {
		return rowCount;
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

}
