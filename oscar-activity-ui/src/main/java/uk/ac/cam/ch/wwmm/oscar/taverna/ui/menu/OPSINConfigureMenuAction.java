package uk.ac.cam.ch.wwmm.oscar.taverna.ui.menu;

import javax.swing.Action;

import net.sf.taverna.t2.workbench.activitytools.AbstractConfigureActivityMenuAction;
import uk.ac.cam.ch.wwmm.oscar.taverna.OPSINActivity;
import uk.ac.cam.ch.wwmm.oscar.taverna.ui.config.OPSINConfigureAction;

public class OPSINConfigureMenuAction extends
		AbstractConfigureActivityMenuAction<OPSINActivity> {

	public OPSINConfigureMenuAction() {
		super(OPSINActivity.class);
	}

	@Override
	protected Action createAction() {
		OPSINActivity a = findActivity();
		Action result = null;
		result = new OPSINConfigureAction(findActivity(),
				getParentFrame());
		result.putValue(Action.NAME, "Configure example service");
		addMenuDots(result);
		return result;
	}

}
