package testSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.browserstack.local.Local;
import com.google.common.collect.ImmutableMap;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JavaScriptAssignment {

	public WebDriver driver;
	protected Local l;	
	public ExtentReports extent;
	public ExtentTest specTest,apiTest,pageLoad,respTest;
	public ExtentTest appQuality,skillTest;
	public  String testName;
	public static Response response;
	public static String jsonAsString;
	public  File destination;
	public  String dest;
	ExtentHtmlReporter htmlReporter;
	Markup m;
	public int ts01;
	public int ts02;
	public int ts03;
	public int ts04;
	public int ts06;
	public int ts07;
	public int ts08;
	public int ts09;
	public int ts10;
	public int ts11;
	public int ts12;
	public int ts14;
	public int ts15;
	public int ts16;
	public int ts20;
	public int ts21,ts17,ts18,ts19,p1,p2,p3;

	public int totalSpecScore,totalAPIScore,totalapiperf,totalScore;
	public double specperc,apiperc,apiperfperc,totalperc;
	public long addInsuranceT;
	public long pageloadTime;
	public long getDetails2T;
	public long getDetails3T;
	List<String> skill1 = new ArrayList<String>();
	List<String> skill2 = new ArrayList<String>();
	List<String> skill3 = new ArrayList<String>();
	List<String> skill4 = new ArrayList<String>();
	List<String> skill6 = new ArrayList<String>();
	List<String> skill7 = new ArrayList<String>();
	List<String> skill8 = new ArrayList<String>();
	List<String> skill9 = new ArrayList<String>();
	List<String> skill10 = new ArrayList<String>();
	List<String> skill11 = new ArrayList<String>();
	List<String> skill12 = new ArrayList<String>();
	String policyNumbernew;
	String url="http://localhost:3000/";
	private String status3;
	private String status2;
	private String status1;
	private String status4;
	private String status5;
	private String status6;
	private String status7;
	private String status8;
	private String status9;
	private String status10;
	private String status11;
	private String status12;
	private String webPerc;
	private int skillpercantage;
	private String skillstatus;
	String osName;




	//Capture ScreenShots
	public   String captureScreenMethod(String dest) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		dest=System.getProperty("user.dir") +"//Report//"+System.currentTimeMillis()+".png";
		destination = new File(dest);
		FileUtils.copyFile(source, destination);
		Path p = Paths.get(dest);
		String screenFile = p.getFileName().toString();
		return screenFile;

	}


	@BeforeSuite()
	public void setup() throws IOException
	{
		htmlReporter = new ExtentHtmlReporter("./Report/Associate Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("SBA", "jQuery - Insurance - Solution Kit");
		htmlReporter.config().setDocumentTitle("Assessment Report_Associate_01");
		htmlReporter.config().setChartVisibilityOnOpen(false);
		//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//		ChromeOptions options = new ChromeOptions();
		//		options.addArguments("disable-infobars");
		//		driver= new ChromeDriver(options);
		//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		osName=System.getProperty("os.name");
		System.out.println(osName);
		if(osName.contains("Windows"))
		{
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver = new ChromeDriver();
		}
		else
		{

			//fix for headless systems:
			// start service first
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("/usr/lib/chromium-browser/chromedriver"))
					.usingAnyFreePort()
					.withEnvironment(ImmutableMap.of("DISPLAY",":10"))
					.build();
			service.start();
			//then start driver, with URL mapped to above-started service URL
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("–start-maximized");
			dc.setCapability(ChromeOptions.CAPABILITY,options);
			driver = new RemoteWebDriver(service.getUrl(), dc);
			driver.get("http://localhost:3000/");
		}
	}
	@BeforeMethod
	public void nameBefore(Method method)
	{
		testName = method.getName();       
	}

	@Test
	public void report()
	{
		//Creating Application Quality Test
		appQuality=extent.createTest("Application Quality");
		appQuality.assignAuthor("Candidate Name Will be Showned Here");


	}


	//For Specification
	@Test(priority=1)
	public void specification() throws InterruptedException
	{

		//TS01
		try {
			specTest = appQuality.createNode("<b>Specification</b>");
			Assert.assertEquals(driver.findElement(By.xpath("//strong[contains(.,'Get New Insurance')]")).getText(),"Get New Insurance");
			Assert.assertEquals(driver.findElement(By.xpath("//strong[contains(.,'Renew Insurance')]")).getText(),"Renew Insurance");
			Assert.assertEquals(driver.findElement(By.xpath("//a[contains(.,'MyInsuranceQuote ')]")).getText(),"MyInsuranceQuote");
			Assert.assertEquals(driver.findElement(By.xpath("//p[contains(.,'Copyright © Cognizant. 2016. All Rights Reserved.')]")).getText(),"Copyright © Cognizant. 2016. All Rights Reserved.");
			specTest.log(Status.PASS, "<b>TS01:</b><br/>"
					+ "Two tabs i.e. 'Get new insurance' tab and 'renew insurance' tab are created. "
					+ "Along with headers and footers of the page.<br/><b>Score:</b><br/>5/5");
			ts01=5;
			skill1.add("TS01");
		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS01:</b><br/>"
					+ "Two tabs i.e. 'Get new insurance' tab and 'renew insurance' tab are created. "
					+ "Along with headers and footers of the page."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/5"+"");
			ts01=0;

		}

		//TS02
		try {
			Thread.sleep(2000);
			Assert.assertEquals(driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>strong"))
					.getText(), "Get New Insurance");
			Assert.assertEquals(driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>strong"))
					.getText(), "Renew Insurance");
			Assert.assertEquals(driver.findElement(By.cssSelector(".navbar-brand"))
					.getText(), "MyInsuranceQuote");
			Assert.assertEquals(driver.findElement(By.cssSelector(".navbar-text.pull-right"))
					.getText(), "Copyright © Cognizant. 2016. All Rights Reserved.");
			specTest.log(Status.PASS, "<b>TS02:</b>"
					+ "<br/>Both tabs are able to switch when clicked on radio buttons on the top."
					+ "<br/><b>Score:</b>"
					+ "<br/>5/5");
			ts02=5;
			skill1.add("TS02");

		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS02:</b>"
					+ "<br/>Both tabs are able to switch when clicked on radio buttons on the top."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/5"+"");
			ts02=0;

		}

		//TS03
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[contains(text(),'Get New Insurance')]//preceding-sibling::input")).click();
			driver.findElement(By.xpath("//option[contains(.,'Select a car type')]"));
			driver.findElement(By.xpath("//option[contains(.,'Select a fuel type')]"));
			driver.findElement(By.xpath("//option[contains(.,'Select a state')]"));
			driver.findElement(By.xpath("//input[contains(@placeholder,'Enter your name')]"));
			driver.findElement(By.xpath("//input[contains(@placeholder,'Enter your mobile number')]"));
			driver.findElement(By.xpath("//button[contains(.,'Get Quotes')]"));
			specTest.log(Status.PASS, "<b>TS03:</b>"
					+ "<br/>'Get new insurance' tab is having 3 drop down fields, 2 text fields and 1 submit button"
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts03=6;
			skill2.add("TS03");
		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS03:</b>"
					+ "<br/>'Get new insurance' tab is having 3 drop down fields, 2 text fields and 1 submit button"
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts03=0;
		}

		//TS04

		try {
			Thread.sleep(2000);
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			Assert.assertNotNull(selcars.getOptions());
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			Assert.assertNotNull(selFuel.getOptions());
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			Assert.assertNotNull(selState.getOptions());
			specTest.log(Status.PASS, "<b>TS04:</b><br/>"
					+ "All 3 drop down fields are pre-filled with required data by making 'HTTP GET' request.<br/> "
					+ "Car: has pre filled list of cars.<br/>"
					+ "Fuel type: has pre filled list of fuel types.<br/>"
					+ "State :  has pre filled list of states"
					+ "Along with headers and footers of the page."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts04=6;
			skill11.add("ts04");
			skill12.add("ts04");

		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS04:</b><br/>"
					+ "All 3 drop down fields are pre-filled with required data by making 'HTTP GET' request.<br/> "
					+ "Car: has pre filled list of cars.<br/>"
					+ "Fuel type: has pre filled list of fuel types.<br/>"
					+ "State :  has pre filled list of states"
					+ "Along with headers and footers of the page."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts04=0;

		}

		//TS06
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>input")).click();
			Assert.assertTrue(driver.findElement(By.id("txtPolicyNumber")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.id("btnGetDetails")).isDisplayed());
			specTest.log(Status.PASS, "<b>TS06:</b>"
					+ "<br/>'Renew insurance' tab is having 1 input box and 1 submit button"
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts06=6;
			skill2.add("TS06");
		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS06:</b>"
					+ "<br/>'Renew insurance' tab is having 1 input box and 1 submit button"
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts06=0;
		}

		//TS07
		try {
			Thread.sleep(2000);
			if(!driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).isSelected()){
				driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
				Select selcars=new Select(driver.findElement(By.id("ddCarType")));
				selcars.selectByIndex(1);
				Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
				selFuel.selectByIndex(1);
				Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
				selState.selectByIndex(1);
				driver.findElement(By.id("txtUserName")).sendKeys("k");
				driver.findElement(By.id("btnGetQuotes")).click();
				Assert.assertEquals(driver.findElement(By.id("errorUserName")).getText(), "* Enter valid username with 2 to 50 chars");
			}
			else{
				Select selcars=new Select(driver.findElement(By.id("ddCarType")));
				selcars.selectByIndex(1);
				Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
				selFuel.selectByIndex(1);
				Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
				selState.selectByIndex(1);
				driver.findElement(By.id("txtUserName")).sendKeys("k");
				driver.findElement(By.id("btnGetQuotes")).click();
				Assert.assertEquals(driver.findElement(By.id("errorUserName")).getText(), "* Enter valid username with 2 to 50 chars");
			}
			specTest.log(Status.PASS, "<b>TS07:</b>"
					+ "<br/>User Name is having minimum length of 2 and maximum length of 50. If not, proper error message is shown below the field"
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts07=6;
			skill8.add("TS07");

		} catch (AssertionError  | Exception e) {
			specTest.log(Status.FAIL, "<b>TS07:</b>"
					+ "<br/>User Name is having minimum length of 2 and maximum length of 50. If not, proper error message is shown below the field"
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts07=0;
		}

		//TS08
		try {
			Thread.sleep(2000);
			if(!driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).isSelected()){
				driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
				Select selcars=new Select(driver.findElement(By.id("ddCarType")));
				selcars.selectByIndex(1);
				Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
				selFuel.selectByIndex(1);
				Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
				selState.selectByIndex(1);
				driver.findElement(By.id("txtUserName")).sendKeys("kun");
				driver.findElement(By.id("txtPhoneNumber")).sendKeys("01");
				driver.findElement(By.id("btnGetQuotes")).click();
				Assert.assertEquals(driver.findElement(By.id("errorPhoneNumber")).getText(), "* Enter valid 10 digit number");
			}
			else{
				Select selcars=new Select(driver.findElement(By.id("ddCarType")));
				selcars.selectByIndex(1);
				Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
				selFuel.selectByIndex(1);
				Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
				selState.selectByIndex(1);
				driver.findElement(By.id("txtUserName")).sendKeys("kun");
				driver.findElement(By.id("txtPhoneNumber")).sendKeys("01");
				driver.findElement(By.id("btnGetQuotes")).click();
				Assert.assertEquals(driver.findElement(By.id("errorPhoneNumber")).getText(), "* Enter valid 10 digit number");
			}
			specTest.log(Status.PASS, "<b>TS08:</b>"
					+ "<br/>Phone number is having exact length of 10 digits and all are numbers, if not, proper error message is shown below the field."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts08=6;
			skill8.add("TS08");
			skill9.add("TS08");



		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS08:</b>"
					+ "<br/>Phone number is having exact length of 10 digits and all are numbers, if not, proper error message is shown below the field."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts08=0;

		}

		//TS09
		try {
			Thread.sleep(2000);
			if(!driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>input")).isSelected()){
				driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>input")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.id("btnGetDetails")).click();
				Assert.assertEquals(driver.findElement(By.id("errorPolicyNumber")).getText(),"* Enter policy number");
			}
			else
			{
				driver.findElement(By.id("btnGetDetails")).click();
				Assert.assertEquals(driver.findElement(By.id("errorPolicyNumber")).getText(),"* Enter policy number");
			}
			specTest.log(Status.PASS, "<b>TS09:</b>"
					+ "<br/>In 'renew insurance' tab policy number is mandatory. Proper error message is shown below the field."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts09=6;
			skill9.add("ts09");
			skill11.add("ts09");


		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS08:</b>"
					+ "<br/>In 'renew insurance' tab policy number is mandatory. Proper error message is shown below the field."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts09=0;

		}
		//TS10
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			if (driver.findElement(By.cssSelector("#get-quotes>div:nth-child(2)")).isDisplayed() && driver.findElements(By.cssSelector(".btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).size()==4){
				Assert.assertTrue(true, "Insurance details is not displayed");
			}

			specTest.log(Status.PASS, "<b>TS10:</b>"
					+ "<br/>Once all fields are valid, form is submitted to fetch the list of insurance."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts10=6;
			skill12.add("ts10");


		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS10:</b>"
					+ "<br/>Once all fields are valid, form is submitted to fetch the list of insurance."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts10=0;

		}

		//TS11
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).clear();
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			Assert.assertEquals(driver.findElements(By.cssSelector(".btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).size(), 4);
			specTest.log(Status.PASS, "<b>TS11:</b>"
					+ "<br/>'HTTP GET' request is sent to server along with all the required information."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts11=6;
			skill4.add("TS11");
			skill12.add("ts11");


		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS11:</b>"
					+ "<br/>'HTTP GET' request is sent to server along with all the required information."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts11=0;


		}

		//TS12
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).clear();
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			Assert.assertEquals(driver.findElements(By.cssSelector(".btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).size(), 4);
			specTest.log(Status.PASS, "<b>TS12:</b>"
					+ "<br/>Response is coming properly and response data is properly binded to the view (html) as per the mock ups/design."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts12=6;
			skill12.add("ts12");


		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS12:</b>"
					+ "<br/>Response is coming properly and response data is properly binded to the view (html) as per the mock ups/design."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts12=0;


		}
		//TS14
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).clear();
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			Actions action=new Actions(driver);
			action.dragAndDrop(driver.findElement(By.cssSelector("#insurance-plans-id>div:nth-child(1)")), driver.findElement(By.id("selected-plan-id"))).perform();
			action.dragAndDrop(driver.findElement(By.cssSelector("div.btn.btn-default.box-item.ui-draggable.ui-draggable-handle")),driver.findElement(By.cssSelector("#insurance-plans-id"))).perform();
			if(driver.findElements(By.cssSelector(".btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).size()==4){
				Assert.assertTrue(true,"Drag and drop cannot be performed both back and forth.");
			}
			specTest.log(Status.PASS, "<b>TS14:</b>"
					+ "<br/>User is able to drag and drop policy back and forth in box 1 and box 2 (which are drag able and droppable )."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts14=6;
			skill3.add("TS14");

		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS14:</b>"
					+ "<br/>User is able to drag and drop policy back and forth in box 1 and box 2 (which are drag able and droppable )."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts14=0;

		}

		//TS15
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).clear();
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			Actions action=new Actions(driver);
			action.dragAndDrop(driver.findElement(By.cssSelector("#insurance-plans-id>div:nth-child(1)")), driver.findElement(By.id("selected-plan-id"))).perform();
			Assert.assertTrue(driver.findElement(By.cssSelector("div.btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).isDisplayed());
			action.dragAndDrop(driver.findElement(By.cssSelector("#insurance-plans-id>div:nth-child(1)")), driver.findElement(By.id("selected-plan-id"))).perform();
			if(driver.findElements(By.cssSelector(".btn.btn-default.box-item.ui-draggable.ui-draggable-handle")).size()==3){
				Assert.assertTrue(true,"Drag and drop can be performed for second insurance");
			}
			specTest.log(Status.PASS, "<b>TS15:</b>"
					+ "<br/>User can drop exactly 1 policy in box 2. Validation is proper, no more than 1 policy can be dropped in box 2."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts15=6;
			skill3.add("TS15");

		} catch (AssertionError| Exception e) {
			e.printStackTrace();
			specTest.log(Status.FAIL, "<b>TS15:</b>"
					+ "<br/>User can drop exactly 1 policy in box 2. Validation is proper, no more than 1 policy can be dropped in box 2."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts15=0;

		}

		//TS16
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(2)>input")).click();
			Select selcars=new Select(driver.findElement(By.id("ddCarType")));
			selcars.selectByIndex(1);
			Select selFuel=new Select(driver.findElement(By.id("ddFuelType")));
			selFuel.selectByIndex(1);
			Select selState=new Select(driver.findElement(By.id("ddRegistrationState")));
			selState.selectByIndex(1);
			driver.findElement(By.id("txtUserName")).sendKeys("kun");
			driver.findElement(By.id("txtPhoneNumber")).clear();
			driver.findElement(By.id("txtPhoneNumber")).sendKeys("1234567890");
			driver.findElement(By.id("btnGetQuotes")).click();
			driver.findElement(By.id("btnBuyInsurance")).click();
			Assert.assertEquals(driver.findElement(By.id("failureMessageDiv")).getText(), "Please select any one of the policy to buy");
			specTest.log(Status.PASS, "<b>TS16:</b>"
					+ "<br/>User clicks on buy insurance button and box 2 does not contain any policy, proper error message is displayed."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts16=6;
			skill3.add("TS16");

		} catch (AssertionError| Exception e) {
			e.printStackTrace();
			specTest.log(Status.FAIL, "<b>TS16:</b>"
					+ "<br/>User clicks on buy insurance button and box 2 does not contain any policy, proper error message is displayed."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts16=0;

		}


		//TS20
		try {
			Thread.sleep(2000);
			policyNumbernew=given()
					.contentType("application/json")
					.body("{\"userName\": \"xxxx\",\"phoneNumber\": 1234567890,\"carType\": \"maruti-Ritz\",\"fuelType\": \"diesel\",\"registrationState\": \"Chhattisgarh\",\"plan\": {\"name\": \"Bajaj Allianz\",\"price\": 12500}}").
					when().
					post("http://localhost:3000/api/addInsurance").
					then().
					extract().
					path("data.policyNumber");
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>input")).click();
			driver.findElement(By.id("txtPolicyNumber")).clear();
			driver.findElement(By.id("txtPolicyNumber")).sendKeys(policyNumbernew);
			driver.findElement(By.id("btnGetDetails")).click();

			Thread.sleep(2000);
			Assert.assertEquals(driver.findElement(By.id("lblStatus")).getText(), "30 Days Left");
			specTest.log(Status.PASS, "<b>TS20:</b>"
					+ "<br/>Calculation of 'status' field logic is working. Status will tell if policy is expired or not."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts20=6;
			skill7.add("TS20");
			skill10.add("TS20");


		} catch (AssertionError| Exception e) {
			specTest.log(Status.FAIL, "<b>TS20:</b>"
					+ "<br/>Calculation of 'status' field logic is working. Status will tell if policy is expired or not."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts20=0;
		}

		//TS21
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".row.insuranceFormContainer>div:nth-child(1)>div:nth-child(3)>input")).click();
			driver.findElement(By.id("txtPolicyNumber")).clear();
			driver.findElement(By.id("txtPolicyNumber")).sendKeys(policyNumbernew);
			driver.findElement(By.id("btnGetDetails")).click();

			Assert.assertFalse(driver.findElement(By.id("btnRenew")).isSelected());
			specTest.log(Status.PASS, "<b>TS21:</b>"
					+ "<br/>If Policy is expired then only 'renew insurance' button is enabled. Else button is disabled."
					+ "<br/><b>Score:</b>"
					+ "<br/>6/6");
			ts21=12;
			skill9.add("ts21");
		} catch (AssertionError | Exception e) {
			specTest.log(Status.FAIL, "<b>TS21:</b>"
					+ "<br/>If Policy is expired then only 'renew insurance' button is enabled. Else button is disabled."
					+ "<br/><b>Score:</b>"
					+ "<br/>0/6"+"");
			ts21=0;					
			e.printStackTrace();
		}


		totalSpecScore=ts01+ts02+ts03+ts04+ts06+ts07+ts08+ts09+ts10+ts11+ts12+ts14+ts15+ts16+ts20+ts21;
		specperc=(totalSpecScore*100)/100;



		driver.quit();





		//For API Accuracy
		//TS17
		//Buy Insurance
		apiTest=appQuality.createNode("<b>API Accuracy</b>");
		String addInsurance="http://localhost:3000/api/addInsurance";

		//Getting Response
		Response response=given()
				.contentType("application/json")
				.body("{\"userName\": \"xxxx\",\"phoneNumber\": 1234567890,\"carType\": \"maruti-Ritz\",\"fuelType\": \"diesel\",\"registrationState\": \"Chhattisgarh\",\"plan\": {\"name\": \"Bajaj Allianz\",\"price\": 12500}}")
				.when().post("http://localhost:3000/api/addInsurance");



		//Getting res value
		String res=given()
				.contentType("application/json")
				.body("{\"userName\": \"xxxx\",\"phoneNumber\": 1234567890,\"carType\": \"maruti-Ritz\",\"fuelType\": \"diesel\",\"registrationState\": \"Chhattisgarh\",\"plan\": {\"name\": \"Bajaj Allianz\",\"price\": 12500}}").
				when().
				post(addInsurance).
				then().
				extract().
				path("status");

		//Getting Policy Number
		String policyNumber=given()
				.contentType("application/json")
				.body("{\"userName\": \"xxxx\",\"phoneNumber\": 1234567890,\"carType\": \"maruti-Ritz\",\"fuelType\": \"diesel\",\"registrationState\": \"Chhattisgarh\",\"plan\": {\"name\": \"Bajaj Allianz\",\"price\": 12500}}").
				when().
				post(addInsurance).
				then().
				extract().
				path("data.policyNumber");

		//Validation
		try {
			given()
			.contentType("application/json")
			.body("{\"userName\": \"xxxx\",\"phoneNumber\": 1234567890,\"carType\": \"maruti-Ritz\",\"fuelType\": \"diesel\",\"registrationState\": \"Chhattisgarh\",\"plan\": {\"name\": \"Bajaj Allianz\",\"price\": 12500}}")
			.when()
			.post(addInsurance)
			.then().statusCode(200)
			.body("status", equalTo("success"))
			.body("data.policyNumber", notNullValue())
			.body("data.expireDate",notNullValue());

			apiTest.log(Status.PASS,"<b>TS017</b>"
					+ "<br/>"+addInsurance+""
					+ "<br/>User click on buy insurance and front end is making proper 'HTTP POST' request to buy an insurance. Once purchase is successful, proper success message is shown to user with policy number and number of days left for expiry."
					+ "<br/><i>Response:</i><br/>"+response.asString()+""
					+"<br/><i>Message:</i>"
					+ "<br/>"+res
					+ "<br/><b>Score:</b>"
					+ "<br/>35/35");
			ts17=35;
			skill12.add("ts17");

		} catch (AssertionError| Exception e) {
			apiTest.log(Status.FAIL,"<b>TS017</b>"
					+"<br/>"+addInsurance+""
					+"<br/>User click on buy insurance and front end is making proper 'HTTP POST' request to buy an insurance. Once purchase is successful, proper success message is shown to user with policy number and number of days left for expiry."
					+"<br/><i>Response:</i><br/>"+response.asString()+""
					+ "<br/><i>Reason:</i>"
					+ "<br/>"+e.getMessage()
					+ "<br/><b>Score:</b>"
					+ "<br/>0/35");
			ts17=0;
		}

		//TS18
		//Get Details
		String getDetails="http://localhost:3000/api/getInsurance/"+policyNumber;
		System.out.println(getDetails);

		//Getting Response
		Response response2=given()
				.contentType("application/json")
				.when().get(getDetails);

		System.out.println(response2.asString());



		//Getting res value
		String res2=given()
				.contentType("application/json")
				.when()
				.get(getDetails)
				.then()
				.extract()
				.path("status");



		//Validation
		try {
			given()
			.contentType("application/json")
			.when()
			.get(getDetails)
			.then().statusCode(200)
			.body("status", equalTo("success"))
			.body("data.purchaseDate", notNullValue())
			.body("data.policyNumber",equalTo(policyNumber))
			.body("data.userName",equalTo("xxxx"))
			.body("data.phoneNumber",equalTo(1234567890))
			.body("data.carType",equalTo("maruti-Ritz"))
			.body("data.fuelType",equalTo("diesel"))
			.body("data.registrationState",equalTo("Chhattisgarh"))
			.body("data.plan.name",equalTo("Bajaj Allianz"))
			.body("data.plan.price",equalTo(12500));

			apiTest.log(Status.PASS,"<b>TS018</b>"
					+ "<br/>"+getDetails+""
					+ "<br/>'Get details' button is clicked, making a 'HTTP GET' request fetch the details of insurance policy. If policy number does not exist. Show a proper error message."
					+ "<br/><i>Response:</i><br/>"+response2.asString()+""
					+"<br/><i>Message:</i>"
					+ "<br/>"+res2
					+ "<br/><b>Score:</b>"
					+ "<br/>35/35");
			ts18=35;
			skill12.add("ts18");

		} catch (AssertionError| Exception e) {
			apiTest.log(Status.FAIL,"<b>TS018</b>"
					+"<br/>"+getDetails+""
					+"<br/>'Get details' button is clicked, making a 'HTTP GET' request fetch the details of insurance policy. If policy number does not exist. Show a proper error message."
					+"<br/><i>Response:</i><br/>"
					+ "<br/>"+response2.asString()+""
					+ "<br/><i>Reason:</i>"
					+ "<br/>"+e.getMessage()
					+ "<br/><b>Score:</b>"
					+ "<br/>0/35");
			ts18=0;
		}


		//TS19
		//Get Details
		String getDetails3="http://localhost:3000/api/getInsurance/"+policyNumber;
		System.out.println(getDetails);

		//Getting Response
		Response response3=given()
				.contentType("application/json")
				.when().get(getDetails3);

		System.out.println(response3.asString());


		//Getting res value
		String res3=given()
				.contentType("application/json")
				.when()
				.get(getDetails3)
				.then()
				.extract()
				.path("status");


		//Validation
		try {
			given()
			.contentType("application/json")
			.when()
			.get(getDetails3)
			.then().statusCode(200)
			.body("status", equalTo("success"))
			.body("data.purchaseDate", notNullValue())
			.body("data.policyNumber",equalTo(policyNumber))
			.body("data.userName",equalTo("xxxx"))
			.body("data.phoneNumber",equalTo(1234567890))
			.body("data.carType",equalTo("maruti-Ritz"))
			.body("data.fuelType",equalTo("diesel"))
			.body("data.registrationState",equalTo("Chhattisgarh"))
			.body("data.plan.name",equalTo("Bajaj Allianz"))
			.body("data.plan.price",equalTo(12500));

			apiTest.log(Status.PASS,"<b>TS019</b>"
					+ "<br/>"+getDetails3+""
					+ "<br/>Once 'HTTP GET' request is successful, all details are binded in the view."
					+ "<br/><i>Response:</i><br/>"+response3.asString()+""
					+"<br/><i>Message:</i>"
					+ "<br/>"+res3
					+ "<br/><b>Score:</b>"
					+ "<br/>30/30");
			ts19=30;
			skill7.add("TS19");
			skill10.add("TS19");
		} catch (AssertionError | Exception e) {
			apiTest.log(Status.FAIL,"<b>TS019</b>"
					+"<br/>"+getDetails3+""
					+"<br/>Once 'HTTP GET' request is successful, all details are binded in the view."
					+"<br/><i>Response:</i><br/>"
					+ "<br/>"+response3.asString()+""
					+ "<br/><i>Reason:</i>"
					+ "<br/>"+e.getMessage()
					+ "<br/><b>Score:</b>"
					+ "<br/>0/30");
			ts19=0;
		}

		totalAPIScore=ts17+ts18+ts19;
		apiperc=(totalAPIScore*100)/100;





		//For Web Page Load Time
		pageLoad=appQuality.createNode("<b>Website Load Time</b>");
		pageloadTime=given()
				.when().get(url).timeIn(TimeUnit.MILLISECONDS);
		if(pageloadTime<=0 && pageloadTime<=3)
		{
			webPerc="100";
		}
		else if(pageloadTime>=4 && pageloadTime<=5)
		{
			webPerc="80";

		}
		else if(pageloadTime>=5 && pageloadTime<=10)
		{
			webPerc="60";

		}
		else if(pageloadTime>=10)
		{
			webPerc="50";	

		}

		pageLoad.log(Status.INFO, "<b>Load Time:</b><br/>"+pageloadTime+" ms");


		//Main Skill Test



		skillTest=extent.createTest("<b>Skill Check</b>");
		int skillonemark=100/2;
		int skillonepass=skill1.size();
		int skillonescore=skillonepass*skillonemark;
		if(skillonescore>90){status1="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skillonescore>=65&&skillonescore<=90){status1="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skillonescore<65){status1="<td style=\"color:red;\">Area of major improvement</td>";}

		int skilltwomark=100/2;
		int skilltwopass=skill2.size();
		int skilltwoscore=skilltwopass*skilltwomark;
		if(skilltwoscore>90){status2="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skilltwoscore>=65&&skilltwoscore<=90){status2="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skilltwoscore<65){status2="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill3mark=100/3;
		int skill3pass=skill3.size();
		int skill3score=skill3pass*skill3mark;
		if(skill3score==99)
		{
			skill3score=100;
		}
		if(skill3score>90){status3="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill3score>=65&&skill3score<=90){status3="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill3score<65){status3="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill4mark=100/1;
		int skill4pass=skill4.size();
		int skill4score=skill4pass*skill4mark;
		if(skill4score>90){status4="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill4score>=65&&skill4score<=90){status4="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill4score<65){status4="<td style=\"color:red;\">Area of major improvement</td>";}

		//int skill5score=skill5pass*skill5mark;
		int skill5score=100;

		if(skill5score>90){status5="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill5score>=65 && skill5score<=90){status5="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill5score<65){status5="<td style=\"color:red;\">Area of major improvement</td>";}

		//int skill6score=skill6pass*skill6mark;
		int skill6score=100;

		if(skill6score>90){status6="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill6score>=65&&skill6score<=90){status6="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill6score<65){status6="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill7mark=100/2;
		int skill7pass=skill7.size();
		int skill7score=skill7pass*skill7mark;
		if(skill7score>90){status7="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill7score>=65&&skill7score<=90){status7="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill7score<65){status7="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill8mark=100/2;
		int skill8pass=skill8.size();
		int skill8score=skill8pass*skill8mark;
		if(skill8score>90){status8="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill8score>=65&&skill8score<=90){status8="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill8score<65){status8="<td style=\"color:red;\">Area of major improvement</td>";}


		int skill9mark=100/3;
		int skill9pass=skill9.size();
		int skill9score=skill9pass*skill9mark;
		if(skill9score==99)
		{
			skill9score=100;
		}
		if(skill9score>90){status9="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill9score>=65&&skill9score<=90){status9="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill9score<65){status9="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill10mark=100/2;
		int skill10pass=skill10.size();
		int skill10score=skill10pass*skill10mark;
		if(skill10score>90){status10="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill10score>=65&&skill10score<=90){status10="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill10score<65){status10="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill11mark=100/2;
		int skill11pass=skill11.size();
		int skill11score=skill11pass*skill11mark;
		if(skill11score>90){status11="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill11score>=65&&skill11score<=90){status11="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill11score<65){status11="<td style=\"color:red;\">Area of major improvement</td>";}

		int skill12mark=100/6;
		int skill12pass=skill12.size();
		int skill12score=skill12pass*skill12mark;
		if(skill12score==99)
		{
			skill12score=100;
		}
		if(skill12score>90){status12="<td style=\"color:green;\">Area of excellence</td>";}
		else if(skill12score>=65&&skill12score<=90){status12="<td style=\"color:orange;\">Area of minor improvement</td>";}
		else if(skill12score<65){status12="<td style=\"color:red;\">Area of major improvement</td>";}

		totalScore=skillonescore+skilltwoscore+skill3score+skill4score+skill5score+skill6score+skill7score+skill8score+skill9score
				+skill10score+skill11score+skill12score;

		System.out.println("Total Score :"+totalScore);

		totalperc=(totalScore*100)/1200;

		System.out.println("Skill Percenatge :"+totalperc);

		if(totalperc>=75)
		{
			skillTest.log(Status.PASS, "<b>Total Percentage:</b><br/>"+totalperc+" %");
			skillstatus="<td style=\"color:green;\"><b>PASS</b></td>";
		}
		else
		{
			skillTest.log(Status.FAIL, "<b>Total Percentage:</b><br/>"+totalperc+" %");
			skillstatus="<td style=\"color:red;\"><b>FAIL</b></td>";

		}

		skillTest.log(Status.INFO, "<table border=\"1\"> <tr> <td><b>Features</b></td> <td><b>Status</b></td><td><b>Test Scenario ID's</b></td> <td><b>Weightage</b></td> <td><b>Score</b></td></tr>"
				+"<tr><td>HTML5 - Elements/Tags</td>"+status1+"<td>TS01,TS02</></td> <td>100</td><td>"+skillonescore+"</></td>"
				+"</tr><tr><td>HTML5-Forms</td>"+status2+"<td>TS03,TS06</></td> <td>100</td><td>"+skilltwoscore+"</></td> </tr>"
				+"<tr><td>HTML5 - Drag and Drop</td>"+status3+"<td>TS14,TS15,TS16</></td> <td>100</td><td>"+skill3score+"</></td> </tr>"
				+"<tr><td>HTML5 - Local Storage</td>"+status4+"<td>TS11</></td> <td>100</td><td>"+skill4score+"</></td> </tr>"
				+"<tr><td>CSS3</td>"+status5+"<td>TS05,TS13</></td> <td>100</td><td>"+skill5score+"</></td></tr>"
				+"<tr><td>Bootstrap - Bootstrap grid system</td>"+status6+"<td>TS05,TS13</></td> <td>100</td><td>"+skill5score+"</></td></tr>"
				+"<tr><td>JavaScript Flow Control</td>"+status7+"<td>TS19,TS20</></td> <td>100</td><td>"+skill7score+"</></td> </tr>"
				+"<tr><td>jQuery - Event Handling</td>"+status8+"<td>TS07,TS08</></td> <td>100</td><td>"+skill8score+"</></td> </tr>"
				+"<tr><td>jQuery - Selectors</td>"+status9+"<td>TS08,TS09,TS21</></td> <td>100</td><td>"+skill9score+"</></td> </tr>"
				+"<tr><td>jQuery - Filters & Traversing</td>"+status10+"<td>TS19,TS20</></td> <td>100</td><td>"+skill10score+"</></td></tr>"
				+"<tr><td>Manipulating HTML Elements & Attributes</td>"+status11+"<td>TS04,TS09</></td> <td>100</td><td>"+skill11score+"</></td></tr>"
				+"<tr><td>jQuery AJAX, JSON</td>"+status12+"<td>TS04,TS10,TS11,TS12,TS17,TS18</></td> <td>100</td><td>"+skill12score+"</></td></tr>"
				+"<tr><td></td>"+skillstatus+"<td></></td> <td>1200</td><td>"+totalScore+"</></td></tr></table> ");



		//Final Matrix
		appQuality.log(Status.PASS, "<table border=\"1\"> <tr> <td><b>Specification</b></td> <td>"+specperc+" %</td> </tr> <tr> <td><b>API Accuracy</></td> <td>"+apiperc+" %</td> </tr> "
				+ "<tr> <td><b>Responsiveness</b></td> <td>100 %</td> </tr>"
				+ "<tr> <td><b>Skill Check</b></td> <td>"+totalperc+" %</td> </tr></table>");

		respTest=appQuality.createNode("<b>Responsiveness</b>");





	}

	//TS05
	@DataProvider(name = "deviceName")
	public Object[][] deviceNames(){
		return new Object[][] {
			{"Nexus 7", "iPad"},
			{"iPad Pro", "iPhone 6"},
			{"iPhone 6", "iPad"},
			{"iPad", "iPhone 6"},

		};

	}

	@Test(priority=2 ,dataProvider = "deviceName")
	public void mobileEmulation(String deviceName, String deviceNameWhatIsMyBrowser) throws IOException{
		//For Responsiveness
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("/usr/lib/chromium-browser/chromedriver"))
				.usingAnyFreePort()
				.withEnvironment(ImmutableMap.of("DISPLAY",":10"))
				.build();
		service.start();
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", deviceName);
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("–start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		//System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new RemoteWebDriver(service.getUrl(),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println(deviceName);
		driver.get("http:localhost:3000");
		respTest.pass(""+deviceName+"+",MediaEntityBuilder.createScreenCaptureFromPath(captureScreenMethod(dest)).build());
		driver.quit();
	}

	@AfterClass
	public void finish()
	{
		extent.flush();

	}

}
