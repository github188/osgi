package com.scsvision.gather.middleware.sqlserver.dao.model;

import java.io.Serializable;

/**
 * VehicleDetector
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午5:29:40
 */
public class VehicleDetector implements Serializable {
	private static final long serialVersionUID = 6466029877396887682L;
	private String sn;
	private int flux;
	private float speedAvg;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getFlux() {
		return flux;
	}

	public void setFlux(int flux) {
		this.flux = flux;
	}

	public float getSpeedAvg() {
		return speedAvg;
	}

	public void setSpeedAvg(float speedAvg) {
		this.speedAvg = speedAvg;
	}

}
