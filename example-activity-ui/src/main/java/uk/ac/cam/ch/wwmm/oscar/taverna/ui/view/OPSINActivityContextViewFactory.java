package uk.ac.cam.ch.wwmm.oscar.taverna.ui.view;

import java.util.Arrays;
import java.util.List;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;

import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivity;

public class OPSINActivityContextViewFactory implements
		ContextualViewFactory<OPSINActivity> {

	public boolean canHandle(Object selection) {
		return selection instanceof OPSINActivity;
	}

	public List<ContextualView> getViews(OPSINActivity selection) {
		return Arrays.<ContextualView>asList(new OPSINContextualView(selection));
	}
	
}
