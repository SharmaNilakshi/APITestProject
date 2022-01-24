package utils;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {

	// Utility method fetching response for dynamic input 
	public void checkResponse(String q, String appid, String URL) 
	{
		String[] propFileValues = readPropData(q, appid, URL);	
		
		given()				
			.param("q", propFileValues[0])
			.param("appid", propFileValues[1])
			.get(propFileValues[2])
		.then()					
			.log().all()
			.statusCode(200)
			.body("sys.country", equalTo("LT"));
	}	
	
	// Utility method reading data from property file
	public String[] readPropData(String q, String appid, String URL)
	{
		String[] propFileValues = null;;
		InputStream input;
		
		try {
			input = new FileInputStream("./././src/main/resources/data.prop");	

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String fetched_q = prop.getProperty(q);
            String fetched_appid = prop.getProperty(appid);
            String fetched_URL = prop.getProperty(URL);   
            
            propFileValues = new String[] {fetched_q, fetched_appid, fetched_URL} ;            
            
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return propFileValues;		
		
	}
}
