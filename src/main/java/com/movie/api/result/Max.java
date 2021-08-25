package com.movie.api.result;
public class Max{
	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;
	
	public Max(String Producer, int Interval, int PreviousWin, int FollowingWin) {
	        this.producer = Producer;
	        this.interval = Interval;
	        this.previousWin = PreviousWin;
	        this.followingWin = FollowingWin;

	}
    
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}
	public int getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}
}

