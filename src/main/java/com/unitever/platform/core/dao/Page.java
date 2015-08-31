package com.unitever.platform.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class Page<T> {

	private int pageNo = 1;//页码，默认是第一页
	private int pageSize = 10;//每页显示的记录数，默认是10
	private int totalRecord;//总记录数
	private int totalPage;//总页数
	private List<T> results;//对应的当前页记录
	private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象

	public static final int DEFAUT_PAGESIZE = 10;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = DEFAUT_PAGESIZE;
		}
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
		setTotalPage(totalPage);
		if(totalPage==0){
			setPageNo(0);
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}
	
	public List<T> getList() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 得到当前分页第一条记录
	 */
	public int getStartOfPage() {
		if (this.getPageNo() < 1)
			return 0;
		return (this.getPageNo() - 1) * this.getPageSize();
	}

	/** 是否有数据 */
	public boolean isHaveData() {
		// TODO Auto-generated method stub
		if (this.getResults() == null) {
			return false;
		}
		if (this.getResults().size() < 1) {
			return false;
		}
		return true;
	}

	/** 是否有下一页 */
	public boolean isHaveNextPage() {
		return this.getPageNo() < this.getTotalPage();
	}

	/** 是否有上一页 */
	public boolean isHavePrePage() {
		return this.getPageNo() > 1;
	}

	/** 是否是首页 */
	public boolean isFirstPage() {
		return this.getPageNo() <= 1;
	}

	/** 是否是尾页 */
	public boolean isLastPage() {
		return this.getPageNo() >= getTotalPage();
	}

	/**
	 * 得到分面信息
	 */
	public String getInfo() {
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("pageSize", this.getPageSize());
		map.put("totalRecords", this.getTotalRecord());
		map.put("totalPages", this.getTotalPage());
		map.put("currentPage", this.getPageNo());
		map.put("isHaveData", this.isHaveData());
		map.put("isFirstPage", this.isFirstPage());
		map.put("isLastPage", this.isLastPage());
		map.put("isHaveNextPage", isHaveNextPage());
		map.put("isHavePrePage", isHavePrePage());
		return JSONObject.toJSONString(map);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=").append(results).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord).append("]");
		return builder.toString();
	}

}