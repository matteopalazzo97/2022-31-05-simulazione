package it.polito.tdp.nyc.model;

import java.util.Objects;

public class VerticeDist implements Comparable<VerticeDist>{
	
	private String c1;
	private double dist;
	
	public VerticeDist(String c1, double dist) {
		super();
		this.c1 = c1;
		this.dist = dist;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(c1, dist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerticeDist other = (VerticeDist) obj;
		return Objects.equals(c1, other.c1) && Double.doubleToLongBits(dist) == Double.doubleToLongBits(other.dist);
	}

	@Override
	public int compareTo(VerticeDist o) {
		return (int)(this.dist-o.dist);
	}

	@Override
	public String toString() {
		return c1 + "   DIST: " + dist;
	}
	
	

}
