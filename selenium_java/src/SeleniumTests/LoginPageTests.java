package SeleniumTests;

import java.util.Objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class LoginPageTests {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\selenium-java-3.141.59\\chromewebdriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		//Start test1
		test1(driver,"testuser1234","123qweASD");		
	}
	
	//Test1 - Correct username and password
	 static void test1(WebDriver driver,String username, String pass) throws InterruptedException
	 {		
		 System.out.println("\n"+"Test 1 ///////////////////////////////////////////////////////////////"); // Start of test 1
		 //open forums.tomshardware.com page
		 driver.get("https://forums.tomshardware.com/");
		 
		 Thread.sleep(2000);		 
		
		 driver.findElement(By.cssSelector("button[class=' css-flk0bs'][type='button']")).click(); //Agree with site cookies 
		 driver.findElement(By.className("p-navgroup-linkText")).click();  //Open user panel
		 
		 Thread.sleep(2000);
		 //Fill the form with correct username and password and click "Log in"
		 driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-1-')]")).sendKeys(username);
		 driver.findElement(By.xpath("//input[contains(@class,'input js-password input--passwordHideShow') and contains(@id,'_xfUid-2-')]")).sendKeys(pass);
		 driver.findElement(By.cssSelector("button[class='button--primary button button--icon button--icon--login'][type='submit']")).click();
		 
		 Thread.sleep(2000);
		 
		 String expectedUrl="https://forums.tomshardware.com/"; //expected URL after log in	 
		 String actualUrl= driver.getCurrentUrl(); // Actual URL after log in
		
		 String user =  driver.findElement(By.xpath("//*[@class='p-navgroup-linkText']")).getText();  //Get the username after logging in
		 //Check if the user is logged in		
		  if(actualUrl.equals(expectedUrl) && username.equals(user))			  
		  {
			  System.out.println("Test with correct password and username, the user should be logged in - successful"); //Print message for correct result
			  
	
			  driver.findElement(By.className("p-navgroup-linkText")).click(); //Open user panel
			  
			  Thread.sleep(2000);
			  
			  driver.findElement(By.linkText("Log out")).click();  //Click the log out button
			  test2(driver,username,pass); // Start test2
		  }
		  else
		  {
			  System.out.println("Test with correct password and username, the user should be logged in - unsuccessful");  //Print message for incorrect result
		  } 
	   }
	 
   	//Test2 - Correct username and incorrect password
	  static void test2(WebDriver driver, String username, String pass) throws InterruptedException 
	  {
		  System.out.println("\n"+"Test 2 ///////////////////////////////////////////////////////////////"); // Start of test 2
		  Thread.sleep(2000);
		  driver.findElement(By.className("p-navgroup-linkText")).click();  //Open user panel
		  Thread.sleep(2000);
		  
		//Fill the form with correct username and wrong password and click "Log in"
		  driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-1-')]")).sendKeys(username);
		  driver.findElement(By.xpath("//input[contains(@class,'input js-password input--passwordHideShow') and contains(@id,'_xfUid-2-')]")).sendKeys("11111111");
		  driver.findElement(By.cssSelector("button[class='button--primary button button--icon button--icon--login'][type='submit']")).click();
		  
		  Thread.sleep(2000);		
		  
			 String expectedUrl = "https://forums.tomshardware.com/login/login"; //Expected URL after log in	 
			  String actualUrl = driver.getCurrentUrl(); //Actual URL after log in
			  
			//Check if the user is logged in	
			  if(Objects.equals(expectedUrl, actualUrl))			  
			  {
				  System.out.println("Test with correct username and wrong password, the user should be redirected to the login page - successful"); //Print message for correct result	
				  
				  //Check for error message
				  String message = driver.findElement(By.xpath("//div[contains(@class,'blockMessage blockMessage--error blockMessage--iconic')]")).getText();
				  if(message.equals("Incorrect password. Please try again."))
				  {
					  System.out.println("Message for incorrect username - successful");// Print message for correct error message
				  }
				  else
				  {
					  System.out.println("Message for incorrect username - unsuccessful");// Print message for incorrect error message
				  }
				  test3(driver, username, pass);  //Start test3
			  }
			  else
			  {
				  System.out.println("Test with correct username and wrong password, the user should be redirected to the login page - unsuccessful"); //Print message for incorrect result
			  } 
			  
	  }
	  
	  //Test 3 - Empty username and password
	  static void test3(WebDriver driver, String username, String pass) throws InterruptedException 
	  {		 
		  System.out.println("\n"+"Test 3 ///////////////////////////////////////////////////////////////"); // Start of test 3
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-1-')]")).clear(); // Clean the username field 
		 
		  Thread.sleep(1000);
		// Try to log in
		  driver.findElement(By.cssSelector("button[class='button--primary button button--icon button--icon--login rippleButton'][type='submit']")).click();
		  
		  Thread.sleep(2000);
			 String expectedUrl = "https://forums.tomshardware.com/login/login"; //Expected URL after the test
			  String actualUrl = driver.getCurrentUrl(); //Actual URL after the test
			  
			//Check if the user is logged in	
			  if(Objects.equals(expectedUrl, actualUrl))			  
			  {
				  System.out.println("Test with empty username and password, the user should be redirected to the login page - successful"); //Print message for correct result
				  
				  //Check for error message
				  String message = driver.findElement(By.xpath("//div[contains(@class,'blockMessage blockMessage--error blockMessage--iconic')]")).getText();
				  if(message.equals("The requested user could not be found."))
				  {
					  System.out.println("Message for empty or unregistered user - successful");// Print message for correct error message
				  }
				  else
				  {
					  System.out.println("Message for empty or unregistered user - unsuccessful");// Print message for incorrect error message
				  }
				  
				  test4(driver,username,pass); // Start test 4
			  }
			  else
			  {
				  System.out.println("Test with empty username and password, the user should be redirected to the login page - unsuccessful"); //Print message for incorrect result
			  } 
			  
	  }
	  
	  //Test 4 - Correct username and empty password
	  static void test4(WebDriver driver, String username, String pass) throws InterruptedException 
	  {
		  System.out.println("\n"+"Test 4 ///////////////////////////////////////////////////////////////"); // Start of test 4
		  Thread.sleep(2000);
		  //Fill the username field and click "Log in"
		  driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-1-')]")).sendKeys(username);			
		  driver.findElement(By.cssSelector("button[class='button--primary button button--icon button--icon--login rippleButton'][type='submit']")).click();
		   
		     Thread.sleep(2000);
			 String expectedUrl="https://forums.tomshardware.com/login/login"; //Expected URL after the test
			 String actualUrl= driver.getCurrentUrl(); //Actual URL after the test
			  
			//Check if the user is logged in	
			  if(Objects.equals(expectedUrl, actualUrl))			  
			  {
				  System.out.println("Test with correct username and empty password, the user should be redirected to the login page - successful"); //Print message for correct result	
				  
				  //Check for error message
				  String message = driver.findElement(By.xpath("//div[contains(@class,'blockMessage blockMessage--error blockMessage--iconic')]")).getText();
				  if(message.equals("Incorrect password. Please try again."))
				  {
					  System.out.println("Message for incorrect username - successful");// Print message for correct error message
				  }
				  else
				  {
					  System.out.println("Message for incorrect username - unsuccessful");// Print message for incorrect error message
				  }
				  
				  test5(driver); // Start test 5
			  }
			  else
			  {
				  System.out.println("Test with correct username and empty password, the user should be redirected to the login page - unsuccessful"); //Print message for incorrect result
			  } 
	  }
	  
	  //Test 5 - Check the password default type 
	  static void test5(WebDriver driver) throws InterruptedException 
	  {		 
		  System.out.println("\n"+"Test 5 ///////////////////////////////////////////////////////////////"); // Start of test 5
		  Thread.sleep(2000);
		  //Fill the password field(not required)
		  driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-2-')]")).sendKeys("111111111");		
		  
		  //Check if the password field is "password" type
		  if (driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-2-')]")).getAttribute("type").equals("password"))
		  {
			  
			  System.out.println("Test the type of the password field, should be 'password' type - successful"); //Print message for correct result	
			  test6(driver);// Start test 6
		  }
		  else
		  {
			  System.out.println("Test the type of the password field, should be 'password' type - unsuccessful"); //Print message for incorrect result
		  } 		  
	  }
	  
	  //Test 6 - Check the password type after click of "Show" button
	  static void test6(WebDriver driver) throws InterruptedException 
	  {	
		  System.out.println("\n"+"Test 6 ///////////////////////////////////////////////////////////////"); // Start of test 6
		  Thread.sleep(2000);
		  //Click the "Show" button
		  driver.findElement(By.xpath("//span[contains(@class,'iconic-label') and contains(text(), 'Show')]")).click();
		  //Check if the password field is "text" type
		  if (driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-2-')]")).getAttribute("type").equals("text"))
		  {
			  System.out.println("Test the type of the password field, should be 'text' type after unhide - successful"); //Print message for correct result	
			  test7(driver); // Start test 7
		  }
		  else
		  {
			  System.out.println("Test the type of the password field, should be 'text' type after unhide - unsuccessful"); //Print message for incorrect result
		  }
	  }
	  
	  //Test 7 - Check the "Forgot your password?" button
	  static void test7(WebDriver driver) throws InterruptedException 
	  {			
		  System.out.println("\n"+"Test 7 ///////////////////////////////////////////////////////////////"); // Start of test 7
		  Thread.sleep(2000);
		  driver.findElement(By.className("uix_forgotPassWord__link")).click(); //Click the "Forgot your password?" button
		  Thread.sleep(2000);
		  
		  // Fill the field with example data and click the button
		  driver.findElement(By.xpath("//input[contains(@class,'input') and contains(@id,'_xfUid-1') and contains(@name,'email')]")).sendKeys("asd@gmail.com");	
		  driver.findElement(By.cssSelector("button[class='button--primary button'][type='submit']")).click();
		  
		  Thread.sleep(2000);
		  String expectedUrl = "https://forums.tomshardware.com/lost-password/requested";  //Expected URL after the test		 
		  String actualUrl = driver.getCurrentUrl();  //Actual URL after the test	
					
		  //Check the current page
			  if(actualUrl.equals(expectedUrl))	
		  {
			  System.out.println("Test the 'Forgot your password?' button, the user should be reddirected after sibmit email - successful"); //Print message for correct result	
			  test8(driver); //Start test 8
		  }
		  else
		  {
			  System.out.println("Test the 'Forgot your password?' button, the user should be reddirected after sibmit email - unsuccessful"); //Print message for incorrect result
		  }
	  }
	  
	  //Test 8 - Check the "Register now" button
	  static void test8(WebDriver driver) throws InterruptedException
	  {      
		  System.out.println("\n"+"Test 8 ///////////////////////////////////////////////////////////////"); // Start of test 8
	      driver.navigate().back(); // Return to the previous page
	      Thread.sleep(2000);
	      driver.findElement(By.xpath("//*[contains(text(), 'Register now')]")).click(); // Click the "Register now" button
	  
		  Thread.sleep(2000);
		  String expectedUrl="https://forums.tomshardware.com/register/"; 		 
			 String actualUrl= driver.getCurrentUrl(); 	
			 
			 //Check the current page		
			  if(actualUrl.equals(expectedUrl))	
		  {
			  System.out.println("Test the 'Register now' button, the user should be reddirected to register page - successful"); //Print message for correct result				 
		  }
		  else
		  {
			  System.out.println("Test the 'Register now' button, the user should be reddirected to register page - unsuccessful"); //Print message for incorrect result
		  } 
	  }
}
