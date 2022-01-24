package Tests;
import org.testng.annotations.Test;
import utils.Utility;

public class TestAPI_01 {
	
	Utility utils;
	
	// Verify response body, status code and country		
		
		@Test
		void verifyReponse_Positive()
		{
	            utils = new Utility();
	            utils.checkResponse("q", "correct_appid", "correct_URL");
		}
		
	//Verify response for incorrect appId
		
		@Test
		void verifyResponseForIncorrectAppId_Negative1()
		{
			
			utils = new Utility();
            utils.checkResponse("q", "incorrect_appid", "correct_URL");	
			
		}
	
	//Verify response for incorrect URL 
	
		@Test
		void verifyResponseForIncorrectUrl_Negative2()
		{
			utils = new Utility();
            utils.checkResponse("q", "correct_appid", "incorrect_URL");
			
		}	
	
}
