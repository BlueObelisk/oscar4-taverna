package uk.ac.cam.ch.wwmm.oscar.taverna.ui.config;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationPanel;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivityConfigurationBean;


@SuppressWarnings("serial")
public class OPSINConfigurationPanel
		extends
		ActivityConfigurationPanel<OPSINActivity, OPSINActivityConfigurationBean> {

	private OPSINActivity activity;
	private OPSINActivityConfigurationBean configBean;
	
	public OPSINConfigurationPanel(OPSINActivity activity) {
		this.activity = activity;
		initGui();
	}

	protected void initGui() {
		removeAll();
		// Populate fields from activity configuration bean
		refreshConfiguration();
	}

	/**
	 * Check that user values in UI are valid
	 */
	@Override
	public boolean checkValues() {
		// All valid, return true
		return true;
	}

	/**
	 * Return configuration bean generated from user interface last time
	 * noteConfiguration() was called.
	 */
	@Override
	public OPSINActivityConfigurationBean getConfiguration() {
		// Should already have been made by noteConfiguration()
		return configBean;
	}

	/**
	 * Check if the user has changed the configuration from the original
	 */
	@Override
	public boolean isConfigurationChanged() {
		return false;
	}

	/**
	 * Prepare a new configuration bean from the UI, to be returned with
	 * getConfiguration()
	 */
	@Override
	public void noteConfiguration() {
		configBean = new OPSINActivityConfigurationBean();
	}

	/**
	 * Update GUI from a changed configuration bean (perhaps by undo/redo).
	 * 
	 */
	@Override
	public void refreshConfiguration() {
		configBean = activity.getConfiguration();
	}
}
