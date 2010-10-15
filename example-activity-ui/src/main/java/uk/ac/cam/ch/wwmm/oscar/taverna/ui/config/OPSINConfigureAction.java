package uk.ac.cam.ch.wwmm.oscar.taverna.ui.config;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationDialog;

import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivityConfigurationBean;

@SuppressWarnings("serial")
public class OPSINConfigureAction
		extends
		ActivityConfigurationAction<OPSINActivity, OPSINActivityConfigurationBean> {

	public OPSINConfigureAction(OPSINActivity activity, Frame owner) {
		super(activity);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		ActivityConfigurationDialog<OPSINActivity, OPSINActivityConfigurationBean> currentDialog = ActivityConfigurationAction
				.getDialog(getActivity());
		if (currentDialog != null) {
			currentDialog.toFront();
			return;
		}
		OPSINConfigurationPanel panel = new OPSINConfigurationPanel(
				getActivity());
		ActivityConfigurationDialog<OPSINActivity, OPSINActivityConfigurationBean> dialog = new ActivityConfigurationDialog<OPSINActivity, OPSINActivityConfigurationBean>(
				getActivity(), panel);

		ActivityConfigurationAction.setDialog(getActivity(), dialog);

	}

}
