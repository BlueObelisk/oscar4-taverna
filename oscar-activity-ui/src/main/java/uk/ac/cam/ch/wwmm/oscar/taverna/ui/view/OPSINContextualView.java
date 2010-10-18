package uk.ac.cam.ch.wwmm.oscar.taverna.ui.view;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivityConfigurationBean;
import uk.ac.cam.ch.wwmm.oscar.taverna.ui.config.OPSINConfigureAction;

@SuppressWarnings("serial")
public class OPSINContextualView extends ContextualView {
	private final OPSINActivity activity;
	private JLabel description = new JLabel("ads");

	public OPSINContextualView(OPSINActivity activity) {
		this.activity = activity;
		initView();
	}

	@Override
	public JComponent getMainFrame() {
		JPanel jPanel = new JPanel();
		jPanel.add(description);
		refreshView();
		return jPanel;
	}

	@Override
	public String getViewTitle() {
		OPSINActivityConfigurationBean configuration = activity
				.getConfiguration();
		return "Example service";
	}

	/**
	 * Typically called when the activity configuration has changed.
	 */
	@Override
	public void refreshView() {
		OPSINActivityConfigurationBean configuration = activity
				.getConfiguration();
		description.setText("Example service ");
		// TODO: Might also show extra service information looked
		// up dynamically from endpoint/registry
	}

	/**
	 * View position hint
	 */
	@Override
	public int getPreferredPosition() {
		// We want to be on top
		return 100;
	}
	
	@Override
	public Action getConfigureAction(final Frame owner) {
		return new OPSINConfigureAction(activity, owner);
	}

}
