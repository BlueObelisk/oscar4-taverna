package uk.ac.cam.ch.wwmm.oscar.taverna.ui.serviceprovider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.servicedescriptions.ServiceDescriptionProvider;

public class OPSINServiceProvider implements ServiceDescriptionProvider {
	
	private static final URI providerId = URI
		.create("http://example.com/2010/service-provider/example-activity-ui");
	
	/**
	 * Do the actual search for services. Return using the callBack parameter.
	 */
	@SuppressWarnings("unchecked")
	public void findServiceDescriptionsAsync(
			FindServiceDescriptionsCallBack callBack) {
		List<ServiceDescription> results = new ArrayList<ServiceDescription>();

		ServiceDescription service = new OPSINServiceDesc();
		service.setDescription("Convert IUPAC names into CML");
		results.add(service);
		callBack.partialResults(Collections.singletonList(service));

		service = new RecognizeEntitiesServiceDesc();
		service.setDescription("Extract chemical entities from a plain text");
		results.add(service);
		callBack.partialResults(Collections.singletonList(service));

		callBack.finished();
	}

	/**
	 * Icon for service provider
	 */
	public Icon getIcon() {
		return OPSINServiceIcon.getIcon();
	}

	/**
	 * Name of service provider, appears in right click for 'Remove service
	 * provider'
	 */
	public String getName() {
		return "My example service";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getId() {
		return providerId.toASCIIString();
	}

}
