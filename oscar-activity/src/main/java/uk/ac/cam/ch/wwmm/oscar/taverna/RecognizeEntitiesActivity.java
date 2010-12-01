package uk.ac.cam.ch.wwmm.oscar.taverna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.taverna.t2.invocation.InvocationContext;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;
import nu.xom.Builder;
import nu.xom.Document;
import uk.ac.cam.ch.wwmm.oscar.document.IProcessingDocument;
import uk.ac.cam.ch.wwmm.oscar.document.IToken;
import uk.ac.cam.ch.wwmm.oscar.document.ITokenSequence;
import uk.ac.cam.ch.wwmm.oscar.document.NamedEntity;
import uk.ac.cam.ch.wwmm.oscar.document.ProcessingDocumentFactory;
import uk.ac.cam.ch.wwmm.oscarMEMM.MEMMRecogniser;
import uk.ac.cam.ch.wwmm.oscartokeniser.Tokeniser;

public class RecognizeEntitiesActivity extends
		AbstractAsynchronousActivity<RecognizeEntitiesActivityConfigurationBean>
		implements AsynchronousActivity<RecognizeEntitiesActivityConfigurationBean> {

	/*
	 * Best practice: Keep port names as constants to avoid misspelling. This
	 * would not apply if port names are looked up dynamically from the service
	 * operation, like done for WSDL services.
	 */
	private static final String INPUT = "plainText";
	private static final String OUTPUT = "entities";
	
	private RecognizeEntitiesActivityConfigurationBean configBean;

	@Override
	public void configure(RecognizeEntitiesActivityConfigurationBean configBean)
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
				String inputText = (String) referenceService.renderIdentifier(
					inputs.get(INPUT), String.class, context
				);
				
				// Register outputs
				Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
				String normalizedText = normalize(inputText);
				List<ITokenSequence> tokens;
				try {
					tokens = tokenize(normalizedText);
					List<NamedEntity> entities = recognizeNamedEntities(tokens);
					
					for (NamedEntity entity : entities) {
						String entityName = entity.getSurface();
						T2Reference simpleRef = referenceService.register(
							entityName, 0, true, context
						);
						outputs.put(OUTPUT, simpleRef);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// return map of output data, with empty index array as this is
				// the only and final result (this index parameter is used if
				// pipelining output)
				callback.receiveResult(outputs, new int[0]);
			}
			
			public List<ITokenSequence> tokenize(String input) throws Exception {
				IProcessingDocument procDoc = new ProcessingDocumentFactory().
					makeTokenisedDocument(Tokeniser.getInstance(),
						input
					);
				List<ITokenSequence> tokenSequences = procDoc.getTokenSequences();
				for (ITokenSequence tokens : tokenSequences) {
					for (IToken token : tokens.getTokens())
						System.out.println("token: " + token.getValue());
				}
				return tokenSequences;
			}

			public String normalize(String input) {
				return input;
			}

			public List<NamedEntity> recognizeNamedEntities(List<ITokenSequence> tokens) throws Exception {
				MEMMRecogniser mer = new MEMMRecogniser();
				return mer.findNamedEntities(tokens);
			}

		});
	}

	@Override
	public RecognizeEntitiesActivityConfigurationBean getConfiguration() {
		return this.configBean;
	}

}
