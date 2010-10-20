package uk.ac.cam.ch.wwmm.oscar.taverna.ui.view;

import java.util.Arrays;
import java.util.List;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;
import uk.ac.cam.ch.wwmm.oscar.taverna.RecognizeEntitiesActivity;

public class RecognizeEntitiesActivityContextViewFactory implements
		ContextualViewFactory<RecognizeEntitiesActivity> {

	public boolean canHandle(Object selection) {
		return selection instanceof RecognizeEntitiesActivity;
	}

	public List<ContextualView> getViews(RecognizeEntitiesActivity selection) {
		return Arrays.<ContextualView>asList(new RecognizeEntitiesContextualView(selection));
	}
	
}
