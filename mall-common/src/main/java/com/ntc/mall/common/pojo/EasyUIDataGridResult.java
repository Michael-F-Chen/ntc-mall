package com.ntc.mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * DataGrid 展示数据的POJO
 * @author Michael-Chen
 */
public class EasyUIDataGridResult implements Serializable {

	private static final long serialVersionUID = 5469132460433407539L;

	private long total;
	
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
