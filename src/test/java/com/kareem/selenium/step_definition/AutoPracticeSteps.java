package com.kareem.selenium.step_definition;

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
import cucumber.api.java.en.*;

public class AutoPracticeSteps extends DriverBase {
   public String url = "http://automationpractice.com/index.php?controller=authentication";
   private WebDriver webDriver;
   private AutoPracticeLoginPage loginPage;

    @BeforeTest
    public void setup() throws Exception{
      webDriver = getDriver();
      loginPage = new AutoPracticeLoginPage();
    }


    @Given("I am on AutomationPractice login page")
    public void I_visit_login_page() {
        webDriver.get(url);
    }

    @When("I login with valid credentials with {email} and {password}")
    public void search_for(String email, String password) {
      loginPage.enterLoginCredentials(email, password);
      loginPage.clickLogin();
   }

   @Then("the page title should start with {string}")
   public void checkTitle(String titleStartsWith) {
       new WebDriverWait(webDriver,10L).until(new ExpectedCondition<Boolean>() {
           public Boolean apply(WebDriver d) {
               return d.getTitle().toLowerCase().startsWith(titleStartsWith);
           }
       });
   }

   @AfterTest()
   public void closeBrowser() {
       webDriver.quit();
   }
}
