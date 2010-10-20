package uk.ac.cam.ch.wwmm.oscar.taverna.ui.serviceprovider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivityConfigurationBean;

public class RecognizeEntitiesServiceDesc extends ServiceDescription<RecognizeEntitiesActivityConfigurationBean> {

	/**
	 * The subclass of Activity which should be instantiated when adding a service
	 * for this description 
	 */
	@Override
	public Class<? extends Activity<RecognizeEntitiesActivityConfigurationBean>> getActivityClass() {
		return RecognizeEntitiesActivity.class;
	}

	/**
	 * The configuration bean which is to be used for configuring the instantiated activity.
	 * Making this bean will typically require some of the fields set on this service
	 * description, like an endpoint URL or method name. 
	 * 
	 */
	@Override
	public RecognizeEntitiesActivityConfigurationBean getActivityConfiguration() {
		RecognizeEntitiesActivityConfigurationBean bean =
			new RecognizeEntitiesActivityConfigurationBean();
		return bean;
	}

	/**
	 * An icon to represent this service description in the service palette.
	 */
	@Override
	public Icon getIcon() {
		return null;
	}

	/**
	 * The display name that will be shown in service palette and will
	 * be used as a template for processor name when added to workflow.
	 */
	@Override
	public String getName() {
		return "Oscar";
	}

	/**
	 * The path to this service description in the service palette. Folders
	 * will be created for each element of the returned path.
	 */
	@Override
	public List<String> getPath() {
		// For deeper paths you may return several strings
		return Arrays.asList("Oscar4");
	}

	/**
	 * Return a list of data values uniquely identifying this service
	 * description (to avoid duplicates). Include only primary key like fields,
	 * ie. ignore descriptions, icons, etc.
	 */
	@Override
	protected List<? extends Object> getIdentifyingData() {
		// FIXME: Use your fields instead of example fields
		return new ArrayList<Object>();
	}

}
