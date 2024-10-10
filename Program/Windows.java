package jattask11;

import method.implicity.utilities.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Windows {

	public static void main(String[] args) {

		//instantiate the firefox browser
		WebDriver fox = new FirefoxDriver();
		//Maximizing the window
		fox.manage().window().maximize();
		//Global waits
		fox.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Navigating to website
		fox.get("https://the-internet.herokuapp.com/windows");
		
		CaptureScreenShot capture = new CaptureScreenShot();
		capture.fullScreen("MainWindow", fox);
		
		//Parent window
		String parentwin = fox.getWindowHandle();
		System.out.println("MainWindow : " + parentwin + "\n");
		//Element located
		WebElement here= fox.findElement(By.xpath("//div[@class='example']//child::a"));
		here.click();
		capture.fullScreen("ChildWindow", fox);
		//Child window
		Set<String> windows = fox.getWindowHandles();
		System.out.println("List of window IDs : "+ windows + "\n");
		
		//Traversing to all the set elements
		Iterator<String> it = windows.iterator();
		//While loop, check the condition set atleast have one element to check condition
		while(it.hasNext())
		{
			//next method check every element in set and store in string variable.
			String childwindow = it.next();
			//Check condition for parent and new window.
			if(!parentwin.equalsIgnoreCase(childwindow))
			{
				//Switching to new window.
				fox.switchTo().window(childwindow);
				System.out.println("======================== Child Window ========================" + "\n");
				WebElement header1 =fox.findElement(By.cssSelector("div[class='example'] h3"));
				System.out.println("Child Page Heading :         " + header1.getText());
				//Title of the current window instance.
				System.out.println("Title of the child Window :  " + fox.getTitle() + "\n");
				//Closed the new window.
				fox.close();
				System.out.println("--------------------- ChildWindow Closed! ---------------------" + "\n");
			}
			
		}
		System.out.println("======================== Parent Window ========================" + "\n");
		//Switching back to original window
		fox.switchTo().window(parentwin);
		capture.fullScreen("BackToMainWindow", fox);
		//Getting the locator of the webpage header.
		WebElement title = fox.findElement(By.cssSelector("div[class='example'] h3"));
		//Text from webpage heading.
		System.out.println("Parent Page Heading ::        " + title.getText());
		//Title of current window instance.
		System.out.println("Title of the Parent Window :: " + fox.getTitle() + "\n");
		
		//Getting window unique ID.
		String parentwin2 = fox.getWindowHandle();
		
		//Check if the previous parent window and switch window same.
		Assert.assertEquals(parentwin, parentwin2); //true 
		//Browser closed
		fox.close();
		System.out.println("----------------------- Browser Closed! -----------------------");
	}

}
