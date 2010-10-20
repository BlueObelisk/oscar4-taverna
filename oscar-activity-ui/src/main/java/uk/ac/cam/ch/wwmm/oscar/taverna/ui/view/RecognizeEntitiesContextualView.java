package uk.ac.cam.ch.wwmm.oscar.taverna.ui.view;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivityConfigurationBean;
import uk.ac.cam.ch.wwmm.oscar.taverna.ui.config.RecognizeEntitiesConfigureAction;

@SuppressWarnings("serial")
public class RecognizeEntitiesContextualView extends ContextualView {
	private final RecognizeEntitiesActivity activity;
	private JLabel description = new JLabel("ads");

	public RecognizeEntitiesContextualView(RecognizeEntitiesActivity activity) {
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
		RecognizeEntitiesActivityConfigurationBean configuration = activity
				.getConfiguration();
		return "Example service";
	}

	/**
	 * Typically called when the activity configuration has changed.
	 */
	@Override
	public void refreshView() {
		RecognizeEntitiesActivityConfigurationBean configuration = activity
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
		return new RecognizeEntitiesConfigureAction(activity, owner);
	}

}
