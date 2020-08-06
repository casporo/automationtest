package API;

import java.io.IOException;
import junitparams.JUnitParamsRunner;
import org.apache.http.HttpHeaders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


@RunWith(JUnitParamsRunner.class)
public class DummyAPI<extentTest> {

    String name = "John Wick", salary = "15000", age = "23";
    String extentReportFile = System.getProperty("D:\\J Projects\\moneylion\\src\\test\\testReport") + "extentReportFile.html";

    // Create object of extent report and specify the report file path.
    ExtentReports extent = new ExtentReports(extentReportFile, false);

    // Start the test using the ExtentTest class object.
    ExtentTest extentTest = extent.startTest("My First Test","Verify WebSite Title");

    //Scenario 1: Create a enw employee entry with First name, Last Name, Salary and Age
    @Before
    public void createData() throws Exception {
        extentTest.log(LogStatus.INFO, "New Employee Entry created with sample data");
        String result = "";
        HttpPost post = new HttpPost("http://dummy.restapiexample.com/api/v1/create");
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\":" + "\"").append(name).append("\"").append(",");
        json.append("\"salary\":" + "\"").append(salary).append("\"").append(",");
        json.append("\"age\":" + "\"").append(age).append("\"");
        json.append("}");

        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        System.out.println("Request Data: " + json.toString());

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
            extentTest.log(LogStatus.PASS, "New Entry Created!");

        }catch (Exception e) {
            extentTest.log(LogStatus.FAIL, "New Entry failed");
        }
    }

    //Scenario 2: Verify entry created in Scenario 1 exist on employee list
   @Test
    public void requestData() throws IOException {
       extentTest.log(LogStatus.INFO, "Verifying entry created in Scenario 1 exist on employee list");

        HttpGet request = new HttpGet("http://dummy.restapiexample.com/api/v1/employees");

        // add request headers
        request.addHeader("Content-Type", "application/x-httpd-php");
        request.addHeader(HttpHeaders.USER_AGENT, "");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String actualResult = EntityUtils.toString(entity);
                String expectedResult = "{" + "\"employee_name:\":" + "\"" + name + "\"" + "," + "\"employee_salary:\":" + "\"" + salary + "\"" + "," + "\"employee_name:\":" + "\"" + age + "\"" + "}";

                if(!expectedResult.equals(actualResult)){
                    Assert.fail("Test Failed!. Created Staff Data is not present in Requested Data");
                    extentTest.log(LogStatus.FAIL, "Created Staff Data is not present in Requested Data");
                }else{
                    extentTest.log(LogStatus.PASS, "Created Staff Data is present in Requested Data");
                }
            }
        }
       // close report.
       extent.endTest(extentTest);

       // writing everything to document.
       extent.flush();
    }

}
