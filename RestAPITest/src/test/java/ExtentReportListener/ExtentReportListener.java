package ExtentReportListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.testng.*;
import org.testng.IReporter;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.*;

public class ExtentReportListener implements IReporter
{
	private ExtentReports extent;
	
	// Method to generate extent report after each run
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory)
	{
		extent = new ExtentReports(outputDirectory + File.separator + "ExtentReport.html", true);
		for (ISuite suite : suites)
		{
			Map<String, ISuiteResult> result = suite.getResults();
			for(ISuiteResult r : result.values())
			{
				ITestContext context = r.getTestContext();
				
				buildTests(context.getPassedTests(), LogStatus.PASS);
				buildTests(context.getFailedTests(), LogStatus.FAIL);
				buildTests(context.getSkippedTests(), LogStatus.SKIP);
			}
		}
		
		extent.flush();		
	}
	
	// Method getting the status from the log
	 private void buildTests(IResultMap tests, LogStatus status)
	 {
		 ExtentTest test = null;
		 
		 if(tests.size()>0)
		 {
			 for (ITestResult result : tests.getAllResults())
			 {
				 test = extent.startTest(result.getMethod().getMethodName());
				 
				 if(result.getThrowable() != null) {
					 test.log(status, result.getThrowable());
				 }else {
					 test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				 }					 
				 extent.endTest(test); 
			 }
			 
		 }
		 
	 }  

}
