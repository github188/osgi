package com.scsvision.gather.middleware.task;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.scsvision.gather.middleware.data.service.DataService;
import com.scsvision.gather.middleware.sqlserver.dao.VehicleDetectorDAO;
import com.scsvision.gather.middleware.sqlserver.dao.model.VehicleDetector;

/**
 * ExtractTask
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-10 下午5:40:30
 */
public class ExtractTask extends TimerTask implements CommandProvider,
		IExtractTask {

	private VehicleDetectorDAO vdDAO;
	private DataService dataService;
	private ScheduledExecutorService executor;

	@Override
	public void run() {
		try {
			if (null != vdDAO && null != dataService) {
				VehicleDetector[] vds = vdDAO.extract();
				for (VehicleDetector vd : vds) {
					com.scsvision.gather.middleware.data.model.VehicleDetector vehicleDetector = new com.scsvision.gather.middleware.data.model.VehicleDetector();
					vehicleDetector.setSn(vd.getSn());
					vehicleDetector.setFlux(vd.getFlux());
					vehicleDetector.setSpeedAvg(vd.getSpeedAvg());
					dataService.writeVd(vehicleDetector);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void setVdDAO(VehicleDetectorDAO vdDAO) {
		this.vdDAO = vdDAO;
	}

	public synchronized void unsetVdDAO(VehicleDetectorDAO vdDAO) {
		this.vdDAO = null;
	}

	public synchronized void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public synchronized void unsetDataService(DataService dataService) {
		this.dataService = null;
	}

	@Override
	public String getHelp() {
		StringBuffer sb = new StringBuffer();
		sb.append("write\n  scope: ExtrackTask\n");
		sb.append("gstart interval\n  scope: ExtrackTask\n");
		sb.append("gstop\n  scope: ExtrackTask\n");
		return sb.toString();
	}

	public void _write(CommandInterpreter ci) {
		VehicleDetector[] vds = vdDAO.extract();
		for (VehicleDetector vd : vds) {
			com.scsvision.gather.middleware.data.model.VehicleDetector vehicleDetector = new com.scsvision.gather.middleware.data.model.VehicleDetector();
			vehicleDetector.setSn(vd.getSn());
			vehicleDetector.setFlux(vd.getFlux());
			vehicleDetector.setSpeedAvg(vd.getSpeedAvg());
			dataService.writeVd(vehicleDetector);
		}
	}

	public void _gstop(CommandInterpreter ci) {
		if (this.executor != null) {
			this.executor.shutdown();
			this.executor = null;
		}
	}

	public void _gstart(CommandInterpreter ci) {
		if (this.executor != null) {
			return;
		}
		String arg = ci.nextArgument();
		int interval = 60000;
		if (StringUtils.isNotBlank(arg)) {
			try {
				interval = Integer.valueOf(arg);
			} catch (NumberFormatException e) {
				System.out.println("Parameter " + arg
						+ " is not a numberic value !");
				return;
			}
		}
		this.executor = Executors.newScheduledThreadPool(4);
		this.executor.scheduleAtFixedRate(this, 0, interval, TimeUnit.SECONDS);
	}

	public void deactive() {
		if (this.executor != null) {
			this.executor.shutdown();
			this.executor = null;
		}
	}

	public void active() {
		this.executor = Executors.newScheduledThreadPool(4);
		this.executor.scheduleAtFixedRate(this, 0, 3, TimeUnit.SECONDS);
	}

}
