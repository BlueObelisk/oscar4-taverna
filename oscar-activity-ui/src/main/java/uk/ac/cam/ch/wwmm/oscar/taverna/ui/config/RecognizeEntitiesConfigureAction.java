package uk.ac.cam.ch.wwmm.oscar.taverna.ui.config;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import net.sf.taverna.t2.workbench.ui.actions.activity.ActivityConfigurationAction;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ActivityConfigurationDialog;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivityConfigurationBean;

@SuppressWarnings("serial")
public class RecognizeEntitiesConfigureAction
		extends
		ActivityConfigurationAction<RecognizeEntitiesActivity, RecognizeEntitiesActivityConfigurationBean> {

	public RecognizeEntitiesConfigureAction(RecognizeEntitiesActivity activity, Frame owner) {
		super(activity);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		ActivityConfigurationDialog<RecognizeEntitiesActivity, RecognizeEntitiesActivityConfigurationBean> currentDialog = ActivityConfigurationAction
				.getDialog(getActivity());
		if (currentDialog != null) {
			currentDialog.toFront();
			return;
		}
		RecognizeEntitiesConfigurationPanel panel = new RecognizeEntitiesConfigurationPanel(
				getActivity());
		ActivityConfigurationDialog<RecognizeEntitiesActivity, RecognizeEntitiesActivityConfigurationBean> dialog =
			    new ActivityConfigurationDialog<RecognizeEntitiesActivity, RecognizeEntitiesActivityConfigurationBean>(
				getActivity(), panel);

		ActivityConfigurationAction.setDialog(getActivity(), dialog);

	}

}
