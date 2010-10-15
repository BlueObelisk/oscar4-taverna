package uk.ac.cam.ch.wwmm.oscar.taverna.ui.view;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import uk.ac.cam.ch.wwmm.oscar.taverna.ExampleActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.ExampleActivityConfigurationBean;
import uk.ac.cam.ch.wwmm.oscar.taverna.ui.config.ExampleConfigureAction;

@SuppressWarnings("serial")
public class ExampleContextualView extends ContextualView {
	private final ExampleActivity activity;
	private JLabel description = new JLabel("ads");

	public ExampleContextualView(ExampleActivity activity) {
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
		ExampleActivityConfigurationBean configuration = activity
				.getConfiguration();
		return "Example service";
	}

	/**
	 * Typically called when the activity configuration has changed.
	 */
	@Override
	public void refreshView() {
		ExampleActivityConfigurationBean configuration = activity
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
		return new ExampleConfigureAction(activity, owner);
	}

}
