package uk.ac.cam.ch.wwmm.oscar.taverna;

import java.util.ArrayList;
import java.util.List;

import net.sf.taverna.t2.visit.VisitReport;
import net.sf.taverna.t2.workflowmodel.health.HealthCheck;
import net.sf.taverna.t2.workflowmodel.health.HealthChecker;

/**
 * Example health checker
 * 
 */
public class OPSINActivityHealthChecker implements
		HealthChecker<OPSINActivity> {

	public boolean canVisit(Object o) {
		// Return True if we can visit the object. We could do
		// deeper (but not time consuming) checks here, for instance
		// if the health checker only deals with ExampleActivity where
		// a certain configuration option is enabled.
		return o instanceof OPSINActivity;
	}

	public boolean isTimeConsuming() {
		// Return true if the health checker does a network lookup
		// or similar time consuming checks, in which case
		// it would only be performed when using File->Validate workflow
		// or File->Run.
		return false;
	}

	public VisitReport visit(OPSINActivity activity, List<Object> ancestry) {
		OPSINActivityConfigurationBean config = activity.getConfiguration();

		// We'll build a list of subreports
		List<VisitReport> subReports = new ArrayList<VisitReport>();

		// The default explanation here will be used if the subreports list is
		// empty
		return new VisitReport(HealthCheck.getInstance(), activity,
				"OPSIN service OK", HealthCheck.NO_PROBLEM, subReports);
	}

}
