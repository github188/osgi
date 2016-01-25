package com.scsvision.gather.middleware.data;

import java.util.Hashtable;
import java.util.Map;

import com.scsvision.gather.middleware.data.model.VehicleDetector;

/**
 * 采集缓存数据
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午3:56:46
 */
public class CacheData {
	private static CacheData instance = new CacheData();
	public Map<String, VehicleDetector> vdData = new Hashtable<String, VehicleDetector>();

	private CacheData() {

	}

	public static CacheData getInstance() {
		return instance;
	}
}
