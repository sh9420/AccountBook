package kr.ac.green.model;

import java.util.Arrays;

public class Table {
	private Object[][] listData;
	private Object[] listColumn;

	public Table() {
		this.listData = new Object[2][4];
		this.listColumn = new Object[5];
	}

	public Object[][] getListData() {
		return listData;
	}


	public void setListData(Object[][] listData) {
		this.listData = listData;
	}


	public Object[] getListColumn() {
		return listColumn;
	}


	public void setListColumn(Object[] listColumn) {
		this.listColumn = listColumn;
	}


	@Override
	public String toString() {
		return "ListTable [listData=" + Arrays.toString(listData) + ", listColumn=" + Arrays.toString(listColumn) + "]";
	}
	
}
