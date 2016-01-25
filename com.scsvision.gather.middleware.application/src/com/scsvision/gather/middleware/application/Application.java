package com.scsvision.gather.middleware.application;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.scsvision.gather.middleware.task.IExtractTask;
import com.scsvision.gather.middleware.task.IReportTask;

/**
 * Application
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014 下午6:52:53
 */
public class Application implements CommandProvider {

	private IExtractTask extractTask;
	private IReportTask reportTask;

	public synchronized void setExtractTask(IExtractTask extractTask) {
		this.extractTask = extractTask;
	}

	public synchronized void unsetExtractTask(IExtractTask extractTask) {
		this.extractTask = null;
	}

	public synchronized void setReportTask(IReportTask reportTask) {
		this.reportTask = reportTask;
	}

	public synchronized void unsetReportTask(IReportTask reportTask) {
		this.reportTask = null;
	}

	@Override
	public String getHelp() {
		return "gstart\n  scope: Application\nrstart\n  scope:  Application";
	}

//	public void _gstart(CommandInterpreter ci) {
//		if (extractTask != null) {
//			extractTask.run();
//		} else {
//			System.out.println("ExtractTask is null, can not start !");
//		}
//	}
//
//	public void _rstart(CommandInterpreter ci) {
//		if (reportTask != null) {
//			reportTask.run();
//		} else {
//			System.out.println("ReportTask is null, can not start !");
//		}
//	}

}
