package com.scsvision.gather.middleware.data.service.impl;

import java.util.Collection;

import com.scsvision.gather.middleware.data.CacheData;
import com.scsvision.gather.middleware.data.model.VehicleDetector;
import com.scsvision.gather.middleware.data.service.DataService;

/**
 * DataServiceImpl
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午4:08:26
 */
public class DataServiceImpl implements DataService {

	public void writeVd(VehicleDetector vd) {
		CacheData.getInstance().vdData.put(vd.getSn(), vd);
	}

	public VehicleDetector readVd(String sn) {
		return CacheData.getInstance().vdData.get(sn);
	}

	public Collection<VehicleDetector> listVd() {
		return CacheData.getInstance().vdData.values();
	}
}
