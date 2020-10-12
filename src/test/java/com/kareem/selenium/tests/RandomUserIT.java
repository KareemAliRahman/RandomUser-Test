package com.kareem.selenium.tests;

import com.kareem.selenium.DriverBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class RandomUserIT extends DriverBase {

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    @Test
    public void randomUserTwitterLink() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();
        

        // And now use this to visit Google
        driver.get("https://randomuser.me/");

        WebElement img = driver.findElement(By.xpath("//div[contains(@class, 'card')]//img"));
        //Assert.assertNotEquals(, 0, "image is not present");
        //Assert.assertTrue(img.isDisplayed());

        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].hidden = true",img);
        //List<WebElement> imgs1 = driver.findElements(By.xpath("//div[contains(@class, 'card')]//img"));
        //Assert.assertTrue(imgs1.get(0).isDisplayed());

        WebElement link = driver.findElement(By.className("twitter"));
        String linkText = link.getAttribute("href");
        Assert.assertEquals(linkText, "https://twitter.com/randomapi", "href link is not what is expected");
        Assert.assertTrue(link.isDisplayed());
        
        
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        //GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        //System.out.println("Page title is: " + driver.getTitle());

        //googleHomePage.enterSearchTerm("Cheese")
                //.submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        //WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        //wait.until(pageTitleStartsWith("Cheese"));

        //System.out.println("Page title is: " + driver.getTitle());
    }

}
