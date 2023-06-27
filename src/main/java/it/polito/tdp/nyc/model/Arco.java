package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class Arco {
	
	private String c1;
	private int lat1;
	private int lng1;
	private String c2;
	private int lat2;
	private int lng2;
	private LatLng pos1;
	private LatLng pos2;
	
	@Override
	public String toString() {
		return "c1= " + c1 + ", c2= " + c2 + ", pos1= " + pos1 + ", pos2= " + pos2;
	}
	public Arco(String c1, int lat1, int lng1, String c2, int lat2, int lng2) {
		super();
		this.c1 = c1;
		this.lat1 = lat1;
		this.lng1 = lng1;
		this.c2 = c2;
		this.lat2 = lat2;
		this.lng2 = lng2;
		this.pos1 = new LatLng(lat1, lng1);
		this.pos2 = new LatLng(lat2, lng2);
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public int getLat1() {
		return lat1;
	}
	public void setLat1(int lat1) {
		this.lat1 = lat1;
	}
	public int getLng1() {
		return lng1;
	}
	public void setLng1(int lng1) {
		this.lng1 = lng1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public int getLat2() {
		return lat2;
	}
	public void setLat2(int lat2) {
		this.lat2 = lat2;
	}
	public int getLng2() {
		return lng2;
	}
	public void setLng2(int lng2) {
		this.lng2 = lng2;
	}
	public LatLng getPos1() {
		return pos1;
	}
	public void setPos1(LatLng pos1) {
		this.pos1 = pos1;
	}
	public LatLng getPos2() {
		return pos2;
	}
	public void setPos2(LatLng pos2) {
		this.pos2 = pos2;
	}
	
	

}
