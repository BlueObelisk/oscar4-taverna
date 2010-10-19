package uk.ac.cam.ch.wwmm.oscar.taverna;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.taverna.t2.activities.testutils.ActivityInvoker;
import net.sf.taverna.t2.workflowmodel.OutputPort;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityInputPort;

import org.junit.Before;
import org.junit.Test;

public class RecognizeEntitiesActivityTest {

	private RecognizeEntitiesActivityConfigurationBean configBean;

	private RecognizeEntitiesActivity activity = new RecognizeEntitiesActivity();

	@Before
	public void makeConfigBean() throws Exception {
		configBean = new RecognizeEntitiesActivityConfigurationBean();
	}

	@Test
	public void invalidConfiguration() throws ActivityConfigurationException {
		RecognizeEntitiesActivityConfigurationBean invalidBean =
			new RecognizeEntitiesActivityConfigurationBean();
		// Should throw ActivityConfigurationException
		activity.configure(invalidBean);
	}

	@Test
	public void executeAsynch() throws Exception {
		activity.configure(configBean);

		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("iupacName", "hello, what about 1-propanol?");

		Map<String, Class<?>> expectedOutputTypes = new HashMap<String, Class<?>>();
		expectedOutputTypes.put("CML", String.class);

		Map<String, Object> outputs = ActivityInvoker.invokeAsyncActivity(
				activity, inputs, expectedOutputTypes);

		assertEquals("Unexpected outputs", 1, outputs.size());
		assertTrue(outputs.get("CML").toString().contains("propanol"));
	}

	@Test
	public void reConfiguredActivity() throws Exception {
		assertEquals("Unexpected inputs", 0, activity.getInputPorts().size());
		assertEquals("Unexpected outputs", 0, activity.getOutputPorts().size());

		activity.configure(configBean);
		assertEquals("Unexpected inputs", 1, activity.getInputPorts().size());
		assertEquals("Unexpected outputs", 1, activity.getOutputPorts().size());

		activity.configure(configBean);
		// Should not change on reconfigure
		assertEquals("Unexpected inputs", 1, activity.getInputPorts().size());
		assertEquals("Unexpected outputs", 1, activity.getOutputPorts().size());
	}

	@Test
	public void reConfiguredSpecialPorts() throws Exception {
		activity.configure(configBean);

		RecognizeEntitiesActivityConfigurationBean specialBean =
			new RecognizeEntitiesActivityConfigurationBean();
		activity.configure(specialBean);
		// Should now have added the optional ports
		assertEquals("Unexpected inputs", 1, activity.getInputPorts().size());
		assertEquals("Unexpected outputs", 1, activity.getOutputPorts().size());
	}

	@Test
	public void configureActivity() throws Exception {
		Set<String> expectedInputs = new HashSet<String>();
		expectedInputs.add("iupacName");

		Set<String> expectedOutputs = new HashSet<String>();
		expectedOutputs.add("CML");

		activity.configure(configBean);

		Set<ActivityInputPort> inputPorts = activity.getInputPorts();
		assertEquals(expectedInputs.size(), inputPorts.size());
		for (ActivityInputPort inputPort : inputPorts) {
			assertTrue("Wrong input : " + inputPort.getName(), expectedInputs
					.remove(inputPort.getName()));
		}

		Set<OutputPort> outputPorts = activity.getOutputPorts();
		assertEquals(expectedOutputs.size(), outputPorts.size());
		for (OutputPort outputPort : outputPorts) {
			assertTrue("Wrong output : " + outputPort.getName(),
					expectedOutputs.remove(outputPort.getName()));
		}
	}
}
