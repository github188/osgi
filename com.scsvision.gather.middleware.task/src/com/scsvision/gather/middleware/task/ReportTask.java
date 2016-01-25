package com.scsvision.gather.middleware.task;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.scsvision.gather.middleware.data.model.VehicleDetector;
import com.scsvision.gather.middleware.data.service.DataService;

/**
 * ReportTask
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-9-11 上午10:59:38
 */
public class ReportTask implements CommandProvider, IReportTask {

	private DataService dataService;
	private ScheduledExecutorService executor;

	@Override
	public void run() {
		try {
			if (null != dataService) {
				Collection<VehicleDetector> vds = dataService.listVd();
				for (VehicleDetector vd : vds) {
					System.out.println(vd.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		sb.append("read\n  scope: ReportTask\n");
		sb.append("rstart interval\n  scope: ReportTask\n");
		sb.append("rstop\n  scope: ReportTask\n");
		return sb.toString();
	}

	public void _read(CommandInterpreter ci) {
		Collection<VehicleDetector> vds = dataService.listVd();
		for (VehicleDetector vd : vds) {
			System.out.println(vd.toString());
		}
	}

	public void _rstop(CommandInterpreter ci) {
		if (this.executor != null) {
			this.executor.shutdown();
			this.executor = null;
		}
	}

	public void _rstart(CommandInterpreter ci) {
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
		this.executor.scheduleAtFixedRate(this, 0, 6, TimeUnit.SECONDS);
	}

}
