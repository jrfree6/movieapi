package com.movie.api.result;

import java.util.List;

public class RootElement{
    public List<Min> getMin() {
		return min;
	}
	public void setMin(List<Min> min) {
		this.min = min;
	}
	public List<Max> getMax() {
		return max;
	}
	public void setMax(List<Max> max) {
		this.max = max;
	}
	private List<Min> min;
    private List<Max> max;
}
