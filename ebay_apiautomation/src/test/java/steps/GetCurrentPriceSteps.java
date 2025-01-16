package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.utils.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GetCurrentPriceSteps {

 
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(GetCurrentPriceSteps.class);
	
	public GetCurrentPriceSteps(TestContext context) {
		this.context = context;
	}

	
	
    // Step 1: Send GET request to Coindesk API
    @Given("I send a GET request to the Coindesk API")
    public void iSendAGETRequestToTheCoindeskAPI() {
    	  String baseUri = "https://api.coindesk.com/v1/bpi/currentprice.json";
    	     
    	context.response = context.requestSetup().when().get(baseUri);
   }

    // Step 2: Verify the response contains 3 BPIs: USD, GBP, and EUR
    @Then("the response should contain 3 BPIs: USD, GBP, and EUR")
    public void theResponseShouldContain3BPIs() {
        assertTrue(context.response.asString().contains("\"USD\""));
        assertTrue(context.response.asString().contains("\"GBP\""));
        assertTrue(context.response.asString().contains("\"EUR\""));
    }

    // Step 3: Verify GBP description is "British Pound Sterling"
    @Then("the GBP description should be \"British Pound Sterling\"")
    public void theGBPDescriptionShouldBeBritishPoundSterling() {
        // Extract the GBP description from JSON
    String gbpDescription =	context.response.jsonPath().getString("bpi.GBP.description");
       
        // Assert that the description is as expected
        assertEquals("British Pound Sterling", gbpDescription);
        LOG.info("GBP description is"+ gbpDescription);
    }
}
