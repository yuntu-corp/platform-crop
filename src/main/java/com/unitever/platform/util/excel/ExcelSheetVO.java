package com.unitever.platform.util.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelSheetVO {

	private String name;

	private List<List<String>> datas = new ArrayList<List<String>>();

	public ExcelSheetVO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<List<String>> getDatas() {
		return datas;
	}

	public void setDatas(List<List<String>> datas) {
		this.datas = datas;
	}

	public void addData(List<String> data) {
		datas.add(data);
	}

	/**
	 * 增加空的数据，为了把excel格式设置为文本类型。
	 * 
	 * @param rows
	 * @param columns
	 */
	public void addEmptyData(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
			List<String> data = new ArrayList<String>();
			for (int j = 0; j < columns; j++) {
				data.add("");
			}
			addData(data);
		}
	}
}
