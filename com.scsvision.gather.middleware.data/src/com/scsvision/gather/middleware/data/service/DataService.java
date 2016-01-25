package com.scsvision.gather.middleware.data.service;

import java.util.Collection;

import com.scsvision.gather.middleware.data.model.VehicleDetector;

/**
 * DataService
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午4:07:48
 */
public interface DataService {
	public void writeVd(VehicleDetector vd);

	public VehicleDetector readVd(String sn);

	public Collection<VehicleDetector> listVd();
}
