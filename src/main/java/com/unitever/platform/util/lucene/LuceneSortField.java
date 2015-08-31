package com.unitever.platform.util.lucene;

public class LuceneSortField {

	private String key;

	private boolean reverse;

	public LuceneSortField() {

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

}
