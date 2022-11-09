import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.response.Response;

public class TestAssurd {
	
	/* Extent Report Declaration */
	static ExtentReports report;
	static ExtentTest test;
	String reportPath = new File("").getAbsolutePath().toString().trim()+"\\target\\";
	@BeforeTest
	public void beforeTest() {
		System.out.println("****************"+reportPath);
		report = new ExtentReports(reportPath+this.getClass().getSimpleName()+".html", false);
		
		test = report.startTest("Rest Assured API Testing");
		
		
	}
	@Test
	public void test01() {
		Response reponse = get("https://reqres.in/api/users?page=2");
		System.out.println(reponse.asString());
		System.out.println(reponse.getBody());
		System.out.println(reponse.getStatusCode());
		System.out.println(reponse.getHeader("content-type"));
		int code = reponse.getStatusCode();
		Assert.assertEquals(code, 200);
	}
@Test
	public void test02() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.assertThat().statusCode(200);	
	
	}
@AfterMethod
public void afterMethod() {
	  
	  //Closing the report
	  report.endTest(test);
	  report.flush();
	  report.close();
}
}

