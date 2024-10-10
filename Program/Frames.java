package jattask11;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class Frames {

	//Expected string value to compare the actual. 
	static String frame_left = "LEFT";
	static String frame_middle = "MIDDLE";
	static String frame_right = "RIGHT";
	static String frame_bottom = "BOTTOM";
	static String pagetitle = "Frames";
	
	public static void main(String[] args) {
		
		//Webdriver instance
		WebDriver chrome = new ChromeDriver();
		//Maximizing browser
		chrome.manage().window().maximize();
		//Global wait
		chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			//WebDriverWait ewait = new WebDriverWait(chrome,Duration.ofSeconds(15));
		//Navigating to URL
		chrome.navigate().to("http://the-internet.herokuapp.com/nested_frames");
		//Locating Top-frame
		//=================================Switched to the topframe-left===================================
		chrome.switchTo().frame("frame-top");
		//Locating to top-frame--> frameset---> Frame-LEFT
		chrome.switchTo().frame("frame-left");
		String left = chrome.findElement(By.cssSelector("body")).getText();
		System.out.println("Topframe-left text : " + left);
		
		//Compares the actual and expected results
		Assert.assertEquals(frame_left, left);
		System.out.println("The left frame has a text LEFT verified!" + "\n");
		
		//=================================Switched to the topframe-middle===================================
		chrome.switchTo().parentFrame();
		//Switching to top middle frame.
		chrome.switchTo().frame("frame-middle");
		String middle = chrome.findElement(By.cssSelector("#content")).getText();
		System.out.println("Topframe-middle text : " + middle);
		
		//Compares the actual and expected results
		Assert.assertEquals(frame_middle, middle);
		System.out.println("The middle frame has a text MIDDLE verified!" + "\n");
		
		//=================================Switched to the topframe-right===================================
		chrome.switchTo().parentFrame();
		//Switching to top right frame
		chrome.switchTo().frame("frame-right");
		String right = chrome.findElement(By.cssSelector("body")).getText();
		System.out.println("Topframe-right text : " + right);
		
		//Compares the actual and expected results
		Assert.assertEquals(frame_right, right);
		System.out.println("The right frame has a text RIGHT verified!" + "\n");
		
		//=================================Switched to the Bottomframe===================================
		chrome.switchTo().defaultContent();
		
		//Switching to Bottom-frame
		chrome.switchTo().frame("frame-bottom");
		String bottom = chrome.findElement(By.cssSelector("body")).getText();
		System.out.println("Bottom-frame text : " + bottom);
		
		//Compares the actual and expected results
		Assert.assertEquals(frame_bottom, bottom);
		System.out.println("The Bottom-frame has a text BOTTOM verified!" + "\n");
		
		//=================================Switched Back tp Top-frame ====================================
		//Switched to default Main frame
		chrome.switchTo().defaultContent();
		//Back to top frame
		chrome.switchTo().frame("frame-top");
		int sizes = chrome.findElements(By.cssSelector("frame")).size();
		System.out.println("Size of the Top-Frame : " + sizes + "\n");
		
		//Comparing the title with expected and actual.
		String actualtitle = chrome.getTitle();
		System.out.println(actualtitle);
		Assert.assertEquals(actualtitle, pagetitle);
		
		}
		catch(NoSuchElementException e)
		{
			System.out.println("CANNOT FIND THE ELEMENT" + e.getMessage());
		}
		catch(NoSuchFrameException f)
		{
			System.out.println("CANNOT FIND THE FRAME" + f.getMessage());
		}
		catch(AssertionError a)
		{
			System.out.println("ASSERTION FAILS " + a.getMessage());
		}
	
	}
}