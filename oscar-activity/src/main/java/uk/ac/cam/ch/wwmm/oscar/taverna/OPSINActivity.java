package uk.ac.cam.ch.wwmm.oscar.taverna;

import java.util.HashMap;
import java.util.Map;

import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureException;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult.OPSIN_RESULT_STATUS;

import net.sf.taverna.t2.invocation.InvocationContext;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;

public class OPSINActivity extends
		AbstractAsynchronousActivity<OPSINActivityConfigurationBean>
		implements AsynchronousActivity<OPSINActivityConfigurationBean> {

	/*
	 * Best practice: Keep port names as constants to avoid misspelling. This
	 * would not apply if port names are looked up dynamically from the service
	 * operation, like done for WSDL services.
	 */
	private static final String INPUT = "iupacName";
	private static final String OUTPUT = "CML";
	
	private OPSINActivityConfigurationBean configBean;

	@Override
	public void configure(OPSINActivityConfigurationBean configBean)
			throws ActivityConfigurationException {

		this.configBean = configBean;
		configurePorts();
	}

	protected void configurePorts() {
		removeInputs();
		removeOutputs();

		addInput(INPUT, 0, true, null, String.class);
		addOutput(OUTPUT, 0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAsynch(final Map<String, T2Reference> inputs,
			final AsynchronousActivityCallback callback) {
		// Don't execute service directly now, request to be run ask to be run
		// from thread pool and return asynchronously
		callback.requestRun(new Runnable() {
			
			public void run() {
				InvocationContext context = callback
						.getContext();
				ReferenceService referenceService = context
						.getReferenceService();
				// Resolve inputs 				
				String iupacName = (String) referenceService.renderIdentifier(
					inputs.get(INPUT), String.class, context
				);
				
				// Register outputs
				Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
				String cml = "<cml/>";
				try {
					NameToStructure nameToStructure = NameToStructure.getInstance();
					OpsinResult result = nameToStructure.parseChemicalName(iupacName);
					if (result.getStatus() == OPSIN_RESULT_STATUS.SUCCESS) {
						cml = result.getCml().toXML();
					}
				} catch (NameToStructureException e) {
					e.printStackTrace();
				}
				T2Reference simpleRef = referenceService.register(cml, 0, true, context);
				outputs.put(OUTPUT, simpleRef);

				// return map of output data, with empty index array as this is
				// the only and final result (this index parameter is used if
				// pipelining output)
				callback.receiveResult(outputs, new int[0]);
			}
		});
	}

	@Override
	public OPSINActivityConfigurationBean getConfiguration() {
		return this.configBean;
	}

}
