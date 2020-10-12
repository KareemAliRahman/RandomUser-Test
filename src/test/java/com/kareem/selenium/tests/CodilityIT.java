package com.kareem.selenium.tests;

import com.kareem.selenium.DriverBase;
//import com.kareem.selenium.page_objects.GoogleHomePage;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.testng.Assert;

//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.util.concurrent.TimeUnit;
//import org.junit.Assert.assert.*;


public class CodilityIT extends DriverBase {

    //private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        //return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    //}

   @Test(enabled=false)
   public void testLoginFormPresent() throws Exception{
     WebDriver webDriver = getDriver();
     List<WebElement> email = webDriver.findElements(By.id("email-input"));
     List<WebElement> password= webDriver.findElements(By.id("password-input"));
     List<WebElement> button = webDriver.findElements(By.id("login-button"));
     Assert.assertTrue(!email.isEmpty());
     Assert.assertTrue(!password.isEmpty());
     Assert.assertTrue(!button.isEmpty());
   }

   public void enterEmailPassword(String emailInput, String passwordInput) throws Exception{
     WebDriver webDriver = getDriver();
     WebElement email = webDriver.findElement(By.id("email-input"));
     WebElement password= webDriver.findElement(By.id("password-input"));
     WebElement button = webDriver.findElement(By.id("login-button"));
     email.sendKeys(emailInput);
     password.sendKeys(passwordInput);
     button.click();
   }

   @Test(enabled=false)
   public void testValidCredentialsWork() throws Exception{
     WebDriver webDriver = getDriver();
     //WebwebDriverWait
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String validEmail = "login@codility.com";
     String validPassword = "password";
     enterEmailPassword(validEmail, validPassword);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.success")));
     WebElement msg = webDriver.findElement(By.cssSelector(".message.success"));
     //Assert.assertEquals(msg.getText(), "Welcome to Codility");
     Assert.assertTrue(msg.getText()=="Welcome to Codility");
   }

   @Test(enabled=false)
   public void testInvalidCredentialsDoesntWork() throws Exception{
     WebDriver webDriver = getDriver();
     //WebwebDriverWait
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String invalidEmail = "unknown@codility.com";
     String invalidPassword = "Password";
     enterEmailPassword(invalidEmail, invalidPassword);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.error")));
     WebElement msg = webDriver.findElement(By.cssSelector(".message.error"));
     Assert.assertEquals(msg.getText(), "You shall not pass! Arr!");
   }

   @Test(enabled=false)
   public void testInvalidEmailFormDoesntWork() throws Exception{
     WebDriver webDriver = getDriver();
     //WebwebDriverWait
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String invalidEmail = "unknown@codility";
     String invalidPassword = "Password";
     enterEmailPassword(invalidEmail, invalidPassword);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation.error")));
     WebElement msg = webDriver.findElement(By.cssSelector(".validation.error"));
     Assert.assertEquals(msg.getText(), "Enter a valid email");
   }

   @Test(enabled=false)
   public void testEmptyCredentialsDoesntWork() throws Exception{
     WebDriver webDriver = getDriver();
     //WebwebDriverWait
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String emptyEmail = "";
     String emptyPassword = "";
     String validEmail = "login@codility.com";
     String validPassword = "password";
     enterEmailPassword(emptyEmail, validPassword);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation.error")));
     WebElement msg = webDriver.findElement(By.cssSelector(".validation.error"));
     Assert.assertEquals(msg.getText(), "Email is required");
     enterEmailPassword(validEmail, emptyPassword);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".validation.error")));
     msg = webDriver.findElement(By.cssSelector(".validation.error"));
     Assert.assertEquals(msg.getText(), "Password is required");
   }
}
