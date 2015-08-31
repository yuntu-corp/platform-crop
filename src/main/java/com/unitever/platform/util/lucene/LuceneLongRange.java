package com.unitever.platform.util.lucene;

public class LuceneLongRange {

	private String key;
	private long min;
	private long max;
	private boolean minInclusive = true;
	private boolean maxInclusive = true;

	public LuceneLongRange() {

	}

	public LuceneLongRange(String key, long min, long max) {
		this.key = key;
		this.min = min;
		this.max = max;
	}

	public LuceneLongRange(String key, long min, long max, boolean minInclusive, boolean maxInclusive) {
		super();
		this.key = key;
		this.min = min;
		this.max = max;
		this.minInclusive = minInclusive;
		this.maxInclusive = maxInclusive;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public boolean isMinInclusive() {
		return minInclusive;
	}

	public void setMinInclusive(boolean minInclusive) {
		this.minInclusive = minInclusive;
	}

	public boolean isMaxInclusive() {
		return maxInclusive;
	}

	public void setMaxInclusive(boolean maxInclusive) {
		this.maxInclusive = maxInclusive;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
