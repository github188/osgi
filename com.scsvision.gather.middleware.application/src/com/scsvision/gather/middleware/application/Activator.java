package com.scsvision.gather.middleware.application;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Activator
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014 下午2:15:28
 */
public class Activator implements BundleActivator {

	private Application application;

	@Override
	public void start(BundleContext context) throws Exception {
		application = new Application();
		context.registerService(Application.class, application, new Hashtable());

		ServiceReference<Application> svr = context
				.getServiceReference(Application.class);

		application = (Application) context.getService(svr);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (application != null) {
			application = null;
		}
	}

}
