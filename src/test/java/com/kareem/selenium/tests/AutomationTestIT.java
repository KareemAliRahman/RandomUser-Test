package com.kareem.selenium.tests;

import com.kareem.selenium.DriverBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.testng.Assert;
import com.kareem.selenium.page_objects.AutoPracticeLoginPage;


public class AutomationTestIT  extends DriverBase {

   public String url = "http://automationpractice.com/index.php?controller=authentication";
   public String validEmail = "testemail@example.com";
   public String validPassword = "password";
   public WebDriver webDriver;
   public AutoPracticeLoginPage loginPage;

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

   @BeforeTest
   public void setUp() throws Exception{
     webDriver = getDriver();
     webDriver.get(url);
     loginPage = new AutoPracticeLoginPage();
   }

   @AfterTest
   public void tearDown(){
     webDriver.quit();
   }

   @Test
   public void testLoginFormPresent() {
     Assert.assertTrue(loginPage.checkLoginFormPresent() != null);
   }

   @Test
   public void testInvalidCredentialsDoesntWork() throws Exception{
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String invalidEmail = "unknown@codility.com";
     String invalidPassword = "Password";
     loginPage.enterLoginCredentials(invalidEmail, invalidPassword);
     loginPage.clickLogin();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
     Assert.assertEquals(loginPage.errorElementContents(), "Authentication failed.");
   }

   @Test
   public void testEmptyCredentialsDoesntWork() throws Exception{
     WebDriverWait wait = new WebDriverWait(webDriver, 3);
     String emptyEmail = "";
     String emptyPassword = "";
     loginPage.enterLoginCredentials(emptyEmail, emptyPassword);
     loginPage.clickLogin();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
     Assert.assertEquals(loginPage.errorElementContents(), "An email address required.");
   }

   @Test
   public void testValidCredentialsWork() throws Exception{
     WebDriverWait wait = new WebDriverWait(webDriver, 3, 100);
     loginPage.enterLoginCredentials(validEmail, validPassword);
     loginPage.clickLogin();
     Assert.assertEquals(webDriver.getTitle().toLowerCase(), "my account - my store", "should show account page");
   }
}
