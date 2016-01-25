package com.scsvision.gather.middleware.sqlserver.dao.impl;

import org.apache.commons.lang.math.RandomUtils;

import com.scsvision.gather.middleware.sqlserver.dao.VehicleDetectorDAO;
import com.scsvision.gather.middleware.sqlserver.dao.model.VehicleDetector;

/**
 * VehicleDetectorDAOImpl
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午4:47:48
 */
public class VehicleDetectorDAOImpl implements VehicleDetectorDAO {
	@Override
	public VehicleDetector[] extract() {
		VehicleDetector[] vds = new VehicleDetector[10];
		for (int i = 0; i < 10; i++) {
			VehicleDetector vd = new VehicleDetector();
			vd.setSn("231000000111101000" + i);
			vd.setFlux(RandomUtils.nextInt(1000));
			vd.setSpeedAvg(RandomUtils.nextFloat() * 100);
			vds[i] = vd;
		}
		return vds;
	}
}
