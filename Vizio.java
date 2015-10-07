//import java.awt.List;
//import java.sql.Driver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;


//import com.opera.core.systems.internal.input.KeyEvent;



//import com.opera.core.systems.internal.input.KeyEvent;



import java.io.FileWriter;
import java.io.IOException;



public class Vizio{

WebDriver _driver, _driver2;
String resFile="/Users/mbhuiyan/Documents/Selenium/Workspace/Visio/Report/Report.csv";

FileWriter writer;
@Before
public void setUp() throws IOException{
   System.setProperty("webdriver.chrome.driver", "/Users/mbhuiyan/Documents/Selenium/ChormeDriver/chromedriver");
  _driver=new ChromeDriver();
  writer = new FileWriter(resFile);
  writer.append("TCNO,Test Case,Result\n");
}

@Test
public void testMethod() throws InterruptedException, IOException{

    String baseUrl = "http://tv.iheart.com/vizio/";
    String SecUrl = "http://www.iheart.com/activate/";
      
   try{  
      writer.append(",Test Activation Start==============,\n"); 
    
	OpenUrl(_driver, baseUrl);
	
	writer.append("TC01,Open Browser,Pass\n");  
	  	
	
		Thread.sleep(4000);
	String activeCode= _driver.findElement(By.id("activationCode")).getText(); 	
		Thread.sleep(4000);
	System.out.println(_driver.getTitle());
	System.out.println("Activation code for Sign up/Sign in:"+ activeCode);
	writer.append("TC02,Activation Code,Pass\n");  
		
	_driver2 =new ChromeDriver();
	
	OpenUrl(_driver2, SecUrl);
	System.out.println(_driver2.getTitle());
		
		Thread.sleep(3000);
	ClickByLinkText(_driver2, "Sign up");
		Thread.sleep(2000);
	EnterTextByName(_driver2,"userName",CreateEmail());
		Thread.sleep(500);
	EnterTextByName(_driver2,"password","abcd1234");
		Thread.sleep(500);
	EnterTextByName(_driver2,"zipCode","11218");
		Thread.sleep(500);
	_driver2.findElement(By.name("birthYear")).sendKeys("1980");
		Thread.sleep(500);
	ClickByName(_driver2,"gender");
		Thread.sleep(500);
	ClickByName(_driver2,"termsAcceptanceDate");
		Thread.sleep(2000);
	ClickByXpath(_driver2,"//button[@type='submit']");	
		Thread.sleep(4000);
	EnterTextByName(_driver2,"code",activeCode);
		Thread.sleep(3000);
	ClickByXpath(_driver2,"//button[@type='submit']");
		Thread.sleep(1000); 
	_driver2.close();
		
		
		
		
		Thread.sleep(4000);	
	ClickById(_driver,"bottomButton");
		Thread.sleep(500);
	System.out.println("Activation Process Done here via Sign up:");
	writer.append("TC03,Sign-IN/Sign Up ...,Pass\n");
		//Activation Process Done here
		
		
		//Select Genre Card 
	ClickByXpath(_driver,"//*[@id='genreGrid']/div/div[1]/div[2]/div[1]"); 
		Thread.sleep(500);
	ClickById(_driver,"genreContinueButton");
		Thread.sleep(500);
		
		//Select Station Card from For you
	ClickByXpath(_driver,"//*[@id='forYouGrid']/div/div[2]/div/div[1]/div[2]"); 
		Thread.sleep(10000);	
	 System.out.println(_driver.getTitle());
	
	
	
	// Log out
	//ClickById(_driver,"settingNav"); 
	//Thread.sleep(2000);
	//ClickById(_driver,"deactivateBtn"); 
	//Thread.sleep(1000);
	//ClickById(_driver,"deactivateConfirmBtn"); 
	//Thread.sleep(1000);
	
	//Log In
    //_driver2 =new ChromeDriver();
	
	//OpenUrl(_driver2, SecUrl);
	//System.out.println(_driver2.getTitle());
		//Thread.sleep(3000);
	//_driver2.findElement(By.name("username")).sendKeys("test771@me.com");
	//_driver2.findElement(By.name("password")).sendKeys("test"); 
	//_driver2.findElement(By.id("Login")).click();
	
	
	
	
	
		_driver.close();
	
	writer.append(",Test Activation End==============,\n"); 
	
  } catch(Exception e){
	  String msg=e.getMessage();
	  writer.append(",Test Test Execution, Fail\n"); 
	  writer.append(",Test  End==============,\n");
	  writer.append("\n\n\n************************** Error Description ***************************\n"+msg);
  } 
	
      
}

@After
public void tearDown() throws InterruptedException, IOException{
	writer.flush();
    writer.close();
}

public void OpenUrl(WebDriver WD, String vUrl){
	 WD.get(vUrl);
     WD.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 WD.manage().window().maximize();
}

public void ClickById (WebDriver WD, String iD){
	WD.findElement(By.id(iD)).click();
}

public void ClickByLinkText (WebDriver WD, String lInkText){
	WD.findElement(By.linkText(lInkText)).click();
}

public void EnterTextByName(WebDriver WD, String eleName, String eText){
	WD.findElement(By.name(eleName)).clear();
	WD.findElement(By.name(eleName)).sendKeys(eText);
}

public void ClickAndWaitByXpath(WebDriver WD, String xPath, Long wTime) throws Exception{
	WD.findElement(By.xpath(xPath)).click();
	Thread.sleep(wTime);
}

public void ClickByXpath(WebDriver WD, String xPath){
	WD.findElement(By.xpath(xPath)).click();
}

public void ClickByName(WebDriver WD, String nAme){
	WD.findElement(By.name(nAme)).click();
}

public String GetTextAttByXpath(WebDriver WD, String xPath){	
	return WD.findElement(By.xpath(xPath)).getAttribute("value");
}

public String GetTextAttById(WebDriver WD, String ID){	
	return WD.findElement(By.id(ID)).getAttribute("value");
}


/*public void keyPressed(KeyEvent e) {

    int key = e.getKey();

    if (key == KeyEvent.VK_LEFT) {
        dx = -1;
    }

    if (key == KeyEvent.VK_RIGHT) {
        dx = 1;
    }

    if (key == KeyEvent.VK_UP) {
        dy = -1;
    }

    if (key == KeyEvent.VK_DOWN) {
        dy = 1;
    }
    if (key == KeyEvent)
}  */


public String CreateEmail(){	
	Random rand = new Random();
	String mEmail;
	int  n = rand.nextInt(1000) + 1;
	mEmail="vizio"+String.valueOf(n)+"@maillinator.com";
	return mEmail;
}

public Boolean IsTextPresent(WebDriver WD, String textToVerify){
	try{ 
			if (WD.findElement(By.xpath(textToVerify)) != null){
				return true;
			}
	   } catch (Exception e){
	       return false;
	   }
	
	return false;
	}
}
