package com.unitever.platform.util.lucene;

public class LuceneKeyWord {

	private String key;

	private String value;

	private boolean isAnd = false;

	public LuceneKeyWord() {

	}

	public LuceneKeyWord(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public LuceneKeyWord(String key, String value, boolean isAnd) {
		super();
		this.key = key;
		this.value = value;
		this.isAnd = isAnd;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isAnd() {
		return isAnd;
	}

	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}

}
